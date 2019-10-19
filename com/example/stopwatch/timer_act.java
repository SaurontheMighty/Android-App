package com.example.stopwatch;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class timer_act extends AppCompatActivity {

    ImageView ttimer_arrow;
    Animation rotater;
    Button btstart, btstop;
    TextView ttimer;
    private CountDownTimer ttimerCount;
    private boolean timer_running;
    private long start_time=600000;
    private long time_left=start_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_act);


        ttimer_arrow = findViewById(R.id.timer_arrow);
        ttimer = findViewById(R.id.timer);
        btstart = findViewById(R.id.btstart);
        btstop = findViewById(R.id.btstop);
        ttimer=findViewById(R.id.ttimer);

        btstart.setText("START");
        btstop.setText("RESET");


        rotater = AnimationUtils.loadAnimation( this, R.anim.rotater);

        Typeface regular = Typeface.createFromAsset(getAssets(), "MRegular.ttf");

        //setup font
        btstart.setTypeface(regular);
        btstop.setTypeface(regular);

        btstart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(timer_running){
                    pauseTimer();
                }
                else{
                    startTimer();
                }
            }
        });


        btstop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View d) {
                resetTimer();
            }
        });
        updateCountDownText();
    }
    private void startTimer(){
        ttimerCount = new CountDownTimer(time_left,1000) {
            @Override
            public void onTick(long time_till_finished) {
                time_left = time_till_finished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timer_running=false;
                btstart.setText("START");
                btstart.setVisibility(View.INVISIBLE);
                btstop.setVisibility(View.VISIBLE);

            }
        }.start();

        timer_running=true;
        btstart.setText("PAUSE");
        btstop.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        ttimerCount.cancel();
        timer_running=false;
        btstart.setText("START");
        btstart.setVisibility(View.VISIBLE);
        btstop.setVisibility(View.VISIBLE);

    }
    private void resetTimer(){
        time_left=start_time;
        updateCountDownText();
        btstop.setVisibility(View.INVISIBLE);
        btstart.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText(){
        int minutes = (int) (time_left/1000)/60;
        int seconds = (int) (time_left/1000)%60;
        String timeLeftformatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        ttimer.setText(timeLeftformatted);
    }
}