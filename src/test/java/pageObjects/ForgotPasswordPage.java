package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage {
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	private final By heading = By.cssSelector("div[id='content'] h1");
	
	public boolean isCorrectUrl() {
		return wait.until(ExpectedConditions.urlContains("fogotten"));
	}
	
	public String getHeadingForgotPwdPage() {
		return getText(heading, "Heading trang Forgotten Password");
	}
}
