import java.util.ArrayList;


import org.openqa.selenium.*;
public class SearchPage {
    private WebDriver driver;
    private ArrayList<String > itemsTitles = new ArrayList<String>();
    private ArrayList<Integer>itemsIndexes = new ArrayList<Integer>();

    public SearchPage (WebDriver driver){
        this.driver = driver;
    }

    public int itemsCount(){
        return driver.findElements(By.xpath("//div[@data-index]")).size();
    }

    private ArrayList<Integer> findItemsIndexes(){ // костыль для обхода рандомных каруселей
        for (int i = 0;i < itemsCount();i++) {
            if (!driver.findElements(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + i + "']//a[@class='a-link-normal a-text-normal']")).isEmpty()) {
                itemsIndexes.add(i);
            }
        }
        return itemsIndexes;
    }

    public String getItemTitle(int itemNumber){
        itemNumber = findItemsIndexes().get(itemNumber);
        return driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + itemNumber + "']//a[@class='a-link-normal a-text-normal']")).getText();
    }

    public float getItemPrice(int itemNumber){
        itemNumber = findItemsIndexes().get(itemNumber);
        return Float.parseFloat(driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='"+itemNumber+"']//span[@class='a-price-whole']")).getText()) + Float.parseFloat("0." + driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='"+itemNumber+"']//span[@class='a-price-fraction']")).getText());
    }

    public ArrayList<String> getItemsTitle() {

        for (int i = 0;i < itemsCount();i++){
            if(!driver.findElements(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + i + "']//a[@class='a-link-normal a-text-normal']")).isEmpty()){
                itemsTitles.add(driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='" + i + "']//a[@class='a-link-normal a-text-normal']")).getText());
            }
        }
        return itemsTitles;
    }

    public String getTitle(){
        return driver.getTitle().toLowerCase();
    }

    public ItemPage goToItemPage(int i ) {
        driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[@data-index='"+i+"']//a[@class='a-link-normal a-text-normal']")).click();
        return new ItemPage(driver);
    }
}
