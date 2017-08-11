package com.wondersgroup.commerce.ynwq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.bean.JingBanItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/22.
 */
public class JingBanAdapter extends RecyclerView.Adapter<JingBanAdapter.ViewHolder> {
    List<JingBanItem> items;

    public JingBanAdapter(List<JingBanItem> items) {
        this.items = items;
    }

    @Override
    public JingBanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_jing_ban,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(JingBanAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.yijian.setText(items.get(position).getOpin());
        holder.renyuan.setText(items.get(position).getPerson());
        holder.jiguan.setText(items.get(position).getOffice());
        holder.shijian.setText(items.get(position).getDate());
        if(items.size()==1){
            holder.upLine.setVisibility(View.INVISIBLE);
            holder.downLine.setVisibility(View.INVISIBLE);
            holder.indi.setImageResource(R.drawable.app_first);
        }else {
            if (position == 0) {
                holder.upLine.setVisibility(View.INVISIBLE);
                holder.downLine.setVisibility(View.VISIBLE);
                holder.indi.setImageResource(R.drawable.app_first);
            } else if (position == items.size() - 1) {
                holder.downLine.setVisibility(View.INVISIBLE);
                holder.upLine.setVisibility(View.VISIBLE);
                holder.indi.setImageResource(R.drawable.app_third);
            } else {
                holder.downLine.setVisibility(View.VISIBLE);
                holder.upLine.setVisibility(View.VISIBLE);
                holder.indi.setImageResource(R.drawable.app_second);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        View upLine;
        View downLine;
        ImageView indi;
        TextView yijian;
        TextView renyuan;
        TextView jiguan;
        TextView shijian;
        public ViewHolder(View itemView) {
            super(itemView);
            upLine=(View)itemView.findViewById(R.id.jb_upline);
            downLine=(View)itemView.findViewById(R.id.jb_downline);
            indi=(ImageView)itemView.findViewById(R.id.jb_indi);
            title=(TextView)itemView.findViewById(R.id.jb_title);
            yijian=(TextView)itemView.findViewById(R.id.jb_yijian);
            renyuan=(TextView)itemView.findViewById(R.id.jb_renyuan);
            jiguan=(TextView)itemView.findViewById(R.id.jb_jiguan);
            shijian=(TextView)itemView.findViewById(R.id.jb_shijian);
        }
    }
}
