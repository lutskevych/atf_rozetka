package framework.pageobjects;

import framework.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;

public abstract class PageObject {
    protected WebDriver webDriver = WebDriverManager.getInstance().getDriver();

}
