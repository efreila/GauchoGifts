package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Tab2JoinExchange extends Fragment {

    private ListView joinexchanges;
    private FirebaseAuth mAuth;
    private Integer id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2joinexchange, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        joinexchanges = (ListView) view.findViewById(R.id.allexchanges);
        mAuth = FirebaseAuth.getInstance();
        id = 0;

        //Establishes connection to Firebase to display the current users current exchanges.
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl(
                "https://gauchogifts.firebaseio.com/Exchanges/AllExchanges");

        final FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                getActivity(), String.class, android.R.layout.simple_list_item_1, databaseReference1) {
            @Override
            protected void populateView(final View v, final String model, final int position) {
                final String gemodel = model;
                final Intent myIntent1 = new Intent(getActivity(), giftexchange.class);

                final TextView textView = (TextView) v.findViewById(android.R.id.text1);
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                rootRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("Current Exchanges").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(model)) {
                            textView.setTextColor(Color.RED);
                            textView.setText(model + " (enrolled)");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        // ...
                    }
                });

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
                                myIntent1.putExtra("Title", getitle);
                                getActivity().startActivity(myIntent1);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geDeadline.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gedeadline = dataSnapshot.getValue(String.class);
                                myIntent1.putExtra("Deadline", gedeadline);
                                getActivity().startActivity(myIntent1);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                        geDescription.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String gedescription = dataSnapshot.getValue(String.class);
                                myIntent1.putExtra("Description", gedescription);
                                getActivity().startActivity(myIntent1);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });
            }
        };
        joinexchanges.setAdapter(firebaseListAdapter);

    }
}
