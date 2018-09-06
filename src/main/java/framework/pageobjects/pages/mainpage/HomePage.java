package framework.pageobjects.pages.mainpage;

import framework.pageobjects.pages.BasePage;
import framework.utils.Wait;

import static framework.properties.PropertyLoader.initProperties;

public class HomePage extends BasePage {

    public HomePage(String url) {
        webDriver.get(url);
        Wait.untilPageLoadComplete(webDriver, initProperties.defaultWait());
    }
}
