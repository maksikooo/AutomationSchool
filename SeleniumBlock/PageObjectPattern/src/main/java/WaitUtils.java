import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


public class WaitUtils {
    public static void waitForElementShow(WebDriver driver, String id, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration, 250);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (TimeoutException ignored) {
        }
    }
}
