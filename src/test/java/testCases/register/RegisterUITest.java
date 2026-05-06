package testCases.register;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import models.RegisterData;
import pageObjects.AccountRegistrationPage;
import pageObjects.AccountRegistrationPage.RegisterField;
import pageObjects.HomePage;
import pageObjects.MyAccountInformationPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class RegisterUITest extends BaseClass {
	private AccountRegistrationPage regpage;
	
	@BeforeMethod 
	public void navigateRegisterPage() {
		HomePage hp = new HomePage(driver);
		regpage = hp.clickMyAccount().clickRegister();
	}
	
	
	@Test
	public void TC_RF_017_verifyNavigationToRegisterPageFromDifferentLinks() {
		logger.info("***** Bắt đầu TC_RF_017_verifyNavigationToRegisterPageFromDifferentLinks *****");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(regpage.isPageRegisterHeading(), "Không đến được trang đăng ký từ trang chủ");
		
		regpage.clickMyAccount().clickLogin().clickContinueNewCustomer();
		softAssert.assertTrue(regpage.isPageRegisterHeading(), "Không đến được trang đăng ký từ trang chủ");
		
		regpage.clickMyAccount().clickLogin().clickRegisterInTheRightColumn();
		softAssert.assertTrue(regpage.isPageRegisterHeading(), "Không đến được trang đăng ký từ trang chủ");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_017_verifyNavigationToRegisterPageFromDifferentLinks *****");
	}
	
	@Test
	public void TC_RF_018_verifyFieldPlaceholders() {
		logger.info("***** Bắt đầu TC_RF_018_verifyFieldPlaceholders *****");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(regpage.getFirstNamePlaceholder(), "First Name", "Sai placeholder First Name");
	    softAssert.assertEquals(regpage.getLastNamePlaceholder(), "Last Name", "Sai placeholder Last Name");
	    softAssert.assertEquals(regpage.getEmailPlaceholder(), "E-Mail", "Sai placeholder E-Mail");
	    softAssert.assertEquals(regpage.getTelephonePlaceholder(), "Telephone", "Sai placeholder Telephone");
	    softAssert.assertEquals(regpage.getPasswordPlaceholder(), "Password", "Sai placeholder Password");
	    softAssert.assertEquals(regpage.getConfirmPasswordPlaceholder(), "Password Confirm", "Sai placeholder Password Confirm");

		softAssert.assertAll();
		logger.info("***** Kết thúc TC_RF_018_verifyFieldPlaceholders *****");
	}
	
	@Test
	public void TC_RF_019_verifyMandatoryFieldsHaveRedAsterisk() {
		logger.info("***** Bắt đầu TC_RF_019_verifyMandatoryFieldsHaveRedAsterisk *****");

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(regpage.isRequiredField(RegisterField.FIRSTNAME), "Trường FirstName không có dấu * đỏ");
		softAssert.assertTrue(regpage.isRequiredField(RegisterField.LASTNAME), "Trường LastName không có dấu * đỏ");
		softAssert.assertTrue(regpage.isRequiredField(RegisterField.EMAIL), "Trường Email không có dấu * đỏ");
		softAssert.assertTrue(regpage.isRequiredField(RegisterField.TELEPHONE), "Trường Telephone không có dấu * đỏ");
		softAssert.assertTrue(regpage.isRequiredField(RegisterField.PASSWORD), "Trường Password không có dấu * đỏ");
		softAssert.assertTrue(regpage.isRequiredField(RegisterField.PASSWORD_CONFIRM), "Trường Password Confirm không có dấu * đỏ");
		softAssert.assertTrue(regpage.getPrivacyPolicyText().contains("*"), "Privacy Policy không có dấu *");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_019_verifyMandatoryFieldsHaveRedAsterisk *****");
	}
	
	@Test
	public void TC_RF_021_verifyLeadingAndTrailingSpacesAreTrimmed() {
		logger.info("***** Bắt đầu TC_RF_021_verifyLeadingAndTrailingSpacesAreTrimmed *****");
		
		RegisterData data = TestDataFactory.leadingAndTrailingSpaceData();
		MyAccountInformationPage accInfo = regpage.fillRegisterForm(data).clickContinueSuccess().clickCnt().clickEditAccount();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(accInfo.getFirstNameValue(), data.getExpectedFirstNameTrimmed(), "Dữ liệu trường FirstName không được trim");
		softAssert.assertEquals(accInfo.getLastNameValue(), data.getExpectedLastNameTrimmed(), "Dữ liệu trường LastName không được trim");
		softAssert.assertEquals(accInfo.getEmailValue(), data.getExpectedEmailTrimmed(), "Dữ liệu trường Email không được trim");
		softAssert.assertEquals(accInfo.getTelephoneValue(), data.getExpectedPhoneTrimmed(), "Dữ liệu trường Telephone không được trim");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_021_verifyLeadingAndTrailingSpacesAreTrimmed *****");
	}
	
	
	@Test
	public void TC_RF_022_verifyPrivacyPolicyNotSelectedByDefault() {
		logger.info("***** Bắt đầu TC_RF_022_verifyPrivacyPolicyNotSelectedByDefault *****");
		
		Assert.assertFalse(regpage.isSelectedPolicy(), "Hộp kiểm Privacy Policy được tích sẵn");
	
		logger.info("***** Kết thúc TC_RF_022_verifyPrivacyPolicyNotSelectedByDefault *****");
	}
	
	
	@Test
	public void TC_RF_023_verifyPasswordCharactersAreMasked() {
		logger.info("***** Bắt đầu TC_RF_023_verifyPasswordCharactersAreMasked *****");
		
		regpage.setPassword("123456").setConfirmPassword("123456");
		Assert.assertTrue(regpage.hidePasswordAndConfPassword());
		
		logger.info("***** Kết thúc TC_RF_023_verifyPasswordCharactersAreMasked *****");
	}
	
	// Test việc điều hướng đến các trang tương ứng khi nhấn các link trên trang đăng kýy
	@Test
	public void TC_RF_024_verifyNavigationLinksOnRegisterPage() {
		logger.info("***** Bắt đầu TC_RF_024_verifyNavigationLinksOnRegisterPage *****");

		logger.info("***** Kết thúc TC_RF_024_verifyNavigationLinksOnRegisterPage *****");
	}
	
	@Test
	public void TC_RF_025_verifyPageTitleUrlHeadingAndBreadcrumb() {
		logger.info("***** Bắt đầu TC_RF_025_verifyPageTitleUrlHeadingAndBreadcrumb *****");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(regpage.getBreadcrumbCorrect(), Arrays.asList("Account", "Register"), "Breadcrumb hiển thị không đúng");
		softAssert.assertTrue(regpage.isPageRegisterHeading(), "Heading trang không đúng");
		softAssert.assertTrue(regpage.getUrl().contains("register"), "Url không đúng");
		softAssert.assertTrue(regpage.getTitle().contains("Register Account"), "Tiêu đề trang không đúng");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_025_verifyPageTitleUrlHeadingAndBreadcrumb *****");
	}
	
	
	@Test
	public void TC_RF_027_verifyRegisterFunctionalityAcrossSupportedEnvironments() {
		logger.info("***** Bắt đầu TC_RF_027_verifyRegisterFunctionalityAcrossSupportedEnvironments *****");
		
		Assert.assertTrue(regpage.isPageRegisterHeading(), "Trang Register không hoạt động trên trình duyệt hiện tại");
		
		logger.info("***** Kết thúc TC_RF_027_verifyRegisterFunctionalityAcrossSupportedEnvironments *****");
	}
}
