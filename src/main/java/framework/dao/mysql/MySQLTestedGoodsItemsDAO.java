package framework.dao.mysql;

import framework.dao.MySQLDAOFactory;
import framework.dao.TestedGoodsItemDAO;
import framework.dataobjects.GoodsItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import static java.util.Arrays.asList;

public class MySQLTestedGoodsItemsDAO implements TestedGoodsItemDAO {
    PreparedStatement ps;
    Statement statement;
    Connection connection;

    public MySQLTestedGoodsItemsDAO() {
        connection = MySQLDAOFactory.getConnection();
    }

    public void addGoodsItem(GoodsItem GoodsItem) {
        addGoodsItems(asList(GoodsItem));
    }

    public void addGoodsItems(Collection<GoodsItem> GoodsItems) {
        try {
            ps = connection.prepareStatement("INSERT INTO test_found_goods VALUES (?,?,?,?,?,?,?,?,?);");
            for (GoodsItem goodsItem : GoodsItems) {
                ps.setString(1, goodsItem.getTitle());
                ps.setString(2, goodsItem.getShortDescription());
                ps.setDouble(3, goodsItem.getActualPrice());
                ps.setString(4, goodsItem.getPriceActiveIcon());
                ps.setString(5, goodsItem.getTitlePromo());
                ps.setString(6, goodsItem.getReference());
                ps.setDouble(7, goodsItem.getOldPrice());
                ps.setString(8, goodsItem.getAdditionalPrice());
                ps.setString(9, goodsItem.getActualPriceSign());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllGoodsItems() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM test_found_goods;");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
