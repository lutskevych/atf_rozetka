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

    public void addGoodsItem(GoodsItem GoodsItem, String testCaseId) {
        addGoodsItems(asList(GoodsItem), testCaseId);
    }

    public void addGoodsItems(Collection<GoodsItem> goodsItems, String testCaseId) {
        try {
            ps = connection.prepareStatement("INSERT INTO test_found_goods VALUES (?,?,?,?,?,?,?,?,?,?);");
            for (GoodsItem g : goodsItems) {
                ps.setString(1, g.getTitle());
                ps.setString(2, g.getShortDescription());
                ps.setDouble(3, g.getActualPrice());
                ps.setString(4, g.getPriceActiveIcon());
                ps.setString(5, g.getTitlePromo());
                ps.setString(6, g.getReference());
                ps.setDouble(7, g.getOldPrice());
                ps.setString(8, g.getAdditionalPrice());
                ps.setString(9, g.getActualPriceSign());
                ps.setString(10, testCaseId);
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
