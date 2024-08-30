package com.example.madlabprograms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lab5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
        Button register=findViewById(R.id.register);
        Button login=findViewById(R.id.login);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        DbHelpper db= new DbHelpper(this);

        register.setOnClickListener(v->{
            String usernameValue=username.getText().toString();
            String passwordValue=password.getText().toString();
            if (usernameValue.isEmpty() || passwordValue.isEmpty()){
                Toast.makeText(this, "Username or Password is Blank", Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d("myTag",usernameValue);
                if (db.insertData(usernameValue,passwordValue)){
                    Toast.makeText(this, usernameValue+ "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, usernameValue+ "User Already Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(v->{
            String usernameValue=username.getText().toString();
            String passwordValue=password.getText().toString();
            if (db.validateData(usernameValue,passwordValue)){
                Toast.makeText(this, usernameValue+ "Logged In", Toast.LENGTH_SHORT).show();
                i.putExtra("username",usernameValue);
                startActivity(i);
            }
            else {
                Toast.makeText(this, usernameValue+ "Wrong credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }


}