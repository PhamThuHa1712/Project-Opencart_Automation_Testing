package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import models.CheckoutData;
import models.GuestCheckoutData;
import models.RegisterCheckoutData;

public class CheckoutPage extends BasePage {
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	private final By titleCheckout = By.xpath("//h1[normalize-space()='Checkout']");
	
	private final By breadcrumb = By.cssSelector(".breadcrumb li");
	
	private final By radioBilling= By.xpath("//input[@value='existing' and @name='payment_address']");
	
	private final By addressBilling = By.xpath("//div[@id='payment-existing']//select[@name='address_id']"); 
	
	private final By radioDelivery = By.xpath("//input[@value='existing' and @name='shipping_address']");
	
	private final By addressDelivery = By.xpath("//div[@id='shipping-existing']//select[@name='address_id']");
	
	private final By btnBilling = By.xpath("//input[@id='button-payment-address']");
	
	private final By btnDelivery = By.xpath("//input[@id='button-shipping-address']");
	
	private final By shippingRate = By.xpath("//input[@name='shipping_method']");
	
	private final By btnDeliveryMethod = By.xpath("//input[@id='button-shipping-method']");
	
	private final By radioCashOnDelivery = By.xpath("//input[@name='payment_method']");
	
	private final By btnPaymentMethodCtn = By.xpath("//input[@id='button-payment-method']");
	
	private final By checkTermsAndConditions = By.xpath("//input[@name='agree']");
	
	private final By btnConfirmOrder = By.xpath("//input[@id='button-confirm']");
	
	private final By colTable = By.xpath("//div[@class='table-responsive']//table//thead//td");
	
	private final By rowTable = By.xpath("//div[@class='table-responsive']//table//tbody//tr");
		
	private final By subTotalXpath = By.xpath("//div[@class='table-responsive']//table//tfoot//tr[1]//td[2]");
	
	private final By flatShippingRate = By.xpath("//div[@class='table-responsive']//table//tfoot//tr[2]//td[2]");
	
	private final By total = By.xpath("//div[@class='table-responsive']//table//tfoot//tr[3]//td[2]");
	
	private final By totalVat = By.xpath("//div[@class='table-responsive']//table//tfoot//tr[5]//td[2]");
	
	private final By newAddressBilling = By.xpath("//input[@value='new']");
	
	private final By firstNameBilling = By.xpath("//input[@id='input-payment-firstname']");
	
	private final By lastNameBilling = By.xpath("//input[@id='input-payment-lastname']");
	
	private final By companyBilling = By.xpath("//input[@id='input-payment-company']");
	
	private final By address1Billing = By.xpath("//input[@id='input-payment-address-1']");
	
	private final By address2Billing = By.xpath("//input[@id='input-payment-address-2']");
	
	private final By cityBilling = By.xpath("//input[@id='input-payment-city']");
	
	private final By postCodeBilling = By.xpath("//input[@id='input-payment-postcode']");
	
	private final By countryBilling = By.xpath("//select[@id='input-payment-country']");
	
	private final By regionStateBilling = By.xpath("//select[@id='input-payment-zone']");
	
	private final By newAddressDelivery = By.cssSelector("input[value='new'][name='shipping_address']");
	
	private final By firstNameDelivery = By.cssSelector("#input-shipping-firstname");
	
	private final By lastNameDelivery = By.cssSelector("#input-shipping-lastname");
	
	private final By companyDelivery = By.cssSelector("#input-shipping-company");
			
	private final By address1Delivery = By.cssSelector("#input-shipping-address-1");
	
	private final By address2Delivery = By.cssSelector("#input-shipping-address-2");
	
	private final By cityDelivery = By.cssSelector("#input-shipping-city");
	
	private final By postCodeDelivery = By.cssSelector("#input-shipping-postcode");
	
	private final By countryDelivery = By.cssSelector("#input-shipping-country");
	
	private final By regionStateDelivery = By.cssSelector("#input-shipping-zone");
	
	private final By msgValidationFirstName = By.xpath("//input[@name='firstname']//following-sibling::div[@class='text-danger']");
	
	private final By msgValidationLastName = By.xpath("//input[@name='lastname']//following-sibling::div[@class='text-danger']");
	
	private final By msgValidationAddress = By.xpath("//input[@name='address_1']//following-sibling::div[@class='text-danger']");
	
	private final By msgValidationCity = By.xpath("//input[@name='city']//following-sibling::div[@class='text-danger']");
	
	private final By msgValidationPostcode = By.xpath("//input[@name='postcode']//following-sibling::div[@class='text-danger']");
	
	private final By msgValidationRegionState = By.xpath("//select[@id='input-payment-zone']//following-sibling::div[@class='text-danger']");
	
	private final By txtCommentInDeliveryMethod = By.cssSelector("div[id='collapse-shipping-method'] textarea[name='comment']");
	
	private final By txtCommentInPaymentMethod = By.cssSelector("div[id='collapse-payment-method'] textarea[name='comment']");
	
	private final By guestCheckout = By.cssSelector("input[value='guest']");
	
	private final By btnCtnNewCustomer = By.cssSelector("#button-account");
	
	private final By sectionBillingDetails = By.cssSelector("#collapse-payment-address"); 
	
	private final By sectionDeliveryDetails = By.cssSelector("#collapse-shipping-address");
	
	private final By sameDeliveryAndBillingAddress = By.cssSelector("input[value='1'][name='shipping_address']");
	
	private final By txtEmailBilling = By.cssSelector("#input-payment-email");
	
	private final By txtTelephoneBilling = By.cssSelector("#input-payment-telephone");
	
	private final By sectionDeliveryMethod = By.cssSelector("#collapse-shipping-method");
	
	private final By sectionPaymentMethod = By.cssSelector("#collapse-payment-method");
	
	private final By sectionConfirmOrder = By.cssSelector("#collapse-checkout-confirm");
	
	private final By registerAccount = By.cssSelector("input[value='register']");
	
	private final By txtEmailLogin = By.cssSelector("#input-email");
	
	private final By txtPasswordLogin = By.cssSelector("#input-password");
	
	private final By btnLogin = By.cssSelector("#button-login");
	
	private final By txtPasswordBilling = By.cssSelector("#input-payment-password");
	
	private final By txtPasswordConfirmBilling = By.cssSelector("#input-payment-confirm");
	
	private final By btnGuestBillingCtn = By.cssSelector("#button-guest");
	
	private final By btnRegisterBillingCtn = By.cssSelector("#button-register");
			
	private final By checkboxPolicy = By.cssSelector("input[value='1'][name='agree']");
	
	private final By btnShoppingCart = By.cssSelector("a[title='Shopping Cart']");
	
	public boolean isDisplayCheckoutPage() {
		return isElementDisplayed(titleCheckout, "Tiêu đề Checkout");
	}
	
	public boolean isCorrectUrl() {
		try {
			return wait.until(ExpectedConditions.urlContains("/checkout"));
		} catch(Exception e) {
			throw new RuntimeException("Lỗi do url hiện tại không chứa '/checkout'", e);
		}
	}
	
	public boolean isRadioBilling() {
		return isElementSelected(radioBilling, "I want to use an existing address in Billing Details");
	}
	
	public String getAddressBilling() {
		return getText(addressBilling, "Địa chỉ giao hàng trong mục Billing Details");
	}
	
	public boolean isRadioDelivery() {
		return isElementSelected(radioDelivery, "I want to use an existing address in Delivary Details");
	}
	
	public String getAddressDelivery() {
		return getText(addressDelivery, "Địa chỉ giao hàng trong mục Delivary Details");
	}
	
	public CheckoutPage clickBtnBillingCtn() {
		click(btnBilling, "Nút Continue trong mục Billing Details");
		return this;
	}
	
	public CheckoutPage clickBtnDeliveryCtn() {
		click(btnDelivery, "Nút Continue trong mục Delivery Details");
		return this;
	}
	
	public boolean selectedShippingRate() {
		return isElementSelected(shippingRate, "Flat Shipping Rate");
	}
	
	public CheckoutPage clickBtnDeliveryMethodCtn() {
		click(btnDeliveryMethod, "Nút Continue trong mục Delivery Method");
		return this;
	}
	
	public boolean selectedCashOnDelivery() {
		return isElementSelected(radioCashOnDelivery, "Cash On Delivery");
	}
	
	public CheckoutPage clickCtnPaymentMethod() {
		click(btnPaymentMethodCtn, "Nút Continue trong mục Payment Method");
		return this;
	}
	
	public CheckoutPage clickTermsAndConditions() {
		click(checkTermsAndConditions, "Checkbox Terms & Conditions");
		return this;
	}
	
	// lấy ra danh sách sản phẩm thanh toán
	public List<List<Object>> getProducts() {
		int rows = getElements(rowTable, "Các hàng của bảng").size();
		int cols = getElements(colTable, "Các cột của bảng").size();
		
		List<List<Object>> list = new ArrayList<>();
		
		for(int i = 1; i <= rows; i++) {
			List<Object> subList = new ArrayList<>();
			for(int j = 1; j <= cols; j++) {
				By xpathEle = By.xpath("//div[@class='table-responsive']//table//tbody//tr[" + i + "]//td[" + j + "]");
				subList.add(getText(xpathEle, "Hàng " + i + "Cột " + j).replace(",", "").replace("$", "")); //
			}
			list.add(subList);
		}
		return list;
	}
	
	public boolean compare(List<List<Object>> listShoppingCart, List<List<Object>> listCheckout) {
		boolean flag = true;
		int size = listShoppingCart.size();
		int subSize = listShoppingCart.get(0).size();
		for(int i = 0; i < size; i++) {
			List<Object> subCart = listShoppingCart.get(i);
			List<Object> subOrder = listCheckout.get(i);
			for(int j = 0; j < subSize; j++) {
				if(j == subSize - 2 || j == subSize - 1) {
					flag = Math.abs(Double.parseDouble((String) subCart.get(j)) - 
						    			Double.parseDouble((String) subOrder.get(j))) > 0.01 ? false: true;
				} else {
					flag = ((String) subCart.get(j)).equalsIgnoreCase(((String) subOrder.get(j)));
				}
			}
		}
		return flag;
	}
	
	public boolean compareCusConfirmOrder(List<List<Object>> listShoppingCart, List<List<Object>> listCheckout, boolean isVat) {
		// Nếu khách hàng ở địa điểm tính thuế thì giá sản phẩm trong giỏ hàng phải công thêm thuế trước khi so sánh với giá trong đơn hàng
		if(isVat) {
			for(int i = 0; i < listShoppingCart.size(); i++) {
				List<Object> subLi = listShoppingCart.get(i);
				// giá của 1 sản phẩm
				int quantity = Integer.parseInt(subLi.get(2).toString());
				double unitPrice = Double.parseDouble(subLi.get(subLi.size() - 2).toString().replace(",", ""));
				unitPrice += (unitPrice * 0.2);
				unitPrice += 2; // 1 sản phẩm nên ecoTax = 2
				subLi.set(subLi.size() - 2, String.valueOf(unitPrice));
				// tổng giá của n sp
				double total = Double.parseDouble(subLi.get(subLi.size() - 1).toString().replace(",", ""));
				total += (total * 0.2);
				total += (quantity * 2); // mỗi sản phẩm có ecoTax = 2
				subLi.set(subLi.size() - 1, String.valueOf(total)); 
			}
		}
		return compare(listShoppingCart, listCheckout);
	}
	
	public boolean compareGuestConfirmOrder(List<List<Object>> listShoppingCart, List<List<Object>> listCheckout, boolean isVat) {
		// Nếu khách hàng vãng lai ở địa điểm không tính thuế thì giá sản phẩm trong giỏ hàng phải trừ thuế đi trước khi so sánh với giá trong đơn hàng
		if(!isVat) {
			for(int i = 0; i < listShoppingCart.size(); i++) {
				List<Object> subLi = listShoppingCart.get(i);
				// giá của 1 sản phẩm
				int quantity = Integer.parseInt(subLi.get(2).toString());
				double unitPrice = Double.parseDouble(subLi.get(subLi.size() - 2).toString().replace(",", ""));
				unitPrice -= 2;
				unitPrice = (unitPrice / 1.2);
				subLi.set(subLi.size() - 2, String.valueOf(unitPrice));
				// tổng giá của n sp
				double total = Double.parseDouble(subLi.get(subLi.size() - 1).toString().replace(",", ""));
				total -= (quantity * 2);
				total = (total / 1.2);
				subLi.set(subLi.size() - 1, String.valueOf(total));
			}
		}
		return compare(listShoppingCart, listCheckout);
	}
	
	// so sánh Sub-Total và Total, danh sách được truyền vào là danh sách sản phẩm thanh toán. Nếu là địa chỉ có tính thuế thì phải tính thêm thuế
	public boolean verifyTotalPrice(List<List<Object>> li, boolean isVat) {
		double subTotal = 0;
		boolean flag = true;
		double ecoTax = 0;
		double vat = 0;
		
		for(int i = 0; i < li.size(); i++) {
			List<Object> subLi = li.get(i);
			double quantity = Double.parseDouble(subLi.get(2).toString()); // Lấy ra số lượng
			double unitPrice = Double.parseDouble(subLi.get(subLi.size() - 1).toString()); // lấy ra tổng tiền của 1 sản phẩm
			// nếu có thuế thì phải trừ đi vat và ecotax để lấy giá gốc cho subTotal
			if(isVat) {
				unitPrice -= (2 * quantity); // vì giá này nó đã tính thêm ecoTax = 2 cho từng sản phẩm
				unitPrice = (unitPrice / 1.2); // tính vat = 20%
				
				ecoTax += (2 * quantity); // ecoTax cho từng sản phẩm
				vat += (unitPrice * 0.2); // vat cho từng sản phẩm
			}
			subTotal += unitPrice;
		}
		
		double currentSubTotal = Double.parseDouble(getText(subTotalXpath, "Sub-Total").toString().trim().replace("$", "").replace(",", ""));
		if(currentSubTotal != subTotal) flag = false;
		
		double shippingRate = Double.parseDouble(getText(flatShippingRate, "Flat Shipping Rate").toString().replace("$", "").replace(",", ""));
		double expectedTotal = shippingRate + subTotal;
		double currentTotal = Double.parseDouble(getText(total, "Total").toString().replace("$", "").replace(",", ""));
		
		if(isVat) {
			ecoTax += 2; // cộng thêm 2 là eco-tax của ship
			vat += (0.2 * shippingRate); // cộng thêm vat cho ship
			expectedTotal += (ecoTax + vat);
			
			// Khi có thêm vat và ecoTax thì dòng total là dòng 
			currentTotal = Double.parseDouble(getText(totalVat, "Total").toString().replace("$", "").replace(",", ""));
			
		}
		// hai số double có thể sai lệch phần thập phân
		if(Math.abs(expectedTotal - currentTotal) > 0.01) flag = false;
		
		return flag;
	}
	
	public OrderSuccessPage clickConfirmOrder() {
		click(btnConfirmOrder, "Nút ConfirmOrder");
		return new OrderSuccessPage(driver);
	}
	
	public CheckoutPage clickConfirmOrderAndAlert() {
		click(btnConfirmOrder, "Nút ConfirmOrder");
		return this;
	}
	
	public CheckoutPage closeAlert() {
		try {
			wait.until(ExpectedConditions.alertIsPresent()).accept();
			logger.info("Thành công: Đã đóng cảnh báo trong trang Checkout");
			return this;
		} catch(Exception e) {
			logger.error("Lỗi: Không thể đóng cảnh báo trong trang checkout. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không thể đóng cảnh báo trong trang checkout", e);
		}
    }
	
	public CheckoutPage fillBillingDetailForm(CheckoutData data) throws InterruptedException {
		click(newAddressBilling, "I want to use a new address (Billing Detail");
		type(firstNameBilling, data.getFirstName(), "First Name");
		type(lastNameBilling, data.getLastName(), "Last Name");
		type(companyBilling, data.getCompany(), "Company");
		type(address1Billing, data.getAddress1(), "Address1");
		type(address2Billing, data.getAddress2(), "Address2");
		type(cityBilling, data.getCity(), "City");
		type(postCodeBilling, data.getPostCode(), "Post Code");
		WebElement country = getElement(countryBilling, "Country");
		
		Select selectCountry = new Select(country); 
		selectCountry.selectByVisibleText(data.getCountry());
		
		wait.until(d -> {
	        WebElement regionEle = d.findElement(regionStateBilling);
	        Select selectRegion = new Select(regionEle);
	        return selectRegion.getOptions().size() > 1; 
	    });
		
		WebElement region_State = getElement(regionStateBilling, "Region/state");
		Select selectRegionState = new Select(region_State); 
		selectRegionState.selectByVisibleText(data.getRegionState());
		
		return this;
	}
	
	public CheckoutPage fillDeliveryDetailForm(CheckoutData data) {
		click(newAddressDelivery, "I want to use a new address (Delivery Detail");
		type(firstNameDelivery, data.getFirstName(), "First Name");
		type(lastNameDelivery, data.getLastName(), "Last Name");
		type(companyDelivery, data.getCompany(), "Company");
		type(address1Delivery, data.getAddress1(), "Address1");
		type(address2Delivery, data.getAddress2(), "Address2");
		type(cityDelivery, data.getCity(), "City");
		type(postCodeDelivery, data.getPostCode(), "Post Code");
		WebElement country = getElement(countryDelivery, "Country");
		
		Select selectCountry = new Select(country); 
		selectCountry.selectByVisibleText(data.getCountry());
		
		wait.until(d -> {
	        WebElement regionEle = d.findElement(regionStateDelivery);
	        Select selectRegion = new Select(regionEle);
	        return selectRegion.getOptions().size() > 1; 
	    });
		
		WebElement region_State = getElement(regionStateDelivery, "Region/state");
		Select selectRegionState = new Select(region_State); 
		selectRegionState.selectByVisibleText(data.getRegionState());
		
		return this;
	}
	
	public CheckoutPage selectNewAddressBilling() {
		click(newAddressBilling, "I want to use a new address");
		return this;
	}
	
	public String getValidationFirstName() {
		return getText(msgValidationFirstName, "Thông báo First Name must be between 1 and 32 characters!");
	}
	
	public String getValidationLastName() {
		return getText(msgValidationLastName, "Thông báo Last Name must be between 1 and 32 characters!");
	}
	
	public String getValidationAddress() {
		return getText(msgValidationAddress, "Thông báo Address 1 must be between 3 and 128 characters!");
	}
	
	public String getValidationCity() {
		return getText(msgValidationCity, "Thông báo City must be between 2 and 128 characters!");
	}
	
	public String getValidationPostcode() {
		return getText(msgValidationPostcode, "Thông báo Postcode must be between 2 and 10 characters!");
	}
	
	public String getValidationRegionState() {
		return getText(msgValidationRegionState, "Thông báo Please select a region / state!");
	}
	
	public String getPlaceHoderFirstNameBilling() {
		return getAttribute(firstNameBilling, "Ô First Name", "placeholder");
	}
	
	public String getPlaceHoderLastNameBilling() {
		return getAttribute(lastNameBilling, "Ô Last Name", "placeholder");
	}
	
	public String getPlaceHoderAddress1Billing() {
		return getAttribute(address1Billing, "Ô Address 1", "placeholder");
	}
	
	public String getPlaceHoderCityBilling() {
		return getAttribute(cityBilling, "Ô City", "placeholder");
	}
	
	public String getPlaceHoderPostcodeBilling() {
		return getAttribute(postCodeBilling, "Ô Post code", "placeholder");
	}
	
	public String getPlaceHoderAddress2Billing() {
		return getAttribute(address2Billing, "Ô Address 2", "placeholder");
	}
	
	public String getPlaceHoderCompanyBilling() {
		return getAttribute(companyBilling, "Ô Company", "placeholder");
	}

	public CheckoutPage selectNewAddressDelivery() {
		click(newAddressDelivery, "I want to use a new address");
		return this;
	}
	
	public String getPlaceHoderFirstNameDelivery() {
		return getAttribute(firstNameDelivery, "Ô First Name", "placeholder");
	}
	
	public String getPlaceHoderLastNameDelivery() {
		return getAttribute(lastNameDelivery, "Ô Last Name", "placeholder");
	}
	
	public String getPlaceHoderAddress1Delivery() {
		return getAttribute(address1Delivery, "Ô Address 1", "placeholder");
	}
	
	public String getPlaceHoderCityDelivery() {
		return getAttribute(cityDelivery, "Ô City", "placeholder");
	}
	
	public String getPlaceHoderPostcodeDelivery() {
		return getAttribute(postCodeDelivery, "Ô Post code", "placeholder");
	}
	
	public String getPlaceHoderAddress2Delivery() {
		return getAttribute(address2Delivery, "Ô Address 2", "placeholder");
	}
	
	public String getPlaceHoderCompanyDelivery() {
		return getAttribute(companyDelivery, "Ô Company", "placeholder");
	}
	
	// ko kiểm tra isDisplayed cho từng trường mà dùng hàm để lấy ra các trường ko hiển thị để viết ít softAssert mà vẫn lấy được ptu ko hiển thị
	public Map<By, String> getBillingFields() {
		return Map.of(
				firstNameBilling, "First Name",
				lastNameBilling, "Last Name",
				companyBilling, "Company",
				address1Billing, "Address 1",
				address2Billing, "Address 2",
				cityBilling, "City",
				postCodeBilling, "Post code",
				countryBilling, "Country",
				regionStateBilling, "Region / State"
				);
	}
	
	public Map<By, String> getDeliveryFields() {
	    return Map.of(
	        firstNameDelivery, "First Name",
	        lastNameDelivery, "Last Name",
	        companyDelivery, "Company",
	        address1Delivery, "Address 1",
	        address2Delivery, "Address 2",
	        cityDelivery, "City",
	        postCodeDelivery, "Post code",
	        countryDelivery, "Country",
	        regionStateDelivery, "Region / State"
	    );
	}

	public List<String> getMissingFields(Map<By, String> map) {
		List<String> list = new ArrayList<>();
		
		for(Map.Entry<By, String> entry : map.entrySet()) {
			if(!isElementDisplayed(entry.getKey(), entry.getValue())) {
				list.add(entry.getValue());
			}
		}
		return list;
	}
	
	public List<String> missingBillingFields(){
		return getMissingFields(getBillingFields());
	}
	
	public List<String> missingDeliveryFields(){
		return getMissingFields(getDeliveryFields());
	}

	public CheckoutPage typeCmtDeliveryMethod(String text) {
		type(txtCommentInDeliveryMethod, text, "Ô bình luận trong Delivery Method");
		return this;
	}
	
	public CheckoutPage typeCmtPaymentMethod(String text) {
		type(txtCommentInPaymentMethod, text, "Ô bình luận trong Payment Method");
		return this;
	}
	
	public String getCmtPaymentMethod() {
		return getText(txtCommentInPaymentMethod, "Ô bình luận trong Payment Method");
	}
	
	public CheckoutPage selectedGuestCheckout() {
		click(guestCheckout, "Guest Checkout");
		return this;
	}
	
	public CheckoutPage selectedRegisterCheckout() {
		click(registerAccount, "Guest Checkout");
		return this;
	}
	
	public CheckoutPage clickCntNewCustomer() {
		click(btnCtnNewCustomer, "Continue in New Customer");
		return this;
	}
	
	public boolean isBillingDetails() {
		return getAttribute(sectionBillingDetails, "Section Billing Detail", "class").contains("in") && 
	           getAttribute(sectionBillingDetails, "Section Billing Detail", "aria-expanded").equals("true");
	}
	
	public boolean selectedSameDeliveryAndBillingAddress() {
		return isElementSelected(sameDeliveryAndBillingAddress, "My delivery and billing addresses are the same.");
	}
	
	public boolean isDeliveryDetails() {
		return getAttribute(sectionDeliveryDetails, "Section Delivery Detail", "class").contains("in") && 
	           getAttribute(sectionDeliveryDetails, "Section Delivery Detail", "aria-expanded").equals("true");
	}
	
	public CheckoutPage fillBillingDetailGuestCheckout(GuestCheckoutData data) {
		type(firstNameBilling, data.getFirstName(), "First Name");
		type(lastNameBilling, data.getLastName(), "Last Name");
		type(txtEmailBilling, data.getEmail(), "Email");
		type(txtTelephoneBilling, data.getTelephone(), "Telephone");
		type(companyBilling, data.getCompany(), "Company");
		type(address1Billing, data.getAddress1(), "Address1");
		type(address2Billing, data.getAddress2(), "Address2");
		type(cityBilling, data.getCity(), "City");
		type(postCodeBilling, data.getPostCode(), "Post Code");
		WebElement country = getElement(countryBilling, "Country");
		
		Select selectCountry = new Select(country); 
		selectCountry.selectByVisibleText(data.getCountry());
		
		wait.until(d -> {
	        WebElement regionEle = d.findElement(regionStateBilling);
	        Select selectRegion = new Select(regionEle);
	        return selectRegion.getOptions().size() > 1; 
	    });
		
		WebElement region_State = getElement(regionStateBilling, "Region/state");
		Select selectRegionState = new Select(region_State); 
		selectRegionState.selectByVisibleText(data.getRegionState());
		
		return this;
	}
	
	public CheckoutPage fillBillingDetailRegisterCheckout(RegisterCheckoutData data) {
		type(firstNameBilling, data.getFirstName(), "First Name");
		type(lastNameBilling, data.getLastName(), "Last Name");
		type(txtEmailBilling, data.getEmail(), "Email");
		type(txtTelephoneBilling, data.getTelephone(), "Telephone");
		type(txtPasswordBilling, data.getPassword(), "Password");
		type(txtPasswordConfirmBilling, data.getPasswordConfirm(), "Password Confirm");
		type(companyBilling, data.getCompany(), "Company");
		type(address1Billing, data.getAddress1(), "Address1");
		type(address2Billing, data.getAddress2(), "Address2");
		type(cityBilling, data.getCity(), "City");
		type(postCodeBilling, data.getPostCode(), "Post Code");
		WebElement country = getElement(countryBilling, "Country");
		
		Select selectCountry = new Select(country); 
		selectCountry.selectByVisibleText(data.getCountry());
		
		wait.until(d -> {
	        WebElement regionEle = d.findElement(regionStateBilling);
	        Select selectRegion = new Select(regionEle);
	        return selectRegion.getOptions().size() > 1; 
	    });
		
		WebElement region_State = getElement(regionStateBilling, "Region/state");
		Select selectRegionState = new Select(region_State); 
		selectRegionState.selectByVisibleText(data.getRegionState());
		
		return this;
	}
	
	public boolean isDeliveryMethod() {
		return getAttribute(sectionDeliveryMethod, "Section Delivery Method", "class").contains("in") && 
		       getAttribute(sectionDeliveryMethod, "Section Delivery Method", "aria-expanded").equals("true");
	}
	
	public boolean isPaymentMethod() {
		return getAttribute(sectionPaymentMethod, "Section Payment Method", "class").contains("in") && 
		       getAttribute(sectionPaymentMethod, "Section Payment Method", "aria-expanded").equals("true");
	}
	
	public boolean isConfirmOrder() {
		return getAttribute(sectionConfirmOrder, "Section Confirm Order", "class").contains("in") && 
		       getAttribute(sectionConfirmOrder, "Section Confirm Order", "aria-expanded").equals("true");
	}
	
	public CheckoutPage clickGuestBillingCtn() {
		click(btnGuestBillingCtn, "Continue-Guest");
		return this;
	}
	
	public CheckoutPage clickRegisterBillingCtn() {
		click(btnRegisterBillingCtn, "Continue-Register");
		return this;
	}
	
	public CheckoutPage checkPolicy() {
		click(checkboxPolicy, "Policy Privacy");
		return this;
	}
	
	public CheckoutPage typeEmailLogin(String email) {
		type(txtEmailLogin, email, "Email in Checkout Options");
		return this;
	}
	
	public CheckoutPage typePasswordLogin(String pwd) {
		type(txtPasswordLogin, pwd, "Password in Checkout Options");
		return this;
	}
	
	public CheckoutPage clickLogin() {
		click(btnLogin, "Button Login in Checkout Options");
		return this;
	}
	
	public ShoppingCartPage goToShoppingCart() {
		click(btnShoppingCart, "Shopping Cart");
		return new ShoppingCartPage(driver);
	}
	
	public List<String> getBreadcrumbCorrect() {
		List<WebElement> eles = getElements(breadcrumb, "Breadcrumb");
		
		return eles.stream().map(e -> e.getText().trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
	}
	
	public String getUrl() {
		wait.until(ExpectedConditions.urlContains("checkout"));
		return driver.getCurrentUrl();
	}
	
	public String getTitle() {
		wait.until(ExpectedConditions.titleIs("Checkout"));
		return driver.getTitle();
	}
}
