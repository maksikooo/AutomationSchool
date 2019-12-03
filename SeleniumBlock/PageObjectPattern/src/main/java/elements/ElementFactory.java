package elements;

import org.openqa.selenium.WebElement;

public interface ElementFactory {
    <E extends AbstractElement> E create(Class<E> elementClass, WebElement wrappedElement);
}
