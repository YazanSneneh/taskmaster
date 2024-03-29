package com.firstapp.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.core.Amplify;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        getSupportActionBar().setTitle("Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor userValues = sp.edit();

        Button savePreferenceButton = findViewById(R.id.setting_user_name_button);
        savePreferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  EditText userNameView = findViewById(R.id.userName);
                  String userName= userNameView.getText().toString();
                  String name =  AWSMobileClient.getInstance().getUsername();
                  userValues.putString("name",name);
                  userValues.apply();

                Intent mainActivityIntent = new Intent(SettingsPage.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}