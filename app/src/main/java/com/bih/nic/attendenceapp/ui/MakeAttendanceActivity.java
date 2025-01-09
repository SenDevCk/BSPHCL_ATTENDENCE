package com.bih.nic.attendenceapp.ui;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.attendenceapp.R;
import com.bih.nic.attendenceapp.dto.AttendenceRequestDto;
import com.bih.nic.attendenceapp.dto.LoginResponse;
import com.bih.nic.attendenceapp.dto.LoginUserDto;
import com.bih.nic.attendenceapp.dto.MyResponse;
import com.bih.nic.attendenceapp.entities.User;
import com.bih.nic.attendenceapp.retrofit.APIClient;
import com.bih.nic.attendenceapp.retrofit.APIInterface;
import com.bih.nic.attendenceapp.retrofit.Urls_this_pro;
import com.bih.nic.attendenceapp.utilities.CommonPref;
import com.bih.nic.attendenceapp.utilities.Utiilties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeAttendanceActivity extends AppCompatActivity implements View.OnClickListener {
     Toolbar toolbar;
     Intent intent;
     ImageView imageView;
     TextView entry_time,place;
    Bitmap bitmap;
    byte[] imgData =null;
    String latitude,longitude,time_gps,in_out;
    Button btn_make_attendence;

    private APIInterface apiInterface;
    ProgressDialog progressDialog;
    private AlertDialog alertDialog = null;

    ArrayList<String> permissionsList;
    String[] permissionsStr = {Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION};
    int permissionsCount = 0;
    ActivityResultLauncher<String[]> permissionsLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    result -> {
                        ArrayList<Boolean> list = new ArrayList<>(result.values());
                        permissionsList = new ArrayList<>();
                        permissionsCount = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (shouldShowRequestPermissionRationale(permissionsStr[i])) {
                                permissionsList.add(permissionsStr[i]);
                            }else if (!hasPermission(MakeAttendanceActivity.this, permissionsStr[i])){
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
                            makeAttendenceService();
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_make_attendance);
        toolbar.setTitle("Pay here (Dec2)");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageView=findViewById(R.id.user_img);
        entry_time=findViewById(R.id.entry_time);
        place=findViewById(R.id.place);
        imageView=findViewById(R.id.user_img);
        btn_make_attendence=findViewById(R.id.btn_make_attendence);
        btn_make_attendence.setOnClickListener(this);
        intent=getIntent();
        imgData = intent.getByteArrayExtra("image");
        latitude = intent.getStringExtra("lat");
        longitude = intent.getStringExtra("lng");
        time_gps = intent.getStringExtra("gps_time");
        in_out = intent.getStringExtra("in_out");
        entry_time.setText(""+time_gps);
        bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(Utiilties.GenerateThumbnail(bitmap, 700, 500));

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_make_attendence){
            permissionsList = new ArrayList<>();
            permissionsList.addAll(Arrays.asList(permissionsStr));
            askForPermissions(permissionsList);
        }
    }




    private void makeAttendenceService() {
        btn_make_attendence.setEnabled(false);
        User user=CommonPref.getUserDetails(MakeAttendanceActivity.this);
        AttendenceRequestDto attendenceRequestDto=  AttendenceRequestDto.builder().staffId(user.getStaffId()).image(Utiilties.BitArrayToString(imgData)).latitude(latitude).longitude(longitude).inOut(in_out).build();
        apiInterface = APIClient.getClient(Urls_this_pro.RETROFIT_BASE_URL,user.getToken()).create(APIInterface.class);
        Call<MyResponse> call1 = apiInterface.makeAttendence(attendenceRequestDto);
        progressDialog = new ProgressDialog(MakeAttendanceActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        call1.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (progressDialog.isShowing()) progressDialog.dismiss();

            }
            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                btn_make_attendence.setEnabled(true);
                Log.e("error", t.getMessage());
                t.printStackTrace();
                Toast.makeText(MakeAttendanceActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) progressDialog.dismiss();
                call.cancel();
            }
        });
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
}