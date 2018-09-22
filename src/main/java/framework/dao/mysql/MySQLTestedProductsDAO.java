package framework.dao.mysql;

import framework.dao.MySQLDAOFactory;
import framework.dao.TestedProductsDAO;
import framework.dataobjects.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import static java.util.Arrays.asList;

public class MySQLTestedProductsDAO implements TestedProductsDAO {
    private final String addQuery = "INSERT INTO found_products VALUES (?,?,?);";
    private final String deleteAllQuery = "DELETE FROM found_product";

    PreparedStatement ps;
    Statement statement;
    Connection connection;

    public MySQLTestedProductsDAO() {
        connection = MySQLDAOFactory.getConnection();
    }

    public void addProduct(Product product) {
        addProducts(asList(product));
    }

    public void addProducts(Collection<Product> products) {
        try {
            ps = connection.prepareStatement(addQuery);
            for (Product product : products) {
                ps.setString(1, product.getTitle());
                ps.setDouble(2, product.getActualPrice());
                ps.setString(3, product.getActualPriceSign());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllProducts() {
        try {
            statement = connection.prepareStatement(deleteAllQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
