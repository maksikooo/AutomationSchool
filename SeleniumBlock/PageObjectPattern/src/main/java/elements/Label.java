package elements;

import org.openqa.selenium.WebElement;

public class Label extends Element {
    protected Label(WebElement wrappedElement) {
        super(wrappedElement);
    }
    public String getText(){
        return getWrappedElement().getText();
    }

    public String getTextByAttribute(){
        return getWrappedElement().getAttribute("innerHTML");
    }
}
