package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        
        this.logger = LogManager.getLogger(this.getClass());
        
        // 3. Đọc linh hoạt thời gian Explicit Wait từ file config.properties
        // ResourceBundle là công cụ tuyệt vời của Java để đọc file .properties trong thư mục resources
         ResourceBundle rb = ResourceBundle.getBundle("config");
         int waitTime = Integer.parseInt(rb.getString("explicit_wait"));
        
        // Cấu hình Wait tường minh cho toàn bộ project
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    protected void click(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            logger.info("Thành công: Đã click vào [" + elementName + "]");
        } catch (Exception e) {
            logger.error("Lỗi: Không thể click vào [" + elementName + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không thể tương tác với: " + elementName, e);
        }
    }

    protected void type(By locator, String textValue, String elementName) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.clear();
            el.sendKeys(textValue);
            logger.info("Thành công: Đã nhập giá trị '" + textValue + "' vào [" + elementName + "]");
        } catch (Exception e) {
            logger.error("Lỗi: Không thể nhập liệu vào [" + elementName + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không thể nhập liệu vào: " + elementName, e);
        }
    }

    
    protected String getText(By locator, String elementName) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
            logger.info("Thành công: Đã lấy được text từ [" + elementName + "] là: '" + text + "'");
            return text;
        } catch (Exception e) {
            logger.error("Lỗi: Không tìm thấy text tại [" + elementName + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không lấy được text từ: " + elementName, e);
        }
    }
    
    protected String getAttribute(By locator, String elementName, String att) {
        try {
            WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String value = type.getAttribute(att);
            logger.info("Thành công: Đã lấy được attribute [" + att + "] từ [" + elementName + "] là: '" + value + "'");
            return value;
        } catch (Exception e) {
            logger.error("Lỗi: Không tìm thấy attribute tại [" + elementName + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không lấy được attribute từ: " + elementName, e);
        }
    }
    
    public WebElement getElement(By locator, String eleName) {
	    	try {
	    		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    		logger.info("Thành công: Đã lấy phần tử " + eleName);
	    		return el;
	    	} catch(Exception e) {
	    		logger.error("Lỗi: Không thể tìm thấy phần tử " + eleName);
	    		throw new RuntimeException("Test dừng do không lấy được phần tử " + eleName, e);
	    	}
    }
    
    public void sendKeysToActiveElement(String actionName, CharSequence... keys) {
    		try {
            wait.until(d -> d.switchTo().activeElement() != null);
            
         // Luôn dùng Actions cho các phím chức năng (Tab, Enter, Space)
            new Actions(driver).sendKeys(keys).perform();
            
            logger.info("Keyboard Action: Thành công [" + actionName + "]");
        } catch (Exception e) {
            logger.error("Keyboard Action: Thất bại tại bước [" + actionName + "]. Chi tiết: " + e.getMessage());
            throw new RuntimeException("Dừng script do lỗi tương tác bàn phím: " + actionName, e);
        }
    }
    
    public void tabUntilElementFound(String value, int maxRetries, String elementName) {
        for (int i = 1; i <= maxRetries; i++) {
            WebElement current = driver.switchTo().activeElement();
            String actualId = Objects.toString(current.getAttribute("id"), "");
            String actualName = Objects.toString(current.getAttribute("name"), "");
            String actualValue = Objects.toString(current.getAttribute("value"), "");
            String text = Objects.toString(current.getText(), "");
            
            if (actualId.contains(value) || actualName.contains(value) || actualValue.contains(value) || text.contains(value)) {
                logger.info("Đã tìm thấy phần tử mục tiêu tại lần Tab thứ: " + i);
                return;
            }
            pressTab();
        }
        throw new RuntimeException("Thất bại: Sau " + maxRetries + " lần Tab vẫn không thấy phần tử " + elementName );
    }

    public void pressTab() {
        sendKeysToActiveElement("Phím TAB", Keys.TAB); 
    }

    public void pressSpace(String context) {
        sendKeysToActiveElement("Phím SPACE tại " + context, Keys.SPACE);
    }

    public void pressEnter(String context) {
        sendKeysToActiveElement("Phím ENTER tại " + context, Keys.ENTER);
    }
    
    protected boolean isElementSelected(By locator, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            // kiểm tra ptu hiển thị sau đó mới check selected, tránh dùng elementToBeSelected nếu ptu ko được chọn thì phải chờ hết time mới báo lỗi
            boolean isSelected = element.isSelected();
            
            logger.info("Kiểm tra trạng thái hiển thị của [" + elementName + "]: Đang được chọn." + isSelected);
            
            return isSelected;
        } catch (TimeoutException e) {
        		logger.info("Lỗi: Phần tử [" + elementName + "] không hiển thị");
            return false;
        } catch (Exception e) {
        		logger.info("Lỗi: Lỗi không xác định khi kiểm tra [" + elementName + "]: " + e.getMessage());
            return false;
        }
    }
    
    /*
     * TH the input bị ẩn đi và thay thế bằng phần tử khác đẹp mắt hơn thì nên dùng cách này. 
     * Vì người dùng tương tác với ptu kia chứ ko phải nút input có type là radio đó, và vì bị ẩn đi nên visibilityOf sẽ ko đúng
     * protected boolean isSelectedByJS(By locator) {
        // 1. Chỉ cần nó có mặt trong DOM (presence)
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        
        // 2. Dùng JS can thiệp thẳng vào thuộc tính 'checked' của HTML
        JavascriptExecutor js = (JavascriptExecutor) driver; // Ép kiểu driver
        
        // Trả về true/false dựa trên ruột của DOM
        return (Boolean) js.executeScript("return arguments[0].checked;", element);
    }
     */
    
    
    protected boolean isElementDisplayed(By locator, String elementName) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isDisplayed = el.isDisplayed();
            logger.info("Kiểm tra trạng thái hiển thị của [" + elementName + "]: Đang hiển thị."  + isDisplayed);
            
            return isDisplayed;
        } catch (TimeoutException e) {
	    		logger.info("Lỗi: Phần tử [" + elementName + "] không hiển thị");
	        return false;
	    } catch (Exception e) {
	    		logger.info("Lỗi: Lỗi không xác định khi kiểm tra [" + elementName + "]: " + e.getMessage());
	        return false;
	    }
    }
    
    protected boolean isElementNotDisplayed(By locator, String elementName) {
    		try {
	    		boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    		logger.info("Kiểm tra trạng thái hiển thị của [" + elementName + "]: Đang không hiển thị."  + isInvisible);
	    		return true;
    		} catch (TimeoutException e) {
	    		logger.info("Lỗi: Phần tử [" + elementName + "] không hiển thị");
	        return false;
	    } catch (Exception e) {
	    		logger.info("Lỗi: Lỗi không xác định khi kiểm tra [" + elementName + "]: " + e.getMessage());
	        return false;
	    }
    }
    
    protected List<WebElement> getElements(By locator, String elementName) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            logger.info("Thành công: Đã tìm thấy " + elements.size() + " phần tử của [" + elementName + "]");
            return elements;
        } catch (Exception e) {
            logger.error("Lỗi: Không tìm thấy danh sách phần tử [" + elementName + "]. Chi tiết: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }
    
    protected void clickElementFromListByText(By listLocator, String textToSearch, String listName) {
        List<WebElement> elements = getElements(listLocator, listName);
        boolean found = false;
        for (WebElement element : elements) {
            if (element.getText().trim().toLowerCase().contains(textToSearch.toLowerCase())) {
                element.click();
                logger.info("Thành công: Đã tìm thấy và click vào item chứa text '" + textToSearch + "' trong [" + listName + "]");
                found = true;
                break;
            }
        }
        if (!found) {
            logger.error("Thất bại: Không tìm thấy item nào chứa text '" + textToSearch + "' trong [" + listName + "]");
            throw new RuntimeException("Không tìm thấy sản phẩm mục tiêu: " + textToSearch);
        }
    }
    
    protected boolean isElementInListByText(By listLocator, String productName) {
        List<WebElement> elements = getElements(listLocator, productName);

        return elements.stream()
                .anyMatch(e -> e.getText().trim().equalsIgnoreCase(productName.trim()));
    }
    
    public void hover(String category, String elementName) {
        try {
        		By linkXpath = By.xpath("//a[normalize-space()='"+ category +"']");
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(linkXpath));
            
            new Actions(driver).moveToElement(el).perform();
            
            logger.info("Thành công: Đã hover chuột vào [" + elementName + "]");
        } catch (Exception e) {
            logger.error("Lỗi: Không thể hover vào [" + elementName + "]. Chi tiết: " + e.getMessage());
            throw new RuntimeException("Dừng test do lỗi Hover tại: " + elementName, e);
        }
    }
    
}
