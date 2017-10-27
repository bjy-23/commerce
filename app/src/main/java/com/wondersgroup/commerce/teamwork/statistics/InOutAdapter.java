package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.statistics.bean.In;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by chan on 7/13/17.
 */

public class InOutAdapter extends RecyclerView.Adapter<InOutAdapter.ViewHolder> {
    private Context context;
    private OnItemClickListener listener;

    private boolean isIn = true;//列入或移出
    private List<In.InOutItem> item = new ArrayList<>();

    public void setOnItemClickListener(OnItemClickListener l) {
        this.listener = l;
    }

    public InOutAdapter(Context context, List<In.InOutItem> item) {
        this.context = context;
        if (item != null) this.item = item;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIsIn(boolean isIn) {
        this.isIn = isIn;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_inout, parent, false));
    }

    public void setItems(List<In.InOutItem> item) {
        if (item != null) this.item = item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = item.get(position).getType() + (isIn ? "_IN" : "_OUT") + "_D_COUNT";
                if (listener != null) {
                    v.setTag(key);
                    listener.onClick(v, position);
                }
            }
        });
        holder.relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = item.get(position).getType() + (isIn ? "_IN" : "_OUT") + "_COUNT";
                if (listener != null) {
                    v.setTag(key);
                    listener.onClick(v, position);
                }
            }
        });
        holder.relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "TOTAL_" + item.get(position).getType() + (isIn ? "_IN" : "_OUT") + "_D_COUNT";
                if (listener != null) {
                    v.setTag(key);
                    listener.onClick(v, position);
                }
            }
        });
        holder.relative3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "TOTAL_" + item.get(position).getType() + (isIn ? "_IN" : "_OUT") + "_COUNT";
                if (listener != null) {
                    v.setTag(key);
                    listener.onClick(v, position);
                }
            }
        });
        if (isIn()) {
            for (TextView view : holder.titleList) {
                view.setText(String.format(view.getText().toString(), "列入"));
            }
        } else {
            for (TextView view : holder.titleList) {
                view.setText(String.format(view.getText().toString(), "移出"));
            }
        }
        holder.type.setText(item.get(position).getName());
        holder.totalNum.setText(getValue(item.get(position).getdCount()));
        holder.totalNum1.setText(getValue(item.get(position).getCount()));
        holder.totalNum2.setText(getValue(item.get(position).getTotalDCount()));
        holder.totalNum3.setText(getValue(item.get(position).getTotalCount()));
    }

    private String getValue(String value) {
        return TextUtils.isEmpty(value) ? "0" : value;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.relative)
        RelativeLayout relative;
        @BindView(R.id.relative_1)
        RelativeLayout relative1;
        @BindView(R.id.relative_2)
        RelativeLayout relative2;
        @BindView(R.id.relative_3)
        RelativeLayout relative3;

        @BindViews({R.id.title, R.id.title1, R.id.title2, R.id.title3})
        List<TextView> titleList;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.totalNum)
        TextView totalNum;
        @BindView(R.id.totalNum1)
        TextView totalNum1;
        @BindView(R.id.totalNum2)
        TextView totalNum2;
        @BindView(R.id.totalNum3)
        TextView totalNum3;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }
}
