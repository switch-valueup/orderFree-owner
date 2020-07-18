package com.example.userapplication.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class FindEmailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("ownerEmail")
    private String ownerEmail;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getOwnerEmail() { return ownerEmail; }
}
