package framework.pageobjects.pages.goodspage;

import framework.dataobjects.Product;
import framework.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GoodsItem extends PageObject {
    private Product product;
    private WebElement goodsItemElement;
    private WebElement priceActiveElement;
    private WebElement titlePromo;
    private WebElement title;
    private WebElement oldPrice;
    private WebElement actualPrice;
    private WebElement reference;

    public GoodsItem(WebElement goodsItemElement) {
        this.goodsItemElement = goodsItemElement;
        initElements();
    }

    private void initElements() {
        priceActiveElement = goodsItemElement.findElement(By.cssSelector(""));
//        TODO
    }

    public GoodsItem addToCart() {
//        TODO
        return this;
    }

    public GoodsItem addToWishList() {
//        TODO
        return this;
    }
}
