import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AmazonTest {
    @RunWith(Parameterized.class)
    public static class ParameterizeTest {
        private String searchString;
        private WebDriver driver;
        String category = "Baby";
        SearchPage searchPage;
        CartPage cartPage;
        ItemPage itemPage;

        public ParameterizeTest(String searchString) {
            this.searchString = searchString;
        }

        @Parameterized.Parameters()
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[]{"puzzle", "sock", "robe"});
        }

        @Before
        public void setUp() {
            driver = new ChromeDriver();
            //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
            driver.get("https://www.amazon.com/");
        }

        @Test
        public void AmazonItemDetailsTest() {
            HomePage home = new HomePage(driver);
            home.changeCategory(category);
            searchPage = home.searchFor(searchString);
            searchPage.pageTitleContainsSearchRequest(searchString);
            searchPage.itemsTitleHasSearchRequest(searchString);
            itemPage = searchPage.goToItemPage(1);
            itemPage.addToCart();
            cartPage = itemPage.goToCart();
            cartPage.compareItemTitle(1);
            cartPage.compareItemPrice(1);
        }

        @After
        public void tearDown() {
            driver.close();
        }
    }
}
