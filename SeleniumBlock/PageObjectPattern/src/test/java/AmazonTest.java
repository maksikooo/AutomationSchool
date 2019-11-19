import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
                    {"puzzle", 1},{"sock",1},{"robe",1},
                    {"puzzle", 2},{"sock",2},{"robe",2},
                    {"puzzle", 3},{"sock",3},{"robe",3}
                    });
        }

//        @Parameterized.Parameters()
//        public static Iterable<Object> dataForTest() {
//            return Arrays.asList(new Object[]{"puzzle", "sock", "robe"});
//        }

        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver","src/tools/chromedriver.exe");
            driver = new ChromeDriver();
            //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
            driver.get("https://www.amazon.com/");
        }
//        @Test
//        public void fuckingTest(){
//            driver.get("https://www.amazon.com/s?k=puzzle&i=baby-products-intl-ship&ref=nb_sb_noss_1");
//            ArrayList<WebElement> items = new ArrayList<>();
//            driver.findElements(By.xpath("//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")).stream().map(x-> items.add(x)).collect(Collectors.toList());
//            System.out.println(items.size());
//            ArrayList<ProductItem> itemArrayList = new ArrayList<>();
//            driver.findElements(By.xpath("//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")).stream().map(x-> itemArrayList.add(new ProductItem(x))).collect(Collectors.toList());
//            itemArrayList.size();
//            itemArrayList.get(1).getItemWebElement().getText();
//        }


        @Test
        public void AmazonItemDetailsTest() {
            HomePage home = new HomePage(driver);
            home.changeCategory(category);
            searchPage = home.searchFor(searchString);
            productItems = searchPage.getProductItems();
            searchPage.pageTitleContainsSearchRequest(searchString);
            searchPage.itemsTitleHasSearchRequest(searchString);
            itemPage = searchPage.goToItemPage(productItems.get(0));
            itemPage.addToCart();
            cartPage = itemPage.goToCart();
            cartPage.compareItemTitle(productItems.get(0));
            cartPage.compareItemPrice(productItems.get(0));
        }

        @After
        public void tearDown() {
            driver.close();
        }
    }
}
