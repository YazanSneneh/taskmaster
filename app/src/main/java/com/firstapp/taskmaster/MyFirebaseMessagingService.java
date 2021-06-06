package com.firstapp.taskmaster;
import android.app.Service;
import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {
    @Override
    public void onNewToken(String token) {
        Log.d("TAG : ", "Refreshed token: " + token);
    }


//FirebaseMessaging.getInstance().getToken()
//    .addOnCompleteListener(new OnCompleteListener<String>() {
//        @Override
//        public void onComplete(@NonNull Task<String> task) {
//            if (!task.isSuccessful()) {
//                Log.w("notification token : ", "Fetching FCM registration token failed", task.getException());
//                return;
//            }
//
//            // Get new FCM registration token
//            String token = task.getResult();
//
//            // Log and toast
//            String msg = getString(R.string.msg_token_fmt, token);
//            Log.d("notification recieved ", msg);
//            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//        }
//    });



}
