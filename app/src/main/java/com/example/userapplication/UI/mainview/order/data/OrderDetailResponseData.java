package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class OrderDetailResponseData {
    @SerializedName("orderNum")
    private int orderNum;

    @SerializedName("menuName")
    private String menuName;

    @SerializedName("count")
    private int count;


    public int getOrderNum() {
        return orderNum;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}
