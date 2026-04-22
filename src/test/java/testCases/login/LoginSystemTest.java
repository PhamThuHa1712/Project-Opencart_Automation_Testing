package testCases.login;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class LoginSystemTest extends BaseClass {
	private final ResourceBundle rb = TestDataFactory.rb;
	private LoginPage lp;
	
	@BeforeMethod
	public void startLogin() {
		HomePage hp = new HomePage(driver);
		lp = hp.clickMyAccount().clickLogin();
	}
	
	
	@Test
	public void TC_LF_017_LoginClosingBrowserWithoutLoggingOut() throws IOException {
	    logger.info("***** Bắt đầu TC_LF_017_LoginClosingBrowserWithoutLoggingOut *****");
	    
	    MyAccountPage macc = lp.typeEmail(rb.getString("email"))
	                           .typePassword(rb.getString("password"))
	                           .clickLoginSuccess();
	    
	    Assert.assertTrue(macc.isMyAccountPageExists(), "Đăng nhập ban đầu không thành công!");
	    
	    // Lưu lại toàn bộ Cookies và URL hiện tại
	    Set<Cookie> savedCookies = driver.manage().getCookies();
	    String targetUrl = driver.getCurrentUrl(); 
	    logger.info("Đã lưu " + savedCookies.size() + " cookies và URL: " + targetUrl);

	    driver.quit(); 
	    
	    // setup() sẽ tạo ra driver mới nhưng chưa có session
	    setup("windows", "chrome"); 
	    
	    // Nạp Cookie (Phải truy cập Domain trước)
	    driver.get(rb.getString("appURL")); 
	    
	    for (Cookie ck : savedCookies) {
	        try {
	            driver.manage().addCookie(ck);
	        } catch (Exception e) {
	            logger.warn("Không thể add cookie: " + ck.getName());
	        }
	    }
	    
	    // 6. Điều hướng thẳng về trang mục tiêu (thay vì chỉ refresh trang chủ)
	    driver.get(targetUrl);
	    
	    macc = new MyAccountPage(driver);
	    boolean isLoggedIn = macc.isMyAccountPageExists();
	    
	    if (!isLoggedIn) {
	        logger.error("Duy trì đăng nhập thất bại.");
	    }
	    
	    Assert.assertTrue(isLoggedIn, "Lỗi: Hệ thống không duy trì đăng nhập sau khi đóng trình duyệt!");
	    
	    logger.info("***** Kết thúc TC_LF_017_LoginClosingBrowserWithoutLoggingOut *****");
	}
}
