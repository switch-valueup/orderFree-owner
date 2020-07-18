package com.example.userapplication.UI.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;
import com.example.userapplication.UI.login.data.FindEmailData;
import com.example.userapplication.UI.login.data.FindEmailResponse;
import com.example.userapplication.UI.login.network.RetrofitClient;
import com.example.userapplication.UI.login.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindEmailActivity extends AppCompatActivity {
    private TextView mGobackView;
    private EditText mSearch_email_nameView;
    private EditText mSearch_email_phoneView;
    private Button mFind_btnView;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findemail);

        mGobackView = (TextView)findViewById(R.id.goback);
        mSearch_email_nameView = (EditText)findViewById(R.id.search_email_name);
        mSearch_email_phoneView = (EditText)findViewById(R.id.search_email_phone);
        mFind_btnView = (Button) findViewById(R.id.find_btn);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        //오더프리계정 찾기 버튼 눌렀을 때
        mFind_btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { attemptFindEmail(); }
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

    private void attemptFindEmail() {
        mSearch_email_nameView.setError(null);
        mSearch_email_phoneView.setError(null);

        String name = mSearch_email_nameView.getText().toString();
        String phone = mSearch_email_phoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 이름 유효성 검사
        if (name.isEmpty()) {
            mSearch_email_nameView.setError("이름을 입력해주세요.");
            focusView = mSearch_email_nameView;
            cancel = true;
        }

        // 전화번호의 유효성 검사
        if (phone.isEmpty()) {
            mSearch_email_phoneView.setError("전화번호를 입력해주세요.");
            focusView = mSearch_email_phoneView;
            cancel = true;
        } else if (!isPhoneNumberValid(phone)) {
            mSearch_email_phoneView.setError(" -을 제외한 10자리 혹은 11자리 전화번호를 입력해주세요.");
            focusView = mSearch_email_phoneView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startFindEmail(new FindEmailData(name, phone));
        }
    }

    private void startFindEmail(FindEmailData data) {
        service.ownerFindEmail(data).enqueue(new Callback<FindEmailResponse>() {
            @Override
            public void onResponse(Call<FindEmailResponse> call, Response<FindEmailResponse> response) {
                FindEmailResponse result = response.body();
                Toast.makeText(FindEmailActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getCode()==201) { //서버로부터 이름과 전화번호에 일치하는 이메일을 찾아왔을 경우
                    String ownerEmail = response.body().getOwnerEmail();
                    Intent intent = new Intent(getApplicationContext(), FindEmailResultActivity.class);
                    intent.putExtra("ownerEmail", ownerEmail);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<FindEmailResponse> call, Throwable t) {
                Toast.makeText(FindEmailActivity.this, "이메일 찾기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("이메일 찾기 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isPhoneNumberValid(String phonenumber) { //전화번호 유효성 검사 함수, 전화번호는 10자리나 11자리여야함
        return ( (phonenumber.length()==(int)11) || (phonenumber.length() ==(int)10) ) ;
    }
}
