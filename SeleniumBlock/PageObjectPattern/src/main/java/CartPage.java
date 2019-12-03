import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CartPage{
    private final WebDriver driver;

    @FindBy(className = "sc-product-title")
    private WebElement productTitleLocator;
    @FindBy (xpath = "//span[@id='sc-subtotal-amount-activecart']/span[contains(@class,'sc-price')]")
    private WebElement itemPriceLocator;

    private void init(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
        init(driver);
    }

    public CartPage compareItemTitle(ProductItem productItem) {
        try{
            assertThat(productTitleLocator.getText().toLowerCase(), equalTo(productItem.getItemName()));
        }catch (AssertionError error){
            error.printStackTrace();
        }
        return this;
    }

    public CartPage compareItemPrice(ProductItem productItem) {
        assertThat(ProductUtils.parsePrice(itemPriceLocator),equalTo(productItem.getItemPrice()));
        return this;
    }
}
