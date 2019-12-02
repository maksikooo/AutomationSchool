import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductItem {
    private String itemName;
    private float itemPrice;
    private WebElement webElement;

     ProductItem(WebElement element){
        webElement = element;
        itemPrice = ProductUtils.parsePrice(webElement.findElement(By.xpath(".//span[@class='a-offscreen' or @class='a-color-base']")));
        itemName = webElement.findElement(By.xpath(".//span[contains(@class,'a-size-base-plus')]")).getText().toLowerCase();
     }

    float getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    WebElement getItemWebElement(){ return webElement; }
}
