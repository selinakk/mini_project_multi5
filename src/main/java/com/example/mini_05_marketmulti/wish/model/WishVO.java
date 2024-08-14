package com.example.mini_05_marketmulti.wish.model;

public class WishVO {
    private int wish_no;
    private String user_id, product_id, thumbnail, product_name;;

    public int getWish_no() {
        return wish_no;
    }

    public void setWish_no(int wish_no) {
        this.wish_no = wish_no;
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
        return "WishVO{" +
                "wish_no=" + wish_no +
                ", user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", product_name='" + product_name + '\'' +
                '}';
    }
}
