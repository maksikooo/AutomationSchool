package productItem;

public class ProductItem {
    private String productName;
    private float productPrice;

    public ProductItem(String productName, Float productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public float getItemPrice() {
        return productPrice;
    }

    public String getItemName() {
        return productName;
    }


}
