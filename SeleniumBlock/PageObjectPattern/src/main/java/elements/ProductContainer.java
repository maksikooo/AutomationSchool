package elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ProductUtils;

public class ProductContainer extends Element {

    @FindBy(xpath = ".//span[contains(@class,'a-size-base-plus')]")
    private Link productName;

    @FindBy(xpath = ".//span[@class='a-offscreen' or @class='a-color-base']")
    private Label productPrice;

    protected ProductContainer(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getProductName() {
        return productName.getText().toLowerCase();
    }

    public void clickOnProduct() {
        productName.click();
    }

    public Float getProductPrice() {
        return ProductUtils.parsePrice(productPrice.getTextByAttribute());
    }


    public void scrollToProduct(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWrappedElement());
    }
}
