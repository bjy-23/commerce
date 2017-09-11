package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.statistics.bean.Annals;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by chan on 7/25/17.
 */

public class HeaderBottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    //模拟数据
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount = 1;//头部View个数
    private int mBottomCount = 0;//底部View个数
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private ColumnChartData data;
    private List<Annals.AnnalsResult> items = new ArrayList<>();

    public HeaderBottomAdapter(Context context, List<Annals.AnnalsResult> item) {
        mContext = context;
        if (null != item) this.items = item;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setItems(List<Annals.AnnalsResult> item) {
        if (null != item) this.items = item;
    }

    //内容长度
    public int getContentItemCount() {
        return items.size();
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
//头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
//底部View
            return ITEM_TYPE_BOTTOM;
        } else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.nb_type)
        TextView nbType;//年报类型
        @Bind(R.id.y_nb)
        TextView ynb;//应年报
        @Bind(R.id.w_nb)
        TextView wnb;//未年报
        @Bind(R.id.yi_nb)
        TextView yinb;//已年报
        @Bind(R.id.l_nb)
        TextView nbl;//年报率

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.column_chart)
        ColumnChartView chart;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.layout_query_header, parent, false));
        } else if (viewType == mHeaderCount) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_annals, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            generateDefaultData(((HeaderViewHolder) holder));
        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).nbType.setText(items.get(position - mHeaderCount).getName());
            ((ContentViewHolder) holder).ynb.setText(items.get(position - mHeaderCount).getYgnb() + "件");
            ((ContentViewHolder) holder).yinb.setText(items.get(position - mHeaderCount).getYnb() + "件");
            ((ContentViewHolder) holder).wnb.setText(items.get(position - mHeaderCount).getWnb() + "件");
            ((ContentViewHolder) holder).nbl.setText(items.get(position - mHeaderCount).getNbl() + "%");
        } else if (holder instanceof BottomViewHolder) {
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

    private void generateDefaultData(HeaderViewHolder holder) {
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < items.size(); ++i) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue((float) Integer.valueOf(items.get(i).getYnb()), Color.parseColor("#76B7FF")));
            values.add(new SubcolumnValue((float) Integer.valueOf(items.get(i).getWnb()), Color.parseColor("#E5E5E5")));
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);
        data.setFillRatio(0.5f);
        data.setStacked(true);

        if (hasAxes) {
            List<AxisValue> axisValues = new ArrayList<AxisValue>();
            for (int i = 0; i < items.size(); i++) {
                AxisValue value = new AxisValue(i);
                value.setLabel(items.get(i).getName());
                axisValues.add(value);
            }
            Axis axisX = new Axis(axisValues);
            axisX.setTextColor(Color.parseColor("#333333"));
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("");
                axisY.setName("");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        holder.chart.setColumnChartData(data);
    }
}