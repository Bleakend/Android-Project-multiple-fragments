package com.example.quizzapp_i438;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizResultFragment extends Fragment {

    private static final String ARG_SCORE = "score";
    private int score;

    public QuizResultFragment() {
        // Required empty public constructor
    }

    public static QuizResultFragment newInstance(int score) {
        QuizResultFragment fragment = new QuizResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_result, container, false);

        TextView percentage_label = view.findViewById(R.id.percentage_label);
        TextView score_label = view.findViewById(R.id.score_label);

        Button main_menu_button = view.findViewById(R.id.main_menu_button);
        Button history_button = view.findViewById(R.id.history_button);
;
        score_label.setText(String.format("You got %d Out of 5 Correct", score));

        float percentage = (float) score / 5.0f * 100;
        percentage_label.setText(String.format("%.1f%%", percentage));

        main_menu_button.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
        );

        history_button.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(getActivity(), QuizHistory.class);
                    startActivity(intent);
                }
        );

        return view;

    }
}