package com.example.userapplication.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;

import java.util.List;

public class SelldataResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("resultArray")
    private List<Selldata> result_Array;

    public SelldataResponse(List<Selldata> result_array) throws JSONException {
        result_Array = result_array;
    }

    public int getCode() { return code; }
    public List getObject() { return result_Array; }
}