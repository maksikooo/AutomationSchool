import java.util.ArrayList;


import org.openqa.selenium.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class SearchPage {
    private WebDriver driver;
    public ArrayList<ProductItem> productItems = new ArrayList<>();

    public SearchPage(WebDriver driver) {
        this.driver = driver;
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
        for (int i = 1; i <= itemsCountOnPage(); i++) {
            productItems.add(new ProductItem());
            productItems.get(i - 1).setItemName(getItemTitle(i));
            productItems.get(i - 1).setItemPrice(getItemPrice(i));
            productItems.get(i - 1).setItemWebElement(getItemWebElement(i));
        }
    }

    public void itemsTitleHasSearchRequest(String searchString) {
        productItemsInitialization();
        for (int i = 0; i < productItems.size(); i++) {
            try {
                assertThat(productItems.get(i).getItemName(), containsString(searchString));
            } catch (AssertionError e) {
                System.out.println(e);
            }
        }
    }

    public ItemPage goToItemPage(int i) {
        productItems.get(i - 1).getItemWebElement().click();
        return new ItemPage(driver, productItems);
    }
}
