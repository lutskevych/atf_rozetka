package framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static final String CHROME_DRIVER_PATH = "src/main/resources/browsers/chromedriver.exe";
    private static final String CHROME_DRIVER_PATH_KEY = "webdriver.chrome.driver";

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private final static WebDriverManager INSTANCE = new WebDriverManager();

    private WebDriverManager() {}

    public static WebDriverManager getInstance() {
        return INSTANCE;
    }

    public WebDriver createDriver(Drivers driver) {
        webDriver.set(driver.initialize());
        return webDriver.get();
    }

    public void closeDriver() {
        webDriver.get().close();
        webDriver.get().quit();
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

    private enum Drivers {
        FIREFOX {
            @Override
            public WebDriver initialize() {
                return new FirefoxDriver();
            }
        },
        CHROME {
            @Override
            public WebDriver initialize() {
                System.setProperty(
                        CHROME_DRIVER_PATH,
                        CHROME_DRIVER_PATH_KEY);
                return new ChromeDriver();
            }
        };

        public abstract WebDriver initialize();
    }
}
