package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountSuccessPage extends BasePage{
	public AccountSuccessPage(WebDriver driver) {
		super(driver);
	}
	
	private final By msgConfirmation = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");

	private final By btnCtn = By.xpath("//a[normalize-space()='Continue']");

	private final By msgConfEmail = By.xpath("//p[contains(text(),'A confirmation has been sent to the provided e-mail')]");
	
	
	public String getConfirmationMsg() {
	    return getText(msgConfirmation, "Thông báo đăng ký thành công!");
	}
	
	public String getConfirmationEmail() {
		return getText(msgConfEmail, "Thông báo xác nhận gửi e-mail");
	}
	
	public MyAccountPage clickCnt() {
		click(btnCtn, "Nút Continue");
		return new MyAccountPage(driver);
	}
}
