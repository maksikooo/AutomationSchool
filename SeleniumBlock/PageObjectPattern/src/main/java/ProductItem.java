import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class ProductItem {
    public String itemName;
    float itemPrice;
    private WebElement webElement;
     ProductItem(WebElement element){
        webElement = element;
        itemPrice = ProductUtils.parsePrice(webElement.findElement(By.xpath(".//span[@class='a-offscreen' or @class='a-color-base']")));
        itemName = webElement.findElement(By.xpath(".//span[contains(@class,'a-size-base-plus')]")).getText().toLowerCase();
     }

    float getItemPrice() {
        return itemPrice;
    }

    String getItemName() {
        return itemName;
    }

    public WebElement getItemWebElement(){ return webElement; }
}
