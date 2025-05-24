package com.example.quizzapp_i438;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_quiz_button = findViewById(R.id.button_start_quiz);
        Button view_history_button = findViewById(R.id.button_view_history);

        start_quiz_button.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(MainActivity.this, Quiz.class);
                    startActivity(intent);
                }
        );

        view_history_button.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(MainActivity.this, QuizHistory.class);
                    startActivity(intent);
                }
        );
    }
}
