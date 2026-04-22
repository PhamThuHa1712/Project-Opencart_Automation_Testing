package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountLogoutPage extends BasePage {
	
	public AccountLogoutPage(WebDriver driver) {
		super(driver);
	}
	
	private final By lnkMyAccount = By.xpath("//a[@title='My Account']");
	private final By lnkRegister = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']");
	private final By lnkLogin = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']");
	
	public AccountLogoutPage clickMyAccount() {
		click(lnkMyAccount, "Dropdown My Account");
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
}
