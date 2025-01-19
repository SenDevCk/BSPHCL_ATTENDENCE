package com.bih.nic.attendenceapp.entities;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


/**
 * Created by Chandan Singh on 08-03-2018.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String entryDate;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubDivId() {
        return subDivId;
    }

    public void setSubDivId(String subDivId) {
        this.subDivId = subDivId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }
}
