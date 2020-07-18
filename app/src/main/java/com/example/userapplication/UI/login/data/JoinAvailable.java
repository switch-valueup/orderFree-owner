package com.example.userapplication.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class JoinAvailable {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    public JoinAvailable(String ownerEmail){
        this.ownerEmail = ownerEmail;
    }
}
