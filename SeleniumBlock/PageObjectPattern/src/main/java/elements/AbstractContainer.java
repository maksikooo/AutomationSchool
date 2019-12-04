package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AbstractContainer {
    private WebElement wrappedElement;
    public AbstractContainer(final WebElement wrappedElement){
        this.wrappedElement = wrappedElement;
    }

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public void setWrappedElement(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }
}
