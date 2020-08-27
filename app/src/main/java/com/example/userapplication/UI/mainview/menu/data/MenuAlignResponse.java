package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuAlignResponse {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("category")
    private int category;

    @SerializedName("resultAlign")
    private List<MenuListResponseData> resultAlign;

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public int getMenuName() {
        return category;
    }

    public List<MenuListResponseData> getResultAlign() {
        return resultAlign;
    }
}
