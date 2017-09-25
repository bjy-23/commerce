package com.wondersgroup.commerce.teamwork.casedeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.casedeal.bean.CaseQueryBean;

import java.util.List;

/**
 * Created by bjy on 2017/9/22.
 */

public class CaseQueryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CaseQueryBean> mData;

    private LayoutInflater layoutInflater;
    private onItemClick onItemClick;

    public CaseQueryAdapter(Context mContext, List<CaseQueryBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_case_query, parent, false);
        return new CaseQueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CaseQueryViewHolder viewHolder = (CaseQueryViewHolder) holder;
        CaseQueryBean bean = mData.get(position);
        viewHolder.tvName.setText(bean.getCaseName());
        viewHolder.tvNo.setText("案件编号：" + bean.getCaseNo());
        viewHolder.tvRegDate.setText("立案日期：" + bean.getRegCaseDateShow1());
        viewHolder.tvEndDate.setText("结案日期：" + bean.getEndCaseDateShow1());
        viewHolder.tvDuration.setText("时限：" + bean.getCaseDuration() + " 天");
        viewHolder.tvRegState.setText("案件阶段：" + bean.getRegStage2Show1()!= null? bean.getRegStage2Show1(): "");
        viewHolder.tvOrgan.setText("立案机关：" + bean.getTransactOrganShow());
        viewHolder.tvConficate.setText("罚没金额：" + bean.getConficateAmount1() + " 元");
        viewHolder.tvInWare.setText("入库金额：" + bean.getInWareAmount1() + " 元");

        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CaseQueryViewHolder extends RecyclerView.ViewHolder{
        private View root;
        private TextView tvName;
        private TextView tvNo;
        private TextView tvRegDate;
        private TextView tvEndDate;
        private TextView tvDuration;
        private TextView tvRegState;
        private TextView tvOrgan;
        private TextView tvConficate;
        private TextView tvInWare;

        public CaseQueryViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNo = (TextView) itemView.findViewById(R.id.tv_no);
            tvRegDate = (TextView) itemView.findViewById(R.id.tv_reg_date);
            tvEndDate = (TextView) itemView.findViewById(R.id.tv_end_date);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
            tvRegState = (TextView) itemView.findViewById(R.id.tv_reg_state);
            tvOrgan = (TextView) itemView.findViewById(R.id.tv_organ);
            tvConficate = (TextView) itemView.findViewById(R.id.tv_conficate);
            tvInWare = (TextView) itemView.findViewById(R.id.tv_inWare);
        }
    }

    public interface onItemClick{
        void onClick(int position);
    }

    public void setOnItemClick(CaseQueryAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
