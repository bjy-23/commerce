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
import com.wondersgroup.commerce.fgdj.bean.LeagueMem;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by bjy on 2017/5/10.
 */

public class LeagueMemAdapter extends RecyclerView.Adapter {
    private List<LeagueMem> data;
    private Context context;
    private DicBean dicBean;
    private OnClickListener onClickListener;

    public LeagueMemAdapter(List<LeagueMem> data, Context context) {
        this.data = data;
        this.context = context;
        dicBean = Hawk.get(Constants.DIC);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuan_yuan,parent,false);
        return new TuanYuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TuanYuanViewHolder viewHolder = (TuanYuanViewHolder) holder;
        LeagueMem leagueMem = data.get(position);
        viewHolder.tvName.setText(leagueMem.getName());
        if (leagueMem.getSex() != null){
            LinkedHashMap hashMap = dicBean.getGender();
            viewHolder.tvSex.setText("(" + hashMap.get(leagueMem.getSex()).toString() + ")");
        }
        if (leagueMem.getType() != null){
            LinkedHashMap hashMap = dicBean.getLeagueMemType();
            viewHolder.tvType.setText(hashMap.get(leagueMem.getType()).toString());
        }
        viewHolder.tvCertNo.setText(leagueMem.getCerno());
        viewHolder.tvJoinDate.setText(leagueMem.getJoinDate());
        if (leagueMem.getLeaguePositon() != null){
            LinkedHashMap hashMap = dicBean.getLeaguePosition();
            viewHolder.tvPosition.setText(hashMap.get(leagueMem.getLeaguePositon()).toString());
        }
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null)
                    onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TuanYuanViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView tvName;
        private TextView tvSex;
        private TextView tvType;
        private TextView tvCertNo;
        private TextView tvJoinDate;
        private TextView tvPosition;

        public TuanYuanViewHolder(View itemView) {
            super(itemView);
            view  = itemView;
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
