package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuDeleteData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("menuName")
    private String menuName;

    public MenuDeleteData(String ownerEmail, String menuName){
        this.ownerEmail = ownerEmail;
        this.menuName = menuName;
    }
}
