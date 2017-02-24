package com.cs48.g12.gauchogifts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmail;
    private EditText mFirstName;
    private EditText mAddressOne;
    private EditText mAddressTwo;
    private EditText mCity;
    private EditText mState;
    private EditText mZIP;
    private EditText mCountry;
    private EditText mLastName;
    private EditText mPassword;
    private Button btnSignUp;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText)findViewById(R.id.signup_email);
        mPassword = (EditText)findViewById(R.id.signup_password);
        btnSignUp = (Button)findViewById(R.id.signup_signup);
        mProgress = new ProgressDialog(this);
        mFirstName = (EditText)findViewById(R.id.signup_firstname);
        mLastName = (EditText)findViewById(R.id.signup_lastname);
        mAddressOne = (EditText)findViewById(R.id.signup_addressone);
        mAddressTwo = (EditText)findViewById(R.id.signup_addresstwo);
        mCity = (EditText)findViewById(R.id.signup_city);
        mState = (EditText)findViewById(R.id.signup_state);
        mZIP = (EditText)findViewById(R.id.signup_zip);
        mCountry = (EditText)findViewById(R.id.signup_country);
        FirebaseAuth.getInstance().signOut();

        //Checks if the user is signed in. Necessary to prevent bugs.
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // NOTE: this Activity should get open only when the user is not signed in, otherwise
                    // the user will receive another verification email.
                    sendVerificationEmail();
                } else {
                    // User is signed out
                }
                // ...
            }
        };
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
    //Sends the vertification email if the user successfully completes the signup process.
    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(signup.this, emailverification.class));
                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            //restart this activity

                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                        }
                    }
                });
    }

    private void startRegister() {
        //The if and else if statements ensures that the user appropriately fills out their information.
        if(TextUtils.isEmpty(mFirstName.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your first name.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mLastName.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your last name.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mAddressOne.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your Address.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mCity.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your city.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mState.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your state.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mZIP.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your ZIP code.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mCountry.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your country.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mEmail.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your email.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(!mEmail.getText().toString().trim().contains("umail.ucsb.edu")) {
            Toast.makeText(signup.this, "Please enter a valid UCSB Umail.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(mPassword.getText().toString().trim())) {
            Toast.makeText(signup.this, "Please enter your password.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(mPassword.getText().toString().trim().length() < 6) {
            Toast.makeText(signup.this, "Password must be at least 6 characters.", Toast.LENGTH_LONG).show();
        }

        else {

            mProgress.setMessage("Signing up...");
            mProgress.show();

            //If the signup process was successful, this adds the user to the Firebase database.
            mAuth.createUserWithEmailAndPassword(mEmail.getText().toString().trim(), mPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference();

                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("First Name").setValue(mFirstName.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Last Name").setValue(mLastName.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Address Line One").setValue(mAddressOne.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Address Line Two").setValue(mAddressTwo.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("City").setValue(mCity.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("State").setValue(mState.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("ZIP").setValue(mZIP.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Country").setValue(mCountry.getText().toString().trim());
                        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Credits").setValue(1);

                        mProgress.dismiss();
                        Toast.makeText(signup.this, "Successfully signed up...", Toast.LENGTH_LONG).show();
                    }

                    else {
                        mProgress.dismiss();
                        Toast.makeText(signup.this, "Registration failed...", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
