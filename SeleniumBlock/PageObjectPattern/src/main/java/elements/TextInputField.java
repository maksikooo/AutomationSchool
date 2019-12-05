package elements;

import org.openqa.selenium.WebElement;

public class TextInputField extends Element {

    protected TextInputField(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void clear() {
        getWrappedElement().clear();
    }

    public void sendKeys(String context) {
        getWrappedElement().sendKeys(context);
    }
}
