package com.firstapp.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class TaskModel {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String body;
    private String state;

    public TaskModel(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
