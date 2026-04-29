package testCases.addToCart;

import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import pageObjects.WishListPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class AddToCartPositiveTest extends BaseClass {
	private ResourceBundle rb = TestDataFactory.rb;
	
	
	@Test
	public void TC_ATC_001_AddToCart_ProductDisplayPage() {
		logger.info("***** Kết thúc TC_ATC_001_AddToCart_ProductDisplayPage *****");
		HomePage hp = new HomePage(driver);
		ProductPage	pp = hp.searchProduct("iMac").clickByName("iMac").clickAdd();
		
		Assert.assertTrue((pp.getSuccessAddMsg()).contains("Success: You have added "), "Không hiển thị thông báo thêm vào giỏ hàng thành công");
		boolean disProduct = pp.clickShoppingCart().presenceOfElement("iMac");
		Assert.assertTrue(disProduct, "Phần tử không hiển thị trong giỏ hàng!");
		
		logger.info("***** Kết thúc TC_ATC_001_AddToCart_ProductDisplayPage *****");
	}
	
	
	@Test
	public void TC_ATC_002_AddToCart_WishListPage() {
		logger.info("***** Kết thúc TC_ATC_002_AddToCart_WishListPage *****");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount().clickLogin().typeEmail(rb.getString("email")).typePassword(rb.getString("password")).clickLoginSuccess();
		WishListPage wp = hp.searchProduct("iMac").clickWistList().clickOptionWistList().clickAddToCart("iMac");
		String msg = wp.msgSuccessAddToCart();
		Assert.assertTrue(msg.contains("Success: You have added "), "Không hiển thị thông báo thêm vào giỏ hàng thành công");
		boolean res = wp.clickShoppingCart().presenceOfElement("iMac");
		Assert.assertTrue(res, "Sản phẩm iMac không hiển thị trong giỏ hàng");
		
		logger.info("***** Kết thúc TC_ATC_002_AddToCart_WishListPage *****");
	}
	
	
	@Test
	public void TC_ATC_003_AddToCart_SearchResultsPage() {
		logger.info("***** Kết thúc TC_ATC_003_AddToCart_SearchResultsPage *****");
		
		HomePage hp = new HomePage(driver);
		SearchPage sp = hp.searchProduct("iMac").clickAddToCart();
		String msg = sp.getMsgSuccessAdd();
		Assert.assertTrue(msg.contains("Success: You have added "), "Không hiển thị thông báo thêm vào giỏ hàng thành công");
		boolean disProduct = sp.clickBtnCart().clickShoppingCart().presenceOfElement("iMac");
		Assert.assertTrue(disProduct, "Sản phẩm không hiển thị trong giỏ hàng");
		
		logger.info("***** Kết thúc TC_ATC_003_AddToCart_SearchResultsPage *****");
	}
	
	
	@Test 
	public void TC_ATC_004_AddToCart_RelatedProducts() {
		logger.info("***** Kết thúc TC_ATC_004_AddToCart_RelatedProducts *****");
		
		String productName = "Apple Cinema 30";
		HomePage hp = new HomePage(driver);
		ProductPage pp = hp.searchProduct(productName).clickByName(productName).addRelateProductToCart();
		String msgSuccess = pp.msgSuccessAddRelateProduct();
		Assert.assertTrue(msgSuccess.contains("Success: You have added "), "Không hiển thị thông báo thêm vào giỏ hàng thành công");
		String relProduct = pp.getNameRelateProduct();
		boolean display = pp.clickShoppingCart().presenceOfElement(relProduct);
		Assert.assertTrue(display, "Sản phẩm không hiển thị trong giỏ hàng");
		
		logger.info("***** Kết thúc TC_ATC_004_AddToCart_RelatedProducts *****");

	}
	
	
	@Test
	public void TC_ATC_005_AddToCart_CategoryPage() {
		logger.info("***** Kết thúc TC_ATC_005_AddToCart_CategoryPage *****");
		
		String nameCategory = "Desktops";
		HomePage hp = new HomePage(driver);
		hp.hover(nameCategory, "Tên danh mục " + nameCategory);
		CategoryPage cp = hp.breadcrumb().clickBreadcrumb("Show All " + nameCategory).clickEleList("Mac").clickAddToCart();
		String msgShoppingCart = cp.getMsgShoppingCart();
		Assert.assertTrue(msgShoppingCart.contains("Success: You have added "), "Không hiển thị thông báo thêm vào giỏ hàng thành công");
		String nameProduct = cp.getNameProduct();
		boolean dis = cp.clickLnkShoppingCart().presenceOfElement(nameProduct);
		Assert.assertTrue(dis, "Sản phẩm không hiển thị trong giỏ hàng");
		
		logger.info("***** Kết thúc TC_ATC_005_AddToCart_CategoryPage *****");
	}
	
	
	@Test
	public void TC_ATC_006_AddToCart_FeaturedSection() {
		logger.info("***** Bắt đầu TC_ATC_006_AddToCart_FeaturedSection *****");
		
		HomePage hp = new HomePage(driver);
		String productName = hp.getProductName();
		hp.clickAddToCart();
		Assert.assertEquals(hp.getMsgAddToCartSuccess(), "Success: You have added " + productName + " to your shopping cart!", "Thông báo thêm sản phẩm vào giỏ hàng thành công không hiển thị");
		boolean dis = hp.clickLnkShoppingCart().presenceOfElement(productName);
		Assert.assertTrue(dis, "Sản phẩm không hiển thị trong giỏ hàng");
		
		logger.info("***** Kết thúc TC_ATC_006_AddToCart_FeaturedSection *****");
	}
	
	
	@Test
	public void TC_ATC_007_AddToCart_ComparisonPage() {
		logger.info("***** Bắt đầu TC_ATC_007_AddToCart_ComparisonPage *****");
		
		HomePage hp = new HomePage(driver);
		String productName = hp.getProductName();
		hp.clickCompareProduct();
		Assert.assertEquals(hp.getMsgAddProductComparisonSuccess(), "Success: You have added " + productName + " to your product comparison!", "Thông báo thêm sản phẩm so sánh thành công không chính xác");
		boolean dis = hp.clickLnkProductComparison().clickAddToCart().clickLnkShoppingCart().presenceOfElement(productName);
		Assert.assertTrue(dis, "Sản phẩm không hiển thị trong giỏ hàng"); 
		
		logger.info("***** Kết thúc TC_ATC_007_AddToCart_ComparisonPage *****");
	}
}
