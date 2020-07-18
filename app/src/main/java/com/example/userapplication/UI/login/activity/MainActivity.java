package com.example.userapplication.UI.login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.activity.personInfoActivity;

public class MainActivity extends AppCompatActivity {
    private Button mMenuButton;
    private Button mOrderListButton;
    private Button mSellStatusButton;
    private Button mPersonInfoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mOwnerNameView = (TextView)findViewById(R.id.ownerName);
        mMenuButton = (Button)findViewById(R.id.main_menu);
        mOrderListButton = (Button)findViewById(R.id.main_orderlist);
        mSellStatusButton = (Button)findViewById(R.id.main_sellstatus);
        mPersonInfoButton = (Button)findViewById(R.id.main_person_info);

        Intent intent = getIntent();
        String ownerName = intent.getStringExtra("ownerName");

        if(intent.hasExtra("ownerName")) {
            mOwnerNameView.setText(ownerName);
        }
        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        mPersonInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPref = getSharedPreferences("autoLoginRecord",MODE_PRIVATE);
                Boolean autoLoginCheck = mPref.getBoolean("autoLoginCheck",false);
                Intent intent = new Intent(getApplicationContext(), personInfoActivity.class);
                if(autoLoginCheck==false){// 자동로그인이 체크되어 있지 않거나 로그인이 첫번째인 경우는 기존에 전달받은 이름을 사용
                    intent.putExtra("ownerName",ownerName);
                    startActivity(intent);
                }else{ //자동로그인이 체크되어있는 경우 SharedPreferences에서 이름 가져오면 됨
                    String ownerName = mPref.getString("ownerName",null);
                    intent.putExtra("ownerName",ownerName);
                    startActivity(intent);
                }
            }
        });
    }
}
