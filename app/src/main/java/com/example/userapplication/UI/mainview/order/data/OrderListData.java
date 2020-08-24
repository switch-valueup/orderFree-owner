package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class OrderListData {
    @SerializedName("ownerEmail")
    private String ownerEmail;

    public OrderListData(String ownerEmail){
        this.ownerEmail = ownerEmail;
    }
}
