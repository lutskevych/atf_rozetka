package framework.utils;

import framework.utils.enums.Browsers;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private final static WebDriverManager INSTANCE = new WebDriverManager();

    private WebDriverManager() {}

    public static WebDriverManager getInstance() {
        return INSTANCE;
    }

    public WebDriver createDriver(Browsers browsers) {
        webDriver.set(browsers.initialize());
        return webDriver.get();
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }
}
