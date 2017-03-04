package com.cs48.g12.gauchogifts;

import android.content.Intent;
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


public class Tab1YourExchanges extends Fragment {

    private ListView exchanges;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1yourexchanges, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        exchanges = (ListView) view.findViewById(R.id.Exchanges);

        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Establishes connection to Firebase to display the current users current exchanges.
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
                "https://gauchogifts.firebaseio.com/Users/" + uID + "/Current Exchanges");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                getActivity(), String.class, android.R.layout.simple_list_item_1, databaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {

                final String gemodel = model;
                final Intent myIntent = new Intent(getActivity(), giftexchange.class);

                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        //Passes the information retrieved from the URL above to the next activity. This is needed to display the correct information
                        //based on the exchange chosen.
                        Firebase geTitle = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Title");
                        Firebase geDeadline = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Deadline");
                        Firebase geDescription = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + gemodel + "/Description");

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

        exchanges.setAdapter(firebaseListAdapter);
    }
}
