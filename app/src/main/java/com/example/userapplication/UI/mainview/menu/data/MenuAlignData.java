package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuAlignData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("category")
    private int category;

    public MenuAlignData(String ownerEmail, int category){
        this.ownerEmail = ownerEmail;
        this.category = category;
    }
}
