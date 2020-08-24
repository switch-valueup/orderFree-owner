package com.example.userapplication.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("ownerPwd")
    private String ownerPwd;

    @SerializedName("ownerName")
    private String ownerName;

    @SerializedName("ownerPhoneNumber")
    private String ownerPhoneNumber;


    public JoinData(String ownerEmail, String ownerPwd,
                    String ownerName, String ownerPhoneNumber) {
        this.ownerEmail = ownerEmail;
        this.ownerPwd = ownerPwd;
        this.ownerName = ownerName;
        this.ownerPhoneNumber=ownerPhoneNumber;
    }
}