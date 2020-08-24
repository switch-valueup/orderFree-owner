package com.example.userapplication.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class Selldata {

    @SerializedName("count")
    private int count;

    @SerializedName("price")
    private int price;

    @SerializedName("orderedDate")
    private String orderedDate;

    @SerializedName("orderNum")
    private int orderNum;


    public Selldata(int count,int price)
    {
        this.count=count;
        this.price=price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() { return price; }

    public int getCount() {
        return count;
    }

    public String getOrderedDate() {
        return orderedDate.substring(0,10);
    }

    public int getOrderNum() {
        return orderNum;
    }
}
