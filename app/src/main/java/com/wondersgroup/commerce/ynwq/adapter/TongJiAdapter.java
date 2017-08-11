package com.wondersgroup.commerce.ynwq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.bean.StatItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/2/1.
 */
public class TongJiAdapter extends RecyclerView.Adapter<TongJiAdapter.ViewHolder>{
    private List<StatItem> items;

    public void setItems(List<StatItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tj_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StatItem item=items.get(position);
        holder.title.setText(item.getEtpsName());
        holder.regNo.setText(item.getRegNo());
        holder.name.setText(item.getLeaderName());
        holder.addr.setText(item.getAddress());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView regNo;
        TextView addr;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tongji_title);
            regNo=(TextView)itemView.findViewById(R.id.tongji_regNo);
            addr=(TextView)itemView.findViewById(R.id.tongji_date);
            name=(TextView)itemView.findViewById(R.id.tongji_office);
        }
    }
}
