package com.example.userapplication.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class CheckPasswordData {
    @SerializedName("ownerPwd")
    String ownerPwd;

    public String getOwnerPwd() {
        String ownerPwd = this.ownerPwd;
        return ownerPwd;
    }

    @SerializedName("ownerEmail")
    String ownerEmail;


    public CheckPasswordData(String ownerEmail, String ownerPwd){
        this.ownerEmail=ownerEmail;
        this.ownerPwd=ownerPwd;
    }
}

