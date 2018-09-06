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

    public WebDriver createDriver(Browsers browser) {
        webDriver.set(browser.initialize());
        return webDriver.get();
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

    public enum Browsers {
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
