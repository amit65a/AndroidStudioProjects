package com.example.kiitnoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database  extends SQLiteOpenHelper {
    public static final String DBNAME="kiitnoteApp.db";
    public Database(Context context) {
        super(context,"kiitnoteApp.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username text primary key,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MYDB, int i, int i1) {
        MYDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result= MyDB.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else{
            return false;
        }
    }

    public Boolean checkuserpassword(String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from users where username =? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
