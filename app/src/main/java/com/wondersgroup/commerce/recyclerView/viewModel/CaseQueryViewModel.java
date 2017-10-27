package com.wondersgroup.commerce.recyclerView.viewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.teamwork.casedeal.bean.CaseQueryBean;

/**
 * Created by bjy on 2017/10/11.
 */

public class CaseQueryViewModel extends ViewModel {
    private CaseQueryBean bean;

    public CaseQueryViewModel(CaseQueryBean bean) {
        this.bean = bean;
    }

    @Override
    public int type() {
        return R.layout.item_case_query;
    }

    @Override
    public RecyclerView.ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dataBind(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.tvNo.setText(bean.getCaseName());
        viewHolder.tvCaseName.setText("案件编号：" + bean.getCaseNo());
        viewHolder.tvOrgan.setText("立案机关：" + bean.getTransactOrganShow());
        viewHolder.tvRegDate.setText("立案日期：" + bean.getRegCaseDateShow1());
        viewHolder.tvPunishOrgan.setText("处罚决定机关：" + bean.getPunishOrgan());
        viewHolder.tvPunishDate.setText("处罚决定日期：" + bean.getPendecissdate1Show());
        viewHolder.tvPunishDoc.setText("处罚决定书文号：" + bean.getPenDecWritNo());
        viewHolder.tvConficate.setText("罚没金额：" + bean.getConficateAmount1() + " 元");
        viewHolder.tvFinishDate.setText("办结日期：" + bean.getFinishCaseDateShow1());

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.onClick(position);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView tvNo;
        private TextView tvCaseName;
        private TextView tvOrgan;
        private TextView tvRegDate;
        private TextView tvPunishOrgan;
        private TextView tvPunishDate;
        private TextView tvPunishDoc;
        private TextView tvConficate;
        private TextView tvFinishDate;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
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

    public CaseQueryBean getBean() {
        return bean;
    }

    public void setBean(CaseQueryBean bean) {
        this.bean = bean;
    }
}
