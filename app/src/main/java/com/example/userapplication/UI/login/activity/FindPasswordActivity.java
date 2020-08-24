package com.example.userapplication.UI.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.userapplication.R;
import com.example.userapplication.UI.login.data.FindPasswordData;
import com.example.userapplication.UI.login.data.FindPasswordResponse;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPasswordActivity extends AppCompatActivity {
    private TextView mGobackView;
    private EditText mSearch_psw_emailView;
    private EditText mSearch_psw_nameView;
    private EditText mSearch_psw_phoneView;
    private Button mFind_btnView;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);

        mGobackView = (TextView)findViewById(R.id.goback);
        mSearch_psw_emailView = (EditText)findViewById(R.id.search_psw_email);
        mSearch_psw_nameView = (EditText)findViewById(R.id.search_psw_name);
        mSearch_psw_phoneView = (EditText)findViewById(R.id.search_psw_phone);
        mFind_btnView = (Button) findViewById(R.id.find_btn);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        //오더프리계정 찾기 버튼 눌렀을 때
        mFind_btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { attemptFindPassword(); }
        });

        //뒤로 가기 버튼 눌렀을 때 전 레이아웃인 로그인화면으로 전환
        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void attemptFindPassword() {
        mSearch_psw_emailView.setError(null);
        mSearch_psw_nameView.setError(null);
        mSearch_psw_phoneView.setError(null);

        String email = mSearch_psw_emailView.getText().toString();
        String name = mSearch_psw_nameView.getText().toString();
        String phone = mSearch_psw_phoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mSearch_psw_emailView.setError("이메일을 입력해주세요.");
            focusView = mSearch_psw_emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mSearch_psw_emailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mSearch_psw_emailView;
            cancel = true;
        }

        // 이름 유효성 검사
        if (name.isEmpty()) {
            mSearch_psw_nameView.setError("이름을 입력해주세요.");
            focusView = mSearch_psw_nameView;
            cancel = true;
        }

        // 전화번호의 유효성 검사
        if (phone.isEmpty()) {
            mSearch_psw_phoneView.setError("전화번호를 입력해주세요.");
            focusView = mSearch_psw_phoneView;
            cancel = true;
        } else if (!isPhoneNumberValid(phone)) {
            mSearch_psw_phoneView.setError(" -을 제외한 10자리 혹은 11자리 전화번호를 입력해주세요.");
            focusView = mSearch_psw_phoneView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startFindPassword(new FindPasswordData(email, name, phone));
        }
    }

    private void startFindPassword(FindPasswordData data) {
        service.ownerFindPassword(data).enqueue(new Callback<FindPasswordResponse>() {
            @Override
            public void onResponse(Call<FindPasswordResponse> call, Response<FindPasswordResponse> response) {
                FindPasswordResponse result = response.body();
                Toast.makeText(FindPasswordActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getCode()==201) { //서버로부터 이름과 전화번호에 일치하는 이메일을 찾아왔을 경우

                }
            }
            @Override
            public void onFailure(Call<FindPasswordResponse> call, Throwable t) {
                Toast.makeText(FindPasswordActivity.this, "이메일 찾기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("이메일 찾기 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {return email.contains("@");}

    private boolean isPhoneNumberValid(String phoneNumber) { //전화번호 유효성 검사 함수, 전화번호는 10자리나 11자리여야함
        return ( (phoneNumber.length()==(int)11) || (phoneNumber.length() ==(int)10) ) ;
    }
}
