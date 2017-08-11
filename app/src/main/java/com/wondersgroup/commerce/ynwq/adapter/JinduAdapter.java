package com.wondersgroup.commerce.ynwq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.bean.JinduItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/24.
 */
public class JinduAdapter extends RecyclerView.Adapter<JinduAdapter.ViewHolder> {
    private List<JinduItem> items;

    public JinduAdapter(List<JinduItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_jindu_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JinduItem item=items.get(position);
        holder.title.setText(item.getTitle());
        holder.regNo.setText(item.getRegNo());
        holder.addr.setText(item.getAddr());
        holder.addrType.setText(item.getAddrType());
        holder.type.setText(item.getType());
        holder.date.setText(item.getDate());
        holder.status.setText(item.getStatus());
        if("申请完成".equals(item.getStatus())){
            holder.icon.setImageResource(R.drawable.app_complete);
        }else if("待初审".equals(item.getStatus())){
            holder.icon.setImageResource(R.drawable.app_examination);
        }else if("待收件".equals(item.getStatus())){
            holder.icon.setImageResource(R.drawable.app_addressee);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView regNo;
        TextView addr;
        TextView addrType;
        TextView type;
        TextView date;
        ImageView icon;
        TextView status;
        public ViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.jindu_title);
            regNo=(TextView)itemView.findViewById(R.id.jindu_serielNo);
            addr=(TextView)itemView.findViewById(R.id.jindu_addr);
            addrType=(TextView)itemView.findViewById(R.id.jindu_jingyingdizhi);
            type=(TextView)itemView.findViewById(R.id.jindu_type);
            date=(TextView)itemView.findViewById(R.id.jindu_date);
            icon=(ImageView)itemView.findViewById(R.id.jindu_icon);
            status= (TextView) itemView.findViewById(R.id.jindu_status);
        }
    }
}
