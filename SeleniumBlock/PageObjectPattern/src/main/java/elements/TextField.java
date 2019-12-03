package elements;

import org.openqa.selenium.WebElement;

public class TextField extends AbstractElement {

    protected TextField(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void clear(){
        wrappedElement.clear();
    }
    public void sendKeys(String context){
        wrappedElement.sendKeys(context);
    }
}
