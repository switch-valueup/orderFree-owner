package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.data.MenuAlignData;
import com.example.userapplication.UI.mainview.menu.data.MenuAlignResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListData;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponseData;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class addMenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rlayoutManager;
    private List<MenuListResponseData> datas;
    private ServiceApi service;
    private boolean drawerOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        drawerOn = false;
        service = RetrofitClient.getClient().create(ServiceApi.class);
        menuList();
    }

    // when clicked add menu button
    public void addMenu(View view){
        Intent intent = new Intent(this, addMenuDetailActivity.class);
        startActivity(intent);
    }

    public void menuAlign(View view){
        drawerOn = !drawerOn;
        if(drawerOn){

        }
        else{

        }
    }

    public void getAlignedData(int category){
        String ownerEmail = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err");
        MenuAlignData menuAlignData = new MenuAlignData(ownerEmail, category);
        service.ownerMenuAlign(menuAlignData).enqueue(new Callback<MenuAlignResponse>() {
            @Override
            public void onResponse(Call<MenuAlignResponse> call, Response<MenuAlignResponse> response) {

            }

            @Override
            public void onFailure(Call<MenuAlignResponse> call, Throwable t) {

            }
        });
    }

    public void addMenuBack(View view){
        onBackPressed();
    }

    public void menuList(){
        String ownerEmail = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err");
        MenuListData data = new MenuListData(ownerEmail);
        service.ownerMenuList(data).enqueue(new Callback<MenuListResponse>() {
            @Override
            public void onResponse(Call<MenuListResponse> call, Response<MenuListResponse> response) {
                if(response.isSuccessful()){
                    Log.e("flag","success");
                    datas = response.body().getResult_menuList();
                    recyclerView = (RecyclerView)findViewById(R.id.mene_recycler);
                    recyclerView.setHasFixedSize(true);
                    rlayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(rlayoutManager);
                    rAdapter = new addMenuAdapter(datas);
                    recyclerView.setAdapter(rAdapter);
                }
            }

            @Override
            public void onFailure(Call<MenuListResponse> call, Throwable t) {
                Log.e("flag","failure");
                Log.e("menuListErr",t.getMessage());

            }
        });
    }
}
