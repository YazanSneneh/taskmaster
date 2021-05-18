package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
<<<<<<< HEAD
=======

        TextView userViewName = findViewById(R.id.shared_user_name);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String name = sp.getString("name","user unkown");
        userViewName.setText(name);

>>>>>>> c147ff06f02188c329a8976f05ea1fcf61b46a62
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