package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.bumptech.glide.Glide;
import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.data.AddMenuData;
import com.example.userapplication.UI.mainview.menu.data.AddMenuResponse;
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
    private String editedMenu;
    private int editedCategory;
    private String editedImage;
    private int editedPrice;
    private String editedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmenu);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        getMenuInfo();
        Log.e("test","test");
    }

    public void getMenuInfo(){
        Log.e("test2","test2");
        MenuDetailData menuDetailData = new MenuDetailData(getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"), getIntent().getStringExtra("menuName"));
        Log.e("ownerEmail", menuDetailData.getOwnerEmail());
        Log.e("menuName", getIntent().getStringExtra("menuName"));
        service.ownerMenuDetail(menuDetailData).enqueue(new Callback<MenuDetailResponse>() {
            @Override
            public void onResponse(Call<MenuDetailResponse> call, Response<MenuDetailResponse> response) {
                Log.e("test3","success");
                menuDetail = response.body().getResult();
                if(menuDetail == null){
                    Log.e("response data","null");
                }
                bindMenuInfo();
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
        category.setText(new CategoryConverter().toStringConvert(menuDetail.getCategory()));
        Glide.with(this).load(menuDetail.getImgUrl()).into(image);
        price.setText(String.valueOf(menuDetail.getPrice()));
        description.setText(menuDetail.getInfo());
    }
    

    // TODO reselect image
    public String imageEdit(){
        return null;
    }

    public void getEditedInfo(){
        TextView menuName = findViewById(R.id.text_store);
        Button category = findViewById(R.id.text_category);
        ImageView image = findViewById(R.id.image_addmenu);
        EditText price = findViewById(R.id.text_price);
        EditText info = findViewById(R.id.text_info);

        // TODO binding image
        editedMenu = menuName.getText().toString();
        editedCategory = new CategoryConverter().toIntConvert(category.getText().toString());
        editedImage = imageEdit();
        editedPrice = Integer.getInteger(price.getText().toString());
        editedInfo = info.getText().toString();
    }

    public void editRegister(View view){
        getEditedInfo();
        AddMenuData data = new AddMenuData(
                getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"),
                editedMenu, editedCategory, editedImage, editedPrice, editedInfo, getIntent().getStringExtra("menuName"));
        service.ownerAddMenu(data).enqueue(new Callback<AddMenuResponse>() {
            @Override
            public void onResponse(Call<AddMenuResponse> call, Response<AddMenuResponse> response) {
                Log.e("success flag", String.valueOf(response.body().getCode()));
            }

            @Override
            public void onFailure(Call<AddMenuResponse> call, Throwable t) {
                Log.e("fail flag", t.getMessage());
            }
        });
    }
}
