import java.util.ArrayList;


import org.openqa.selenium.*;

public class SearchPage {
    private WebDriver driver;
    private ArrayList<String> itemsTitles = new ArrayList<String>();
    private ArrayList<Integer> itemsIndexes = new ArrayList<Integer>();

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public int itemsCount() {
        return driver.findElements(By.xpath("//div[@data-index]")).size();
    }


    public String getItemTitle(int itemNumber) {
        return driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//a[@class='a-link-normal a-text-normal']", itemNumber))).getText();
    }

    public float getItemPrice(int itemNumber) {
        // потратил какое то количество времени на то что пофиксить .getText() возвращал Null,это из за того что span скрыт с помощью css? правильно я понял?
        return Float.parseFloat(driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//span[@class='a-offscreen']", itemNumber))).getAttribute("innerHTML").replaceAll("\\$", ""));
    }


    private int itemsCountOnPage() {
        return driver.findElements(By.xpath("//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS']")).size();
    }

    public ArrayList<String> getItemsTitle() {
        System.out.println(itemsCountOnPage());
        for (int i = 1; i <= itemsCountOnPage(); i++) {
            itemsTitles.add(driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//a[@class='a-link-normal a-text-normal']", i))).getText().toLowerCase());
        }
        return itemsTitles;
    }

    public String getTitle() {
        return driver.getTitle().toLowerCase();
    }

    public ItemPage goToItemPage(int i) {
        driver.findElement(By.xpath(String.format("(//span[@cel_widget_id='SEARCH_RESULTS-SEARCH_RESULTS'])[%d]//a[@class='a-link-normal']", i))).click();
        return new ItemPage(driver);
    }
}
