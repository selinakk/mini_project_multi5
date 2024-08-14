package com.example.mini_05_marketmulti.product.model;

public class ProductVO {
    private String product_id, product_name, mfr, thumbnail;
    private int price;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getMfr() {
        return mfr;
    }

    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", mfr='" + mfr + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                '}';
    }
}
