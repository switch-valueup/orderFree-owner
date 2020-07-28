package com.example.userapplication.UI.mainview.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class orderlistAdapter extends RecyclerView.Adapter<orderlistAdapter.ViewHolder> {
    private int[] number;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ViewHolder(@NonNull View v) {
            super(v);
            itemView = v;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, orderDetailActivity.class);
                    intent.putExtra("orderList",number);
                    intent.putExtra("currentOrderPos", getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }

    public orderlistAdapter(int[] datas){
        number = datas;
    }

    @NonNull
    @Override
    public orderlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.orderlist_item, parent, false);
        orderlistAdapter.ViewHolder viewHolder = new orderlistAdapter.ViewHolder(item);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull orderlistAdapter.ViewHolder holder, int position) {
        TextView num = holder.itemView.findViewById(R.id.orderlist_number);
        num.setText(number[position]);
    }

    @Override
    public int getItemCount() {
        return number.length;
    }
}
