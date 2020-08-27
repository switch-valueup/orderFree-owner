package com.example.userapplication.UI.mainview.menu.data;

import com.example.userapplication.UI.mainview.menu.AddMenuItem;
import com.google.gson.annotations.SerializedName;

public class MenuListResponseData {
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
