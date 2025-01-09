package com.bih.nic.attendenceapp.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bih.nic.attendenceapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button check_in,check_out;
    Intent intent;

    Bitmap bitmap1,bitmap2,bitmap3;
    byte[] imageData1=null,imageData2=null,imageData3=null;
    String latitude1, longitude1, gps_time1;
    String latitude2, longitude2, gps_time2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_in=findViewById(R.id.check_in);
        check_out=findViewById(R.id.check_out);
        check_in.setOnClickListener(this);
        check_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.check_in){
            intent=new Intent(MainActivity.this,CameraActivity.class);
            intent.putExtra("KEY_PIC", "1");
            mStartForResult.launch(intent);
        }else if(v.getId()==R.id.check_out){
            intent=new Intent(MainActivity.this,CameraActivity.class);
            intent.putExtra("KEY_PIC", "2");
            mStartForResult.launch(intent);
        }
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent2=new Intent(MainActivity.this, MakeAttendanceActivity.class);
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
                                intent2.putExtra("lat",latitude1);
                                intent2.putExtra("lang",longitude1);
                                intent2.putExtra("gps_time",gps_time1);
                                intent2.putExtra("image",imageData1);
                                intent2.putExtra("in_out","I");
                                intent2.putExtra("key_pic",data.getIntExtra("KEY_PIC", 0));
                                break;
                            case 2:
                                //bitmap2 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                                //ser_wire_img.setScaleType(ImageView.ScaleType.FIT_XY);
                                //ser_wire_img.setImageBitmap(Utiilties.GenerateThumbnail(bitmap2, 700, 500));
                                imageData2 = imgData;
                                latitude2 = data.getStringExtra("Lat");
                                longitude2 = data.getStringExtra("Lng");
                                gps_time2 = data.getStringExtra("GPSTime");
                                intent2.putExtra("lat",latitude2);
                                intent2.putExtra("lng",longitude2);
                                intent2.putExtra("gps_time",gps_time2);
                                intent2.putExtra("image",imageData2);
                                intent2.putExtra("in_out","O");
                                intent2.putExtra("key_pic",data.getIntExtra("KEY_PIC", 0));
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "KEY_PIC not found !", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        startActivity(intent2);
                    }
                }
            });
}