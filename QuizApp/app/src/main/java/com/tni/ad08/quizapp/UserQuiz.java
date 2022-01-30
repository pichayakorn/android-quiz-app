package com.tni.ad08.quizapp;

import com.google.firebase.Timestamp;

public class UserQuiz {
    private String username;
    private Timestamp timestamp;

    public UserQuiz() {
    }

    public UserQuiz(String username, Timestamp timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
