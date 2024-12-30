package com.bih.nic.attendenceapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bih.nic.attendenceapp.R;
import com.bih.nic.attendenceapp.utilities.Utiilties;

public class MakeAttendanceActivity extends AppCompatActivity {
     Toolbar toolbar;
     Intent intent;
     ImageView imageView;
     TextView entry_time,place;
    Bitmap bitmap;
    String latitude,longitude,time_gps;
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
        //imageView=findViewById(R.id.user_img);
        intent=getIntent();
        byte[] imgData = intent.getByteArrayExtra("image");
        latitude = intent.getStringExtra("lat");
        longitude = intent.getStringExtra("lng");
        time_gps = intent.getStringExtra("gps_time");
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
}