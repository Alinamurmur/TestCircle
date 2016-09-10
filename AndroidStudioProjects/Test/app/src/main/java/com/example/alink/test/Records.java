package com.example.alink.test;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Records extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableR);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);
       // tableLayout.setBackgroundColor(Color.GRAY);

        TableRow rowNam1 = new TableRow(this);
        TextView name = new TextView(this);
        name.setText("Alina");
        name.setTypeface(Typeface.DEFAULT_BOLD);
        name.setGravity(Gravity.CENTER_HORIZONTAL);
        name.setTextSize(20);
        rowNam1.addView(name);
        TextView t1 = new TextView(this);
        t1.setText("0:30");
        t1.setGravity(Gravity.CENTER_HORIZONTAL);
        t1.setTextSize(20);
        rowNam1.addView(t1);

        TableRow rowNam2 = new TableRow(this);
        TextView name2 = new TextView(this);
        name2.setText("Max");
        name2.setTypeface(Typeface.DEFAULT_BOLD);
        name2.setGravity(Gravity.CENTER_HORIZONTAL);
        name2.setTextSize(20);
        rowNam2.addView(name2);
        TextView t2 = new TextView(this);
        t2.setText("0:20");
        t2.setGravity(Gravity.CENTER_HORIZONTAL);
        t2.setTextSize(20);
        rowNam2.setBackgroundColor(Color.GRAY);
        rowNam2.addView(t2);

        tableLayout.addView(rowNam1);
        tableLayout.addView(rowNam2);

       // setContentView(tableLayout);



    }
}
