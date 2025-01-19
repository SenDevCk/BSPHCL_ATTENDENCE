package com.bih.nic.attendenceapp.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.attendenceapp.R;
import com.bih.nic.attendenceapp.dto.AttendenceRequestDto;
import com.bih.nic.attendenceapp.dto.LoginResponse;
import com.bih.nic.attendenceapp.dto.LoginUserDto;
import com.bih.nic.attendenceapp.entities.User;
import com.bih.nic.attendenceapp.retrofit.APIClient;
import com.bih.nic.attendenceapp.retrofit.APIInterface;
import com.bih.nic.attendenceapp.retrofit.Urls_this_pro;
import com.bih.nic.attendenceapp.utilities.CommonPref;
import com.bih.nic.attendenceapp.utilities.GlobalVariables;
import com.bih.nic.attendenceapp.utilities.Utiilties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_user_name, edit_pass;
    Button button_login;

    private APIInterface apiInterface;
    ProgressDialog progressDialog;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (ContextCompat.checkSelfPermission(
//                LoginActivity.this, Manifest.permission.READ_SMS) ==
//                PackageManager.PERMISSION_GRANTED) {
//            // You can use the API that requires the permission.
//            init();
//        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
//                this, Manifest.permission.READ_SMS)) {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected, and what
//            // features are disabled if it's declined. In this UI, include a
//            // "cancel" or "no thanks" button that lets the user continue
//            // using your app without granting the permission.
//            // dialogForSMSDisale();
//        } else {
//            // You can directly ask for the permission.
//            // The registered ActivityResultCallback gets the result of this request.
//            requestPermissionLauncher.launch(Manifest.permission.READ_SMS);
//        }
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

    private void init() {
        edit_user_name = findViewById(R.id.edit_user_name);
        edit_pass = findViewById(R.id.edit_pass);
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.err.println("clicked");
        if (v.getId() == R.id.button_login) {
            System.err.println("entered clicked");
            //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            loginUsingRetrofit(edit_user_name.getText().toString().trim(), edit_pass.getText().toString().trim());
        }
    }

    private void loginUsingRetrofit(String userId, String password) {
        System.err.println("clicked loginUsingRetrofit");
        LoginUserDto loginUserDto = LoginUserDto.builder().userId(userId).password(password).build();
        apiInterface = APIClient.getClient(Urls_this_pro.RETROFIT_BASE_URL).create(APIInterface.class);
        Call<LoginResponse> call1 = apiInterface.doLogin(loginUserDto);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.err.println("clicked loginUsingRetrofit response \n" + response.body());
                if (progressDialog.isShowing()) progressDialog.dismiss();
                LoginResponse result = null;
                if (response.body() != null) result = response.body();
                if (result == null) {
                    button_login.setEnabled(true);
                    alertDialog.setTitle("Login Unsuccessful");
                    alertDialog.setMessage("Try after some time !");
                    alertDialog.setButton("OK", (dialog, which) -> {
                        //edit_user_name.setFocusable(true);
                    });
                    alertDialog.show();
                } else if (result != null && result.getToken() == null) {
                    alertDialog.setTitle("Login Unsuccessful");
                    alertDialog.setMessage("" + result.getMsg());
                    alertDialog.setButton("OK", (dialog, which) -> edit_user_name.setFocusable(true));
                    alertDialog.show();

                } else {
                    Intent cPannel = new Intent(LoginActivity.this, MainActivity.class);
                    if (Utiilties.isOnline(LoginActivity.this)) {
                        if (result != null) {
                            try {
                                //result.setPassword(edit_pass.getText().toString());
                                GlobalVariables.LoggedUser = result.getData();
                                CommonPref.setUserDetails(LoginActivity.this, result.getData(), result.getToken());
                                startActivity(cPannel);
                                finish();
                            } catch (Exception ex) {
                                button_login.setEnabled(true);
                                ex.printStackTrace();
                                Toast.makeText(LoginActivity.this, "Login failed due to Some Error !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        if (CommonPref.getUserDetails(LoginActivity.this) != null) {
                            GlobalVariables.LoggedUser = result.getData();
                            if (GlobalVariables.LoggedUser.getStaffId().equalsIgnoreCase(edit_user_name.getText().toString().trim()) && GlobalVariables.LoggedUser.getPassword().equalsIgnoreCase(edit_pass.getText().toString().trim())) {
                                startActivity(cPannel);
                                finish();

                            } else {
                                button_login.setEnabled(true);
                                Toast.makeText(LoginActivity.this, "User name and password not matched !", Toast.LENGTH_LONG).show();
                                //String version1 = "App Version : " + version + " ( " + imei + " )";
                                //((TextView) findViewById(R.id.text_ver)).setText(version1);
                            }
                        } else {
                            button_login.setEnabled(true);
                            Toast.makeText(LoginActivity.this, "Please enable internet connection for first time login.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                button_login.setEnabled(true);
                Log.e("error", t.getMessage());
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) progressDialog.dismiss();
                call.cancel();
            }
        });
    }

}