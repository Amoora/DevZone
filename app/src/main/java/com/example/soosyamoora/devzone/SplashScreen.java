package com.example.soosyamoora.devzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread background = new Thread() {
            public void run() {
                try {
//                    Thread will sleep for 5 seconds
                    sleep(6 * 1000);

//                    after 5 seconds redirect to login activity
                    Intent i = new Intent(getBaseContext(), SignInActivity.class);
                    startActivity(i);

//                    remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
//        start thread
        background.start();
    }
}
