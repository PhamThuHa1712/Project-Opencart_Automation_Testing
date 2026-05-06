package testCases.register;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import models.RegisterData;
import pageObjects.AccountRegistrationPage;
import pageObjects.AccountRegistrationPage.RegisterField;
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
	public void TC_RF_008_verifyWarningForEmptyMandatoryFields() {
		logger.info("***** Bắt đầu TC_RF_008_verifyWarningForEmptyMandatoryFields *****");
		
		SoftAssert softAssert = new SoftAssert();
		
		regpage.clickContinueFail();
		
		softAssert.assertEquals(regpage.displayMsgFirstName(), "First Name must be between 1 and 32 characters!", "Thông báo lỗi First Name không hiển thị");
		softAssert.assertEquals(regpage.displayMsgLastName(), "Last Name must be between 1 and 32 characters!", "Thông báo lỗi Last Name không hiển thị");
		softAssert.assertEquals(regpage.displayMsgEmail(), "E-Mail Address does not appear to be valid!", "Thông báo lỗi Email không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPhone(), "Telephone must be between 3 and 32 characters!", "Thông báo lỗi Telephone không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPassword(), "Password must be between 4 and 20 characters!", "Thông báo lỗi Password không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPolicy(), "Warning: You must agree to the Privacy Policy!", "Thông báo lỗi Privacy Policy không hiển thị");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_008_verifyWarningForEmptyMandatoryFields *****");
	}
	
	@Test(dataProvider="emailValidationData", dataProviderClass = DataProviders.class, description = "Kiểm tra validate trường Email")
	public void TC_RF_011_EmailValidation(String caseName, String email, String expectedMessage) {
		logger.info("***** Bắt đầu TC_RF_011_EmailValidation *****");
		
		RegisterData data = TestDataFactory.validData();
		data.setEmail(email);
		
		regpage.fillRegisterForm(data);
		JavaScriptHelper.disableHTML5Validation(driver);
		regpage.clickContinueFail();
		
		Assert.assertEquals(regpage.displayMsgEmail(), expectedMessage, "[" + caseName + "] thông báo email sai định dạng không khớp!");
		
		logger.info("***** Kết thúc TC_RF_011_EmailValidation *****");
	}
	
	@Test(description = "Đăng ký với Email đã tồn tại trong hệ thống")
	public void TC_RF_010_RegisterWithExistingEmail() {
		logger.info("***** Bắt đầu TC_RF_010_RegisterWithExistingEmail *****");
		
		RegisterData data = TestDataFactory.validData();
		data.setEmail(TestDataFactory.getExistingEmail());
		
		regpage.fillRegisterForm(data).clickContinueFail();
		
		Assert.assertEquals(regpage.displayEmailExists(), "Warning: E-Mail Address is already registered!", "Thông báo email đã tồn tại không khớp!");
		
		logger.info("***** Kết thúc TC_RF_010_RegisterWithExistingEmail *****");
	}
	
	@Test(description = "Đăng ký với mật khẩu và mật khẩu xác nhận không khớp")
	public void TC_RF_009_verifyWarningForPasswordMismatch() {
		logger.info("***** Bắt đầu TC_RF_009_verifyWarningForPasswordMismatch *****");
		
		RegisterData data = TestDataFactory.validData();
		data.setPassword("12345");
		data.setConfirmPassword("abcde");
		
		regpage.fillRegisterForm(data).clickContinueFail();
		
		Assert.assertEquals(regpage.displayMsgConfPassword(), "Password confirmation does not match password!", "Thông báo mật khẩu xác nhận không khớp không đúng!");
		
		logger.info("***** Kết thúc TC_RF_009_verifyWarningForPasswordMismatch *****");
	}
	
	@Test(dataProvider="phoneValidationData", dataProviderClass = DataProviders.class)
	public void TC_RF_012_verifyWarningForInvalidPhoneNumber(String caseName, String phone, String expectedMessage) {
		logger.info("***** Bắt đầu TC_RF_012_verifyWarningForInvalidPhoneNumber *****");
				
		RegisterData data = TestDataFactory.validData();
		data.setPhone(phone);
		
		regpage.fillRegisterForm(data).clickContinueFail();
		
		boolean isSuccess = regpage.waitForResponseFor(RegisterField.TELEPHONE);

	    if(isSuccess) {
	    		Assert.fail("Website không validate định dạng số điện thoại (Dữ liệu nhập: " + phone + ")");
	    } else {
	    		Assert.assertEquals(regpage.displayMsgPhone(), expectedMessage, "Thông báo lỗi trường Telephone không khớp");
	    }
	    
		logger.info("***** Kết thúc TC_RF_012_verifyWarningForInvalidPhoneNumber *****");
	}
	
	@Test
	public void TC_RF_013_verifyWarningWhenEnteringOnlySpaces() {
		logger.info("***** Bắt đầu TC_RF_013_verifyWarningWhenEnteringOnlySpaces *****");
		
		RegisterData data = TestDataFactory.multipleSpaceData(5);
		regpage.fillRegisterForm(data).clickContinueFail();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(regpage.displayMsgFirstName(), "First Name must be between 1 and 32 characters!", "Thông báo lỗi First Name không hiển thị");
		softAssert.assertEquals(regpage.displayMsgLastName(), "Last Name must be between 1 and 32 characters!", "Thông báo lỗi Last Name không hiển thị");
		softAssert.assertEquals(regpage.displayMsgEmail(), "E-Mail Address does not appear to be valid!", "Thông báo lỗi Email không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPhone(), "Telephone must be between 3 and 32 characters!", "Thông báo lỗi Telephone không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPassword(), "Password must be between 4 and 20 characters!", "Thông báo lỗi Password không hiển thị");
		softAssert.assertEquals(regpage.displayMsgPolicy(), "Warning: You must agree to the Privacy Policy!", "Thông báo lỗi Privacy Policy không hiển thị");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_RF_013_verifyWarningWhenEnteringOnlySpaces  *****");
	}
	
	@Test(dataProvider="passwordValidationData", dataProviderClass=DataProviders.class)
	public void TC_RF_014_verifyPasswordComplexityStandards(String caseName, String pwd, String expectedMessage) {
        logger.info("***** Bắt đầu TC_RF_014_verifyPasswordComplexityStandards *****");
                
        RegisterData data = TestDataFactory.validData();
        data.setPassword(pwd);
        data.setConfirmPassword(pwd);
        regpage.fillRegisterForm(data).clickContinueFail();
        
		boolean isSuccess = regpage.waitForResponseFor(RegisterField.PASSWORD);

		if(isSuccess) {
        		Assert.fail("Hệ thống cho phép đăng ký với " + caseName);
        } else {
        		Assert.assertEquals(regpage.displayMsgPassword(), expectedMessage, "Thông báo lỗi trường Password không khớp");
        }
        
        logger.info("***** Kết thúc TC_RF_014_verifyPasswordComplexityStandards *****");
	}
	
	@Test(groups = {"Master"}, description = "Kiểm tra validate trường Privacy Policy")
    public void TC_RF_015_verifyWarningWhenPrivacyPolicyNotAccepted() {
        logger.info("***** Bắt đầu TC_RF_015_verifyWarningWhenPrivacyPolicyNotAccepted *****");

        RegisterData data = TestDataFactory.validData();
        data.setPrivacyPolicy(false);

        regpage.fillRegisterForm(data).clickContinueFail();

        Assert.assertEquals(regpage.displayMsgPolicy(), "Warning: You must agree to the Privacy Policy!", "Thông báo lỗi Privacy Policy không hiển thị");

        logger.info("***** Kết thúc TC_RF_015_verifyWarningWhenPrivacyPolicyNotAccepted *****");
    }

    @Test(groups = {"Master"})
    public void TC_RF_016_verifyWarningForEmptyPasswordConfirm() {
        logger.info("***** Bắt đầu TC_RF_016_verifyWarningForEmptyPasswordConfirm *****");

        RegisterData data = TestDataFactory.validData();
        data.setConfirmPassword("");

        regpage.fillRegisterForm(data).clickContinueFail();
        
        Assert.assertEquals(regpage.displayMsgConfPassword(), "Password confirmation does not match password!", "Thông báo lỗi Password Confirm không hiển thị");

        logger.info("***** Kết thúc TC_RF_016_verifyWarningForEmptyPasswordConfirm *****");
    }
}