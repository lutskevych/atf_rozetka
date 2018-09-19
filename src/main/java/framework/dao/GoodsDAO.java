package framework.dao;

import framework.dataobjects.Product;

import java.util.Collection;

public interface GoodsDAO {
    GoodsDAO selectProduct(Product product);
    Collection<Product> selectProductsByPriceCategory(String priceCategoryName);
}
