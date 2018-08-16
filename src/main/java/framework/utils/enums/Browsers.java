package framework.utils.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                    "webdriver.chrome.driver",
                    "src/main/resources/browsers/chromedriver.exe");
            return new ChromeDriver();
        }
    };

    public abstract WebDriver initialize();
}
