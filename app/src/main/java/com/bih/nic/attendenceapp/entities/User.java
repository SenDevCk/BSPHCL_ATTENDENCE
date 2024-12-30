package com.bih.nic.attendenceapp.entities;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("MessageString")
    private String MessageString;
    @SerializedName("UserID")
    private String UserID;
    @SerializedName("UserName")
    private String UserName;
    @SerializedName("SubDiv")
    private String SubDiv;
    @SerializedName("SubdivName")
    private String SubdivName;
    @SerializedName("WalletId")
    private String WalletId;
    @SerializedName("WalletAmount")
    private String WalletAmount;
    @SerializedName("Authenticated")
    private boolean Authenticated;
    @SerializedName("ImeiNo")
    private String ImeiNo;
    @SerializedName("ContactNo")
    private String ContactNo;
    @SerializedName("IFSCCode")
    private String IFSCCode;
    @SerializedName("password")
    private String password;
    @SerializedName("serialNo")
    private String serialNo;
    @SerializedName("ACCT_NO")
    private String actNo;
}
