package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	private final By msgConfirmation = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");

	private final By btnCtn = By.xpath("//a[normalize-space()='Continue']");

	private final By msgEmail = By.xpath("//p[contains(text(),'A confirmation has been sent to the provided e-mail')]");
	
	// Action Methods
	public void setFirstName(String fname) {
		type(txtFirstname, fname, "Ô First Name");
	}
	
	public void setLastName(String lname) {
		type(txtLastname, lname, "Ô Last Name");
	}
	
	public void setEmail(String email) {
		type(txtEmail, email, "Ô Email");
	}

	public void setTelephone(String tel) {
		type(txtTelephone, tel, "Ô Telephone");
	}
	
	public void setPassword(String pwd) {
		type(txtPassword, pwd, "Ô Password");
	}

	public void setConfirmPassword(String pwd) {
		type(txtConfirmPassword, pwd, "Ô ConfirmPassword");
	}
	
	public void setRadioYes() {
		click(radioYes, "Lựa chọn Yes trong NewsLetter");
	}
	
	public void setRadioNo() {
		click(radioNo, "Lựa chọn No trong NewsLetter");
	}
	
	public void setPrivacyPolicy() {
		click(chkbPolicy, "Ô check Privacy Policy");
	}
	
	public void clickContinue() {
	    //sol1
		click(btnContinue, "Nút Continue");

	    //sol2
	    //btnContinue.submit();

	    //sol3
	    //Actions act=new Actions(driver);
	    //act.moveToElement(btnContinue).click().perform();

	    //sol4
	    //JavascriptExecutor js=(JavascriptExecutor)driver;
	    //js.executeScript("arguments[0].click();", btnContinue);

	    //Sol 5
	    //btnContinue.sendKeys(Keys.RETURN);

	    //Sol6
	    //WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    //mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMsg() {
	    return getText(msgConfirmation, "Thông báo đăng ký thành công!");
	}
	
	public String getConfirmationEmail() {
		return getText(msgEmail, "Thông báo xác nhận gửi e-mail");
	}
	
	public void clickCnt() {
		click(btnCtn, "Nút Continue");
	}
}
