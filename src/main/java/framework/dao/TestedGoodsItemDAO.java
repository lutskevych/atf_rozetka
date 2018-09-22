package framework.dao;

import framework.dataobjects.GoodsItem;

import java.util.Collection;

public interface TestedGoodsItemDAO {
    void addGoodsItem(GoodsItem GoodsItem);
    void addGoodsItems(Collection<GoodsItem> GoodsItems);
    void deleteAllGoodsItems();
}
