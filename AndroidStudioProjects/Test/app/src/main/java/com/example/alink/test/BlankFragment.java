package com.example.alink.test;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class BlankFragment extends Fragment {
    private Chronometer chronometer;
    Button stop, pause;
    AlertDialog.Builder ald, paus;
    long timeWhenStopped = 0;
    String nameUser;
    int sec = 0;
    //RelativeLayout relativeLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                sec = sec + 1;
            }
        }, 1000, 1000);
        t.cancel();

        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer);
        chronometer.start();

        // final RecPlay rp = new RecPlay();

/**
 ald = new AlertDialog.Builder(getActivity());
 ald.setMessage(R.string.message);
 ald.setPositiveButton(R.string.menu, new DialogInterface.OnClickListener() {
@Override public void onClick(DialogInterface dialog, int which) {
Intent intent = new Intent(getActivity(), MainActivity.class);
startActivity(intent);
getActivity.finish();
}
});
 ald.setNeutralButton(R.string.b1, new DialogInterface.OnClickListener() {
 public void onClick(DialogInterface dialog, int which) {
 FragmentManager fragmentManager = getFragmentManager();
 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
 BlankFragment fragment = new BlankFragment();
 fragmentTransaction.add(R.id.fragment_container,fragment);
 fragmentTransaction.addToBackStack(null);
 fragmentTransaction.commit();
 timeWhenStopped = 0;
 }
 });
 ald.setNegativeButton(R.string.b2, new DialogInterface.OnClickListener() {
@Override public void onClick(DialogInterface dialog, int which) {
//rp.prov(getActivity(),);
final Intent intentR = new Intent(getActivity(),Records.class);
intentR.putExtra("Play",play);
startActivity(intentR);
}
});
 ald.setCancelable(true);
 ald.setOnCancelListener(new DialogInterface.OnCancelListener() {
 public void onCancel(DialogInterface dialog) {
 Toast.makeText(getActivity(),"Вы ничего не выбрали :(",Toast.LENGTH_LONG).show();
 }
 });
 stop = (Button) rootView.findViewById(R.id.button4);
 stop.setOnClickListener(new View.OnClickListener() {
@Override public void onClick(View v) {
chronometer.stop();
String chronoText = chronometer.getText().toString();
//  rp.setTimeUser(chronoText);
//  rp.setNameUser(nameUser);
// rp.ProverkaAndUpdate(getActivity());

ald.show();
}
});
 **/
        paus = new AlertDialog.Builder(getActivity());
        paus.setMessage("Игра приостановлена");
        paus.setCancelable(false);
        paus.setPositiveButton("Продолжить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
            }
        });

        pause = (Button) rootView.findViewById(R.id.button5);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                paus.show();
            }
        });


        // relativeLayout = (RelativeLayout) rootView.findViewById(R.id.cir);
        // DisplayMetrics displaymetrics = getResources().getDisplayMetrics();

        GridLayout gridLayout = (GridLayout) rootView.findViewById(R.id.grid);
        gridLayout.setColumnCount(4);
        int[] img = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight,
                R.drawable.nine, R.drawable.ten, R.drawable.eleven, R.drawable.twelve,
                R.drawable.thirteen, R.drawable.fourteen, R.drawable.fifteen, R.drawable.sixteen};

        ArrayList<Integer> numb = getRandomNumber();

        for (int i = 0; i < 16; i++) {
            FloatingActionButton fbut = new FloatingActionButton(getActivity());
            fbut.setImageResource(img[numb.get(i)]);
            fbut.setId(numb.get(i) + 1);
            gridLayout.addView(fbut);

        }
        return rootView;
    }

    private ArrayList<Integer> getRandomNumber() {
        ArrayList<Integer> numbGen = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Random random = new Random();
            int iNumb = random.nextInt(16);
            if (!numbGen.contains(iNumb)) {
                numbGen.add(iNumb);
            } else {
                i--;
            }
        }
        return numbGen;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
/**

        for (int i=0;i<3;i++){
            FloatingActionButton fbut = new FloatingActionButton(getActivity());
            fbut.setImageResource(img[i]);

            int xbut = rX(displaymetrics.widthPixels);
            int ybut = rY(displaymetrics.heightPixels);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)fbut.getLayoutParams();
            params.setMargins(xbut,ybut,0,0);
            //fbut.setX(xbut);
            //fbut.setY(ybut);
            fbut.setId(i+1);
            relativeLayout.addView(fbut);
        }





