package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 8/24/17.
 */

public class ReasonAdapter extends RecyclerView.Adapter<ReasonAdapter.ViewHolder> {
    private Context context;
    private List<Map.Entry<Integer, String>> items = new ArrayList<>();
    private Map<Integer, String> reasonMap = new HashMap<>();

    public ReasonAdapter(Context context, List<Map.Entry<Integer, String>> items) {
        this.context = context;
        if (null != items) this.items = items;
    }

    public void setReasonMap(Map<Integer, String> reasonMap) {
        if (reasonMap != null) this.reasonMap = reasonMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReasonAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_reason, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position * 2 < items.size()) {
            holder.mLinearLeft.setVisibility(View.VISIBLE);
            holder.mReasonLeft.setText(reasonMap.get(items.get(position * 2).getKey()));
            holder.mValueLeft.setText(items.get(position * 2).getValue());
        }

        if (position * 2 + 1 < items.size()) {
            holder.mLinearRight.setVisibility(View.VISIBLE);
            holder.mReasonRight.setText(reasonMap.get(items.get(position * 2 + 1).getKey()));
            holder.mValueRight.setText(items.get(position * 2 + 1).getValue());
        }
    }

    @Override
    public int getItemCount() {
        return (int) Math.ceil(items.size() / 2.0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.linear_left)
        LinearLayout mLinearLeft;
        @BindView(R.id.linear_right)
        LinearLayout mLinearRight;
        @BindView(R.id.reason_left)
        TextView mReasonLeft;
        @BindView(R.id.reason_right)
        TextView mReasonRight;
        @BindView(R.id.value_left)
        TextView mValueLeft;
        @BindView(R.id.value_right)
        TextView mValueRight;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
