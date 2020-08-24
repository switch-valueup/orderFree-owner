package com.example.userapplication.UI.login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences mPref = getSharedPreferences("autoLoginRecord",MODE_PRIVATE);
        String saved_email = mPref.getString("ownerEmail",null);
        String saved_pwd = mPref.getString("ownerPwd",null);
        boolean autoLoginCheck = mPref.getBoolean("autoLoginCheck",false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                //만약 저장된 정보가 없거나 자동로그인 체크되어 있지 않으면 로그인 화면으로 이동
                if(!autoLoginCheck || saved_email.length()==0 || saved_pwd.length()==0 ){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else if(autoLoginCheck && saved_email.length()!=0 && saved_pwd.length()!=0){ //저장된 것이 있고 자동로그인이 체크되어 있으면 메인화면으로 이동
                    String ownerName = mPref.getString("ownerName","");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("ownerName",ownerName);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }
}
