package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClickListener {
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Main Page");
        Button addTask = findViewById(R.id.button1);
        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addTasks();
            }
        });

        Button allTasks = findViewById(R.id.button2);
        allTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewAllTasks();
            };
        });

        TextView userViewName = findViewById(R.id.shared_user_name);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String name = sp.getString("name","user unkown");
        userViewName.setText(name.trim()+"'s Tasks");

        // Room Client
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "tasks_master")
                .allowMainThreadQueries().build();

        DataAccessObject tasksDao = db.tasksDao();
         List tasks = tasksDao.getAllTasks();
        RecyclerView recyclerView = findViewById(R.id.rvTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailsIntent = new Intent(this, TaskDetail.class);
        // Room Client
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "tasks_master")
                .allowMainThreadQueries().build();

        DataAccessObject tasksDao = db.tasksDao();
        TaskModel task = tasksDao.getAllTasks().get(position);
        detailsIntent.putExtra("title",task.getTitle());
        detailsIntent.putExtra("body",task.getBody());
        detailsIntent.putExtra("status",task.getState());
        startActivity(detailsIntent);
    }

    protected void addTasks(){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }

    protected void viewAllTasks(){
        Intent intent = new Intent(this, AllTasks.class);
        startActivity(intent);
    };

    public void TaskDetailRedirect(View view) {
        Intent taskDetailIntent = new Intent(this, TaskDetail.class);
        Button button =(Button)view;
        String title = button.getText().toString();
        taskDetailIntent.putExtra("title",title);
        startActivity(taskDetailIntent);
    }

    public void goToSettingPage(View view) {
        Intent settingIntent = new Intent(this,SettingsPage.class);
        startActivity(settingIntent);
    }
}