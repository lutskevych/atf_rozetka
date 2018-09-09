package framework.pageobjects;

import framework.managers.WebDriverManager;
import framework.utils.Timer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        wait.withTimeout(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.alertIsPresent());
        wait.withTimeout(initProperties.defaultWait(), TimeUnit.SECONDS);
        return webDriver.switchTo().alert();
    }

    public void closePopupElementIfPresent() {
        System.out.println("closePopupElementIfPresent()");
        timer.startTimer();
        List<WebElement> elements = webDriver.findElements(By.cssSelector("a.exponea-banner.exponea-popup-banner.exponea-animate"));
        timer.printElapsedTime("Find popup element");
        if (elements.size() != 0) {
            System.out.println("Popup is found!");
            timer.startTimer();
            WebElement closeBtn = elements.get(0).findElement(By.cssSelector("span.exponea-close-cross"));
            wait.until(ExpectedConditions.visibilityOf(closeBtn));
            closeBtn.click();
            timer.printElapsedTime("Find and click on close button");
        }
    }
}
