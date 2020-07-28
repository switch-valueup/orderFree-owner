package com.example.userapplication.UI.mainview.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.userapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class orderDetailActivity extends AppCompatActivity {
    private int[] orderList;
    private int currentOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();
        orderList = intent.getIntArrayExtra("orderList");
        currentOrder = intent.getIntExtra("currentOrderPos",0);
    }

    // when clicked food ready button
    public void foodReady(View view){
        Intent intent = new Intent(this, foodreadyActivity.class);
        intent.putExtra("orderNumber", currentOrder);
        startActivity(intent);
    }
}
