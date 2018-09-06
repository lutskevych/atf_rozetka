package framework.pageobjects;

import framework.managers.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static framework.properties.PropertyLoader.initProperties;

public abstract class PageObject {
    protected WebDriver webDriver = WebDriverManager.getInstance().getDriver();
    protected WebDriverWait wait = new WebDriverWait(webDriver, initProperties.defaultWait(), 250);

    public Alert switchToAlert() {
        wait.withTimeout(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.alertIsPresent());
        wait.withTimeout(initProperties.defaultWait(), TimeUnit.SECONDS);
        return webDriver.switchTo().alert();
    }
}
