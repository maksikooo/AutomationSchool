import java.util.ArrayList;
import java.util.stream.Collectors;


import org.hamcrest.beans.HasPropertyWithValue;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


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

    public void productItemsInitialization() {
        driver.findElements(By.xpath("//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")).stream().map(x -> productItems.add(new ProductItem(x))).collect(Collectors.toList());
    }

    public ArrayList<ProductItem> getProductItems() {
        return productItems;
    }

    public void itemsTitleHasSearchRequest(String searchString) {
        try {
            assertThat(productItems, everyItem(HasPropertyWithValue.hasProperty("itemName", is(containsString(searchString)))));
        } catch (AssertionError e) {
            e.printStackTrace();
        }

    }

    public ItemPage goToItemPage(ProductItem productItem) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",productItem.getItemWebElement());
        productItem.getItemWebElement().findElement(By.xpath(".//a")).click();
        return new ItemPage(driver);
    }
}
