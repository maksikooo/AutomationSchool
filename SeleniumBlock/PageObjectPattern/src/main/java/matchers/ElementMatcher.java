package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class ElementMatcher extends TypeSafeMatcher<WebElement> {

    @Override
    protected boolean matchesSafely(WebElement element) {
        for (int i = 0; i < 10; i++) {
            try {
                return element.isDisplayed();
            } catch (NoSuchElementException e) {
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

    public static Matcher<WebElement> elementIsDisplayed(){
        return new ElementMatcher();
    }
}

