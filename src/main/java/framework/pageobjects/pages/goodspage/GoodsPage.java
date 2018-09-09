package framework.pageobjects.pages.goodspage;

import com.google.common.base.Function;
import framework.pageobjects.pages.BasePage;
import framework.utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        timer.printElapsedTime("Wait for GoodsPage load complete");

        closePopupElementIfPresent();
    }

    public GoodsPage selectNext32Items() {
        System.out.println("selectNext32Items"); //TODO remove it after debugging
        closePopupElementIfPresent();
        timer.startTimer();
        WebElement nxt32btn = wait.until(
                (Function<WebDriver, WebElement>) driver -> webDriver.findElement(By.cssSelector("div[name='more_goods'] a")));
        System.out.println("try to click button");
        nxt32btn.click();
        timer.printElapsedTime("Wait for next32Items button");
        timer.startTimer();
        wait.until((WebDriver d) -> {
                    WebElement btn = webDriver.findElement(By.cssSelector("div[name='more_goods']"));
                    return btn.getAttribute("class").contains("run-animation");
                });
        timer.printElapsedTime("Wait for next32Items button has run-animation");
        timer.startTimer();
        wait.until((WebDriver d) -> {
            WebElement btn = webDriver.findElement(By.cssSelector("div[name='more_goods']"));
            return !btn.getAttribute("class").contains("run-animation");
        });
        timer.printElapsedTime("Wait for next32Items button doesn't have run-animation");
        return this;
    }

    public List<GoodsItem> getAllGoodsItemsFromPages(int pagesNumber) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<WebElement> sourceList = getGoodsItemElementsFromPages(pagesNumber);
        timer.startTimer();
        for (WebElement e : sourceList) {
            resultList.add(new GoodsItem(e));
        }
        timer.printElapsedTime("Creating List<GoodsItem>");
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
        timer.printElapsedTime("Create result List from source List");
        return resultList;
    }

    private List<WebElement> getGoodsItemElementsFromPages(int pagesNumber) {
        for (int i = 2; i <= pagesNumber; i++) {
            System.out.println("Going to select Next32Btn, pagesNumber: " + pagesNumber);
            selectNext32Items();
        }
        System.out.println("After cycle of pressing Next32Btn, pagesNumber: " + pagesNumber);

        timer.startTimer();
        catalogGoodsBlock = webDriver.findElement(By.cssSelector("div[name='goods_list']"));
        timer.printElapsedTime("Find catalogGoodsBlock");

        timer.startTimer();
        List<WebElement> result = catalogGoodsBlock.findElements(By.cssSelector("div.g-i-tile.g-i-tile-catalog:not([name='more_goods'])"));
        System.out.println("GOODS_PAGE catalogGoodsBlock contains: " + result.size() + " elements");
        timer.printElapsedTime("Find all goodsItems on the page");
        return result;
    }
}
