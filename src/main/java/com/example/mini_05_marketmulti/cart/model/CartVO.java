package com.example.mini_05_marketmulti.cart.model;

public class CartVO {
    private int cart_no;
    private String user_id, product_id;
    private int qty, price;
    private String thumbnail, product_name;

    public int getCart_no() {
        return cart_no;
    }

    public void setCart_no(int cart_no) {
        this.cart_no = cart_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cart_no=" + cart_no +
                ", user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", thumbnail='" + thumbnail + '\'' +
                ", product_name='" + product_name + '\'' +
                '}';
    }
}
