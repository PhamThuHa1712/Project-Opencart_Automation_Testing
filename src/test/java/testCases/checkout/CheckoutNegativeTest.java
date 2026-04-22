package testCases.checkout;

import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class CheckoutNegativeTest extends BaseClass {
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
	public void TC_CO_001_Checkout_EmptyCartRestriction() {
		logger.info("***** Bắt đầu TC_CO_001_Checkout_EmptyCartRestriction *****");

		HomePage hp = new HomePage(driver);
		ShoppingCartPage sp = hp.clickCheckoutWithoutProduct();
		Assert.assertTrue(sp.isShoppingCartEmpty().contains("Your shopping cart is empty!"), "Thông báo giỏ hàng trống không hiển thị");
		
		logger.info("***** Kết thúc TC_CO_001_Checkout_EmptyCartRestriction *****");
	}
	
	@Test
	public void TC_CO_009_Checkout_BillingMandatoryValidation() {
		logger.info("***** Bắt đầu TC_CO_009_Checkout_BillingMandatoryValidation *****");

		SoftAssert softAssert = new SoftAssert();
		searchAndAddToCart("iMac");
		cp.selectNewAddressBilling().clickBtnBillingCtn();
		
		softAssert.assertTrue(cp.getValidationFirstNameBilling().contains("First Name must be between 1 and 32 characters!"), "Thông báo lỗi trường First Name không hiển thị");
		softAssert.assertTrue(cp.getValidationLastNameBilling().contains("Last Name must be between 1 and 32 characters!"), "Thông báo lỗi trường Last Name không hiển thị");
		softAssert.assertTrue(cp.getValidationAddressBilling().contains("Address 1 must be between 3 and 128 characters!"), "Thông báo lỗi trường Address 1 không hiển thị");
		softAssert.assertTrue(cp.getValidationCityBilling().contains("City must be between 2 and 128 characters!"), "Thông báo lỗi trường City không hiển thị");
		softAssert.assertTrue(cp.getValidationPostcodeBilling().contains("Postcode must be between 2 and 10 characters!"), "Thông báo lỗi trường Postcode không hiển thị");
		softAssert.assertTrue(cp.getValidationRegionStateBilling().contains("Please select a region / state!"), "Thông báo lỗi trường region / state không hiển thị");
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_009_Checkout_BillingMandatoryValidation *****");
	}
}
