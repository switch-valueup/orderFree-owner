package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordData {
    @SerializedName("ownerPwd")
    String ownerPwd;

    @SerializedName("ownerEmail")
    String ownerEmail;


    public ChangePasswordData(String ownerEmail, String ownerPwd){
        this.ownerEmail=ownerEmail;
        this.ownerPwd=ownerPwd;
    }
}

