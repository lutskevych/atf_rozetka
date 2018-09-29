package tests;

import framework.dao.DAOFactory;
import framework.dao.GoodsDAO;
import framework.dao.TestedGoodsItemDAO;
import framework.managers.WebDriverManager;
import framework.managers.WebDriverManager.Browsers;
import framework.pageobjects.pages.mainpage.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static framework.properties.PropertyLoader.initProperties;

public class BaseTest {
    protected HomePage homePage;
    protected WebDriver webDriver;
    protected GoodsDAO goodsDAO;
    protected TestedGoodsItemDAO testedGoodsItemDAO;

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(Browsers browser) {
        initializeWebDriver(browser);
        initializeDAO();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        webDriver.quit();
    }

    private void initializeWebDriver(Browsers browser) {
        webDriver = WebDriverManager.getInstance().createDriver(browser);
        webDriver.manage().timeouts().pageLoadTimeout(initProperties.pageLoadWait(), TimeUnit.SECONDS);
        if (initProperties.windowMaximize()){
            webDriver.manage().window().maximize();
        }
    }

    private void initializeDAO() {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        goodsDAO = mySQLFactory.getGoodsDAO();
        testedGoodsItemDAO = mySQLFactory.getTestedGoodsItemDAO();

    }

}
