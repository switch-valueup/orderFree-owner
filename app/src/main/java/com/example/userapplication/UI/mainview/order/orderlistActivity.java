package com.example.userapplication.UI.mainview.order;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.addMenuAdapter;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponseData;
import com.example.userapplication.UI.mainview.order.data.OrderListData;
import com.example.userapplication.UI.mainview.order.data.OrderListResponse;
import com.example.userapplication.UI.mainview.order.data.OrderListResponseData;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderlistActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rlayoutManager;
    private List<OrderListResponseData> datas;
    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        getOrders();
    }

    public void getOrders(){
        String ownerEmail = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err");
        service.ownerOrderList(new OrderListData(ownerEmail)).enqueue(new Callback<OrderListResponse>() {
            @Override
            public void onResponse(Call<OrderListResponse> call, Response<OrderListResponse> response) {
                Log.e("response code", String.valueOf(response.code()));
                if(response == null){
                    Log.e("test1", "response null");
                }
                if(response.body() == null){
                    Log.e("test2", "body null");
                }
                if(response.body().getOrderedList() == null){
                    Log.e("test3", "orderlist null");
                }
                datas = response.body().getOrderedList();
                recyclerView = (RecyclerView)findViewById(R.id.orderlist);
                recyclerView.setHasFixedSize(true);
                rlayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(rlayoutManager);
                rAdapter = new orderlistAdapter(datas);
                recyclerView.setAdapter(rAdapter);

            }

            @Override
            public void onFailure(Call<OrderListResponse> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });
    }
}
