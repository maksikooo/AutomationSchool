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
        assertThat(driver.findElement(By.className("sc-product-title")).getText().toLowerCase(), containsString(productItem.getItemName()));
    }

    public void compareItemPrice(ProductItem productItem) {
        assertThat(ProductUtils.parsePrice(driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span[contains(@class,'sc-price')]"))),equalTo(productItem.getItemPrice()));
    }
}
