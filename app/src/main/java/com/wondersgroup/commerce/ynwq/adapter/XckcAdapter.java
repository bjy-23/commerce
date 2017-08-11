package com.wondersgroup.commerce.ynwq.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.activity.PicShowActivity;
import com.wondersgroup.commerce.ynwq.bean.PicBean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bjy on 2017/4/17.
 */

public class XckcAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<PicBean> mData;
    private OnItemClickListener onItemClickListener;

    public XckcAdapter(Context mContext, List<PicBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_xckc,parent,false);
        return new XckcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        XckcViewHolder viewHolder = (XckcViewHolder) holder;
        if (mData.get(position) != null){
            PicBean picBean = mData.get(position);
            if (picBean.getType() == 0){
//                Picasso.with(mContext)
//                        .load(picBean.getPicPath())
//                        .resize(120,120)
//                        .centerCrop()
//                        .into(viewHolder.imageView, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                ((XckcViewHolder) holder).tvRemind.setVisibility(View.GONE);
//                            }
//
//                            @Override
//                            public void onError() {
//                                ((XckcViewHolder) holder).tvRemind.setText("加载失败！");
//                            }
//                        });
            }else {
                Uri uri = Uri.fromFile(new File(picBean.getPicPath()));
                Picasso.with(mContext)
                        .load(uri)
                        .resize(120,120)
                        .centerCrop()
                        .into(viewHolder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                ((XckcViewHolder) holder).tvRemind.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError() {
                                ((XckcViewHolder) holder).tvRemind.setText("加载失败！");
                            }
                        });
            }
        }else {
            viewHolder.imageView.setImageResource(R.mipmap.add);
            viewHolder.tvRemind.setVisibility(View.GONE);
        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }


    class XckcViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvRemind;

        public XckcViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            tvRemind = (TextView) itemView.findViewById(R.id.tv_remind);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
