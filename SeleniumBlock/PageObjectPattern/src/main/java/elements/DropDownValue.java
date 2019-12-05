package elements;

import org.openqa.selenium.WebElement;

public class DropDownValue extends Element {
    protected DropDownValue(WebElement wrappedElement) {
        super(wrappedElement);
    }

    void selectValue() {
        getWrappedElement().click();
    }

    public void dropDownValueAvailable() {
        //if needed ,realize
    }

    public String getDropDownValueText() {
        return getWrappedElement().getText();
    }
}
