package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.AdvertisementBean;
import com.wondersgroup.commerce.model.ad.AdQuery;

import java.util.List;

/**
 * Created by bjy on 2017/9/18.
 */

public class AdvertisementAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<AdQuery.AdOp> mData;
    private ItemClickListener itemClickListener;

    public AdvertisementAdapter(Context mContext, List<AdQuery.AdOp> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_advertisement, parent, false);
        return new AdvertisementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AdvertisementViewHolder viewHolder = (AdvertisementViewHolder) holder;
        AdQuery.AdOp bean = mData.get(position);
        viewHolder.tvId.setText(getNullString(bean.getBulicNo()));
        viewHolder.tvPerson.setText("单位名称：" + getNullString(bean.getBuentName()));
        viewHolder.tvAddress.setText("单位地址：" + getNullString(bean.getOpLoc()));
        viewHolder.tvName.setText("联系人：" + getNullString(bean.getLinkMan()));
        viewHolder.tvTel.setText("联系电话：" + getNullString(bean.getTel()));
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(position);
                }
            }
        });
    }

    private String getNullString(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class AdvertisementViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView tvId;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPerson;
        private TextView tvTel;

        public AdvertisementViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            tvPerson = (TextView) itemView.findViewById(R.id.tv_person);
            tvTel = (TextView) itemView.findViewById(R.id.tv_tel);
        }
    }

    public interface ItemClickListener {
        void onClick(int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
