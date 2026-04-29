package testCases.login;

import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class LoginSecurityTest extends BaseClass {
	private LoginPage lp;
	private ResourceBundle rb = TestDataFactory.rb;
	
	@BeforeMethod
	public void startLogin() {
		HomePage hp = new HomePage(driver);
		lp = hp.clickMyAccount().clickLogin();
	}
	
	
	@Test
	public void TC_LF_013_PasswordVisibilityToggled() {
		logger.info("***** Bắt đầu TC_LF_013_PasswordVisibilityToggled *****");
		
		lp.typePassword(rb.getString("password"));
		Assert.assertEquals(lp.getAttributePwd("type"), "password", "Trường Password không được ẩn dưới dạng * or .");
		
		logger.info("***** Kết thúc TC_LF_013_PasswordVisibilityToggled *****");
	}
	
	@Test
	public void TC_LF_014_verifyPasswordCopyIsBlocked() {
		logger.info("***** Bắt đầu TC_LF_014_verifyPasswordCopyIsBlocked *****");
		
		String password = randomAlphaNumberic();
		
		String copiedPwd = lp.typePassword(password).copyPasswordByShortcut().getClipboardText("copy password");
		
		Assert.assertNotEquals(copiedPwd, password, "Hệ thống không ngăn chặn copy dữ liệu trường Password thông qua Ctrl + c");
		
		logger.info("***** Kết thúc TC_LF_014_verifyPasswordCopyIsBlocked *****");
	}
	
	@Test
	public void TC_LF_015_PasswordNotVisibleInPageSource() {
		logger.info("***** Bắt đầu TC_LF_015_PasswordNotVisibleInPageSource *****");
		SoftAssert softAssert = new SoftAssert();
		String pwd = "abcd";
		String typePwd = lp.typePassword(pwd).getAttributePwd("type");
		softAssert.assertEquals(typePwd, "password", "Trường Password không được ẩn dưới dạng * or .");
		
		boolean pwdSource = lp.clickLoginFail().verifyPasswordNotPresentInSource("type", pwd);
		softAssert.assertFalse(pwdSource, "Mật khẩu hiển thị trên mã nguồn của trang");
		
		softAssert.assertAll();
		logger.info("***** Kết thúc TC_LF_015_PasswordNotVisibleInPageSource *****");
	}
	
	@Test
	public void TC_LF_018_LoginSessionTimeout() {
		logger.info("***** Bắt đầu TC_LF_018_LoginSessionTimeout *****");
		
		MyAccountPage macc = lp.typeEmail(rb.getString("email")).typePassword(rb.getString("password")).clickLoginSuccess();
		// MyAccountPage macc = lp.typeEmail("nguyenlinh@gmail.com").typePassword("123456").clickLoginSuccess();
		Assert.assertTrue(macc.isMyAccountPageExists(), "Đăng nhập không thành công!");
		
		driver.manage().deleteAllCookies();
		
		driver.navigate().refresh();
		Assert.assertTrue(lp.displayLoginPage(), "Trang login vẫn hiển thị khi token hết hạn");
		
		logger.info("***** Kết thúc TC_LF_018_LoginSessionTimeout *****");
	}
}
