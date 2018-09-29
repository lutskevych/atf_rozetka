package framework.dao.mysql;

import framework.dao.GoodsDAO;
import framework.dao.MySQLDAOFactory;
import framework.dataobjects.GoodsItem;
import framework.pageobjects.components.goodsnavigator.GoodsCategories;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

public class MySQLGoodsDAO implements GoodsDAO {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LAST_PRICE = "last_price";
    private static final String ACTUAL_PRICE = "actual_price";
    private static final String EXTRA_PRICE = "extra_price";
    private static final String PRICE_SIGN = "sign";
    private static final String GOODS_CATEGORY = "category_name";
    private static final String REFERENCE = "reference";

    Statement statement;
    PreparedStatement ps;
    ResultSet resultSet;
    Connection connection;

    public MySQLGoodsDAO() {
        connection = MySQLDAOFactory.getConnection();
    }

    @Override
    public Collection<GoodsItem> selectGoodsItems(GoodsCategories goodsCategory, String priceCategory) {
        String query = format(
                "SELECT g.title, g.description, g.reference, p.last_price, p.actual_price, p.extra_price, c.sign, gc.category_name " +
                        "FROM goods g " +
                        "LEFT JOIN price p " +
                        "ON g.id = p.product_id " +
                        "LEFT JOIN price_categories pc " +
                        "ON g.price_category_id = pc.id " +
                        "LEFT JOIN goods_categories gc " +
                        "ON g.goods_category_id = gc.id " +
                        "LEFT JOIN currency c " +
                        "ON p.price_sign_id = c.id " +
                        "WHERE pc.category_name = '%s' " +
                        "AND gc.category_name = '%s';", priceCategory, goodsCategory);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertToGoodsItemList(resultSet);
    }

    @Override
    public Collection<GoodsItem> selectGoodsItemsTitleWithActualPrice(GoodsCategories goodsCategory, String priceCategory) {
        String query = format("SELECT g.title, p.actual_price " +
                        "FROM goods g " +
                        "LEFT JOIN price p " +
                        "ON p.product_id = g.id " +
                        "WHERE price_category_id = (SELECT id FROM price_categories WHERE category_name = '%s') " +
                        "AND goods_category_id = (SELECT id FROM goods_categories WHERE category_name = '%s');",
                priceCategory, goodsCategory);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convertToGoodsItemList(resultSet);
    }

    public void addGoodsTitlePriceAndGoodsCategories(Collection<GoodsItem> goodsItems, GoodsCategories category) {
        String query = "INSERT INTO goods (title, price_category_id, goods_category_id) VALUES " +
                "(?, (SELECT id FROM price_categories pc WHERE pc.category_name = ?), " +
                "(SELECT id FROM goods_categories gc WHERE gc.category_name = ?));";
        try {
            ps = connection.prepareStatement(query);
            for (GoodsItem goodsItem : goodsItems) {
                ps.setString(1, goodsItem.getTitle());
                ps.setString(2, goodsItem.getPriceActiveIcon());
                ps.setString(3, category.toString());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGoodsPriceInfo(Collection<GoodsItem> goodsItems) {
        String query = "INSERT INTO price (product_id, actual_price, price_sign_id) VALUES " +
                "((SELECT id FROM goods g WHERE g.title = ?), " +
                "?, (SELECT id FROM currency c WHERE c.sign = ?));";
        try {
            ps = connection.prepareStatement(query);
            for (GoodsItem goodsItem : goodsItems) {
                ps.setString(1, goodsItem.getTitle());
                ps.setDouble(2, goodsItem.getActualPrice());
                ps.setString(3, goodsItem.getActualPriceSign());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<GoodsItem> convertToGoodsItemList(ResultSet rs) {
        List<GoodsItem> resultList = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        ResultSetMetaData metaData;
        GoodsItem goodsItem;
        try {
            metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                headers.add(metaData.getColumnName(i));
            }
            while (rs.next()) {
                goodsItem = new GoodsItem();
                if (headers.contains(TITLE)) goodsItem.setTitle(rs.getString(TITLE));
                if (headers.contains(DESCRIPTION)) goodsItem.setShortDescription(rs.getString(DESCRIPTION));
                if (headers.contains(LAST_PRICE)) goodsItem.setOldPrice(rs.getDouble(LAST_PRICE));
                if (headers.contains(ACTUAL_PRICE)) goodsItem.setActualPrice(rs.getDouble(ACTUAL_PRICE));
                if (headers.contains(EXTRA_PRICE)) goodsItem.setAdditionalPrice(rs.getString(EXTRA_PRICE));
                if (headers.contains(PRICE_SIGN)) goodsItem.setAdditionalPrice(rs.getString(PRICE_SIGN));
                if (headers.contains(REFERENCE)) goodsItem.setActualPriceSign(rs.getString(REFERENCE));
                if (headers.contains(GOODS_CATEGORY)) goodsItem.setActualPriceSign(rs.getString(GOODS_CATEGORY));
                resultList.add(goodsItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
