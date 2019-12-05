package elements;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;

public class Label extends Element {
    protected Label(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Label should(Matcher matcher) {
        assertThat(getWrappedElement(), matcher);
        return this;
    }

    public String getText() {
        return getWrappedElement().getText();
    }

    public String getTextByAttribute() {
        return getWrappedElement().getAttribute("innerHTML");
    }
}
