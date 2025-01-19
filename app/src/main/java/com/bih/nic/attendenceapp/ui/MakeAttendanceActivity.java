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
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.attendenceapp.R;
import com.bih.nic.attendenceapp.database.AppDatabase;
import com.bih.nic.attendenceapp.database.AttendanceDao;
import com.bih.nic.attendenceapp.database.DatabaseAttendenceClient;
import com.bih.nic.attendenceapp.dto.AttendenceRequestDto;
import com.bih.nic.attendenceapp.dto.LoginResponse;
import com.bih.nic.attendenceapp.dto.LoginUserDto;
import com.bih.nic.attendenceapp.dto.MyResponse;
import com.bih.nic.attendenceapp.dto.UserAttendenceDto;
import com.bih.nic.attendenceapp.entities.User;
import com.bih.nic.attendenceapp.entities.UserAttendence;
import com.bih.nic.attendenceapp.retrofit.APIClient;
import com.bih.nic.attendenceapp.retrofit.APIInterface;
import com.bih.nic.attendenceapp.retrofit.Urls_this_pro;
import com.bih.nic.attendenceapp.utilities.CommonPref;
import com.bih.nic.attendenceapp.utilities.Utiilties;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeAttendanceActivity extends AppCompatActivity implements View.OnClickListener {
     Toolbar toolbar;
     Intent intent;
     ImageView imageView;
     TextView entry_time,place,name;
    Bitmap bitmap;
    byte[] imgData =null;
    String latitude,longitude,time_gps,in_out;
    Button btn_make_attendence;

    private APIInterface apiInterface;
    User user=null;
    ProgressDialog progressDialog;

    AppDatabase db;

    @Override
    protected void onStart() {
        super.onStart();
        db = DatabaseAttendenceClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_make_attendance);
        toolbar.setTitle("Make Attendence");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        user=CommonPref.getUserDetails(MakeAttendanceActivity.this);
        imageView=findViewById(R.id.user_img);
        entry_time=findViewById(R.id.entry_time);
        name=findViewById(R.id.name);
        place=findViewById(R.id.place);
        imageView=findViewById(R.id.user_img);
        btn_make_attendence=findViewById(R.id.btn_make_attendence);
        btn_make_attendence.setOnClickListener(this);
        intent=getIntent();
        imgData = intent.getByteArrayExtra("image");
        latitude = intent.getStringExtra("lat");
        longitude = intent.getStringExtra("lang");
        time_gps = intent.getStringExtra("gps_time");
        in_out = intent.getStringExtra("in_out");
        entry_time.setText(""+time_gps);
        name.setText(""+user.getStaffName());
        place.setText(""+getAddressFromLatLong(latitude,longitude));
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
            makeAttendenceService();
        }
    }


    private void makeAttendenceService() {
        btn_make_attendence.setEnabled(false);
        AttendenceRequestDto attendenceRequestDto=  AttendenceRequestDto.builder()
                .staffId(user.getStaffId()).locId(user.getLocationId())
                .image(Utiilties.BitArrayToString(imgData)).latitude(latitude).
                longitude(longitude).inOut(in_out).build();
        apiInterface = APIClient.getClient(Urls_this_pro.RETROFIT_BASE_URL,CommonPref.getToken(MakeAttendanceActivity.this)).create(APIInterface.class);
        Call<MyResponse> call1 = apiInterface.makeAttendence(attendenceRequestDto);
        progressDialog = new ProgressDialog(MakeAttendanceActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        call1.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (progressDialog.isShowing()) progressDialog.dismiss();
                 MyResponse data=response.body();
                 if(data!=null){
                     LinkedTreeMap<String,Object> adata= (LinkedTreeMap) data.getData();
                     System.out.println(adata);
                     System.out.println(adata.toString());
                     UserAttendenceDto userAttendence =new UserAttendenceDto();
                     for (Map.Entry<String, Object> entry : adata.entrySet()) {
                         System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                         switch (entry.getKey()){
                             case "attendenceId":
                                 userAttendence.setAttendenceId(entry.getValue().toString());
                                 break;
                             case "inOut":
                                 userAttendence.setInOut(entry.getValue().toString());
                                 break;
                             case "urlImage":
                                 userAttendence.setUrlImage(entry.getValue().toString());
                                 break;
                             case "inOutTime":
                                 //userAttendence.setInOutTime(entry.getValue().toString());
                                 break;
                             case "latitude":
                                 userAttendence.setLatitude(Double.parseDouble(entry.getValue().toString()));
                                 break;
                             case "longitude":
                                 userAttendence.setLongitude(Double.parseDouble(entry.getValue().toString()));
                                 break;
                             default:
                                 break;

                         }
                     }
                     System.out.println(userAttendence);
                     Calendar calendar = Calendar.getInstance();
                     ExecutorService executor = Executors.newSingleThreadExecutor();
                     executor.execute(() -> {
                         db.attendanceDao()
                                 .insert(UserAttendence.builder().attendenceId(userAttendence.getAttendenceId())
                                         .inOutTime(calendar.getTime().toString())
                                         .urlImage(userAttendence.getUrlImage())
                                         .latitude(userAttendence.getLatitude())
                                         .longitude(userAttendence.getLongitude()).build()
                                 );
                     });
                     executor.shutdown();
                     Toast.makeText(MakeAttendanceActivity.this, "Done", Toast.LENGTH_SHORT).show();
                     finish();
                 }else{
                     Toast.makeText(MakeAttendanceActivity.this, "Data not Found !", Toast.LENGTH_SHORT).show();
                 }
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


    private String getAddressFromLatLong(String lat,String lang){

        Geocoder geocoder;
        List<Address> addresses;
        String finalAddress="";
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lang), 1);
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            finalAddress=new StringBuilder().append(address).append(", ")
                    .append(city).append(", ").append(state).append(", ").append(country).append(", ").append(postalCode).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return finalAddress;
    }
}