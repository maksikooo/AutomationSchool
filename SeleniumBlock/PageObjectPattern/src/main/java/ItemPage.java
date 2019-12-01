import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class ItemPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void changeSize() {
        driver.findElement(By.xpath("//span[@role='button']/span")).click();
        driver.findElement(By.xpath("//ul[@role='listbox']/li[2]")).click();
        WaitUtils.waitUntilElementChangeAttribute(driver,By.id("add-to-cart-button"),"cursor","pointer");
        addToCart();
    }

    public void addToCart() {
        if (canBeAddedToCart()) {
            driver.findElement(By.id("add-to-cart-button")).click();
        } else changeSize();
    }

    public boolean canBeAddedToCart() {
        WaitUtils.waitForElementShow(driver,By.id("add-to-cart-button"),5);
        return !driver.findElement(By.id("add-to-cart-button")).getCssValue("cursor").equals("not-allowed");
    }

    public CartPage goToCart() {
        WaitUtils.waitForElementShow(driver,By.id("attach-sidesheet-view-cart-button"),4);
        try{
            driver.findElement(By.id("attach-sidesheet-view-cart-button")).isDisplayed();
            driver.findElement(By.id("attach-sidesheet-view-cart-button")).click();
        }catch (NoSuchElementException a){
            driver.findElement(By.id("nav-cart")).click();
        }

        return new CartPage(driver);
    }
}
