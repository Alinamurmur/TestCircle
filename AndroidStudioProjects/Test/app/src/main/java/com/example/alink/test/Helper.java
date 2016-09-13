package com.example.alink.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Helper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DB_NAME = "reco";
    private static final int DB_VERSION = 1;

    Helper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECORDS (" + BaseColumns._ID
                + " integer primary key autoincrement, "
                    +"NAME TEXT, "
                    +"TIME NUMERIC);");
        insertTime(db,"Max","00:30");
        insertTime(db,"Alina","10:44");
        insertTime(db,"Bill","01:20");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertTime (SQLiteDatabase db, String name, String time){
        ContentValues timeValues = new ContentValues();
        timeValues.put("NAME",name);
        timeValues.put("TIME",time);
        db.insert("RECORDS",null,timeValues);
    }

    public static void updateTable(SQLiteDatabase db,String name, String time){
        ContentValues timeUpdateValues = new ContentValues();
        timeUpdateValues.put("TIME",time);
        db.update("RECORDS", timeUpdateValues, "NAME=?", new String[]{name});
    }
}
