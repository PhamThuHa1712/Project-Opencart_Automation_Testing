package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	private final By msgHeading = By.xpath("//h2[normalize-space()='My Account']");

	private final By newsletter = By.xpath("//a[normalize-space()='Subscribe / unsubscribe to newsletter']");

	private final By lnkLogout = By.xpath("//div[@class='list-group']//a[text()='Logout']");
	
	public boolean isMyAccountPageExists()
	{
		return isElementDisplayed(msgHeading, "My Account");
	}
	
	public void clickNewsletter() {
		click(newsletter, "Subscribe / unsubscribe to newsletter");
	}
	
	public void clickLogout() {
		click(lnkLogout, "Logout");
	}
}
