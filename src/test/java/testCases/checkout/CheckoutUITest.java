package testCases.checkout;

import java.util.ResourceBundle;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class CheckoutUITest extends BaseClass {
	private CheckoutPage cp;
	private ShoppingCartPage sp; 
	private ResourceBundle rb = TestDataFactory.rb;
	
	public void searchAndAddToCart(String name) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount()
			.clickLogin()
			.typeEmail(rb.getString("email"))
			.typePassword(rb.getString("password"))
			.clickLoginSuccess()
			.toHomepage();
		cp = hp.searchProduct(name).clickAddToCart().clickShoppingCart().clickCheckout();
	}
	
	@Test
	public void TC_CO_008_Checkout_BillingPlaceholderCheck() {
		logger.info("***** Bắt đầu TC_CO_008_Checkout_BillingPlaceholderCheck *****");
		
		SoftAssert softAssert = new SoftAssert();
		searchAndAddToCart("iMac");
		cp.selectNewAddressBilling().clickBtnBillingCtn();
		
		softAssert.assertTrue(cp.getPlaceHoderFirstNameBilling().contains("First Name"), "Placehoder trường First Name không hiển thị");
		softAssert.assertTrue(cp.getPlaceHoderLastNameBilling().contains("Last Name"), "Placehoder trường Last Name không hiển thị");
		softAssert.assertTrue(cp.getPlaceHoderCompanyBilling().contains("Company"), "Placehoder trường Company không hiển thị");
		softAssert.assertTrue(cp.getPlaceHoderAddress1Billing().contains("Address 1"), "Placehoder trường Address 1 không hiển thị");
		softAssert.assertTrue(cp.getPlaceHoderAddress2Billing().contains("Address 2"), "Placehoder trường Address 2 không hiển thị");
		softAssert.assertTrue(cp.getPlaceHoderCityBilling().contains("City"), "Placehoder trường City không hiển thị");
		softAssert.assertTrue(cp.getPlaceHoderPostcodeBilling().contains("Post Code"), "Placehoder trường Postcode không hiển thị");
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_008_Checkout_BillingPlaceholderCheck *****");
	}
}
