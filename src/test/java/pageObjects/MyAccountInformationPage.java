package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountInformationPage extends BasePage {
	
	public MyAccountInformationPage(WebDriver driver) {
		super(driver);
	}
	
	private final By txtFirstName = By.cssSelector("#input-firstname");
	
	private final By txtLastName = By.cssSelector("#input-lastname");
	
	private final By txtEmail = By.cssSelector("#input-email");
	
	private final By txtTelephone = By.cssSelector("#input-telephone");
	
	public String getFirstNameValue() {
		return getAttribute(txtFirstName, "FirstName trong trang My Account Information", "value");
	}
	
	public String getLastNameValue() {
		return getAttribute(txtLastName, "LastName trong trang My Account Information", "value");
	}
	
	public String getEmailValue() {
		return getAttribute(txtEmail, "Email trong trang My Account Information", "value");
	}
	
	public String getTelephoneValue() {
		return getAttribute(txtTelephone, "Telephone trong trang My Account Information", "value");
	}
}
