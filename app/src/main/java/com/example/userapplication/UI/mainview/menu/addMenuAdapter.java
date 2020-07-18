package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.Data.menuType;
import com.example.userapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class addMenuAdapter extends RecyclerView.Adapter<addMenuAdapter.ViewHolder> {
    private menuType[] category;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ViewHolder(@NonNull View v) {
            super(v);
            itemView = v;
        }
    }

    public addMenuAdapter(menuType[] datas){
        category = datas;
    }

    @NonNull
    @Override
    public addMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.addmenu_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull addMenuAdapter.ViewHolder holder, int position) {
        TextView cate = holder.itemView.findViewById(R.id.addmenu_item_category);
        TextView menu = holder.itemView.findViewById(R.id.addmenu_item_category);
        cate.setText(category[position].getCategory());
        menu.setText(category[position].getCategory());
    }

    @Override
    public int getItemCount() {
        return category.length;
    }
}
