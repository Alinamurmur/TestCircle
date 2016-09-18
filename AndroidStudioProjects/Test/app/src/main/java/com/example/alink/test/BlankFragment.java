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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class BlankFragment extends Fragment {
    private Chronometer chronometer;
    Button  pause;
    AlertDialog.Builder ald, paus,nam;
    long timeWhenStopped = 0;
    String nameUser,timeUser;
    int sec = 0;
    FloatingActionButton fbut;
    Timer t;
    //RelativeLayout relativeLayout;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);


        final TextView secView = (TextView)rootView.findViewById(R.id.sec);

        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer);

        nam = new AlertDialog.Builder(getActivity());
        nam.setMessage(R.string.priv);
        final EditText inputName = new EditText(getActivity());
        inputName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()== KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    if (inputName.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Нужно ввести имя",Toast.LENGTH_LONG).show();
                    }
                    else {nameUser = inputName.getText().toString();
                        chronometer.start();
                        t = new Timer();
                        t.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                sec++;
                                //secView.setText(sec);
                            }
                        }, 1000, 1000);}
                    return true;
                }
                return false;
            }
        });
        nam.setView(inputName);
        nam.setPositiveButton(R.string.priv2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (inputName.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Нужно ввести имя", Toast.LENGTH_LONG).show();
                } else {
                    nameUser = inputName.getText().toString();
                    chronometer.start();
                    t = new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            sec++;
                            //secView.setText(sec);
                        }
                    }, 1000, 1000);

                }
            }
        });
        nam.show();

        ald = new AlertDialog.Builder(getActivity());
        ald.setMessage(R.string.message);
        ald.setPositiveButton(R.string.menu, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                //TODO vernutsia v menu
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
                 final Intent intentR = new Intent(getActivity(),Records.class);
                intentR.putExtra("Name",nameUser);
                intentR.putExtra("TimeString",timeUser);
                 startActivity(intentR);
            }
        });
        ald.setCancelable(true);
        ald.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getActivity(),"Вы ничего не выбрали :(",Toast.LENGTH_LONG).show();
            }
        });

        paus = new AlertDialog.Builder(getActivity());
        paus.setMessage("Игра приостановлена");
        paus.setCancelable(false);
        paus.setPositiveButton("Продолжить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        sec++;
                    }
                }, 1000, 1000);
            }
        });

        pause = (Button) rootView.findViewById(R.id.button5);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop(); t.cancel();
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
            fbut = new FloatingActionButton(getActivity());
            fbut.setImageResource(img[numb.get(i)]);
            fbut.setId(numb.get(i) + 1);
            fbut.setOnClickListener(new MyListener());
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

    public class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId()<16){
                 
                fbut.setVisibility(View.INVISIBLE);
            } else {
                chronometer.stop(); t.cancel();
                timeUser = chronometer.getText().toString();
                ald.show();
            }
        }
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

**/



