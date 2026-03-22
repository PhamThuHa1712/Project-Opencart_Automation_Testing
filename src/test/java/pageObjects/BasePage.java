package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        
        PageFactory.initElements(driver, this);

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
            logger.error("LỖI: Không thể click vào [" + elementName + "]. Chi tiết: " + e);
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
            logger.error("LỖI: Không thể nhập liệu vào [" + elementName + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không thể nhập liệu vào: " + elementName, e);
        }
    }

    
    protected String getText(By locator, String elementName) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            logger.info("Thành công: Đã lấy được text từ [" + elementName + "] là: '" + text + "'");
            return text;
        } catch (Exception e) {
            logger.error("LỖI: Không tìm thấy text tại [" + elementName + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không lấy được text từ: " + elementName, e);
        }
    }
    
    protected boolean isElementSelected(By locator, String elementName) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean status = el.isSelected();
            logger.info("Phần tử [" + elementName + "] selected = " + status);
            return status;
        } catch (Exception e) {
            logger.error("LỖI: Không thể kiểm tra isSelected của [" + elementName + "]. Chi tiết: " + e.getMessage());
            throw new RuntimeException("Test dừng: Không thể kiểm tra isSelected của [" + elementName + "]", e);
        }
    }
    
    /*
     * TH ther input bị ẩn đi và thay thế bằng phần tử khác đẹp mắt hơn thì nên dùng cách này. 
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
    
    /** DÙNG CHO BY Locator
     * Trả về true nếu tồn tại trong DOM và có hiển thị.
     * Trả về false nếu bị ẩn, hoặc hoàn toàn không có trong DOM.
    protected boolean isElementDisplayed(By locator) {
        // Dùng số nhiều để không bao giờ bị văng NoSuchElementException
        List<WebElement> elements = driver.findElements(locator);
        
        if (elements.size() > 0) {
            // Nếu có tồn tại trong DOM, thì mới kiểm tra xem nó có bị CSS ẩn đi không
            return elements.get(0).isDisplayed();
        }
        
        // Nếu List rỗng (không có trong DOM), dĩ nhiên là không hiển thị
        return false;
    }
    */
    protected boolean isElementDisplayed(By locator, String elementName) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean status = el.isDisplayed();
            logger.info("Phần tử [" + elementName + "] displayed = " + status);
            return status;
        } catch (Exception e) {
            logger.error("LỖI: Không thể kiểm tra isDisplayed của [" + elementName + "]. Chi tiết: " + e.getMessage());
            throw new RuntimeException("Test dừng: Không thể kiểm tra isDisplayed của [" + elementName + "]", e);
        }
    }
    
    protected boolean isElementNotDisplayed(By locator) {
        try {
            // Chỉ cho phép đợi 2 giây để xác nhận nó không có ở đó
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            // Nếu sau 2s nó vẫn vẫn hiển thị -> trả về false
            return false;
        }
    }
    
}
