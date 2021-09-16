package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListItem> items= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //リストを作成
        for(int i=0; i<5; i++){
            Date dateVal = new Date();
            String titleText = "予定　" + Integer.valueOf(i).toString();
            String detailText = "詳細　" + Integer.valueOf(i).toString();
            ListItem item = new ListItem(dateVal, titleText, detailText);
            items.add(item);
        }

        // ListViewのインスタンスを生成
        ListView listView = findViewById(R.id.list_view);

        final BaseAdapter adapter = new ListAdapter(this.getApplicationContext(), R.layout.list_item, items);

        listView.setAdapter(adapter);

    }
}