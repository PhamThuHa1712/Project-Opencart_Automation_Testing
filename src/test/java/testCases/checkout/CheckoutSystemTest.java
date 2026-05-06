package testCases.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class CheckoutSystemTest extends BaseClass {
	
	@Test
	public void TC_CO_020_Checkout_CrossBrowserEnvironment() {
		logger.info("***** Bắt đầu TC_CO_020_Checkout_CrossBrowserEnvironment *****");
		
		HomePage hp = new HomePage(driver);
		CheckoutPage cp = hp.searchProduct("iMac").clickAddToCart().clickShoppingCart().clickCheckout();
		
		Assert.assertTrue(cp.isDisplayCheckoutPage(), "Trang thanh toán không hoạt động trên trình duyệt hiện tại");
		
		logger.info("***** Kết thúc TC_CO_020_Checkout_CrossBrowserEnvironment *****");
	}
}
