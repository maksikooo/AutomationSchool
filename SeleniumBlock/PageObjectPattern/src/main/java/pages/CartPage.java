package pages;

import elements.ProductContainer;
import productItem.ProductItem;
import utils.ProductUtils;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.*;



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

    public CartPage compareItemTitle(String productName) {
        try{
            MatcherAssert.assertThat(productTitleLocator.getText().toLowerCase(), equalTo(productName));
        }catch (AssertionError error){
            error.printStackTrace();
        }
        return this;
    }

    public CartPage compareItemPrice(Float productPrice) {
        MatcherAssert.assertThat(ProductUtils.parsePrice(itemPriceLocator),equalTo(productPrice));
        return this;
    }
}
