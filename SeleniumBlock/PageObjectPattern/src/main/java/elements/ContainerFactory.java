package elements;

import org.openqa.selenium.WebElement;

public interface ContainerFactory {
    <E extends AbstractContainer> E create(Class<E> elementClass, WebElement wrappedElement);
}
