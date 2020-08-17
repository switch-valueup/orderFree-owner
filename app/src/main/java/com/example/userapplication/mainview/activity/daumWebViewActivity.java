package com.example.userapplication.mainview.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.userapplication.R;

public class daumWebViewActivity extends AppCompatActivity {
    private WebView daum_webView;
    private Handler handler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_adress);

        // WebView 초기화
        init_webView();

        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView newWebView = new WebView(daumWebViewActivity.this);

            WebSettings webSettings = newWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            final Dialog dialog = new Dialog(daumWebViewActivity.this);
            dialog.setContentView(newWebView);

            ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
            dialog.show();

            newWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onCloseWindow(WebView window) {
                    dialog.dismiss();
                }
            });
            ((WebView.WebViewTransport)resultMsg.obj).setWebView(newWebView);
            resultMsg.sendToTarget();
            return true;
        }
    }

    public void init_webView() {
        // WebView 설정
        daum_webView = (WebView) findViewById(R.id.daum_webview);

        // JavaScript 허용
        daum_webView.getSettings().setJavaScriptEnabled(true);

        // JavaScript의 window.open 허용
        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        daum_webView.getSettings().setSupportMultipleWindows(true);

        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        daum_webView.addJavascriptInterface(new AndroidBridge(), "TestApp");

        // web client 를 chrome 으로 설정
        daum_webView.setWebChromeClient(new WebChromeClient());

        // webview url load. php 파일 주소
        daum_webView.loadUrl("http://ec2-3-34-156-73.ap-northeast-2.compute.amazonaws.com:3000/");
    }


    private class AndroidBridge {

        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // WebView를 초기화 하지않으면 재사용할 수 없음
                    //init_webView();
                    Intent getintent = getIntent();
                    String ownerName =getintent.getStringExtra("ownerName");
                    String ownerEmail = getintent.getStringExtra("ownerEmail");

                    Log.d("here", arg1+arg2);
                    Intent intent = new Intent(getApplicationContext(),personInfoActivity.class);
                    intent.putExtra("addr",arg1+" "+arg2);
                    intent.putExtra("ownerEmail",ownerEmail);
                    intent.putExtra("ownerName",ownerName);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}