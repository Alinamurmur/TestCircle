package com.example.alink.test;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class BlankFragment extends Fragment {
    private Chronometer chronometer;
    Button stop,pause;
    AlertDialog.Builder ald,paus;
    long timeWhenStopped = 0;
    String nameUser;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer);
        chronometer.start();

        ald = new AlertDialog.Builder(getActivity());
        ald.setMessage(R.string.message);
        ald.setPositiveButton(R.string.menu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Intent intentR = new Intent(getActivity(),Records.class);
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
            @Override
            public void onClick(View v) {
                chronometer.stop();
                String chronoText = chronometer.getText().toString();
                RecPlay rp = new RecPlay();
                rp.setTimeUser(chronoText);
                rp.setNameUser(nameUser);
                rp.ProverkaAndUpdate();
                ald.show();
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
            }
        });

        pause = (Button)rootView.findViewById(R.id.button5);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                paus.show();
            }
        });

        RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.cir);
        relativeLayout.addView(new Circle(getActivity()));
        return rootView;
    }

    public void setNameUser(String name) {
        this.nameUser = name;
    }
}


