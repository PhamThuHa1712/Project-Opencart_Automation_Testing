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
	
}
