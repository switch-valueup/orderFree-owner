package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.userapplication.R;
import com.example.userapplication.UI.login.activity.MainActivity;
import com.example.userapplication.UI.mainview.menu.data.MenuAlignData;
import com.example.userapplication.UI.mainview.menu.data.MenuAlignResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponseData;
import com.example.userapplication.Util.CategoryConverter;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuAlignActivity extends AppCompatActivity {
    private ServiceApi service;
    private int index = 0;
    private MenuListResponseData list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        ConstraintLayout layout = findViewById(R.id.align_activity);
        ConstraintLayout container = findViewById(R.id.align_container);
        Button button = findViewById(R.id.align_btn);
        Spinner spinner = findViewById(R.id.spinner_category);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                if(index == 0){
                    finish();
                }
            }
        });
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", new CategoryConverter().toIntConvert(spinner.getSelectedItem().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
