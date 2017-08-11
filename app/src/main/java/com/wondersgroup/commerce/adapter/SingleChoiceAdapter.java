package com.wondersgroup.commerce.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.SingleChoiceActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.AreaBean;
import com.wondersgroup.commerce.model.KeyValue;

import java.util.ArrayList;

/**
 * Created by bjy on 2017/7/14.
 */

public class SingleChoiceAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private ArrayList<AreaBean> data;
    private LayoutInflater inflater;
    private OnClickListener onClickListener;

    public SingleChoiceAdapter(Activity activity, ArrayList<AreaBean> data) {
        this.activity = activity;
        this.data = data;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_single_choice,parent,false);
        return new SingleChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SingleChoiceViewHolder viewHolder = (SingleChoiceViewHolder) holder;
        viewHolder.tvContent.setText(data.get(position).getName());
        if (data.get(position).isHasChild()){
            viewHolder.imgIndex.setVisibility(View.VISIBLE);
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener!=null){
                        onClickListener.onNext(data.get(position).getId(),data.get(position).getName());
                    }
                }
            });
        }else {
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if (onClickListener!=null){
                    KeyValue keyValue = new KeyValue();
                    keyValue.setKey(data.get(position).getId());
                    keyValue.setValue(data.get(position).getName());
                    onClickListener.onBack(keyValue);
                }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class SingleChoiceViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;
        private ImageView imgIndex;
        private View view;
        public SingleChoiceViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            imgIndex = (ImageView) itemView.findViewById(R.id.img_index);

        }
    }

    public interface OnClickListener{
        void onNext(String id,String title);

        void onBack(KeyValue keyValue);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
