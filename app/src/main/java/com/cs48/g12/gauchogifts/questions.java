package com.cs48.g12.gauchogifts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class questions extends AppCompatActivity {
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    TextView answer5;
    TextView answer6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        answer1 = (TextView) findViewById(R.id.answer1);
        // hide until its title is clicked
        answer1.setVisibility(View.INVISIBLE);

        answer2 = (TextView) findViewById(R.id.answer2);
        // hide until its title is clicked
        answer2.setVisibility(View.INVISIBLE);

        answer3 = (TextView) findViewById(R.id.answer3);
        // hide until its title is clicked
        answer3.setVisibility(View.INVISIBLE);

        answer4 = (TextView) findViewById(R.id.answer4);
        // hide until its title is clicked
        answer4.setVisibility(View.INVISIBLE);

        answer5 = (TextView) findViewById(R.id.answer5);
        // hide until its title is clicked
        answer5.setVisibility(View.INVISIBLE);

        answer6 = (TextView) findViewById(R.id.answer6);
        // hide until its title is clicked
        answer6.setVisibility(View.INVISIBLE);

    }

    public void toggle_contents(View v) {
        answer1.setVisibility(answer1.isShown()
                ? View.INVISIBLE
                : View.VISIBLE);
    }
    public void toggle_contents2(View v) {
        answer2.setVisibility(answer2.isShown()
                ? View.INVISIBLE
                : View.VISIBLE);
    }
    public void toggle_contents3(View v) {
        answer3.setVisibility(answer3.isShown()
                ? View.INVISIBLE
                : View.VISIBLE);
    } public void toggle_contents4(View v) {
        answer4.setVisibility(answer4.isShown()
                ? View.INVISIBLE
                : View.VISIBLE);
    }
    public void toggle_contents5(View v) {
        answer5.setVisibility(answer5.isShown()
                ? View.INVISIBLE
                : View.VISIBLE);
    }

    public void toggle_contents6(View v) {
        answer6.setVisibility(answer6.isShown()
                ? View.INVISIBLE
                : View.VISIBLE);
    }
}