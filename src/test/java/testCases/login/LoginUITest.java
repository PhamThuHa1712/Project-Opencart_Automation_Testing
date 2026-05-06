package testCases.login;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class LoginUITest extends BaseClass {
	private LoginPage lp;
	private HomePage hp;
	private ResourceBundle rb = TestDataFactory.rb;
	
	
	public void openLoginPage() {
		hp = new HomePage(driver);
		lp = hp.clickMyAccount().clickLogin();
	}
	
	@Test
	public void TC_LF_009_ForgottenPasswordLink() {
		logger.info("***** Bắt đầu TC_LF_009_ForgottenPasswordLink *****");

		openLoginPage();
		Assert.assertTrue(lp.displayLoginPage(), "Không hiển thị trang Login");
		Assert.assertTrue(lp.isDisplayForgotPwdLink(), "Không hiển thị link Forgotten Password");
		
		ForgotPasswordPage fp = lp.clickForgotPassword();
		Assert.assertEquals(fp.getHeadingForgotPwdPage(), "Forgot Your Password?", "Trang Forgotten Password không hiển thị");
		
		logger.info("***** Kết thúc TC_LF_009_ForgottenPasswordLink *****");
	}
	
	@Test
	public void TC_LF_010_PlaceholderText() {
		logger.info("***** Bắt đầu TC_LF_010_PlaceholderText *****");
		
		openLoginPage();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(lp.getPlaceholderEmail(), "E-Mail Address", "Placeholder trường E-Mail Address không chính xác");
		softAssert.assertEquals(lp.getPlaceholderPassword(), "Password", "Placeholder trường Password không chính xác");
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_LF_010_PlaceholderText *****");
	}
	
	@Test
	public void TC_LF_011_BrowsingBackAfterLogin() {
		logger.info("***** Bắt đầu TC_LF_011_BrowsingBackAfterLogin *****");

		openLoginPage();
		MyAccountPage myacc = lp.typeEmail(rb.getString("email")).typePassword(rb.getString("password")).clickLoginSuccess().clickBrowserBack();
		Assert.assertTrue(myacc.isMyAccountPageExists(), "Bị đăng xuất sau khi nhấn nút Back của trình duyệt");
		
		logger.info("***** Kết thúc TC_LF_011_BrowsingBackAfterLogin *****");
	}
	
	@Test
	public void TC_LF_012_BrowsingBackAfterLogout() {
		logger.info("***** Bắt đầu TC_LF_012_BrowsingBackAfterLogout *****");
		
		openLoginPage();
		lp.typeEmail(rb.getString("email")).typePassword(rb.getString("password")).clickLoginSuccess().clickMyAcc().clickOptionLogout().clickBrowserBack();
		Assert.assertTrue(lp.displayLoginPage(), "Sau khi đăng xuất người dùng được đăng nhập lại sau khi nhấn nút Back trên trình duyệt");
		
		logger.info("***** Kết thúc TC_LF_012_BrowsingBackAfterLogout *****");
	}
	
	// Test việc điều hướng đến các trang tương ứng khi nhấn các link trên trang đăng nhậpp
	@Test
	public void TC_LF_013_MapsToDifferentPages() {
		logger.info("***** TC_LF_013_MapsToDifferentPages *****");
		
		openLoginPage();
		
		logger.info("***** TC_LF_013_MapsToDifferentPages *****");
	}
	
	@Test
	public void TC_LF_014_DifferentWaysOfNavigatingToLogin() {
		logger.info("***** TC_LF_014_DifferentWaysOfNavigatingToLogin *****");
		
		SoftAssert softAssert = new SoftAssert();
		hp = new HomePage(driver);
		lp = hp.clickMyAccount().clickRegister().clickLnkLoginPage();
		softAssert.assertTrue(lp.displayLoginPage(), "Không điều hướng đến trang đăng nhập thông qua đường dẫn 'login page' trên trang đăng ký được");
		
		lp = lp.clickLoginFromRightColumn();
		softAssert.assertTrue(lp.displayLoginPage(), "Không điều hướng đến trang đăng nhập thông qua đường dẫn 'login' từ menu bên phải");
		
		lp = lp.clickDropdownMyAccount().clickOptionLogin();
		softAssert.assertTrue(lp.displayLoginPage(), "Không điều hướng đến trang đăng nhập thông qua lựa chọn 'login' trong 'My Account'");
		
		logger.info("***** TC_LF_014_DifferentWaysOfNavigatingToLogin *****");
	}
	
	@Test
	public void TC_LF_015_BreadcrumbPageTitleURL() {
		logger.info("***** TC_LF_015_BreadcrumbPageTitleURL *****");
		
		openLoginPage();
		List<String> breadcrumb = Arrays.asList("Account", "Login");
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(lp.getBreadcrumb(), breadcrumb, "Breadcrumb không chính xác");
		softAssert.assertTrue(lp.correctUrlLogin(), "Url trang Login không chính xác");
		softAssert.assertTrue(lp.isCorrectTitle(), "Tiêu đề trang Login không chính xác");
		softAssert.assertAll();
		
		logger.info("***** TC_LF_015_BreadcrumbPageTitleURL *****");
	}
}
