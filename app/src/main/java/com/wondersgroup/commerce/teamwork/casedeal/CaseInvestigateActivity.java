package com.wondersgroup.commerce.teamwork.casedeal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.adapter.CaseBtnAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.activity.ListInfoActivity;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.SerializableMap;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.teamwork.casedeal.adapter.CaseAdapter;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.utils.DataUtils;
import com.wondersgroup.commerce.utils.DividerItemDecoration;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.ynwq.widget.CountBar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/*
* 待调查案件、案件查询结果 列表页面
* activityType="myCaseToInvestigate"  待调查案件列表
* activityType="queryMyCase"  案件条件查询结果列表
* by Lee
* */

public class CaseInvestigateActivity extends AppCompatActivity {

    private final String TAG = "CaseInvestigateActivity";
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.simple_navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.search_bar)
    RelativeLayout searchBar;
    @Bind(R.id.searchtxt)
    EditText searchEdit;
    @Bind(R.id.drawer_btn_list)
    RecyclerView casestageRecyclerView;
    @Bind(R.id.date1)
    LinearLayout startDateLayout;
    @Bind(R.id.date1txt)
    TextView startDateTxt;
    @Bind(R.id.date2)
    LinearLayout endDateLayout;
    @Bind(R.id.date2txt)
    TextView endDateTxt;
    @Bind(R.id.layout_error)
    View layoutError;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private CaseAdapter caseAdapter;
    private List<CaseInvestigateTitle> dataList = new ArrayList<>();
    private String activityType = null;
    Map<String, String> conditionMap = new HashMap<String, String>();
    private RootAppcation app;

    //待调查案件筛选相关
    private CaseBtnAdapter caseBtnAdapter;
    private List<Boolean> itemsIsClick = new ArrayList<>();
    private List<String> stateBtnStr = new ArrayList<String>();
    private boolean isDate1null = true;
    private boolean isDate2null = true;
    private Map<String, String> caseStatusMap;
    private String caseState;   //案件阶段
    private String startDate;   //立案日期始
    private String endDate;     //立案日期止
    private TotalLoginBean loginBean;
    private CountBar countBar;
    private int pageNo = 1, pageMax = 1, totalRecord;
    private int loadType = Constants.LOAD_REFRESH;
    private boolean isLoaded;//true:如果正在加载中：取消滑动加载更多

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_investigate);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        initView();
        initData();
    }

    private void initView() {
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        caseBtnAdapter = new CaseBtnAdapter(this, stateBtnStr, itemsIsClick);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        casestageRecyclerView.setLayoutManager(gridLayoutManager);
        casestageRecyclerView.setAdapter(caseBtnAdapter);
        caseBtnAdapter.setOnItemClickListener(new CaseBtnAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                itemsIsClick.set(position, !itemsIsClick.get(position));
                for (int j = 0; j < itemsIsClick.size(); j++) {
                    if (j != position) {
                        itemsIsClick.set(j, false);
                    }
                }
                if (itemsIsClick.get(position)) {
                    caseState = stateBtnStr.get(position);
                } else
                    caseState = "";
                caseBtnAdapter.notifyDataSetChanged();
            }
        });


        caseAdapter = new CaseAdapter(CaseInvestigateActivity.this, dataList);
        caseAdapter.setOnItemClickListener(new CaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                CaseInvestigateTitle data = dataList.get(position);
                Bundle args = new Bundle();
                args.putSerializable("CaseInvestigateTitle", data);
                if (activityType.equals(ApiManager.caseApi.INVESTIGATE_CASE_LIST)) {
                    Intent mIntent = new Intent(CaseInvestigateActivity.this, CaseDetailActivity.class);
                    mIntent.putExtras(args);
                    startActivity(mIntent);
                } else {
                    Intent mIntent = new Intent(CaseInvestigateActivity.this, CaseQueryDetailActivity.class);
                    mIntent.putExtra("clueNo", data.getClueNo());
                    mIntent.putExtra(Constants.TYPE, Constants.SC);
                    startActivity(mIntent);
                }
            }
        });
        recyclerView.setAdapter(caseAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (countBar != null) {
                        if (countBar.isVisible())
                            countBar.getDialog().hide();
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    synchronized (this) {
                        if (countBar == null) {
                            if (recyclerView.getAdapter() != null) {
                                countBar = CountBar.newInstance(totalRecord);
                                countBar.show(getSupportFragmentManager(), "CountBar");
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (countBar != null && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (countBar.getDialog() != null)
                        countBar.getDialog().show();
                    if (countBar.isVisible()) {
                        countBar.setCur(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                        countBar.setTotal(totalRecord);
                    }
                }

                if ((linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1) &&
                        (pageNo < pageMax) && isLoaded) {
                    loadType = Constants.LOAD_MORE;
                    isLoaded = false;
                    pageNo++;
                    if (activityType.equals(ApiManager.caseApi.INVESTIGATE_CASE_LIST)) {
                        getMyCaseToInvestigate(pageNo);
                    } else {
                        getQueryMyCase(pageNo);
                    }
                }
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        activityType = bundle.getString("activityType");
        if (activityType.equals(ApiManager.caseApi.INVESTIGATE_CASE_LIST)) {
            title.setText("案件调查");
            caseState = "";
            startDate = "";
            endDate = "";
            getMyCaseToInvestigate(pageNo);
        } else {
            title.setText("案件查询结果");
            searchBar.setVisibility(View.GONE);
            final SerializableMap serializableMap = (SerializableMap) bundle.getSerializable("conditionMap");
            conditionMap = serializableMap.getMap();
            getQueryMyCase(pageNo);
        }

    }

    @OnClick({R.id.searchbtn, R.id.filterbtn, R.id.date1, R.id.date2, R.id.resetbtn, R.id.submitbtn})
    public void onClick(View v) {
        final CusDatePickerDialog dateDialog;
        switch (v.getId()) {
            case R.id.searchbtn:
                searchByName();
                break;
            case R.id.filterbtn:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.date1:
                dateDialog = CusDatePickerDialog.newInstance("选择起始时间");
                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void OnDateSet(String dateString) {
                        if (!"截止日期".equals(endDateTxt.getText().toString())
                                && Date.valueOf(dateString).after(Date.valueOf(endDateTxt.getText().toString()))) {
                            Toast.makeText(CaseInvestigateActivity.this, "起始时间不能大于截止时间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isDate1null) {
                            startDateLayout.setBackgroundResource(R.drawable.rounded_rect_blueline);
                            startDateTxt.setTextColor(ContextCompat.getColor(CaseInvestigateActivity.this, R.color.blue));
                            isDate1null = false;
                        }
                        startDateTxt.setText(dateString);
                        startDate = dateString;
                    }
                });
                dateDialog.show(getSupportFragmentManager(), "HCSJ");
                break;
            case R.id.date2:
                dateDialog = CusDatePickerDialog.newInstance("选择截止时间");
                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void OnDateSet(String dateString) {
                        if (!"起始日期".equals(startDateTxt.getText().toString())
                                && Date.valueOf(dateString).before(Date.valueOf(startDateTxt.getText().toString()))) {
                            Toast.makeText(CaseInvestigateActivity.this, "截止时间不能小于起始时间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isDate2null) {
                            endDateLayout.setBackgroundResource(R.drawable.rounded_rect_blueline);
                            endDateTxt.setTextColor(ContextCompat.getColor(CaseInvestigateActivity.this, R.color.blue));
                            isDate2null = false;
                        }
                        endDateTxt.setText(dateString);
                        endDate = dateString;
                    }
                });
                dateDialog.show(getSupportFragmentManager(), "HCSJ");
                break;
            case R.id.resetbtn:
                resetBtns();
                break;
            case R.id.submitbtn:
                pageNo = 1;
                loadType = Constants.LOAD_REFRESH;
                getMyCaseToInvestigate(pageNo);
                drawerLayout.closeDrawers();
                break;
        }
    }

    //重置各按钮状态
    private void resetBtns() {

        //RecyclerView
        for (int i = 0; i < itemsIsClick.size(); i++) {
            itemsIsClick.set(i, false);
        }
        caseBtnAdapter.notifyDataSetChanged();
        caseState = "";

        //时间按钮
        if (!isDate1null) {
            startDateLayout.setBackgroundResource(R.drawable.rounded_rect_grayfill);
            startDateTxt.setTextColor(ContextCompat.getColor(this, R.color.light_gray));
            startDateTxt.setText("起始日期");
            isDate1null = true;
            startDate = "";
        }
        if (!isDate2null) {
            endDateLayout.setBackgroundResource(R.drawable.rounded_rect_grayfill);
            endDateTxt.setTextColor(ContextCompat.getColor(this, R.color.light_gray));
            endDateTxt.setText("截止日期");
            isDate2null = true;
            endDate = "";
        }
    }


    //获取待调查案件列表
    private void getMyCaseToInvestigate(final int page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", loginBean.getResult().getUserId());
        map.put("currentPage", String.valueOf(page));
        map.put("wsCodeReq", "03010002");
        if (!"".equals(searchEdit.getText().toString().trim()))
            map.put("caseNameOrNo", searchEdit.getText().toString().trim());
        if (!"".equals(caseState))
            map.put("caseStatus", DataUtils.getKey(caseStatusMap, caseState));
        if (!"".equals(startDate))
            map.put("regCaseDate", startDate);
        if (!"".equals(endDate))
            map.put("regCaseDate2", endDate);

        String url = "";
        Call<CaseInvestigateListBean> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.INVESTIGATE_CASE_LIST;
            call = ApiManager.caseApi.getMyCaseList(url,map);
        } else{
            url = CaseApi.INVESTIGATE_CASE_LIST;
            call = ApiManager.shyApi.getMyCaseList(url,map);
        }
        Log.d(TAG, "map.toString() = " + map.toString());

        call.enqueue(new Callback<CaseInvestigateListBean>() {
            @Override
            public void onResponse(Response<CaseInvestigateListBean> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null && response.body().getResult() != null
                        && response.body().getResult().getList() != null && response.body().getResult().getList().size() != 0) {
                    MyProgressDialog.dismiss();
                    isLoaded = true;
                    CaseInvestigateListBean caseList = response.body();
                    totalRecord = caseList.getResult().getTotalRecord();
                    pageMax = caseList.getResult().getPageCount();

                    if (caseStatusMap == null && caseList.getCaseStatus() != null) {
                        caseStatusMap = DataUtils.makeDicFromJson(caseList.getCaseStatus());
                        stateBtnStr.addAll(DataUtils.makeDicList(caseList.getCaseStatus(), false));
                        for (int i = 0; i < stateBtnStr.size(); i++) {
                            itemsIsClick.add(false);
                        }
                        caseBtnAdapter.notifyDataSetChanged();
                    }
                    if (loadType == Constants.LOAD_REFRESH)
                        dataList.clear();
                    dataList.addAll(caseList.getResult().getList());
                    caseAdapter.notifyDataSetChanged();

                    layoutError.setVisibility(View.GONE);
                } else {
                    layoutError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });

    }

    //案件条件查询
    private void getQueryMyCase(int page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", loginBean.getResult().getUserId());
//        map.put("userId", "00005859");
        map.put("wsCodeReq", "03010003");
        conditionMap.put("\"" + "currentPage" + "\"", "\"" + String.valueOf(page) + "\"");
        map.put("condition", conditionMap.toString());
        String url = "";
        Call<CaseQueryResult> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_QUERY_MY_CASE;
            call = ApiManager.caseApi.queryMyCase(url,map);
        } else{
            url = CaseApi.CASE_QUERY_MY_CASE;
            call = ApiManager.shyApi.queryMyCase(url,map);
        }
        Log.d(TAG, "map.toString() = " + map.toString());

        call.enqueue(new Callback<CaseQueryResult>() {
            @Override
            public void onResponse(Response<CaseQueryResult> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null && response.body().getResult() != null
                        && response.body().getResult().size() != 0) {
                    MyProgressDialog.dismiss();
                    isLoaded = true;
                    CaseQueryResult caseQueryResult = response.body();
                    totalRecord = caseQueryResult.getTotalRecord();
                    dataList.addAll(caseQueryResult.getResult());
                    caseAdapter.notifyDataSetChanged();
                } else {
                    if (dataList.size() == 0)
                        layoutError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (dataList.size() == 0)
                    layoutError.setVisibility(View.VISIBLE);
            }
        });
    }

    //通过名称或编号查询
    private void searchByName() {
        pageNo = 1;
        loadType = Constants.LOAD_REFRESH;
        getMyCaseToInvestigate(pageNo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
