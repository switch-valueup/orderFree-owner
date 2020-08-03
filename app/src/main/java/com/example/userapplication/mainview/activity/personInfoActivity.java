package com.example.userapplication.mainview.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;
import com.example.userapplication.login.activity.AccountDeleteActivity;
import com.example.userapplication.login.activity.ChangePasswordActivity;
import com.example.userapplication.login.activity.LoginActivity;
import com.example.userapplication.login.activity.MainActivity;
import com.example.userapplication.login.network.RetrofitClient;
import com.example.userapplication.login.network.ServiceApi;
import com.example.userapplication.mainview.data.CheckPasswordData;
import com.example.userapplication.mainview.data.CheckPasswordResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class personInfoActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    private TextView mOwnerNameView;
    private Button mLogoutButton;
    private Button mWitdrawalButton;
    private Button mChangePasswordButton;
    private Button mSearchAddressButton;
    private Button mCompleteButton;
    private TextView mOwnerEmailView;
    private TextView mEnrollmentView;
    private TextView mAddressView;
    private TextView mGobackView;
    private EditText mPasswordCheckView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);

        mPasswordCheckView=(EditText) findViewById(R.id.info_password_tv);

        mGobackView = (TextView) findViewById(R.id.goback);
        mOwnerNameView = (TextView) findViewById(R.id.ownerName) ;
        mSearchAddressButton = (Button)findViewById(R.id.searchAdd_btn);
        mChangePasswordButton=(Button)findViewById(R.id.change_password_btn);
        mLogoutButton = (Button) findViewById(R.id.logout_btn);
        mWitdrawalButton = (Button)findViewById(R.id.witdrawal_btn);
        mLogoutButton.setPaintFlags(mLogoutButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//버튼 밑줄 긋기
        mWitdrawalButton.setPaintFlags(mWitdrawalButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//버튼 밑줄 긋기
        mCompleteButton = (Button)findViewById(R.id.complete_btn);
        mOwnerEmailView =(TextView)findViewById(R.id.info_email_tv);
        mEnrollmentView = (TextView)findViewById(R.id.info_enrollment_tv);
        mEnrollmentView.setPaintFlags(mEnrollmentView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mAddressView = (TextView)findViewById(R.id.info_address_tv);
        mAddressView.setPaintFlags(mAddressView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        //뒤로 가기 버튼 눌렀을 때 전 레이아웃인 메인화면으로 전환
        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGobackView.isClickable()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Intent intent = getIntent();
        String ownerName = intent.getStringExtra("ownerName");
        String ownerEmail = intent.getStringExtra("ownerEmail");

        if(intent.hasExtra("ownerName")) {
            mOwnerNameView.setText(ownerName);
        }
        if(intent.hasExtra("ownerEmail")) {
            mOwnerEmailView.setText(ownerEmail);
        }

        mSearchAddressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), daumWebViewActivity.class);
               startActivityForResult(intent,SEARCH_ADDRESS_ACTIVITY);
            }
        });

        mWitdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountDeleteActivity.class);
                intent.putExtra("ownerEmail",ownerEmail);
                startActivity(intent);
            }
        });


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

    public void mOnPopupClick(View v)
    {
        Intent i = getIntent();
        String ownerEmail = i.getStringExtra("ownerEmail");
        mPasswordCheckView.setError(null);

        String password = mPasswordCheckView.getText().toString();

        boolean cancel = false;
        View focusView=null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mPasswordCheckView.setError("비밀번호를 입력해주세요.");
            focusView = mPasswordCheckView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startChangePassword(new CheckPasswordData(ownerEmail, password));
        }
    }

    private void startChangePassword(CheckPasswordData data){
        service.ownerCheckPassowrd(data).enqueue(new Callback<CheckPasswordResponse>(){
            @Override
            public void onResponse(Call<CheckPasswordResponse> call, Response<CheckPasswordResponse> response){
                CheckPasswordResponse result = response.body();
                if(result.getCode()==200) {
                    Intent i = getIntent();
                    String ownerEmail = i.getStringExtra("ownerEmail");
                    Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                    intent.putExtra("ownerEmail", ownerEmail).putExtra("ownerPwd", (data.getOwnerPwd()));
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CheckPasswordResponse> call, Throwable t) {
                Toast.makeText(personInfoActivity.this, "비밀번호 에러", Toast.LENGTH_SHORT).show();
                Log.e("비밀번호 에러", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return ((password.length() >= 8) && (password.length()<=20));
    }
}
