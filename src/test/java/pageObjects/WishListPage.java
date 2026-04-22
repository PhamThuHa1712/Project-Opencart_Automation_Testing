package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WishListPage extends BasePage {
	
	public WishListPage(WebDriver driver) {
		super(driver);
	}
	
	private final By nameProduct = By.xpath("//div[@class='table-responsive']//tbody//td[2]//a");
	
	private final By btnAddToCart = By.xpath("//button[@class='btn btn-primary']");
	
	private final By optionShoppingCart = By.xpath("//a[@title='Shopping Cart']");
	
	private final By msgAddTocart = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private final By products = By.xpath("//table[@class='table table-bordered table-hover']//tbody//td");
	
	
	public WishListPage clickAddToCart(String productName) {
	    try {
	        List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(products));
	        
	        for (WebElement row : productList) {
	            if (row.getText().toLowerCase().contains(productName.toLowerCase())) {
	            		WebElement btn = wait.until(d -> row.findElement(btnAddToCart)); 
	                wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
	                logger.info("Đã click Add to Cart cho sản phẩm: " + productName);
	                return this;
	            }
	        }
	        throw new RuntimeException("Không tìm thấy sản phẩm '" + productName + "' trong Wishlist");
	        
	    } catch (Exception e) {
	        logger.error("Lỗi khi click Add to Cart: " + e.getMessage());
	        throw e; 
	    }
	}
	
	public ShoppingCartPage clickShoppingCart() {
		click(optionShoppingCart, "Giỏ hàng");
		return new ShoppingCartPage(driver);
	}
	
	public String msgSuccessAddToCart() {
		return getText(msgAddTocart, "Thông báo thêm vào giỏ hàng thành công!");
	}
}
