package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class ShareFromOutSide extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_from_out_side);

        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        // Figure out what to do based on the intent type
        if (intent.getType().indexOf("image/") != -1) {
            Log.i("content from outside", "Image Received");
        } else if (intent.getType().equals("text/plain")) {
            // Handle intents with text ...
            Log.d("content from outside", "Text");
        }
    }
}