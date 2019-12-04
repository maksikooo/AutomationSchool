package elements;

import elements.AbstractElement;
import elements.DefaultElementFactory;
import elements.ElementFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private ElementFactory elementFactory = new DefaultElementFactory();
    private ContainerFactory containerFactory = new DefaultContainerFactory();

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
        if (AbstractContainer.class.isAssignableFrom(field.getType())) {
            return decorateContainer(loader, field);
        }
        return super.decorate(loader, field);
    }

    protected List createList(final ClassLoader loader, final Field field) {
        if (AbstractContainer.class.isAssignableFrom((Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0])) {
            InvocationHandler handler = new LocatingCustomContainerListHandler(factory.createLocator(field), field);
            return (List<AbstractContainer>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        } else {
            InvocationHandler handler = new LocatingCustomElementListHandler(factory.createLocator(field), field);
            return (List<AbstractElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        }
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        final AbstractElement element = elementFactory.create((Class<AbstractElement>) field.getType(), wrappedElement);
        PageFactory.initElements(new ExtendedFieldDecorator(wrappedElement), element);
        return element;
    }

    private Object decorateContainer(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        final AbstractContainer container = containerFactory.create((Class<AbstractContainer>) field.getType(), wrappedElement);
        PageFactory.initElements(new ExtendedFieldDecorator(wrappedElement), container);
        return container;
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

    class LocatingCustomContainerListHandler extends DefaultContainerFactory implements InvocationHandler {
        private final ElementLocator locator;
        private final Field field;

        public LocatingCustomContainerListHandler(ElementLocator locator, final Field field) {
            this.field = field;
            this.locator = locator;
        }

        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
            List<WebElement> elements = locator.findElements();
            List<AbstractContainer> customs = new ArrayList<>();

            for (WebElement element : elements) {
                final AbstractContainer container = create(((Class<? extends AbstractContainer>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]), element);
                PageFactory.initElements(new ExtendedFieldDecorator(element), container);
                container.setWrappedElement(element);
                customs.add(container);
            }
            return method.invoke(customs, objects);

        }
    }

}
