package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	private final By lnkMyAccount = By.xpath("//a[@title='My Account']");

	private final By lnkRegister = By.xpath("//a[normalize-space()='Register']");

	private final By lnkLogin = By.xpath("//a[normalize-space()='Login']");
	
	public void clickMyAccount() {
		click(lnkMyAccount, "My Account");
	}
	
	public void clickRegister() {
		click(lnkRegister, "Register");
	}
	
	public void clickLogin() {
		click(lnkLogin, "Login");
	}

}
