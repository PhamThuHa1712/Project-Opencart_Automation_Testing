package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	private final By btnAddToCart = By.xpath("//button[@id='button-cart']");
	
	private final By lnkShoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
	
	private final By msgShopping = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private final By addRelateProductToCart = By.xpath("//div[@class='product-thumb transition']//div[@class='button-group']//button[1]");
	
	private final By msgSuccessAddRelateProduct = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private final By nameRelateProduct = By.xpath("//div[@class='product-thumb transition']//div[@class='caption']/h4//a");
	
	public ProductPage clickAdd() {
		click(btnAddToCart, "Nút thêm sản phẩm");
		return this;
	}
	
	public ShoppingCartPage clickShoppingCart() {
		click(lnkShoppingCart, "Link Shopping Cart");
		return new ShoppingCartPage(driver);
	}
	
	public String getSuccessAddMsg() {
		return getText(msgShopping, "Thông báo thêm vào giỏ hàng thành công");
	}
	
	public ProductPage addRelateProductToCart() {
		click(addRelateProductToCart, "Nút thêm sản phẩm liên quan vào giỏ hàng");
		return this;
	}
	
	public String msgSuccessAddRelateProduct() {
		return getText(msgSuccessAddRelateProduct, "Thông báo thêm sản phẩm liên quan vào giỏ hàng thành công");
	}
	
	public String getNameRelateProduct() {
		return getText(nameRelateProduct, "Tên sản phẩm liên quan");
	}
}
