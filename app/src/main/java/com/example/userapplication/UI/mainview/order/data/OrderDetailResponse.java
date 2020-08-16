package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("orderedList")
    private List<OrderDetailResponseData> orderedList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<OrderDetailResponseData> getOrderedList() {
        return orderedList;
    }
}
