package com.example.stopwatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView als;
    Animation als_ani;
    Animation btn_ani;
    Button btnsplash;
    Button btntimer;
    Button btnactualtimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup
        als = findViewById(R.id.als);
        btnsplash = findViewById(R.id.btnsplash);
        btntimer = findViewById(R.id.btntimer);
        btnactualtimer = findViewById(R.id.btnactualtimer);

        //load anim
        als_ani = AnimationUtils.loadAnimation( this, R.anim.als_ani);
        btn_ani = AnimationUtils.loadAnimation( this, R.anim.btn_ani);


        //pass anim
        als.startAnimation(als_ani);
        btnsplash.startAnimation(btn_ani);
        btntimer.startAnimation(btn_ani);
        btnactualtimer.startAnimation(btn_ani);


        btnsplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( MainActivity.this, stopwatch_act.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);}
            }

        );

        btntimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent b = new Intent(MainActivity.this, timer_act.class);
                b.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(b);}
            }

        );

        btnactualtimer.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View a) {
                                            Intent b = new Intent(MainActivity.this, set_actualtimer.class);
                                            b.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(b);}
                                    }

        );

        //import font
        Typeface regular = Typeface.createFromAsset(getAssets(), "MRegular.ttf");

        //setup font
        als.setTypeface(regular);
        btnsplash.setTypeface(regular);
        btntimer.setTypeface(regular);
        btnactualtimer.setTypeface(regular);
    }
}
