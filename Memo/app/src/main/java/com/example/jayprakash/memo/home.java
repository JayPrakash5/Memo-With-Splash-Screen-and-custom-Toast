package com.example.jayprakash.memo;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class home extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    Button loginbutton,registerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(6000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);

        loginbutton=(Button)findViewById(R.id.homelogin);
        registerbutton=(Button)findViewById(R.id.homeregister);

        loginbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hometologin_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(hometologin_intent);
            }
        });

        registerbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hometoregister_intent = new Intent(getApplicationContext(), register.class);
                startActivity(hometoregister_intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}
