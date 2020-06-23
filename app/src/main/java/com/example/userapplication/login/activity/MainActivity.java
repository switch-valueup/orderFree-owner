package com.example.userapplication.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;

public class MainActivity extends AppCompatActivity {
    private Button mMenuButton;
    private Button mOrderListButton;
    private Button mSellStatusButton;
    private Button mReviseButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mUserEmailView = (TextView)findViewById(R.id.userName);
        mMenuButton = (Button)findViewById(R.id.main_menu);
        mOrderListButton = (Button)findViewById(R.id.main_orderlist);
        mSellStatusButton = (Button)findViewById(R.id.main_sellstatus);
        mReviseButton = (Button)findViewById(R.id.main_revise_person_info);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        if(intent.hasExtra("userName")) {
            mUserEmailView.setText(userName);
        }
        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),com.example.userapplication.mainview.activity.addMenuActivity.class);
                startActivity(intent);
            }
        });

        mOrderListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        mSellStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mReviseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
