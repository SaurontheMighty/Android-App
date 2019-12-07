package com.example.stopwatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

public class set_actualtimer extends AppCompatActivity{
    EditText settime;
    Animation btn_ani;
    Button btset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_actualtimer);

        //setup
        settime = findViewById(R.id.settime);
        btset = findViewById(R.id.btset);

        //import font
        Typeface regular = Typeface.createFromAsset(getAssets(), "MRegular.ttf");

        //setup font
        btset.setTypeface(regular);

        btset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                String time = settime.getText().toString();;
                Intent z = new Intent(set_actualtimer.this, actualtimer_act.class);
                z.putExtra("inputted", time);
                z.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(z);
            }

        });
    }
}
