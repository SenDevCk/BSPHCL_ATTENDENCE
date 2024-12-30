package com.bih.nic.attendenceapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.bih.nic.attendenceapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startMyActivity();
    }

    private void startMyActivity() {
        new Handler().postAtTime(() -> startActivity(new Intent(SplashActivity.this,LoginActivity.class)),5000);
    }
}