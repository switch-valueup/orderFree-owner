package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.data.MenuDetailData;
import com.example.userapplication.UI.mainview.menu.data.MenuDetailResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuDetailResponseData;
import com.example.userapplication.Util.CategoryConverter;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editMenuActivity extends AppCompatActivity {
    private ServiceApi service;
    private MenuDetailResponseData menuDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmenu);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        getMenuInfo();
        bindMenuInfo();
    }

    public void getMenuInfo(){
        MenuDetailData menuDetailData = new MenuDetailData(getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"), getIntent().getStringExtra("menuName"));
        service.ownerMenuDetail(menuDetailData).enqueue(new Callback<MenuDetailResponse>() {
            @Override
            public void onResponse(Call<MenuDetailResponse> call, Response<MenuDetailResponse> response) {
                menuDetail = response.body().getResult();
            }

            @Override
            public void onFailure(Call<MenuDetailResponse> call, Throwable t) {
                Log.e("menu detail err",t.getMessage());
            }
        });
    }

    public void bindMenuInfo(){
        TextView menuName = findViewById(R.id.text_store);
        Button category = findViewById(R.id.text_category);
        ImageView image = findViewById(R.id.image_addmenu);
        EditText price = findViewById(R.id.text_price);
        EditText description = findViewById(R.id.text_info);

        menuName.setText(menuDetail.getMenuName());
        category.setText(new CategoryConverter(menuDetail.getCategory()).convert());
        Glide.with(this).load(menuDetail.getImgUrl()).into(image);
        price.setText(menuDetail.getPrice());
        description.setText(menuDetail.getInfo());
    }
}
