package pageObjects;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private final By txtEmail = By.id("input-email");
	
	private final By txtPassword = By.id("input-password");
	
	private final By btnLogin = By.cssSelector("input[value='Login']");
	
	private final By lnkForgotPassword = By.cssSelector("div[class='form-group'] a");
	
	private final By returningCustomer = By.xpath("//h2[normalize-space()='Returning Customer']");
	
	private final By warnFalsePassword = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	private final By warnExceeded = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	private final By btnCtnNewCustomer = By.cssSelector("a[class='btn btn-primary']");
	
	private final By lnkRegisterInTheRightColumn = By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");
	
	private final By lnkRightColumnLoginOption = By.xpath("//a[@class='list-group-item'][normalize-space()='Login']");
	
	private final By dropdownMyAccount = By.cssSelector("a[title='My Account']");
	
	private final By optionLogin = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Login')]");
	
	private final By breadcrumb = By.cssSelector(".breadcrumb a");
	
	public AccountRegistrationPage clickContinueNewCustomer() {
		click(btnCtnNewCustomer, "Continue in New Customer of LoginPage");
		return new AccountRegistrationPage(driver);
	}
	
	public AccountRegistrationPage clickRegisterInTheRightColumn() {
		click(lnkRegisterInTheRightColumn, "Register in the right column");
		return new AccountRegistrationPage(driver);
	}
	
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
	
	public ForgotPasswordPage clickForgotPassword() {
		click(lnkForgotPassword, "Link Forgotten Password");
		return new ForgotPasswordPage(driver);
	}
	
	public boolean isDisplayForgotPwdLink() {
		return isElementDisplayed(lnkForgotPassword, "Link Forgotten Password");
	}
	
	public boolean displayLoginPage() {
		return isElementDisplayed(returningCustomer, "Tiêu đề Returning Customer");
	}
	
	public boolean correctUrlLogin() {
		return wait.until(ExpectedConditions.urlContains("/login"));
	}
	
	public String getPlaceholderEmail() {
		return getAttribute(txtEmail, "Trường Email", "placeholder");
	}
	
	public String getPlaceholderPassword() {
		return getAttribute(txtPassword, "Trường Password", "placeholder");
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
		
		return (typePwd.equals("password")) && (!rawHtml.contains(password));
	}
	
	public LoginPage copyPasswordByShortcut() {
		copyByShortCut(txtPassword, "Ô nhập liệu Password");
		return this;
	}
	
	public LoginPage copyEmailByShortcut() {
		copyByShortCut(txtEmail, "Ô nhập liệu E-Mail Address");
		return this;
	}
	
	public LoginPage clickLoginFromRightColumn() {
		click(lnkRightColumnLoginOption, "Lựa chọn Login từ cột bên phải");
		return new LoginPage(driver);
	}
	
	public LoginPage clickDropdownMyAccount() {
		click(dropdownMyAccount, "Dropdown My Account");
		return this;
	}
	
	public LoginPage clickOptionLogin() {
		click(optionLogin, "Lựa chọn Login trong My Account");
		return new LoginPage(driver);
	}
	
	public List<String> getBreadcrumb(){
		List<WebElement> listBreadcrumb = getElements(breadcrumb, "Breadcrumb");
		
		return listBreadcrumb.stream().map(e -> e.getText().trim()).filter(d -> !d.isEmpty()).collect(Collectors.toList());
	}
	
	public boolean isCorrectTitle() {
		return wait.until(ExpectedConditions.titleIs("Account Login"));
	}
}
