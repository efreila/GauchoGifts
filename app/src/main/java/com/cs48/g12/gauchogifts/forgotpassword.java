package com.cs48.g12.gauchogifts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgotpassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText forgotpass_email;
    private Button forgotPasswordResetBtn;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgotpass_email = (EditText)findViewById(R.id.resetPasswordEmail);
        forgotPasswordResetBtn = (Button)findViewById(R.id.resetPasswordBtn);
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        FirebaseAuth.getInstance().signOut();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // NOTE: this Activity should get open only when the user is not signed in, otherwise
                    // the user will receive another verification email.
                } else {
                    // User is signed out
                }
                // ...
            }
        };


        forgotPasswordResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(forgotpass_email.getText().toString().trim())) {
                    Toast.makeText(forgotpassword.this, "Please enter your email.", Toast.LENGTH_LONG).show();
                    return;
                }

                String email = forgotpass_email.getText().toString().trim();
                mProgress.setMessage("Please wait...");
                mProgress.show();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            mProgress.dismiss();
                            Toast.makeText(forgotpassword.this, "Please check your email to reset your password.", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(forgotpassword.this, login.class);
                            startActivity(myIntent);

                        }

                        else
                            Toast.makeText(forgotpassword.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
