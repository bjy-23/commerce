package com.wondersgroup.yngs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.yngs.R;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/27.
 */
public class DeptSelectAdapter extends RecyclerView.Adapter<DeptSelectAdapter.ViewHolder> {
    private List<String> items;
    private  int position=-1;
    private static OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dept_select_row,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(items.get(position));
        holder.text.setSelected(this.position==position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text=(TextView)itemView.findViewById(R.id.list_item);
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
