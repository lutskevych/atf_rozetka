package tests.components;

import framework.dao.mysql.MySQLGoodsDAO;
import framework.dao.mysql.MySQLTestedProductsDAO;
import framework.dataobjects.Product;
import framework.pageobjects.components.goodsnavigator.GoodsCatalogNavigator;
import framework.pageobjects.components.goodsnavigator.GoodsCategories;
import framework.pageobjects.pages.goodspage.GoodsItem;
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
        new MySQLTestedProductsDAO().deleteAllProducts();
    }

    @Test
    public void topPopularGoods() {
        GoodsCatalogNavigator catalog = mainPage.getGoodsCatalogNavigator().openGoodsCatalog();
        GoodsPage page = catalog.openSubCategory(GoodsCategories.SMARTPHONES_TV_AND_ELECTRONICS, "a[href*='=smartfon/']");
        List<GoodsItem> result = page.getGoodsItemsFromPagesWithActionIcon(1, GoodsItem.PRICE_ACTIVE_ICON_POPULAR);

        List<Product> actualProducts = new ArrayList<>();
        for (GoodsItem e : result) {
            actualProducts.add(e.saveAsProduct());
        }

        List<Product> expectedProducts = (ArrayList)new MySQLGoodsDAO().selectProductsByPriceCategory(GoodsItem.PRICE_ACTIVE_ICON_POPULAR);
        new MySQLTestedProductsDAO().addProducts(actualProducts);

        Assertions.assertThat(actualProducts.size()).isEqualTo(expectedProducts.size());


    }
}
