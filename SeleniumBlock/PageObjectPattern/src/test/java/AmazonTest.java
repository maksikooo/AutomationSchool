import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.SearchPage;
import productItem.ProductItem;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTest {
    @RunWith(Parameterized.class)
    public static class ParameterizeTest {
        String category = "Baby";
        List<ProductItem> productItems;
        private String searchString;
        private int itemNumber;
        private WebDriver driver;

        public ParameterizeTest(String searchString, int itemNumber) {
            this.searchString = searchString;
            this.itemNumber = itemNumber;
        }

        @Parameterized.Parameters()
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {"puzzle", 1}, {"sock", 1}, {"robe", 1}, {"puzzle", 2}, {"sock", 2}, {"robe", 2}, {"puzzle", 3}, {"sock", 3}, {"robe", 3}
            });

        }

        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "src/tools/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        }

        @Test
        public void AmazonItemDetailsTest() {
            driver.get("https://www.amazon.com/");
            driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dbaby-products-intl-ship&field-keywords=");
            SearchPage onSearchPage = new HomePage(driver).changeCategory(category)
                    .searchFor(searchString)
                    .pageTitleContainsSearchRequest(searchString)
                    .itemsTitleHasSearchRequest(searchString);
            productItems = onSearchPage.getProductItems();

            onSearchPage.goToItemPage(itemNumber)
                    .addToCart()
                    .goToCart()
                    .compareItemPrice(productItems.get(itemNumber).getItemPrice())
                    .compareItemTitle(productItems.get(itemNumber).getItemName());
        }

        @After
        public void tearDown() {
            driver.close();
        }
    }
}
