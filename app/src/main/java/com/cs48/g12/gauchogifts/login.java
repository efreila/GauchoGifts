package com.cs48.g12.gauchogifts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txtEmailLogin;
    private EditText txtPasswordLogin;
    private Button loginBtn;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmailLogin = (EditText)findViewById(R.id.login_email);
        txtPasswordLogin = (EditText)findViewById(R.id.login_password);
        loginBtn = (Button)findViewById(R.id.login_login);
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setMessage("Logging in...");
                mProgress.show();
                userLogin();
            }
        });


    }

    //Ensures that the user fills out all the information necessary to login.
    private void userLogin() {

        if(TextUtils.isEmpty(txtEmailLogin.getText().toString().trim())) {
            Toast.makeText(login.this, "Please enter your email.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(txtPasswordLogin.getText().toString().trim())) {
            Toast.makeText(login.this, "Please enter your password.", Toast.LENGTH_LONG).show();
            return;
        }

        else {

            mAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString().trim(), txtPasswordLogin.getText().toString().trim()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(login.this, "Wrong username/password", Toast.LENGTH_SHORT).show();

                    } else {
                        checkIfEmailVerified();
                    }
                    // ...
                }
            });

        }

    }

    //This checks if the user has verified their account and prevents them from logging in if they hadn't.
    private void checkIfEmailVerified() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Intent myIntent = new Intent(login.this, navheader.class);
            startActivity(myIntent);
            finish();
            mProgress.dismiss();
        }
        else
        {
            Toast.makeText(login.this, "Please Verify Email.. ", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }

    }
}
