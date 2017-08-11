package com.wondersgroup.commerce.teamwork.casedeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;

import java.util.List;

/**
 * Created by bjy on 2017/8/3.
 */

public class CaseAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<CaseInvestigateTitle> data;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public CaseAdapter(Context context, List<CaseInvestigateTitle> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CaseViewHolder(inflater.inflate(R.layout.item_case_investigate_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CaseViewHolder viewHolder = (CaseViewHolder) holder;
        CaseInvestigateTitle bean = data.get(position);
        viewHolder.tv_CaseName.setText(bean.getCaseName());
        viewHolder.tv_CaseNo.setText(bean.getCaseNo());
        viewHolder.tv_CaseDate.setText(bean.getCaseFidate());
        viewHolder.tvCaseStatus.setText(bean.getAppStatus());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CaseViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_CaseName;
        private TextView tv_CaseNo;
        private TextView tv_CaseDate;
        private TextView tvCaseStatus;
        private View view;

        public CaseViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tv_CaseName = (TextView) itemView.findViewById(R.id.case_name_textview);
            tv_CaseNo = (TextView) itemView.findViewById(R.id.case_number_textview);
            tv_CaseDate = (TextView) itemView.findViewById(R.id.case_date_textview);
            tvCaseStatus = (TextView) itemView.findViewById(R.id.tv_case_status);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
