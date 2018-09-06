package framework.pageobjects.components.goodsnavigator;

public enum GoodsCategories {
    NOTEBOOKS_AND_COMPUTERS("2416"),
    SMARTPHONES_TV_AND_ELECTRONICS("3361"),
    HOUSEHOLD_APPLIANCES("4306"),
    GOODS_FOR_HOME("5300"),
    TOOLS_AND_CAR_GOODS("6700"),
    PLUMBING_AND_REPAIR("7806"),
    COUNTRY_HOUSE_AND_GARDEN("8261"),
    SPORTS_AND_HOBBIES("9017"),
    CLOTHING_SHOES_AND_JEWELRY("10515"),
    BEAUTY_AND_HEALTH("12258"),
    CHILDREN_GOODS("13224"),
    STATIONARY_AND_BOOKS("14127"),
    ALCOHOLIC_BEVERAGES_AND_PRODUCTS("14939"),
    GOODS_FOR_BUSINESS("15422"),
    AIR_TICKETS_SERVICES_AND_OTHER("15954");

    private String cssId;

    GoodsCategories(String locator) {
        this.cssId = locator;
    }

    public String getCssId() {
        return cssId;
    }
}
