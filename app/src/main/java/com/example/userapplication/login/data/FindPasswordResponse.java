package com.example.userapplication.login.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}