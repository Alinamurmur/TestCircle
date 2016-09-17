package com.example.alink.test;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RecPlay {
    String nameUser, timeUser;


    public void setNameUser(String name) {
        this.nameUser = name;
    }

    public void setTimeUser(String time) {
        this.timeUser = time;
    }

    private int getMin(String time) {
        String array[] = time.split(":");
        int min = Integer.parseInt(array[0]);
        return min;
    }

    private int getSec(String time) {
        String array[] = time.split(":");
        int sec = Integer.parseInt(array[2]);
        return sec;
    }

    public void prov(Context context,SQLiteDatabase db){

        Cursor cursor;
        cursor = db.query("RECORDS",new String[]{"NAME", "TIME"},
                null,null,null,null,null);
        cursor.moveToFirst();
        String nameBase = cursor.getString(0);
        Helper.updateTable(db, nameBase, timeUser);
        cursor.close();
        db.close();
    }

    public void ProverkaAndUpdate(Context context) {
        int minUser = getMin(timeUser);
        int secUser = getSec(timeUser);
        boolean equ = false;
        SQLiteOpenHelper dbHelper = new Helper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("RECORDS",new String[]{"NAME", "TIME"},
                null,null,null,null,null);
        String nameBase,timeBase;
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    nameBase = cursor.getString(0);
                    if (nameBase.equals(nameUser)) {
                        equ = true;//чтобы потом знать, что не было ни одного совпадения
                        timeBase = cursor.getString(1);
                        int minBase = getMin(timeBase);
                        int secBase = getSec(timeBase);
                        if (minUser < minBase) {
                            Helper.updateTable(db, nameBase, timeUser);
                        } else if (minUser == minBase) {
                            if (secUser < secBase) {
                                Helper.updateTable(db, nameBase, timeUser);
                            }
                        }
                        break;//мы нашли этого пользователя, обновили данные, и больше тут делать нечего
                    }
                    cursor.moveToNext();
                }
                if (!equ) {
                    Helper.insertTime(db, nameUser, timeUser);
                }
        cursor.close();
        db.close();
        }

    }
