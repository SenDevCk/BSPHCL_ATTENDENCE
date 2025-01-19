package com.bih.nic.attendenceapp.dto;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Chandan Singh on 1/9/2025.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAttendenceDto {
    //staffId+ddmmYYYY+in/out
    @SerializedName("attendenceId")
    private String  attendenceId;
    @SerializedName("inOut")
    private String inOut;
    @SerializedName("urlImage")
    private String urlImage;
    @SerializedName("inOutTime")
    private List<Double> inOutTime;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
}
