package com.example.alink.test;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import io.realm.Realm;

import io.realm.RealmResults;
import io.realm.Sort;

public class Records extends Activity {
   // SQLiteDatabase db;
   // Cursor cursor;
    boolean play;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableR);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

       // RealmConfiguration realmConfiguration =new RealmConfiguration.Builder(this)
         //      .build();
       // Realm.deleteRealm(realmConfiguration);
       // realm = Realm.getInstance(realmConfiguration);
        realm=Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Base base = realm.createObject(Base.class);
                base.setName("Anna");
                base.setTime("00:39");
            }
        });


        Base b = realm.where(Base.class).findFirst();
        realm.copyToRealm(b);
        //RealmResult<Base> base = realm.where(Base.class).findAllSorted("time", Sort.ASCENDING);
        // = realm.where(Base.class).findFirst();
        tableLayout.addView(rowMake(b.getNameUser(),b.getTimeUser(),Color.GRAY));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    /**
        Intent intent =getIntent();
        play=intent.getBooleanExtra("Play",false);

        SQLiteOpenHelper dbHelper = new Helper(this);
        db = dbHelper.getWritableDatabase();

        if (play){

        }
        try {
            cursor = db.query("RECORDS",new String[]{"NAME", "TIME"},
                                null,null,null,null,"TIME");
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                tableLayout.addView(rowMake(cursor.getString(0),cursor.getString(1),Color.GREEN));
                cursor.moveToNext();
            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Ой как нехорошо вышло то", Toast.LENGTH_SHORT);
            toast.show();
        }
    }**/

    private TableRow rowMake (String name, String time,int color){
        TableRow row = new TableRow(this);
        TextView nameView = new TextView(this);
        nameView.setText(name);
        nameView.setTypeface(Typeface.DEFAULT_BOLD);
        nameView.setGravity(Gravity.CENTER_HORIZONTAL);
        nameView.setTextSize(20);
        row.addView(nameView);
        TextView timeView = new TextView(this);
        timeView.setText(time);
        timeView.setGravity(Gravity.CENTER_HORIZONTAL);
        timeView.setTextSize(20);
        row.addView(timeView);
        row.setBackgroundColor(color);
        return row;
    }
}
