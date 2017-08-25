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
import com.wondersgroup.commerce.utils.StringUtil;

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

        if (dicBean != null){
            if (partyMem.getSex() != null && dicBean.getGender() != null){
                viewHolder.tvSex.setText("(" + StringUtil.changeNull(dicBean.getGender().get(partyMem.getSex())) + ")");
            }

            if (partyMem.getType()!=null && dicBean.getPartyMemType() != null){
                viewHolder.tvType.setText(StringUtil.changeNull(dicBean.getPartyMemType().get(partyMem.getType())));
            }

            if (partyMem.getPartyPositon() != null && dicBean.getPartyPosition() != null){
                viewHolder.tvPosition.setText(StringUtil.changeNull(dicBean.getPartyPosition().get(partyMem.getPartyPositon())));
            }

        }
        viewHolder.tvCertNo.setText(partyMem.getCerno());
        viewHolder.tvJoinDate.setText(partyMem.getJoinDate());
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
