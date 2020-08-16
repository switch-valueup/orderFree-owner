package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("orderedList")
    private List<OrderListResponseData> orderedList;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public List<OrderListResponseData> getOrderedList() {
        return orderedList;
    }
}
