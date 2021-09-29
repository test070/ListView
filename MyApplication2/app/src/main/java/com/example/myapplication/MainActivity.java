package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA
            = "com.example.DATA";
    public static final String RETURN_DATA
            = "com.example.RETURN_DATA";
    public static final String INDEX_NUMBER
            = "com.example.INDEX_NUMBER";
    public static final String ADD_ITEM_FLAG = "com.example.ADD_FLAG";//新規追加の場合のインデックス
    public static final String REMOVE_ITEM_FLAG = "com.example.REMOVE_FLAG";//削除の場合のインデックス
    public static final int ERROR_NUMBER = -11111;
    private final DBAccess dbAccess= new DBAccess();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewのインスタンスを生成
        ListView listView = findViewById(R.id.list_view);
        //ListViewにItemを詰める
        updateListView(listView, dbAccess.getList());

        //ListViewクリック処理
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //クリックした要素をitemsから取得する
                        ListItemEntity item = dbAccess.getItemByIndex(position);
                        //InputActivityのインテントを生成
                        Intent intent = new Intent(MainActivity.this, InputActivity.class);
                        intent.putExtra(INDEX_NUMBER, position);
                        intent.putExtra(EXTRA_DATA, item);
                        //インテントで画面遷移
                        launcher.launch(intent);

                    }
                }
        );

        //追加ボタン処理
        FloatingActionButton fab = findViewById(R.id.fabAddListItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新しいリスト要素を作成
                ListItemEntity item = new ListItemEntity(ADD_ITEM_FLAG, new Date(), "","");
                //InputActivityのインテントを生成
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                intent.putExtra(EXTRA_DATA, item);
                //インテントで画面遷移
                launcher.launch(intent);
            }
        });
    }

    //画面遷移の処理
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override //戻り値の処理
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent      resultData = result.getData();
                        if (resultData != null) {
                            int index = resultData.getIntExtra(INDEX_NUMBER, ERROR_NUMBER);
                            ListItemEntity item = (ListItemEntity)resultData.getSerializableExtra(RETURN_DATA);
                            if(item.getDbActionFlag().equals(ADD_ITEM_FLAG) && item != null){
                                //新規追加の場合
                                dbAccess.addListItem(item);
                            }else if(item.getDbActionFlag().equals(REMOVE_ITEM_FLAG) && index != ERROR_NUMBER){
                                //削除の場合
                                dbAccess.removeListItem(index);
                            }else if(item != null && index != ERROR_NUMBER){
                                //情報更新の場合
                                dbAccess.updateListItem(index, item);
                            }

                            //ListViewのインスタンスを生成
                            ListView listView = findViewById(R.id.list_view);
                            //ListView更新
                            updateListView(listView, dbAccess.getList());
                        }
                    }
                }
            }
    );

    //ListViewの情報を更新する
    private void updateListView(ListView listView, ArrayList<ListItemEntity> items){
        //アダプターのインスタンスを生成
        final BaseAdapter adapter = new ListViewAdapter(MainActivity.this.getApplicationContext(), R.layout.list_item, items);
        //アダプターを追加して、リストビューに要素を追加、表示させる
        listView.setAdapter(adapter);
    }
}