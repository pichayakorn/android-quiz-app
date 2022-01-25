package com.tni.ad08.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private ImageView scoreStar1, scoreStar2, scoreStar3;
    private TextView totalScoreTV;
    private TextView percentScoreTV;
    private TextView correctScoreTV;
    private TextView wrongScoreTV;

    private Button mainMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        scoreStar1 = findViewById(R.id.score_star_show1);
        scoreStar2 = findViewById(R.id.score_star_show2);
        scoreStar3 = findViewById(R.id.score_star_show3);

        totalScoreTV = findViewById(R.id.score_text_showTotalScore);
        percentScoreTV = findViewById(R.id.score_text_showPercent);
        correctScoreTV = findViewById(R.id.score_text_showCorrectAns);
        wrongScoreTV = findViewById(R.id.score_text_showWrongAns);

        mainMenuBtn = findViewById(R.id.main_menu_btn);

        // Get intent
        Intent receiveIntent = getIntent();
        String mScore = receiveIntent.getStringExtra(QuizCardActivity.EXTRA_MY_SCORE);
        String fullScore = receiveIntent.getStringExtra(QuizCardActivity.EXTRA_FULL_SCORE);
        String wrongScore = Integer.toString(Integer.parseInt(fullScore) - Integer.parseInt(mScore));

        // Set view
        String totalScoreText = mScore + "/" + fullScore;
        totalScoreTV.setText(totalScoreText);

        int percentScore = Integer.parseInt(mScore) * 100 / Integer.parseInt(fullScore);
        String percentScoreText = percentScore + "%";
        percentScoreTV.setText(percentScoreText);

        correctScoreTV.setText(mScore);
        wrongScoreTV.setText(wrongScore);

        if (percentScore < 33) {
            scoreStar1.setVisibility(View.INVISIBLE);
            scoreStar2.setVisibility(View.INVISIBLE);
            scoreStar3.setVisibility(View.INVISIBLE);
        } else if (percentScore < 66) {
            scoreStar1.setVisibility(View.VISIBLE);
            scoreStar2.setVisibility(View.INVISIBLE);
            scoreStar3.setVisibility(View.INVISIBLE);
        } else if (percentScore < 99) {
            scoreStar1.setVisibility(View.VISIBLE);
            scoreStar2.setVisibility(View.VISIBLE);
            scoreStar3.setVisibility(View.INVISIBLE);
        } else {
            scoreStar1.setVisibility(View.VISIBLE);
            scoreStar2.setVisibility(View.VISIBLE);
            scoreStar3.setVisibility(View.VISIBLE);
        }

        mainMenuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SummaryActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }
}