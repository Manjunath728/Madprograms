package com.example.madlabprograms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView displayName=findViewById(R.id.displayName);
        Intent i=getIntent();
        String name=i.getStringExtra("username");
        displayName.setText(name);

    }

}