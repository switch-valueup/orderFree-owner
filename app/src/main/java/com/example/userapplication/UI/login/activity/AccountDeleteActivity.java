package com.example.userapplication.UI.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.userapplication.R;
import com.example.userapplication.UI.login.data.DeleteAccountData;
import com.example.userapplication.UI.login.data.DeleteAccountResponse;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;
import com.example.userapplication.UI.mainview.activity.personInfoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//5-4
public class AccountDeleteActivity extends AppCompatActivity {

    private TextView mGobackView;
    private Button mAccountDeleteButton;
    private CheckBox mCheckBox;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_delete);

        mGobackView = (TextView)findViewById(R.id.goback);
        mAccountDeleteButton = (Button)findViewById(R.id.account_delete_btn);
        mCheckBox = (CheckBox)findViewById(R.id.checkBox);

        service = RetrofitClient.getClient().create(ServiceApi .class);

        Intent intent = getIntent();
        String ownerEmail = intent.getStringExtra("ownerEmail");

        //뒤로 가기 버튼 눌렀을 때 전 레이아웃으로 전환
        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGobackView.isClickable()) {
                    Intent intent = new Intent(getApplicationContext(), personInfoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        mAccountDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mGobackView.isClickable()) {
                    if(checkState()){
                        startDeleteAccount(new DeleteAccountData(ownerEmail));
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    }
                    else {
                        Toast.makeText(AccountDeleteActivity.this,"회원탈퇴에 동의하지 않으셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean checkState()
    {
        if(mCheckBox.isChecked()){
            return true;
        }
        else
            return false;
    }

    private void startDeleteAccount(DeleteAccountData data) {
        service.ownerDeleteAccount(data).enqueue(new Callback<DeleteAccountResponse>() {

            @Override
            public void onResponse(Call<DeleteAccountResponse> call, Response<DeleteAccountResponse> response) {
                DeleteAccountResponse result = response.body();
                Log.d("n","hi");
                if(result.getCode() == 200) {
                    Log.d("n","hi1");
                    Toast.makeText(AccountDeleteActivity.this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAccountResponse> call, Throwable t) {
                Toast.makeText(AccountDeleteActivity.this, "회원 탈퇴 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원 탈퇴 에러 발생", t.getMessage());
            }
        });
    }
}
