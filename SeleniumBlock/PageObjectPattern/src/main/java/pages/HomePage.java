package pages;

import elements.Button;
import elements.ExtendedFieldDecorator;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private TextField searchField;

    @FindBy(xpath = "//form[@name='site-search']//input[@type='submit']")
    private Button searchSubmitButtonLocator;

    public void initElements(WebDriver driver){
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
    }

    public HomePage changeCategory(String category) {
        driver.findElement(By.xpath(String.format("//select[@id='searchDropdownBox']//option[text()='%s']",category))).click();
        return this;
    }

    public SearchPage searchFor(String searchString) {

        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchField.sendKeys(searchString);
        searchSubmitButtonLocator.click();
        return new SearchPage(driver);
    }
}
