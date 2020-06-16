package com.example.userapplication.login.network;

import com.example.userapplication.login.data.JoinData;
import com.example.userapplication.login.data.JoinResponse;
import com.example.userapplication.login.data.LoginData;
import com.example.userapplication.login.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}