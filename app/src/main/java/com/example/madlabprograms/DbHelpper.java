package com.example.madlabprograms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLClientInfoException;

public class DbHelpper extends SQLiteOpenHelper {
    public static final String DBName="register.db";
    public DbHelpper(Lab5 context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (username TEXT primary key , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public boolean insertData(String username, String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        long result=myDB.insert("users",null,cv);
        myDB.close();
        if (result==-1){
            return false;
        }else return true;
    }
    public boolean validateData(String username, String password){
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor c= myDB.rawQuery( "select  username,password from users where username=? and password=?", new String[]{username,password});
        if (c.getCount()>0){
            return true;
        }else return false;
    }
}
