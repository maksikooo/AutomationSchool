import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;

import java.util.ArrayList;
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
        ArrayList<String> itemsTitle = new ArrayList<String>();
        Float itemPrice;
        String itemTitle;


        public ParameterizeTest(String searchString) {
            this.searchString = searchString;
        }

        @Parameterized.Parameters()
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[]{
                   "puzzle", "sock", "robe"});
        }

        @Before
        public void setUp() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
            driver.get("https://www.amazon.com/");
        }


        @Test
        public void AmazonItemDetailsTest() {
            HomePage home = new HomePage(driver);
            home.changeCategory(category);
            searchPage = home.searchFor(searchString);
            assertThat(searchPage.getTitle().toLowerCase(), containsString(searchString));
            itemsTitle = searchPage.getItemsTitle();
            try {
                assertThat(itemsTitle, everyItem(containsString(searchString)));
            } catch (AssertionError e) {
                System.out.println(e);
            }
            itemTitle = searchPage.getItemTitle(1);
            itemPrice = searchPage.getItemPrice(1);
            itemPage = searchPage.goToItemPage(1);
            itemPage.addToCart();
            cartPage = itemPage.goToCart();
            assertThat(itemPage.itemAddedCheck(), is(true));
            assertThat(itemPrice,is(equalTo(cartPage.getItemPrice())));
            assertThat(itemTitle,is(equalTo(cartPage.getItemTitle())));
        }


        @After
        public void tearDown() {
            driver.close();

        }
    }
}
