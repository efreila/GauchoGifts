package com.cs48.g12.gauchogifts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Tab3Help extends Fragment implements View.OnClickListener {

    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    TextView answer5;
    TextView answer6;
    TextView question1;
    TextView question2;
    TextView question3;
    TextView question4;
    TextView question5;
    TextView question6;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3help, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        question1 = (TextView)view.findViewById(R.id.title);
        question1.setOnClickListener(this);

        question2 = (TextView)view.findViewById(R.id.question2);
        question2.setOnClickListener(this);

        question3 = (TextView)view.findViewById(R.id.question3);
        question3.setOnClickListener(this);

        question4 = (TextView)view.findViewById(R.id.question4);
        question4.setOnClickListener(this);

        question5 = (TextView)view.findViewById(R.id.question5);
        question5.setOnClickListener(this);

        question6 = (TextView)view.findViewById(R.id.question6);
        question6.setOnClickListener(this);


        answer1 = (TextView) view.findViewById(R.id.answer1);
        // hide until its title is clicked
        answer1.setVisibility(View.INVISIBLE);

        answer2 = (TextView) view.findViewById(R.id.answer2);
        // hide until its title is clicked
        answer2.setVisibility(View.INVISIBLE);

        answer3 = (TextView) view.findViewById(R.id.answer3);
        // hide until its title is clicked
        answer3.setVisibility(View.INVISIBLE);

        answer4 = (TextView) view.findViewById(R.id.answer4);
        // hide until its title is clicked
        answer4.setVisibility(View.INVISIBLE);

        answer5 = (TextView) view.findViewById(R.id.answer5);
        // hide until its title is clicked
        answer5.setVisibility(View.INVISIBLE);

        answer6 = (TextView) view.findViewById(R.id.answer6);
        // hide until its title is clicked
        answer6.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title:
                answer1.setVisibility(answer1.isShown() ? View.INVISIBLE : View.VISIBLE);
                break;
            case R.id.question2:
                answer2.setVisibility(answer2.isShown() ? View.INVISIBLE : View.VISIBLE);
                break;
            case R.id.question3:
                answer3.setVisibility(answer3.isShown() ? View.INVISIBLE : View.VISIBLE);
                break;
            case R.id.question4:
                answer4.setVisibility(answer4.isShown() ? View.INVISIBLE : View.VISIBLE);
                break;
            case R.id.question5:
                answer5.setVisibility(answer5.isShown() ? View.INVISIBLE : View.VISIBLE);
                break;
            case R.id.question6:
                answer6.setVisibility(answer6.isShown() ? View.INVISIBLE : View.VISIBLE);
                break;
        }
    }

}
