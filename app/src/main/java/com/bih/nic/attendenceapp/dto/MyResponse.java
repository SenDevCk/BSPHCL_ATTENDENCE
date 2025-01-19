package com.bih.nic.attendenceapp.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Chandan Singh on 1/9/2025.
 */

public class MyResponse<T> {


    @SerializedName("data")
    private T data;
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("status")
    private String status;
    @SerializedName("remarks")
    private String remarks;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "data=" + data +
                ", responseCode=" + responseCode +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
