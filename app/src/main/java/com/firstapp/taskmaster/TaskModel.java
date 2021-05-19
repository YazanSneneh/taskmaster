package com.firstapp.taskmaster;

public class TaskModel {
    String title;
    String body;
    String state;

    public TaskModel(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}
