package com.example.userapplication.mainview.data;

import com.google.gson.annotations.SerializedName;

public class SelldataRequest {

    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("endDate")
    private String endDate;

    public SelldataRequest() { //혹시 몰라서 매개변수가 없는 것도 만들었다.
    }

    public SelldataRequest(String ownerEmail, String startDate, String endDate) {
        this.ownerEmail=ownerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getter & Setter Method..
    //멤버 변수가 private이면 다른 곳에서 접근하기 위해 Getter & Setter Method 필요하다.
    // 자동완성 Alt+Insert 에 Getter & Setter 있다.

}