package framework.dao;

import framework.dataobjects.Product;

import java.util.Collection;

public interface TestedProductsDAO {
    void addProduct(Product product);
    void addProducts(Collection<Product> products);
    void deleteAllProducts();
}
