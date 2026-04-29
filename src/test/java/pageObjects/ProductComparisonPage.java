package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductComparisonPage extends BasePage {
	
	public ProductComparisonPage(WebDriver driver) {
		super(driver);
	}
	
	private final By btnAddToCart = By.xpath("//tbody/tr/td[2]/input[1]");
	
	private final By msgAddToCartSuccess = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private final By lnkShoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
	
	public ProductComparisonPage clickAddToCart() {
		click(btnAddToCart, "Nút thêm sản phẩm vào giỏ hàng");
		return this;
	}
	
	public String getMsgAddToCartSuccess() {
		return getText(msgAddToCartSuccess, "Thông báo thêm sản phẩm vào giỏ hàng thành công");
	}
	
	public ShoppingCartPage clickLnkShoppingCart() {
		 click(lnkShoppingCart, "Shopping cart");
		 return new ShoppingCartPage(driver);
	}
	
}
