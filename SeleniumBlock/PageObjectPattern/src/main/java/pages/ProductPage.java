package pages;

import elements.Button;
import elements.DropDownContainer;
import elements.ExtendedFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;


public class ProductPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@id='dropdown_selected_size_name']") //span[@role='button']/span
    private DropDownContainer sizeDropDownContainer;
    @FindBy(id = "add-to-cart-button")
    private Button addToCartButtonLocator;
    @FindBy(id = "nav-cart")
    private Button cartLinkLocator;
    @FindBy(id = "attach-sidesheet-view-cart-button")
    private Button sideCartLinkLocator;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver);
    }

    private void initElements(WebDriver driver) {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public void changeSize(int index) {
        sizeDropDownContainer.openDropDown().dropDownValueHasIndex(index).selectDropDownValue(index);
        WaitUtils.waitUntilElementChangeAttribute(driver, addToCartButtonLocator.getWrappedElement(), "cursor", "pointer");
        addToCart();
    }

    public ProductPage addToCart() {
        if (canBeAddedToCart()) {
            addToCartButtonLocator.click();
        } else changeSize(2);
        return this;
    }

    public boolean canBeAddedToCart() {
        WaitUtils.waitForElementShow(driver, addToCartButtonLocator.getWrappedElement(), 5);
        return !addToCartButtonLocator.getWrappedElement().getCssValue("cursor").equals("not-allowed");
    }

    public CartPage goToCart() {
        WaitUtils.waitForElementShow(driver, sideCartLinkLocator.getWrappedElement(), 4);
        try {
            sideCartLinkLocator.click();
        } catch (NoSuchElementException a) {
            cartLinkLocator.click();
        }

        return new CartPage(driver);
    }
}
