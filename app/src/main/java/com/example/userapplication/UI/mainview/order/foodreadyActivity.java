package com.example.userapplication.UI.mainview.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.order.data.OrderCompleteData;
import com.example.userapplication.UI.mainview.order.data.OrderCompleteResponse;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;


import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class foodreadyActivity extends AppCompatActivity {
    private int orderNumber;
    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodready);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        setOrderNumber();
        completeOrder();
    }

    public void setOrderNumber(){
        Intent intent = getIntent();
        orderNumber = intent.getIntExtra("orderNumber",-1);
        TextView orderNum = (TextView)findViewById(R.id.foodready_number);
        orderNum.setText(String.valueOf(orderNumber) + "번 ");
    }

    public void completeOrder(){
        OrderCompleteData data = new OrderCompleteData(orderNumber, getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"));
        service.ownerOrderComplete(data).enqueue(new Callback<OrderCompleteResponse>() {
            @Override
            public void onResponse(Call<OrderCompleteResponse> call, Response<OrderCompleteResponse> response) {

            }

            @Override
            public void onFailure(Call<OrderCompleteResponse> call, Throwable t) {

            }
        });
    }
}
