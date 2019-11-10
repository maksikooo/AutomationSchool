import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
    private WebDriver driver;
    private String category;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void changeCategory(String category) {
    this.category = category;
    driver.findElement(By.xpath("//select[@id='searchDropdownBox']//option[text()='"+category+"']")).click();

    }
    public SearchPage searchFor(String searchString){
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchString);
        driver.findElement(By.xpath("//form[@name='site-search']/.//input[@type='submit']")).click();
        return new SearchPage(driver);
    }
}
