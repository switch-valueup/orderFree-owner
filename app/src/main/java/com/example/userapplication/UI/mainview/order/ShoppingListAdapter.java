package com.example.userapplication.UI.mainview.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.order.data.OrderDetailResponseData;
import com.example.userapplication.UI.mainview.order.data.ShoppingListData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private List<OrderDetailResponseData> datas;

    public ShoppingListAdapter(List<OrderDetailResponseData> datas){ this.datas = datas; }

    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.shopping_list_item, parent, false);
        ShoppingListAdapter.ViewHolder viewHolder = new ShoppingListAdapter.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {
        holder.menu.setText(String.valueOf(datas.get(position).getMenuName()));
        holder.count.setText(String.valueOf(datas.get(position).getCount()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // TODO
        public TextView menu;
        public TextView count;
        public ViewHolder(@NonNull View v) {
            super(v);
            menu = v.findViewById(R.id.shopping_menu);
            count = v.findViewById(R.id.shopping_count);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
