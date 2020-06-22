package com.example.userapplication.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;
import com.example.userapplication.login.data.JoinData;
import com.example.userapplication.login.data.JoinResponse;
import com.example.userapplication.login.network.RetrofitClient;
import com.example.userapplication.login.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    //private AutoCompleteTextView mEmailView;
    private EditText mIdView;
    private EditText mPasswordView;
    private EditText mEmailView;
    private EditText mNameView;
    private EditText mPhoneNumberView;

    private Button mJoinButton;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mIdView = (EditText) findViewById(R.id.join_id);
        mPasswordView = (EditText) findViewById(R.id.join_password);
        mEmailView = (EditText) findViewById(R.id.join_email);
        mNameView = (EditText) findViewById(R.id.join_name);
        mPhoneNumberView =(EditText) findViewById(R.id.join_phone_number);
        mJoinButton = (Button) findViewById(R.id.join_button);
        mProgressView = (ProgressBar) findViewById(R.id.join_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin();
            }
        });
    }

    private void attemptJoin() {
        mIdView.setError(null);
        mPasswordView.setError(null);
        mEmailView.setError(null);
        mNameView.setError(null);
        mPhoneNumberView.setError(null);


        String id = mIdView.getText().toString();
        String password = mPasswordView.getText().toString();
        String email = mEmailView.getText().toString();
        String name = mNameView.getText().toString();
        String phone_number = mPhoneNumberView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 아이디의 유효성 검사
        if (id.isEmpty()) {
            mIdView.setError("아이디를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        } else if (!isIdValid(id)) {
            mIdView.setError("4자 이상 20자 이하의 아이디를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        }

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mEmailView.setError("비밀번호를 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("8자 이상 20자 이하 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            mNameView.setError("이름을 입력해주세요.");
            focusView = mNameView;
            cancel = true;
        }

        // 전화번호의 유효성 검사
        if (phone_number.isEmpty()) {
            mPhoneNumberView.setError("전화번호를 입력해주세요.");
            focusView = mPhoneNumberView;
            cancel = true;
        } else if (!isPhoneNumberValid(phone_number)) {
            mPhoneNumberView.setError(" -을 제외한 10자리 혹은 11자리 전화번호를 입력해주세요.");
            focusView = mPhoneNumberView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(id, password, email, name, phone_number));
            showProgress(true);
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                if (result.getCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }


    private boolean isIdValid(String id) {
        return (id.length() >= 4 && id.length() <= 20);
    }

    private boolean isPasswordValid(String password) {
        return (password.length() >= 8 && password.length() <= 20);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPhoneNumberValid(String phonenumber) {
        return ( (phonenumber.length()==(int)11) || (phonenumber.length() ==(int)10) ) ;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}