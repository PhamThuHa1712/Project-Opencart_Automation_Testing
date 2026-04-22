package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	private final By imageProducts = By.xpath("//div[@class='row']//div[@class='product-thumb']/div[@class='image']");
	
	private final By nameProducts = By.xpath("//div[@class='row']//div[@class='product-thumb']//div[@class='caption']//a");
	
	private final By btnWistList = By.xpath("//div[@class='button-group']//button[@data-original-title='Add to Wish List']");
	
	private final By optionWishlist = By.id("wishlist-total");
	
	private final By btnAddToCart = By.xpath("//div[@class='button-group']/button[1]");
	
	private final By msgSuccessAdd = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private final By lnkShoppingCart = By.xpath("//ul[@class='list-inline']//li/a[@title='Shopping Cart']");
	
	private final By btnCart = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");
	
	private final By lnkViewCart = By.xpath("//strong[normalize-space()='View Cart']");
	
	private final By optionCheckout = By.xpath("//nav[@id='top']//li//a[normalize-space()='Checkout']");
	
	private final By lnkCheckout = By.xpath("//strong[normalize-space()='Checkout']");
	
	public ProductPage clickByName(String product) {
		clickElementFromListByText(nameProducts, product, "Sản phẩm có tên" + product);
		return new ProductPage(driver);
	}
	
	public SearchPage clickWistList() {
		click(btnWistList, "Nút thêm vào danh sách yêu thích");
		return this;
	}
	
	public WishListPage clickOptionWistList() {
		click(optionWishlist, "Sản phẩm yêu thích");
		return new WishListPage(driver);
	}
	
	public SearchPage clickAddToCart() {
		click(btnAddToCart, "Nút thêm sản phẩm vào giỏ hàng");
		return this;
	}
	
	public String getMsgSuccessAdd() {
		return getText(msgSuccessAdd, "Thông báo thêm sản phẩm vào giỏ hàng thành công!");
	}
	
	public ShoppingCartPage clickShoppingCart() {
		click(lnkShoppingCart, "Shopping Cart");
		return new ShoppingCartPage(driver);
	}
	
	public SearchPage clickBtnCart() {
		click(btnCart, "Nút Cart");
		return this;
	}
	
	public ShoppingCartPage clickViewCart() {
		click(lnkViewCart, "View Cart");
		return new ShoppingCartPage(driver);
	}
	
	public CheckoutPage clickCheckout() {
		click(optionCheckout, "Nút Checkout");
		return new CheckoutPage(driver);
	}
	
	public CheckoutPage clickLnkCheckout() {
		click(lnkCheckout, "Link Checkout từ nút Cart");
		return new CheckoutPage(driver);
	}
}

