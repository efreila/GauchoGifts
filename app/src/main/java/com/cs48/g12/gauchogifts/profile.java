package com.cs48.g12.gauchogifts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.os.Build;
import android.widget.Toast;

//realtime database libraries
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.client.Firebase;
import java.net.URL;
import java.net.URI;
import java.io.*;

public class profile extends AppCompatActivity {

    public static final int GET_PIC = 1;
    ImageButton userImageBtn;
    TextView firstName, lastName, street1, street2, City, State, Zip;
    TextView interestInput;
    private TextView saveText;
    private FirebaseAuth mAuth;
    private Firebase mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button done;

    String currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        userImageBtn = (ImageButton) findViewById(R.id.userImage);
        userImageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //CheckPermission();
            }
        });

        firstName = (TextView) findViewById(R.id.profile_firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        street1 = (TextView) findViewById(R.id.street1);
        street2 = (TextView) findViewById(R.id.street2);
        City = (TextView) findViewById(R.id.City);
        State = (TextView) findViewById(R.id.State);
        Zip = (TextView) findViewById(R.id.Zip);
        interestInput = (TextView) findViewById(R.id.interestInput);

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

        //URL firstn = new URL("https://gauchogifts.firebaseio.com/Users/" + currentUid + "/First Name");


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/First Name");

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

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/Last Name");

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

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/Address Line One");

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

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/Address Line Two");

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

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/City");

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

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/State");

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

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/ZIP");

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


/* USER PROFILE IMAGE STUFF
    void CheckPermission()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                return;
            }
        }

        //LoadImage();
    }
    final private int REQUEST_CODE_PERMISSION = 123;

    //@Override
    public void onRequestPermissionRes(int request, String[] permission, int[] results)
    {
        switch (request)
        {
            case REQUEST_CODE_PERMISSION:
                if (results[0] == PackageManager.PERMISSION_GRANTED) {
                    LoadImage();
                }
                else //permission denied
                {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                //super.onRequestPermissionsResult(i, LOAD_IMAGE);

        }


    }

    void LoadImage()
    {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivityForResult(i, LOAD_IMAGE);
    }

*/

}