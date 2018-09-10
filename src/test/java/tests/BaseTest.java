package tests;

import framework.managers.WebDriverManager;
import framework.managers.WebDriverManager.Browsers;
import framework.pageobjects.pages.mainpage.HomePage;
import framework.utils.Timer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static framework.properties.PropertyLoader.initProperties;

public class BaseTest {
    protected HomePage mainPage;
    protected WebDriver webDriver;
    protected Timer timer;

    @BeforeClass
//    @Parameters("browser")
    public void beforeClass() {
        webDriver = WebDriverManager.getInstance().createDriver(Browsers.FIREFOX); //TODO change after debugging
        webDriver.manage().timeouts().pageLoadTimeout(initProperties.pageLoadWait(), TimeUnit.SECONDS);
        if (initProperties.windowMaximize()){
            webDriver.manage().window().maximize();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        mainPage = new HomePage(initProperties.url());
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

}
