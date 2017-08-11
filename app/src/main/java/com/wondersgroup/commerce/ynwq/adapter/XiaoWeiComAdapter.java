package com.wondersgroup.commerce.ynwq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.bean.XwComItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/28.
 */
public class XiaoWeiComAdapter extends RecyclerView.Adapter<XiaoWeiComAdapter.ViewHolder>{
    private List<XwComItem> items;

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    private static OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public XiaoWeiComAdapter(List<XwComItem> items) {
        this.items = items;
    }

    @Override
    public XiaoWeiComAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_xwcom_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(XiaoWeiComAdapter.ViewHolder holder, int position) {
        XwComItem item=items.get(position);
        holder.title.setText(item.getTitle());
        holder.regNo.setText(item.getRegNo());
        holder.capital.setText(item.getCapital());
        holder.capitalType.setText(item.getCapitalType()+"：  ");
        holder.entType.setText(item.getType());
        holder.office.setText(item.getOffice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView regNo;
        TextView capital;
        TextView capitalType;
        TextView entType;
        TextView office;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.xwcom_title);
            regNo=(TextView)itemView.findViewById(R.id.xwcom_regNo);
            capital=(TextView)itemView.findViewById(R.id.xwcom_capital);
            capitalType=(TextView)itemView.findViewById(R.id.xwcom_capitalType);
            entType=(TextView)itemView.findViewById(R.id.xwcom_entType);
            office=(TextView)itemView.findViewById(R.id.xwcom_office);
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
