package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

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

           findViewById(R.id.download_file).setOnClickListener(v->{
               Amplify.Storage.downloadFile(
                       "11111",
                       new File(getApplicationContext().getFilesDir() + "/11111.txt"),
                       result -> Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName()),
                       error -> Log.e("MyAmplifyApp",  "Download Failure", error)
               );
           });
    }


}