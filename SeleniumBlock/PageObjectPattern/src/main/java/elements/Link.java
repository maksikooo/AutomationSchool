package elements;

import org.openqa.selenium.WebElement;

public class Link extends AbstractElement {
    protected Link(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getText(){
        return getWrappedElement().getText();
    }
    public void click(){
        getWrappedElement().click();
    }
}
