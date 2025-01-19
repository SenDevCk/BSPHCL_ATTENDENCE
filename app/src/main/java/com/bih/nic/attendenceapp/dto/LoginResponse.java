package com.bih.nic.attendenceapp.dto;

import com.bih.nic.attendenceapp.entities.User;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Chandan Singh on 1/8/2025.
 */

public class LoginResponse {

        @SerializedName("token")
        private String token;
        @SerializedName("expiresIn")
        private long expiresIn;
        @SerializedName("data")
        private User data;
        @SerializedName("msg")
        private String msg;

        public String getToken() {
                return token;
        }

        public void setToken(String token) {
                this.token = token;
        }

        public long getExpiresIn() {
                return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
                this.expiresIn = expiresIn;
        }

        public User getData() {
                return data;
        }

        public void setData(User data) {
                this.data = data;
        }

        public String getMsg() {
                return msg;
        }

        public void setMsg(String msg) {
                this.msg = msg;
        }

        @Override
        public String toString() {
                return "LoginResponse{" +
                        "token='" + token + '\'' +
                        ", expiresIn=" + expiresIn +
                        ", data=" + data +
                        ", msg='" + msg + '\'' +
                        '}';
        }
}
