package framework.pageobjects.components.goodsnavigator;

import framework.pageobjects.PageObject;
import org.openqa.selenium.WebElement;

public abstract class BaseSubCategory extends PageObject {

    protected WebElement subCategoryBlock;

    public BaseSubCategory initialize(WebElement webElement) {
        subCategoryBlock = webElement;
        return this;
    }
}
