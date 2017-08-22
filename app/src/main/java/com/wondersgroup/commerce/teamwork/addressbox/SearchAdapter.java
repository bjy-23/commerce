package com.wondersgroup.commerce.teamwork.addressbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.Address;

import java.util.List;

/**
 * Created by bjy on 2017/4/5.
 * Updated by yclli on 2017/8/15
 */

public class SearchAdapter extends RecyclerView.Adapter {
    private List<Address.AddlistPersonalInfo> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnClickListener listener;

    public SearchAdapter(List<Address.AddlistPersonalInfo> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_address_list,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder viewHolder = (SearchViewHolder) holder;
        viewHolder.nameTv.setText(data.get(position).getName());
        viewHolder.deptTv.setText(data.get(position).getDept());
        viewHolder.phoneTv.setText(data.get(position).getTel());
        viewHolder.letterTv.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTv;
        private TextView phoneTv;
        private TextView deptTv;
        private TextView letterTv;

        public SearchViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            phoneTv = (TextView) itemView.findViewById(R.id.tv_phone);
            deptTv = (TextView) itemView.findViewById(R.id.tv_dept);
            letterTv = (TextView) itemView.findViewById(R.id.tv_letter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }

    public interface OnClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.listener = onClickListener;
    }
}
