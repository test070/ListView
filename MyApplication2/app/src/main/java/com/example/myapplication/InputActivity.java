package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //テキストエリアのインスタンスを取得
        TextView inputTitle = findViewById(R.id.editTextInputTitle);
        TextView inputDate = findViewById(R.id.editTextInputDate);
        TextView inputContents = findViewById(R.id.editTextInputContents);

        Intent intent = getIntent();
        ListItemEntity item = (ListItemEntity)intent.getSerializableExtra(MainActivity.EXTRA_DATA);

        inputTitle.setText(item.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
        inputDate.setText(dateFormat.format(item.getDate()));
        inputContents.setText(item.getContents());

        Button btnExecution = findViewById(R.id.buttonExecution);
        btnExecution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //テキストエリアのインスタンスを取得
                TextView inputTitle1 = findViewById(R.id.editTextInputTitle);
                TextView inputDate1 = findViewById(R.id.editTextInputDate);
                TextView inputContents1 = findViewById(R.id.editTextInputContents);
                //戻り値用のListItemEntityデータを作成
                String str = inputDate.getText().toString();
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss").parse(inputDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ListItemEntity retItem = new ListItemEntity(date, inputTitle.getText().toString(), inputContents.getText().toString());
                //ListItemEntityデータをインテントにセット
                Intent retIntent = getIntent();
                retIntent.putExtra(MainActivity.RETURN_DATA, retItem);
                //戻る
                setResult(RESULT_OK, retIntent);
                finish();
            }
        });

        Button btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}