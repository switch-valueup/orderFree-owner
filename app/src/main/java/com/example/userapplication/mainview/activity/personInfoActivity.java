package com.example.userapplication.mainview.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;
import com.example.userapplication.login.activity.LoginActivity;

public class personInfoActivity extends AppCompatActivity {
    private TextView mOwnerNameView;
    private Button mLogoutButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);

        mOwnerNameView = (TextView) findViewById(R.id.ownerName) ;
        mLogoutButton = (Button) findViewById(R.id.logout_btn);

        Intent intent = getIntent();
        String ownerName = intent.getStringExtra("ownerName");

        if(intent.hasExtra("ownerName")) {
            mOwnerNameView.setText(ownerName);
        }

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(personInfoActivity.this)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //로그아웃 확인을 눌렀을 때 자동로그인 및 저장된 정보들 모두 삭제
                                SharedPreferences mPref = getSharedPreferences("autoLoginRecord",MODE_PRIVATE);
                                SharedPreferences.Editor editor = mPref.edit();
                                editor.clear();
                                editor.commit();

                                //로그아웃 눌렀을 경우 로그인페이지로 보내고 그 전까지의 activity는 다 지워버림
                                Intent intent = new Intent(personInfoActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            }
        });
    }
}
