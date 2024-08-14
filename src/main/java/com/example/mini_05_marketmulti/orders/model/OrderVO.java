package com.example.mini_05_marketmulti.orders.model;

import java.sql.Date;

public class OrderVO {
    private long order_no;
    private String user_id,product_id, shipping_addr,product_name;
    private Date odate;
    private String status;
    private int qty, price;

    public long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(long order_no) {
        this.order_no = order_no;
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

    public String getShipping_addr() {
        return shipping_addr;
    }

    public void setShipping_addr(String shipping_addr) {
        this.shipping_addr = shipping_addr;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "OrderVO{" +
                "order_no=" + order_no +
                ", user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", shipping_addr='" + shipping_addr + '\'' +
                ", product_name='" + product_name + '\'' +
                ", odate=" + odate +
                ", status='" + status + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
