package com.example.alink.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper{

    private static final String DB_NAME = "reco";
    private static final int DB_VERSION = 1;

    Helper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECORDS ("
                    +"NAME TEXT, "
                    +"TIME NUMERIC);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertTime (SQLiteDatabase db, String name, long time){
        ContentValues timeValues = new ContentValues();
        timeValues.put("NAME",name);
        timeValues.put("TIME",time);
        db.insert("RECORDS",null,timeValues);
    }
}
