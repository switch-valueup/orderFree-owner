package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class OrderCompleteResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
