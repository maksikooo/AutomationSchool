package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class ElementMatcher extends TypeSafeMatcher<WebElement> {
    private int timeout = 5;

    public ElementMatcher(int timeout) {
        this.timeout = timeout;
    }

    public ElementMatcher() {
    }

    public static Matcher<WebElement> elementIsDisplayed() {
        return new ElementMatcher();
    }

    public static Matcher<WebElement> elementIsDisplayed(int timeout) {
        return new ElementMatcher(timeout);
    }

    @Override
    protected boolean matchesSafely(WebElement element) {
        for (int i = 0; i < timeout * 2; i++) {
            try {
                return element.isDisplayed();
            } catch (NoSuchElementException ignored) {
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Element is displayed");
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" element not displayed");
    }
}

