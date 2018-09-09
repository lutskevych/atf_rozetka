package framework.pageobjects.pages.mainpage;

import framework.pageobjects.pages.BasePage;
import framework.utils.Wait;

import static framework.properties.PropertyLoader.initProperties;

public class HomePage extends BasePage {

    public HomePage(String url) {
        webDriver.get(url);
//        timer.startTimer();
//        Wait.untilPageLoadComplete(webDriver, initProperties.defaultWait());
//        timer.printElapsedTime("Wait for HomePage load complete");
        closePopupElementIfPresent();
    }
}
