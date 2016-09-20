package com.example.alink.test;

import android.app.Activity;
import android.content.Intent;
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
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableR);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("Name");
        final String time = intent.getStringExtra("TimeString");

        realm=Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Base base = realm.createObject(Base.class);
                base.setName("Anna");
                base.setTime("00:39");
                base.setName(name);
                base.setTime(time);
            }
        });


        RealmResults<Base> realmResults= realm.where(Base.class).findAllSorted("timeUser");
        int k = realmResults.size();
        realm.copyToRealm(realmResults);

        //Base b = realm.where(Base.class).findFirst();
        //realm.copyToRealm(b);
        //RealmResult<Base> base = realm.where(Base.class).findAllSorted("time", Sort.ASCENDING);

        for (int i=0;i<k;i++){
            tableLayout.addView(rowMake(realmResults.get(0).toString(),realmResults.get(1).toString(),Color.GRAY));

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

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
