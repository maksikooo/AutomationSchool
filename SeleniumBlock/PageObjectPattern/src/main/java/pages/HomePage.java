package pages;

import elements.Button;
import elements.ExtendedFieldDecorator;
import elements.TextInputField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static matchers.ElementMatcher.elementIsDisplayed;
import static utils.WaitUtils.threadSleep;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private TextInputField searchField;

    @FindBy(xpath = "//form[@name='site-search']//input[@type='submit']")
    private Button searchSubmitButtonLocator;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
    }

    private void initElements(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage changeCategory(String category) {
        driver.findElement(By.xpath(String.format("//select[@id='searchDropdownBox']//option[text()='%s']", category))).click();
        return this;
    }

    public SearchPage searchFor(String searchString) {
        threadSleep(1000);
        searchField.sendKeys(searchString);
        searchSubmitButtonLocator.should(elementIsDisplayed(5)).click();
        return new SearchPage(driver);
    }
}
