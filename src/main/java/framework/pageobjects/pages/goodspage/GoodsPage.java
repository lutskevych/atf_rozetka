package framework.pageobjects.pages.goodspage;

import framework.dataobjects.Product;
import framework.pageobjects.pages.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoodsPage extends BasePage {
    private WebElement catalogGoodsBlock;
    private List<Product> goodsList; //TODO determine list from one page or from all pages. Other variants...

    public GoodsPage(){
        super();
    }

}
