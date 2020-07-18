package com.example.userapplication.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("ownerEmail")
    String ownerEmail;

    @SerializedName("ownerPwd")
    String ownerPwd;

    public LoginData(String ownerEmail, String ownerPwd){
        this.ownerEmail=ownerEmail;
        this.ownerPwd=ownerPwd;
    }
}
