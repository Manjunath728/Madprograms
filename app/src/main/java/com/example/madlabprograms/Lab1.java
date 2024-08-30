package com.example.madlabprograms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class Lab1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);

        ImageButton button = findViewById(R.id.imageButton);
        Switch state = findViewById(R.id.switch1);
        ConstraintLayout layout = findViewById(R.id.Layout);

        CalendarView cal = new CalendarView(this);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        );

        layout.addView(cal);

        button.setEnabled(false);
        state.setOnCheckedChangeListener((buttonView, isChecked) ->  {
                if(isChecked){
                    button.setEnabled(true);
                    state.setText("Enabled");
                }
                else {
                    button.setEnabled(false);
                    state.setText("Disabled");
                }
        });

        button.setOnClickListener(v->{
            Toast.makeText(this, "Button is Clicked", Toast.LENGTH_SHORT).show();
        });
    }
}