package com.bih.nic.attendenceapp.entities;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Chandan Singh on 08-03-2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @SerializedName("staffId")
    private String staffId;
    @SerializedName("staffName")
    private String staffName;
    @SerializedName("mobileNo")
    private String mobileNo;
    @SerializedName("emailId")
    private String emailId;
    @SerializedName("authenticate")
    private String authenticate;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("entryDate")
    private LocalDateTime entryDate;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("role")
    private String role;
    @SerializedName("locationId")
    private String locationId;
    @SerializedName("password")
    private String password;
    @SerializedName("subDivId")
    private String subDivId;
    @SerializedName("section")
    private String section;
    @SerializedName("division")
    private String division;
    @SerializedName("circle")
    private String circle;

}
