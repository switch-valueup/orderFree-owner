package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("ownerName")
    private String ownerName;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getOwnerName() { return ownerName; }
}