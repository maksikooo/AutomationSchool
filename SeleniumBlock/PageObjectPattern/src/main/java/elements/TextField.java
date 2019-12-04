package elements;

import org.openqa.selenium.WebElement;

public class TextField extends AbstractElement {

    protected TextField(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void clear(){
        getWrappedElement().clear();
    }
    public void sendKeys(String context){
        getWrappedElement().sendKeys(context);
    }
}
