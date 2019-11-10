import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CartPage {
    private final WebDriver driver;
    private float itemPrice;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public float getItemPrice() {
        itemPrice = Float.parseFloat(driver.findElement(By.id("sc-subtotal-amount-activecart")).getText().substring(1));
        return itemPrice;
    }

    public String getItemTitle() {
        return driver.findElement(By.className("sc-product-title")).getText();
    }
}
