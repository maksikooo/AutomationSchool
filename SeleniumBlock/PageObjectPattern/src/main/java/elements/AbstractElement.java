package elements;

import org.openqa.selenium.WebElement;


public abstract class  AbstractElement {
    protected WebElement wrappedElement;

    protected AbstractElement(final WebElement wrappedElement){this.wrappedElement = wrappedElement;}

    public boolean isDisplayed(){return wrappedElement.isDisplayed();}

}
