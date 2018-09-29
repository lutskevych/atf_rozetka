package framework.dao;

import framework.dataobjects.GoodsItem;
import framework.pageobjects.components.goodsnavigator.GoodsCategories;

import java.util.Collection;

public interface GoodsDAO {
    Collection<GoodsItem> selectGoodsItems(GoodsCategories goodsCategory, String priceCategoryName);
    Collection<GoodsItem> selectGoodsItemsTitleWithActualPrice(GoodsCategories goodsCategory, String priceCategoryName);
    void addGoodsTitlePriceAndGoodsCategories(Collection<GoodsItem> goodsItems, GoodsCategories category);
    void addGoodsPriceInfo(Collection<GoodsItem> goodsItems);
}
