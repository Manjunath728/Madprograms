package com.example.madlabprograms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Lab3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);

        Button send_button;
        EditText send_text_name;
        EditText send_text_age;

        send_button = findViewById(R.id.send_button_id);
        send_text_name = findViewById(R.id.send_text_id_name);
        send_text_age = findViewById(R.id.send_text_id_age);

        send_button.setOnClickListener(v -> {
            String name = send_text_name.getText().toString();
            String age = send_text_age.getText().toString();
            Intent intent = new Intent(this,Lab3_1.class);

            intent.putExtra("name",name);
            intent.putExtra("age",age);

            startActivity(intent);

        });
    }
}