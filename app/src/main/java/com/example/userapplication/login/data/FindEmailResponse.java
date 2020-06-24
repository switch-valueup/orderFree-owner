package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class FindEmailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userEmail")
    private String userEmail;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserEmail() { return userEmail; }
}
