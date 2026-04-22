package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
	
	// Action Methods
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
	
	public AccountRegistrationPage setPrivacyPolicy() {
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
		setFirstName(data.getFirstName());
		setLastName(data.getLastName());
		setEmail(data.getEmail());
		setTelephone(data.getPhone());
		setPassword(data.getPassword());
		setConfirmPassword(data.getConfirmPassword());
		selectNewsletter(data.isNewsletter());
		if(data.isPrivacyPolicy()) {
			setPrivacyPolicy();
		}
		return this;
	}
	
	
	public boolean isPhoneErrorDisplayed() {
	    try {
	        List<WebElement> errors = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(msgTelephone));
	        return errors.size() > 0;
	    } catch (TimeoutException e) {
	        logger.info("Thông báo lỗi điện thoại không xuất hiện.");
	        return false;
	    }
	}
}
