package framework.dataobjects;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GoodsItem {
    private String priceActiveIcon;
    private String titlePromo;
    private String title;
    private double oldPrice;
    private double actualPrice;
    private String oldPriceSign;
    private String actualPriceSign;
    private String additionalPrice;
    private String shortDescription;
    private String reference;
    private boolean isLimited;
    private String goodsCategory;

    public GoodsItem() {}

    private GoodsItem(final Builder builder) {
        priceActiveIcon = builder.priceActiveIcon;
        titlePromo = builder.titlePromo;
        title = builder.title;
        oldPrice = builder.oldPrice;
        actualPrice = builder.actualPrice;
        oldPriceSign = builder.oldPriceSign;
        actualPriceSign = builder.actualPriceSign;
        additionalPrice = builder.additionalPrice;
        shortDescription = builder.shortDescription;
        reference = builder.reference;
        isLimited = builder.isLimited;
        goodsCategory = builder.goodsCategory;
    }

    public static class Builder {
        private String priceActiveIcon;
        private String titlePromo;
        private String title;
        private float oldPrice;
        private float actualPrice;
        private String oldPriceSign;
        private String actualPriceSign;
        private String additionalPrice;
        private String shortDescription;
        private String reference;
        private boolean isLimited;
        private String goodsCategory;

        public Builder setPriceActiveIcon(String priceActiveIcon) {
            this.priceActiveIcon = priceActiveIcon;
            return this;
        }

        public Builder setTitlePromo(String titlePromo) {
            this.titlePromo = titlePromo;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setOldPrice(float oldPrice) {
            this.oldPrice = oldPrice;
            return this;
        }

        public Builder setActualPrice(float actualPrice) {
            this.actualPrice = actualPrice;
            return this;
        }

        public Builder setOldPriceSign(String oldPriceSign) {
            this.oldPriceSign = oldPriceSign;
            return this;
        }

        public Builder setActualPriceSign(String actualPriceSign) {
            this.actualPriceSign = actualPriceSign;
            return this;
        }

        public Builder setAdditionalPrice(String additionalPrice) {
            this.additionalPrice = additionalPrice;
            return this;
        }

        public Builder setShortDescription(String description) {
            this.shortDescription = description;
            return this;
        }

        public Builder setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder setIsLimited(boolean isLimited) {
            this.isLimited = isLimited;
            return this;
        }

        public Builder setGoodsCategory(String category) {
            this.goodsCategory = category;
            return this;
        }

        public GoodsItem build() {
            return new GoodsItem(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (int)actualPrice;
        hash = 31 * hash + (actualPriceSign != null ? actualPriceSign.hashCode() : 0);
        hash = 31 * hash + (additionalPrice != null ? additionalPrice.hashCode() : 0);
        hash = 31 * hash + (goodsCategory != null ? goodsCategory.hashCode() : 0);
        hash = 31 * hash + (isLimited ? 1 : 0);
        hash = 31 * hash + (int)oldPrice;
        hash = 31 * hash + (oldPriceSign != null ? oldPriceSign.hashCode() : 0);
        hash = 31 * hash + (priceActiveIcon != null ? priceActiveIcon.hashCode() : 0);
        hash = 31 * hash + (reference != null ? reference.hashCode() : 0);
        hash = 31 * hash + (shortDescription != null ? shortDescription.hashCode() : 0);
        hash = 31 * hash + (title != null ? title.hashCode() : 0);
        hash = 31 * hash + (titlePromo != null ? titlePromo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof GoodsItem)) return false;

        final GoodsItem o1 = (GoodsItem)o;
        if (actualPrice != 0 ? Double.compare(actualPrice, o1.actualPrice) != 0 : o1.actualPrice != 0) return false;
        if (actualPriceSign != null ? !actualPriceSign.equals(o1.actualPriceSign) : o1.actualPriceSign != null) return false;
        if (additionalPrice != null ? !additionalPrice.equals(o1.additionalPrice) : o1.additionalPrice != null) return false;
        if (goodsCategory != null ? !goodsCategory.equals(o1.additionalPrice) : o1.goodsCategory != null) return false;
        if (isLimited != o1.isLimited) return false;
        if (oldPrice != 0 ? Double.compare(oldPrice, o1.oldPrice) != 0 : o1.oldPrice != 0) return false;
        if (oldPriceSign != null ? !oldPriceSign.equals(o1.oldPriceSign) : o1.oldPriceSign != null) return false;
        if (priceActiveIcon != null ? !priceActiveIcon.equals(o1.priceActiveIcon) : o1.priceActiveIcon != null) return false;
        if (reference != null ? !reference.equals(o1.reference) : o1.reference != null) return false;
        if (shortDescription != null ? !shortDescription.equals(o1.shortDescription) : o1.shortDescription != null) return false;
        if (title != null ? !title.equals(o1.title) : o1.title != null) return false;
        if (titlePromo != null ? !titlePromo.equals(o1.titlePromo) : o1.titlePromo != null) return false;
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\n=============================================")
                .append("\npriceActiveIcon: ").append(priceActiveIcon)
                .append("\ntitlePromo: ").append(titlePromo)
                .append("\ntitle: ").append(title)
                .append("\noldPrice: ").append(oldPrice)
                .append("\noldPriceSign: ").append(oldPriceSign)
                .append("\nactualPrice: ").append(actualPrice)
                .append("\nactualPriceSign: ").append(actualPriceSign)
                .append("\nadditionalPrice: ").append(additionalPrice)
                .append("\nshortDescription: ").append(shortDescription)
                .append("\nreference: ").append(reference)
                .append("\nisLimited: ").append(isLimited)
                .append("\ngoodsCategory: ").append(goodsCategory)
                .append("\n=============================================")
                .toString();
    }
}
