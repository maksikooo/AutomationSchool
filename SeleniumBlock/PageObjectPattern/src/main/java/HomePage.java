import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchFiled;

    @FindBy(xpath = "//form[@name='site-search']//input[@type='submit']")
    private WebElement searchButton;

    public void initElements(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
    }

    public void changeCategory(String category) {
        driver.findElement(By.xpath(String.format("//select[@id='searchDropdownBox']//option[text()='%s']",category))).click();

    }

    public SearchPage searchFor(String searchString) {

        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchFiled.sendKeys(searchString);
        searchButton.click();
        return new SearchPage(driver);
    }
}
