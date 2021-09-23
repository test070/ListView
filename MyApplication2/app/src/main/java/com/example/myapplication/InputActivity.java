package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
//        inputDate.setText(item.getDate());
        TextView inputContents = findViewById(R.id.editTextInputContents);
        inputContents.setText(item.getContents());

    }
}