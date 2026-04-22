package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends BasePage {
	
	public CategoryPage(WebDriver driver) {
		super(driver);
	}
	
	private final By addToCart = By.xpath("//div[@class='button-group']//button[1]");
	
	private final By lnkShoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
	
	private final By nameProduct = By.xpath("//div[@class='product-thumb']//div[@class='caption']/h4//a");

	private final By msgShoppingCart = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public CategoryPage clickEleList(String name) {
		By eleXpath = By.xpath("//div[@class='list-group']//a[contains(text(), '" + name +"')]");
		click(eleXpath, "Danh mục " + name);
		return this;
	}
	
	public CategoryPage clickAddToCart() {
		click(addToCart, "Nút thêm giỏ hàng trên sản phẩm");
		return this;
	}
	
	public ShoppingCartPage clickLnkShoppingCart() {
		click(lnkShoppingCart, "Link Shopping Cart");
		return new ShoppingCartPage(driver);
	}
	
	public String getMsgShoppingCart() {
		return getText(msgShoppingCart, "Thông báo thêm vào giỏ hàng thành công");
	}
	
	public String getNameProduct() {
		return getText(nameProduct, "Tên sản phẩm liên quan");
	}
}
