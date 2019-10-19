package com.example.stopwatch;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

public class stopwatch_act extends AppCompatActivity {

    ImageView timer_arrow;
    Animation rotater;
    Button bsstart, bsstop;
    Chronometer timer;
    TextView mean_msg;
    long time_used;
    long timeWhenStopped;
    boolean start;
    boolean caught;
    boolean first;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch_act);

        timer_arrow = findViewById(R.id.timer_arrow);
        mean_msg = findViewById(R.id.mean_msg);
        timer = findViewById(R.id.timer);
        bsstart = findViewById(R.id.bsstart);
        bsstop = findViewById(R.id.bsstop);


        rotater = AnimationUtils.loadAnimation( this, R.anim.rotater);

        System.out.println("ffghjk");
        Typeface regular = Typeface.createFromAsset(getAssets(), "MRegular.ttf");

        //setup font
        bsstart.setTypeface(regular);
        bsstop.setTypeface(regular);
        mean_msg.setTypeface(regular);


        mean_msg.setAlpha(0);

        start=false;
        caught=false;
        first=true;
        timeWhenStopped=0;
        bsstart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println("LOOK HERE:  "+timeWhenStopped);
                if(start==false) {
                    timer_arrow.setAnimation(rotater);
                    //bsstop.animate().alpha(1).setDuration(100).start();
                    //bsstart.animate().alpha(0).setDuration(100).start();
                    if(timeWhenStopped==0) {
                        timer.setBase(SystemClock.elapsedRealtime());
                        timer.start();
                        start = true;
                    }
                    else{
                        timer.setBase(SystemClock.elapsedRealtime()-timeWhenStopped);
                        timer.start();
                        start=true;
                    }
                }
                else if(start==true){
                    time_used=SystemClock.elapsedRealtime()- timer.getBase();

                    if(time_used<60000&&mean_msg.getAlpha()==0){
                        v.setAnimation(rotater);
                        mean_msg.setAlpha(1);
                    }
                    else if(mean_msg.getAlpha()==1){
                        caught=true;
                        mean_msg.setAlpha(0);
                    }
                    else{
                        timer.stop();
                        timeWhenStopped = SystemClock.elapsedRealtime()- timer.getBase();
                        v.clearAnimation();
                        start=false;
                    }

                    if(caught==true){
                        timer.stop();
                        v.clearAnimation();
                        timeWhenStopped = SystemClock.elapsedRealtime()- timer.getBase();
                        start=false;
                        caught=false;


                    }

                    /*if(SystemClock.elapsedRealtime()- timer.getBase()!=0) {
                        timer.setBase(SystemClock.elapsedRealtime()-timer.getBase());
                        timer.start();
                    }
                    time_used=SystemClock.elapsedRealtime()- timer.getBase();
                    if(mean_msg.getAlpha()==1){
                        caught=true;
                        v.clearAnimation();
                        mean_msg.setAlpha(0);
                    }
                    if(caught==true) {
                        timer.stop();
                        caught=false;
                    }
                    else if(time_used<300000) {
                        v.setAnimation(rotater);
                        mean_msg.setAlpha(1);
                    }*/


                }
            }
        });


        bsstop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View d) {
                timer.setBase(SystemClock.elapsedRealtime());
                start=false;
                timeWhenStopped=0;
            }
        });
    }
}
