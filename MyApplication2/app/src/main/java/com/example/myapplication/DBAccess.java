package com.example.myapplication;

import java.util.ArrayList;
import java.util.Date;

public class DBAccess {
    private final ArrayList<ListItemEntity> items = new ArrayList<ListItemEntity>();

    DBAccess(){
        //リストを作成
        for(int i=0; i<5; i++){
            Date dateVal = new Date();
            String titleText = "予定　" + Integer.valueOf(i).toString();
            String detailText = "詳細　" + Integer.valueOf(i).toString();
            ListItemEntity item = new ListItemEntity(i, dateVal, titleText, detailText);
            items.add(item);
        }
    }

    public ArrayList<ListItemEntity> getList(){
        return items;
    }

    public int getListSize() {
        return items.size();
    }

    public ListItemEntity getItemByIndex(int index){
        return items.get(index);
    }

    public void updateListItem(int index, ListItemEntity item){
        items.set(index, item);
    }

    public void addListItem(ListItemEntity item){
         items.add(item);
    }

    public void removeListItem(int index){
        items.remove(index);
    }
}
