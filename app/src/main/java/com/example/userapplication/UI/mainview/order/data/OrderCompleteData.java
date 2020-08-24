package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class OrderCompleteData {
    @SerializedName("orderNum")
    private int orderNum;

    @SerializedName("ownerEmail")
    private String ownerEmail;

    public OrderCompleteData(int orderNum, String ownerEmail){
        this.orderNum = orderNum;
        this.ownerEmail = ownerEmail;
    }
}
