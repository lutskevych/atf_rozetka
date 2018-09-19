package framework.dao;

public abstract class DAOFactory {
    public static final int mySQL = 1;

    public abstract TestedProductsDAO getProductDAO();
    public abstract GoodsDAO getGoodsDAO();

    public static DAOFactory getDAOFactory(int factoryId) {
        switch (factoryId) {
            case mySQL: return new MySQLDAOFactory();
            default: return null;
        }
    }

}
