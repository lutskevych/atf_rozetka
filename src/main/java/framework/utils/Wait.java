package framework.utils;

import com.google.common.base.Predicate;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

    public static void untilDocumentReadyStateInteractive(WebDriver driver, long timeOutInSeconds) {
        until(driver,
              (WebDriver d) -> (Boolean)((JavascriptExecutor) d).executeScript("return document.readyState=='interactive'"),
              timeOutInSeconds);
    }

    public static void untilDocumentReadyStateComplete(WebDriver driver, long timeOutInSeconds) {
        until(driver,
                (WebDriver d) -> (Boolean)((JavascriptExecutor) d).executeScript("return document.readyState=='complete'"),
                timeOutInSeconds);
    }

    private static void until(WebDriver driver, Predicate<WebDriver> waitCondition, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(waitCondition);
    }
}
