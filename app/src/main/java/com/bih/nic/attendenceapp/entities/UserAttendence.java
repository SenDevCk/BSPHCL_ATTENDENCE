package com.bih.nic.attendenceapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Chandan Singh on 1/9/2025.
 */

@Entity(tableName = "UserAttendence")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAttendence{
    //staffId+ddmmYYYY+in/out
    public String MyDefaultValuedCol = "defaultString";
    @PrimaryKey
    @NonNull
    @SerializedName("attendenceId")
    private String  attendenceId;
    @SerializedName("inOut")
    private String inOut;

    @SerializedName("urlImage")
    private String urlImage;
    @SerializedName("inOutTime")
    private String inOutTime;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    @NonNull
    public String getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(@NonNull String attendenceId) {
        this.attendenceId = attendenceId;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getInOutTime() {
        return inOutTime;
    }

    public void setInOutTime(String inOutTime) {
        this.inOutTime = inOutTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "UserAttendence{" +
                "MyDefaultValuedCol='" + MyDefaultValuedCol + '\'' +
                ", attendenceId='" + attendenceId + '\'' +
                ", inOut='" + inOut + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", inOutTime=" + inOutTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
