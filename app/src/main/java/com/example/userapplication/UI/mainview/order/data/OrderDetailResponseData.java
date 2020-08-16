package com.example.userapplication.UI.mainview.order.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailResponseData {
    @SerializedName("OrderNum")
    private int OrderNum;

    @SerializedName("ShoppingList")
    private List<ShoppingListData> ShoppingList;

    public int getOrderNum() {
        return OrderNum;
    }

    public List<ShoppingListData> getShoppingList() {
        return ShoppingList;
    }
}
