package productItem;

import utils.ProductUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductItem {
    private String itemName;
    private float itemPrice;

    public ProductItem(WebElement element) {
        itemPrice = ProductUtils.parsePrice(element.findElement(By.xpath(".//span[@class='a-offscreen' or @class='a-color-base']")));
        itemName = element.findElement(By.xpath(".//span[contains(@class,'a-size-base-plus')]")).getText().toLowerCase();
    }

    public float getItemPrice() { return itemPrice; }

    public String getItemName() {
        return itemName;
    }

}
