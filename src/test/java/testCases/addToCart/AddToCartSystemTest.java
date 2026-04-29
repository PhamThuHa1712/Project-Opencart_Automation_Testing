package testCases.addToCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class AddToCartSystemTest extends BaseClass {
	
	@Test
	public void TC_ATC_009_AddToCart_CrossBrowserEnvironment() {
		logger.info("***** Bắt đầu TC_ATC_009_AddToCart_CrossBrowserEnvironment *****");
		
		HomePage hp = new HomePage(driver);
		String product = "iMac";
		SearchPage sp =hp.searchProduct(product).clickAddToCart();
		String productName = hp.getProductName();
		Assert.assertTrue(sp.getMsgSuccessAdd().contains("Success: You have added"), "Thông báo thêm sản phẩm vào giỏ hàng thành công không chính xác");

		boolean display = sp.clickShoppingCart().presenceOfElement(productName);
		Assert.assertTrue(display, "Sản phẩm không hiển thị trong giỏ hàng sau khi thêm");
		
		logger.info("***** Kết thúc TC_ATC_009_AddToCart_CrossBrowserEnvironment *****");
	}
}
