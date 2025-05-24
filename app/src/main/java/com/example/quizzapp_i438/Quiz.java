package com.example.quizzapp_i438;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Quiz extends AppCompatActivity {
    private FrameLayout fragment_container;
    private FirebaseFirestore database;
    public int question_index = 0;
    public int correct_answers = 0;
    public ArrayList<Question> question_list = new ArrayList<>();
    public Question current_question;
    private Fragment fragment;
    private ScoreStorage score_storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        fragment_container = findViewById(R.id.fragment_container);
        Button submit_answer_button = findViewById(R.id.button_submit_answer);
        TextView question_number_label = findViewById(R.id.question_number_label);
        score_storage = new ScoreStorage(this);

        FirebaseApp.initializeApp(this);
        database = FirebaseFirestore.getInstance();

        database.collection("questions")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        List<DocumentSnapshot> question_documents = querySnapshot.getDocuments();
                        Collections.shuffle(question_documents);
                        for (int i = 0; i < 5; i++) {
                            DocumentSnapshot random_question = question_documents.get(i);

                            String question_text = random_question.getString("question");
                            String answer1_text = random_question.getString("answer1");
                            String answer2_text = random_question.getString("answer2");
                            String correct_answer_text = random_question.getString("correct_answer");
                            String[] options = {answer1_text, answer2_text, correct_answer_text};
                            question_list.add(new Question(question_text, options, correct_answer_text));
                        }
                        current_question = question_list.get(0);
                        fragment = question1Fragment.newInstance(
                                current_question.get_question(),
                                current_question.get_options(),
                                current_question.get_correct_answer()
                        );
                        load_fragment(fragment);
                        submit_answer_button.setVisibility(View.VISIBLE);
                    }
                });
        submit_answer_button.setOnClickListener(
                (v) -> {
                    if(question_index == 0) {
                        correct_answers += ((question1Fragment) fragment).submit_answer();
                    }
                    else if(question_index == 1){
                        correct_answers += ((question2Fragment) fragment).submit_answer();
                    }
                    else if(question_index == 2){
                        correct_answers += ((question3Fragment) fragment).submit_answer();
                    }
                    else if(question_index == 3){
                        correct_answers += ((question4Fragment) fragment).submit_answer();
                    }
                    else if(question_index == 4){
                        correct_answers += ((question5Fragment) fragment).submit_answer();
                    }

                    question_index += 1;
                    question_number_label.setText(String.format("Question %s of 5", String.valueOf(question_index + 1)));
                    if (question_index == question_list.size()) {
                        score_storage.saveQuizScore(correct_answers);
                        question_number_label.setVisibility(View.INVISIBLE);
                        Fragment result_fragment = QuizResultFragment.newInstance(correct_answers);
                        load_fragment(result_fragment);
                    } else {
                        //Show next question
                        current_question = question_list.get(question_index);
                        if(question_index == 1){
                            fragment = question2Fragment.newInstance(
                                    current_question.get_question(),
                                    current_question.get_options(),
                                    current_question.get_correct_answer()
                            );
                        } else if (question_index == 2) {
                            fragment = question3Fragment.newInstance(
                                    current_question.get_question(),
                                    current_question.get_options(),
                                    current_question.get_correct_answer()
                            );
                        }else if (question_index == 3) {
                            fragment = question4Fragment.newInstance(
                                    current_question.get_question(),
                                    current_question.get_options(),
                                    current_question.get_correct_answer()
                            );
                        }else if (question_index == 4) {
                            fragment = question5Fragment.newInstance(
                                    current_question.get_question(),
                                    current_question.get_options(),
                                    current_question.get_correct_answer()
                            );
                        }

                        load_fragment(fragment);
                    }
                }

        );
    }

    public void load_fragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}
