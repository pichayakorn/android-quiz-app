package com.tni.ad08.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class QuizCardActivity extends AppCompatActivity {

    private static final String TAG = "QuizCardActivity";

    private TextView quizNumTV;
    private TextView questionTV;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    private String answer;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_card);

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        quizNumTV = findViewById(R.id.quiz_number);
        questionTV = findViewById(R.id.quiz_question);
        answerBtn1 = findViewById(R.id.answer_button_1);
        answerBtn2 = findViewById(R.id.answer_button_2);
        answerBtn3 = findViewById(R.id.answer_button_3);
        answerBtn4 = findViewById(R.id.answer_button_4);

        // Load data from Cloud Firestore database
        loadData();

        answerBtn1.setOnClickListener(view -> {
            if (answer.equals(answerBtn1.getText().toString())) {
                Intent intent = new Intent(QuizCardActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
        answerBtn2.setOnClickListener(view -> {
            if (answer.equals(answerBtn2.getText().toString())) {
                Intent intent = new Intent(QuizCardActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
        answerBtn3.setOnClickListener(view -> {
            if (answer.equals(answerBtn3.getText().toString())) {
                Intent intent = new Intent(QuizCardActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
        answerBtn4.setOnClickListener(view -> {
            if (answer.equals(answerBtn4.getText().toString())) {
                Intent intent = new Intent(QuizCardActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        DocumentReference docRef = db.collection("quizzes").document("TawN7TFfpaCINvmPBoq3");
        docRef.get()
                .addOnSuccessListener(documentSnapshot -> {
                    Quiz quiz = documentSnapshot.toObject(Quiz.class);

                    questionTV.setText(quiz.getQuestion());
                    answerBtn1.setText(quiz.getChoices().get("a1"));
                    answerBtn2.setText(quiz.getChoices().get("a2"));
                    answerBtn3.setText(quiz.getChoices().get("a3"));
                    answerBtn4.setText(quiz.getChoices().get("a4"));

                    answer = quiz.getAnswer();

                    Log.d(TAG, "Get data complete");

                });

    }
}