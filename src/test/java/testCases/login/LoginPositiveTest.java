package testCases.login;

import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class LoginPositiveTest extends BaseClass {
	private final ResourceBundle rb = TestDataFactory.rb;
	private LoginPage lp;
	private boolean isPasswordChanged = false;
	
	@BeforeMethod
	public void startLogin() {
		HomePage hp = new HomePage(driver);
		lp = hp.clickMyAccount().clickLogin();
	}
	
	@Test
	public void TC_LF_001_LoginWithValidCredentials() {
		logger.info("***** Bắt đầu TC_LF_001_LoginWithValidCredentials *****");
		
		Assert.assertTrue(lp.correctUrlLogin(), "URL trang login không khớp");
		Assert.assertTrue(lp.displayLoginPage(), "Trang login không hiển thị");
		
		MyAccountPage acc = lp.typeEmail(rb.getString("email")).typePassword(rb.getString("password")).clickLoginSuccess();
		
		Assert.assertTrue(acc.isCorrectUrl(), "URL trang My Account không khớp");
		Assert.assertTrue(acc.isMyAccountPageExists(), "Trang My Account không hiển thị");
		
		logger.info("***** Kết thúc TC_LF_001_LoginWithValidCredentials *****");
	}
	
	@Test
	public void TC_LF_007_LoginUsingKeyboardKeys() {
		logger.info("***** Bắt đầu TC_LF_007_LoginUsingKeyboardKeys *****");
		
		MyAccountPage acc = lp.setEmailByTabKey(rb.getString("email")).setPasswordByTabKey(rb.getString("password")).clickCtnByTabKeySuccess();
		
		Assert.assertTrue(acc.isCorrectUrl(), "URL trang My Account không khớp");
		Assert.assertTrue(acc.isMyAccountPageExists(), "Trang My Account không hiển thị");
		
		logger.info("***** Kết thúc TC_LF_007_LoginUsingKeyboardKeys *****");
	}
	
	@Test
	public void TC_LF_016_LoginAfterChangingPassword() {
		logger.info("***** Bắt đầu TC_LF_016_LoginAfterChangingPassword *****");
		
		String email = rb.getString("email");
		String oldPwd = rb.getString("password");
		String newPwd = "abcd1234";
		
		ChangePasswordPage cpwd = lp.typeEmail(email)
									.typePassword(oldPwd)
									.clickLoginSuccess()
									.clickChangePwd();
		
		lp = cpwd.typePassword(newPwd)
			.typeConfPassword(newPwd)
			.clickContinueSuccess()
			.clickLogout()
			.clickMyAccount()
			.clickLogin()
			.typeEmail(email)
			.typePassword(oldPwd)
			.clickLoginFail();
		
		isPasswordChanged = true;
		Assert.assertEquals(lp.isDisplayWarning(), "Warning: No match for E-Mail Address and/or Password.", "Thông báo sai email hoặc mật khẩu không khớp.");
		
		MyAccountPage macc = lp.typePassword(newPwd).clickLoginSuccess();
		Assert.assertTrue(macc.isMyAccountPageExists(), "Không hiển thị trang My Account khi đăng nhập với mật khẩu mới");
		
		if(isPasswordChanged) {
			macc.clickChangePwd()
	            .typePassword(oldPwd)
	            .typeConfPassword(oldPwd)
	            .clickContinueSuccess();
        
			isPasswordChanged = false;
			logger.info("Đã khôi phục mật khẩu cũ thành công!");
		}
		
		logger.info("***** Kết thúc TC_LF_016_LoginAfterChangingPassword *****");
	}
}
