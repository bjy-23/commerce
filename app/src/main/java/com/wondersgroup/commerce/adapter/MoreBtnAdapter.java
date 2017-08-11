package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

/**
 * Created by yclli on 2016/10/18.
 */
public class MoreBtnAdapter extends RecyclerView.Adapter<MoreBtnAdapter.ViewHolder>{
    private Context mContext;
    private List<String> items;
    private List<Boolean> itemsTime;
    private int clickPos;

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    private static OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public MoreBtnAdapter(Context mContext, List<String> items, List<Boolean> itemsIsClick) {
        this.mContext = mContext;
        this.items = items;
        this.itemsTime = itemsIsClick;
    }

    @Override
    public MoreBtnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.view_recycler_btn,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MoreBtnAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).split("=")[1]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPos = holder.getLayoutPosition();
                //notifyDataSetChanged();
                if(listener!=null){
                    listener.OnItemClick(holder.itemView, clickPos);
                }
            }
        });
        if(itemsTime.size()==0){
            return;
        }
        if(position == clickPos){
            if(itemsTime.get(position)){
                holder.itemView.setBackgroundResource(R.drawable.rounded_rect_blueline);
                holder.title.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
            }else{
                holder.itemView.setBackgroundResource(R.drawable.rounded_rect_grayfill);
                holder.title.setTextColor(ContextCompat.getColor(mContext, R.color.deep_gray));
            }

        }else{
            holder.itemView.setBackgroundResource(R.drawable.rounded_rect_grayfill);
            holder.title.setTextColor(ContextCompat.getColor(mContext, R.color.deep_gray));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.recycler_btn_txt);
        }

    }
}
