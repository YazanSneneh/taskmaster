package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // top bar setting
        getSupportActionBar().setTitle("Tasks Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

           TextView taskTitleDetail = findViewById(R.id.taskTitleDetail);
           TextView bodyDetail = findViewById(R.id.taskBodyDetail);
           TextView statusDetails = findViewById(R.id.task_status);
           String title = getIntent().getStringExtra("title");
           String body = getIntent().getStringExtra("body");
           String status = getIntent().getStringExtra("status");

           taskTitleDetail.setText(title);
           bodyDetail.setText(body);
           statusDetails.setText(status);
    }
}