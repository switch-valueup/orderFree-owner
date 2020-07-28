package com.example.userapplication.UI.mainview.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userapplication.R;


import androidx.appcompat.app.AppCompatActivity;

public class foodreadyActivity extends AppCompatActivity {
    private int orderNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodready);
        setOrderNumber();
    }

    public void setOrderNumber(){
        Intent intent = getIntent();
        orderNumber = intent.getIntExtra("orderNumber",-1);
        TextView orderNum = (TextView)findViewById(R.id.foodready_number);
        orderNum.setText(orderNumber);
    }
}
