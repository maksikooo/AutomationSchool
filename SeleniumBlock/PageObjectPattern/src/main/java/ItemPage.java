import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class ItemPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@role='button']/span")
    private WebElement sizeDropDownLocator;
    @FindBy(xpath = "//ul[@role='listbox']/li[2]")
    private WebElement dropDownSecondValue;
    @FindBy (id = "add-to-cart-button")
    private WebElement addToCartButtonLocator;
    @FindBy (id = "nav-cart")
    private WebElement cartLinkLocator;
    @FindBy (id = "attach-sidesheet-view-cart-button")
    private WebElement sideCartLinkLocator;

    private void init(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public ItemPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        init(driver);
    }

    public void changeSize() {
        sizeDropDownLocator.click();
        dropDownSecondValue.click();
        WaitUtils.waitUntilElementChangeAttribute(driver,addToCartButtonLocator,"cursor","pointer");
        addToCart();
    }

    public ItemPage addToCart() {
        if (canBeAddedToCart()) {
            addToCartButtonLocator.click();
        } else changeSize();
        return this;
    }

    public boolean canBeAddedToCart() {
        WaitUtils.waitForElementShow(driver,addToCartButtonLocator,5);
        return !addToCartButtonLocator.getCssValue("cursor").equals("not-allowed");
    }

    public CartPage goToCart() {
        WaitUtils.waitForElementShow(driver,sideCartLinkLocator,4);
        try{
           sideCartLinkLocator.click();
        }catch (NoSuchElementException a){
            cartLinkLocator.click();
        }

        return new CartPage(driver);
    }
}
