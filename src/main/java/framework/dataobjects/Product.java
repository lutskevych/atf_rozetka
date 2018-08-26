package framework.dataobjects;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Product {
    private String priceActiveElement;
    private String titlePromo;
    private String title;
    private double oldPrice;
    private double actualPrice;
    private String reference;

    private Product(final Builder builder) {
        priceActiveElement = builder.priceActiveElement;
        titlePromo = builder.titlePromo;
        title = builder.title;
        oldPrice = builder.oldPrice;
        actualPrice = builder.actualPrice;
        reference = builder.reference;
    }

    public static class Builder {
        private String priceActiveElement;
        private String titlePromo;
        private String title;
        private double oldPrice;
        private double actualPrice;
        private String reference;

        public Builder setPriceActiveElement(String priceActiveElement) {
            this.priceActiveElement = priceActiveElement;
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

        public Builder setOldPrice(double oldPrice) {
            this.oldPrice = oldPrice;
            return this;
        }

        public Builder setActualPrice(double actualPrice) {
            this.actualPrice = actualPrice;
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
}
