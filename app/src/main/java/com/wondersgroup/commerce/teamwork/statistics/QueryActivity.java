package com.wondersgroup.commerce.teamwork.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * 业务查询
 */
public class QueryActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.type)
    TextView mType;
    ArrayList<String> type = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    HeaderBottomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("业务查询");
        type.add("企业");
        type.add("农专");
        type.add("个体工商户");
        status.add("存续");
        status.add("吊销、未注销");
        status.add("迁出");
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new HeaderBottomAdapter(this);
        adapter.setType(type);
        adapter.setStatus(status);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.type)
    void onTypeClick() {
        String[] items = new String[]{"按企业状态查看", "按企业类型查看"};
        new MaterialDialog.Builder(this)
                .items(items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (which == 0) {
                            adapter.setType(type);
                            adapter.setStatus(status);
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.setType(status);
                            adapter.setStatus(type);
                            adapter.notifyDataSetChanged();
                        }
                        mType.setText(text.toString());
                    }
                })
                .show();
    }

//    Viewport v = new Viewport(chart.getMaximumViewport());
//    v.left = -0.5f;
//    v.right = 3.5f;
//chart.setCurrentViewport(v);
//chart.setZoomEnabled(false);


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
