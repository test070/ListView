package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListItemEntity> items= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //リストを作成
        for(int i=0; i<5; i++){
            Date dateVal = new Date();
            String titleText = "予定　" + Integer.valueOf(i).toString();
            String detailText = "詳細　" + Integer.valueOf(i).toString();
            ListItemEntity item = new ListItemEntity(dateVal, titleText, detailText);
            items.add(item);
        }

        // ListViewのインスタンスを生成
        ListView listView = findViewById(R.id.list_view);

        final BaseAdapter adapter = new ListAdapter(this.getApplicationContext(), R.layout.list_item, items);

        //アダプターを追加して、リストビューに要素を追加、表示させる
        listView.setAdapter(adapter);

        //クリック処理
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListItemEntity item = items.get(position);
                        Toast.makeText(MainActivity.this,item.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}