package pageObjects;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BreadcrumbComponent {
	private WebDriver driver;
	private WebDriverWait wait;
    private Logger logger;

    public BreadcrumbComponent(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(this.getClass());
        
        ResourceBundle rb = ResourceBundle.getBundle("config");
        int waitTime = Integer.parseInt(rb.getString("explicit_wait"));
        
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }
    

    public CategoryPage clickBreadcrumb(String name) {
        By listXpath = By.xpath("//ul[@class='nav navbar-nav']//a[normalize-space()='" + name + "']");
        try {
        		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(listXpath));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            logger.info("Thành công: Đã click vào [" + name + "]");
            return new CategoryPage(driver);
        } catch (Exception e) {
            logger.error("Lỗi: Không thể click vào [" + name + "]. Chi tiết: " + e);
            throw new RuntimeException("Test dừng do không thể tương tác với: " + name, e);
        }
    }
}
