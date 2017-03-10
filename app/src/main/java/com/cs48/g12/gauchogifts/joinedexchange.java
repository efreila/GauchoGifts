package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.Date;

public class joinedexchange extends AppCompatActivity {

    private TextView acTitle;
    private TextView acDeadline;
    private TextView acDescription;
    private EditText questionOneAnswer;
    private EditText questionTwoAnswer;
    private EditText questionThreeAnswer;
    private EditText questionFourAnswer;
    private EditText questionFiveAnswer;
    private EditText questionSixAnswer;
    private TextView questionTwo;
    private TextView questionThree;
    private TextView questionFour;
    private TextView questionFive;
    private TextView questionSix;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinedexchange);

        acTitle = (TextView)findViewById(R.id.geTitleJ);
        acDeadline = (TextView)findViewById(R.id.geDeadlineJ);
        acDescription = (TextView)findViewById(R.id.geDescriptonJ);
        questionOneAnswer = (EditText)findViewById(R.id.q1AnsJ);
        questionTwoAnswer = (EditText)findViewById(R.id.q2AnsJ);
        questionThreeAnswer = (EditText)findViewById(R.id.q3AnsJ);
        questionFourAnswer = (EditText)findViewById(R.id.q4AnsJ);
        questionFiveAnswer = (EditText)findViewById(R.id.q5AnsJ);
        questionSixAnswer = (EditText)findViewById(R.id.q6AnsJ);
        questionTwo = (TextView)findViewById(R.id.question2J);
        questionThree = (TextView)findViewById(R.id.question3J);
        questionFour = (TextView)findViewById(R.id.question4J);
        questionFive = (TextView)findViewById(R.id.question5J);
        questionSix = (TextView)findViewById(R.id.question6J);

        Bundle geinfo = getIntent().getExtras();
        String exchangeTitle = geinfo.getString("Title");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //When the activity is loaded the specific title of the exchange chosen is displayed
        String exTitle = geinfo.getString("Title");
        acTitle.setText(exTitle);
        //When the activity is loaded the deadline of the exchange chosen is displayed
        String exDeadline = geinfo.getString("Deadline");
        acDeadline.setText(exDeadline);
        //When the activity is loaded the description of the exchange chosen is displayed
        String exDescription = geinfo.getString("Description");
        acDescription.setText(exDescription);
        //When the activity is loaded, the first gift exchanges survey question is displayed
        String exQuestionOne = geinfo.getString("QuestionOne");
        questionTwo.setText(exQuestionOne);
        //When the activity is loaded, the second gift exchanges survey question is displayed
        String exQuestionTwo = geinfo.getString("QuestionTwo");
        questionThree.setText(exQuestionTwo);
        //When the activity is loaded, the third gift exchanges survey question is displayed
        String exQuestionThree = geinfo.getString("QuestionThree");
        questionFour.setText(exQuestionThree);
        //When the activity is loaded, the fourth gift exchanges survey question is displayed
        String exQuestionFour = geinfo.getString("QuestionFour");
        questionFive.setText(exQuestionFour);
        //When the activity is loaded, the fifth gift exchanges survey question is displayed
        String exQuestionFive = geinfo.getString("QuestionFive");
        questionSix.setText(exQuestionFive);

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle +  "/Enrolled Users/" + uid + "/Questions/General Info");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String generalinfo = dataSnapshot.getValue(String.class);

                questionOneAnswer.setText(generalinfo);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle +  "/Enrolled Users/" + uid + "/Questions/Question1");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String question1ans = dataSnapshot.getValue(String.class);

                questionTwoAnswer.setText(question1ans);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle +  "/Enrolled Users/" + uid + "/Questions/Question2");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String question2ans = dataSnapshot.getValue(String.class);

                questionThreeAnswer.setText(question2ans);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle +  "/Enrolled Users/" + uid + "/Questions/Question3");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String question3ans = dataSnapshot.getValue(String.class);

                questionFourAnswer.setText(question3ans);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle +  "/Enrolled Users/" + uid + "/Questions/Question4");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String question4ans = dataSnapshot.getValue(String.class);

                questionFiveAnswer.setText(question4ans);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef = new Firebase("https://gauchogifts.firebaseio.com/Exchanges/" + exchangeTitle +  "/Enrolled Users/" + uid + "/Questions/Question5");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String question5ans = dataSnapshot.getValue(String.class);

                questionSixAnswer.setText(question5ans);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Button quit = (Button)findViewById(R.id.quitBtn);
        //if before deadline
        quit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                quitExchange();
            }
        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void quitExchange() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Bundle geinfo = getIntent().getExtras();
        String exchangeTitle = geinfo.getString("Title");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(uid).setValue(null);
        myRef.child("Users").child(uid).child("Current Exchanges").child(exchangeTitle).setValue(null);

        Intent myIntent = new Intent(joinedexchange.this, navheader.class);
        startActivity(myIntent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(joinedexchange.this, navheader.class);
        startActivity(myIntent);
    }
}
