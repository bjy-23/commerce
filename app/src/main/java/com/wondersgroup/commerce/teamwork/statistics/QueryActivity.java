package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.statistics.bean.EtpsAndPeInfo;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 业务查询市场主体户数统计
 */
public class QueryActivity extends AppCompatActivity {
    public final String unit = "%s件";
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.divider)
    View divider;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private ColumnChartData data;
    @BindViews({R.id.legend1, R.id.legend2, R.id.legend3})
    List<TextView> legendList;
    @BindView(R.id.column_chart)
    ColumnChartView chart;

    @BindViews({R.id.ent, R.id.nz, R.id.pe})
    List<TextView> typeViewList;
    @BindViews({R.id.total_live, R.id.qy_live, R.id.nz_live, R.id.pe_live})
    List<TextView> liveViewList;
    @BindViews({R.id.total_repeal, R.id.qy_repeal, R.id.nz_repeal, R.id.pe_repeal})
    List<TextView> repealViewList;
    @BindViews({R.id.total_pe, R.id.qy_move, R.id.nz_move, R.id.pe_move})
    List<TextView> moveViewList;
    @BindViews({R.id.label_qy_live, R.id.label_nz_live, R.id.label_pe_live})
    List<TextView> liveLabelList;
    @BindViews({R.id.label_qy_repeal, R.id.label_nz_repeal, R.id.label_pe_repeal})
    List<TextView> repealLabelList;
    @BindViews({R.id.label_qy_move, R.id.label_nz_move, R.id.label_pe_move})
    List<TextView> moveLabelList;
    List<String> statusList = new ArrayList<>();
    List<String> typeList = new ArrayList<>();
    private HashMap<String, String> params = new HashMap<>();
    private boolean isZCBJ = true;//1表示获取全局数据，0表示只查本级。
    private int type;
    private TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
    @BindView(R.id.startDate)
    TextView mStartDate;
    @BindView(R.id.endDate)
    TextView mEndDate;
    @BindView(R.id.linear_date)
    LinearLayout mLinearDate;
    @BindView(R.id.iv_select)
    ImageView mIVSelect;
    private AnnalsDatePopup popup;
    private EtpsAndPeInfo.EtpsAndPeInfoResult etpsAndPeInfoResult;
    private List<EtpsAndPeInfo.EtpsAndPeInfoItem> chartData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("市场主体户数统计");
        location.setText(loginBean.getResult().getOrganName());
        mStartDate.setText(getFirstDay());
        mEndDate.setText(getToday());
        initView();
    }

    private void initView() {
        statusList.add("存续");
        statusList.add("吊销、未注销");
        statusList.add("迁出");
        typeList.add("企业");
        typeList.add("农专");
        typeList.add("个体工商户");
        params.put("organId", loginBean.getResult().getOrganId());
//        params.put("organId", "510000000");
//        params.put("startDate", AnnalsDatePopup.StringToDate(getFirstDay(), "yyyy/MM/dd", "yyyy-MM-dd"));
//        params.put("endDate", AnnalsDatePopup.StringToDate(getToday(), "yyyy/MM/dd", "yyyy-MM-dd"));
        params.put("allFlag", isZCBJ ? "0" : "1");//0表示获取全局数据，1表示只查本级。
        initData();
    }

    @OnClick(R.id.type)
    void onTypeClick() {
        String[] items = new String[]{"按企业状态查看", "按企业类型查看"};
        new MaterialDialog.Builder(this)
                .items(items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        type = which;
                        mType.setText(text.toString());
                        generateData();
                    }
                })
                .show();
    }

    @OnClick(R.id.only_local)
    void onZCBJClick(View view) {
        if (isZCBJ) {
            mIVSelect.setImageResource(R.mipmap.dx_unselected);
            isZCBJ = false;
        } else {
            mIVSelect.setImageResource(R.mipmap.dx_selected);
            isZCBJ = true;
        }
        params.put("allFlag", isZCBJ ? "0" : "1");
        initData();
    }

    @OnClick(R.id.location)
    void OnLocationClick(View view) {
        Intent intent = new Intent(this, DeptActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, "登记机关");
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.linear_date)
    void onDateClick(View view) {
        String dateS = mStartDate.getText().toString();
        String dateE = mEndDate.getText().toString();
        if (popup == null)
            popup = new AnnalsDatePopup(this)
                    .setEndDate(dateE)
                    .setStartDate(dateS);
        popup.setDismissListener(new AnnalsDatePopup.OnDismissListener() {
            @Override
            public void onDismiss(String startDate, String endDate) {
                if (TextUtils.isEmpty(startDate)) {
                    mStartDate.setText("请选择");
                } else {
                    mStartDate.setText(startDate);
                    startDate = AnnalsDatePopup.StringToDate(startDate, "yyyy/MM/dd", "yyyy-MM-dd");
                }
                if (TextUtils.isEmpty(endDate)) {
                    mEndDate.setText("请选择");
                } else {
                    mEndDate.setText(endDate);
                    endDate = AnnalsDatePopup.StringToDate(endDate, "yyyy/MM/dd", "yyyy-MM-dd");
                }
//                params.put("startDate", startDate);
//                params.put("endDate", endDate);
                initData();
            }
        });
        popup.show(divider);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initData() {
        MyProgressDialog.show(this);
        ApiManager.tjApi.getEtpsAndPeInfo(params).enqueue(new Callback<EtpsAndPeInfo>() {
            @Override
            public void onResponse(Response<EtpsAndPeInfo> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                EtpsAndPeInfo result = response.body();
                if (result != null && result.getResult() != null) {
                    if (result.getCode() == 200) {
                        etpsAndPeInfoResult = result.getResult();
                    } else {
                        showMsg(getResources().getString(R.string.error_connect));
                        etpsAndPeInfoResult = new EtpsAndPeInfo().new EtpsAndPeInfoResult();
                    }
                } else {
                    etpsAndPeInfoResult = new EtpsAndPeInfo().new EtpsAndPeInfoResult();
                    showMsg(getResources().getString(R.string.error_connect));
                }
                generateData();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                etpsAndPeInfoResult = new EtpsAndPeInfo().new EtpsAndPeInfoResult();
                generateData();
                showMsg(getResources().getString(R.string.error_connect));
            }
        });
    }

    private void generateData() {
        if (type == 0)
            entStatus();
        else entType();
        generateDefaultData();
    }

    /**
     * 按企业类型查看
     */
    private void entType() {
        for (int i = 0; i < statusList.size(); i++) {
            legendList.get(i).setText(statusList.get(i));
        }
        for (int i = 0; i < typeList.size(); i++) {
            typeViewList.get(i).setText(typeList.get(i));
        }
        for (int i = 0; i < statusList.size(); i++) {
            liveLabelList.get(i).setText(statusList.get(i));
            repealLabelList.get(i).setText(statusList.get(i));
            moveLabelList.get(i).setText(statusList.get(i));
        }
        int liveTotal = 0;
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getQY_LIVE_COUNT()))
            liveTotal += Integer.valueOf(etpsAndPeInfoResult.getQY_LIVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getNZ_LIVE_COUNT()))
            liveTotal += Integer.valueOf(etpsAndPeInfoResult.getNZ_LIVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getPE_LIVE_COUNT()))
            liveTotal += Integer.valueOf(etpsAndPeInfoResult.getPE_LIVE_COUNT());
        liveViewList.get(0).setText(format(liveTotal + ""));
        liveViewList.get(1).setText(format(etpsAndPeInfoResult.getQY_LIVE_COUNT()));
        liveViewList.get(2).setText(format(etpsAndPeInfoResult.getNZ_LIVE_COUNT()));
        liveViewList.get(3).setText(format(etpsAndPeInfoResult.getPE_LIVE_COUNT()));
        int repealTotal = 0;
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getQY_REPEAL_COUNT()))
            repealTotal += Integer.valueOf(etpsAndPeInfoResult.getQY_REPEAL_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getNZ_REPEAL_COUNT()))
            repealTotal += Integer.valueOf(etpsAndPeInfoResult.getNZ_REPEAL_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getPE_REPEAL_COUNT()))
            repealTotal += Integer.valueOf(etpsAndPeInfoResult.getPE_REPEAL_COUNT());
        repealViewList.get(0).setText(format(repealTotal + ""));
        repealViewList.get(1).setText(format(etpsAndPeInfoResult.getQY_REPEAL_COUNT()));
        repealViewList.get(2).setText(format(etpsAndPeInfoResult.getNZ_REPEAL_COUNT()));
        repealViewList.get(3).setText(format(etpsAndPeInfoResult.getPE_REPEAL_COUNT()));
        int moveTotal = 0;
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getQY_MOVE_COUNT()))
            moveTotal += Integer.valueOf(etpsAndPeInfoResult.getQY_MOVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getNZ_MOVE_COUNT()))
            moveTotal += Integer.valueOf(etpsAndPeInfoResult.getNZ_MOVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getPE_MOVE_COUNT()))
            moveTotal += Integer.valueOf(etpsAndPeInfoResult.getPE_MOVE_COUNT());
        moveViewList.get(0).setText(format(moveTotal + ""));
        moveViewList.get(1).setText(format(etpsAndPeInfoResult.getQY_MOVE_COUNT()));
        moveViewList.get(2).setText(format(etpsAndPeInfoResult.getNZ_MOVE_COUNT()));
        moveViewList.get(3).setText(format(etpsAndPeInfoResult.getPE_MOVE_COUNT()));

        //拼装图表数据
        chartData.clear();
        EtpsAndPeInfo.EtpsAndPeInfoItem item = new EtpsAndPeInfo.EtpsAndPeInfoItem();
        item.setFst(getValue(etpsAndPeInfoResult.getQY_LIVE_COUNT()));
        item.setSnd(getValue(etpsAndPeInfoResult.getNZ_LIVE_COUNT()));
        item.setTrd(getValue(etpsAndPeInfoResult.getPE_LIVE_COUNT()));
        chartData.add(item);
        item = new EtpsAndPeInfo.EtpsAndPeInfoItem();
        item.setFst(getValue(etpsAndPeInfoResult.getQY_REPEAL_COUNT()));
        item.setSnd(getValue(etpsAndPeInfoResult.getNZ_REPEAL_COUNT()));
        item.setTrd(getValue(etpsAndPeInfoResult.getPE_REPEAL_COUNT()));
        chartData.add(item);
        item = new EtpsAndPeInfo.EtpsAndPeInfoItem();
        item.setFst(getValue(etpsAndPeInfoResult.getQY_MOVE_COUNT()));
        item.setSnd(getValue(etpsAndPeInfoResult.getNZ_MOVE_COUNT()));
        item.setTrd(getValue(etpsAndPeInfoResult.getPE_MOVE_COUNT()));
        chartData.add(item);
    }

    /**
     * 按企业状态查看
     */
    private void entStatus() {
        for (int i = 0; i < typeList.size(); i++) {
            legendList.get(i).setText(typeList.get(i));
        }
        for (int i = 0; i < statusList.size(); i++) {
            typeViewList.get(i).setText(statusList.get(i));
        }
        for (int i = 0; i < typeList.size(); i++) {
            liveLabelList.get(i).setText(typeList.get(i));
            repealLabelList.get(i).setText(typeList.get(i));
            moveLabelList.get(i).setText(typeList.get(i));
        }
        int liveTotal = 0;
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getQY_LIVE_COUNT()))
            liveTotal += Integer.valueOf(etpsAndPeInfoResult.getQY_LIVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getQY_REPEAL_COUNT()))
            liveTotal += Integer.valueOf(etpsAndPeInfoResult.getQY_REPEAL_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getQY_MOVE_COUNT()))
            liveTotal += Integer.valueOf(etpsAndPeInfoResult.getQY_MOVE_COUNT());
        liveViewList.get(0).setText(format(liveTotal + ""));
        liveViewList.get(1).setText(format(etpsAndPeInfoResult.getQY_LIVE_COUNT()));
        liveViewList.get(2).setText(format(etpsAndPeInfoResult.getQY_REPEAL_COUNT()));
        liveViewList.get(3).setText(format(etpsAndPeInfoResult.getQY_MOVE_COUNT()));
        int repealTotal = 0;
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getNZ_LIVE_COUNT()))
            repealTotal += Integer.valueOf(etpsAndPeInfoResult.getNZ_LIVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getNZ_REPEAL_COUNT()))
            repealTotal += Integer.valueOf(etpsAndPeInfoResult.getNZ_REPEAL_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getNZ_MOVE_COUNT()))
            repealTotal += Integer.valueOf(etpsAndPeInfoResult.getNZ_MOVE_COUNT());
        repealViewList.get(0).setText(format(repealTotal + ""));
        repealViewList.get(1).setText(format(etpsAndPeInfoResult.getNZ_LIVE_COUNT()));
        repealViewList.get(2).setText(format(etpsAndPeInfoResult.getNZ_REPEAL_COUNT()));
        repealViewList.get(3).setText(format(etpsAndPeInfoResult.getNZ_MOVE_COUNT()));
        int moveTotal = 0;
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getPE_LIVE_COUNT()))
            moveTotal += Integer.valueOf(etpsAndPeInfoResult.getPE_LIVE_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getPE_REPEAL_COUNT()))
            moveTotal += Integer.valueOf(etpsAndPeInfoResult.getPE_REPEAL_COUNT());
        if (!TextUtils.isEmpty(etpsAndPeInfoResult.getPE_MOVE_COUNT()))
            moveTotal += Integer.valueOf(etpsAndPeInfoResult.getPE_MOVE_COUNT());
        moveViewList.get(0).setText(format(moveTotal + ""));
        moveViewList.get(1).setText(format(etpsAndPeInfoResult.getPE_LIVE_COUNT()));
        moveViewList.get(2).setText(format(etpsAndPeInfoResult.getPE_REPEAL_COUNT()));
        moveViewList.get(3).setText(format(etpsAndPeInfoResult.getPE_MOVE_COUNT()));

        chartData.clear();
        EtpsAndPeInfo.EtpsAndPeInfoItem item = new EtpsAndPeInfo.EtpsAndPeInfoItem();
        item.setFst(getValue(etpsAndPeInfoResult.getQY_LIVE_COUNT()));
        item.setSnd(getValue(etpsAndPeInfoResult.getQY_REPEAL_COUNT()));
        item.setTrd(getValue(etpsAndPeInfoResult.getQY_MOVE_COUNT()));
        chartData.add(item);
        item = new EtpsAndPeInfo.EtpsAndPeInfoItem();
        item.setFst(getValue(etpsAndPeInfoResult.getNZ_LIVE_COUNT()));
        item.setSnd(getValue(etpsAndPeInfoResult.getNZ_REPEAL_COUNT()));
        item.setTrd(getValue(etpsAndPeInfoResult.getNZ_MOVE_COUNT()));
        chartData.add(item);
        item = new EtpsAndPeInfo.EtpsAndPeInfoItem();
        item.setFst(getValue(etpsAndPeInfoResult.getPE_LIVE_COUNT()));
        item.setSnd(getValue(etpsAndPeInfoResult.getPE_REPEAL_COUNT()));
        item.setTrd(getValue(etpsAndPeInfoResult.getPE_MOVE_COUNT()));
        chartData.add(item);
    }

    private void generateDefaultData() {
        List<String> typesList = (type == 0 ? statusList : typeList);
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < chartData.size(); ++i) {

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue((float) Integer.valueOf(chartData.get(i).getFst()), Color.parseColor("#5193FF")));
            values.add(new SubcolumnValue((float) Integer.valueOf(chartData.get(i).getSnd()), Color.parseColor("#005184")));
            values.add(new SubcolumnValue((float) Integer.valueOf(chartData.get(i).getTrd()), Color.parseColor("#70EEB7")));
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
            for (int i = 0; i < typesList.size(); i++) {
                AxisValue value = new AxisValue(i);
                value.setLabel(typesList.get(i));
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
        chart.invalidate();
    }

    private String format(String value) {
        return !TextUtils.isEmpty(value) ? String.format(unit, value) : String.format(unit, "0");
    }

    private String getValue(String value) {
        return !TextUtils.isEmpty(value) ? value : "0";
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回当前年份的第一天
     *
     * @return
     */
    public static String getFirstDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        String firstDay = calendar.get(Calendar.YEAR) + "/01/01";
        return firstDay;
    }

    private String getToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                String organId = b.getString("organId");//str即为回传的值
                String organName = b.getString("organName");
                location.setText(organName);
                params.put("organId", organId);
                initData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popup != null) {
            popup.dismiss();
        }
    }

}
