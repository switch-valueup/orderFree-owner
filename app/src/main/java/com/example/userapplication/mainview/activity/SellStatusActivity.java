package com.example.userapplication.mainview.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapplication.R;
import com.example.userapplication.login.activity.MainActivity;
import com.example.userapplication.login.network.RetrofitClient;
import com.example.userapplication.login.network.ServiceApi;
import com.example.userapplication.mainview.data.Selldata;
import com.example.userapplication.mainview.data.SelldataRequest;
import com.example.userapplication.mainview.data.SelldataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SellStatusActivity extends AppCompatActivity {

    private TextView mGobackView;
    private TextView mSellCountView;
    private TextView mTotalAmountView;
    Button mStartdate;
    Button mEnddate;
    Button mChoice;
    Button mConfirm;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Selldata> list=new ArrayList<>();
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellstatus);

        Intent intent = getIntent();
        String ownerEmail = intent.getStringExtra("ownerEmail");

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mGobackView = (TextView) findViewById(R.id.goback);
        mSellCountView = (TextView)findViewById(R.id.sell_cnt_tv);
        mTotalAmountView =(TextView)findViewById(R.id.total_amount_tv);
        mStartdate = (Button) findViewById(R.id.startdate_btn);
        mEnddate = (Button) findViewById(R.id.enddate_btn);
        mChoice =(Button)findViewById(R.id.choice_btn);
        mConfirm = (Button) findViewById(R.id.confirm_btn);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        final String[] mStartDate = {""};
        final String[] mEndDate = {""};

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

        //확인 버튼 눌렀을 때 전 레이아웃인 메인 화면으로 전환
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mConfirm.isClickable()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //시작날짜선택
        DatePickerDialog startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(month<9&&dayOfMonth<10)
                    mStartDate[0] =Integer.toString(year)+"0"+Integer.toString(month+1)+"0"+Integer.toString(dayOfMonth);
                else if(month>9&&dayOfMonth<10)
                    mStartDate[0] =Integer.toString(year)+Integer.toString(month+1)+"0"+Integer.toString(dayOfMonth);
                else if(month<9&&dayOfMonth>10)
                    mStartDate[0] =Integer.toString(year)+"0"+Integer.toString(month+1)+Integer.toString(dayOfMonth);
                else
                    mStartDate[0] =Integer.toString(year)+Integer.toString(month+1)+Integer.toString(dayOfMonth);
                mStartdate.setText( year +"/" + (month+1) + "/" + dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        //종료날짜선택
        DatePickerDialog endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(month<9&&dayOfMonth<10)
                    mEndDate[0] =Integer.toString(year)+"0"+Integer.toString(month+1)+"0"+Integer.toString(dayOfMonth);
                else if(month>9&&dayOfMonth<10)
                    mEndDate[0] =Integer.toString(year)+Integer.toString(month+1)+"0"+Integer.toString(dayOfMonth);
                else if(month<9&&dayOfMonth>10)
                    mEndDate[0] =Integer.toString(year)+"0"+Integer.toString(month+1)+Integer.toString(dayOfMonth);
                else
                    mEndDate[0] =Integer.toString(year)+Integer.toString(month+1)+Integer.toString(dayOfMonth);

                mEnddate.setText( year +"/" + (month+1) + "/" + dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        //시작날짜선택
        mStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStartdate.isClickable()) {
                    startDatePickerDialog.show();
                }
            }
        });

        //종료날짜선택
        mEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEnddate.isClickable()) {
                    endDatePickerDialog.show();
                }
            }
        });

       //날짜 선택 후 확인 버튼
        mChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChoice.isClickable()) {
                    SharedPreferences mPref = getSharedPreferences("autoLoginRecord",MODE_PRIVATE);
                    String saved_email = mPref.getString("ownerEmail",null);
                    Log.v("버튼눌림","getData직전");
                     getData(new SelldataRequest(saved_email,mStartDate[0],mEndDate[0]));
                    }
                }
            });
        }

        //selldataresponse에서 파싱해서 가격값을 합쳐서 저장하는 함수
        public int getItemTotalAmount(List<Selldata> list){
             int sum=0;
             if(list!=null) {
                  for (int i = 0; i < list.size(); i++) {
                     sum += list.get(i).getPrice();
              }
          }
              else
                 sum=0;
              return sum;
        }

         private void getData(SelldataRequest data) {
              service.ownerSellData(data).enqueue(new Callback<SelldataResponse>() {
                 @Override
                 public void onResponse(Call<SelldataResponse> call, Response<SelldataResponse> response) {
                 SelldataResponse result = response.body();
                 List<Selldata> list=new ArrayList<>();
                 if (result.getCode()==200) { //서버로부터 data에 일치하는 sell data 받음
                     //menu(string), count(int), price(int)
                     list.clear();
                     list = result.getObject();
                     recyclerView = (RecyclerView)findViewById(R.id.recylerView); //아이디 연결
                     recyclerView.setHasFixedSize(true); // 리사이클뷰 기존성능 강화
                     layoutManager = new LinearLayoutManager(getApplicationContext());
                     recyclerView.setLayoutManager(layoutManager);
                     adapter = new SellAdapter(list);
                     recyclerView.setAdapter(adapter);
                     adapter.notifyDataSetChanged();
                     Log.v("getData2","getData2");
                      mSellCountView.setText(Integer.toString(adapter.getItemCount()));
                      mTotalAmountView.setText(Integer.toString(getItemTotalAmount(list)));
                     //리사이클러뷰에 어댑터 연결
                  }
             }

            @Override
            public void onFailure(Call<SelldataResponse> call, Throwable t) {
                Toast.makeText(SellStatusActivity.this, "판매 데이터 에러", Toast.LENGTH_SHORT).show();
                Log.e("판매 데이터 에러", t.getMessage());
            }
        });
    }
}