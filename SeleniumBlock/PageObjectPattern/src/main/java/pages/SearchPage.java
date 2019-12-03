package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import productItem.ProductItem;
import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.HasPropertyWithValue;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SearchPage {
    private WebDriver driver;
    private ArrayList<ProductItem> productItems = new ArrayList<>();
    private ArrayList<WebElement> productItems1 = new ArrayList<>();

    @FindBy(xpath = "//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")
    private List<WebElement> searchResults;

    public void initElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
        productItemsInitialization();
    }

    public SearchPage pageTitleContainsSearchRequest(String searchString) {
        assertThat(driver.getTitle().toLowerCase(), containsString(searchString));
        return this;
    }

    public void productItemsInitialization() {
        searchResults.stream().map(x -> productItems1.add(x)).collect(Collectors.toList());
    }

    public ArrayList<WebElement> getProductItems() {
        return productItems1;
    }

    public SearchPage itemsTitleHasSearchRequest(String searchString) {
        try {
            MatcherAssert.assertThat(productItems, everyItem(HasPropertyWithValue.hasProperty("itemName", is(containsString(searchString)))));
        } catch (AssertionError e) {
            e.printStackTrace();
        }
        return this;
    }

    public ItemPage goToItemPage(WebElement product) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
        product.click();
        return new ItemPage(driver);
    }
}
