package com.example.userapplication.UI.mainview.activity;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.userapplication.UI.login.activity.AccountDeleteActivity;
import com.example.userapplication.UI.login.activity.ChangePasswordActivity;
import com.example.userapplication.UI.login.activity.LoginActivity;
import com.example.userapplication.UI.login.activity.MainActivity;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;
import com.example.userapplication.UI.mainview.data.CheckPasswordData;
import com.example.userapplication.UI.mainview.data.CheckPasswordResponse;
import com.example.userapplication.UI.mainview.data.EnrollmentAddressRequest;
import com.example.userapplication.UI.mainview.data.EnrollmentAddressResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class personInfoActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    private TextView mOwnerNameView;
    private Button mLogoutButton;
    private Button mWitdrawalButton;
    private Button mChangePasswordButton;
    private Button mCompleteButton;
    private EditText mSpecificAddress;
    private TextView mSearchAddressTextView;
    private EditText mStoreNameEnrollment;
    private TextView mOwnerEmailView;

    private TextView mGobackView;
    private EditText mPasswordCheckView;
    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String specificAddress;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);

        mPasswordCheckView=(EditText) findViewById(R.id.info_password_tv); //원래 비밀번호 입력 tv
        mStoreNameEnrollment=(EditText)findViewById(R.id.storename_erollment); // 가게 이름 등록 textview
        mSearchAddressTextView=(TextView)findViewById(R.id.search_address_tv); //도로명 주소 찾기 textview
        mGobackView = (TextView) findViewById(R.id.goback);
        mOwnerNameView = (TextView) findViewById(R.id.ownerName) ;
        mChangePasswordButton=(Button)findViewById(R.id.change_password_btn);
        mLogoutButton = (Button) findViewById(R.id.logout_btn);
        mWitdrawalButton = (Button)findViewById(R.id.witdrawal_btn);
        mLogoutButton.setPaintFlags(mLogoutButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//버튼 밑줄 긋기
        mWitdrawalButton.setPaintFlags(mWitdrawalButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//버튼 밑줄 긋기
        mCompleteButton = (Button)findViewById(R.id.complete_btn); // 완료 버튼
        mOwnerEmailView =(TextView)findViewById(R.id.info_email_tv); //사용자 이메일 보여주는 tv
        mSpecificAddress = (EditText)findViewById(R.id.specific_address); //상세주소 입력 editText



        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        String address = intent.getStringExtra("addr");
        String ownerName = intent.getStringExtra("ownerName");
        String ownerEmail = intent.getStringExtra("ownerEmail");

        if(intent.hasExtra("ownerName")) {
            mOwnerNameView.setText(ownerName);
        }
        if(intent.hasExtra("ownerEmail")) {
            mOwnerEmailView.setText(ownerEmail);
        }
        if(intent.hasExtra("addr"))
            mSearchAddressTextView.setText(address);

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



        mSearchAddressTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), daumWebViewActivity.class);
               intent.putExtra("ownerName",ownerName);
               intent.putExtra("ownerEmail",ownerEmail);
               startActivity(intent);
            }
        });

        mCompleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mStoreNameEnrollment.getText().toString() != null){
                    SharedPreferences mPrefs = getSharedPreferences("ownerStore", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("storeName", mStoreNameEnrollment.getText().toString());
                    editor.commit();
                }
                mOwnerEmailView.setText(ownerEmail);
                confirmEnrollmentAddress(new EnrollmentAddressRequest(mOwnerEmailView.getText().toString(),mStoreNameEnrollment.getText().toString(),mSearchAddressTextView.getText().toString()+" "+mSpecificAddress.getText().toString()));
            }
        });

        mWitdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountDeleteActivity.class);
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

    private void confirmEnrollmentAddress(EnrollmentAddressRequest data){
        service.erollmentAddress(data).enqueue(new Callback<EnrollmentAddressResponse>(){
            @Override
            public void onResponse(Call<EnrollmentAddressResponse> call, Response<EnrollmentAddressResponse> response){
                EnrollmentAddressResponse result = response.body();
                if(result.getCode()==201) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Toast.makeText(personInfoActivity.this, "주소등록 완료", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<EnrollmentAddressResponse> call, Throwable t) {
                Toast.makeText(personInfoActivity.this, "주소등록 에러", Toast.LENGTH_SHORT).show();
                Log.e("주소등록 에러", t.getMessage());
            }
        });
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
