package com.example.userapplication.UI.mainview.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.order.data.OrderDetailData;
import com.example.userapplication.UI.mainview.order.data.OrderDetailResponse;
import com.example.userapplication.UI.mainview.order.data.OrderDetailResponseData;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderDetailActivity extends AppCompatActivity {
    private int currentOrder;
    private ServiceApi service;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rlayoutManager;
    private List<OrderDetailResponseData> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Intent intent = getIntent();
        currentOrder = intent.getIntExtra("orderNum", 0);
        getShoppingList();
        bindingView(currentOrder);
    }

    public void getShoppingList(){
        OrderDetailData data = new OrderDetailData(getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"), currentOrder);
        service.ownerOrderDetail(data).enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                datas = response.body().getResultArray();
                recyclerView = (RecyclerView)findViewById(R.id.shopping_list);
                recyclerView.setHasFixedSize(true);
                rlayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(rlayoutManager);
                rAdapter = new ShoppingListAdapter(datas);
                recyclerView.setAdapter(rAdapter);
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {

            }
        });
    }

    public void bindingView(int orderNum){
        TextView order = findViewById(R.id.text_ordernumber);
        order.setText(String.valueOf(orderNum));
    }

    // when clicked food ready button
    public void foodReady(View view){
        Intent intent = new Intent(this, foodreadyActivity.class);
        intent.putExtra("orderNumber", currentOrder);
        startActivity(intent);
    }

    public void getPreShoppingList(){
        OrderDetailData data = new OrderDetailData(getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"), currentOrder);
        service.ownerPreOrder(data).enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                if(response.body() == null){
                    Log.e("pre body", "body null");
                }
                if(response.body().getResultArray() == null){
                    Log.e("pre result", "result null");
                }
                Log.e("pre order test", String.valueOf(response.body().getResultArray().size()));
                datas = response.body().getResultArray();
                recyclerView = (RecyclerView)findViewById(R.id.shopping_list);
                recyclerView.setHasFixedSize(true);
                rlayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(rlayoutManager);
                rAdapter = new ShoppingListAdapter(datas);
                recyclerView.setAdapter(rAdapter);
                bindingView(response.body().getResultArray().get(0).getOrderNum());
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                Log.e("fail: preOrder", t.getMessage());
            }
        });
    }

    public void getNextShoppingList(){
        OrderDetailData data = new OrderDetailData(getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"), currentOrder);
        service.ownerNextOrder(data).enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                if(response.body() == null){
                    Log.e("next body", "body null");
                }
                if(response.body().getResultArray() == null){
                    Log.e("next result", "result null");
                }
                Log.e("next order test", String.valueOf(response.body().getResultArray().size()));
                datas = response.body().getResultArray();
                recyclerView = (RecyclerView)findViewById(R.id.shopping_list);
                recyclerView.setHasFixedSize(true);
                rlayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(rlayoutManager);
                rAdapter = new ShoppingListAdapter(datas);
                recyclerView.setAdapter(rAdapter);
                bindingView(response.body().getResultArray().get(0).getOrderNum());
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                Log.e("fail: nextOrder", t.getMessage());
            }
        });
    }

    public void preOrder(View view){
        getPreShoppingList();
    }

    public void nextOrder(View view){
        getNextShoppingList();
    }
}
