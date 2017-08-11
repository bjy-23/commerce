package com.wondersgroup.commerce.fgdj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.PartyMem;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by bjy on 2017/4/28.
 */

public class PartyMemAdapter extends RecyclerView.Adapter {
    private List<PartyMem> data;
    private Context context;
    private OnClickListener onClickListener;
    private DicBean dicBean;

    public PartyMemAdapter(List data, Context context) {
        this.data = data;
        this.context = context;
        dicBean = Hawk.get(Constants.DIC);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dang_yuan,parent,false);
        return new DangyuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DangyuanViewHolder viewHolder = (DangyuanViewHolder) holder;
        PartyMem partyMem = data.get(position);
        viewHolder.tvName.setText(partyMem.getName());
        if (partyMem.getSex() != null){
            LinkedHashMap hashMap = dicBean.getGender();
            viewHolder.tvSex.setText("(" + hashMap.get(partyMem.getSex()).toString() + ")");
        }
        if (partyMem.getType()!=null){
            LinkedHashMap hashMap = dicBean.getPartyMemType();
            viewHolder.tvType.setText(hashMap.get(partyMem.getType()).toString());
        }
        viewHolder.tvCertNo.setText(partyMem.getCerno());
        viewHolder.tvJoinDate.setText(partyMem.getJoinDate());
        if (partyMem.getPartyPositon() != null){
            LinkedHashMap hashMap = dicBean.getPartyPosition();
            viewHolder.tvPosition.setText(hashMap.get(partyMem.getPartyPositon()).toString());
        }
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DangyuanViewHolder extends RecyclerView.ViewHolder{
        private int position;
        private View view;
        private TextView tvName;
        private TextView tvSex;
        private TextView tvType;
        private TextView tvCertNo;
        private TextView tvJoinDate;
        private TextView tvPosition;

        public DangyuanViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvSex = (TextView) itemView.findViewById(R.id.tv_sex);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvCertNo = (TextView) itemView.findViewById(R.id.tv_cert_no);
            tvJoinDate = (TextView) itemView.findViewById(R.id.tv_join_date);
            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
        }
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
