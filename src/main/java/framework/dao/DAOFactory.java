package framework.dao;

public abstract class DAOFactory {
    public static final int MySQL = 1;

    public abstract TestedGoodsItemDAO getTestedGoodsItemDAO();
    public abstract GoodsDAO getGoodsDAO();

    public static DAOFactory getDAOFactory(int factoryId) {
        switch (factoryId) {
            case MySQL: return new MySQLDAOFactory();
            default: return null;
        }
    }

}
