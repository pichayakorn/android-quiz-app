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

import java.util.ArrayList;


public class QuizCardActivity extends AppCompatActivity {

    private static final String TAG = "QuizCardActivity";
    public static final String EXTRA_MY_SCORE = "com.tni.ad08.quizapp.extra.MY_SCORE";
    public static final String EXTRA_FULL_SCORE = "com.tni.ad08.quizapp.extra.FULL_SCORE";

    private TextView quizNumTV;
    private TextView questionTV;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    private String answer;

    private int number;
    private int score;

    FirebaseFirestore db;
    ArrayList<DocumentReference> docRef = new ArrayList<>();

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

        number = 0;
        score = 0;

        // Load data from Cloud Firestore database
        loadData();
        // Set data from loaded Cloud Firestore database
        setData();

        answerBtn1.setOnClickListener(view -> {
            if (checkAnswer(answerBtn1.getText().toString())) {
                score++;
            }

            if (number < docRef.size() - 1) {
                number++;
                setData();
            } else {
                lunchSummaryActivity();
            }
        });
        answerBtn2.setOnClickListener(view -> {
            if (checkAnswer(answerBtn2.getText().toString())) {
                score++;
            }

            if (number < docRef.size() - 1) {
                number++;
                setData();
            } else {
                lunchSummaryActivity();
            }
        });
        answerBtn3.setOnClickListener(view -> {
            if (checkAnswer(answerBtn3.getText().toString())) {
                score++;
            }

            if (number < docRef.size() - 1) {
                number++;
                setData();
            } else {
                lunchSummaryActivity();
            }
        });
        answerBtn4.setOnClickListener(view -> {
            if (checkAnswer(answerBtn4.getText().toString())) {
                score++;
            }

            if (number < docRef.size() - 1) {
                number++;
                setData();
            } else {
                lunchSummaryActivity();
            }
        });
    }

    private void loadData() {
        docRef.add(0, db.collection("quizzes").document("WzPnaUI8X1KS4CScezvV"));
        docRef.add(1, db.collection("quizzes").document("TawN7TFfpaCINvmPBoq3"));
        docRef.add(2, db.collection("quizzes").document("r96bVy6KKQAZRJxeMy1T"));
    }

    private void setData() {
        // Set question number
        String mNumber = (number + 1) + "/" + docRef.size();
        quizNumTV.setText(mNumber);

        docRef.get(number).get()
                .addOnSuccessListener(documentSnapshot -> {
                    Quiz quiz = documentSnapshot.toObject(Quiz.class);

                    questionTV.setText(quiz.getQuestion());
                    answerBtn1.setText(quiz.getChoices().get("a1"));
                    answerBtn2.setText(quiz.getChoices().get("a2"));
                    answerBtn3.setText(quiz.getChoices().get("a3"));
                    answerBtn4.setText(quiz.getChoices().get("a4"));

                    answer = quiz.getAnswer();

                    Log.d(TAG, "Set data complete");
                });
    }

    private boolean checkAnswer(String ans) {
        return answer.equals(ans);
    }

    private void lunchSummaryActivity() {
        Intent intent = new Intent(QuizCardActivity.this, SummaryActivity.class);
        String mScore = Integer.toString(score);
        intent.putExtra(EXTRA_MY_SCORE, mScore);
        intent.putExtra(EXTRA_FULL_SCORE, Integer.toString(docRef.size()));
        startActivity(intent);
    }
}