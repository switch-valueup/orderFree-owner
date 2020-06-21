package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userID")
    private String userID;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userMobilePhone")
    private String userMobilePhone;


    public JoinData(String userID, String userPwd, String userEmail,
                    String userName, String userMobilePhone) {
        this.userID = userID;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userMobilePhone=userMobilePhone;
    }
}