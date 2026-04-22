package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSuccessPage extends BasePage {
	public OrderSuccessPage(WebDriver driver) {
		super(driver);
	}
	
	private final By titleOrderSuccess = By.xpath("//h1[normalize-space()='Your order has been placed!']");
	
	public boolean isTitleOrderSuccess() {
		return isElementDisplayed(titleOrderSuccess, "Thông báo đặt hàng thành công");
	}
}
