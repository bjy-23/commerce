package com.wondersgroup.commerce.teamwork.statistics;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.statistics.bean.Annals;
import com.wondersgroup.commerce.teamwork.statistics.bean.EtpsAndPeInfo;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 年报
 */
public class AnnalsActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.year)
    TextView year;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.iv_select)
    ImageView mIVSelect;
    private HashMap<String, String> params = new HashMap<>();
    private boolean isZCBJ = true;//1表示获取全局数据，0表示只查本级。
    private TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
    private HeaderBottomAdapter adapter;

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
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(new HeaderBottomAdapter(this, null));
        year.setText(getYear() + "");
        params.put("organId", loginBean.getResult().getOrganId());
//        params.put("organId", "510000000");
        params.put("annlYear", year.getText().toString());
        params.put("allFlag", isZCBJ ? "0" : "1");//0表示获取全局数据，1表示只查本级。
        initData();
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
        startActivityForResult(new Intent(this, DeptActivity.class), 0);
    }


    @OnClick(R.id.year)
    void onYearClick() {
        YearPickerDialog dialog = new YearPickerDialog.Builder(this).setNegativeButton("取消", null)
                .setPositiveButton("确定", new YearPickerDialog.Builder.OnYearPickListener() {
                    @Override
                    public void onYearPick(DialogInterface dialog, int witch, int... args) {
                        year.setText(args[0] + "");
                        params.put("annlYear", year.getText().toString());
                        initData();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    private int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
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

    private List<Annals.AnnalsResult> generateData(Annals.AnnalsItem item) {
        List<Annals.AnnalsResult> data = new ArrayList<>();
        Annals.AnnalsResult result = new Annals.AnnalsResult();
        result.setName("内资");
        result.setYnb(defaultZero(item.getNZ_YES_ANNL_COUNT()));
        result.setWnb(defaultZero(item.getNZ_NOT_ANNL_COUNT()));
        result.setYgnb(defaultZero(item.getNZ_NEED_ANNL_COUNT()));
        result.setNbl(defaultZero(item.getNZ_ANNL_PERCENT()));
        data.add(result);
        result = new Annals.AnnalsResult();
        result.setName("外资");
        result.setYnb(defaultZero(item.getWZ_YES_ANNL_COUNT()));
        result.setWnb(defaultZero(item.getWZ_NOT_ANNL_COUNT()));
        result.setYgnb(defaultZero(item.getWZ_NEED_ANNL_COUNT()));
        result.setNbl(defaultZero(item.getWZ_ANNL_PERCENT()));
        data.add(result);
        result = new Annals.AnnalsResult();
        result.setName("农专");
        result.setYnb(defaultZero(item.getNOZ_YES_ANNL_COUNT()));
        result.setWnb(defaultZero(item.getNOZ_NOT_ANNL_COUNT()));
        result.setYgnb(defaultZero(item.getNOZ_NEED_ANNL_COUNT()));
        result.setNbl(defaultZero(item.getNOZ_ANNL_PERCENT()));
        data.add(result);
        result = new Annals.AnnalsResult();
        result.setName("个体");
        result.setYnb(defaultZero(item.getGT_YES_ANNL_COUNT()));
        result.setWnb(defaultZero(item.getGT_NOT_ANNL_COUNT()));
        result.setYgnb(defaultZero(item.getGT_NEED_ANNL_COUNT()));
        result.setNbl(defaultZero(item.getGT_ANNL_PERCENT()));
        data.add(result);
        return data;
    }

    private String defaultZero(String value) {
        return TextUtils.isEmpty(value) ? "0" : value;
    }

    /**
     * 空数据
     */
    private List<Annals.AnnalsResult> generateEmptyData() {
        List<Annals.AnnalsResult> data = new ArrayList<>();
        Annals.AnnalsResult result = new Annals.AnnalsResult();
        result.setName("内资");
        result.setYnb("0");
        result.setWnb("0");
        result.setYgnb("0");
        result.setNbl("0");
        data.add(result);
        result = new Annals.AnnalsResult();
        result.setName("外资");
        result.setYnb("0");
        result.setWnb("0");
        result.setYgnb("0");
        result.setNbl("0");
        data.add(result);
        result = new Annals.AnnalsResult();
        result.setName("农专");
        result.setYnb("0");
        result.setWnb("0");
        result.setYgnb("0");
        result.setNbl("0");
        data.add(result);
        result = new Annals.AnnalsResult();
        result.setName("个体");
        result.setYnb("0");
        result.setWnb("0");
        result.setYgnb("0");
        result.setNbl("0");
        data.add(result);
        return data;
    }

    private void initData() {
        MyProgressDialog.show(this);
        ApiManager.tjApi.getAnnlStatInfo(params).enqueue(new Callback<Annals>() {
            @Override
            public void onResponse(Response<Annals> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                Annals result = response.body();
                if (result != null && result.getResult() != null) {
                    if (result.getCode() == 200) {
                        if (adapter == null) {
                            adapter = new HeaderBottomAdapter(AnnalsActivity.this, generateData(result.getResult()));
                            recyclerView.setAdapter(adapter);
                        } else {
                            adapter.setItems(generateData(result.getResult()));
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        showMsg(getResources().getString(R.string.error_connect));
                        if (adapter == null) {
                            adapter = new HeaderBottomAdapter(AnnalsActivity.this, generateEmptyData());
                            recyclerView.setAdapter(adapter);
                        } else {
                            adapter.setItems(generateEmptyData());
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    showMsg(getResources().getString(R.string.error_connect));
                    if (adapter == null) {
                        adapter = new HeaderBottomAdapter(AnnalsActivity.this, generateEmptyData());
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.setItems(generateEmptyData());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                showMsg(getResources().getString(R.string.error_connect));
                if (adapter == null) {
                    adapter = new HeaderBottomAdapter(AnnalsActivity.this, generateEmptyData());
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.setItems(generateEmptyData());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
