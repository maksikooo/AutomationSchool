package utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitUtils {
    public static void waitForElementShow(WebDriver driver, WebElement element, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration, 250);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ignored) {
        }
    }
    public static void waitForElementCanBeClicked(WebDriver driver,WebElement element, int duration){
        WebDriverWait wait = new WebDriverWait(driver, duration, 250);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void threadSleep(int timeoutMills){
        try {
            Thread.sleep(timeoutMills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementChangeAttribute(WebDriver driver,WebElement element,String attribute,String value){
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            wait.until(ExpectedConditions.attributeToBe(element, attribute,value));
        } catch (StaleElementReferenceException ignored){
        }
    }
}
