package com.example.alink.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class RecPlay {
    String nameUser, timeUser;
    Context context;


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

    public void ProverkaAndUpdate() {
        int minUser = getMin(timeUser);
        int secUser = getSec(timeUser);
        boolean equ = false;
        SQLiteOpenHelper dbHelper = new Helper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("RECORDS", new String[]{"NAME", "TIME"},
                null, null, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String nameBase = cursor.getString(0);
                    if (nameBase.equals(nameUser)) {
                        equ = true;//чтобы потом знать, что не было ни одного совпадения
                        String timeBase = cursor.getString(1);
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
                cursor.close();
                if (!equ) {
                    Helper.insertTime(db, nameUser, timeUser);
                }

            } else {
                Helper.insertTime(db, nameUser, timeUser);
            }
            // cursor.close();
            db.close();
        }
        catch (Exception e){
            Toast.makeText(context,"Ох шит, печеньки закончились",Toast.LENGTH_LONG).show();
        }
    }
}