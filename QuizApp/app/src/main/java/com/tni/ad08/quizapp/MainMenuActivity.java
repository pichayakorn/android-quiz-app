package com.tni.ad08.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    private ImageView mathBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mathBtn = findViewById(R.id.menu_btn_math);
        mathBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, QuizCardActivity.class);
            startActivity(intent);
        });
    }
}