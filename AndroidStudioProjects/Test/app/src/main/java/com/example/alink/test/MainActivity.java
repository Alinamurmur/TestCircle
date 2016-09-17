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
    boolean play=false;
    FrameLayout container;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout)findViewById(R.id.frame);

        Menu menu = new Menu();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame,menu);
        ft.addToBackStack(null);
        ft.commit();





/**
        Intent intent = getIntent();
        final String nameUser = intent.getStringExtra("nameUser");


        Button but1 = (Button)findViewById(R.id.button);
        Button but2 = (Button)findViewById(R.id.button2);
        Button but3 = (Button)findViewById(R.id.button3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play=true;
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BlankFragment fragment = new BlankFragment();
                fragment.setNameUser(nameUser);
                fragment.setPlay(play);
                fragmentTransaction.add(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intR = new Intent(MainActivity.this,Records.class);
                intR.putExtra("Play",play);
                startActivity(intR);
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intRe = new Intent(MainActivity.this,RecPlay.class);

               // startActivity(intRe);
                // Intent intS = new Intent(this,Settings.class);
                // intS.putExtra("nameUser",nameUser);
                //startActivity(intS);
            }
        });**/
    }
}

