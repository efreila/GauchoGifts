package com.cs48.g12.gauchogifts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class giftexchange extends AppCompatActivity {

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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftexchange);

        acTitle = (TextView)findViewById(R.id.geTitle);
        acDeadline = (TextView)findViewById(R.id.geDeadline);
        acDescription = (TextView)findViewById(R.id.geDescripton);
        mAuth = FirebaseAuth.getInstance();
        questionOneAnswer = (EditText)findViewById(R.id.q1Ans);
        questionTwoAnswer = (EditText)findViewById(R.id.q2Ans);
        questionThreeAnswer = (EditText)findViewById(R.id.q3Ans);
        questionFourAnswer = (EditText)findViewById(R.id.q4Ans);
        questionFiveAnswer = (EditText)findViewById(R.id.q5Ans);
        questionSixAnswer = (EditText)findViewById(R.id.q6Ans);
        questionTwo = (TextView)findViewById(R.id.question2);
        questionThree = (TextView)findViewById(R.id.question3);
        questionFour = (TextView)findViewById(R.id.question4);
        questionFive = (TextView)findViewById(R.id.question5);
        questionSix = (TextView)findViewById(R.id.question6);

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

        Button join = (Button)findViewById(R.id.joinBtn);
        //if before deadline
        join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkFields();
            }
        });
        //else (after deadline)

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    // NOTE: this Activity should get open only when the user is not signed in, otherwise
//                    // the user will receive another verification email.
//                } else {
//                    // User is signed out
//                }
//                // ...
//            }
//        };

        //Makes sure keyboard doesn't pop up when window loads
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void checkFields() {
        if(TextUtils.isEmpty(questionTwoAnswer.getText().toString().trim())) {
            Toast.makeText(giftexchange.this, "Please make sure all required fields are filled out.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(questionThreeAnswer.getText().toString().trim())) {
            Toast.makeText(giftexchange.this, "Please make sure all required fields are filled out.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(questionFourAnswer.getText().toString().trim())) {
            Toast.makeText(giftexchange.this, "Please make sure all required fields are filled out.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(questionFiveAnswer.getText().toString().trim())) {
            Toast.makeText(giftexchange.this, "Please make sure all required fields are filled out.", Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(questionSixAnswer.getText().toString().trim())) {
            Toast.makeText(giftexchange.this, "Please make sure all required fields are filled out.", Toast.LENGTH_LONG).show();
            return;
        }

        else
            joinGiftExchange();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    private void joinGiftExchange() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Bundle geinfo = getIntent().getExtras();
        String exchangeTitle = geinfo.getString("Title");

        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").setValue("Hello");
        myRef.child("Users").child(mAuth.getCurrentUser().getUid()).child("Current Exchanges").child(exchangeTitle).setValue(exchangeTitle);

        //Store User's survey answers in database
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").child("General Info").setValue(questionOneAnswer.getText().toString().trim());
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").child("Question1").setValue(questionTwoAnswer.getText().toString().trim());
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").child("Question2").setValue(questionThreeAnswer.getText().toString().trim());
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").child("Question3").setValue(questionFourAnswer.getText().toString().trim());
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").child("Question4").setValue(questionFiveAnswer.getText().toString().trim());
        myRef.child("Exchanges").child(exchangeTitle).child("Enrolled Users").child(mAuth.getCurrentUser().getUid()).child("Questions").child("Question5").setValue(questionSixAnswer.getText().toString().trim());

        Intent myIntent = new Intent(giftexchange.this, navheader.class);
        startActivity(myIntent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(giftexchange.this, navheader.class);
        startActivity(myIntent);
    }
}
