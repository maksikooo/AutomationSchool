package elements;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private ElementFactory elementFactory = new ElementFactory();

    public ExtendedFieldDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        if (Element.class.isAssignableFrom(field.getType())) {
            final WebElement wrappedElement = proxyForLocator(loader, factory.createLocator(field));
            final Element element = elementFactory.create((Class<Element>) field.getType(), wrappedElement);
            PageFactory.initElements(new ExtendedFieldDecorator(wrappedElement), element);
            element.setWrappedElement(wrappedElement);
            return element;
        }
        if (List.class.isAssignableFrom(field.getType())) {
            InvocationHandler handler = new LocatingCustomElementListHandler(factory.createLocator(field), field);
            return Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        }
        return super.decorate(loader, field);
    }

    class LocatingCustomElementListHandler extends ElementFactory implements InvocationHandler {
        private final ElementLocator locator;
        private final Field field;

        public LocatingCustomElementListHandler(ElementLocator locator, final Field field) {
            this.field = field;
            this.locator = locator;
        }

        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
            List<WebElement> elements = locator.findElements();
            List<Element> customs = new ArrayList<>();
            for (WebElement element : elements) {
                Element container = create(((Class<? extends Element>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]), element);
                PageFactory.initElements(new ExtendedFieldDecorator(element), container);
                container.setWrappedElement(element);
                customs.add(container);
            }
            return method.invoke(customs, objects);

        }
    }

}
