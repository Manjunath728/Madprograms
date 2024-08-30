package com.example.madlabprograms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Lab3_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab31);

        TextView received_name;
        TextView received_age;
        received_name = findViewById(R.id.name_receive);
        received_age = findViewById(R.id.age_receive);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");

        received_name.setText(name);
        received_age.setText(age);
    }
}