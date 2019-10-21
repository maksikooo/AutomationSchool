import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AmazonTests {
    @RunWith(Parameterized.class)
    public static class ParameterizeTest {
        private String searchString;
        private WebDriver driver;

        public ParameterizeTest(String searchString) {
            this.searchString = searchString;
        }

        @Parameterized.Parameters()
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[]{
                    "puzzle", "sock", "robe"});
        }

        @Before
        public void SetUp() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test
        public void AmazonUI() {
            int searchResultItemsCount;
            float itemPrice, subTotalPrice;
//        int searchResultCorrectItems = 0;
            String itemTitle, itemTitleFromCart;
            driver.get("https://www.amazon.com/");
            WebElement element = driver.findElement(By.id("searchDropdownBox")).findElement(By.xpath(".//option[text()='Baby']"));
            element.click();
            element = driver.findElement(By.id("twotabsearchtextbox"));
            element.sendKeys(searchString);
            element = driver.findElement(By.xpath("//form[@name='site-search']/.//input[@type='submit']"));
            element.click();
            assertTrue("Title not match", driver.getTitle().toLowerCase().contains(searchString));
//        if(driver.getTitle().contains("puzzle"))
//            System.out.println("Title have puzzle");
//        else
//            System.out.println("Title doesn't have puzzle");
            searchResultItemsCount = driver.findElements(By.xpath("//div[@data-index]")).size();
            for (int i = 0; i < searchResultItemsCount; i++) {
                itemTitle = driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + i + "']")).findElement(By.className("a-text-normal")).getText();
                assertTrue("Item title not match :" + i, itemTitle.toLowerCase().contains(searchString));
//            if(itemTitle.toLowerCase().contains("puzzle"))
//                searchResultCorrectItems++;
            }
            itemTitle = driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='0']")).findElement(By.className("a-text-normal")).getText();
            itemPrice = Float.parseFloat(driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='0']")).findElement(By.className("a-price-whole")).getText()) + Float.parseFloat("0." + driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='0']")).findElement(By.className("a-price-fraction")).getText());
//        System.out.println("Item: "+itemTitle);
//        System.out.println("Price: "+itemPrice);
            element = driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='0']")).findElement(By.className("a-link-normal"));
            element.click();
            element = driver.findElement(By.id("add-to-cart-button"));
            element.click();
            element = driver.findElement(By.id("nav-cart-count"));
            element.click();
            subTotalPrice = Float.parseFloat(driver.findElement(By.id("sc-subtotal-amount-activecart")).getText().substring(1));
            assertTrue("Item price not match", itemPrice == subTotalPrice);
//        if(itemPrice==Float.parseFloat(subTotalPrice.substring(1))){
//            System.out.println("Price match");
//        }else
//            System.out.println("Price another");
            itemTitleFromCart = driver.findElement(By.className("sc-product-title")).getText();
            assertTrue("Item title not match in cart", itemTitle == itemTitleFromCart);
//        if (itemTitle.equals(itemTitleFromCart)){
//            System.out.println("Title match");
//        }else
//            System.out.println("Title another");
        }

        @After
        public void DropDown() {
            driver.close();
        }
    }
}

