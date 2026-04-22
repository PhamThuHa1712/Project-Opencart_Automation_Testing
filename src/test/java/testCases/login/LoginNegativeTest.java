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
import utilities.DataProviders;
import utilities.TestDataFactory;

public class LoginNegativeTest extends BaseClass {
	private LoginPage lp;
	private ResourceBundle rb = TestDataFactory.rb;
	
	@BeforeMethod
	public void startLogin() {
		HomePage hp = new HomePage(driver);
		lp = hp.clickMyAccount().clickLogin();
	}
	
	
	@Test(dataProvider="loginValidationData", dataProviderClass=DataProviders.class)
	public void TC_LF_InvalidLoginDataDriven(String caseName, String email, String password, String expectedMsg) {
		logger.info("***** Bắt đầu TC_LF_InvalidLoginDataDriven *****");
		
		SoftAssert softAssert = new SoftAssert();
		lp.typeEmail(email).typePassword(password).clickLoginFail();
		softAssert.assertEquals(lp.isDisplayWarning(), expectedMsg, "Lỗi: " + caseName + "Thông báo sai email hoặc mật khẩu không khớp");
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_LF_InvalidLoginDataDriven *****");
	}
	
	@Test(priority=2)
	public void TC_LF_012_UnsuccessfulLoginAttempts() {
		logger.info("***** Bắt đầu TC_LF_012_UnsuccessfulLoginAttempts *****");
		
		String currentWarning = lp.typeEmail("hathuha@gmail.com").typePassword("wrong_password").clickLogin6Times().checkWarnExceeded();
		Assert.assertEquals(currentWarning, "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.", "Cảnh báo đăng nhập sai vượt quá số lần cho phép không khớp");
		
		logger.info("***** Kết thúc TC_LF_012_UnsuccessfulLoginAttempts *****");
	}
}
