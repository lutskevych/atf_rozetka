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
        Wait.untilPageLoadComplete(webDriver, initProperties.defaultWait());
        catalogGoodsBlock = webDriver.findElement(By.cssSelector("div[name='goods_list']"));
    }

    public GoodsPage selectNext32Items() {
        webDriver.findElement(By.cssSelector("div[name='more_goods']")).click();
        Wait.untilPageLoadComplete(webDriver);
        catalogGoodsBlock = webDriver.findElement(By.cssSelector("div[name='goods_list']"));
        return this;
    }

    public List<GoodsItem> getAllGoodsItemsFromPages(int pagesNumber) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<WebElement> sourceList = getGoodsItemElementsFromPages(pagesNumber);
        for (WebElement e : sourceList) {
            resultList.add(new GoodsItem(e));
        }
        return resultList;
    }

    public List<GoodsItem> getGoodsItemsFromPagesWithActionIcon(int pagesNumber, String priceActionIconName) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<GoodsItem> sourceList = getAllGoodsItemsFromPages(pagesNumber);
        for (GoodsItem e : sourceList) {
            if (e.getPriceActiveIcon().equals(priceActionIconName)) {
                resultList.add(e);
            }
        }
        return resultList;
    }

    private List<WebElement> getGoodsItemElementsFromPages(int pagesNumber) {
        for (int i = 2; i <= pagesNumber; i++) {
            selectNext32Items();
        }
        return catalogGoodsBlock.findElements(By.cssSelector("div.g-i-tile.g-i-tile-catalog:not([name='more_goods'])"));
    }
}
