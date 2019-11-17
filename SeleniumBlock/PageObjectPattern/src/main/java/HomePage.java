import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void changeCategory(String category) {
        driver.findElement(By.xpath("//select[@id='searchDropdownBox']//option[text()='" + category + "']")).click();

    }

    public SearchPage searchFor(String searchString) {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchString);
        driver.findElement(By.xpath("//form[@name='site-search']/.//input[@type='submit']")).click();
        return new SearchPage(driver);
    }
}
