import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yecht.Data;

import  java.lang.AssertionError.*;

public class UiForMavenTest {

    @Test
    public void testAmazon() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");

        WebElement searchField = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
        WebElement goButton = driver.findElement(By.xpath("//input[@value='Go']"));

        searchField.sendKeys("puzzle");
        goButton.click();

        assert (driver.getTitle().contains("puzzle"));
        driver.quit();
    }

    @Test
    public void testApple() {
        WebDriver driver = new ChromeDriver();
       // driver.manage().window().maximize();
        driver.get("https://pn.com.ua");

        WebDriverWait wait = new WebDriverWait(driver,5);

        WebElement cellPhone = driver.findElement(By.xpath("//*[@id='column-center']/section/div[2]/ul/li[1]/a"));
        cellPhone.click();
        WebElement appleFilter = driver.findElement(By.xpath("//*[@id='producers-filter-block']//a[text()='Apple']"));
        appleFilter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Очистить')]"))); // добавлена для успешного завершения теста
        WebElement searchResultTitle = driver.findElement(By.xpath("//*[@id='column-center']/section/div[3]//div[@class='catalog-block-head']/a"));
        assert (driver.getTitle().contains("Apple"));
        assert (searchResultTitle.getText().contains("Apple iPhone"));
        driver.quit();
    }
}
