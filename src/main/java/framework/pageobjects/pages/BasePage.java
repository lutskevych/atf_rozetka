package framework.pageobjects.pages;

import framework.pageobjects.PageObject;
import framework.pageobjects.components.goodsnavigator.GoodsCatalogNavigator;
import framework.pageobjects.components.header.HeaderBlock;

public abstract class BasePage extends PageObject {

    public HeaderBlock getHeaderBlock() {
        return new HeaderBlock();
    }

    public GoodsCatalogNavigator getGoodsCatalogNavigator() {
        return new GoodsCatalogNavigator();
    }

}
