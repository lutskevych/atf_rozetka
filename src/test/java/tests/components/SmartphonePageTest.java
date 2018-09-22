package tests.components;

import framework.dao.mysql.MySQLGoodsDAO;
import framework.dao.mysql.MySQLTestedGoodsItemsDAO;
import framework.dataobjects.GoodsItem;
import framework.pageobjects.components.goodsnavigator.GoodsCatalogNavigator;
import framework.pageobjects.components.goodsnavigator.GoodsCategories;
import framework.pageobjects.pages.goodspage.GoodsItemBlock;
import framework.pageobjects.pages.goodspage.GoodsPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class SmartphonePageTest extends BaseTest {

    @BeforeMethod
    public void clearFoundProductsTable() {
        new MySQLTestedGoodsItemsDAO().deleteAllGoodsItems();
    }

    @Test
    public void topPopularGoods() {
        GoodsCatalogNavigator catalog = mainPage.getGoodsCatalogNavigator().openGoodsCatalog();
        GoodsPage page = catalog.openSubCategory(GoodsCategories.SMARTPHONES_TV_AND_ELECTRONICS, "a[href*='=smartfon/']");
        List<GoodsItemBlock> result = page.getGoodsItemsFromPagesWithActionIcon(1, GoodsItemBlock.PRICE_ACTIVE_ICON_POPULAR);

        List<GoodsItem> actualGoodsItems = new ArrayList<>();
        for (GoodsItemBlock e : result) {
            actualGoodsItems.add(e.createGoodsItemObject());
        }

        List<GoodsItem> expectedGoodsItems = (ArrayList)new MySQLGoodsDAO().selectGoodsItemsByPriceCategory(GoodsItemBlock.PRICE_ACTIVE_ICON_POPULAR);
        new MySQLTestedGoodsItemsDAO().addGoodsItems(actualGoodsItems);

        Assertions.assertThat(actualGoodsItems.size()).isEqualTo(expectedGoodsItems.size());


    }
}
