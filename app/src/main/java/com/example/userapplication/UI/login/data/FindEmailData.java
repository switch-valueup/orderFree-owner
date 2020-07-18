package com.example.userapplication.UI.login.data;

        import com.google.gson.annotations.SerializedName;

public class FindEmailData {
    @SerializedName("ownerName")
    private String ownerName;

    @SerializedName("ownerPhoneNumber")
    private String ownerPhoneNumber;

    public FindEmailData(String ownerName, String ownerPhoneNumber){
        this.ownerName=ownerName;
        this.ownerPhoneNumber=ownerPhoneNumber;
    }
}
