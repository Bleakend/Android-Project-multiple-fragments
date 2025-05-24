package com.example.quizzapp_i438;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link question3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class question3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_question_text = "question_text";
    private static final String ARG_options = "options";
    private static final String ARG_correct_answer = "correct_answer";
    private String question_text;
    private String[] options;
    private String correct_answer;
    private String user_answer;

    public question3Fragment() {
        // Required empty public constructor
    }
    public static question3Fragment newInstance(String question_text, String[] options, String correct_answer) {
        question3Fragment fragment = new question3Fragment();
        Bundle args = new Bundle();
        args.putString("question_text", question_text);
        args.putStringArray("options", options);
        args.putString("correct_answer", correct_answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question_text = getArguments().getString(ARG_question_text);
            options = getArguments().getStringArray(ARG_options);
            correct_answer = getArguments().getString(ARG_correct_answer);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        update_question(question_text, options);

    }

    public void update_question(String question_text, String[] options) {
        TextView questionLabel = getView().findViewById(R.id.label_question);
        RadioButton answer1 = getView().findViewById(R.id.radio_answer1);
        RadioButton answer2 = getView().findViewById(R.id.radio_answer2);
        RadioButton answer3 = getView().findViewById(R.id.radio_answer3);

        questionLabel.setText(question_text);

        int start = new Random().nextInt(3);

        answer1.setText(options[start % 3]);
        answer2.setText(options[(start + 1) % 3]);
        answer3.setText(options[(start + 2) % 3]);
    }

    public boolean is_correct_answer(String user_answer) {
        return user_answer.equals(correct_answer);
    }

    public int submit_answer(){
        View view = getView();
        RadioGroup radio_group = view.findViewById(R.id.answers_group);
        int selected_id = radio_group.getCheckedRadioButtonId();
        if (selected_id == -1){
            Toast.makeText(getActivity(), "Please select an answer before submitting", Toast.LENGTH_LONG).show();
            return 0;
        }
        RadioButton selected_radio_button = view.findViewById(selected_id);
        String selected_answer = selected_radio_button.getText().toString();
        if (is_correct_answer(selected_answer)) return 1;
        return 0;
    }
}