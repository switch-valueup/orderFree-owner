package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuListData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    public MenuListData(String ownerEmail){
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }
}
