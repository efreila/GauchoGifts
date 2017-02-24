package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class emailverification extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);
    }

    //Takes user to the login activity when the login button is clicked.
    public void verifLoginBtnClicked(View view) {
        Intent loginIntent = new Intent(emailverification.this, login.class);
        startActivity(loginIntent);
        finish();
    }
}
