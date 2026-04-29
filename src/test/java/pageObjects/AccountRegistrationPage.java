package pageObjects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import models.RegisterData;

public class AccountRegistrationPage extends BasePage{
	
	// Constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	// Locators	
	private final By txtFirstname = By.xpath("//input[@id='input-firstname']");

	private final By txtLastname = By.xpath("//input[@id='input-lastname']");

	private final By txtEmail = By.xpath("//input[@id='input-email']");

	private final By txtTelephone = By.xpath("//input[@id='input-telephone']");

	private final By txtPassword = By.xpath("//input[@id='input-password']");

	private final By txtConfirmPassword = By.xpath("//input[@id='input-confirm']");

	private final By radioYes = By.xpath("//input[@name='newsletter' and @value='1']");

	private final By radioNo = By.xpath("//input[@name='newsletter' and @value='0']");

	private final By chkbPolicy = By.xpath("//input[@name='agree']");

	private final By btnContinue = By.xpath("//input[@value='Continue']");

	private final By msgFirstName = By.xpath("//input[@name='firstname']/following-sibling::div[@class='text-danger']");
	
	private final By msgLastName = By.xpath("//input[@name='lastname']/following-sibling::div[@class='text-danger']");
	
	private final By msgEmail = By.xpath("//input[@name='email']/following-sibling::div[@class='text-danger']");
	
	private final By msgTelephone = By.xpath("//input[@name='telephone']/following-sibling::div[@class='text-danger']"); 
	
	private final By msgPassword = By.xpath("//input[@name='password']/following-sibling::div[@class='text-danger']");
	
	private final By msgConfPassword = By.xpath("//input[@name='confirm']/following-sibling::div[@class='text-danger']");
	
	private final By msgPrivacyPolicy = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	private final By headerRegiterPage = By.cssSelector("div[id='content'] h1");
	
	private final By lnkMyAccount = By.cssSelector("a[title='My Account']");
	
	private final By lnkLogin = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Login')]");
	
	private final By labelFirstName = By.cssSelector("label[for='input-firstname']");
	
	private final By labelLastName = By.cssSelector("label[for='input-lastname']");
	
	private final By labelEmail = By.cssSelector("label[for='input-email']");
	
	private final By labelTelephone = By.cssSelector("label[for='input-telephone']");
	
	private final By labelPassword = By.cssSelector("label[for='input-password']");
	
	private final By labelConfPassword = By.cssSelector("label[for='input-confirm']");
	
	private final By privacyPolicyText = By.cssSelector("div[class='pull-right']");
	
	private final By listBreadCrumb = By.cssSelector(".breadcrumb li");
	
	private final By lnkLoginPage = By.cssSelector("div[id='content'] p a");
	
	public enum RegisterField {
	    TELEPHONE, PASSWORD, EMAIL, FIRSTNAME, LASTNAME, PASSWORD_CONFIRM
	}
	
	
	public boolean isPageRegisterHeading() {
		return getText(headerRegiterPage, "Trang đăng ký").contains("Register Account");
	}
	
	public AccountRegistrationPage clickMyAccount() {
		click(lnkMyAccount, "My Account");
		return this;
	}
	
	public LoginPage clickLogin() {
		click(lnkLogin, "Login");
		return new LoginPage(driver);
	}
	
	
	public AccountRegistrationPage setFirstName(String fname) {
		type(txtFirstname, fname, "Trường First Name");
		return this;
	}
	
	public AccountRegistrationPage setLastName(String lname) {
		type(txtLastname, lname, "Trường Last Name");
		return this;
	}
	
	public AccountRegistrationPage setEmail(String email) {
		type(txtEmail, email, "Trường Email");
		return this;
	}

	public AccountRegistrationPage setTelephone(String tel) {
		type(txtTelephone, tel, "Trường Telephone");
		return this;
	}
	
	public AccountRegistrationPage setPassword(String pwd) {
		type(txtPassword, pwd, "Trường Password");
		return this;
	}
	public AccountRegistrationPage setConfirmPassword(String pwd) {
		type(txtConfirmPassword, pwd, "Trường ConfirmPassword");
		return this;
	}
	
	public AccountRegistrationPage selectNewsletter(boolean value) {
	    if (value) {
	        click(radioYes, "Lựa chọn Yes trong NewsLetter");
	    } else {
	        click(radioNo, "Lựa chọn No trong NewsLetter");
	    }
        return this;
	}
	
	public AccountRegistrationPage setPrivacyPolicy(boolean value) {
		if(value)
			click(chkbPolicy, "Trường check Privacy Policy");
		return this;
	}

	
	public AccountSuccessPage clickContinueSuccess() {
	    //sol1
		click(btnContinue, "Nút Continue");
		return new AccountSuccessPage(driver);

	    //sol2
	    //btnContinue.submit();

	    //sol3
	    //Actions act=new Actions(driver);
	    //act.moveToElement(btnContinue).click().perform();

	    //sol4
	    //JavascriptExecutor js=(JavascriptExecutor)driver;
	    //js.executeScript("arguments[0].click();", btnContinue);

	    //Sol5
	    //btnContinue.sendKeys(Keys.RETURN);

	    //Sol6
	    //WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    //mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public AccountRegistrationPage clickContinueFail() {
		click(btnContinue, "Nút Continue");
		return this;
	}
	
	public LoginPage clickLnkLoginPage() {
		click(lnkLoginPage, "Link Login Page");
		return new LoginPage(driver);
	}
	
	public AccountSuccessPage fillRegistrationFormByKeyboard(RegisterData data) {
		tabUntilElementFound("firstname", 30, "Trường First Name");
		sendKeysToActiveElement("Trường First Name", data.getFirstName());
		tabUntilElementFound("lastname", 3, "Trường Last Name");
	    sendKeysToActiveElement("Trường Last Name", data.getLastName());
	    tabUntilElementFound("email", 3, "Trường Email");
	    sendKeysToActiveElement("Trường Email", data.getEmail());
	    tabUntilElementFound("telephone", 3, "Trường Telephone");
	    sendKeysToActiveElement("Trường Telephone", data.getPhone());
	    tabUntilElementFound("password", 3, "Trường Password");
	    sendKeysToActiveElement("Trường Password", data.getPassword());
	    tabUntilElementFound("confirm", 3, "Trường Confirm Password");
	    sendKeysToActiveElement("Trường Confirm Password", data.getConfirmPassword());
	    tabUntilElementFound("newsletter", 3, "Trường Newsletter");
	    pressSpace("Newsletter");
	    tabUntilElementFound("agree", 3, "Trường Privacy Policy");
	    pressSpace("Privacy Policy");
	    tabUntilElementFound("Continue", 3, "Nút Continue");
	    pressEnter("Nút Continue");
	    return new AccountSuccessPage(driver);
	}
	
	public String displayMsgFirstName() {
		return getText(msgFirstName, "Thông báo lỗi trường First Name");
	}
	
	public String displayMsgLastName() {
		return getText(msgLastName, "Thông báo lỗi trường Last Name");
	}
	
	public String displayMsgEmail() {
		return getText(msgEmail, "Thông báo lỗi trường Email");
	}
	
	public String displayMsgPhone() {
		return getText(msgTelephone, "Thông báo lỗi trường Phone");
	}
	
	public String displayMsgPassword() {
		return getText(msgPassword, "Thông báo lỗi trường Password");
	}
	
	public String displayMsgConfPassword() {
		return getText(msgConfPassword, "Thông báo lỗi trường Confirm Password");
	}
	
	public String displayMsgPolicy() {
		return getText(msgPrivacyPolicy, "Thông báo lỗi trường Privacy Policy");
	}
	
	public String displayEmailExists() {
		return getText(msgPrivacyPolicy, "Thông báo lỗi email đã tồn tại!");
	}
	
	public AccountRegistrationPage fillRegisterForm(RegisterData data) {
		return this
			.setFirstName(data.getFirstName())
			.setLastName(data.getLastName())
			.setEmail(data.getEmail())
			.setTelephone(data.getPhone())
			.setPassword(data.getPassword())
			.setConfirmPassword(data.getConfirmPassword())
			.selectNewsletter(data.isNewsletter())
			.setPrivacyPolicy(data.isPrivacyPolicy());
	}
	
	public boolean waitForResponseFor(RegisterField field) {
	    By targetElement;
	    
	    switch (field) {
	    		case FIRSTNAME: targetElement = msgFirstName; break;
	    		case LASTNAME: targetElement = msgLastName; break;
	    		case EMAIL: targetElement = msgEmail; break;
	        case TELEPHONE: targetElement = msgTelephone; break;
	        case PASSWORD:  targetElement = msgPassword; break;
	        default: throw new IllegalArgumentException("Trường không tồn tại");
	    }
	    try {
	    		wait.until(ExpectedConditions.or(
		        ExpectedConditions.urlContains("success"),
		        ExpectedConditions.visibilityOfElementLocated(targetElement)
	    		));
	    } catch(Exception e) {
	    		throw new RuntimeException("Hệ thống không phản hồi sau thời gian chờ", e);
	    }
	    return driver.getCurrentUrl().contains("success");
	} 
	
	public String getFirstNamePlaceholder() {
		return getAttribute(txtFirstname, "Ô nhập liệu FirstName", "placeholder");
	}
	
	public String getLastNamePlaceholder() {
		return getAttribute(txtLastname, "Ô nhập liệu LastName", "placeholder");
	}
	
	public String getEmailPlaceholder() {
		return getAttribute(txtEmail, "Ô nhập liệu Email", "placeholder");
	}
	
	public String getTelephonePlaceholder() {
		return getAttribute(txtTelephone, "Ô nhập liệu Telephone", "placeholder");
	}
	
	public String getPasswordPlaceholder() {
		return getAttribute(txtPassword, "Ô nhập liệu Password", "placeholder");
	}
	
	public String getConfirmPasswordPlaceholder() {
		return getAttribute(txtConfirmPassword, "Ô nhập liệu Confirm Password", "placeholder");
	}
	
	private String getPseudoEle(RegisterField field, String property) {
	    By targetElement;

	    switch(field) {
	        case FIRSTNAME: targetElement = labelFirstName; break;
	        case LASTNAME: targetElement = labelLastName; break;
	        case EMAIL: targetElement = labelEmail; break;
	        case TELEPHONE: targetElement = labelTelephone; break;
	        case PASSWORD: targetElement = labelPassword; break;
	        case PASSWORD_CONFIRM: targetElement = labelConfPassword; break;
	        default: throw new IllegalArgumentException("Trường không tồn tại");
	    }

	    return getPseudoElementProperty(targetElement, "::before", property);
	}
	
	public boolean isRequiredField(RegisterField field) {
		String content = getPseudoEle(field, "content");
		String color = getPseudoEle(field, "color");
		
		return content.contains("*") && color.contains("255, 0, 0");
	}
	
	public String getPrivacyPolicyText() {
		return getText(privacyPolicyText, "I have read and agree to the Privacy Policy");
	}
	
	public boolean isSelectedPolicy() {
		return isElementSelected(chkbPolicy, "Privacy Policy");
	}
	
	public boolean hidePasswordAndConfPassword() {
		String typePwd = getAttribute(txtPassword, "Ô nhập liệu Password", "type");
		String typeConfPwd = getAttribute(txtConfirmPassword, "Ô nhập liệu Password Confirm", "type");
		
		return typePwd.equals("password") && typeConfPwd.equals("password");
	}
	
	public List<String> getBreadcrumbCorrect() {
		List<WebElement> eles = getElements(listBreadCrumb, "Breadcrumb");
		
		return eles.stream().map(e -> e.getText().trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
	}
	
	public String getUrl() {
		wait.until(ExpectedConditions.urlContains("register"));
		return driver.getCurrentUrl();
	}
	
	public String getTitle() {
		wait.until(ExpectedConditions.titleIs("Register Account"));
		return driver.getTitle();
	}
}
