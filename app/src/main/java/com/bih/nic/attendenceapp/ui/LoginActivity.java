package com.bih.nic.attendenceapp.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bih.nic.attendenceapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_user_name,edit_pass;
    Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ContextCompat.checkSelfPermission(
                LoginActivity.this, Manifest.permission.READ_SMS) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
           init();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.READ_SMS)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected, and what
            // features are disabled if it's declined. In this UI, include a
            // "cancel" or "no thanks" button that lets the user continue
            // using your app without granting the permission.
           // dialogForSMSDisale();
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    Manifest.permission.READ_SMS);
        }
    }

    // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher, as an instance variable.
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    init();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(this, "Please Enable SMS Permission", Toast.LENGTH_SHORT).show();
                }
            });

    private void init(){
        edit_user_name=findViewById(R.id.edit_user_name);
        edit_pass=findViewById(R.id.edit_pass);
        button_login=findViewById(R.id.button_login);
        button_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_login){
          startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }


}