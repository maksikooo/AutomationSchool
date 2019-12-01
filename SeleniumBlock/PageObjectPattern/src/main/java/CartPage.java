import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CartPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void compareItemTitle(ProductItem productItem) {
        try{
            assertThat(driver.findElement(By.className("sc-product-title")).getText().toLowerCase(), equalTo(productItem.getItemName()));
        }catch (AssertionError error){
            error.printStackTrace();
        }
    }

    public void compareItemPrice(ProductItem productItem) {
        assertThat(ProductUtils.parsePrice(driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span[contains(@class,'sc-price')]"))),equalTo(productItem.getItemPrice()));
    }
}
