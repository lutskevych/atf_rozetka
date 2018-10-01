package tests.components;

import framework.dataobjects.GoodsItem;
import framework.pageobjects.components.goodsnavigator.GoodsCatalogNavigator;
import framework.pageobjects.components.goodsnavigator.GoodsCategories;
import framework.pageobjects.pages.goodspage.GoodsItemBlock;
import framework.pageobjects.pages.goodspage.GoodsPage;
import framework.pageobjects.pages.mainpage.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import tests.BaseTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
@Story("Prices on the web page have to match prices in the Data Base")
public class AllGoodsCategoriesTopSalesTest extends BaseTest {
    GoodsCatalogNavigator goodsCatalogNavigator;
    List<GoodsItem> actualGoodsItems;

    @Description("Opening home page")
    @BeforeClass
    public void openHomePage() {
        testedGoodsItemDAO.deleteAllGoodsItems();
        homePage = new HomePage();
        goodsCatalogNavigator = new GoodsCatalogNavigator();
    }

    @BeforeMethod
    public void openMainGoodsCatalogNavigator() {
        goodsCatalogNavigator.openGoodsCatalog();
    }

    @Test(enabled = true, testName = "TC-100.10", dataProvider = "categoriesAndSubcategories")
    public void topPopularGoodsTitlePriceVerification(GoodsCategories goodsCategory, String subCategoryCssSelector) {
        GoodsPage goodsPage = goodsCatalogNavigator.openSubCategory(goodsCategory, subCategoryCssSelector);
        actualGoodsItems = goodsPage.getGoodsItemsTitleWithActualPriceFromPagesByActionIcon(1, GoodsItemBlock.PRICE_ACTIVE_ICON_POPULAR);
        List<GoodsItem> expectedGoodsItems = (ArrayList)goodsDAO.selectGoodsItemsTitleWithActualPrice(goodsCategory, GoodsItemBlock.PRICE_ACTIVE_ICON_POPULAR);
        Assertions.assertThat(actualGoodsItems)
                .hasSize(expectedGoodsItems.size())
                .hasSameElementsAs(expectedGoodsItems);
    }

    @Test(enabled = false, dataProvider = "categoriesAndSubcategories")
    public void retrieveGoodsAndSaveToDb(GoodsCategories goodsCategory, String subCategoryCssSelector) {
        GoodsPage page = goodsCatalogNavigator.openSubCategory(goodsCategory, subCategoryCssSelector);
        actualGoodsItems = page.getGoodsItemsFromPagesWithActionIcon(1, GoodsItemBlock.PRICE_ACTIVE_ICON_POPULAR);
        goodsDAO.addGoodsTitlePriceAndGoodsCategories(actualGoodsItems, goodsCategory);
        goodsDAO.addGoodsPriceInfo(actualGoodsItems);
    }

    @AfterMethod(alwaysRun = true)
    public void saveResultsToDB(Method method) {
        testedGoodsItemDAO.addGoodsItems(actualGoodsItems, method.getAnnotation(Test.class).testName());
    }

    @DataProvider
    public Object[][] categoriesAndSubcategories() {
        return new Object[][] {
                {GoodsCategories.NOTEBOOKS_AND_COMPUTERS, "a.f-menu-sub-l-i-link.blacklink[href*='/projector/']"},
                {GoodsCategories.SMARTPHONES_TV_AND_ELECTRONICS, "a.f-menu-sub-l-i-link.blacklink[href*='=smartfon/']"},
                {GoodsCategories.HOUSEHOLD_APPLIANCES, "a.f-menu-sub-l-i-link.blacklink[href*='/steam_cleaners/']"},
                {GoodsCategories.GOODS_FOR_HOME, "a.f-menu-sub-l-i-link.blacklink[href*='/schetchiki-vody/']"}
        };
    }
}
