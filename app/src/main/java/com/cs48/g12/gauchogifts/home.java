package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class home extends AppCompatActivity {

    private ListView exchanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        exchanges = (ListView)findViewById(R.id.Exchanges);

        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Establishes connection to Firebase to display the current users current exchanges.
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
                "https://gauchogifts.firebaseio.com/Users/" + uID + "/Current Exchanges");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                final String gemodel = model;
                final Intent myIntent = new Intent(home.this, giftexchange.class);

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
                                startActivity(myIntent);
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
                                startActivity(myIntent);
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
                                startActivity(myIntent);
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
    };
}
