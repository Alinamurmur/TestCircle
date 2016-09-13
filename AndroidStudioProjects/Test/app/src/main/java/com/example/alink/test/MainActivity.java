package com.example.alink.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String nameUser = intent.getStringExtra("nameUser");


        Button but1 = (Button)findViewById(R.id.button);
        Button but2 = (Button)findViewById(R.id.button2);
        Button but3 = (Button)findViewById(R.id.button3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BlankFragment fragment = new BlankFragment();
                fragment.setNameUser(nameUser);
                fragmentTransaction.add(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intR = new Intent(MainActivity.this,Records.class);
                startActivity(intR);
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intRe = new Intent(MainActivity.this,RecPlay.class);
                //intRe.putExtra("User",nameUser);
               // startActivity(intRe);
                // Intent intS = new Intent(this,Settings.class);
                // intS.putExtra("nameUser",nameUser);
                //startActivity(intS);
            }
        });
    }
}
