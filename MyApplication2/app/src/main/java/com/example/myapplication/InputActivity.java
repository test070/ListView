package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Intent intent = getIntent();
        ListItemEntity item = (ListItemEntity)intent.getSerializableExtra(MainActivity.EXTRA_DATA);

        TextView inputTitle = findViewById(R.id.editTextInputTitle);
        inputTitle.setText(item.getTitle());
        TextView inputDate = findViewById(R.id.editTextInputDate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
        inputDate.setText(dateFormat.format(item.getDate()));
        TextView inputContents = findViewById(R.id.editTextInputContents);
        inputContents.setText(item.getContents());

        Button btnExecution = findViewById(R.id.buttonExecution);
        btnExecution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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