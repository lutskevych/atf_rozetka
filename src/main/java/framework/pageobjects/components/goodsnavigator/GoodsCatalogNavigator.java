package framework.pageobjects.components.goodsnavigator;

import com.google.common.base.Function;
import framework.pageobjects.PageObject;
import framework.pageobjects.pages.goodspage.GoodsCategoryPage;
import framework.pageobjects.pages.goodspage.GoodsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.lang.String.format;

public class GoodsCatalogNavigator extends PageObject {
    private WebElement mainGoodsCategoriesMenu;

    public GoodsCatalogNavigator openGoodsCatalog() {
        logger.debug("openGoodsCatalog");
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.id("fat_menu_btn")))
                .build()
                .perform();
        wait.until((WebDriver d) -> {
            mainGoodsCategoriesMenu = webDriver.findElement(By.id("m-main-ul"));
            return !(mainGoodsCategoriesMenu.getAttribute("class").contains("hidden"));
        });
        return this;
    }

    public GoodsCatalogNavigator closeGoodsCatalog() {
        logger.debug("closeGoodsCatalog");
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.tagName("header")))
                .build()
                .perform();
        return this;
    }

    public GoodsCategoryPage openCategory(GoodsCategories goodsCategory) {
        logger.debug("openCategory");
        mainGoodsCategoriesMenu.findElement(By.id(goodsCategory.getCssId())).click();
        return new GoodsCategoryPage();
    }

    public GoodsPage openSubCategory(GoodsCategories goodsCategory, String subCategoryCssSelector) {
        logger.debug(format("openSubCategory {%s} for category %s", subCategoryCssSelector, goodsCategory));
        WebElement subCategory = getSubCategoriesMenu(goodsCategory);
        subCategory.findElement(By.cssSelector(subCategoryCssSelector)).click();
        return new GoodsPage();
    }

    private WebElement getSubCategoriesMenu(GoodsCategories goodsCategory) {
        logger.debug(format("getSubCategoriesMenu of %s", goodsCategory));
        new Actions(webDriver)
                .moveToElement(mainGoodsCategoriesMenu.findElement(By.id(goodsCategory.getCssId())))
                .build()
                .perform();
        WebElement subMenu = wait.until(
                (Function<WebDriver, WebElement>) driver ->
                        mainGoodsCategoriesMenu.findElement(By.cssSelector("li.f-menu-l-i.hover")));
        return subMenu;
    }
}
