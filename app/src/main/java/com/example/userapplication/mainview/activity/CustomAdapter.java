package com.example.userapplication.mainview.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userapplication.R;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;

    // 아이템 뷰를 저장하는 뷰 홀더 클래스
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView english;
        protected TextView korean;


        public CustomViewHolder(View view) {
            super(view);
            //뷰 객체에 대한 참조. (hold strong reference)
            this.id = (TextView) view.findViewById(R.id.id_listitem);
            this.english = (TextView) view.findViewById(R.id.english_listitem);
            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
        }
    }

    // 생성자에게 데이터 리스트 객체를 전달받는다.
    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }


    //onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하고 뷰를 붙여주는 부분
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.addmenu_itemlist, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    // onBindViewHolder() - 재활용 되는 뷰가 호출하여 실행되는 메소드, 뷰 홀더를 전달하고 어댑터는 position의 데이터를 결합
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.english.setGravity(Gravity.CENTER);
        viewholder.korean.setGravity(Gravity.CENTER);

        viewholder.id.setText(mList.get(position).getId());
        viewholder.english.setText(mList.get(position).getEnglish());
        viewholder.korean.setText(mList.get(position).getKorean());
    }

    // getItemCount () - 전체 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
