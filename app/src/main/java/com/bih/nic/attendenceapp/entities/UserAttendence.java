package com.bih.nic.attendenceapp.entities;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Created by Chandan Singh on 1/9/2025.
 */
@Data
public class UserAttendence {
    //staffId+ddmmYYYY+in/out
    @SerializedName("attendenceId")
    private String  attendenceId;
    @SerializedName("inOut")
    private String inOut;
    @SerializedName("urlImage")
    private String urlImage;
    @SerializedName("inOutTime")
    private LocalDateTime inOutTime;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
}
