package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private final By txtEmail = By.id("input-email");
	private final By txtPassword = By.id("input-password");
	private final By btnLogin = By.cssSelector("input[value='Login']");
	private final By lnkForgotPassword = By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
	private final By returningCustomer = By.xpath("//h2[normalize-space()='Returning Customer']");
	private final By warnFalsePassword = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private final By warnExceeded = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	public LoginPage typeEmail(String email) {
		type(txtEmail, email, "Trường Email Address");
		return new LoginPage(driver);
	}
	
	public LoginPage typePassword(String pwd) {
		type(txtPassword, pwd, "Trường Password");
		return new LoginPage(driver);
	}
	
	public MyAccountPage clickLoginSuccess() {
		click(btnLogin, "Nút Login");
		return new MyAccountPage(driver);
	}
	
	public LoginPage clickLoginFail() {
		click(btnLogin, "Nút Login");
		return this;
	}
	
	public boolean displayLoginPage() {
		return isElementDisplayed(returningCustomer, "Tiêu đề Returning Customer");
	}
	
	public boolean correctUrlLogin() {
		return wait.until(ExpectedConditions.urlContains("/login"));
	}
	
	public LoginPage setEmailByTabKey(String email) {
		tabUntilElementFound("email", 30, "Trường email");
		sendKeysToActiveElement("Trường email", email);
		return this;
	}
	
	public LoginPage setPasswordByTabKey(String password) {
		tabUntilElementFound("password", 3, "Trường Password");
		sendKeysToActiveElement("Trường Password", password);
		return this;
	}
	
	public MyAccountPage clickCtnByTabKeySuccess() {
		tabUntilElementFound("Login", 3, "Nút Login");
		pressEnter("Nút Login");
		return new MyAccountPage(driver);
	}
	
	public LoginPage clickCtnByTabKeyFail() {
		tabUntilElementFound("Login", 3, "Nút Login");
		pressEnter("Nút Login");
		return this;
	}
	
	public String isDisplayWarning() {
		return getText(warnFalsePassword, "Cảnh báo email/mật khẩu không đúng!");
	}
	
	public LoginPage clickLogin6Times() {
		for(int i = 1; i <= 6; i++) {
			click(btnLogin, "Nút Login");
		}
		return this;
	}
	
	public String checkWarnExceeded() {
		return getText(warnExceeded, "Cảnh báo đăng nhập sai vượt quá số lần cho phép");
	}
	
	public String getAttributePwd(String type) {
		return getAttribute(txtPassword, "Trường Password", type);
	}
	
	public boolean verifyPasswordNotPresentInSource(String type, String password) {
		String typePwd = getAttribute(txtPassword, "Trường Password", type);
		String rawHtml = getAttribute(txtPassword, "Trường Password", "outerHTML");
		
		return (typePwd.equals("password")) && (rawHtml.contains(password));
	}
}
