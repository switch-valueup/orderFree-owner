package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("ownerEmail")
    String ownerEmail;

    @SerializedName("ownerPwd")
    String ownerPwd;

    @SerializedName("ownerDeviceToken")
    String ownerDeviceToken;

    public LoginData(String ownerEmail, String ownerPwd, String deviceToken){
        this.ownerEmail=ownerEmail;
        this.ownerPwd=ownerPwd;
        this.ownerDeviceToken = deviceToken;
    }
}
