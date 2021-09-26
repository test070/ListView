package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputActivity extends AppCompatActivity {
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //テキストエリアのインスタンスを取得
        EditText inputTitle = findViewById(R.id.editTextInputTitle);
        EditText inputDate = findViewById(R.id.editTextInputDate);
        EditText inputContents = findViewById(R.id.editTextInputContents);

        Intent intent = getIntent();
        ListItemEntity item = (ListItemEntity)intent.getSerializableExtra(MainActivity.EXTRA_DATA);
        index = item.getIndex();

        inputTitle.setText(item.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
        inputDate.setText(dateFormat.format(item.getDate()));
        inputContents.setText(item.getContents());

        //保存ボタンのアクション設定
        Button btnExecution = findViewById(R.id.buttonExecution);
        btnExecution.setOnClickListener(new View.OnClickListener() {
            @Override //保存ボタン押下処理
            public void onClick(View v) {
                //テキストエリアのインスタンスを取得
                EditText inputTitle1 = findViewById(R.id.editTextInputTitle);
                EditText inputDate1 = findViewById(R.id.editTextInputDate);
                EditText inputContents1 = findViewById(R.id.editTextInputContents);
                //戻り値用のListItemEntityデータを作成
                String str = inputDate.getText().toString();
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy/MM/dd").parse(inputDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ListItemEntity retItem = new ListItemEntity(index, date, inputTitle.getText().toString(), inputContents.getText().toString());
                //ListItemEntityデータをインテントにセット
                Intent retIntent = getIntent();
                retIntent.putExtra(MainActivity.RETURN_DATA, retItem);
                //戻る
                setResult(RESULT_OK, retIntent);
                finish();
            }
        });

        //キャンセルボタンのアクション設定
        Button btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override //キャンセルボタン押下処理
            public void onClick(View v) {
                finish();
            }
        });

        //日時欄の変更
        inputDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //Calendarインスタンスを取得
                final Calendar date = Calendar.getInstance();

                //DatePickerDialogインスタンスを取得
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InputActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //setした日付を取得して表示
                                inputDate.setText(String.format("%d/%02d/%02d", year, month+1, dayOfMonth));
                            }
                        },
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DATE)
                );

                //dialogを表示
                datePickerDialog.show();
            }
        });
    }
}