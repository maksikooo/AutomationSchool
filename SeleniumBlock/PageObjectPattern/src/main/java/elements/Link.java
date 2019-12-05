package elements;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;

public class Link extends Element {
    protected Link(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public Link should(Matcher matcher) {
        assertThat(getWrappedElement(), matcher);
        return this;
    }

    public String getText() {
        return getWrappedElement().getText();
    }

    public void click() {
        getWrappedElement().click();
    }
}
