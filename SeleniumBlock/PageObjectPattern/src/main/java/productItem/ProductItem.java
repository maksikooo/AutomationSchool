package productItem;

import utils.ProductUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductItem {
    private String productName;
    private float productPrice;

    public ProductItem(String productName,Float productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public float getItemPrice() { return productPrice; }

    public String getItemName() {
        return productName;
    }


}
