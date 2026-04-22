package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	private final By productName = By.xpath("//div[@class='table-responsive']//tbody//td[2]//a");
	
	private final By btnCheckout = By.xpath("//a[@class='btn btn-primary']");
	
	private final By colTable = By.xpath("//div[@class='table-responsive']//table//thead//td");
	
	private final By rowTable = By.xpath("//div[@class='table-responsive']//table//tbody//tr");
		
	private final By msgShoppingCartEmpty = By.xpath("//div[@id='content']//p[contains(text(),'Your shopping cart is empty!')]");
	
	public boolean presenceOfElement(String product) {
		return isElementInListByText(productName, product);
	}
	
	public CheckoutPage clickCheckout() {
		click(btnCheckout, "Nút Checkout");
		return new CheckoutPage(driver);
	}
	
	public String isShoppingCartEmpty() {
		return getText(msgShoppingCartEmpty, "Thông báo giỏ hàng đang trống");
	}
	
	// kiểm tra giá của từng sản phẩm * số lượng sản phẩm có giống với total ko
	public boolean checkTotalPrice() {
		int rows = getElements(rowTable, "Các hàng của bảng").size();
		
		for(int i = 1; i <= rows; i++) {
			By xpathQuan = By.xpath("//div[@class='table-responsive']//table//tbody//tr[" + i + "]//td[4]//input");
			By xpathPrice = By.xpath("//div[@class='table-responsive']//table//tbody//tr[" + i + "]//td[5]");
			By xpathTotal = By.xpath("//div[@class='table-responsive']//table//tbody//tr[" + i + "]//td[6]");
			/*
			System.out.println("Đang lấy số lượng sản phẩm");
			double cquantity = Double.parseDouble(getAttribute(xpathQuan, "Số lượng", "value"));
			
			System.out.println("Đang lấy giá sản phẩm " + cquantity);
			double cprice = Double.parseDouble(getText(xpathPrice, "Giá sản phẩm").substring(1).replace(",", ""));
			
			System.out.println("Đang lấy tổng tiền sản phẩm " + cprice);
			double ctotal = Double.parseDouble(getText(xpathTotal, "Tổng tiền").substring(1).replace(",", ""));
			
			System.out.println("Đã lấy xong giá trị. Bây giờ tính tổng tiền");
			double expectedTotal = cquantity * cprice;
			System.out.println("Đã lấy xong giá trị. Bây giờ tính tổng tiền" + expectedTotal + " " + ctotal);
			return expectedTotal == ctotal;
			*/
			double expectedTotal = 
					(Double.parseDouble(getAttribute(xpathQuan, "Số lượng", "value")) * Double.parseDouble(getText(xpathPrice, "Giá sản phẩm").substring(1).replace(",", "")));
			double currentTotal = Double.parseDouble(getText(xpathTotal, "Tổng tiền").substring(1).replace(",", ""));
			
			if(Math.abs(expectedTotal - currentTotal) > 0.01) {
				return false;
			}
		}
		return true;
	}
	
	// Lấy ra danh sách sản phẩm trong trang giỏ hàng
	public List<List<Object>> getProducts() {
		int rows = getElements(rowTable, "Các hàng của bảng").size();
		int cols = getElements(colTable, "Các cột của bảng").size();
		
		List<List<Object>> list = new ArrayList<>();
		
		for(int i = 1; i <= rows; i++) {
			List<Object> subList = new ArrayList<>();
			for(int j = 2; j <= cols; j++) {
				if(j == 4) {
					By xpathEle = By.xpath("//div[@class='table-responsive']//table//tbody//tr[" + i + "]//td[4]//input");
					subList.add(getAttribute(xpathEle, "Hàng " + i + "Cột " + j, "value"));
				} else {
					By xpathEle = By.xpath("//div[@class='table-responsive']//table//tbody//tr[" + i + "]//td[" + j + "]");
					subList.add(getText(xpathEle, "Hàng " + i + "Cột " + j).replace(",", ""));
				}
			}
			list.add(subList);
		}
		return list;
	}
}
