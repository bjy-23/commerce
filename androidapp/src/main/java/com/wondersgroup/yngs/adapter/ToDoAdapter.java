package com.wondersgroup.yngs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.ToDoItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/15.
 */
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{
    private List<ToDoItem> items;

    public ToDoAdapter(List<ToDoItem> items) {
        this.items = items;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private static OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_yushen_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.serialNo.setText(items.get(position).getRegNo());
        holder.addr.setText(items.get(position).getAddr());
        holder.date.setText(items.get(position).getDate());
        holder.type.setText(items.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView serialNo;
        TextView addr;
        TextView type;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.yushen_title);
            serialNo=(TextView)itemView.findViewById(R.id.yushen_serielNo);
            addr=(TextView)itemView.findViewById(R.id.yushen_addr);
            type=(TextView)itemView.findViewById(R.id.yushen_type);
            date=(TextView)itemView.findViewById(R.id.yushen_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onItemClick(v,getLayoutPosition());
            }
        }
    }
}
