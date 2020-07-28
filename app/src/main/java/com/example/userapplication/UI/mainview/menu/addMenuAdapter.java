package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponseData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class addMenuAdapter extends RecyclerView.Adapter<addMenuAdapter.ViewHolder> {
    private List<MenuListResponseData> category;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ViewHolder(@NonNull View v) {
            super(v);
            itemView = v;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), editMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("menuName", category.get(pos).getMenuName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public addMenuAdapter(List<MenuListResponseData> datas){
        category = datas;
    }

    @NonNull
    @Override
    public addMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.addmenu_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull addMenuAdapter.ViewHolder holder, int position) {
        TextView cate = holder.itemView.findViewById(R.id.addmenu_item_category);
        TextView menu = holder.itemView.findViewById(R.id.addmenu_item_category);
        cate.setText(category.get(position).getCategory());
        menu.setText(category.get(position).getMenuName());
    }

    @Override
    public int getItemCount() {
        return category.size();
    }
}
