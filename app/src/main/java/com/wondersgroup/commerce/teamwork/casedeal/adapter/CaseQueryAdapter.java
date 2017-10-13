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
        viewHolder.tvNo.setText(bean.getCaseNo());
        viewHolder.tvCaseName.setText("案件名称：" + bean.getCaseName());
        viewHolder.tvOrgan.setText("立案机关：" + bean.getTransactOrganShow());
        viewHolder.tvRegDate.setText("立案日期：" + bean.getRegCaseDateShow1());
        viewHolder.tvPunishOrgan.setText("处罚决定机关：" + bean.getPunishOrgan());
        viewHolder.tvPunishDate.setText("处罚决定日期：" + bean.getPendecissdate1Show());
        viewHolder.tvPunishDoc.setText("处罚决定书文号：" + bean.getPenDecWritNo());
        viewHolder.tvConficate.setText("罚没金额：" + bean.getConficateAmount1() + " 元");
        viewHolder.tvFinishDate.setText("办结日期：" + bean.getFinishCaseDateShow1());

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
        private TextView tvNo;
        private TextView tvCaseName;
        private TextView tvOrgan;
        private TextView tvRegDate;
        private TextView tvPunishOrgan;
        private TextView tvPunishDate;
        private TextView tvPunishDoc;
        private TextView tvConficate;
        private TextView tvFinishDate;

        public CaseQueryViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            tvNo = (TextView) itemView.findViewById(R.id.tv_no);
            tvCaseName = (TextView) itemView.findViewById(R.id.tv_case_name);
            tvOrgan = (TextView) itemView.findViewById(R.id.tv_organ);
            tvRegDate = (TextView) itemView.findViewById(R.id.tv_reg_date);
            tvPunishOrgan = (TextView) itemView.findViewById(R.id.tv_punish_organ);
            tvPunishDate = (TextView) itemView.findViewById(R.id.tv_punish_date);
            tvPunishDoc = (TextView) itemView.findViewById(R.id.tv_punish_doc);
            tvConficate = (TextView) itemView.findViewById(R.id.tv_conficate);
            tvFinishDate = (TextView) itemView.findViewById(R.id.tv_finish_date);
        }
    }

    public interface onItemClick{
        void onClick(int position);
    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
