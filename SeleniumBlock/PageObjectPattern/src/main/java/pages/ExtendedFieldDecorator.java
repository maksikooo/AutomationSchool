package pages;

import elements.AbstractElement;
import elements.DefaultElementFactory;
import elements.ElementFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private ElementFactory elementFactory = new DefaultElementFactory();

    public ExtendedFieldDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        ElementLocator locator = factory.createLocator(field);
        if (AbstractElement.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field);
        }
        if (List.class.isAssignableFrom(field.getType())){
            return createList(loader,locator);
        }
        return super.decorate(loader, field);
    }
    protected List<AbstractElement> createList(ClassLoader loader,ElementLocator locator){
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<AbstractElement> elements = (List<AbstractElement>) Proxy.newProxyInstance(loader,new Class[] {List.class},handler);
        return elements;
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        return elementFactory.create((Class<? extends AbstractElement>) field.getType(), wrappedElement);
    }

    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

}
