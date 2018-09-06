package framework.pageobjects.pages.goodspage;

import framework.dataobjects.Product;
import framework.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoodsItem extends PageObject {
    public static final String PRICE_ACTIVE_ICON_NOVELTY = "novelty";
    public static final String PRICE_ACTIVE_ICON_POPULAR = "popularity";
    public static final String PRICE_ACTIVE_ICON_ACTION = "action";
    public static final String PRICE_ACTIVE_ICON_SUPER_PRICE = "superprice";

    private WebElement goodsItemElement;

    public GoodsItem(WebElement goodsItemElement) {
        this.goodsItemElement = goodsItemElement;
    }

    public String getPriceActiveIcon() {
        String classValue = getElement("i[name=prices_active_element_original]").getAttribute("class");
        if (classValue == null) {
            return "no_icon";
        }
        int startInd = classValue.indexOf("g-tag-icon-middle-");
        if (startInd == -1) {
            return "no icon";
        }
        int endInd = classValue.indexOf("sprite");
        endInd = (endInd == -1) ? (classValue.length() - 1) : (endInd - 1);
        return classValue.substring(startInd + 18, endInd);
    }

    public boolean isLimited() {
        WebElement e = getElement("div.g-i-status.limited");
        if (e == null) {
            return false;
        }
        return true;
    }

    public String getTitlePromo() {
        WebElement e = getElement("div[name='promotions_catalog_tile']");
        if (e == null) {
            return "no_promo_title";
        }
        return e.getText();
    }

    public String getTitle() {
        WebElement e = getElement("p[class='g-i-tile-i-title clearfix'] a");
        if (e == null) {
            return "no_title";
        }
        return e.getText();
    }

    public float getOldPriceValue() {
        WebElement e = getElement("div.g-price-old-inner");
        if (e == null) {
            return -1;
        }
        String sign = e.findElement(By.tagName("span")).getText();
        return Float.parseFloat(e.getText().replaceAll("\\s|\\u2009|" + sign, ""));
    }

    public float getActualPriceValue() {
        WebElement e = getElement("div[name='price'][class*='g-price']:not([class*='-old'])");
        if (e == null) {
            return -1;
        }
        String sign = e.findElement(By.tagName("span")).getText();
        return Float.parseFloat(e.getText().replaceAll("\\s|\\u2009|" + sign, ""));
    }

    public String getOldPriceSign() {
        WebElement e = getElement("div.g-price-old-inner span");
        if (e == null) {
            return "no_sign";
        }
        return e.getText();
    }

    public String getActualPriceSign() {
        WebElement e = getElement("div[name='price'][class*='g-price']:not([class*='-old']) span");
        if (e == null) {
            return "no_sign";
        }
        return e.getText();
    }

    public String getAdditionalPrice() {
        WebElement e = getElement("span.cat-g-addprice-price");
        if (e == null) {
            return "no_additional_price";
        }
        return e.getText();
    }

    public String getShortDescription() {
        WebElement e = getElement("div.short-description");
        if (e == null) {
            return "no_short_description";
        }
        return e.getText();
    }

    public String getReference() {
        WebElement e = getElement("p[class='g-i-tile-i-title clearfix'] a");
        if (e == null) {
            return "no_reference";
        }
        return e.getAttribute("href");
    }

    public GoodsItem addToCart() {
        getElement("button[name='topurchasesfromcatalog']").click();
        return this;
    }

    public Product saveAsProduct() {
        return new Product.Builder()
                .setPriceActiveIcon(getPriceActiveIcon())
                .setTitle(getTitle())
                .setTitlePromo(getTitlePromo())
                .setActualPrice(getActualPriceValue())
                .setActualPriceSign(getActualPriceSign())
                .setOldPrice(getOldPriceValue())
                .setOldPriceSign(getOldPriceSign())
                .setAdditionalPrice(getAdditionalPrice())
                .setShortDescription(getShortDescription())
                .setReference(getReference())
                .setIsLimited(isLimited())
                .build();
    }

    private WebElement getElement(String cssSelector) {
        List<WebElement> result = goodsItemElement.findElements(By.cssSelector(cssSelector));
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}
