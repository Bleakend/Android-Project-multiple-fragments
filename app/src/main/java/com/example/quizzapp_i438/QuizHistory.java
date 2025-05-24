package com.example.quizzapp_i438;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.utilities.Score;

public class QuizHistory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_history);

        HistogramView histogramView = findViewById(R.id.histogramView);
        Button main_menu_button = findViewById(R.id.main_menu_button);
        Button clear_history = findViewById(R.id.clear_history);

        ScoreStorage scoreStorage = new ScoreStorage(this);

        main_menu_button.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(QuizHistory.this, MainActivity.class);
                    startActivity(intent);
                }
        );

        clear_history.setOnClickListener(
                (v) -> {
                    scoreStorage.clearSharedPreference();
                    histogramView.clearQuizScore();
                }
        );

        int[] quiz_score = scoreStorage.getAllScores();
        histogramView.setScores(quiz_score);
    }
}