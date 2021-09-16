package com.example.myapplication;

import java.util.Date;

public class ListItem {
    private Date date;
    private String title;
    private String detail;

    ListItem(Date argDate, String argtitle, String argDetail){
        date = argDate;
        title = argtitle;
        detail = argDetail;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
