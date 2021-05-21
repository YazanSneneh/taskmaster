package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // top bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Task");

        Button button = findViewById(R.id.new_task_btn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addLabel();
            }
        });
    }
    protected void addLabel(){
        TextView titleText = findViewById(R.id.add_task_db);
          String title = titleText.getText().toString();
        TextView bodyText = findViewById(R.id.add_task_discrption);
          String body = bodyText.getText().toString();
        String status = "new";

        TaskModel task = new TaskModel(title, body, status);

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "tasks_master")
                .allowMainThreadQueries().build();

        DataAccessObject tasksDao = db.tasksDao();
        tasksDao.insertOne(task);

        TextView text = findViewById(R.id.submitted);
        text.setVisibility(View.VISIBLE);

        Intent detailsIntent = new Intent(this, MainActivity.class);
          startActivity(detailsIntent);
    }
}