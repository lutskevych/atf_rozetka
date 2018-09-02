package framework.pageobjects.components.header;

import framework.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderBlock extends PageObject {

    @FindBy(how = How.CSS, using = "div[class='clearfix body-header-row-top']")
    private WebElement topRow;
    @FindBy(how = How.CSS, using = "div[class='clearfix body-header-row-bottom']")
    private WebElement bottomRow;

    public HeaderBlock() {
        super();
    }
}
