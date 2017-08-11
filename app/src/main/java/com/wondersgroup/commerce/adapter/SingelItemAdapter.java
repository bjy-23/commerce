package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.KeyValue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjy on 2017/5/11.
 */

public class SingelItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<KeyValue> mData;
    private LayoutInflater inflater;

    private OnClickListener onClickListener;

    public SingelItemAdapter(Context mContext, List mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_single_select,parent,false);
        return new SingleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SingleItemViewHolder viewHolder = (SingleItemViewHolder) holder;
        viewHolder.tvValue.setText(mData.get(position).getValue());
        viewHolder.position = position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class SingleItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvValue;
        private int position;

        public SingleItemViewHolder(View itemView) {
            super(itemView);

            tvValue = (TextView) itemView.findViewById(R.id.tv_value);
            tvValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener!=null){
                        onClickListener.clickPosition(position);
                    }
                }
            });
        }
    }

    public interface OnClickListener{
        void clickPosition(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
