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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmailLogin = (EditText)findViewById(R.id.login_email);
        txtPasswordLogin = (EditText)findViewById(R.id.login_password);
        loginBtn = (Button)findViewById(R.id.login_login);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


    }

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

    private void checkIfEmailVerified() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Toast.makeText(login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(login.this, home.class);
            startActivity(myIntent);
            finish();
        }
        else
        {
            Toast.makeText(login.this, "Please Verify Email.. ", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }

    }
}
