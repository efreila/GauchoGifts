package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.Date;


public class Tab1YourExchanges extends Fragment {

    private ListView joinedexchanges;
    private Calendar c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1yourexchanges, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        joinedexchanges = (ListView) view.findViewById(R.id.Exchanges);
        c = Calendar.getInstance();


        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Establishes connection to Firebase to display the current users current exchanges.
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
                "https://gauchogifts.firebaseio.com/Users/" + uID + "/Current Exchanges");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                getActivity(), String.class, android.R.layout.simple_list_item_1, databaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {

                final String gemodel = model;
                final Intent myIntent = new Intent(getActivity(), joinedexchange.class);

                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        try {
                            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                            String formattedDate = df.format(c.getTime());
                            Date date1 = df.parse(formattedDate);

                            String str2 = "03/05/2017";
                            Date date2 = df.parse(str2);

                            if (date1.after(date2))
                            {
                                System.out.println("date2 is Greater than my date1");
                            }

                        }

                        catch (ParseException e1){
                            e1.printStackTrace();
                        }

                        //Passes the information retrieved from the URL above to the next activity. This is needed to display the correct information
                        //based on the exchange chosen.
                        Firebase geTitle = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Title");
                        Firebase geDeadline = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Deadline");
                        Firebase geDescription = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Description");
                        Firebase geQuestionTwo = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Questions/Question1");
                        Firebase geQuestionThree = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Questions/Question2");
                        Firebase geQuestionFour = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Questions/Question3");
                        Firebase geQuestionFive = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Questions/Question4");
                        Firebase geQuestionSix = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Questions/Question5");

                        geQuestionTwo.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gequestionone = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("QuestionOne", gequestionone);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geQuestionThree.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gequestiontwo = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("QuestionTwo", gequestiontwo);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geQuestionFour.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gequestionthree = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("QuestionThree", gequestionthree);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geQuestionFive.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gequestionfour = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("QuestionFour", gequestionfour);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geQuestionSix.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gequestionfive = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("QuestionFive", gequestionfive);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });


                        geTitle.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String getitle = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("Title", getitle);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geDeadline.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gedeadline = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("Deadline", gedeadline);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geDescription.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gedescription = dataSnapshot.getValue(String.class);
                                myIntent.putExtra("Description", gedescription);
                                getActivity().startActivity(myIntent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });
            }
        };

        joinedexchanges.setAdapter(firebaseListAdapter);
    }
}
