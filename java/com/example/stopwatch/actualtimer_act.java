package com.example.stopwatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class actualtimer_act extends AppCompatActivity {
    TextView timeLeft;
    Animation btn_ani;
    Button pausebtn;
    Button btback;
    boolean timer_running;
    long start_time;
    String input;
    private CountDownTimer ttimerCount;
    private long time_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualtimer_act);

        pausebtn = findViewById(R.id.pausebtn);
        btback = findViewById(R.id.btback);
        timeLeft = findViewById(R.id.timeLeft);

        Typeface regular = Typeface.createFromAsset(getAssets(), "MRegular.ttf");

        //setup font
        pausebtn.setTypeface(regular);
        btback.setTypeface(regular);
        timeLeft.setTypeface(regular);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        input = bundle.getString("inputted");

        start_time = Long.parseLong(input);
        start_time = start_time*60000;
        pausebtn.setText("START");

        time_left=start_time;
        updateCountDownText();
        System.out.println("HERE "+time_left);



        pausebtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(timer_running){
                    pausebtn.setText("START");
                    pauseTimer();
                }
                else{
                    startTimer();
                    pausebtn.setText("PAUSE");
                    timer_running=true;
                }
            }
        });
        btback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(actualtimer_act.this, MainActivity.class);
                y.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(y);
            }
        });

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
                pausebtn.setText("START");
            }
        }.start();

        timer_running=true;
        pausebtn.setText("PAUSE");
    }
    private void pauseTimer(){
        ttimerCount.cancel();
        timer_running=false;
        pausebtn.setText("START");
    }
    private void updateCountDownText(){
        int minutes = (int) (time_left/1000)/60;
        int seconds = (int) (time_left/1000)%60;
        String timeLeftformatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timeLeft.setText(timeLeftformatted);
    }
}