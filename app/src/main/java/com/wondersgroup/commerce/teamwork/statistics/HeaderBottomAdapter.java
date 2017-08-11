package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

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
    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private ColumnChartData data;

    public HeaderBottomAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public void setStatus(ArrayList<String> status) {
        this.status = status;
    }

    //内容长度
    public int getContentItemCount() {
        return type.size();
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
        @Bind(R.id.legend1)
        TextView legend1;
        @Bind(R.id.legend2)
        TextView legend2;
        @Bind(R.id.legend3)
        TextView legend3;
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
            ((ContentViewHolder) holder).nbType.setText(type.get(position - mHeaderCount));
        } else if (holder instanceof BottomViewHolder) {
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

    private void generateDefaultData(HeaderViewHolder holder) {
        holder.legend1.setText(status.get(0));
        holder.legend2.setText(status.get(1));
        holder.legend3.setText(status.get(2));
        int numSubcolumns = 2;
        int numColumns = 8;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < type.size(); ++i) {

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#70EEB7")));
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#005184")));
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#5193FF")));
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
            for (int i = 0; i < type.size(); i++) {
                AxisValue value = new AxisValue(i);
                value.setLabel(type.get(i));
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
        holder.chart.invalidate();
    }
}