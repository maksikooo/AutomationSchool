import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage {
    private final WebDriver driver;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(By.id("add-to-cart-button")).click();
    }

    public CartPage goToCart() {
        if(!driver.findElements(By.id("nav-cart")).isEmpty()) {
            driver.findElement(By.id("nav-cart")).click();
        }else
            driver.findElement(By.id("attach-sidesheet-view-cart-button-announce")).click();
        return new CartPage(driver);
    }

    public boolean itemAddedCheck(){
        if(driver.findElements(By.id("sc-subtotal-amount-activecart")).isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
