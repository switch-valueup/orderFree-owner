package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("ownerEamil")
    private String ownerEmail;

    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getToken() {
        return token;
    }

}
