package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	private final By myAccount = By.xpath("//a[@title='My Account']");
	
	private final By msgHeading = By.xpath("//h2[normalize-space()='My Account']");

	private final By newsletter = By.xpath("//a[normalize-space()='Subscribe / unsubscribe to newsletter']");

	private final By lnkLogout = By.xpath("//div[@class='list-group']//a[text()='Logout']");
	
	private final By lnkChangepwd = By.xpath("//a[normalize-space()='Change your password']");
	
	private final By logo = By.xpath("//img[@title='Your Store']");
	
	private final By lnkEditAccount = By.xpath("//a[normalize-space()='Edit Account']");
	
	public MyAccountPage clickMyAcc() {
		click(myAccount, "Dropdown My Account");
		return this;
	}
	
	public boolean isMyAccountPageExists()
	{
		return isElementDisplayed(msgHeading, "My Account");
	}
	
	public NewsletterSubscriptionPage clickNewsletter() {
		click(newsletter, "Subscribe / unsubscribe to newsletter");
		return new NewsletterSubscriptionPage(driver);
	}
	
	public AccountLogoutPage clickLogout() {
		click(lnkLogout, "Logout");
		return new AccountLogoutPage(driver);
	}
	
	public boolean isCorrectUrl() {
		try {
			return wait.until(ExpectedConditions.urlContains("/account"));
		} catch(Exception e) {
			throw new RuntimeException("Lỗi do url hiện tại không chứa '/account'", e);
		}
	}
	
	public ChangePasswordPage clickChangePwd() {
		click(lnkChangepwd, "Change Password");
		return new ChangePasswordPage(driver);
	}
	
	public HomePage toHomepage() {
		click(logo, "Logo");
		return new HomePage(driver);
	}
	
	public MyAccountInformationPage clickEditAccount() {
		click(lnkEditAccount, "Edit Account");
		return new MyAccountInformationPage(driver);
	}
}
