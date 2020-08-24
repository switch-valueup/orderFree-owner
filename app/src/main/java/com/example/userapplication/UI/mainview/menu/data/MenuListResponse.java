package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result_menuList")
    private List<MenuListResponseData> result_menuList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<MenuListResponseData> getResult_menuList() {
        return result_menuList;
    }
}
