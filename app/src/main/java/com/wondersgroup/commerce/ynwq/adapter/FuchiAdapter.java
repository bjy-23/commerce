package com.wondersgroup.commerce.ynwq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.bean.FuchiItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/8.
 */
public class FuchiAdapter extends RecyclerView.Adapter<FuchiAdapter.ViewHolder> {
    private List<FuchiItem> items;
    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    private static OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FuchiAdapter(List<FuchiItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_fuchi_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getEtpsName());
        holder.regNo.setText(items.get(position).getRegNo());
        holder.office.setText(items.get(position).getUniScid());
        holder.date.setText(items.get(position).getImpspDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView regNo;
        TextView office;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.fuchi_title);
            regNo=(TextView)itemView.findViewById(R.id.fuchi_regNo);
            office=(TextView)itemView.findViewById(R.id.fuchi_office);
            date=(TextView)itemView.findViewById(R.id.fuchi_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){
                onItemClickListener.onItemClick(v,getLayoutPosition());
            }
        }
    }
}
