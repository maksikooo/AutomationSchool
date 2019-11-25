import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


public class WaitUtils {
    public static void waitForElementShow(WebDriver driver, By by, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration, 250);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException ignored) {
        }
    }
    public static void waitForElementCanBeClicked(WebDriver driver,WebElement element, int duration){
        WebDriverWait wait = new WebDriverWait(driver, duration, 250);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitUntilElementChangeAttribute(WebDriver driver,By by,String attribute,String value){
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            wait.until(ExpectedConditions.attributeToBe(by, "cursor", "pointer"));
        } catch (StaleElementReferenceException ignored){
        }
    }
}
