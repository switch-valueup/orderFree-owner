package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

public class OrderListResponseData {
    @SerializedName("OrderNum")
    private int OrderNum;

    public int getOrderNum() {
        return OrderNum;
    }
}
