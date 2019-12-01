import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AmazonTest {
    @RunWith(Parameterized.class)
    public static class ParameterizeTest {
        private String searchString;
        private int itemNumber;
        private WebDriver driver;
        String category = "Baby";
        SearchPage searchPage;
        CartPage cartPage;
        ItemPage itemPage;
        ArrayList<ProductItem> productItems;

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
            driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        }


        @Test
        public void AmazonItemDetailsTest() {
            driver.get("https://www.amazon.com/");
            driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dbaby-products-intl-ship&field-keywords=");
            HomePage home = new HomePage(driver);
            home.changeCategory(category);
            searchPage = home.searchFor(searchString);
            productItems = searchPage.getProductItems();
            searchPage.pageTitleContainsSearchRequest(searchString);
            searchPage.itemsTitleHasSearchRequest(searchString);
            productItems = searchPage.getProductItems();
            itemPage = searchPage.goToItemPage(productItems.get(itemNumber));
            itemPage.addToCart();
            cartPage = itemPage.goToCart();
            cartPage.compareItemTitle(productItems.get(itemNumber));
            cartPage.compareItemPrice(productItems.get(itemNumber));
        }

        @After
        public void tearDown() {
            driver.close();
        }
    }
}
