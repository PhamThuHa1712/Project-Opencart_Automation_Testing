package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	private static ThreadLocal<ExtentSparkReporter> sparkThread = new ThreadLocal<>();
    
    // Sử dụng ThreadLocal để quản lý riêng biệt từng Instance cho mỗi luồng
    private static ThreadLocal<ExtentReports> extentThread = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ThreadLocal<String> repNameThread = new ThreadLocal<>();
    
    private String getTestName(ITestResult result) {
        Object[] params = result.getParameters();
        String caseName = "";
        if (params != null && params.length > 0) {
        		caseName = params[0].toString(); // caseName
        }

        return result.getTestClass().getName() + "-" + result.getMethod().getMethodName() + (!caseName.equals("") ? "-" : "") + caseName;
    }

    @Override
    public void onStart(ITestContext testContext) {
        // 1. Tạo tên báo cáo riêng biệt cho từng Test Tag trong XML
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String testName = testContext.getName();
        String repName = "Test-Report-" + testName + "-" + timeStamp + ".html";
        repNameThread.set(repName); // Lưu tên file vào ThreadLocal

        // 2. Cấu hình giao diện SparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkThread.set(sparkReporter);

        // 3. Khởi tạo ExtentReports riêng cho luồng này
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkThread.get());
        
        // 4. Thiết lập thông tin hệ thống (Mỗi file sẽ chỉ chứa thông tin của đúng thẻ <test> đó)
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }

        // 5. Lưu đối tượng extent vào ThreadLocal của luồng hiện tại
        extentThread.set(extent);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Lấy đối tượng extent của luồng hiện tại để tạo test
        ExtentTest test = extentThread.get().createTest(getTestName(result)); // String fullName = result.getTestClass().getName() + "."  + result.getMethod().getMethodName();
        testThread.set(test); // Lưu vào ThreadLocal để các bước sau (failure) dùng tới
        
        testThread.get().assignCategory(result.getMethod().getGroups());
        testThread.get().log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentThread.get().createTest(getTestName(result));
        testThread.set(test);

        testThread.get().assignCategory(result.getMethod().getGroups());
        testThread.get().log(Status.FAIL, result.getName() + " got failed");
        testThread.get().log(Status.INFO, result.getThrowable().getMessage());

        try {
            BaseClass currentTestInstance = (BaseClass) result.getInstance();
            String imgPath = currentTestInstance.captureScreen(result.getName());
            testThread.get().addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentThread.get().createTest(getTestName(result));
        testThread.set(test);
        
        testThread.get().assignCategory(result.getMethod().getGroups());
        testThread.get().log(Status.SKIP, result.getName() + " got skipped");
        testThread.get().log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        // Xuất báo cáo cho luồng hiện tại
        if (extentThread.get() != null) {
            extentThread.get().flush();
        }

        // Mở file báo cáo tương ứng của luồng này
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repNameThread.get();
        File extentReport = new File(pathOfExtentReport);

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(extentReport.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Xóa dữ liệu ThreadLocal sau khi kết thúc để tránh rò rỉ bộ nhớ (Memory Leak)
        sparkThread.remove();
        extentThread.remove();
        testThread.remove();
        repNameThread.remove();
    }
}