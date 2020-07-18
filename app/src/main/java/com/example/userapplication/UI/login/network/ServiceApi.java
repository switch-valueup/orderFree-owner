package com.example.userapplication.UI.login.network;

import com.example.userapplication.UI.login.data.FindEmailData;
import com.example.userapplication.UI.login.data.FindEmailResponse;
import com.example.userapplication.UI.login.data.FindPasswordData;
import com.example.userapplication.UI.login.data.FindPasswordResponse;
import com.example.userapplication.UI.login.data.JoinAvailable;
import com.example.userapplication.UI.login.data.JoinAvailableResponse;
import com.example.userapplication.UI.login.data.JoinData;
import com.example.userapplication.UI.login.data.JoinResponse;
import com.example.userapplication.UI.login.data.LoginData;
import com.example.userapplication.UI.login.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/owner/login")
    Call<LoginResponse> ownerLogin(@Body LoginData data);

    @POST("/owner/join")
    Call<JoinResponse> ownerJoin(@Body JoinData data);

    @POST("/owner/join/emailcheck")
    Call<JoinAvailableResponse> ownerJoinAvailable(@Body JoinAvailable data);

    @POST("/owner/login/emailfind")
    Call<FindEmailResponse> ownerFindEmail(@Body FindEmailData data);

    @POST("/owner/login/pwdfind")
    Call<FindPasswordResponse> ownerFindPassword(@Body FindPasswordData data);
}