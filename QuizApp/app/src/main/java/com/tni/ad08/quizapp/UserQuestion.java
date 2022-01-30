package com.tni.ad08.quizapp;

public class UserQuestion {
    private String question;
    private String userAnswer;
    private boolean correct;

    public UserQuestion() {
    }

    public UserQuestion(String question, String userAnswer, boolean correct) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
