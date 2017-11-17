package com.wondersgroup.commerce.recyclerView.viewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.ClueRegisterItemBean;
import com.wondersgroup.commerce.recyclerView.ViewModel;

/**
 * Created by bjy on 2017/11/16.
 */

public class ClueRegisterViewModel extends ViewModel {
    private ClueRegisterItemBean clueRegisterItemBean;

    public ClueRegisterViewModel(ClueRegisterItemBean clueRegisterItemBean) {
        this.clueRegisterItemBean = clueRegisterItemBean;
    }

    @Override
    public int type() {
        return R.layout.clue_redister_item;
    }

    @Override
    public RecyclerView.ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dataBind(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.tvClueName.setText(clueRegisterItemBean.getName());
        holder.tvClueId.setText("线索来源     " + clueRegisterItemBean.getId());
        holder.tvTime.setText("登记时间     " + clueRegisterItemBean.getTime());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.onClick(position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView tvClueName;
        private TextView tvClueId;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvClueName = itemView.findViewById(R.id.tv_clue_name);
            tvClueId = itemView.findViewById(R.id.tv_clue_id);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
