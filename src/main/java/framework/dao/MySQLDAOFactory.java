package framework.dao;

import framework.dao.mysql.MySQLGoodsDAO;
import framework.dao.mysql.MySQLTestedGoodsItemsDAO;
import framework.properties.DataBaseProperties;
import org.aeonbits.owner.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    private static Connection connection;
    private static DataBaseProperties dbProperties;

    public static Connection getConnection() {
        if (connection == null) setConnection();
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public TestedGoodsItemDAO getTestedGoodsItemDAO() {
        return new MySQLTestedGoodsItemsDAO();
    }

    public GoodsDAO getGoodsDAO() {
        return new MySQLGoodsDAO();
    }

    private static void setConnection() {
        try {
            dbProperties = ConfigFactory.create(DataBaseProperties.class);
            connection = DriverManager.getConnection(
                    dbProperties.url(),
                    dbProperties.user(),
                    dbProperties.password());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
