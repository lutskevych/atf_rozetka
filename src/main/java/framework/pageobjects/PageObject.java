package framework.pageobjects;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import framework.managers.WebDriverManager;
import framework.utils.Timer;
import framework.utils.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static framework.properties.PropertyLoader.initProperties;

public abstract class PageObject {
    protected Timer timer = new Timer();
    protected WebDriver webDriver = WebDriverManager.getInstance().getDriver();
    protected WebDriverWait wait = new WebDriverWait(webDriver, initProperties.defaultWait(), 250);

    public Alert switchToAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        return webDriver.switchTo().alert();
    }

    protected void closePopupElementIfPresent() {
        System.out.println("closePopupElementIfPresent()");
        timer.startTimer();
        List<WebElement> elements = webDriver.findElements(By.cssSelector("a.exponea-banner.exponea-popup-banner.exponea-animate"));
        timer.printElapsedTime("Find popup element");
        if (elements.size() != 0) {
            System.out.println("Popup is found!");
            timer.startTimer();
            WebElement closeBtn = elements.get(0).findElement(By.cssSelector("span.exponea-close-cross"));
            waitUntilWithTimeout(ExpectedConditions.visibilityOf(closeBtn), 2);
            waitUntilWithTimeout(ExpectedConditions.visibilityOf(closeBtn), 3);
            closeBtn.click();
            timer.printElapsedTime("Find and click on close button");
        }
    }

    protected void waitUntilWithTimeout(ExpectedCondition<WebElement> expectedCondition, long timeOutSeconds) {
        wait.withTimeout(timeOutSeconds, TimeUnit.SECONDS);
        wait.until(expectedCondition);
        wait.withTimeout(initProperties.defaultWait(), TimeUnit.SECONDS);
    }

    protected void waitUntilWithTimeout(Predicate<WebDriver> expectedCondition, long timeOutSeconds) {
        wait.withTimeout(timeOutSeconds, TimeUnit.SECONDS);
        wait.until(expectedCondition);
        wait.withTimeout(initProperties.defaultWait(), TimeUnit.SECONDS);
    }

    protected WebElement waitUntilWithTimeout(Function<WebDriver, WebElement> function, long timeOutSeconds) {
        wait.withTimeout(timeOutSeconds, TimeUnit.SECONDS);
        WebElement webElement = wait.until(function);
        wait.withTimeout(initProperties.defaultWait(), TimeUnit.SECONDS);
        return webElement;
    }
}
