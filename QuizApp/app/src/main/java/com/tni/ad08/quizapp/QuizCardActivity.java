package com.tni.ad08.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Date;


public class QuizCardActivity extends AppCompatActivity {

    private static final String TAG = "QuizCardActivity";
    public static final String EXTRA_MY_SCORE = "com.tni.ad08.quizapp.extra.MY_SCORE";
    public static final String EXTRA_FULL_SCORE = "com.tni.ad08.quizapp.extra.FULL_SCORE";
    private UserQuiz userQuizData = new UserQuiz();
    private ArrayList<UserQuestion> userQuestionData = new ArrayList<>();

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

        answerBtn1.setOnClickListener(this::choiceBtnHandler);
        answerBtn2.setOnClickListener(this::choiceBtnHandler);
        answerBtn3.setOnClickListener(this::choiceBtnHandler);
        answerBtn4.setOnClickListener(this::choiceBtnHandler);
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

                    // Collect user data
                    userQuestionData.add(number, new UserQuestion());
                    userQuestionData.get(number).setQuestion(quiz.getQuestion());
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

    private void addData() {
        db.collection("user-history")
                .add(userQuizData)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    for (int i = 0; i < number + 1; ++i) {
                    db.collection("user-history").document(documentReference.getId()).collection("question")
                            .add(userQuestionData.get(i))
                            .addOnSuccessListener(subDocRef -> {
                                Log.d(TAG, "SubDocumentSnapshot written with ID: " + subDocRef.getId());
                            })
                            .addOnFailureListener(e -> Log.w(TAG, "Error adding inner document", e));
                    }
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

        Log.d(TAG, "Adding user history data successfully");
    }

    private void choiceBtnHandler(View view) {
        // Collect user data
        Button btn = (Button) view;
        userQuestionData.get(number).setUserAnswer(btn.getText().toString());

        if (checkAnswer(btn.getText().toString())) {
            score++;
            // Collect user data
            userQuestionData.get(number).setCorrect(true);
        } else {
            userQuestionData.get(number).setCorrect(false);
        }

        if (number < docRef.size() - 1) {
            number++;
            setData();
        } else {
            // Collect user quiz data
            userQuizData.setUsername("nattawat007@gmail.com");
            userQuizData.setTimestamp(new Timestamp(new Date()));
            addData();
            lunchSummaryActivity();
        }
    }
}