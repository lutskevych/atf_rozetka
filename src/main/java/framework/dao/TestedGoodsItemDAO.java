package framework.dao;

import framework.dataobjects.GoodsItem;

import java.util.Collection;

public interface TestedGoodsItemDAO {
    void addGoodsItem(GoodsItem GoodsItem, String testCaseId);
    void addGoodsItems(Collection<GoodsItem> GoodsItems, String testCaseId);
    void deleteAllGoodsItems();
}
