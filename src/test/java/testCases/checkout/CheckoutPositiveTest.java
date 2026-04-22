package testCases.checkout;

import java.util.List;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import models.CheckoutData;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.OrderSuccessPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;
import utilities.TestDataFactory;

public class CheckoutPositiveTest extends BaseClass {
	private CheckoutPage cp;
	private ShoppingCartPage sp; 
	private ResourceBundle rb = TestDataFactory.rb;
	private boolean verifyShoppingCart;
	private List<List<Object>> liShoppingCart;
	
	public void searchAndAddToCart(HomePage hp, String name, boolean getInfoShoppingCart) {
		sp = hp.searchProduct(name).clickAddToCart().clickShoppingCart();
		if(getInfoShoppingCart) {
			verifyShoppingCart = sp.checkTotalPrice();
			
			liShoppingCart = sp.getProducts();
		}
		cp = sp.clickCheckout();
	}
	
	public void searchAndAddToCart(String name, boolean getInfoShoppingCart) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount()
			.clickLogin()
			.typeEmail(rb.getString("email"))
			.typePassword(rb.getString("password"))
			.clickLoginSuccess()
			.toHomepage();
		searchAndAddToCart(hp, name, getInfoShoppingCart);
	}
	
	
	@Test
	public void TC_CO_002_Checkout_NavigateFromShoppingCartPage() {
		logger.info("***** Bắt đầu TC_CO_002_Checkout_NavigateFromShoppingCartPage *****");
		
		HomePage hp = new HomePage(driver);
		searchAndAddToCart(hp, "iMac", false);
		
		Assert.assertTrue(cp.isDisplayCheckoutPage(), "Trang checkout không hiển thị");
		
		Assert.assertTrue(cp.isCorrectUrl(), "Lỗi do url trang checkout không chứa '/checkout'");
		
		logger.info("***** Kết thúc TC_CO_002_Checkout_NavigateFromShoppingCartPage *****");
	}
	
	@Test
	public void TC_CO_003_Checkout_NavigateFromHeaderOption() {
		logger.info("***** Bắt đầu TC_CO_003_Checkout_NavigateFromHeaderOption *****");
		
		String nameProduct = "iMac";
		HomePage hp = new HomePage(driver);
		CheckoutPage cp = hp.searchProduct(nameProduct).clickAddToCart().clickCheckout();
		
		Assert.assertTrue(cp.isDisplayCheckoutPage(), "Trang checkout không hiển thị");
		
		Assert.assertTrue(cp.isCorrectUrl(), "Lỗi do url trang checkout không chứa '/checkout'");
		logger.info("***** Kết thúc TC_CO_003_Checkout_NavigateFromHeaderOption *****");
	}
	
	@Test
	public void TC_CO_004_Checkout_NavigateFromCartBlock() {
		logger.info("***** Bắt đầu TC_CO_004_Checkout_NavigateFromCartBlock *****");
		
		String nameProduct = "iMac";
		HomePage hp = new HomePage(driver);
		CheckoutPage cp = hp.searchProduct(nameProduct).clickAddToCart().clickBtnCart().clickLnkCheckout();
				
		Assert.assertTrue(cp.isDisplayCheckoutPage(), "Trang checkout không hiển thị");
		
		Assert.assertTrue(cp.isCorrectUrl(), "Lỗi do url trang checkout không chứa '/checkout'");
		
		logger.info("***** Kết thúc TC_CO_004_Checkout_NavigateFromCartBlock *****");
	}
	
	@Test
	public void TC_CO_005_Checkout_RegUser_ExistingAddress() {
		logger.info("***** Bắt đầu TC_CO_005_Checkout_RegUser_ExistingAddress *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		searchAndAddToCart("iMac", true);
		
		softAssert.assertTrue(cp.isRadioBilling(), "I want to use an existing address trong mục Billing Details không được chọn mặc định");
		softAssert.assertTrue(cp.getAddressBilling().contains(address), "Địa chỉ giao hàng trong mục Billing Details không đúng");
		
		cp.clickBtnBillingCtn();
		
		softAssert.assertTrue(cp.isRadioDelivery(), "I want to use an existing address trong mục Delivery Details không được chọn mặc định");
		softAssert.assertTrue(cp.getAddressDelivery().contains(address), "Địa chỉ giao hàng trong mục Delivery Details không đúng");
		
		cp.clickBtnDeliveryCtn();
		
		softAssert.assertTrue(cp.selectedShippingRate(), "Flat Shipping Rate không được chọn mặc định");
		
		cp.clickBtnDeliveryMethodCtn();
		
		softAssert.assertTrue(cp.selectedCashOnDelivery(), "Cash On Delivery không được chọn mặc định");
		
		cp.clickTermsAndConditions().clickCtnPaymentMethod();
		
		
		softAssert.assertTrue(verifyShoppingCart, "Tổng tiền của sản phẩm trong giỏ hàng không chính xác");
		
		List<List<Object>> liCheckout = cp.getProducts();
		
		softAssert.assertTrue(cp.compareConfirmOrder(liShoppingCart, liCheckout), "Các sản phẩm trong trang Shopping Cart và trang Checkout không giống nhau");
		softAssert.assertTrue(cp.verifyTotalPrice(liCheckout), "Tổng tiền thanh toán không chính xác");
		
		OrderSuccessPage op = cp.clickConfirmOrderAndAlert().closeAlert().clickConfirmOrder();
		softAssert.assertTrue(op.isTitleOrderSuccess(), "Trang đặt hàng thành công không hiển thị");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_005_Checkout_RegUser_ExistingAddress *****");
	}
	
	@Test
	public void TC_CO_006_Checkout_RegUser_NewBillingMandatory() throws InterruptedException {
		logger.info("***** Bắt đầu TC_CO_006_Checkout_RegUser_NewBillingMandatory *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		searchAndAddToCart("iMac", true);
		softAssert.assertTrue(cp.isRadioBilling(), "I want to use an existing address trong mục Billing Details không được chọn mặc định");
		softAssert.assertTrue(cp.getAddressBilling().contains(address), "Địa chỉ giao hàng trong mục Billing Details không đúng");

		CheckoutData data = TestDataFactory.checkoutData();
		data.setAddress2("");
		data.setCompany("");
		data.setPostCode("");
		
		cp.fillBillingDetailForm(data).clickBtnBillingCtn();
		
		softAssert.assertTrue(cp.isRadioDelivery(), "I want to use an existing address trong mục Delivery Details không được chọn mặc định");
		softAssert.assertTrue(cp.getAddressDelivery().contains(address), "Địa chỉ giao hàng trong mục Delivery Details không đúng");
		
		cp.clickBtnDeliveryCtn();
		softAssert.assertTrue(cp.selectedShippingRate(), "Flat Shipping Rate không được chọn mặc định");

		cp.clickBtnDeliveryMethodCtn();
		softAssert.assertTrue(cp.selectedCashOnDelivery(), "Cash On Delivery không được chọn mặc định");

		cp.clickTermsAndConditions().clickCtnPaymentMethod();
		
		
		softAssert.assertTrue(verifyShoppingCart, "Tổng tiền của sản phẩm trong giỏ hàng không chính xác");
		
		List<List<Object>> liCheckout = cp.getProducts();
		
		softAssert.assertTrue(cp.compareConfirmOrder(liShoppingCart, liCheckout), "Các sản phẩm trong trang Shopping Cart và trang Checkout không giống nhau");
		softAssert.assertTrue(cp.verifyTotalPrice(liCheckout), "Tổng tiền thanh toán không chính xác");
		
		OrderSuccessPage op = cp.clickConfirmOrderAndAlert().closeAlert().clickConfirmOrder();
		softAssert.assertTrue(op.isTitleOrderSuccess(), "Trang đặt hàng thành công không hiển thị");
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_006_Checkout_RegUser_NewBillingMandatory *****");
	}
}
