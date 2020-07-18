package com.example.userapplication.UI.mainview.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.userapplication.Data.menuType;
import com.example.userapplication.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//TODO init datas
public class addMenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rlayoutManager;
    private menuType[] datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        if(datas != null){
            recyclerView = (RecyclerView)findViewById(R.id.mene_recycler);
            rlayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(rlayoutManager);
            rAdapter = new addMenuAdapter(datas);
            recyclerView.setAdapter(rAdapter);
        }
    }

    // when clicked add menu button
    public void addMenu(View view){
        Intent intent = new Intent(this,menuDetailActivity.class);
        intent.putExtra("isNew", 1);
        startActivity(intent);
    }

}
