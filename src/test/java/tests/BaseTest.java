package tests;

import framework.managers.WebDriverManager;
import framework.managers.WebDriverManager.Browsers;
import framework.pageobjects.pages.mainpage.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static framework.properties.PropertyLoader.initProperties;

public class BaseTest {
    protected HomePage mainPage;
    protected WebDriver webDriver;

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(Browsers browser) {
        webDriver = WebDriverManager.getInstance().createDriver(browser);
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
