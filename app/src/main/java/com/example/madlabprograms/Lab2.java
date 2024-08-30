package com.example.madlabprograms;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

public class Lab2 extends AppCompatActivity {

    private ProgressBar pbar;
    private Button load;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        pbar = findViewById(R.id.progressBar);
        load = findViewById(R.id.startButton);
        messageTextView = findViewById(R.id.messageTextView);

        load.setOnClickListener(v->{
            load.setEnabled(false);
            pbar.setVisibility(View.VISIBLE);
            long min=10*1000;
            new CountDownTimer(min,1000){
                @Override
                public void onTick(long l){
                    long a=min-l;
                    float total=((float) a / (float)min)*100;
                    pbar.setProgress((int)total,true);
                }
                @Override
                public void onFinish(){
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            Lab2.this
                    );
                    builder.setTitle("Loaded !");
                    builder.setMessage("loaded successfully");
                    builder.setPositiveButton(
                            "OK",(dialog,which)-> dialog.cancel()
                    );
                    builder.setNegativeButton("exit",((dialog, which) -> finish()));
                    AlertDialog alert=builder.create();
                    alert.show();
                    load.setEnabled(true);
                }

            }.start();
        });
    }

}
