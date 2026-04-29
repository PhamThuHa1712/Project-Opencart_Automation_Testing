package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	private BreadcrumbComponent breadcrumb;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.breadcrumb = new BreadcrumbComponent(driver);
	}
	
	private final By lnkMyAccount = By.xpath("//a[@title='My Account']");

	private final By lnkRegister = By.xpath("//a[normalize-space()='Register']");

	private final By lnkLogin = By.xpath("//a[normalize-space()='Login']");
	
	private final By txtSearch = By.cssSelector("input[name='search']");
	
	private final By btnSearch = By.xpath("//button[@class='btn btn-default btn-lg']");
	
	private final By optionCheckout = By.xpath("//nav[@id='top']//li//a[normalize-space()='Checkout']");
	
	private final By btnAddToCart = By.xpath("//div[@class='product-thumb transition']//div[@class='button-group']//button[1]");
	
	private final By lnkShoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
	
	private final By productName = By.xpath("//div[@class='caption']//h4//a");
	
	private final By msgAddToCartSuccess = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private final By btnCompareProduct = By.xpath("//div[@class='product-thumb transition']//div[@class='button-group']//button[3]");
	
	private final By lnkProductComparison = By.xpath("//a[normalize-space()='product comparison']");
	
	private final By msgAddProductComparisonSuccess = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public BreadcrumbComponent breadcrumb() {
        return breadcrumb;
    }
	
	public HomePage clickMyAccount() {
		click(lnkMyAccount, "My Account");
		return this;
	}
	
	public AccountRegistrationPage clickRegister() {
		click(lnkRegister, "Register");
		return new AccountRegistrationPage(driver);
	}
	
	public LoginPage clickLogin() {
		click(lnkLogin, "Login");
		return new LoginPage(driver);
	}
	
	public HomePage typeSearch(String name) {
		type(txtSearch, name, "Ô tìm kiếm");
		return this;
	}

	public SearchPage clickSearch() {
		click(btnSearch, "Nút tìm kiếm");
		return new SearchPage(driver);
	}
	
	public SearchPage searchProduct(String name) {
		type(txtSearch, name, "Ô tìm kiếm");
		click(btnSearch, "Nút tìm kiếm");
		return new SearchPage(driver);
	}
	
	public CheckoutPage clickCheckout() {
		click(optionCheckout, "Nút Checkout");
		return new CheckoutPage(driver);
	}
	
	public ShoppingCartPage clickCheckoutWithoutProduct() {
		click(optionCheckout, "Nút Checkout");
		return new ShoppingCartPage(driver);
	}
	
	public HomePage clickAddToCart() {
		click(btnAddToCart, "Nút thêm giỏ hàng trên sản phẩm");
		return this;
	}
	
	public ShoppingCartPage clickLnkShoppingCart() {
		click(lnkShoppingCart, "Shopping Cart");
		return new ShoppingCartPage(driver);
	}
	
	public String getProductName() {
		return getText(productName, "Tên sản phẩm");
	}
	
	public String getMsgAddToCartSuccess() {
		return getText(msgAddToCartSuccess, "Thông báo thêm sản phẩm vào giỏ hàng thành công").replace("×", "").trim();
	}
	
	public HomePage clickCompareProduct() {
		click(btnCompareProduct, "Nút so sánh sản phẩm");
		return this;
	}
	
	public ProductComparisonPage clickLnkProductComparison() {
		click(lnkProductComparison, "Product comparison");
		return new ProductComparisonPage(driver);
	}
	
	public String getMsgAddProductComparisonSuccess() {
		return getText(msgAddProductComparisonSuccess, "Thông báo thêm sản phẩm so sánh thành công").replace("×", "").trim();
	}
}
