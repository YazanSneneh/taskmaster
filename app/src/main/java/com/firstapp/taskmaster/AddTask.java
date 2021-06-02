package com.firstapp.taskmaster;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.aws.AWSApiPluginConfiguration;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class AddTask extends AppCompatActivity {
     File filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        findViewById(R.id.upload_file).setOnClickListener(v -> {
            TextView text = findViewById(R.id.upload_file_text);
             Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
             fileIntent.setType("*/*");

             startActivityForResult(fileIntent, 10);
            String filePathName = filePath.toString();
            text.setText(filePathName);
        });

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

    public void uploadFile(File f, String fn){
        Amplify.Storage.uploadFile(
                fn,
                f,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE){
            Amplify.Auth.handleWebUISignInResponse(data);
        }

        if(requestCode ==10){
            File file = new File(getApplicationContext().getFilesDir(),"uploads");
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
//                FileUtils.copy(inputStream, new FileOutputStream(file));
                System.out.println(filePath);
                uploadFile(file,"11111");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}