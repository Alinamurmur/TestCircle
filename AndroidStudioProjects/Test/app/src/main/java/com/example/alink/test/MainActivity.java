package com.example.alink.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    FrameLayout container;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout)findViewById(R.id.frame);

        Menu menu = new Menu();
        FragmentTransaction ft = getFragmentManager()
                .beginTransaction();
        ft.add(R.id.frame,menu);
       // ft.addToBackStack(null);
        ft.commit();

    }
}

