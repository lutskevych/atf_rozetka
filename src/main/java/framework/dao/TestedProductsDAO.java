package framework.dao;

import framework.dataobjects.Product;

import java.util.Collection;

public interface TestedProductsDAO {
    int addProduct(Product product);
    int addProducts(Collection<Product> products);
    boolean deleteAllProducts();
}
