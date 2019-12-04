package elements;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public class DefaultContainerFactory implements ContainerFactory{
    @Override
    public  <E extends AbstractContainer> E create(final Class<E> elementClass, final WebElement wrappedElement) {
        try {
            E container = elementClass
                    .getDeclaredConstructor(WebElement.class)
                    .newInstance(wrappedElement);
            return container;
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
