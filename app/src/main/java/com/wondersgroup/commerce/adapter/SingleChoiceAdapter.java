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
import com.wondersgroup.commerce.model.TreeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2017/7/14.
 */

public class SingleChoiceAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private ArrayList<TreeBean> data;
    private List<TreeBean> treeData;
    private LayoutInflater inflater;
    private OnClickListener onClickListener;
    private OnClick onClick;
    private int type;

    public SingleChoiceAdapter(Activity activity, ArrayList<TreeBean> data) {
        this.activity = activity;
        this.data = data;
        inflater = LayoutInflater.from(activity);
    }

    public SingleChoiceAdapter(Activity activity, List<TreeBean> treeData, int type) {
        this.activity = activity;
        this.treeData = treeData;
        this.type = type;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_single_choice, parent, false);
        return new SingleChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (type == 1){
            SingleChoiceViewHolder viewHolder = (SingleChoiceViewHolder) holder;
            TreeBean bean = treeData.get(position);
            viewHolder.tvContent.setText(bean.getName());
            if (bean.getChilds() != null)
                viewHolder.imgIndex.setVisibility(View.VISIBLE);
            else
                viewHolder.imgIndex.setVisibility(View.GONE);
            if (bean.isSelected())
                viewHolder.imageOk.setVisibility(View.VISIBLE);
            else
                viewHolder.imageOk.setVisibility(View.GONE);
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick != null)
                        onClick.back(position);
                }
            });

        }else {
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
    }

    @Override
    public int getItemCount() {
        if (type == 1)
            return treeData.size();
        return data.size();
    }


    class SingleChoiceViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;
        private ImageView imgIndex;
        private ImageView imageOk;
        private View view;
        public SingleChoiceViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            imgIndex = (ImageView) itemView.findViewById(R.id.img_index);
            imageOk = (ImageView) itemView.findViewById(R.id.img_ok);
        }
    }

    public interface OnClickListener{
        void onNext(String id,String title);

        void onBack(KeyValue keyValue);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClick{
        void back(int position);
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
}
