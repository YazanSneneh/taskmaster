package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Tasks");
        setContentView(R.layout.activity_all_tasks);
    }
}