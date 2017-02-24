package com.example.dianareyes.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etYear=(EditText)findViewById(R.id.etYear);
    }

    public void buGetage(View view) {
        int year=Integer.parseInt(etYear.getText().toString());
        int oldAge= 2016-year;

        Toast.makeText(getApplicationContext(), "age: "+String.valueOf(oldAge), Toast.LENGTH_LONG.show());
    }
}
