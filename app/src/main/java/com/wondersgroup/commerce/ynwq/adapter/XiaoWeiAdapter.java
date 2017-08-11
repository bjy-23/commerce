package com.wondersgroup.commerce.ynwq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.bean.XiaoWeiItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/21.
 */
public class XiaoWeiAdapter extends RecyclerView.Adapter<XiaoWeiAdapter.ViewHolder> {
    private List<XiaoWeiItem> items;

    public XiaoWeiAdapter(List<XiaoWeiItem> items) {
        this.items = items;
    }

    @Override
    public XiaoWeiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_xiao_wei_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(XiaoWeiAdapter.ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.content.setText(items.get(position).getValue());
        String type=items.get(position).getType();
        if("etpsName".equals(type)){
            holder.icon.setImageResource(R.drawable.app_name);
        }else if("regNo".equals(type)){
            holder.icon.setImageResource(R.drawable.app_sign);
        }else if("enjspAmount".equals(type)||"cptlTotal".equals(type)){
            holder.icon.setImageResource(R.drawable.app_capital);
        }else if("impspDeptname".equals(type)||"regOrganId".equals(type)){
            holder.icon.setImageResource(R.drawable.app_register);
        }else if("impspDate".equals(type)||"establishDate".equals(type)){
            holder.icon.setImageResource(R.drawable.app_date);
        }else if("enjspBasisName".equals(type)){
            holder.icon.setImageResource(R.drawable.app_type);
        }else if("enjspContent".equals(type)||"industryPhy".equals(type)){
            holder.icon.setImageResource(R.drawable.app_category);
        }else {
            holder.icon.setImageResource(R.drawable.app_category);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView name;
        ExpandableTextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            icon= (ImageView) itemView.findViewById(R.id.xiaowei_icon);
            name=(TextView)itemView.findViewById(R.id.xiaowei_name);
            content=(ExpandableTextView)itemView.findViewById(R.id.xiaowei_content);
        }
    }
}
