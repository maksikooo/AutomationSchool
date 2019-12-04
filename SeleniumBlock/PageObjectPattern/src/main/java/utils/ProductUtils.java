package utils;

import org.openqa.selenium.WebElement;

public  class ProductUtils  {
    public static float parsePrice(WebElement element){
        return Float.parseFloat(element.getAttribute("innerHTML").replaceAll("\\$", ""));
    }
    public static float parsePrice(String string){
        return Float.parseFloat(string.replaceAll("\\$", ""));
    }
}
