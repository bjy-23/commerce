package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.email.EmailBean;

import java.util.List;

/**
 * Created by bjy on 2017/9/6.
 */

public class CommonAdapter  extends RecyclerView.Adapter{
    private Context context;
    private List<EmailBean> data;

    private LayoutInflater inflater;
    private ItemClick itemClick;

    public CommonAdapter(Context context, List<EmailBean> data) {
        this.context = context;
        this.data = data;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_email,parent,false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        EmailViewHolder viewHolder = (EmailViewHolder) holder;
        EmailBean bean = data.get(position);
        viewHolder.tvName.setText(bean.getUserName());
        viewHolder.tvTime.setText(bean.getDate());
        viewHolder.tvTitle.setText(bean.getTitle());
        if ("1".equals(bean.getFlag()))
            viewHolder.imgDot.setVisibility(View.GONE);
        else
            viewHolder.imgDot.setVisibility(View.VISIBLE);
        if (itemClick != null){
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class EmailViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvTitle;
        private ImageView imgDot;
        private ImageView imgAttach;
        public EmailViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            imgDot = (ImageView) itemView.findViewById(R.id.img_dot);
            imgAttach = (ImageView) itemView.findViewById(R.id.img_attach);
        }
    }

    public interface ItemClick{
        void onClick(int position);
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
