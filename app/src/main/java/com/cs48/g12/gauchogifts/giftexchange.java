package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class giftexchange extends AppCompatActivity {

    private TextView acTitle;
    private TextView acDeadline;
    private TextView acDescription;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftexchange);

        //Navigation Bar
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Home) {
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                    Intent myIntent = new Intent(giftexchange.this, home.class);
                    startActivity(myIntent);
                    finish();
                } else if (item.getItemId() == R.id.AddGE) {
                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                    Intent myIntent = new Intent(giftexchange.this, home.class);
                    startActivity(myIntent);
                    finish();
                } else if (item.getItemId() == R.id.FAQ) {
                    bottomNavigationView.getMenu().getItem(2).setChecked(true);
                    Intent myIntent = new Intent(giftexchange.this, home.class);
                    startActivity(myIntent);
                    finish();
                } else if (item.getItemId() == R.id.Profile) {
                    bottomNavigationView.getMenu().getItem(3).setChecked(true);
                    Intent myIntent = new Intent(giftexchange.this, profile.class);
                    startActivity(myIntent);
                    finish();
                }
                return false;
            }
        });

        acTitle = (TextView)findViewById(R.id.geTitle);
        acDeadline = (TextView)findViewById(R.id.geDeadline);
        acDescription = (TextView)findViewById(R.id.geDescripton);

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
    }
}
