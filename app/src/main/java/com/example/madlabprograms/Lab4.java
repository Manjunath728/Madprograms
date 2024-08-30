package com.example.madlabprograms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lab4 extends AppCompatActivity {

    Button addButton;
    Button removeButton;
    Button replaceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);
        replaceButton = findViewById(R.id.replaceButton);

        MyFrag f1 = new MyFrag();
        MyFrag_2 f2 = new MyFrag_2();
        replaceButton.setEnabled(false);
        addButton.setOnClickListener(v->{
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.myframe,f1)
                        .commit();
                addButton.setEnabled(false);
            replaceButton.setEnabled(true);
        });

        removeButton.setOnClickListener(v->{
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(f1)
                        .remove(f2)
                        .commit();
                addButton.setEnabled(true);
                replaceButton.setEnabled(false);
        });

        replaceButton.setOnClickListener(v->{
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.myframe, f2)
                    .commit();
        });
    }
}