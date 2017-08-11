package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.List;

/**
 * 仅有一个TextView的adapter
 * 注意：所使用的数据源是一个特殊列表，每项中间用"="链接，请勿随意使用
 * Created by yclli on 2016/11/29.
 */
public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.ViewHolder> {

    Context context;
    List<String> data;

    public SingleAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }

    private OnItemClickListener listener;
    public  interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).split("=")[1].trim());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}

