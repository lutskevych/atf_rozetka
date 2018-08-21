package framework.utils;

import com.google.common.base.Predicate;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.properties.PropertyLoader.initProperties;

public class Wait {
    public static void untilJQueryIsDone(WebDriver driver) {
        untilJQueryIsDone(driver, initProperties.implicitlyWait());
    }

    public static void untilJQueryIsDone(WebDriver driver, long timeOutInSeconds) {
        until(driver,
              (WebDriver d) -> (Boolean)((JavascriptExecutor) d).executeScript("return JQuery.active == 0"),
              timeOutInSeconds);
    }

    public static void untilPageLoadComplete(WebDriver driver) {
        untilPageLoadComplete(driver, initProperties.implicitlyWait());
    }

    public static void untilPageLoadComplete(WebDriver driver, long timeOutInSeconds) {
        until(driver,
              (WebDriver d) -> (Boolean)((JavascriptExecutor) d).executeScript("return document.readyState==complete"),
              timeOutInSeconds);
    }

    private static void until(WebDriver driver, Predicate<WebDriver> waitCondition, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(waitCondition);
    }
}
