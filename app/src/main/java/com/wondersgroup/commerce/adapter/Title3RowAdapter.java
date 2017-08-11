package com.wondersgroup.commerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.Title4RowItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/28.
 */
public class Title3RowAdapter extends RecyclerView.Adapter<Title3RowAdapter.ViewHolder>{
    private List<Title4RowItem> items;

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    private static OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public Title3RowAdapter(List<Title4RowItem> items) {
        this.items = items;
    }

    @Override
    public Title3RowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_title3row_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Title3RowAdapter.ViewHolder holder, int position) {
        Title4RowItem item=items.get(position);
        holder.title.setText(item.getTitle());
        holder.rowOneTitle.setText(item.getRowOneTitle());
        holder.rowTwoTitle.setText(item.getRowTwoTitle());
        holder.rowThrTitle.setText(item.getRowThrTitle());
        holder.rowOneContent.setText(item.getRowOneContent());
        holder.rowTwoContent.setText(item.getRowTwoContent());
        holder.rowThrContent.setText(item.getRowThrContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView rowOneTitle;
        TextView rowTwoTitle;
        TextView rowThrTitle;
        TextView rowOneContent;
        TextView rowTwoContent;
        TextView rowThrContent;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.item_title);
            rowOneTitle = (TextView) itemView.findViewById(R.id.item_row_one_title);
            rowTwoTitle = (TextView) itemView.findViewById(R.id.item_row_two_title);
            rowThrTitle = (TextView) itemView.findViewById(R.id.item_row_thr_title);
            rowOneContent = (TextView) itemView.findViewById(R.id.item_row_one_content);
            rowTwoContent = (TextView) itemView.findViewById(R.id.item_row_two_content);
            rowThrContent = (TextView) itemView.findViewById(R.id.item_row_thr_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }
}
