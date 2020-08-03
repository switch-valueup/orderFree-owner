package com.example.userapplication.login.network;

import com.example.userapplication.login.data.ChangePasswordData;
import com.example.userapplication.login.data.ChangePasswordResponse;
import com.example.userapplication.login.data.DeleteAccountData;
import com.example.userapplication.login.data.DeleteAccountResponse;
import com.example.userapplication.login.data.FindEmailData;
import com.example.userapplication.login.data.FindEmailResponse;
import com.example.userapplication.login.data.FindPasswordData;
import com.example.userapplication.login.data.FindPasswordResponse;
import com.example.userapplication.login.data.JoinAvailable;
import com.example.userapplication.login.data.JoinAvailableResponse;
import com.example.userapplication.login.data.JoinData;
import com.example.userapplication.login.data.JoinResponse;
import com.example.userapplication.login.data.LoginData;
import com.example.userapplication.login.data.LoginResponse;
import com.example.userapplication.mainview.data.CheckPasswordData;
import com.example.userapplication.mainview.data.CheckPasswordResponse;
import com.example.userapplication.mainview.data.SelldataRequest;
import com.example.userapplication.mainview.data.SelldataResponse;

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

    @POST("/main/info/withdraw")
    Call<DeleteAccountResponse> ownerDeleteAccount(@Body DeleteAccountData data);

    @POST("/main/sellstatus")
    Call<SelldataResponse> ownerSellData(@Body SelldataRequest data);

    @POST("/main/info/checkpwd")
    Call<CheckPasswordResponse> ownerCheckPassowrd(@Body CheckPasswordData data);

    @POST("owner/login/changepwd")
    Call<ChangePasswordResponse> ownerChangePassword(@Body ChangePasswordData data);

}