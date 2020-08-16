package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuDetailData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("menuName")
    private String menuName;

    public MenuDetailData(String ownerEmail, String menuName){
        this.ownerEmail = ownerEmail;
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }
}
