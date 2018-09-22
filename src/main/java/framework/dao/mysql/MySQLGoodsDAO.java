package framework.dao.mysql;

import framework.dao.GoodsDAO;
import framework.dao.MySQLDAOFactory;
import framework.dataobjects.GoodsItem;

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

    public Collection<GoodsItem> selectGoodsItemsByPriceCategory(String priceCategoryName) {
        String query = format(
                        "SELECT g.title, g.description, g.reference, p.last_price, p.actual_price, p.extra_price, c.sign " +
                        "FROM goods g " +
                        "LEFT JOIN price p " +
                        "ON g.id = p.product_id " +
                        "LEFT JOIN price_categories pc " +
                        "ON g.price_category_id = pc.id " +
                        "LEFT JOIN currency c " +
                        "ON p.price_sign_id = c.id " +
                        "WHERE pc.category_name = '%s';", priceCategoryName);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertToGoodsItemList(resultSet);
    }

    private List<GoodsItem> convertToGoodsItemList(ResultSet rs) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        ResultSetMetaData metaData;
        GoodsItem GoodsItem;
        try {
            metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                headers.add(metaData.getColumnName(i));
            }
            while (rs.next()) {
                GoodsItem = new GoodsItem();
                if (headers.contains("title")) GoodsItem.setTitle(rs.getString("title"));
                if (headers.contains("description")) GoodsItem.setShortDescription(rs.getString("description"));
                if (headers.contains("price")) GoodsItem.setActualPrice(rs.getDouble("price"));
                if (headers.contains("price_sign")) GoodsItem.setActualPriceSign(rs.getString("price_sign"));
                resultList.add(GoodsItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
