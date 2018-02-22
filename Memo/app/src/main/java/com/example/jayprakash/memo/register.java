package com.example.jayprakash.memo;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Text;


public class register extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    EditText regusername, regemail, regpass, regcnfpass;
    Button register;
    DatabaseHandler db;

    private RelativeLayout holder;
    TextView customText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 6 seconds
        animationDrawable.setEnterFadeDuration(6000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);


        //Register button click
        regusername= (EditText)findViewById(R.id.regusername);
        regemail =(EditText)findViewById(R.id.regemail);
        regpass=(EditText)findViewById(R.id.regpass);
        regcnfpass =(EditText)findViewById(R.id.regcnfpass);

        register=(Button)findViewById(R.id.createaccount);

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String edusername = regusername.getText().toString();
                String edemail = regemail.getText().toString();
                String edpass = regpass.getText().toString();
                String edcnfpass = regcnfpass.getText().toString();

                if(edusername.matches("") || edemail.matches("") || edpass.matches("") || edcnfpass.matches(""))
                {
                    holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast, (RelativeLayout) findViewById(R.id.rel));
                    customText = (TextView) holder.findViewById(R.id.textView);

                    customText.setText("!!! Fill all the Deatils !!!");
                    customText.setTextSize(25);

                    final Toast t = new Toast(getApplicationContext());
                    t.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
                    t.setView(holder);
                    t.show();
                }

                else {

                    if (edcnfpass.equals(edpass)) {
                        db = new DatabaseHandler(register.this, null, null, 2);
                        Registerdata reg = new Registerdata();

                        reg.setUserName(edusername);
                        reg.setEmailId(edemail);
                        reg.setPassword(edpass);
                        db.addregister(reg);

                        holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast_success, (RelativeLayout) findViewById(R.id.relsuccess));
                        customText = (TextView) holder.findViewById(R.id.textView);

                        customText.setText("Registered Successfully");
                        customText.setTextSize(25);

                        final Toast t = new Toast(getApplicationContext());
                        t.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
                        t.setView(holder);
                        t.show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast, (RelativeLayout) findViewById(R.id.rel));
                        customText = (TextView) holder.findViewById(R.id.textView);

                        customText.setText("Password doesn't match");
                        customText.setTextSize(25);

                        final Toast t = new Toast(getApplicationContext());
                        t.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
                        t.setView(holder);
                        t.show();
                        regpass.setText("");
                        regcnfpass.setText("");
                    }
                }

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

