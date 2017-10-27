package com.wondersgroup.commerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chan on 9/20/17.
 * 广告详情-提交材料
 */

public class TJCLAdapter extends RecyclerView.Adapter<TJCLAdapter.ViewHolder> {
    private OnDownloadClickListener listener;


    public void setOnDownloadClickListener(OnDownloadClickListener l) {
        this.listener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tjcl, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (listener != null) {
            holder.mDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDownloadClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.status)
        TextView mStatus;
        @BindView(R.id.date)
        TextView mDate;
        @BindView(R.id.page)
        TextView mPage;
        @BindView(R.id.download)
        TextView mDownload;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    interface OnDownloadClickListener {
        void onDownloadClick(View view, int position);
    }
}
