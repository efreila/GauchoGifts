package com.cs48.g12.gauchogifts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Tab4Profile extends Fragment {

    public static final int GET_PIC = 1;
    ImageButton userImageBtn;
    TextView firstName, lastName, street1, street2, City, State, Zip, Credits, Country;
    TextView interestInput;
    private TextView saveText;
    private FirebaseAuth mAuth;
    private Firebase mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private BottomNavigationView bottomNavigationView;
    Button done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mAuth = FirebaseAuth.getInstance();
        userImageBtn = (ImageButton) view.findViewById(R.id.userImage);
        userImageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //CheckPermission();
            }
        });

        firstName = (TextView) view.findViewById(R.id.profile_firstName);
        lastName = (TextView) view.findViewById(R.id.lastName);
        Credits = (TextView) view.findViewById(R.id.credits);
        street1 = (TextView) view.findViewById(R.id.street1);
        street2 = (TextView) view.findViewById(R.id.street2);
        City = (TextView) view.findViewById(R.id.City);
        State = (TextView) view.findViewById(R.id.State);
        Zip = (TextView) view.findViewById(R.id.Zip);
        interestInput = (TextView) view.findViewById(R.id.interestInput);
        Country = (TextView) view.findViewById(R.id.country);

        //Checks if the current user is signed in or not. Prevents bugs.
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

        /*
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
*/
        //Loads the current user's first name.
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
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
        //Loads the current user's last name.
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
        //Loads the current user's Credit information.
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Credits");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String credit = dataSnapshot.getValue(String.class);

                Credits.setText(credit);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Loads the current user's Address
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
        //Loads the rest of the current user's Address
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
        //Loads the current user's city.
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
        //Loads the current user's state.
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
        //Loads the current user's ZIP code.
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
        //Loads the current user's country.
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


        /*//when done button clicked goes to display profile class
        Button done = (Button) findViewById(R.id.donebtn);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), profileDisplay.class);
                startActivityForResult(myIntent, 0);
            }
        });
        */


    }
}
