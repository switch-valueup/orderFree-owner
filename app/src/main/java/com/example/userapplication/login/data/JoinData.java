package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userMobilePhone")
    private String userMobilePhone;


    public JoinData(String userEmail, String userPwd,
                    String userName, String userMobilePhone) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userMobilePhone=userMobilePhone;
    }
}