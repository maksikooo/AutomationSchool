import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CartPage {
    private final WebDriver driver;
    private final ArrayList<ProductItem> productItems;

    public CartPage(WebDriver driver, ArrayList<ProductItem> productItems) {
        this.driver = driver;
        this.productItems = productItems;
    }

    public void compareItemTitle(int i) {
        assertThat(driver.findElement(By.className("sc-product-title")).getText().toLowerCase(), containsString(productItems.get(i - 1).getItemName()));
    }

    public void compareItemPrice(int i) {
        assertThat(Float.parseFloat(driver.findElement(By.id("sc-subtotal-amount-activecart")).getText().replaceAll("\\$", "")), is(equalTo(productItems.get(i - 1).getItemPrice())));
    }
}
