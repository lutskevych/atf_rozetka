package framework.pageobjects.pages.goodspage;

import framework.pageobjects.pages.BasePage;
import framework.utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static framework.properties.PropertyLoader.initProperties;

public class GoodsPage extends BasePage {
    private WebElement catalogGoodsBlock;

    public GoodsPage(){
        System.out.println("GoodsPage constructor"); //TODO remove it after debugging
        timer.startTimer();
        Wait.untilPageLoadComplete(webDriver, initProperties.defaultWait());
        timer.stopTimer().printElapsedTime("Wait for GoodsPage load complete");

        timer.startTimer();
        catalogGoodsBlock = webDriver.findElement(By.cssSelector("div[name='goods_list']"));
        timer.stopTimer().printElapsedTime("Find catalogGoodsBlock");
    }

    public GoodsPage selectNext32Items() {
        System.out.println("selectNext32Items"); //TODO remove it after debugging
        timer.startTimer();
        webDriver.findElement(By.cssSelector("div[name='more_goods']")).click();
        timer.stopTimer().printElapsedTime("Find and click on next32Items button");

        timer.startTimer();
        Wait.untilPageLoadComplete(webDriver);
        timer.stopTimer().printElapsedTime("Wait for goods page load complete");

        timer.startTimer();
        catalogGoodsBlock = webDriver.findElement(By.cssSelector("div[name='goods_list']"));
        timer.stopTimer().printElapsedTime("Find catalogGoodsBlock");
        return this;
    }

    public List<GoodsItem> getAllGoodsItemsFromPages(int pagesNumber) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<WebElement> sourceList = getGoodsItemElementsFromPages(pagesNumber);
        timer.startTimer();
        for (WebElement e : sourceList) {
            resultList.add(new GoodsItem(e));
        }
        timer.stopTimer().printElapsedTime("Creating List<GoodsItem>");
        return resultList;
    }

    public List<GoodsItem> getGoodsItemsFromPagesWithActionIcon(int pagesNumber, String priceActionIconName) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<GoodsItem> sourceList = getAllGoodsItemsFromPages(pagesNumber);
        timer.startTimer();
        for (GoodsItem e : sourceList) {
            if (e.getPriceActiveIcon().equals(priceActionIconName)) {
                resultList.add(e);
            }
        }
        timer.stopTimer().printElapsedTime("Create result List from source List");
        return resultList;
    }

    private List<WebElement> getGoodsItemElementsFromPages(int pagesNumber) {
        for (int i = 2; i <= pagesNumber; i++) {
            selectNext32Items();
        }
        timer.startTimer();
        List<WebElement> result = catalogGoodsBlock.findElements(By.cssSelector("div.g-i-tile.g-i-tile-catalog:not([name='more_goods'])"));
        timer.stopTimer().printElapsedTime("Find all goodsItems on the page");
        return result;
    }
}
