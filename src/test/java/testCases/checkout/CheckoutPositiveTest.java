package testCases.checkout;

import java.util.List;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import models.GuestCheckoutData;
import models.RegisterCheckoutData;
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
	
	private void searchAndAddToCart(HomePage hp, String name, boolean getInfoShoppingCart) {
		sp = hp.searchProduct(name).clickAddToCart().clickShoppingCart();
		if(getInfoShoppingCart) {
			verifyShoppingCart = sp.checkTotalPrice();
			
			liShoppingCart = sp.getProducts();
		}
		cp = sp.clickCheckout();
	}
	
	private void searchAndAddToCart(String name, boolean getInfoShoppingCart) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount()
			.clickLogin()
			.typeEmail(rb.getString("email"))
			.typePassword(rb.getString("password"))
			.clickLoginSuccess()
			.toHomepage();
		searchAndAddToCart(hp, name, getInfoShoppingCart);
	}
	
	private void goToCheckoutAndVerifyBilling(SoftAssert softAssert, String address) {
	    searchAndAddToCart("iMac", true);

	    softAssert.assertTrue(cp.isRadioBilling(), "Billing radio không được chọn mặc định");
	    softAssert.assertTrue(cp.getAddressBilling().contains(address), "Billing address sai");
	}
	
	private void verifyDeliveryDefault(SoftAssert softAssert, String address) {
	    softAssert.assertTrue(cp.isRadioDelivery(), "Delivery radio không được chọn mặc định");
	    softAssert.assertTrue(cp.getAddressDelivery().contains(address), "Delivery address sai");
	}

	private void completeCheckoutFlow(SoftAssert softAssert) {
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
	}
	
	private void completeCheckoutWithComment(SoftAssert softAssert, boolean deliveryCmt, boolean paymentCmt) {
		searchAndAddToCart("iMac", true);

		cp.clickBtnBillingCtn().clickBtnDeliveryCtn();
		String text = "Comment In This Field";
		if(deliveryCmt && !paymentCmt) {
			cp.typeCmtDeliveryMethod(text).clickBtnDeliveryMethodCtn();
			softAssert.assertEquals(cp.getCmtPaymentMethod(), text, "Bình luận trong Delivery Method không hiển thị trong Payment Method");
		} else if(paymentCmt && !deliveryCmt) {
			cp.clickBtnDeliveryMethodCtn().typeCmtPaymentMethod(text);
		}
		cp.clickTermsAndConditions().clickCtnPaymentMethod();
		OrderSuccessPage op = cp.clickConfirmOrderAndAlert().closeAlert().clickConfirmOrder();
	    softAssert.assertTrue(op.isTitleOrderSuccess(), "Trang đặt hàng thành công không hiển thị");
		
	}
	
	private void proceedToConfirmOrderForNewCustomer(SoftAssert softAssert, boolean guest, boolean register) {
		cp.clickCntNewCustomer();
		softAssert.assertTrue(cp.isBillingDetails(), "Không nhận được section 'Billing Details'");
		softAssert.assertTrue(cp.selectedSameDeliveryAndBillingAddress(), "My delivery and billing addresses are the same không được chọn mặc định");
		
		if(guest) {
			GuestCheckoutData data = TestDataFactory.guestCheckoutData();
			cp.fillBillingDetailGuestCheckout(data).clickGuestBillingCtn();
		} else {
			RegisterCheckoutData data = TestDataFactory.registerCheckoutData();
			cp.fillBillingDetailRegisterCheckout(data).checkPolicy().clickRegisterBillingCtn();
		}
		
		softAssert.assertTrue(cp.isDeliveryMethod(), "Không nhận được section 'Delivery Method'");
		softAssert.assertTrue(cp.selectedShippingRate(), "Flat Shipping Rate không được chọn mặc định");
		
		cp.clickBtnDeliveryMethodCtn();
		softAssert.assertTrue(cp.isPaymentMethod(), "Không nhận được section 'Payment Method'");
		softAssert.assertTrue(cp.selectedCashOnDelivery(), "Cash On Delivery không được chọn mặc định");
		
		cp.clickTermsAndConditions().clickCtnPaymentMethod();
		softAssert.assertTrue(cp.isConfirmOrder(), "Không nhận được section 'Confirm Order'");

		softAssert.assertTrue(verifyShoppingCart, "Tổng tiền của sản phẩm trong giỏ hàng không chính xác");

	    List<List<Object>> liCheckout = cp.getProducts();

	    System.out.println(liShoppingCart + "\n" + liCheckout);
	    Assert.assertTrue(cp.compareConfirmOrder(liShoppingCart, liCheckout), "Các sản phẩm trong trang Shopping Cart: " + liShoppingCart + " và trang Checkout: " + liCheckout + " không giống nhau.");
	    softAssert.assertTrue(cp.verifyTotalPrice(liCheckout), "Tổng tiền thanh toán không chính xác");
	    
	    OrderSuccessPage op = cp.clickConfirmOrderAndAlert().closeAlert().clickConfirmOrder();
	    softAssert.assertTrue(op.isTitleOrderSuccess(), "Trang đặt hàng thành công không hiển thị");
	}
	/*
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
	
	// Thanh toán với tài khoản đã login
	@Test
	public void TC_CO_005_Checkout_RegUser_ExistingAddress() {
		logger.info("***** Bắt đầu TC_CO_005_Checkout_RegUser_ExistingAddress *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		goToCheckoutAndVerifyBilling(softAssert, address);

	    cp.clickBtnBillingCtn();

	    verifyDeliveryDefault(softAssert, address);

	    completeCheckoutFlow(softAssert);
        
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_005_Checkout_RegUser_ExistingAddress *****");
	}
	
	@Test
	public void TC_CO_006_Checkout_RegUser_NewBillingMandatory() throws InterruptedException {
		logger.info("***** Bắt đầu TC_CO_006_Checkout_RegUser_NewBillingMandatory *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		goToCheckoutAndVerifyBilling(softAssert, address);

	    cp.selectNewAddressBilling();

	    CheckoutData data = TestDataFactory.checkoutData();
	    data.setAddress2("");
	    data.setCompany("");
	    data.setPostCode("");

	    List<String> missing = cp.missingBillingFields();
	    softAssert.assertTrue(missing.isEmpty(), "Các trường không hiển thị là: " + missing);

	    cp.fillBillingDetailForm(data);

	    cp.clickBtnBillingCtn(); 

	    verifyDeliveryDefault(softAssert, address);

	    completeCheckoutFlow(softAssert);
        
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_006_Checkout_RegUser_NewBillingMandatory *****");
	}
	
	@Test
	public void TC_CO_007_Checkout_RegUser_NewBillingAllFields() throws InterruptedException {
		logger.info("***** Bắt đầu TC_CO_007_Checkout_RegUser_NewBillingAllFields *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		goToCheckoutAndVerifyBilling(softAssert, address);

	    cp.selectNewAddressBilling();

	    CheckoutData data = TestDataFactory.checkoutData();

	    List<String> missing = cp.missingBillingFields();
	    softAssert.assertTrue(missing.isEmpty(), "Các trường không hiển thị là: " + missing);

	    cp.fillBillingDetailForm(data);

	    cp.clickBtnBillingCtn();

	    verifyDeliveryDefault(softAssert, address);

	    completeCheckoutFlow(softAssert);
        
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_007_Checkout_RegUser_NewBillingAllFields *****");
	}
	
	@Test
	public void TC_CO_010_Checkout_RegUser_NewDeliveryMandatory() {
		logger.info("***** Bắt đầu TC_CO_010_Checkout_RegUser_NewDeliveryMandatory *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		goToCheckoutAndVerifyBilling(softAssert, address);

	    cp.clickBtnBillingCtn();

	    verifyDeliveryDefault(softAssert, address);

	    cp.selectNewAddressDelivery();

	    CheckoutData data = TestDataFactory.checkoutData();
	    data.setAddress2("");
	    data.setCompany("");
	    data.setPostCode("");

	    List<String> missing = cp.missingDeliveryFields();
	    softAssert.assertTrue(missing.isEmpty(), "Các trường không hiển thị là: " + missing);

	    cp.fillDeliveryDetailForm(data);

	    completeCheckoutFlow(softAssert);
        
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_010_Checkout_RegUser_NewDeliveryMandatory *****");
	}
	
	@Test
	public void TC_CO_011_Checkout_RegUser_NewDeliveryAllFields() {
		logger.info("***** Bắt đầu TC_CO_011_Checkout_RegUser_NewDeliveryAllFields *****");
		
		SoftAssert softAssert = new SoftAssert();
		String address = "Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam, Hà Nội, Ha Noi, Viet Nam";
		
		goToCheckoutAndVerifyBilling(softAssert, address);

	    cp.clickBtnBillingCtn();

	    verifyDeliveryDefault(softAssert, address);

	    cp.selectNewAddressDelivery();

	    CheckoutData data = TestDataFactory.checkoutData();

	    List<String> missing = cp.missingDeliveryFields();
	    softAssert.assertTrue(missing.isEmpty(), "Các trường không hiển thị là: " + missing);

	    cp.fillDeliveryDetailForm(data);

	    completeCheckoutFlow(softAssert);
        
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_011_Checkout_RegUser_NewDeliveryAllFields *****");
	}
	
	@Test
	public void TC_CO_014_Checkout_AddCommentDeliveryMethod() {
		logger.info("***** Bắt đầu TC_CO_014_Checkout_AddCommentDeliveryMethod *****");
		
		SoftAssert softAssert = new SoftAssert();
		completeCheckoutWithComment(softAssert, true, false);
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_014_Checkout_AddCommentDeliveryMethod *****");
	}
	
	@Test
	public void TC_CO_015_Checkout_AddCommentPaymentMethod() {
		logger.info("***** Bắt đầu TC_CO_015_Checkout_AddCommentPaymentMethod *****");
		
		SoftAssert softAssert = new SoftAssert();
		completeCheckoutWithComment(softAssert, false, true);
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_015_Checkout_AddCommentPaymentMethod *****");
	}
	
	
	@Test
	public void TC_CO_016_Checkout_GuestFlow() {
		logger.info("***** Bắt đầu TC_CO_016_Checkout_GuestFlow *****");
		
		HomePage hp = new HomePage(driver);
		searchAndAddToCart(hp, "iMac", true);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(cp.isDisplayCheckoutPage(), "Trang thanh toán không hiển thị");
		
		cp.selectedGuestCheckout();
		
		proceedToConfirmOrderForNewCustomer(softAssert, true, false);
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_016_Checkout_GuestFlow *****");
	}
	*/
	
	@Test
	public void TC_CO_017_Checkout_NewUserRegistration() {
		logger.info("***** Bắt đầu TC_CO_017_Checkout_NewUserRegistration *****");
		
		HomePage hp = new HomePage(driver);
		searchAndAddToCart(hp, "iMac", true);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(cp.isDisplayCheckoutPage(), "Trang thanh toán không hiển thị");
		
		cp.selectedRegisterCheckout();
		
		proceedToConfirmOrderForNewCustomer(softAssert, false, true);
		
		softAssert.assertAll();
		
		logger.info("***** Kết thúc TC_CO_017_Checkout_NewUserRegistration *****");
	}
}
