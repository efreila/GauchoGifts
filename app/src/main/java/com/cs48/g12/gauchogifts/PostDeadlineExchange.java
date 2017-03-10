package com.cs48.g12.gauchogifts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.google.firebase.database.ValueEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class PostDeadlineExchange extends AppCompatActivity implements View.OnClickListener {

    Button complete, goBack;
    TextView firstName, lastName, street1, street2, City, State, Zip, Credits, Country, interestInput, q1Ans, q2Ans, q3Ans, q4Ans, q5Ans, q6Ans;
    private TextView saveText;
    private FirebaseAuth mAuth;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_deadline_exchange);

        mAuth = FirebaseAuth.getInstance();

        firstName = (TextView) findViewById(R.id.profile_firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        Credits = (TextView) findViewById(R.id.credits);
        street1 = (TextView) findViewById(R.id.street1);
        street2 = (TextView) findViewById(R.id.street2);
        City = (TextView) findViewById(R.id.City);
        State = (TextView) findViewById(R.id.State);
        Zip = (TextView) findViewById(R.id.Zip);
        interestInput = (TextView) findViewById(R.id.interestInput);
        Country = (TextView) findViewById(R.id.country);
        q1Ans = (TextView) findViewById(R.id.q1AnsJ);
        q2Ans = (TextView) findViewById(R.id.q2AnsJ);
        q3Ans = (TextView) findViewById(R.id.q3AnsJ);
        q4Ans = (TextView) findViewById(R.id.q4AnsJ);
        q5Ans = (TextView) findViewById(R.id.q5AnsJ);
        q6Ans = (TextView) findViewById(R.id.q6AnsJ);

        goBack = (Button) findViewById(R.id.backBtn);
        goBack.setOnClickListener(this);
        complete = (Button) findViewById(R.id.exchangeComplete);
        complete.setOnClickListener(this); // calling onClick() method

        //Loads the giftee's first name.



        Bundle geinfo = getIntent().getExtras();
        String exchangeTitle = geinfo.getString("Title");
        String uid = "8qp8fkR8VJfrbcKE6b82mfXPNYw2";
                //FirebaseAuth.getInstance().getCurrentUser().getUid();


        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/First Name");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String firstname = dataSnapshot.getValue(String.class);

                firstName.setText(firstname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the giftee's last name.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Last Name");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String lastname = dataSnapshot.getValue(String.class);

                lastName.setText(lastname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
   
        //Loads the giftee's Address
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Address Line One");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String address1 = dataSnapshot.getValue(String.class);

                street1.setText(address1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the rest of the giftee's Address
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Address Line Two");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String address2 = dataSnapshot.getValue(String.class);

                street2.setText(address2);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the giftee's city.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/City");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String city = dataSnapshot.getValue(String.class);

                City.setText(city);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the giftee's state.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/State");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String state = dataSnapshot.getValue(String.class);

                State.setText(state);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the giftee's ZIP code.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/ZIP");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String zip = dataSnapshot.getValue(String.class);

                Zip.setText(zip);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the giftee's country.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Country");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String country = dataSnapshot.getValue(String.class);

                Country.setText(country);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Loads the giftee's interests.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Interests");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String interest = dataSnapshot.getValue(String.class);

                interestInput.setText(interest);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        //Loads giftee answer to question 1.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle + "/Enrolled Users/" + uid + "/Questions/General Info");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String q1 = dataSnapshot.getValue(String.class);

                q1Ans.setText(q1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Loads giftee answer to question 2.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle + "/Enrolled Users/" + uid + "/Questions/Question1");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String q2 = dataSnapshot.getValue(String.class);

                q2Ans.setText(q2);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Loads giftee answer to question 3.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle + "/Enrolled Users/" + uid + "/Questions/Question2");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String q3 = dataSnapshot.getValue(String.class);

                q3Ans.setText(q3);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Loads giftee answer to question 4.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle + "/Enrolled Users/" + uid + "/Questions/Question3");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String q4 = dataSnapshot.getValue(String.class);

                q4Ans.setText(q4);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Loads giftee answer to question 5.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle + "/Enrolled Users/" + uid + "/Questions/Question4");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String q5 = dataSnapshot.getValue(String.class);

                q5Ans.setText(q5);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Loads giftee answer to question 6.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle + "/Enrolled Users/" + uid + "/Questions/Question5");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String q6 = dataSnapshot.getValue(String.class);

                q6Ans.setText(q6);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.exchangeComplete) {
            changeCredit();
            Intent myIntent = new Intent(v.getContext(), navheader.class);
            startActivityForResult(myIntent, 0);
        }
        else if (v.getId() == R.id.backBtn)
        {
            Intent myIntent = new Intent(v.getContext(), navheader.class);
            startActivityForResult(myIntent, 0);
        }
    }

    private void changeCredit() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Credits");

        mRef.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                removeExchange();
                Integer creditInt = dataSnapshot.getValue(Integer.class);

                creditInt += 2;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Credits").setValue(creditInt);

                Toast.makeText(PostDeadlineExchange.this, "You earned 2 credits!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void removeExchange() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Bundle geinfo = getIntent().getExtras();
        String exchangeTitle = geinfo.getString("Title");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(uid).setValue(null);
        myRef.child("Users").child(uid).child("Current Exchanges").child(exchangeTitle).setValue(null);

        Intent myIntent = new Intent(PostDeadlineExchange.this, navheader.class);
        startActivity(myIntent);

    }


}
