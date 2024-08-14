package com.example.mini_05_marketmulti.review.model;

import java.sql.Date;

public class ReviewVO {
    private int review_no;
    private String product_id, user_id, content;
    private Date rdate;
    private int rating;

    public int getReview_no() {
        return review_no;
    }

    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "ReviewVO{" +
                "review_no=" + review_no +
                ", product_id='" + product_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                ", rdate=" + rdate +
                ", rating=" + rating +
                '}';
    }
}
