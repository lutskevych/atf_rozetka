package framework.pageobjects.pages;

import framework.pageobjects.PageObject;
import framework.pageobjects.components.header.HeaderBlock;
import framework.pageobjects.components.goodsnavigator.GoodsCatalogNavigator;

public abstract class BasePage extends PageObject {

    public BasePage() {
        super();
    }

    public HeaderBlock getHeaderBlock() {
        return new HeaderBlock();
    }

    public GoodsCatalogNavigator getGoodsCatalogNavigator() {
        return new GoodsCatalogNavigator();
    }

}
