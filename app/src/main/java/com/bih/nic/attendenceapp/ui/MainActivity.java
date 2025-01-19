package com.bih.nic.attendenceapp.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.attendenceapp.R;
import com.bih.nic.attendenceapp.entities.User;
import com.bih.nic.attendenceapp.utilities.CommonPref;
import com.bih.nic.attendenceapp.utilities.Utiilties;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button check_in, check_out;
    Intent intent;



    Bitmap bitmap1, bitmap2, bitmap3;
    byte[] imageData1 = null, imageData2 = null, imageData3 = null;
    String latitude1, longitude1, gps_time1;
    String latitude2, longitude2, gps_time2;
    Handler h;
    TextView text_date, text_time,text_username,text_role;

    private static final String TAG = "HandlerThreadExample";

    private HandlerThread handlerThread;
    private Handler backgroundHandler;
    private Handler mainHandler;

    private int KEY_PIC=0;


    private AlertDialog alertDialog = null;

    ArrayList<String> permissionsList;
    String[] permissionsStr = {Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION};
    int permissionsCount = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_username = findViewById(R.id.text_username);
        text_role = findViewById(R.id.text_role);
        User user=CommonPref.getUserDetails(MainActivity.this);
        text_username.setText(""+user.getStaffName());
        text_role.setText(""+user.getRole());
        text_date = findViewById(R.id.text_date);
        text_time = findViewById(R.id.text_time);
        check_in = findViewById(R.id.check_in);
        check_out = findViewById(R.id.check_out);
        check_in.setOnClickListener(this);
        check_out.setOnClickListener(this);
        handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();

        // Create a handler for the background thread
        backgroundHandler = new Handler(handlerThread.getLooper());

        // Create a handler for the main thread (to update UI)
        mainHandler = new Handler(Looper.getMainLooper());

        // Post a task to the background thread
        backgroundHandler.post(() -> {
            Log.d(TAG, "Background task started on: " + Thread.currentThread().getName());

            try {
                // Simulate a long-running task
                while (true){
                Thread.sleep(1000);

                // Post back to the main thread to update the UI
                mainHandler.post(() -> {
                    Log.d(TAG, "Updating UI on: " + Thread.currentThread().getName());
                    updateUI();
                });
                }

            } catch (InterruptedException e) {
                Log.e(TAG, "Task interrupted", e);
                Thread.currentThread().interrupt();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_in) {
            permissionsList = new ArrayList<>();
            permissionsList.addAll(Arrays.asList(permissionsStr));
            askForPermissions(permissionsList);
            KEY_PIC=1;
        } else if (v.getId() == R.id.check_out) {
            permissionsList = new ArrayList<>();
            permissionsList.addAll(Arrays.asList(permissionsStr));
            askForPermissions(permissionsList);
            KEY_PIC=2;
        }
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent2 = new Intent(MainActivity.this, MakeAttendanceActivity.class);
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // Handle the Intent
                        byte[] imgData = data.getByteArrayExtra("CapturedImage");
                        switch (data.getIntExtra("KEY_PIC", 0)) {
                            case 1:
                                //bitmap1 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                                //met_img.setScaleType(ImageView.ScaleType.FIT_XY);
                                //met_img.setImageBitmap(Utiilties.GenerateThumbnail(bitmap1, 700, 500));
                                imageData1 = imgData;
                                latitude1 = data.getStringExtra("Lat");
                                longitude1 = data.getStringExtra("Lng");
                                gps_time1 = data.getStringExtra("GPSTime");
                                intent2.putExtra("lat", latitude1);
                                intent2.putExtra("lang", longitude1);
                                intent2.putExtra("gps_time", gps_time1);
                                intent2.putExtra("image", imageData1);
                                intent2.putExtra("in_out", "I");
                                intent2.putExtra("key_pic", data.getIntExtra("KEY_PIC", 0));
                                break;
                            case 2:
                                //bitmap2 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                                //ser_wire_img.setScaleType(ImageView.ScaleType.FIT_XY);
                                //ser_wire_img.setImageBitmap(Utiilties.GenerateThumbnail(bitmap2, 700, 500));
                                imageData2 = imgData;
                                latitude2 = data.getStringExtra("Lat");
                                longitude2 = data.getStringExtra("Lng");
                                gps_time2 = data.getStringExtra("GPSTime");
                                intent2.putExtra("lat", latitude2);
                                intent2.putExtra("lang", longitude2);
                                intent2.putExtra("gps_time", gps_time2);
                                intent2.putExtra("image", imageData2);
                                intent2.putExtra("in_out", "O");
                                intent2.putExtra("key_pic", data.getIntExtra("KEY_PIC", 0));
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "KEY_PIC not found !", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        startActivity(intent2);
                    }
                }
            });

    ActivityResultLauncher<String[]> permissionsLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    result -> {
                        ArrayList<Boolean> list = new ArrayList<>(result.values());
                        permissionsList = new ArrayList<>();
                        permissionsCount = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (shouldShowRequestPermissionRationale(permissionsStr[i])) {
                                permissionsList.add(permissionsStr[i]);
                            }else if (!hasPermission(MainActivity.this, permissionsStr[i])){
                                permissionsCount++;
                            }
                        }
                        if (permissionsList.size() > 0) {
                            //Some permissions are denied and can be asked again.
                            askForPermissions(permissionsList);
                        } else if (permissionsCount > 0) {
                            //Show alert dialog
                            showPermissionDialog();
                        } else {
                            //All permissions granted. Do your stuff
                            intent = new Intent(MainActivity.this, CameraActivity.class);
                            intent.putExtra("KEY_PIC", String.valueOf(KEY_PIC));
                            mStartForResult.launch(intent);
                        }
                    });

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void updateUI() {
        text_date.setText("" + Utiilties.getCurrentDate());
        Calendar calendar = Calendar.getInstance();
        text_time.setText("" + String.valueOf(calendar.getTime()).substring(0,19));
    }

    private void askForPermissions(ArrayList<String> permissionsList) {
        String[] newPermissionStr = new String[permissionsList.size()];
        for (int i = 0; i < newPermissionStr.length; i++) {
            newPermissionStr[i] = permissionsList.get(i);
        }
        if (newPermissionStr.length > 0) {
            permissionsLauncher.launch(newPermissionStr);
        } else {
        /* User has pressed 'Deny & Don't ask again' so we have to show the enable permissions dialog
        which will lead them to app details page to enable permissions from there. */
            showPermissionDialog();
        }
    }

    private void showPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission required")
                .setMessage("Some permissions are need to be allowed to use this app without any problems.")
                .setPositiveButton("Settings", (dialog, which) -> {
                    dialog.dismiss();
                });
        if (alertDialog == null) {
            alertDialog = builder.create();
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }
        }
    }

    private boolean hasPermission(Context context, String permissionStr) {
        return ContextCompat.checkSelfPermission(context, permissionStr) == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the HandlerThread
        if (handlerThread != null) {
            handlerThread.quitSafely();
            handlerThread = null;
        }
    }


}