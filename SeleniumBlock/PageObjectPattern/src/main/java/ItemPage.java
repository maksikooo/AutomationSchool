import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;


public class ItemPage {
    private WebDriver driver;
    ArrayList<ProductItem> productItems;

    public ItemPage(WebDriver driver, ArrayList<ProductItem> productItems) {
        this.driver = driver;
        this.productItems = productItems;
    }

    public void addToCart() {
        if (canBeAddedToCart()) driver.findElement(By.id("add-to-cart-button")).click();
        else {
            driver.findElement(By.xpath("//span[@role='button']/span")).click();
            driver.findElement(By.xpath("//ul[@role='listbox']/li[2]")).click();
            try {
                Thread.sleep(2000); // временное решение,не нашел способ дождаться окончания js ,получаю ошибку element click intercepted
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.id("add-to-cart-button")).click();
        }
    }

    public boolean canBeAddedToCart() {
        if (driver.findElement(By.id("add-to-cart-button")).getCssValue("cursor").equals("not-allowed")) return false;
        else return true;
    }


    public CartPage goToCart() {
        if (!driver.findElements(By.id("attach-close_sideSheet-link")).isEmpty()) {
            driver.findElement(By.id("attach-close_sideSheet-link")).click();
            try {
                Thread.sleep(3000); // временное решение,не нашел способ дождаться окончания js ,получаю ошибку element click intercepted
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.id("nav-cart")).click();
        } else {
            driver.findElement(By.id("nav-cart")).click();
        }
        return new CartPage(driver, productItems);
    }
}
