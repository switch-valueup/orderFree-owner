package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuDetailResponseData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    @SerializedName("imgUrl")
    private String imgUrl;

    @SerializedName("price")
    private int price;

    @SerializedName("info")
    private String info;

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getInfo() {
        return info;
    }
}
