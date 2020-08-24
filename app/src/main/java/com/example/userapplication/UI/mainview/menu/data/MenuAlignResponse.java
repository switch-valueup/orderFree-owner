package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuAlignResponse {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("category")
    private int category;

    @SerializedName("resultAlign")
    private MenuAlignResponseData resultAlign;

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public int getMenuName() {
        return category;
    }

    public MenuAlignResponseData getResultAlign() {
        return resultAlign;
    }
}
