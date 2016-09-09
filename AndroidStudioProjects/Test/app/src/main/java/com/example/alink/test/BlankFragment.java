package com.example.alink.test;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    Button stop;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        chronometer =(Chronometer)rootView.findViewById(R.id.chronometer);
        //chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        stop = (Button)rootView.findViewById(R.id.button4);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

    RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.cir);
    relativeLayout.addView(new Circle(getActivity()));
    return rootView;
}


}


