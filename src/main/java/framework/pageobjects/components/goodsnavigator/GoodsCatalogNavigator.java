package framework.pageobjects.components.goodsnavigator;

import framework.managers.WebDriverManager;
import framework.pageobjects.PageObject;
import framework.pageobjects.pages.goodspage.GoodsCategoryPage;
import framework.pageobjects.pages.goodspage.GoodsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GoodsCatalogNavigator extends PageObject {

    public GoodsCatalogNavigator openGoodsCatalog() {
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.id("fat_menu_btn")))
                .build()
                .perform();
        wait.until((WebDriver d) -> {
            WebElement goodsCategories = webDriver.findElement(By.id("m-main-ul"));
            return !(goodsCategories.getAttribute("class").contains("hidden"));
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

    public enum GoodsCatalog {
        NOTEBOOKS_AND_COMPUTERS("2416"),
        SMARTPHONES_TV_AND_ELECTRONICS("3361"),
        HOUSEHOLD_APPLIANCES("4306"),
        GOODS_FOR_HOME("5300"),
        TOOLS_AND_CAR_GOODS("6700"),
        PLUMBING_AND_REPAIR("7806"),
        COUNTRY_HOUSE_AND_GARDEN("8261"),
        SPORTS_AND_HOBBIES("9017"),
        CLOTHING_SHOES_AND_JEWELRY("10515"),
        BEAUTY_AND_HEALTH("12258"),
        CHILDREN_GOODS("13224"),
        STATIONARY_AND_BOOKS("14127"),
        ALCOHOLIC_BEVERAGES_AND_PRODUCTS("14939"),
        GOODS_FOR_BUSINESS("15422"),
        AIR_TICKETS_SERVICES_AND_OTHER("15954");

        private String locator;
        private WebElement categoryItem;
        private WebDriver driver;

        GoodsCatalog(String elementLocator) {
            driver = WebDriverManager.getInstance().getDriver();
            locator = elementLocator;
            categoryItem = driver.findElement(By.id(locator));
        }

        public GoodsPage openFromSubCatalog(By by) {
            driver.findElement(by).click();
            return new GoodsPage();
        }

        public GoodsCategoryPage select() {
            categoryItem.click();
            return new GoodsCategoryPage();
        }
    }
}
