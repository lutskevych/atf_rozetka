package framework.pageobjects.pages.mainpage;

import framework.pageobjects.PageObject;

import static framework.properties.PropertyLoader.initProperties;

public class HomePage extends PageObject {

    public HomePage() {
        webDriver.get(initProperties.url());
        closePopupElementIfPresent();
    }
}
