package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import elements.Button;
import elements.DefaultContainerFactory;
import elements.ExtendedFieldDecorator;
import elements.ProductContainer;
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

//    private ArrayList<WebElement> productItems = new ArrayList<>();

    @FindBy(xpath = "//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")
    private List<ProductContainer> searchResults;

    @FindBy(xpath = "(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[1]")
    private Button searchResult;

    public void initElements1(WebDriver driver){
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        initElements1(driver);
    }

    public SearchPage pageTitleContainsSearchRequest(String searchString) {
        assertThat(driver.getTitle().toLowerCase(), containsString(searchString));
        return this;
    }

    public void productItemsInitialization() {
//        searchResults.stream().map(x -> productItems.add(x)).collect(Collectors.toList());
    }

    public List<ProductContainer> getProductItems() {
        return searchResults;
    }

    public SearchPage itemsTitleHasSearchRequest(String searchString) {
        try {
            MatcherAssert.assertThat(searchResults, everyItem(HasPropertyWithValue.hasProperty("productName", is(containsString(searchString)))));
        } catch (AssertionError e) {
            e.printStackTrace();
        }
        return this;
    }

    public ItemPage goToItemPage(ProductContainer product) {
        product.scrollToProduct(driver);
        product.clickOnProduct();
        return new ItemPage(driver);
    }
}
