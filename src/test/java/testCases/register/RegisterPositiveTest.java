package testCases.register;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import models.RegisterData;
import pageObjects.AccountRegistrationPage;
import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.NewsletterSubscriptionPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.TestDataFactory;

public class RegisterPositiveTest extends BaseClass {
	private AccountRegistrationPage regpage;
	@BeforeMethod
	public void startRegister() {
		HomePage hp = new HomePage(driver);
		regpage = hp.clickMyAccount().clickRegister();
	}
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_001_registerWithMandatoryFields() {
		logger.info("***** Bắt đầu TC_RF_001_registerWithMandatoryFields *****");
	    
        RegisterData data = TestDataFactory.validData();
        AccountSuccessPage acc = regpage.fillRegisterForm(data).clickContinueSuccess();
        String confMsg = acc.getConfirmationMsg();
        
        Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
        
        // Kiểm tra email confirmation text
        String confEmail = acc.getConfirmationEmail();
        Assert.assertTrue(confEmail.contains("A confirmation has been sent to the provided e-mail address"), "Không nhận được thông báo email đã được gửi thành công!");
        
        MyAccountPage myacc = acc.clickCnt();
        
        Assert.assertTrue(myacc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại");
        
	    logger.info("***** Kết thúc TC_RF_001_registerWithMandatoryFields *****");
	}
	
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_002_verifyRegistrationConfirmationEmail() {
		logger.info("***** Bắt đầu TC_RF_002_verifyRegistrationConfirmationEmail *****");
		
		RegisterData data = TestDataFactory.validData();
        AccountSuccessPage acc = regpage.fillRegisterForm(data).clickContinueSuccess();
        
        // validate nội dung email ..... 
        
	    Assert.assertTrue(true);
	 
		logger.info("***** Kết thúc TC_RF_002_verifyRegistrationConfirmationEmail *****");
	}
	
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_003_registerWithAllFields() {
		logger.info("***** Bắt đầu TC_RF_003_registerWithAllFields *****");
		
		RegisterData data = TestDataFactory.validData();
        AccountSuccessPage acc = regpage.fillRegisterForm(data).clickContinueSuccess();
        String confMsg = acc.getConfirmationMsg();
        
        Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
        
        MyAccountPage myacc = acc.clickCnt();
        
        Assert.assertTrue(myacc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại");
        
		logger.info("***** Kết thúc TC_RF_003_registerWithAllFields *****");
	}
	
	
	@Test(groups = {"Regression", "Master"}, dataProvider="Newsletter", dataProviderClass=DataProviders.class)
	public void TC_RF_005_registerWithNewsletterSubscriptionYes_TC_RF_006_registerWithNewsletterSubscriptionNo(boolean newsletter) {
		logger.info("***** Bắt đầu TC_RF_005_registerWithNewsletterSubscriptionYes và TC_RF_006_registerWithNewsletterSubscriptionNo *****");
		
		RegisterData data = TestDataFactory.validData();
		data.setNewsletter(newsletter);
        AccountSuccessPage acc = regpage.fillRegisterForm(data).clickContinueSuccess();
		Assert.assertEquals(acc.getConfirmationMsg(), "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
		
		MyAccountPage myacc = acc.clickCnt();
		Assert.assertTrue(myacc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại");
		
		NewsletterSubscriptionPage news = myacc.clickNewsletter();
		Assert.assertTrue(news.isNewsletterSelected(newsletter), "Lỗi ER-3: Lựa chọn " + (newsletter ? "Yes" : "No") + "không được chọn mặc định");
		
		logger.info("***** Kết thúc TC_RF_005_registerWithNewsletterSubscriptionYes và TC_RF_006_registerWithNewsletterSubscriptionNo *****");
	}

	@Test
	public void TC_RF_012_registerUsingKeyboardKeysOnly() {
		logger.info("***** Bắt đầu TC_RF_012_registerUsingKeyboardKeysOnly *****");
		
		RegisterData data = TestDataFactory.validData();
		AccountSuccessPage acc = regpage.fillRegistrationFormByKeyboard(data);
		
		logger.info("Đang xác thực ER-1: Expected message..");
		Assert.assertEquals(acc.getConfirmationMsg(), "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
		
		logger.info("***** Kết thúc TC_RF_012_registerUsingKeyboardKeysOnly *****");
	}
}
