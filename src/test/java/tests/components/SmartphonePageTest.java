package tests.components;

import framework.pageobjects.components.goodsnavigator.GoodsCatalogNavigator;
import framework.pageobjects.components.goodsnavigator.GoodsCategories;
import framework.pageobjects.pages.goodspage.GoodsItem;
import framework.pageobjects.pages.goodspage.GoodsPage;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

public class SmartphonePageTest extends BaseTest {

    @Test
    public void topPopularGoods() {
        GoodsCatalogNavigator catalog = mainPage.getGoodsCatalogNavigator().openGoodsCatalog();
        GoodsPage page = catalog.openSubCategory(GoodsCategories.SMARTPHONES_TV_AND_ELECTRONICS, "a[href*='=smartfon/']");
//        List<GoodsItem> result = page.getGoodsItemsFromPagesWithActionIcon(1, GoodsItem.PRICE_ACTIVE_ICON_POPULAR);
        List<GoodsItem> result = page.getAllGoodsItemsFromPages(3);
        for (GoodsItem e : result) {
            String p = e.saveAsProduct().toString();
            System.out.println(p);
        }
    }
}
