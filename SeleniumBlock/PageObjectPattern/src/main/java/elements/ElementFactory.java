package elements;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public class ElementFactory {

    public <E extends Element> E create(final Class<E> elementClass, final WebElement wrappedElement) {
        try {
            return elementClass.getDeclaredConstructor(WebElement.class).newInstance(wrappedElement);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

