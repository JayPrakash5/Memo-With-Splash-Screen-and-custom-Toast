package com.example.jayprakash.memo;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    EditText email, password;
    Button login, not_reg;
    DatabaseHandler db;
    Cursor cursor;

    private RelativeLayout holder;
    TextView customText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 6 seconds
        animationDrawable.setEnterFadeDuration(6000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);



        //LOGIN ACTIVITY
        email =(EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login =(Button)findViewById(R.id.login);

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db = new DatabaseHandler(MainActivity.this, null, null, 2);
                String emailchk = email.getText().toString();
                String passwordchk = password.getText().toString();

                if (emailchk.matches("") || passwordchk.matches("")) {
                    holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast, (RelativeLayout) findViewById(R.id.rel));
                    customText = (TextView) holder.findViewById(R.id.textView);

                    customText.setText("!!! Fill all the fields !!!");
                    customText.setTextSize(25);

                    final Toast t = new Toast(getApplicationContext());
                    t.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
                    t.setView(holder);
                    t.show();
                } else {
                    String StoredPassword = db.getregister(emailchk);

                    if (passwordchk.equals(StoredPassword)) {
                        holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast_success, (RelativeLayout) findViewById(R.id.relsuccess));
                        customText = (TextView) holder.findViewById(R.id.textView);

                        customText.setText("Logged In Successfully");
                        customText.setTextSize(25);

                        final Toast t = new Toast(getApplicationContext());
                        t.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
                        t.setView(holder);
                        t.show();

                        Intent logintomemo = new Intent(MainActivity.this, MainActivityMemo.class);
                        startActivity(logintomemo);


                    } else {
                        holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast, (RelativeLayout) findViewById(R.id.rel));
                        customText = (TextView) holder.findViewById(R.id.textView);

                        customText.setText("Email / Password Incorrect");
                        customText.setTextSize(25);

                        final Toast t = new Toast(getApplicationContext());
                        t.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
                        t.setView(holder);
                        t.show();
                        email.setText("");
                        password.setText("");
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
