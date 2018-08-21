package framework.pageobjects;

import framework.managers.WebDriverManager;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject {
    protected WebDriver webDriver = WebDriverManager.getInstance().getDriver();
    protected WebDriverWait wait = new WebDriverWait(webDriver, 10, 250);

    public PageObject() {
        this.wait.ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(webDriver, this);
    }
}
