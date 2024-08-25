package com.example.simplereviewfilm.ListPhim;

public class userCMT {

    private String id,ten, cmt;

    public userCMT(){}

    public userCMT(String id, String ten, String cmt) {
        this.id = id;
        this.ten = ten;
        this.cmt = cmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}
