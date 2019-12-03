package elements;

import elements.AbstractElement;
import elements.DefaultElementFactory;
import elements.ElementFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private ElementFactory elementFactory = new DefaultElementFactory();

    public ExtendedFieldDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        if (AbstractElement.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field);
        }
        if (List.class.isAssignableFrom(field.getType())) {
            return createList(loader, field);
        }
        return super.decorate(loader, field);
    }

    protected List<AbstractElement> createList(final ClassLoader loader, final Field field) {
        InvocationHandler handler = new LocatingCustomElementListHandler(factory.createLocator(field), field);
        List<AbstractElement> elements = (List<AbstractElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return elements;
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        return elementFactory.create((Class<AbstractElement>) field.getType(), wrappedElement);
    }

    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

    class LocatingCustomElementListHandler extends DefaultElementFactory implements InvocationHandler {
        private final ElementLocator locator;
        private final Field field;

        public LocatingCustomElementListHandler(ElementLocator locator, final Field field) {
            this.field = field;
            this.locator = locator;
        }

        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
            List<WebElement> elements = locator.findElements();
            List<AbstractElement> customs = new ArrayList<>();

            for (WebElement element : elements) {
                customs.add(create(((Class<? extends AbstractElement>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]), element));
            }
            return method.invoke(customs, objects);

        }
    }

}
