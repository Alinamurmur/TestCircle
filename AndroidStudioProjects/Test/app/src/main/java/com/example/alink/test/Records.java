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

import java.util.Random;

import io.realm.Realm;

import io.realm.RealmResults;
import io.realm.Sort;

public class Records extends Activity {
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
        RealmResults<Base> realmResults= realm.where(Base.class).findAllSorted("timeUser", Sort.ASCENDING);
        for (Base base:realmResults){
            tableLayout.addView(rowMake(base.getNameUser(),base.getTimeUser()));
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //del();
        realm.close();
    }
   // int colo [] = {R.color.t1,R.color.t2,R.color.t3,R.color.t4};
   // int colors[] = {Color.GRAY,Color.GREEN,Color.WHITE,Color.YELLOW};
int color=0;
    private TableRow rowMake (String name, String time){
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
        if((color%2)==0){
            row.setBackgroundColor(Color.GRAY);
        } else {
            row.setBackgroundColor(Color.WHITE);
        }
        color++;
        return row;
    }
    public void del (){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(Base.class);
        realm.commitTransaction();
    }

}
