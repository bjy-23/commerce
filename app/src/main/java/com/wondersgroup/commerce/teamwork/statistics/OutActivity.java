package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.statistics.bean.In;
import com.wondersgroup.commerce.teamwork.statistics.bean.Out;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 移出经营异常名录统计
 */
public class OutActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private HashMap<String, String> params = new HashMap<>();
    private boolean isZCBJ = true;//1表示获取全局数据，0表示只查本级。
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
    private InOutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("移出经营异常名录统计");
        location.setText(loginBean.getResult().getOrganName());
        mStartDate.setText(getFirstDay());
        mEndDate.setText(getToday());
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        params.put("organId", loginBean.getResult().getOrganId());
//        params.put("organId", "510000000");
        params.put("startDate", AnnalsDatePopup.StringToDate(getFirstDay(), "yyyy/MM/dd", "yyyy-MM-dd"));
        params.put("endDate", AnnalsDatePopup.StringToDate(getToday(), "yyyy/MM/dd", "yyyy-MM-dd"));
        params.put("allFlag", isZCBJ ? "0" : "1");//1本级以及本级一下、0本级。
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
                params.put("startDate", startDate);
                params.put("endDate", endDate);
                initData();
            }
        });
        popup.show(divider);
    }

    private void initData() {
        MyProgressDialog.show(this);
        ApiManager.tjApi.getExceptStatOutInfo(params).enqueue(new Callback<Out>() {
            @Override
            public void onResponse(Response<Out> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                Out result = response.body();
                if (result != null && result.getResult() != null) {
                    if (result.getCode() == 200) {
                        if (adapter == null) {
                            adapter = new InOutAdapter(OutActivity.this, generateData(result.getResult()));
                            adapter.setIsIn(false);
                            adapter.setOnItemClickListener(new InOutAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(View v, int position) {
                                    params.put("statType", (String) v.getTag());
                                    Bundle bundle = new Bundle();
                                    Intent intent = new Intent();
                                    bundle.putSerializable("PARAMS", params);
                                    intent.setClass(OutActivity.this, ReasonActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        } else {
                            adapter.setItems(generateData(result.getResult()));
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        showMsg(getResources().getString(R.string.error_connect));
                        if (adapter == null) {
                            adapter = new InOutAdapter(OutActivity.this, generateEmptyData());
                            recyclerView.setAdapter(adapter);
                        } else {
                            adapter.setItems(generateEmptyData());
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    showMsg(getResources().getString(R.string.error_connect));
                    if (adapter == null) {
                        adapter = new InOutAdapter(OutActivity.this, generateEmptyData());
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
                    adapter = new InOutAdapter(OutActivity.this, generateEmptyData());
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.setItems(generateEmptyData());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private List<In.InOutItem> generateData(Out.OutResult result) {
        List<In.InOutItem> data = new ArrayList<>();
        In.InOutItem item = new In.InOutItem();
        item.setName("企业");
        item.setType("QY");
        item.setdCount(result.getQY_OUT_D_COUNT());
        item.setCount(result.getQY_OUT_COUNT());
        item.setTotalDCount(result.getTOTAL_QY_OUT_D_COUNT());
        item.setTotalCount(result.getTOTAL_QY_OUT_COUNT());
        data.add(item);
        item = new In.InOutItem();
        item.setName("农民专业合作社");
        item.setType("NM");
        item.setdCount(result.getNM_OUT_D_COUNT());
        item.setCount(result.getNM_OUT_COUNT());
        item.setTotalDCount(result.getTOTAL_NM_OUT_D_COUNT());
        item.setTotalCount(result.getTOTAL_NM_OUT_COUNT());
        data.add(item);
        item = new In.InOutItem();
        item.setName("个体");
        item.setType("GT");
        item.setdCount(result.getGT_OUT_D_COUNT());
        item.setCount(result.getGT_OUT_COUNT());
        item.setTotalDCount(result.getTOTAL_GT_OUT_D_COUNT());
        item.setTotalCount(result.getTOTAL_GT_OUT_COUNT());
        data.add(item);
        return data;
    }

    private List<In.InOutItem> generateEmptyData() {
        List<In.InOutItem> data = new ArrayList<>();
        In.InOutItem item = new In.InOutItem();
        item.setName("企业");
        item.setType("QY");
        data.add(item);
        item = new In.InOutItem();
        item.setName("农民专业合作社");
        item.setType("NM");
        data.add(item);
        item = new In.InOutItem();
        item.setName("个体");
        item.setType("GT");
        data.add(item);
        return data;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
