package framework.pageobjects.pages.goodspage;

import com.google.common.base.Function;
import framework.pageobjects.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class GoodsPage extends BasePage {

    private WebElement catalogGoodsBlock;

    public GoodsPage(){
        closePopupElementIfPresent();
    }

    public GoodsPage selectNext32Items() {
        logger.debug("selectNext32Items");
        closePopupElementIfPresent();
        logger.debug("Find next32Items button and try to click");
        wait.until(
                (Function<WebDriver, WebElement>) driver ->
                        webDriver.findElement(By.cssSelector("div[name='more_goods'] a"))).click();
        logger.debug("Wait while page updates");
        wait.until((WebDriver d) -> {
                    WebElement btn = webDriver.findElement(By.cssSelector("div[name='more_goods']"));
                    return btn.getAttribute("class").contains("run-animation");
                });
        logger.debug("Wait until next 32 items will be displayed");
        wait.until((WebDriver d) -> {
            WebElement btn = webDriver.findElement(By.cssSelector("div[name='more_goods']"));
            return !btn.getAttribute("class").contains("run-animation");
        });
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
        catalogGoodsBlock = webDriver.findElement(By.cssSelector("div[name='goods_list']"));
        List<WebElement> result = catalogGoodsBlock.findElements(By.cssSelector("div.g-i-tile.g-i-tile-catalog:not([name='more_goods'])"));
        logger.debug(format("catalogGoodsBlock contains: %s elements", result.size()));
        return result;
    }
}
