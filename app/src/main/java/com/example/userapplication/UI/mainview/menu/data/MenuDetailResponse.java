package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuDetailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private MenuDetailResponseData result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public MenuDetailResponseData getResult() {
        return result;
    }
}
