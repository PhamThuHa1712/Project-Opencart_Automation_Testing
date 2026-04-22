package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsletterSubscriptionPage extends BasePage {
	
	public NewsletterSubscriptionPage(WebDriver driver) {
		super(driver);
	}
	
	private final By yesRadio = By.xpath("//input[@name='newsletter' and @value='1']");

	private final By noRadio = By.xpath("//input[@name='newsletter' and @value='0']");
	
	public boolean isNewsletterSelected(boolean expected) {
	    return expected ? isElementSelected(yesRadio, "Yes radio") : isElementSelected(noRadio, "No radio");
	}
}
