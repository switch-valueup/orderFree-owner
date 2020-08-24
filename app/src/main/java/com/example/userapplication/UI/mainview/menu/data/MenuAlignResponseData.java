package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuAlignResponseData {
    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    public String getMenuName() {
        return menuName;
    }

    public int getCategory() {
        return category;
    }
}
