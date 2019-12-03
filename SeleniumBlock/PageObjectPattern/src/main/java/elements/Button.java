package elements;

import org.openqa.selenium.WebElement;

public class Button extends AbstractElement{
    public Button(WebElement wrappedElement) {
        super(wrappedElement);
    }


    public void sendKeys(String s) {
        wrappedElement.sendKeys(s);
    }

    public void click() {
        wrappedElement.click();
    }
}
