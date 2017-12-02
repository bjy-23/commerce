
package com.wondersgroup.yngs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.BurNingItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/9.
 */
public class BurNingRecyclerAdapter extends RecyclerView.Adapter<BurNingRecyclerAdapter.ViewHolder> {
    private List<BurNingItem> items;
    public BurNingRecyclerAdapter(List<BurNingItem> items) {
        this.items=items;
    }

    private static OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BurNingRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_burning_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BurNingRecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.num.setText(items.get(position).getNum());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView num;
        public ViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.buring_item_title);
            num=(TextView)itemView.findViewById(R.id.buring_item_num);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onItemClick(itemView,getLayoutPosition());
            }
        }
    }
}
