package framework.dataobjects;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Product {
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

    private Product(final Builder builder) {
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

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public int hashCode() {
//        TODO
        return super.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("=============================================")
                .append("\npriceActiveIcon: ").append(priceActiveIcon)
                .append("\ntitlePromo: ").append(titlePromo)
                .append("\ntitle: ").append(title)
                .append("\noldPrice: ").append(oldPrice).append(" ").append(oldPriceSign)
                .append("\nactualPrice: ").append(actualPrice).append(" ").append(actualPriceSign)
                .append("\nadditionalPrice: ").append(additionalPrice)
                .append("\nshortDescription: ").append(shortDescription)
                .append("\nreference")
                .append("=============================================")
                .toString();
    }
}
