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
        private int itemNumber;
        private WebDriver driver;
        String category = "Baby";
        SearchPage searchPage;
        CartPage cartPage;
        ItemPage itemPage;

        public ParameterizeTest(String searchString, int itemNumber) {
            this.searchString = searchString;
            this.itemNumber = itemNumber;
        }

//        @Parameterized.Parameters()
//        public static Iterable<Object[]> dataForTest() {
//            return Arrays.asList(new Object[][]{
//                    {"puzzle", 1},{"sock",1},{"robe",1},
//                    {"puzzle", 2},{"sock",2},{"robe",2},
//                    {"puzzle", 3},{"sock",3},{"robe",3}
//                    });
//        }

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
            itemPage = searchPage.goToItemPage(itemNumber);
            itemPage.addToCart();
            cartPage = itemPage.goToCart();
            cartPage.compareItemTitle(itemNumber);
            cartPage.compareItemPrice(itemNumber);
        }

        @After
        public void tearDown() {
            driver.close();
        }
    }
}
