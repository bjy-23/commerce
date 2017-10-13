package com.wondersgroup.commerce.recyclerView.viewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.teamwork.email.EmailBean;

/**
 * Created by bjy on 2017/10/11.
 */

public class EmailViewModel extends ViewModel {
    private EmailBean bean;

    public EmailViewModel(EmailBean bean) {
        this.bean = bean;
    }

    @Override
    public int type() {
        return R.layout.item_email;
    }

    @Override
    public RecyclerView.ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dataBind(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;

        holder.tvName.setText(bean.getUserName());
        holder.tvTime.setText(bean.getDate());
        holder.tvTitle.setText(bean.getTitle());
        if ("1".equals(bean.getFlag()))
            holder.imgDot.setVisibility(View.GONE);
        else
            holder.imgDot.setVisibility(View.VISIBLE);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onClick(position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvTitle;
        private ImageView imgDot;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            imgDot = (ImageView) itemView.findViewById(R.id.img_dot);
        }
    }

    public EmailBean getBean() {
        return bean;
    }

    public void setBean(EmailBean bean) {
        this.bean = bean;
    }
}
