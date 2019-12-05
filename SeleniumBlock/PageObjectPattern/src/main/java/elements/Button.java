package elements;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;


public class Button extends Element {
    public Button(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Button should(Matcher matcher) {
        assertThat(getWrappedElement(), matcher);
        return this;
    }

    public void click() {
        getWrappedElement().click();
    }
}
