package com.example.moviedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dbHandler extends SQLiteOpenHelper {
    public dbHandler(@Nullable Context context) {
        super(context, "userdb", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create ="CREATE TABLE IF NOT EXISTS user (uId INTEGER PRIMARY KEY, phone_no TEXT, password TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean check_id(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("oppsss","before query");
        String read = "SELECT * FROM user WHERE phone_no = "+id;

        Log.d("oppsss","after query");
        Cursor cursor = db.rawQuery(read, null);
        if(cursor.getCount()>0){
            cursor.close();
            Log.d("oppsss","returning false");
            return false;
        }
        else {
            cursor.close();
            Log.d("oppsss","returning true");
            return true;
        }
    }
    public void Add_user(String id, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("impimp", id);
        Log.d("impimp", pass);
        values.put("phone_no", id);
        values.put("password", pass);
        db.insert("user", null,values);
        db.close();
    }

    public boolean read_user(String id, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String read2 = "SELECT * FROM user WHERE phone_no = "+id+" and password = "+pass;
        Log.d("newimp", "before query run"+read2);
        Cursor cursor = db.rawQuery(read2, null);
        Log.d("newimp", "after query run");
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

}
