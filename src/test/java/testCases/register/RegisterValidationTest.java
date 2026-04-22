package testCases.register;

import static org.testng.Assert.assertFalse;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import models.RegisterData;
import pageObjects.AccountRegistrationPage;
import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.JavaScriptHelper;
import utilities.TestDataFactory;

public class RegisterValidationTest extends BaseClass {
	private AccountRegistrationPage regpage;
	
	@BeforeMethod
	public void startHomePage() {
		HomePage hp = new HomePage(driver);
		regpage = hp.clickMyAccount().clickRegister();
	}
	
	@Test
	public void TC_RF_004_verifyWarningForEmptyMandatoryFields() {
		logger.info("***** Bắt đầu TC_RF_004_verifyWarningForEmptyMandatoryFields *****");
		
		SoftAssert softAssert = new SoftAssert();
		
		regpage.clickContinueFail();
		
		softAssert.assertEquals(regpage.displayMsgFirstName(), "First Name must be between 1 and 32 characters!", "Thông báo lỗi First Name không hiển thị");
		softAssert.assertEquals(regpage.displayMsgLastName(), "Last Name must be between 1 and 32 characters!", "Thông báo lỗi Last Name không hiển thị");
		softAssert.assertEquals(regpage.displayMsgEmail(), "E-Mail Address does not appear to be valid!", "Thông báo lỗi Email không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPhone(), "Telephone must be between 3 and 32 characters!", "Thông báo lỗi Telephone không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPassword(), "Password must be between 4 and 20 characters!", "Thông báo lỗi Password không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPolicy(), "Warning: You must agree to the Privacy Policy!", "Thông báo lỗi Privacy Policy không hiển thị");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_004_verifyWarningForEmptyMandatoryFields *****");
	}
	
	@Test(dataProvider="emailValidationData", dataProviderClass = DataProviders.class, description = "Kiểm tra validate trường Email")
	public void TC_RF_010_EmailValidation(String caseName, String email, String expectedMessage) {
		logger.info("***** Bắt đầu TC_RF_010_EmailValidation *****");
		
		SoftAssert softAssert = new SoftAssert();
		RegisterData data = TestDataFactory.validData();
		data.setEmail(email);
		
		regpage.fillRegisterForm(data);
		JavaScriptHelper.disableHTML5Validation(driver);
		regpage.clickContinueFail();
		
		softAssert.assertEquals(regpage.displayMsgEmail(), expectedMessage, "[" + caseName + "] thông báo email sai định dạng không khớp!");
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_010_EmailValidation *****");
	}
	
	@Test(description = "Đăng ký với Email đã tồn tại trong hệ thống")
	public void TC_RF_009_RegisterWithExistingEmail() {
		logger.info("***** Bắt đầu TC_RF_009_RegisterWithExistingEmail *****");
		
		RegisterData data = TestDataFactory.validData();
		data.setEmail(TestDataFactory.getExistingEmail());
		
		regpage.fillRegisterForm(data).clickContinueFail();
		
		Assert.assertEquals(regpage.displayEmailExists(), "Warning: E-Mail Address is already registered!", "Thông báo email đã tồn tại không khớp!");
		
		logger.info("***** Kết thúc TC_RF_009_RegisterWithExistingEmail *****");
	}
	
	@Test(description = "Đăng ký với mật khẩu và mật khẩu xác nhận không khớp")
	public void TC_RF_008_verifyWarningForPasswordMismatch() {
		logger.info("***** Bắt đầu TC_RF_008_verifyWarningForPasswordMismatch *****");
		
		RegisterData data = TestDataFactory.validData();
		data.setPassword("12345");
		data.setConfirmPassword("abcde");
		
		regpage.fillRegisterForm(data).clickContinueFail();
		
		Assert.assertEquals(regpage.displayMsgConfPassword(), "Password confirmation does not match password!", "Thông báo mật khẩu xác nhận không khớp không đúng!");
		
		logger.info("***** Kết thúc TC_RF_008_verifyWarningForPasswordMismatch *****");
	}
	
	@Test(dataProvider="phoneValidationData", dataProviderClass = DataProviders.class)
	public void TC_RF_011_verifyWarningForInvalidPhoneNumber(String caseName, String phone, String expectedMessage) {
		logger.info("***** Bắt đầu TC_RF_011_verifyWarningForInvalidPhoneNumber *****");
		
		SoftAssert softAssert = new SoftAssert();
		
		RegisterData data = TestDataFactory.validData();
		data.setPhone(phone);
		
		regpage.fillRegisterForm(data).clickContinueFail();
		
		boolean isDisplayed = regpage.isPhoneErrorDisplayed();

	    // Nếu phoneNumber là loại đúng độ dài nhưng sai định dạng 
	    // Mà isDisplayed lại là false -> Website đang thiếu tính năng validate định dạng
	    softAssert.assertTrue(isDisplayed, 
	        "[BUG][Phone][" + caseName + "] Website không validate định dạng số điện thoại (Dữ liệu nhập: " + phone + ")");
	    
	    if(isDisplayed) {
	    		softAssert.assertEquals(regpage.displayMsgPhone(), expectedMessage, "Thông báo sai định dạng số điện thoại không khớp!");
	    }
	    	softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_011_verifyWarningForInvalidPhoneNumber *****");
	}
	
	@Test(groups = {"Master"}, description = "Kiểm tra validate trường Privacy Policy")
    public void TC_RF_021_verifyWarningWhenPrivacyPolicyNotAccepted() {
        logger.info("***** Bắt đầu TC_RF_021_verifyWarningWhenPrivacyPolicyNotAccepted *****");

        RegisterData data = TestDataFactory.validData();
        data.setPrivacyPolicy(false);

        regpage.fillRegisterForm(data).clickContinueFail();

        Assert.assertEquals(regpage.displayMsgPolicy(), "Warning: You must agree to the Privacy Policy!", "Thông báo lỗi Privacy Policy không hiển thị");

        logger.info("***** Kết thúc TC_RF_021_verifyWarningWhenPrivacyPolicyNotAccepted *****");
    }

    @Test(groups = {"Master"})
    public void TC_RF_024_verifyWarningForEmptyPasswordConfirm() {
        logger.info("***** Bắt đầu TC_RF_024_verifyWarningForEmptyPasswordConfirm *****");

        RegisterData data = TestDataFactory.validData();
        data.setConfirmPassword("");

        regpage.fillRegisterForm(data).clickContinueFail();
        
        Assert.assertEquals(regpage.displayMsgConfPassword(), "Password confirmation does not match password!", "Thông báo lỗi Password Confirm không hiển thị");

        logger.info("***** Kết thúc TC_RF_024_verifyWarningForEmptyPasswordConfirm *****");
    }
}