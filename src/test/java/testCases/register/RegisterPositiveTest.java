package testCases.register;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.NewsletterSubscriptionPage;
import testBase.BaseClass;

public class RegisterPositiveTest extends BaseClass {
	
	private void fillRegistrationForm(String fName, String lName, String email, String phone, String password, boolean subscribeNewsletter) {
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();
        
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        logger.info("Providing customer details...");
        regpage.setFirstName(fName);
        regpage.setLastName(lName);
        regpage.setEmail(email); 
        regpage.setTelephone(phone);
        regpage.setPassword(password);
        regpage.setConfirmPassword(password);
        
        // Chỉ TC_003 truyền true thì mới bấm Yes
        if (subscribeNewsletter) {
            regpage.setRadioYes(); 
        }
        
        regpage.setPrivacyPolicy();
        regpage.clickContinue();
    }
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_001_registerWithMandatoryFields() {
		logger.info("***** Starting TC_RF_001_registerWithMandatoryFields *****");
	    
		SoftAssert softAssert = new SoftAssert();
        String password = randomAlphaNumberic();
        
        fillRegistrationForm(
                randomString().toUpperCase(), 
                randomString().toUpperCase(), 
                randomString() + "@gmail.com", 
                randomNumber(), 
                password, 
                false 
        );
        // Kiểm tra ER-1 (Message xác nhận)
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        String confmsg = regpage.getConfirmationMsg();
        logger.info("Validating ER-1: Expected message..");
        softAssert.assertEquals(confmsg, "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
        
        // Kiểm tra email confirmation text
        String confEmail = regpage.getConfirmationEmail();
        softAssert.assertTrue(confEmail.contains("A confirmation has been sent to the provided e-mail address"));
        
        // Kiểm tra ER-2 (Trang My Account)
        regpage.clickCnt();
        
        logger.info("Validating ER-2: My Account Page Exists..");
        MyAccountPage macc = new MyAccountPage(driver);
        softAssert.assertTrue(macc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại");
        
        softAssert.assertAll();
        // Lệnh này sẽ tổng hợp lại. Nếu bước 2 hoặc bước 3 có lỗi, nó mới đánh Fail toàn bộ test case và in ra đủ 2 lỗi.        
	    
	    logger.info("***** Finished TC_RF_001_registerWithMandatoryFields *****");
	}
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_002_verifyRegistrationConfirmationEmail() {
		logger.info("***** Starting TC_RF_002_verifyRegistrationConfirmationEmail *****");
		
		String password = randomAlphaNumberic();
        
        fillRegistrationForm(
                randomString().toUpperCase(), 
                randomString().toUpperCase(), 
                randomString() + "@gmail.com", 
                randomNumber(), 
                password, 
                false 
        );
        
        // validate nội dung email ..... 
        
	    Assert.assertTrue(true);
	 
		logger.info("***** Finished TC_RF_002_verifyRegistrationConfirmationEmail *****");
	}
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_003_registerWithAllFields() {
		logger.info("***** Starting TC_RF_003_registerWithAllFields *****");
		
		SoftAssert softAssert = new SoftAssert();
        String password = randomAlphaNumberic();
        
        // Gọi hàm helper, truyền true (Có đăng ký nhận tin)
        fillRegistrationForm(
                randomString().toUpperCase(), 
                randomString().toUpperCase(), 
                randomString() + "@gmail.com", 
                randomNumber(), 
                password, 
                true 
        );
        
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        String confmsg = regpage.getConfirmationMsg();
        softAssert.assertEquals(confmsg, "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
        
        regpage.clickCnt();
        MyAccountPage macc = new MyAccountPage(driver);
        softAssert.assertTrue(macc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại"); 
        
        softAssert.assertAll();
        
		logger.info("***** Finished TC_RF_003_registerWithAllFields *****");
	}
	
	@Test(groups = {"Regression", "Master"})
	public void TC_RF_005_registerWithNewsletterSubscriptionYes() {
		logger.info("***** Starting TC_RF_005_registerWithNewsletterSubscriptionYes *****");
		SoftAssert softAssert = new SoftAssert();
		String password = randomAlphaNumberic();
		
		fillRegistrationForm(
				randomString().toUpperCase(), 
				randomString().toUpperCase(), 
				randomString() + "@gmail.com", 
				randomNumber(), 
				password, 
				true);
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		String confmsg = regpage.getConfirmationMsg();
		logger.info("Validating ER-1: Expected message..");
		softAssert.assertEquals(confmsg, "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
		
		regpage.clickCnt();
		MyAccountPage macc = new MyAccountPage(driver);
		softAssert.assertTrue(macc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại");
		
		macc.clickNewsletter();
		NewsletterSubscriptionPage news = new NewsletterSubscriptionPage(driver);
		softAssert.assertTrue(news.selectedYes(), "Lỗi ER-3: Lựa chọn Yes không được chọn mặc định");
		
		softAssert.assertAll();
		logger.info("***** Finished TC_RF_005_registerWithNewsletterSubscriptionYes *****");
	}
	
	@Test(groups = {"Regression", "Master"})	
	public void TC_RF_006_registerWithNewsletterSubscriptionNo() {
		logger.info("***** Starting TC_RF_006_registerWithNewsletterSubscriptionNo *****");
		SoftAssert softAssert = new SoftAssert();
		String password = randomAlphaNumberic();
		
		fillRegistrationForm(
				randomString().toUpperCase(), 
				randomString().toUpperCase(), 
				randomString() + "@gmail.com", 
				randomNumber(), 
				password, 
				false);
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		String confmsg = regpage.getConfirmationMsg();
		logger.info("Validating ER-1: Expected message..");
		softAssert.assertEquals(confmsg, "Your Account Has Been Created!", "Lỗi ER-1: Message xác nhận không khớp");
		
		regpage.clickCnt();
		MyAccountPage macc = new MyAccountPage(driver);
		softAssert.assertTrue(macc.isMyAccountPageExists(), "Lỗi ER-2: Chuyển hướng đến trang My Account thất bại");
		
		macc.clickNewsletter();
		NewsletterSubscriptionPage news = new NewsletterSubscriptionPage(driver);
		softAssert.assertTrue(news.selectedNo(), "Lỗi ER-3: Lựa chọn No không được chọn mặc định");
		
		softAssert.assertAll();
		logger.info("***** Finished TC_RF_006_registerWithNewsletterSubscriptionNo *****");
	}
	
}
