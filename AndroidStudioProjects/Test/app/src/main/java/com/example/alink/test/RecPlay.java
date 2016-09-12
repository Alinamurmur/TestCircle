package com.example.alink.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecPlay extends Activity {
    String nameUser,timeUser ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_play);
        Intent intent = getIntent();
        nameUser = intent.getStringExtra("User");
        timeUser = intent.getStringExtra("Time");

        TextView na = (TextView)findViewById(R.id.na);
        na.setText(nameUser);
        TextView ti = (TextView)findViewById(R.id.ti);
        ti.setText(timeUser);

    }
}
