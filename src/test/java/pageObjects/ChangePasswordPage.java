package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPage extends BasePage {
	public ChangePasswordPage(WebDriver driver) {
		super(driver);
	}
	
	private final By txtPassword = By.xpath("//input[@id='input-password']");
	private final By txtConfPassword = By.xpath("//input[@id='input-confirm']");
	private final By btnContinue = By.xpath("//input[@value='Continue']");
	
	public ChangePasswordPage typePassword(String pwd) {
		type(txtPassword, pwd, "Trường Password");
		return this;
	}
	
	public ChangePasswordPage typeConfPassword(String pwd) {
		type(txtConfPassword, pwd, "Trường Confirm Password");
		return this;
	}
	
	public MyAccountPage clickContinueSuccess() {
		click(btnContinue, "Nút Continue");
		return new MyAccountPage(driver);
	}
	
	public ChangePasswordPage clickContinueFail() {
		click(btnContinue, "Nút Continue");
		return this;
	}
}
