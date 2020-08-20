package com.example.userapplication.UI.mainview.menu.data;

import com.google.gson.annotations.SerializedName;

public class MenuDetailResponseData {
    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    @SerializedName("imgURL")
    private String imgUrl;

    @SerializedName("price")
    private int price;

    @SerializedName("info")
    private String info;

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
