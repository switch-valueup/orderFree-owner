package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordData {
    @SerializedName("userEmail")
    private String userEamil;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userMobilePhone")
    private String userMobilePhone;

    public FindPasswordData(String userEmail, String userName, String userMobilePhone){
        this.userEamil=userEmail;
        this.userName=userName;
        this.userMobilePhone=userMobilePhone;
    }
}
