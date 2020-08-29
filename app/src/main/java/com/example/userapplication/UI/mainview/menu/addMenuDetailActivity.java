package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.data.AddMenuData;
import com.example.userapplication.UI.mainview.menu.data.AddMenuResponse;
import com.example.userapplication.Util.CategoryConverter;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addMenuDetailActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST_CODE = 0;
    private String imageUrl;
    private String menuName;
    private boolean isAddImage = false;
    private ServiceApi service;
    private String bucket_path = "https://s3.ap-northeast-2.amazonaws.com/valueup/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu_detail);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Button backBtn = findViewById(R.id.addmenu_detail_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void addMenuPost(View view){
        Log.e("flag1", String.valueOf(isAddImage));
        if(isAddImage){
            EditText editMenu = findViewById(R.id.text_menu);
            menuName = editMenu.getText().toString();
            Log.e("menuName", menuName);
            Spinner spinner = findViewById(R.id.spinner_category);
            int category = new CategoryConverter().toIntConvert(spinner.getSelectedItem().toString());
            EditText editPrice = findViewById(R.id.text_price);
            Log.e("price", editPrice.getText().toString());
            int price = Integer.parseInt(editPrice.getText().toString());
            EditText editInfo = findViewById(R.id.text_info);
            String info = editInfo.getText().toString();
            networkPost(menuName, category, imageUrl, price, info);
            Log.e("test1","test1") ;
        }
        else{
            // make toast
        }
        Intent intent = new Intent(this, addMenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void networkPost(String menu, int cate, String img, int price, String info){
        AddMenuData data = new AddMenuData(
                getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err"),
                menu, cate, img, price, info);
        service.ownerAddMenu(data).enqueue(new Callback<AddMenuResponse>() {
            @Override
            public void onResponse(Call<AddMenuResponse> call, Response<AddMenuResponse> response) {
                if(response.body() == null){
                    Log.e("isNull", "yes");
                }
                Log.e("menu register success", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<AddMenuResponse> call, Throwable t) {
                Log.e("fail flag", t.getMessage());
            }
        });
    }

    public void addImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap bmImage = BitmapFactory.decodeStream(in);
                    ImageView imageView = findViewById(R.id.image_addmenu);
                    imageView.setImageBitmap(bmImage);

                    File storage = this.getCacheDir(); // 이 부분이 임시파일 저장 경로
                    EditText editMenu = findViewById(R.id.text_menu);
                    menuName = editMenu.getText().toString();
                    String fileName = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("ownerEmail","err")+ menuName + ".jpg";  // 파일이름은 마음대로!
                    File tempFile = new File(storage,fileName);
                    try{
                        tempFile.createNewFile();  // 파일을 생성해주고
                        FileOutputStream out = new FileOutputStream(tempFile);
                        bmImage.compress(Bitmap.CompressFormat.JPEG, 90 , out);  // 넘거 받은 bitmap을 jpeg(손실압축)으로 저장해줌
                        out.close(); // 마무리로 닫아줍니다.
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    in.close();
                    uploadImageToS3(tempFile);
                    isAddImage = true;
                }catch(Exception e){
                    Log.e("flag3", e.toString());
                }
            }
            else if(resultCode == RESULT_CANCELED){
                Log.e("flag4", "cancel");
                // 사진 선택 취소
            }
        }
    }

    public void uploadImageToS3(File image){
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-northeast-2:1adc58bb-e3a5-4e13-a03f-e632e1b6bed6", // 자격 증명 풀 ID
                Regions.AP_NORTHEAST_2 // 리전
        );

        TransferNetworkLossHandler.getInstance(getApplicationContext());
        TransferUtility transferUtility = TransferUtility.builder()
                .context(getApplicationContext()).defaultBucket("valueup")
                .s3Client(new AmazonS3Client(credentialsProvider, Region.getRegion(Regions.AP_NORTHEAST_2))).build();

        TransferObserver uploadObserver = transferUtility.upload(bucket_path+image.getName(), image, CannedAccessControlList.PublicRead);
        imageUrl = bucket_path+image.getName();
        Log.e("imageName", image.getName());
        Log.e("upload key", bucket_path+image.getName());
        uploadObserver.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if(state == TransferState.COMPLETED){

                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

            }

            @Override
            public void onError(int id, Exception ex) {

            }
        });
    }
}
