package com.example.userapplication.login.data;

        import com.google.gson.annotations.SerializedName;

public class FindEmailData {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userMobilePhone")
    private String userMobilePhone;

    public FindEmailData(String userName, String userMobilePhone){
        this.userName=userName;
        this.userMobilePhone=userMobilePhone;
    }
}
