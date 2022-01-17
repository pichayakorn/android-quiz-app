package com.tni.ad08.quizapp;

import java.util.Map;

public class Quiz {
    private String question;
    private String answer;
    private Map<String, String> choices;

    public Quiz() {
    }

    public Quiz(String question, String answer, Map<String, String> choices) {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Map<String, String> getChoices() {
        return choices;
    }
}
