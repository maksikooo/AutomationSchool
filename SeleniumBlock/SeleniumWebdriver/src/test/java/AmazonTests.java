import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.concurrent.TimeUnit;



public class AmazonTests {

    @RunWith(Parameterized.class)
    public static class ParameterizeTest {
        private ArrayList<Integer> itemIndexes = new ArrayList<Integer>();
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
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(2,TimeUnit.SECONDS);
        }

        @Test
        public void AmazonUI() {
            int searchResultItemsCount;
            float itemPrice, subTotalPrice;
            String itemTitle, itemTitleFromCart;
            driver.get("https://www.amazon.com/");
            driver.findElement(By.xpath("//select[@id='searchDropdownBox']//option[text()='Baby']")).click();
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchString);
            driver.findElement(By.xpath("//form[@name='site-search']/.//input[@type='submit']")).click();
            Assert.assertTrue("Title not match",driver.getTitle().toLowerCase().contains(searchString));
            searchResultItemsCount = driver.findElements(By.xpath("//div[@data-index]")).size();
            for (int i = 0;i < searchResultItemsCount;i++){
                if(!driver.findElements(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + i + "']//a[@class='a-link-normal a-text-normal']")).isEmpty()){
                    itemTitle = driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + i + "']//a[@class='a-link-normal a-text-normal']")).getText();
                    itemIndexes.add(i);  //костыль для обхода рандомных каруселей
                    try {
                        Assert.assertTrue(itemTitle.toLowerCase().contains(searchString));
                    }catch (AssertionError a){
                        System.out.println("Item title not contains "+searchString+" : "+itemTitle);
                    }
                }
            }
            itemTitle = driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + itemIndexes.get(0) + "']//a[@class='a-link-normal a-text-normal']")).getText();
            itemPrice = Float.parseFloat(driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='"+itemIndexes.get(0)+"']//span[@class='a-price-whole']")).getText()) + Float.parseFloat("0." + driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='"+itemIndexes.get(0)+"']//span[@class='a-price-fraction']")).getText()); //стоит ли делить такие длинные строки на 2 для читабельности
            driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='"+itemIndexes.get(0)+"']//a[@class='a-link-normal a-text-normal']")).click();
            driver.findElement(By.id("add-to-cart-button")).click();
            if(!driver.findElements(By.id("nav-cart")).isEmpty()) {
                driver.findElement(By.id("nav-cart")).click();
            }else
                driver.findElement(By.id("attach-sidesheet-view-cart-button-announce")).click();
            Assert.assertTrue("Item not added to cart(options not selected)",!driver.findElements(By.id("sc-subtotal-amount-activecart")).isEmpty());
            subTotalPrice = Float.parseFloat(driver.findElement(By.id("sc-subtotal-amount-activecart")).getText().substring(1));
            Assert.assertEquals(itemPrice, subTotalPrice,0f);
            itemTitleFromCart = driver.findElement(By.className("sc-product-title")).getText();
            Assert.assertEquals("Item title not match in cart", itemTitle,itemTitleFromCart);
        }

        @After
        public void DropDown() {
            driver.close();
        }
    }
}

