package com.example.userapplication.mainview.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.userapplication.R;

public class addMenuResultActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmenu_result);

        String id = "";
        String korean = "";
        String english = "";

        Bundle extras = getIntent().getExtras();

        id = extras.getString("id");
        korean = extras.getString("korean");
        english = extras.getString("english");


        TextView textView = (TextView) findViewById(R.id.textView_result);

        String str = id + '\n' + english + '\n' + korean;
        textView.setText(str);
    }
}