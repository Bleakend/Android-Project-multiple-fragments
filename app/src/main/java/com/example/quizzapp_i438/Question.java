package com.example.quizzapp_i438;

public class Question {
    private final String question;
    private final String[] options;
    private final String correct_answer;

    public Question(String question, String[] options, String correct_answer){
        this.question = question;
        this.options = options;
        this.correct_answer = correct_answer;
    }

    public String get_question() {
        return question;
    }

    public String[] get_options() {
        return options;
    }

    public String get_correct_answer() {
        return correct_answer;
    }
}
