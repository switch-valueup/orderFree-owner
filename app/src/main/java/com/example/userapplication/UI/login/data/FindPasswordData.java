package com.example.userapplication.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("ownerName")
    private String ownerName;

    @SerializedName("ownerPhoneNumber")
    private String ownerPhoneNumber;

    public FindPasswordData(String ownerEmail, String ownerName, String ownerPhoneNumber){
        this.ownerEmail=ownerEmail;
        this.ownerName=ownerName;
        this.ownerPhoneNumber=ownerPhoneNumber;
    }
}
