package com.example.mini_05_marketmulti.bbs.model;

import java.sql.Date;

public class BbsVO {
    private int bbs_no;
    private String title, content, writer;
    private Date bdate;


    public int getBbs_no() {
        return bbs_no;
    }

    public void setBbs_no(int bbs_no) {
        this.bbs_no = bbs_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }
    @Override
    public String toString() {
        return "BbsVO{" +
                "bbs_no=" + bbs_no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", bdate=" + bdate +
                '}';
    }

}