package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuListResponseData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCategory() {
        return category;
    }
}
