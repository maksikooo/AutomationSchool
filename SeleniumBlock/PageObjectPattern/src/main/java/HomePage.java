import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void changeCategory(String category) {
        driver.findElement(By.xpath(String.format("//select[@id='searchDropdownBox']//option[text()='%s']",category))).click();

    }

    public SearchPage searchFor(String searchString) {
        //добавил задержку т.к. бывают случаи когда во время работы sendkeys() фокус менялся и ввод обрывался
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchString);
        driver.findElement(By.xpath("//form[@name='site-search']//input[@type='submit']")).click();
        return new SearchPage(driver);
    }
}
