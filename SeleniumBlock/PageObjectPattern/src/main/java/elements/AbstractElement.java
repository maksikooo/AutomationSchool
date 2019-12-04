package elements;

import org.openqa.selenium.WebElement;


public abstract class  AbstractElement {
    private WebElement wrappedElement;

    protected AbstractElement(final WebElement wrappedElement){this.wrappedElement = wrappedElement;}

    public boolean isDisplayed(){return wrappedElement.isDisplayed();}

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public void setWrappedElement(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }
}
