package com.example.userapplication.mainview.data;

import com.google.gson.annotations.SerializedName;

public class EnrollmentAddressRequest {

    @SerializedName("ownerEmail")
    private String ownerEmail;

    @SerializedName("ownerStoreName")
    private String ownerStoreName;

    @SerializedName("ownerStoreAddress")
    private String ownerStoreAddress ;



    public EnrollmentAddressRequest(String ownerEmail, String ownerStoreName, String ownerStoreAddress) {
        this.ownerEmail=ownerEmail;
        this.ownerStoreName = ownerStoreName;
        this.ownerStoreAddress = ownerStoreAddress;
    }
}
