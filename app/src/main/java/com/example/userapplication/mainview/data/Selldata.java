package com.example.userapplication.mainview.data;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Selldata {

    @SerializedName("count")
    private int count;

    @SerializedName("price")
    private int price;

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

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
