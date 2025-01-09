package com.bih.nic.attendenceapp.dto;

import com.bih.nic.attendenceapp.entities.User;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Chandan Singh on 1/8/2025.
 */
@Data
public class LoginResponse {
        @SerializedName("token")
        private String token;
        @SerializedName("expiresIn")
        private long expiresIn;
        @SerializedName("data")
        private User data;
        @SerializedName("msg")
        private String msg;

}
