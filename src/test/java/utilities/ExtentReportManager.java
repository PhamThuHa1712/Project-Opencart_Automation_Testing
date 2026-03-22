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

// Nếu kiểm thử đa trình duyệt thì nó sẽ tạo ra nhiều báo cáo: Cho trình duyệt Chrome, edge, firefox
public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // testContext là cta đang thực thi phương thức kiểm thử nào, chi tiết của phương thức kiểm thử đó sẽ được lưu trong biến này
    public void onStart(ITestContext testContext) {
        
    		/*
        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt=new Date();
        String currentdatetimestamp=df.format(dt);
        */
    		// Thay vì phải viết 3 dòng trên thì ta có thể viết thành 1 dòng
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
       
        // Nối tên báo cáo với dấu thời gian
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

        sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
        sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        /*
        Module "Admin" (Phân hệ Quản trị): Một hệ thống thương mại điện tử như OpenCart thường được chia thành 2 mảng chính: Trang dành cho khách mua hàng (Storefront) và Trang quản trị dành cho chủ shop (Admin panel). Đặt Module là "Admin" chỉ ra rằng các kịch bản test (test cases) này đang được chạy và kiểm tra trên hệ thống của Quản trị viên.
		Sub Module "Customers" (Chức năng quản lý khách hàng): Bên trong trang Admin lại có rất nhiều mục quản lý khác nhau như: Quản lý sản phẩm (Catalog), Quản lý đơn hàng (Sales), Cài đặt hệ thống (Settings), và Quản lý khách hàng (Customers).	
		Tóm lại: Người viết test script thiết lập thông tin này nhằm mục đích phân loại và dán nhãn cho báo cáo. Khi báo cáo (Report) được xuất ra, đội ngũ phát triển hoặc QA nhìn vào sẽ biết ngay lập tức rằng: "Các lỗi hoặc kết quả test trong báo cáo này thuộc về phần Quản lý tài khoản khách hàng, nằm trong hệ thống Admin của ứng dụng OpenCart."
        */
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");

        // Từ testContext có thể lấy được test XML hiện tại và lấy được tham số os và browser
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // Lấy ra tên các nhóm mà ta đã chỉ định trong phần include
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }
    // Từ result nó sẽ lấy được lớp và từ lớp lấy được tên lớp, từ đó nó tạo một mục testcase trong báo cáo
    // Khi phương thức onTestSuccess được kích hoạt, result sẽ thu thập tất cả thông tin kết quả
    // từ test method. Từ result đó cta sẽ biết được lớp nào đã được thực thi
    public void onTestSuccess(ITestResult result) {
        // Từ result lấy được tên lớp và từ tên lớp tạo 1 mục nhập trong báo cáo
        test = extent.createTest(result.getTestClass().getName());
        // Từ bài kiểm thử cụ thể đó, bất kỳ phương thức kiểm thử nào được lấy tôi thực thi phương thức đó 
        // và lấy nhóm mà phương thức kiểm thử được gán 
        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
        test.log(Status.PASS, result.getName()+" got successfully executed");
        
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        // từ result lấy phương thức kiểm thử mà ta đã thực thi, từ phương thức kiểm thử lấy các nhóm 
        // phương thức kiểm thử thuộc nhóm nào nó sẽ ghi lại các nhóm đó và các nhóm đó sẽ được đính kèm 
        // vào báo cáo theo từng danh mục 
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName()+" got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        // Đính kèm ảnh chụp màn hình của lỗi
        try {
        		// Thêm phương thức captureScreen vào lớp BaseClass vì phương thức này là chung cho tất cả các trường hợp kiểm thử
        		// captureScreen() ko phải là phương thức static nên ko thể truy cập mà ko tạo đối tượng nên phải dùng new BaseClass().captureScreen chỉ là ko lưu đối tượng vào 1 biến thôi
            //String imgPath = new BaseClass().captureScreen(result.getName()); // nhận tên phương thức kiểm thử làm tham số 
        		BaseClass currentTestInstance = (BaseClass) result.getInstance();
        		String imgPath = currentTestInstance.captureScreen(result.getName());
        	
        		/*
            "Chúng ta cần đảm bảo web driver là static trong base class (lớp cơ sở) bởi vì chúng ta tham chiếu đến cùng một instance (phiên bản) driver trong class tiện ích extent report. Xem này, nếu tôi quay lại class tiện ích extent report, ok, trong class này chúng ta đang gọi phương thức captureScreen (chụp màn hình) đúng không? Ở đây chúng ta gọi phương thức captureScreen và truyền vào tên file, tên class.
			Sau đó nó sẽ đi đến base class. Trong base class, nó sẽ gọi captureScreen và lấy tên, nhưng bên trong phương thức này chúng ta đang sử dụng biến driver đúng không? Về cơ bản, một driver sẽ được tạo ở đây. Và từ extent report manager, chúng ta đang tạo một object (đối tượng) mới. Bất cứ khi nào bạn tạo một object mới cho Base Class, thì chuyện gì sẽ xảy ra? Base Class sẽ có một object khác, và object đó cũng có một driver mới đúng không?
			Thấy chưa, lúc này sẽ có hai driver được tạo ra. Đầu tiên, trong base class chúng ta đã tạo sẵn một instance driver rồi. Bây giờ, trong extent report manager chúng ta lại tạo một object của base class, nên bên trong object đó lại có thêm một driver nữa.
			Vì vậy, driver của base class (đang chạy test) và driver của object mới (dùng để chụp ảnh) không giống nhau. Ngay khi object được tạo, nó là một phần riêng biệt, do đó có hai driver khác nhau tồn tại và sẽ gây ra xung đột.
			Vậy chúng ta cần làm gì? Chúng ta cần biến web driver thành static (tĩnh), khi đó cùng một driver sẽ được tham chiếu trong mọi object, bạn hiểu ý tôi chứ? Tại sao phải làm vậy? Vì base class dù sao cũng sẽ tạo ra một driver dùng chung khắp nơi. Nhưng thêm vào đó, trong extent report manager, do chúng ta tạo một object mới chứ không truy cập trực tiếp vào driver từ base class, nên object này sẽ sinh ra một driver khác. Nếu có hai instance driver, chắc chắn sẽ có xung đột và việc thực thi sẽ không diễn ra.
			Vậy làm thế nào để biến nó thành một biến dùng chung trên nhiều object? Cách duy nhất là khai báo biến đó là static. Trong các bài học Java chúng ta đã thảo luận về điều đó rồi, vì vậy tôi sẽ thêm từ khóa static vào đây, ok."
            */
            test.addScreenCaptureFromPath(imgPath);
            
        } catch (IOException e1) {
            e1.printStackTrace(); // chỉ hiển thị thông báo ngoại lệ
        }
        // Để vào khối try-catch là vì nếu ảnh chụp ko được chụp đúng cách or ko có sẵn thì sẽ có ngoại lện ko tìm thấy tệp 
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        
        extent.flush(); // phương thức này hợp nhất tất cả thông tin từ báo cáo và cuối cùng sẽ tạo ra 1 báo cáo hoàn chỉnh

        /*
        Vì vậy, thông thường báo cáo sẽ được tạo ra ở đâu? Nằm trong thư mục reports, và nếu bạn muốn mở 
        báo cáo, chúng ta sẽ mở như thế nào? Chúng ta cần đi đến thư mục báo cáo, sau đó chúng ta cần nhấp
         chuột phải vào tệp báo cáo, nhấp chuột phải chọn mở trong trình duyệt. Chúng ta phải làm điều đó 
         một cách thủ công đúng không? Vì vậy, để tránh việc này, giả sử ngay sau khi tôi thực thi xong các
          test case, ngay sau khi báo cáo của tôi được tạo ra, nó sẽ ngay lập tức tự động mở lên. Tôi 
          không muốn mở báo cáo một cách thủ công, tôi muốn mở báo cáo hoàn toàn tự động
        */
        
        /*
        Vì vậy, tôi sẽ lưu nó vào một biến, về cơ bản đây là đường dẫn của báo cáo. Sau đó, chúng ta phải tạo một đối tượng File: extentReport = new File(). Bởi vì báo cáo Extent dạng .HTML đó cũng là một tệp (file), nên chúng ta sẽ tạo một đối tượng file và truyền vị trí này (đường dẫn) vào trong nó. Vậy biến extentReport này đại diện cho điều gì? Nó đại diện cho chính file báo cáo Extent.
		Tiếp theo, chúng ta phải gọi một phương thức. Desktop là một lớp (class) có sẵn, trong lớp này chúng ta có một phương thức gọi là getDesktop().browse(), và chúng ta sẽ truyền file báo cáo extentReport đó vào dưới dạng URI (extentReport.toURI()). Phương thức này sẽ làm nhiệm vụ gì? Nó sẽ tự động mở báo cáo này trên trình duyệt. Chúng ta không cần phải tự làm, không cần mở báo cáo một cách thủ công, nó sẽ tự động mở trên trình duyệt.
		Vậy tại sao tôi lại đặt nó trong khối try-catch? Ví dụ: nếu file báo cáo này không tồn tại/không có sẵn, nó sẽ ném ra một ngoại lệ (exception). Đó là lý do tôi đặt nó trong khối try-catch
        */
        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Đoạn code tự động gửi email sau khi tạo báo cáo, sau khi các bài test automation hoàn tất
        /*
	         try {
	         	// Chuyển đổi đường dẫn đến file báo cáo thành định dạng url
	            // Tạo URL trỏ tới file report trong thư mục reports của project
	            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
	
	            // Tạo đối tượng email dạng HTML
	            ImageHtmlEmail email = new ImageHtmlEmail();
	
	            // Thiết lập nguồn dữ liệu cho email (lấy nội dung từ file report)
	            email.setDataSourceResolver(new DataSourceUrlResolver(url));
	
	            // Thiết lập server SMTP của Gmail
	            email.setHostName("smtp.googlemail.com");
	
	            // Thiết lập cổng SMTP của Gmail
	            email.setSmtpPort(465);
	
	            // Thiết lập tài khoản và mật khẩu để gửi email
	            email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
	
	            // Bật kết nối SSL để gửi email an toàn
	            email.setSSLOnConnect(true);
	
	            // Thiết lập địa chỉ người gửi
	            email.setFrom("pavanoltraining@gmail.com"); // Sender
	
	            // Thiết lập tiêu đề email
	            email.setSubject("Test Results");
	
	            // Nội dung email
	            email.setMsg("Please find Attached Report....");
	
	            // Địa chỉ người nhận email
	            email.addTo("pavankumar.busya@gmail.com"); // Receiver
	
	            // Đính kèm file report vào email
	            email.attach(url, "extent report", "please check report...");
	
	            // Gửi email
	            email.send();
	
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
         */
    }
}
