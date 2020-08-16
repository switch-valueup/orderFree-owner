package com.example.userapplication.UI.mainview.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.R;
import com.example.userapplication.UI.mainview.menu.data.MenuDeleteData;
import com.example.userapplication.UI.mainview.menu.data.MenuDeleteResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponse;
import com.example.userapplication.UI.mainview.menu.data.MenuListResponseData;
import com.example.userapplication.Util.CategoryConverter;
import com.example.userapplication.network.RetrofitClient;
import com.example.userapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addMenuAdapter extends RecyclerView.Adapter<addMenuAdapter.ViewHolder> {
    private List<MenuListResponseData> lists;

    public addMenuAdapter(List<MenuListResponseData> datas){
        lists = datas;
    }

    @NonNull
    @Override
    public addMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.addmenu_category_item, parent, false);
        addMenuAdapter.ViewHolder viewHolder = new addMenuAdapter.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull addMenuAdapter.ViewHolder holder, int position) {
        AddMenuItem item = new AddMenuItem(new CategoryConverter().toStringConvert(lists.get(position).getCategory()),lists.get(position).getMenuName());

        holder.category.setText(item.getCategory());
        holder.menu.setText(item.getMenu());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView category;
        TextView menu;
        public ViewHolder(@NonNull View v) {
            super(v);

            category = v.findViewById(R.id.addmenu_item_category);
            menu = v.findViewById(R.id.addmenu_item_name);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), editMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("menuName", lists.get(pos).getMenuName());
                    v.getContext().startActivity(intent);
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setMessage("삭제하시겠습니까?");
                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData(lists.get(getAdapterPosition()).getMenuName(),v);
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void deleteData(String menuName, View v){
        MenuDeleteData menuDeleteData = new MenuDeleteData(v.getContext().getSharedPreferences("autoLoginRecord", v.getContext().MODE_PRIVATE).getString("ownerEmail","err"), menuName);
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        service.ownerMenuDelete(menuDeleteData).enqueue(new Callback<MenuDeleteResponse>() {
            @Override
            public void onResponse(Call<MenuDeleteResponse> call, Response<MenuDeleteResponse> response) {

            }

            @Override
            public void onFailure(Call<MenuDeleteResponse> call, Throwable t) {

            }
        });
    }
}
