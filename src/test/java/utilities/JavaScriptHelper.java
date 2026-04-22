package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptHelper {
	public static void disableHTML5Validation(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "var forms = document.getElementsByTagName('form');" +
                            "for(var i=0; i<forms.length; i++){" +
                            "   forms[i].setAttribute('novalidate', 'novalidate');" +
                            "}";
            js.executeScript(script);
        } catch (Exception e) {
        		throw new RuntimeException("CRITICAL: Lỗi cấu hình JS tại trang Register. Chi tiết: " + e.getMessage());
        }
    }
}
