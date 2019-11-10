import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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


        public ParameterizeTest(String searchString){
            this.searchString = searchString;
        }

        @Parameterized.Parameters()
        public static Iterable<Object> dataForTest(){
            return Arrays.asList(new Object[]{
                "puzzle","sock","robe"});
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
            Assert.assertTrue("Title not match", searchPage.getTitle().toLowerCase().contains(searchString));
            itemsTitle = searchPage.getItemsTitle();
            for (String itemTitle : itemsTitle) {
                try {
                    Assert.assertTrue(itemTitle.toLowerCase().contains(searchString));
                } catch (AssertionError e) {
                    System.out.println("Item title not contains " + searchString + ":" + itemTitle);
                }
            }
            itemTitle = searchPage.getItemTitle(0);
            itemPrice = searchPage.getItemPrice(0);
            itemPage = searchPage.goToItemPage(0);
            itemPage.addToCart();
            cartPage = itemPage.goToCart();
            Assert.assertTrue("Item not added to cart(options not selected)", itemPage.itemAddedCheck());
            Assert.assertEquals(itemPrice, cartPage.getItemPrice(), 0f);
            Assert.assertEquals("Item title from cart not match search page item title", itemTitle, cartPage.getItemTitle());
        }


        @After
        public void tearDown() {
            driver.close();

        }
    }
}
