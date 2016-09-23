package com.example.alink.test;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Menu extends Fragment{

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_menu, container, false);

        Button but1 = (Button) myview.findViewById(R.id.button);
        Button but2 = (Button) myview.findViewById(R.id.button2);
        Button but3 = (Button) myview.findViewById(R.id.button3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new BlankFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intR = new Intent(getActivity(), Records.class);
                getActivity().startActivity(intR);
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),About.class);
                startActivity(intent);

            }
        });

        return myview;}

}
