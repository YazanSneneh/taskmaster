package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;

import com.amplifyframework.datastore.generated.model.Status;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.util.ArrayList;
import java.util.List;

import static com.amplifyframework.datastore.generated.model.Status.HIGH;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClickListener {
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Todo ", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Todo ", "Could not initialize Amplify", e);
        }

        findViewById(R.id.signUpMain).setOnClickListener(v ->{
            Intent signUp = new Intent(this, SignUp.class);
            startActivity(signUp);
        });

        findViewById(R.id.mainLoginButton).setOnClickListener(v ->{
            Intent LogIn = new Intent(this, LogIn.class);
            startActivity(LogIn);
        });
        // Check the current auth session  :: no session i will get error yet
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );

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

        findViewById(R.id.mainSignOut).setOnClickListener(v ->{
            Amplify.Auth.signOut(
                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
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

//        List tasks = new ArrayList();
//     try {
//         Amplify.DataStore.query(Todo.class,
//        Where.matches(Todo.STATUS.eq(Status.HIGH)),
//               todos -> {
//              TaskModel task = null;
//                    while (todos.hasNext()) {
//                      Todo todo = todos.next();
//                            task.setTitle(todo.getName());
//                        System.out.println("the title is title : "+task.getTitle());
//                        if (todo.getStatus() != null) {
//                                task.setState(todo.getStatus().toString());
//                           System.out.println("the title is status: "+todo.getStatus());
//                       }
//                       if (todo.getBody() != null) {
//                             task.setBody(todo.getBody());
//                           System.out.println("the title is body: "+todo.getBody());
//                         }
//                        tasks.add(task);
//                      }
//               },
//                         failure -> Log.e("Todo", "Could not query DataStore", failure)
//                   );
//               }catch (Exception exception){
//                   Log.e("Error:", " "+ exception);
//               }
//
//        RecyclerView recyclerView = findViewById(R.id.rvTasks);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new TaskAdapter(tasks);
//        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(MainActivity.this);
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