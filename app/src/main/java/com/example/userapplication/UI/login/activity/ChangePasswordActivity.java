package com.example.userapplication.UI.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.userapplication.R;
import com.example.userapplication.UI.login.data.ChangePasswordData;
import com.example.userapplication.UI.login.data.ChangePasswordResponse;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;
import com.example.userapplication.UI.mainview.activity.personInfoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//5-4
public class ChangePasswordActivity extends Activity {

    private Button mCloseButton;
    private Button mChangeButton;
    private TextView mNewPasswordView;
    private TextView mRePasswordView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.change_password);

        mCloseButton=(Button)findViewById(R.id.close_btn);
        mChangeButton=(Button)findViewById(R.id.change_btn);
        mNewPasswordView=(TextView)findViewById(R.id.new_password_et);
        mRePasswordView=(TextView)findViewById(R.id.re_password_et);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCloseButton.isClickable()) {
                    Intent intent = new Intent(getApplicationContext(), personInfoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
    }
    private void changePassword(){
        mNewPasswordView.setError(null);
        mRePasswordView.setError(null);

        String new_password=mNewPasswordView.getText().toString();
        String re_password=mRePasswordView.getText().toString();

        Intent intent = getIntent();
        String email = intent.getStringExtra("ownerEmail");

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (new_password.isEmpty()) {
            mNewPasswordView.setError("비밀번호를 입력해주세요.");
            focusView = mNewPasswordView;
            cancel = true;
        } else if (!isPasswordValid(new_password)) {
            mNewPasswordView.setError("8자 이상 20자 이하 비밀번호를 입력해주세요.");
            focusView = mNewPasswordView;
            cancel = true;
        } else if(re_password.isEmpty()){
            mRePasswordView.setError("비밀번호를 한 번 더 입력해주세요");
            focusView=mRePasswordView;
            cancel=true;
        }else if(!(re_password.equals(new_password))) {
            mRePasswordView.setError("동일한 비밀번호를 입력해주세요.");
            focusView = mRePasswordView;
            cancel = true;
            Toast.makeText(ChangePasswordActivity.this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
        }

        if (cancel) {
            focusView.requestFocus();
        } else
        {
            startChangePassword(new ChangePasswordData(email, new_password));
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean isPasswordValid(String password) {
        return (password.length() >= 8 && password.length() <= 20);
    }

    private void startChangePassword(ChangePasswordData data){
        service.ownerChangePassword(data).enqueue(new Callback<ChangePasswordResponse>(){
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                ChangePasswordResponse result = response.body();
                if(result.getCode()==201) {

                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "패스워드 변경 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("패스워드 변경 에러 발생", t.getMessage());
            }
        });
    }
}
