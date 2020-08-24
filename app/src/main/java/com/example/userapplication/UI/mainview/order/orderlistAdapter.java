package com.example.userapplication.UI.mainview.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.order.data.OrderListResponseData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class orderlistAdapter extends RecyclerView.Adapter<orderlistAdapter.ViewHolder> {
    private List<OrderListResponseData> number;

    public orderlistAdapter(List<OrderListResponseData> datas){
        number = datas;
    }

    @NonNull
    @Override
    public orderlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.orderlist_item, parent, false);
        orderlistAdapter.ViewHolder viewHolder = new orderlistAdapter.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderlistAdapter.ViewHolder holder, int position) {
        holder.order.setText(String.valueOf(number.get(position).getOrderNum()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView order;
        public ViewHolder(@NonNull View v) {
            super(v);
            order = v.findViewById(R.id.orderlist_number);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), orderDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderNum", number.get(getAdapterPosition()).getOrderNum());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return number.size();
    }
}
