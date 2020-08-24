package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class ShoppingListData {
    @SerializedName("menu")
    private String menu;

    @SerializedName("count")
    private int count;

    @SerializedName("price")
    private int price;

    public String getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
