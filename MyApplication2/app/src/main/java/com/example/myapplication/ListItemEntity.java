package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class ListItemEntity implements Serializable {
    private Date date;
    private String title;
    private String contents;
    private String dbActionFlag;

    ListItemEntity(String argDBActionFlag, Date argDate, String argtitle, String argContents){
        dbActionFlag = argDBActionFlag;
        date = argDate;
        title = argtitle;
        contents = argContents;
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

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setDbActionFlag(String dbActionFlag) {
        this.dbActionFlag = dbActionFlag;
    }

    public String getDbActionFlag() {
        return dbActionFlag;
    }
}
