import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;


public class ItemPage {
    private WebDriver driver;
    ArrayList<ProductItem> productItems;
    private WebDriverWait wait;

    public ItemPage(WebDriver driver, ArrayList<ProductItem> productItems) {
        this.driver = driver;
        this.productItems = productItems;
    }

    public void addToCart() {
        wait = new WebDriverWait(driver, 10);
        if (canBeAddedToCart()) driver.findElement(By.id("add-to-cart-button")).click();
        else {
            driver.findElement(By.xpath("//span[@role='button']/span")).click();
            driver.findElement(By.xpath("//ul[@role='listbox']/li[2]")).click();
            wait.until(ExpectedConditions.attributeToBe(By.id("add-to-cart-button"), "cursor", "pointer"));
            driver.findElement(By.id("add-to-cart-button")).click();
        }
    }

    public boolean canBeAddedToCart() {
        if (driver.findElement(By.id("add-to-cart-button")).getCssValue("cursor").equals("not-allowed")) return false;
        else return true;
    }

    public CartPage goToCart() {
        if (!driver.findElements(By.id("attach-close_sideSheet-link")).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("attach-sidesheet-view-cart-button")));
            driver.findElement(By.id("attach-sidesheet-view-cart-button")).click();
        } else driver.findElement(By.id("nav-cart")).click();
        return new CartPage(driver, productItems);
    }
}
