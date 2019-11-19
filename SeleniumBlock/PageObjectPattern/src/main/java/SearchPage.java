import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.openqa.selenium.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class SearchPage {
    private WebDriver driver;
    public ArrayList<ProductItem> productItems = new ArrayList<>();

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        productItemsInitialization();
    }

    public void pageTitleContainsSearchRequest(String searchString) {
        assertThat(driver.getTitle().toLowerCase(), containsString(searchString));
    }

    private int itemsCountOnPage() {
        return driver.findElements(By.xpath("//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")).size();
    }

    public float getItemPrice(int i) {
        return ProductUtils.parsePrice(driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//span[@class='a-offscreen' or @class='a-color-base']", i))));
    }

    public String getItemTitle(int i) {
        return driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//span[contains(@class,'a-size-base-plus')]", i))).getText().toLowerCase();
    }

    public WebElement getItemWebElement(int i) {
        return driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//span[contains(@class,'a-size-base-plus')]", i)));
    }

    public void productItemsInitialization() {
        driver.findElements(By.xpath("//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")).stream().map(x-> productItems.add(new ProductItem(x))).collect(Collectors.toList());
       // System.out.println(productItems1.size());
//        for (int i = 1; i <= itemsCountOnPage(); i++) {
//            productItems.add(new ProductItem());
//            productItems.get(i - 1).setItemName(getItemTitle(i));
//            productItems.get(i - 1).setItemPrice(getItemPrice(i));
//            productItems.get(i - 1).setItemWebElement(getItemWebElement(i));
//        }
    }

    public ArrayList<ProductItem> getProductItems(){
        return productItems;
    }

    public void itemsTitleHasSearchRequest(String searchString) {
        productItems.stream().forEach(x-> assertThat(x.getItemName(),containsString(searchString)));
 // Внизу было ,вверху стало
//        for (int i = 0; i < productItems.size(); i++) {
//            try {
//                assertThat(productItems.get(i).getItemName(), containsString(searchString));
//            } catch (AssertionError e) {
//                System.out.println(e);
//            }
//        }
    }

    public ItemPage goToItemPage(ProductItem productItem) {
        productItem.getItemWebElement().click();
        return new ItemPage(driver);
    }
}
