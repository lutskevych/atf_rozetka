package framework.dao;

import framework.dataobjects.GoodsItem;

import java.util.Collection;

public interface GoodsDAO {
    Collection<GoodsItem> selectGoodsItemsByPriceCategory(String priceCategoryName);
}
