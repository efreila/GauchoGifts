package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

public class intro extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //If the login button is pressed, the app will be taken to the login page.
        Button login = (Button)findViewById(R.id.intro_login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), login.class);
                startActivityForResult(myIntent, 0);
            }
        });
        //If the signup button is pressed, the app will be taken to the signup page.
        Button signup = (Button)findViewById(R.id.intro_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent1 = new Intent(view.getContext(), signup.class);
                startActivityForResult(myIntent1, 0);
            }
        });

        Toast.makeText(intro.this, "", Toast.LENGTH_SHORT).show();
    }
    protected void sendMessage(View v)
    {
        //relaunches the welcome slides
        SlideManager prefManager = new SlideManager(getApplicationContext());

        // make first time launch TRUE
        prefManager.setFirstTimeLaunch(true);

        startActivity(new Intent(intro.this, welcomeslides.class));
        finish();

    }
}