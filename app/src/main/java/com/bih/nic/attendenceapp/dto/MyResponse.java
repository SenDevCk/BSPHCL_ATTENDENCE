package com.bih.nic.attendenceapp.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Chandan Singh on 1/9/2025.
 */
@Data
public class MyResponse<T> {
    @SerializedName("data")
    private T data;
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("status")
    private String status;
    @SerializedName("remarks")
    private String remarks;

}
