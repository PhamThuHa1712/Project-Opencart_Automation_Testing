package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsletterSubscriptionPage extends BasePage {
	
	public NewsletterSubscriptionPage(WebDriver driver) {
		super(driver);
	}
	
	private final By radioYes = By.xpath("//input[@name='newsletter' and @value='1']");

	private final By radioNo = By.xpath("//input[@name='newsletter' and @value='0']");
	
	public boolean selectedYes() {
		return isElementSelected(radioYes, "Yes radio");
	}
	
	public boolean selectedNo() {
		return isElementSelected(radioNo, "No radio");
	}
}
