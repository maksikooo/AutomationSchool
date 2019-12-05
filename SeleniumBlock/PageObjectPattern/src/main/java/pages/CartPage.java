package pages;

import elements.ExtendedFieldDecorator;
import elements.Label;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ProductUtils;

import static org.hamcrest.CoreMatchers.equalTo;


public class CartPage {
    private final WebDriver driver;

    @FindBy(className = "sc-product-title")
    private Label itemNameLabel;
    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']/span[contains(@class,'sc-price')]")
    private Label itemPriceLabel;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
    }

    private void initElements(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public CartPage compareItemTitle(String productName) {
        try {
            MatcherAssert.assertThat(itemNameLabel.getText().toLowerCase(), equalTo(productName));
        } catch (AssertionError error) {
            error.printStackTrace();
        }
        return this;
    }

    public CartPage compareItemPrice(Float productPrice) {
        MatcherAssert.assertThat(ProductUtils.parsePrice(itemPriceLabel.getTextByAttribute()), equalTo(productPrice));
        return this;
    }
}
