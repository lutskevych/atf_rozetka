package framework.pageobjects.components.goodsnavigator;

import com.google.common.base.Function;
import framework.pageobjects.PageObject;
import framework.pageobjects.pages.goodspage.GoodsCategoryPage;
import framework.pageobjects.pages.goodspage.GoodsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GoodsCatalogNavigator extends PageObject {
    private WebElement mainGoodsCategoriesMenu;

    public GoodsCatalogNavigator openGoodsCatalog() {
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
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.tagName("header")))
                .build()
                .perform();
        return this;
    }

    public GoodsCategoryPage openCategory(GoodsCategories goodsCategory) {
        mainGoodsCategoriesMenu.findElement(By.id(goodsCategory.getCssId())).click();
        return new GoodsCategoryPage();
    }

    public GoodsPage openSubCategory(GoodsCategories goodsCategory, String subCategoryCssSelector) {
        System.out.println("Try to open subcategory"); //TODO remove it
        WebElement subCategory = getSubCategoriesMenu(goodsCategory);
        System.out.println("subMenu should be visible now"); //TODO remove it
        subCategory.findElement(By.cssSelector(subCategoryCssSelector)).click();
        return new GoodsPage();
    }

    private WebElement getSubCategoriesMenu(GoodsCategories goodsCategory) {
        System.out.println("Try to choose subcategory"); //TODO remove it
        new Actions(webDriver)
                .moveToElement(mainGoodsCategoriesMenu.findElement(By.id(goodsCategory.getCssId())))
                .build()
                .perform();
        return wait.until(
                (Function<WebDriver, WebElement>) driver ->
                        mainGoodsCategoriesMenu.findElement(By.cssSelector("li.f-menu-l-i.hover")));
    }
}
