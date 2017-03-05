package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class giftexchange extends AppCompatActivity {

    private TextView acTitle;
    private TextView acDeadline;
    private TextView acDescription;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftexchange);


        acTitle = (TextView)findViewById(R.id.geTitle);
        acDeadline = (TextView)findViewById(R.id.geDeadline);
        acDescription = (TextView)findViewById(R.id.geDescripton);
        mAuth = FirebaseAuth.getInstance();


        Bundle geinfo = getIntent().getExtras();

        //When the activity is loaded the specific title of the exchange chosen is displayed
        String exTitle = geinfo.getString("Title");
        acTitle.setText(exTitle);
        //When the activity is loaded the deadline of the exchange chosen is displayed
        String exDeadline = geinfo.getString("Deadline");
        acDeadline.setText(exDeadline);
        //When the activity is loaded the description of the exchange chosen is displayed
        String exDescription = geinfo.getString("Description");
        acDescription.setText(exDescription);

        Button join = (Button)findViewById(R.id.joinBtn);
        join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                joinGiftExchange();
            }
        });

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    // NOTE: this Activity should get open only when the user is not signed in, otherwise
//                    // the user will receive another verification email.
//                } else {
//                    // User is signed out
//                }
//                // ...
//            }
//        };

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    private void joinGiftExchange() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Bundle geinfo = getIntent().getExtras();
        String exchangeTitle = geinfo.getString("Title");

        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").setValue("Hello");
        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("Current Exchanges").child(exchangeTitle).setValue(exchangeTitle);


        Intent myIntent = new Intent(giftexchange.this, navheader.class);
        startActivity(myIntent);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(giftexchange.this, navheader.class);
        startActivity(myIntent);
    }
}
