package com.example.userapplication.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userapplication.R;

public class FindEmailResultActivity extends AppCompatActivity {
    private TextView mGobackView;
    private TextView mTextVeiw;
    private TextView mTextVeiw2;
    private TextView mTextView3;
    private Button mOk_btnView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_email_result);

        mGobackView = (TextView)findViewById(R.id.goback);
        mTextVeiw = (TextView)findViewById(R.id.textView);
        mTextVeiw2 = (TextView)findViewById(R.id.textView2);
        mTextView3 = (TextView)findViewById(R.id.textView3);
        TextView mSearchedEmailView = (TextView)findViewById(R.id.searched_email);
        mOk_btnView = (Button) findViewById(R.id.ok_btn);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

        if(intent.hasExtra("userEmail")) {
            mSearchedEmailView.setText(userEmail);
        }

        mOk_btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //뒤로 가기 버튼 눌렀을 때 전 레이아웃인 FindEmailActivity화면
        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    Intent intent = new Intent(getApplicationContext(),FindEmailActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
