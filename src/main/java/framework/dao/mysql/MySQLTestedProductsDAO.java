package framework.dao.mysql;

import framework.dao.MySQLDAOFactory;
import framework.dao.TestedProductsDAO;
import framework.dataobjects.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

public class MySQLTestedProductsDAO implements TestedProductsDAO {
    Statement statement;
    ResultSet resultSet;
    Connection connection;

    public MySQLTestedProductsDAO() {
        connection = MySQLDAOFactory.getConnection();
    }

    public int addProduct(Product product) {
//        TODO
        return 0;
    }

    public int addProducts(Collection<Product> products) {
//        TODO
        return 0;
    }

    public boolean deleteAllProducts() {
//        TODO
        return false;
    }
}
