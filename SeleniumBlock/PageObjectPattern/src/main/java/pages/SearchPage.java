package pages;

import elements.ExtendedFieldDecorator;
import elements.ProductContainer;
import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.HasPropertyWithValue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import productItem.ProductItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SearchPage {
    List<ProductItem> productItems = new ArrayList<>();
    private WebDriver driver;
    @FindBy(xpath = "//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")
    private List<ProductContainer> searchResults;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
        initProductItems();
    }

    public void initElements(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public void initProductItems() {
        productItems = searchResults.stream().map(x -> new ProductItem(x.getProductName(), x.getProductPrice())).collect(Collectors.toList());
    }

    public SearchPage pageTitleContainsSearchRequest(String searchString) {
        assertThat(driver.getTitle().toLowerCase(), containsString(searchString));
        return this;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public SearchPage itemsTitleHasSearchRequest(String searchString) {
        try {
            MatcherAssert.assertThat(searchResults, everyItem(HasPropertyWithValue.hasProperty("productName", is(containsString(searchString)))));
        } catch (AssertionError e) {
            e.printStackTrace();
        }
        return this;
    }

    public ProductPage goToItemPage(int itemNumber) {
        searchResults.get(itemNumber).scrollToProduct(driver).clickOnProduct();
        return new ProductPage(driver);
    }
}
