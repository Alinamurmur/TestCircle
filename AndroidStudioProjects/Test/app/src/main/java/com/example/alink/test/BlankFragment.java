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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;


public class BlankFragment extends Fragment {
    private Chronometer chronometer;
    Button  pause;
    AlertDialog.Builder ald, paus,nam;
    long timeWhenStopped = 0;
    String nameUser,timeUser;
    int sec = 0;
    FloatingActionButton fbut;
    Timer t;
public int k=1;
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer);

        nam = new AlertDialog.Builder(getActivity());
        nam.setMessage(R.string.priv);
        final EditText inputName = new EditText(getActivity());
      /**  inputName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()== KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    if (inputName.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Нужно ввести имя",Toast.LENGTH_LONG).show();
                    }
                    else {nameUser = inputName.getText().toString();
                        t = new Timer();
                        t.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                sec++;
                                //secView.setText(sec);
                            }
                        }, 1000, 1000);
                        chronometer.start();
                    }
                    return true;
                }
                return false;
            }
        });**/
        nam.setView(inputName);
        nam.setPositiveButton(R.string.priv2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (inputName.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Нужно ввести имя", Toast.LENGTH_LONG).show();

                } else {
                    nameUser = inputName.getText().toString();
                    t = new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            sec++;
                            //secView.setText(sec);
                        }
                    }, 1000, 1000);
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }
            }
        });
        nam.setCancelable(false);
        nam.show();

        ald = new AlertDialog.Builder(getActivity());
        ald.setMessage(R.string.message);
        ald.setPositiveButton(R.string.menu, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Menu fragmentMenu = new Menu();
                fragmentTransaction.replace(R.id.fragment_container,fragmentMenu);
                fragmentTransaction.commit();
            }
        });
        ald.setNeutralButton(R.string.b1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BlankFragment fragment = new BlankFragment();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                timeWhenStopped = 0;
            }
        });
        ald.setNegativeButton(R.string.b2, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                final Intent intentR = new Intent(getActivity(),Records.class);
                Menu menu = new Menu();
                FragmentTransaction ft = getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,menu);
                ft.commit();
                startActivity(intentR);
            }
        });
        ald.setCancelable(false);
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
               // t.schedule(new TimerTask() {
                 //   @Override
                   // public void run() {
                     //   sec++;
                   // }
                //}, 1000, 1000);
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

        GridLayout gridLayout = (GridLayout) rootView.findViewById(R.id.grid);
        gridLayout.setColumnCount(4);
        int[] img = {R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4,
                R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8,
                R.drawable.n9, R.drawable.n10, R.drawable.n11, R.drawable.n12,
                R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16};

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
            if (v.getId() < 16) {
                int Idb = v.getId();
                FloatingActionButton b = (FloatingActionButton) v.findViewById(Idb);
                if (Idb == k) {
                    b.setVisibility(View.INVISIBLE);
                    k++;
                }

            } else {
                if (k == 16) {
                chronometer.stop();
                t.cancel();
                timeUser = chronometer.getText().toString();
                ald.show();
                Realm realm;
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Base base = realm.createObject(Base.class);
                        base.setName(nameUser);
                        base.setTime(timeUser);
                    }
                });

            }
              else {Toast.makeText(getActivity(),"Так не честно!",Toast.LENGTH_SHORT).show();
                }
        }
        }
    }
}



