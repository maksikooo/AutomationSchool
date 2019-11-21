import org.openqa.selenium.WebElement;

public class ProductItem {
    private String itemName;
    private float itemPrice;
    private WebElement itemWebElement;

    public void setItemName(String itemName) { this.itemName = itemName; }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemWebElement(WebElement itemWebElement) { this.itemWebElement = itemWebElement; }

    public WebElement getItemWebElement(){ return itemWebElement; }
}
