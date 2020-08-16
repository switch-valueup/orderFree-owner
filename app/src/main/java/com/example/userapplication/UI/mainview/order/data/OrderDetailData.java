package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class OrderDetailData {
    @SerializedName("ownerEmail")
    private String ownerEmail;
    @SerializedName("orderNum")
    private int orderNum;

    public OrderDetailData(String ownerEmail, int orderNum){
        this.ownerEmail = ownerEmail;
        this.orderNum = orderNum;
    }
}
