package com.example.alink.test;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CircleButton extends View {
    int x,y;
    int[]img ={R.drawable.one,R.drawable.two,R.drawable.three};
   // int w,h;


    public CircleButton(Context context) {
        super(context);
    }

    public int rX(int w) {
        x = (int) (Math.random()*w + 40);
        return x;
    }
    public int rY(int h) {
        y = (int) (Math.random() * h + 40);
        return y;
    }


    public RelativeLayout circles(RelativeLayout relativeLayout, Context context){

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
      /**
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
**/
        FloatingActionButton fbut = new FloatingActionButton(context);
        fbut.setX(100);
        fbut.setY(400);
        relativeLayout.addView(fbut);

        ImageButton imb = new ImageButton(context);



      /**  for (int i=0;i<3;i++){
            FloatingActionButton fbut = new FloatingActionButton(context);
            fbut.setImageResource(img[i]);

            int xbut = rX(displaymetrics.widthPixels);
            int ybut = rY(displaymetrics.heightPixels);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)fbut.getLayoutParams();
            params.setMargins(xbut,ybut,displaymetrics.widthPixels-xbut,displaymetrics.heightPixels-ybut);


            //fbut.setX(xbut);
            //fbut.setY(ybut);

            fbut.setId(i+1);
            relativeLayout.addView(fbut);
        }**/
        return relativeLayout;
    }
}
