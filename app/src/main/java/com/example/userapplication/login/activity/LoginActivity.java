package com.example.userapplication.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;
import com.example.userapplication.login.data.LoginData;
import com.example.userapplication.login.data.LoginResponse;
import com.example.userapplication.login.network.RetrofitClient;
import com.example.userapplication.login.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private Button mJoinButton;
    private TextView mFindIdPassword;
    private CheckBox mAutoLogin;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        mAutoLogin = (CheckBox) findViewById(R.id.auto_login_checkBox);
        mLoginButton = (Button) findViewById(R.id.login_btn);
        mJoinButton = (Button) findViewById(R.id.join_btn);
        mFindIdPassword = (TextView) findViewById(R.id.find_id_password);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        mFindIdPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (v.isClickable()) {
                    //레이아웃 파일 find_email_password.xml을 불러와 화면에 다이얼로그를 보여준다.
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.find_email_password, null, false);
                    builder.setView(view);

                    final Button closeBtn = (Button) view.findViewById(R.id.close_btn);
                    final Button loseEmailBtn = (Button) view.findViewById(R.id.email_find_btn);
                    final Button losePasswordBtn = (Button) view.findViewById(R.id.password_find_btn);

                    final AlertDialog dialog = builder.create();
                    dialog.setCancelable(false); //화면 외부 클릭시 dismiss 현상 막기

                    closeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    loseEmailBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), FindEmailActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    losePasswordBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), FindPasswordActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

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

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mPasswordView.setError("비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("8자 이상 20자 이하의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(email, password));
        }
    }

    private void startLogin(LoginData data) {
        service.ownerLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getCode()==201) {
                    String ownerName = response.body().getOwnerName();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("ownerName", ownerName);
                    if(mAutoLogin.isChecked()){
                        //자동 로그인 구현하기 위해서 sharedPreference로 이메일, 비밀번호, 사용자 이름, 자동로그인 버튼 클릭 체크 여부 저장
                        SharedPreferences mPrefs = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("ownerEmail",mEmailView.getText().toString());
                        editor.putString("ownerPwd",mPasswordView.getText().toString());
                        editor.putString("ownerName",ownerName);
                        editor.putBoolean("autoLoginCheck",true);
                        editor.commit();

                        startActivity(intent);
                        finish();
                    }else{
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {return email.contains("@");}

    private boolean isPasswordValid(String password) {
        return ((password.length() >= 8) && (password.length()<=20));
    }
}
