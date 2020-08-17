package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class AddMenuData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    @SerializedName("imgURL")
    private String imgURL;

    @SerializedName("price")
    private int price;

    @SerializedName("info")
    private String info;

    @SerializedName("decisionNum")
    private int decisionNum;

    @SerializedName("menuOriginalName")
    private String menuOriginalName;

    public AddMenuData(String ownerEmail, String menuName, int category, String imgUrl, int price, String info, String menuOriginalName){
        this.ownerEmail = ownerEmail;
        this.menuName = menuName;
        this.category = category;
        this.imgURL = imgUrl;
        this.price = price;
        this.info = info;
        this.decisionNum = 2;
        this.menuOriginalName = menuOriginalName;
    }

    public AddMenuData(String ownerEmail, String menuName, int category, String imgUrl, int price, String info){
        this.ownerEmail = ownerEmail;
        this.menuName = menuName;
        this.category = category;
        this.imgURL = imgUrl;
        this.price = price;
        this.info = info;
        this.decisionNum = 1;
        this.menuOriginalName = "";
    }
}
