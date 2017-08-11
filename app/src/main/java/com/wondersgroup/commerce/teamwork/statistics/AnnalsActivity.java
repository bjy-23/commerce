package com.wondersgroup.commerce.teamwork.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;
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
 * 年报
 */
public class AnnalsActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.column_chart)
    ColumnChartView chart;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annals);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("年报");
        initView();
    }

    private void initView() {
        scrollView.smoothScrollTo(0, 0);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AnnalsAdapter(this));
        generateDefaultData();
    }

    private void generateDefaultData() {
        int numSubcolumns = 2;
        int numColumns = 8;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#76B7FF")));
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.parseColor("#E5E5E5")));
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);
        data.setStacked(true);

        if (hasAxes) {
            List<AxisValue> axisValues = new ArrayList<AxisValue>();
            for (int i = 0; i < numColumns; i++) {
                AxisValue value = new AxisValue(i);
                value.setLabel("内资");
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
        chart.setColumnChartData(data);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
