package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    }
    protected void sendMessage(View v)
    {
        Intent intent = new Intent(getApplicationContext(), info.class);
        startActivity(intent);
    }
}