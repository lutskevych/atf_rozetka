package framework.dao.mysql;

import framework.dao.GoodsDAO;
import framework.dao.MySQLDAOFactory;
import framework.dataobjects.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

public class MySQLGoodsDAO implements GoodsDAO {
    Statement statement;
    ResultSet resultSet;
    Connection connection;

    public MySQLGoodsDAO() {
        connection = MySQLDAOFactory.getConnection();
    }
    public GoodsDAO selectProduct(Product product) {
//        TODO
        return null;
    }

    public Collection<Product> selectProductsByPriceCategory(String priceCategoryName) {
        String query = format(
                "SELECT g.title, g.price, g.price_sign, pc.category_name " +
                "FROM goods g " +
                "LEFT JOIN price_categories pc " +
                "ON g.price_category_id = pc.id " +
                "WHERE pc.category_name = '%s';", priceCategoryName);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertToProductList(resultSet);
    }

    private List<Product> convertToProductList(ResultSet rs) {
        List<Product> resultList = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        ResultSetMetaData metaData;
        Product product;
        try {
            metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                headers.add(metaData.getColumnName(i));
            }
            while (rs.next()) {
                product = new Product();
                if (headers.contains("title")) product.setTitle(rs.getString("title"));
                if (headers.contains("description")) product.setShortDescription(rs.getString("description"));
                if (headers.contains("price")) product.setActualPrice(rs.getDouble("price"));
                if (headers.contains("price_sign")) product.setActualPriceSign(rs.getString("price_sign"));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
