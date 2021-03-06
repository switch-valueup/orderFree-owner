package com.example.userapplication.network;

import com.example.userapplication.UI.login.data.ChangePasswordData;
import com.example.userapplication.UI.login.data.ChangePasswordResponse;
import com.example.userapplication.UI.login.data.DeleteAccountData;
import com.example.userapplication.UI.login.data.DeleteAccountResponse;
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
import com.example.userapplication.UI.mainview.data.CheckPasswordData;
import com.example.userapplication.UI.mainview.data.CheckPasswordResponse;
import com.example.userapplication.UI.mainview.data.EnrollmentAddressRequest;
import com.example.userapplication.UI.mainview.data.EnrollmentAddressResponse;
import com.example.userapplication.UI.mainview.data.SelldataRequest;
import com.example.userapplication.UI.mainview.data.SelldataResponse;
import com.example.userapplication.UI.mainview.menu.data.AddMenuData;
import com.example.userapplication.UI.mainview.menu.data.AddMenuResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuAlignData;
import com.example.userapplication.UI.mainview.menu.data.MenuAlignResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuDeleteData;
import com.example.userapplication.UI.mainview.menu.data.MenuDeleteResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuDetailData;
import com.example.userapplication.UI.mainview.menu.data.MenuDetailResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListData;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponse;
import com.example.userapplication.UI.mainview.order.data.OrderCompleteData;
import com.example.userapplication.UI.mainview.order.data.OrderCompleteResponse;
import com.example.userapplication.UI.mainview.order.data.OrderDetailData;
import com.example.userapplication.UI.mainview.order.data.OrderDetailResponse;
import com.example.userapplication.UI.mainview.order.data.OrderListData;
import com.example.userapplication.UI.mainview.order.data.OrderListResponse;

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

    @POST("/owner/login/changepwd")
    Call<ChangePasswordResponse> ownerChangePassword(@Body ChangePasswordData data);

    @POST("/main/info/registore")
    Call<EnrollmentAddressResponse> erollmentAddress(@Body EnrollmentAddressRequest data);

    // addMenu
    @POST("/main/menu/menuList")
    Call<MenuListResponse> ownerMenuList(@Body MenuListData data);

    @POST("/main/menu/add")
    Call<AddMenuResponse> ownerAddMenu(@Body AddMenuData data);

    @POST("/main/menu/menuSpecification")
    Call<MenuDetailResponse> ownerMenuDetail(@Body MenuDetailData data);

    @POST("/main/menu/delete")
    Call<MenuDeleteResponse> ownerMenuDelete(@Body MenuDeleteData data);

    @POST("/main/menu/align")
    Call<MenuAlignResponse> ownerMenuAlign(@Body MenuAlignData data);

    // orderList
    @POST("/main/menu/orderedList")
    Call<OrderListResponse> ownerOrderList(@Body OrderListData data);

    @POST("/main/menu/orderedListSpecification")
    Call<OrderDetailResponse> ownerOrderDetail(@Body OrderDetailData data);

    @POST("/main/menu/orderList/previousOrder")
    Call<OrderDetailResponse> ownerPreOrder(@Body OrderDetailData data);

    @POST("/main/menu/orderList/nextOrder")
    Call<OrderDetailResponse> ownerNextOrder(@Body OrderDetailData data);

    @POST("/main/orderlist/complete")
    Call<OrderCompleteResponse> ownerOrderComplete(@Body OrderCompleteData data);



}