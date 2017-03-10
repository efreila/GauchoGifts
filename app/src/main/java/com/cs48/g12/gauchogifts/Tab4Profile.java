package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.InputType;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.database.Cursor;
import android.provider.MediaStore;
import android.os.Build;
import android.provider.DocumentsContract;
import android.os.Environment;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.content.res.Resources;

import java.io.*;

import com.firebase.client.Firebase;
import com.firebase.client.utilities.Base64.InputStream;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Tab4Profile extends Fragment implements View.OnClickListener{

    private static final int GET_PIC = 1;
    ImageButton userImageBtn;
    String selectedImagePath;
    TextView firstName, lastName, street1, street2, City, State, Zip, Credits, Country;
    EditText interestInput;
    private TextView saveText;
    private FirebaseAuth mAuth;
    private Firebase mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private BottomNavigationView bottomNavigationView;
    Button saveNameBtn, editNameBtn, saveAddressBtn, editAddressBtn, saveInterestBtn, editInterestBtn, logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4profile, container, false);
        return rootView;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mAuth = FirebaseAuth.getInstance();
        //userImageBtn = (ImageButton) view.findViewById(R.id.userImage);
        //userImageBtn.setOnClickListener(this);


        firstName = (TextView) view.findViewById(R.id.profile_firstName);
        lastName = (TextView) view.findViewById(R.id.lastName);
        Credits = (TextView) view.findViewById(R.id.credits);
        street1 = (TextView) view.findViewById(R.id.street1);
        street2 = (TextView) view.findViewById(R.id.street2);
        City = (TextView) view.findViewById(R.id.City);
        State = (TextView) view.findViewById(R.id.State);
        Zip = (TextView) view.findViewById(R.id.Zip);
        interestInput = (EditText) view.findViewById(R.id.interestInput);
        interestInput.setEnabled(false);
        Country = (TextView) view.findViewById(R.id.country);

        saveNameBtn = (Button) getView().findViewById(R.id.saveName);
        saveNameBtn.setOnClickListener(this); // calling onClick() method
        editNameBtn = (Button) getView().findViewById(R.id.editName);
        editNameBtn.setOnClickListener(this);
        saveAddressBtn = (Button) getView().findViewById(R.id.saveAddress);
        saveAddressBtn.setOnClickListener(this); // calling onClick() method
        editAddressBtn = (Button) getView().findViewById(R.id.editAddress);
        editAddressBtn.setOnClickListener(this);
        saveInterestBtn = (Button) getView().findViewById(R.id.saveInterests);
        saveInterestBtn.setOnClickListener(this); // calling onClick() method
        editInterestBtn = (Button) getView().findViewById(R.id.editInterests);
        editInterestBtn.setOnClickListener(this);
        logout = (Button) getView().findViewById(R.id.logoutBtn);
        logout.setOnClickListener(this);

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

        //Loads the current user's country.
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

        /*
        mRef = new Firebase("https://gauchogifts.firebaseio.com/Users/" + uid + "/User Info/Photo");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                Uri profPic = dataSnapshot.getValue(Uri.class);

                userImageBtn.setImageURI(profPic);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        }); */



    }



    @Override
    public void onClick(View v1) {
        if (v1.getId() == R.id.userImage){
            /*FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();

            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GET_PIC);

            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Photo").setValue(userImageBtn.getImageAlpha());
        */
        }
        else if (v1.getId() == R.id.saveName)
        {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();

            //saveNameBtn.setVisibility(v1.INVISIBLE);
            editNameBtn.setVisibility(v1.VISIBLE);

            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("First Name").setValue(firstName.getText().toString().trim());
            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Last Name").setValue(lastName.getText().toString().trim());

            firstName.setCursorVisible(false);
            firstName.setFocusableInTouchMode(false);
            firstName.setInputType(InputType.TYPE_NULL);
            firstName.setText(firstName.getText().toString().trim());

            lastName.setCursorVisible(false);
            lastName.setFocusableInTouchMode(false);
            lastName.setInputType(InputType.TYPE_NULL);
            lastName.setText(lastName.getText().toString().trim());

        }

        else if (v1.getId() == R.id.editName){
            saveNameBtn.setVisibility(v1.VISIBLE);
            //editNameBtn.setVisibility(v1.INVISIBLE);

            firstName.setCursorVisible(true);
            firstName.setFocusableInTouchMode(true);
            firstName.setInputType(InputType.TYPE_CLASS_TEXT);
            firstName.requestFocus();
            firstName.setText("");
            firstName.setHint("First Name");

            lastName.setCursorVisible(true);
            lastName.setFocusableInTouchMode(true);
            lastName.setInputType(InputType.TYPE_CLASS_TEXT);
            lastName.requestFocus();
            lastName.setText("");
            lastName.setHint("Last Name");

            firstName.setHintTextColor(Color.RED);
            lastName.setHintTextColor(Color.RED);


        }

        else if (v1.getId() == R.id.saveAddress) {

            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();

            editAddressBtn.setVisibility(v1.VISIBLE);

            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Address Line One").setValue(street1.getText().toString().trim());
            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Address Line Two").setValue(street2.getText().toString().trim());
            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("City").setValue(City.getText().toString().trim());
            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("State").setValue(State.getText().toString().trim());
            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("ZIP").setValue(Zip.getText().toString().trim());
            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Country").setValue(Country.getText().toString().trim());

            street1.setCursorVisible(false);
            street1.setFocusableInTouchMode(false);
            street1.setInputType(InputType.TYPE_NULL);

            street2.setCursorVisible(false);
            street2.setFocusableInTouchMode(false);
            street2.setInputType(InputType.TYPE_NULL);

            City.setCursorVisible(false);
            City.setFocusableInTouchMode(false);
            City.setInputType(InputType.TYPE_NULL);

            State.setCursorVisible(false);
            State.setFocusableInTouchMode(false);
            State.setInputType(InputType.TYPE_NULL);

            Zip.setCursorVisible(false);
            Zip.setFocusableInTouchMode(false);
            Zip.setInputType(InputType.TYPE_NULL);

            Country.setCursorVisible(false);
            Country.setFocusableInTouchMode(false);
            Country.setInputType(InputType.TYPE_NULL);

        }

        else if (v1.getId() == R.id.editAddress){
            saveAddressBtn.setVisibility(v1.VISIBLE);

            street1.setCursorVisible(true);
            street1.setFocusableInTouchMode(true);
            street1.setInputType(InputType.TYPE_CLASS_TEXT);
            street1.requestFocus();
            street1.setText("");
            street1.setHint("Address Line 1");
            street1.setHintTextColor(Color.RED);

            street2.setCursorVisible(true);
            street2.setFocusableInTouchMode(true);
            street2.setInputType(InputType.TYPE_CLASS_TEXT);
            street2.requestFocus();
            street2.setText("");
            street2.setHint("Address Line 2");
            street2.setHintTextColor(Color.RED);

            City.setCursorVisible(true);
            City.setFocusableInTouchMode(true);
            City.setInputType(InputType.TYPE_CLASS_TEXT);
            City.requestFocus();
            City.setText("");
            City.setHint("City");
            City.setHintTextColor(Color.RED);

            State.setCursorVisible(true);
            State.setFocusableInTouchMode(true);
            State.setInputType(InputType.TYPE_CLASS_TEXT);
            State.requestFocus();
            State.setText("");
            State.setHint("State");
            State.setHintTextColor(Color.RED);

            Zip.setCursorVisible(true);
            Zip.setFocusableInTouchMode(true);
            Zip.setInputType(InputType.TYPE_CLASS_TEXT);
            Zip.requestFocus();
            Zip.setText("");
            Zip.setHint("ZIP");
            Zip.setHintTextColor(Color.RED);

            Country.setCursorVisible(true);
            Country.setFocusableInTouchMode(true);
            Country.setInputType(InputType.TYPE_CLASS_TEXT);
            Country.requestFocus();
            Country.setText("");
            Country.setHint("Country");
            Country.setHintTextColor(Color.RED);
        }

        else if (v1.getId() == R.id.saveInterests)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();

            myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("User Info").child("Interests").setValue(interestInput.getText().toString().trim());

            interestInput.setEnabled(false);

            editInterestBtn.setVisibility(v1.VISIBLE);
        }

        else if (v1.getId() == R.id.editInterests){
            interestInput.setEnabled(true);
        }

        else if (v1.getId() == R.id.logoutBtn){
            Intent myIntent = new Intent(v1.getContext(), intro.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(myIntent, 0);

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == GET_PIC && resultCode == Activity.RESULT_OK && data != null){

            userImageBtn.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.userImage, 100, 100));
        }*/
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize (BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}

