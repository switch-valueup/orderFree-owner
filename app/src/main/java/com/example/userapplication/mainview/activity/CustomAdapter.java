package com.example.userapplication.mainview.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.userapplication.R;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;
    private Context mContext;

    // context 메뉴를 사용하려면 RecyclerView.ViewHolder를 상속받은 클래스에서
    // onCreateContextMenuListener 구현해야한다.
    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener { // 아이템 뷰를 저장하는 뷰 홀더 클래스

        protected TextView mId;
        protected TextView mEnglish;
        protected TextView mKorean;

        public CustomViewHolder(View view) {
            super(view);

            //뷰 객체에 대한 참조. (hold strong reference)
            this.mId = (TextView) view.findViewById(R.id.id_listitem);
            this.mEnglish = (TextView) view.findViewById(R.id.english_listitem);
            this.mKorean = (TextView) view.findViewById(R.id.korean_listitem);

            // OnCreateContextMenuLister 현재 클래스에서 구현한다고 설
            view.setOnCreateContextMenuListener(this);
        }

        // context 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록
        // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE,1001,1,"항목 수정");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "항목 삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // context 메뉴에서 항목 클릭시 동작을 설정
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: //항목 수정 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        // Dialog를 보여주기 위해서 addmenu_edit_box.xml 파일 사용
                        View view = LayoutInflater.from(mContext).
                                inflate(R.layout.addmenu_edit_box, null, false);
                        builder.setView(view);

                        final Button submitButton = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                        final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
                        final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);

                        // 해당 줄에 입력되어 있던 데이터를 불러와서 dialog에 보여주기
                        editTextID.setText(mList.get(getAdapterPosition()).getId());
                        editTextEnglish.setText((mList.get(getAdapterPosition()).getEnglish()));
                        editTextKorean.setText(mList.get(getAdapterPosition()).getKorean());

                        final AlertDialog dialog = builder.create();
                        submitButton.setOnClickListener(new View.OnClickListener() {

                            //항목 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                            @Override
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strEnglish = editTextEnglish.getText().toString();
                                String strKorean = editTextKorean.getText().toString();

                                Dictionary dic = new Dictionary(strID, strEnglish, strKorean);

                                //ListArray에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dic);

                                //어댑터에서 RecyclerView에 반영하도록 설정
                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                        break;

                    case 1002: //항목 삭제를 누른 경우

                        // ArrayList에서 해당 데이터를 삭제
                        mList.remove(getAdapterPosition());

                        // 어댑터에서 RecyclerView에 반영하도록 설정
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemChanged(getAdapterPosition(),mList.size());

                        break;
                }
                return true;
            }
        };
    }

    // 생성자에게 데이터 리스트 객체를 전달받는다.
    public CustomAdapter(Context context, ArrayList<Dictionary> list) {
        this.mContext=context;
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

        viewholder.mId.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mEnglish.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mKorean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.mId.setGravity(Gravity.CENTER);
        viewholder.mEnglish.setGravity(Gravity.CENTER);
        viewholder.mKorean.setGravity(Gravity.CENTER);

        viewholder.mId.setText(mList.get(position).getId());
        viewholder.mEnglish.setText(mList.get(position).getEnglish());
        viewholder.mKorean.setText(mList.get(position).getKorean());
    }

    // getItemCount () - 전체 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
