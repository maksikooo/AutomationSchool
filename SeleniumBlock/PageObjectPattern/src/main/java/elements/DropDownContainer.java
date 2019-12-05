package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

public class DropDownContainer extends Element {
    @FindBy(xpath = "//ul[@role='listbox']/li")
    private List<DropDownValue> dropDownValues;

    protected DropDownContainer(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public DropDownContainer openDropDown() {
        getWrappedElement().click();
        return this;
    }

    private int getDropDownValuesCount() {
        return dropDownValues.size();
    }

    public DropDownContainer dropDownValueHasIndex(int index) {
        assertThat(getDropDownValuesCount(), greaterThanOrEqualTo(index));
        return this;
    }

    public void selectDropDownValue(int index) {
        dropDownValues.get(index).selectValue();
    }

}
