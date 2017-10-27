package com.wondersgroup.commerce.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.AdvertisementAdapter;
import com.wondersgroup.commerce.adapter.TextWpicAdapter;
import com.wondersgroup.commerce.adapter.Title3RowAdapter;
import com.wondersgroup.commerce.adapter.Title4RowAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.recyclerView.viewModel.CaseQueryViewModel;
import com.wondersgroup.commerce.recyclerView.viewModel.EmailViewModel;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.model.ad.AdQuery;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.teamwork.casedeal.CaseQueryDetailActivity;
import com.wondersgroup.commerce.teamwork.casedeal.bean.CaseQueryBean;
import com.wondersgroup.commerce.teamwork.email.EmailBean;
import com.wondersgroup.commerce.model.GwjsBean;
import com.wondersgroup.commerce.model.GwjsCondition;
import com.wondersgroup.commerce.model.TextWpicItem;
import com.wondersgroup.commerce.model.Title4RowItem;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.CCCXResult;
import com.wondersgroup.commerce.model.ccjc.CCToDoResult;
import com.wondersgroup.commerce.model.yn.CaseInfo;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.teamwork.email.EmailDetailsActivity;
import com.wondersgroup.commerce.teamwork.email.EmailResult;
import com.wondersgroup.commerce.utils.DateUtil;
import com.wondersgroup.commerce.utils.DividerItemDecoration;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.recyclerView.CommonAdapter;
import com.wondersgroup.commerce.widget.SearchLayout;
import com.wondersgroup.commerce.ynwq.widget.CountBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.wondersgroup.commerce.activity.ViewPagerActivity.BUENT_ID;

/**
 * Created by yclli on 2016/3/16.
 * <p>
 * 抽查检查录入、公文检索结果、收件箱
 */
public class RecyclerActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.search_bar)
    LinearLayout searchBar;
    @BindView(R.id.searchtxt)
    EditText searchTxt;
    @BindView(R.id.activity_recycler)
    RecyclerView recycler;
    @BindView(R.id.layout_search)
    View layoutSearch;
    @BindView(R.id.view_error)
    View viewError;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.searchLayout)
    SearchLayout searchLayout;

    private String doctype;
    private GwjsCondition condition;
    private String type;
    private TextWpicAdapter textWpicAdapter;
    private Title4RowAdapter title4RowAdapter;
    private Title3RowAdapter title3RowAdapter;
    private List<Title4RowItem> title4RowItems;
    private List<Title4RowItem> title3RowItems;
    private List<TextWpicItem> textWpicItems;
    private List<GwjsBean.Result.Result_> dataList;
    private List<ViewModel> viewModels;
    private CommonAdapter adapter;
    private AdvertisementAdapter adAdapter;
    private List<AdQuery.AdOp> adList;
    private String userId;
    private String organId;
    private String deptId;
    private HashMap<String, String> param;
    private List<String> checkIds;
    private List<String> entIds;
    private List<String> onlyLocals;
    private CountBar countBar;
    private int pageNo = 0, pageMax = 0, totalRecord = 0;
    private boolean isLoaded = false;
    private int state = Constants.LOAD_REFRESH;
    private HashMap queryConditionMap;
    private Gson gson;
    private RecyclerView.OnScrollListener onScrollListener;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_recycler);
        ButterKnife.bind(this);
        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        organId = loginBean.getResult().getOrganId();
        deptId = loginBean.getResult().getDeptId();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        gson = new Gson();
        String titleString = getIntent().getStringExtra(Constants.TITLE);
        if (titleString == null)
            titleString = "公文检索结果";
        title.setText(titleString);
        type = getIntent().getStringExtra(Constants.TYPE);
        if (type == null)
            type = "GWJS";

        //滑动监听
        initOnScrollListener();

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        init();
    }

    public void init() {
        param = new HashMap<>();
        checkIds = new ArrayList<>();
        entIds = new ArrayList<>();
        onlyLocals = new ArrayList<>();

        //通用的adapter
        viewModels = new ArrayList<>();
        adapter = new CommonAdapter(this, viewModels);

        switch (type) {
            case "casequery"://案件查询
                searchLayout.setVisibility(View.GONE);
                adapter.setOnItemClick(new CommonAdapter.OnItemClick() {
                    @Override
                    public void onClick(int position) {
                        CaseQueryViewModel caseQueryViewModel = (CaseQueryViewModel) viewModels.get(position);
                        Intent intent = new Intent(RecyclerActivity.this, CaseQueryDetailActivity.class);
                        intent.putExtra("clueNo", caseQueryViewModel.getBean().getClueNo());
                        intent.putExtra(Constants.TYPE, Constants.AREA_SC);
                        startActivity(intent);
                    }
                });
                recycler.setAdapter(adapter);
                recycler.addOnScrollListener(onScrollListener);
                queryConditionMap = (HashMap) getIntent().getSerializableExtra("queryConditionMap");
                param = (HashMap<String, String>) getIntent().getSerializableExtra("param");
                param.put("queryCondition", gson.toJson(queryConditionMap));
                pageNo = 1;

                getCaseQueryList();

                break;
            case "email"://收件箱
                searchLayout.setVisibility(View.VISIBLE);
                searchLayout.setSearchListenr(new SearchLayout.SearchListener() {
                    @Override
                    public void search(String content) {
                        pageNo = 1;
                        state = Constants.LOAD_REFRESH;
                        param.put("condition", String.format("{pageNo:%d,pageSize:%d,wordKey:'%s'}", pageNo, 10, content));
                        getEmail();
                    }
                });
                adapter.setOnItemClick(new CommonAdapter.OnItemClick() {
                    @Override
                    public void onClick(int position) {
                        ArrayList<EmailBean> emailBeanList = new ArrayList<EmailBean>();
                        for (ViewModel model: viewModels){
                            emailBeanList.add(((EmailViewModel)model).getBean());
                        }
                        Intent intent = new Intent(RecyclerActivity.this, EmailDetailsActivity.class);
                        intent.putExtra(Constants.POSITION, position);
                        intent.putParcelableArrayListExtra("emailBeanList", emailBeanList);
                        startActivity(intent);
                    }
                });
                recycler.setAdapter(adapter);
                recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
                recycler.addOnScrollListener(onScrollListener);

                param.put(Constants.WS_CODE_REQ, "07010016");
                param.put(Constants.USER_ID, userId);
                param.put(Constants.DEPT_ID, deptId);
                param.put("businessType", "1");
                pageNo = 1;
                param.put("condition", String.format("{pageNo:%d,pageSize:%d}", pageNo, 10));

                getEmail();

                break;
            case "gglb"://广告列表
                if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable("PARAMS") != null) {
                    Map<String, String> map = (Map<String, String>) getIntent().getExtras().getSerializable("PARAMS");
                    param.putAll(map);
                }
                adList = new ArrayList<>();
                adAdapter = new AdvertisementAdapter(RecyclerActivity.this, adList);
                adAdapter.setItemClickListener(new AdvertisementAdapter.ItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(RecyclerActivity.this, ViewPagerActivity.class);
                        intent.putExtra(Constants.TITLE, "广告详情");
                        intent.putExtra(Constants.TYPE, "GGXQ");
                        BUENT_ID = adList.get(position).getBuentId();
                        startActivity(intent);
                    }
                });
                recycler.setAdapter(adAdapter);
                recycler.addOnScrollListener(onScrollListener);
                getAdQuery();
                break;
            case "GWJS":
                searchBar.setVisibility(View.GONE);
                doctype = getIntent().getStringExtra("doctype");
                condition = Hawk.get("gwCondition");
                dataList = new ArrayList<>();
                textWpicItems = new ArrayList<>();
                textWpicAdapter = new TextWpicAdapter(textWpicItems);
                recycler.setAdapter(textWpicAdapter);
                textWpicAdapter.setOnItemClickListener(new TextWpicAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        Intent intent = new Intent(RecyclerActivity.this, ViewPagerActivity.class);
                        intent.putExtra("title", dataList.get(position).getTitle());
                        intent.putExtra("type", "GWJSXQ");
                        Hawk.put("gwType", doctype);
                        Hawk.put("gwId", dataList.get(position).getDocId());
                        startActivity(intent);
                    }
                });

                getGWJSDataList();
                break;
            case "TSCX":
            case "JBCX":
                title4RowItems = new ArrayList<>();
                title4RowAdapter = new Title4RowAdapter(title4RowItems);
                recycler.setAdapter(title4RowAdapter);
                title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        Intent intent = new Intent(RecyclerActivity.this, ViewPagerActivity.class);
                        intent.putExtra("type", type.startsWith("TS") ? "TSXQXXLZ" : "JBXQXXLZ");
                        intent.putExtra("title", title4RowItems.get(position).getTitle());
                        Hawk.put("TSJBXQ_caseId", title4RowItems.get(position).getTitle());
                        startActivity(intent);
                    }
                });
                String infoType = type.startsWith("TS") ? "1" : "2";
                String basicName = getIntent().getStringExtra("TSJBCX_basicName").equals("") ? " " : getIntent().getStringExtra("TSJBCX_basicName");
                String accuseName = getIntent().getStringExtra("TSJBCX_accuseName").equals("") ? " " : getIntent().getStringExtra("TSJBCX_accuseName");
                String caseId = getIntent().getStringExtra("TSJBCX_caseId").equals("") ? " " : getIntent().getStringExtra("TSJBCX_caseId");
                String pageSize = "25";
                Call<Result<List<CaseInfo>>> call = ApiManager.ynApi.searchCase(userId, infoType, basicName, accuseName, caseId, "1", pageSize);
                call.enqueue(new Callback<Result<List<CaseInfo>>>() {
                    @Override
                    public void onResponse(Response<Result<List<CaseInfo>>> response, Retrofit retrofit) {
                        isLoaded = true;
                        if (response.isSuccess() && response.body() != null) {
                            if (200 == response.body().getCode()) {
                                List<CaseInfo> list = response.body().getObject();
                                if (list != null) {
                                    if (list.size() != 0) {
                                        for (CaseInfo info : list) {
                                            Title4RowItem tmp = new Title4RowItem();
                                            tmp.setTitle(info.getCaseId());
                                            tmp.setRowOneTitle("消费者");
                                            tmp.setRowOneContent(info.getBasicName());
                                            if (type.startsWith("TS")) {
                                                tmp.setRowTwoTitle("被诉单位");
                                            } else {
                                                tmp.setRowTwoTitle("被举报单位");
                                            }
                                            tmp.setRowTwoContent(info.getAccuseName());
                                            tmp.setRowThrTitle("登记时间");
                                            tmp.setRowThrContent(info.getRegDate());
                                            tmp.setRowForTitle("当前状态");
                                            tmp.setRowForContent(info.getStatus());

                                            title4RowItems.add(tmp);
                                        }
                                        title4RowAdapter.notifyItemRangeInserted(0, title4RowItems.size());
                                    } else {
                                        layoutSearch.setVisibility(View.GONE);
                                        viewError.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    layoutSearch.setVisibility(View.GONE);
                                    viewError.setVisibility(View.VISIBLE);
                                }
                            } else {
                                layoutSearch.setVisibility(View.GONE);
                                viewError.setVisibility(View.VISIBLE);
                            }
                        } else {
                            layoutSearch.setVisibility(View.GONE);
                            viewError.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        isLoaded = true;
                        layoutSearch.setVisibility(View.GONE);
                        viewError.setVisibility(View.VISIBLE);
                    }
                });
                break;
            case "ZTCX":
                getIntent().getStringExtra("name");
                getIntent().getStringExtra("uid");
                getIntent().getStringExtra("register");
                title3RowItems = new ArrayList<>();
                title3RowAdapter = new Title3RowAdapter(title3RowItems);
                recycler.setAdapter(title3RowAdapter);
                for (int i = 0; i < 10; i++) {
                    Title4RowItem tmp = new Title4RowItem();
                    tmp.setTitle("某某有限公司");
                    tmp.setRowOneTitle("统一社会信用代码");
                    tmp.setRowOneContent("5329830183940813048");
                    tmp.setRowTwoTitle("注册号");
                    tmp.setRowTwoContent("123456789");
                    tmp.setRowThrTitle("地址");
                    tmp.setRowThrContent("某某街道");
                    title3RowItems.add(tmp);
                }
                title3RowAdapter.notifyItemRangeInserted(0, title3RowItems.size());
                break;
            case "CCJCCX":
                title4RowItems = new ArrayList<>();
                title4RowAdapter = new Title4RowAdapter(title4RowItems);
                recycler.setAdapter(title4RowAdapter);
                title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        Intent intent = new Intent(RecyclerActivity.this, ViewPagerActivity.class);
                        intent.putExtra("type", "CCJCCX");
                        intent.putExtra("title", "抽查检查查询");
                        Hawk.put("CCJC_CheckId", checkIds.get(position));
                        Hawk.put("CCJC_EntId", entIds.get(position));
                        startActivity(intent);
                    }
                });
                getCCJCCX();
                break;
            case "CCJCDB":
                searchBar.setVisibility(View.VISIBLE);
                title4RowItems = new ArrayList<>();
                title4RowAdapter = new Title4RowAdapter(title4RowItems);
                recycler.setAdapter(title4RowAdapter);
                title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        Intent intent = new Intent(RecyclerActivity.this, ViewPagerActivity.class);
                        intent.putExtra("type", "CCJCDB");
                        intent.putExtra("title", "抽查检查待办");
                        Hawk.put("CCJC_CheckId", checkIds.get(position));
                        Hawk.put("CCJC_EntId", entIds.get(position));
                        Hawk.put("CCJC_OnlyLocal", onlyLocals.get(position));
                        startActivity(intent);
                    }
                });
                getCCJCDB();
                break;
        }
    }

    public void initOnScrollListener() {

        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView view, int dx, int dy) {
                super.onScrolled(view, dx, dy);

                if (countBar != null) {
                    if (countBar.getDialog() != null)
                        countBar.getDialog().show();
                    if (countBar.isVisible()) {
                        countBar.setCur(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                        countBar.setTotal(totalRecord);
                    }
                }

                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getItemCount() - 2
                        && isLoaded) {

                    loadMore();

                    switch (type) {
                        // < pageMax-1:pageNo从0开始计;< pageMax:pageNo从1开始计
                        case "CCJCCX":
                            if (pageNo <= pageMax - 1) {
                                getCCJCCX();
                            }
                            break;
                        case "CCJCDB":
                            if (pageNo <= pageMax - 1) {
                                getCCJCDB();
                            }
                            break;
                        case "GWJS":
                            if (pageNo <= pageMax - 1) {
                                getGWJSDataList();
                            }
                            break;
                        case "gglb":
                            if (pageNo <= pageMax - 1) {
                                getAdQuery();
                            }
                            break;
                        case "email":
                            if (pageNo <= pageMax) {
                                param.put("condition", String.format("{pageNo:%d,pageSize:%d}", pageNo, 10));
                                getEmail();
                            }
                            break;
                        case "casequery":
                            if (pageNo <= pageMax) {
                                queryConditionMap.put("currentPage", pageNo);
                                param.put("queryCondition", gson.toJson(queryConditionMap));
                                getCaseQueryList();
                            }
                            break;
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (countBar != null) {
                        if (countBar.isVisible()){
//                            countBar.getDialog().hide();//hide存在一些问题
                            countBar.getDialog().dismiss();
                        }
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    synchronized (this) {
                        if (countBar == null || countBar.getDialog() == null) {
                            if (recyclerView.getAdapter() != null) {
                                countBar = CountBar.newInstance(totalRecord);
                                countBar.show(getSupportFragmentManager(), "CountBar");
                            }
                        }
                    }
                }
            }
        };
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getCCJCCX() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(RecyclerActivity.this)
                .build();
        loadingDialog.show();
        param = (HashMap<String, String>) getIntent().getSerializableExtra("body");
        param.put(Constants.PAGE_NO, pageNo + "");
        Call<CCCXResult> call = ApiManager.ccjcApi.ccCX(param);
        call.enqueue(new Callback<CCCXResult>() {
            @Override
            public void onResponse(Response<CCCXResult> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                isLoaded = true;
                pageMax = response.body().getPageCount();
                totalRecord = response.body().getTotalRecord();
                if (response.isSuccess()) {
                    CCCXResult result = response.body();
                    if ("200".equals(result.getCode())) {
                        if (state == Constants.LOAD_REFRESH) {
                            title4RowItems.clear();
                            checkIds.clear();
                            entIds.clear();
                            onlyLocals.clear();
                        }
                        for (CCCXResult.Result r :
                                result.getResult()) {
                            Title4RowItem tmp = new Title4RowItem();
                            tmp.setTitle(r.getEntName());
                            tmp.setRowOneTitle("统一社会信用代码");
                            tmp.setRowOneContent(r.getUniScid());
                            tmp.setRowTwoTitle("注册号");
                            tmp.setRowTwoContent(r.getRegNo());
                            tmp.setRowThrTitle("地址");
                            tmp.setRowThrContent(r.getAddress());
                            tmp.setRowForTitle("抽查检查状态");
                            tmp.setRowForContent(r.getFlowStatus());
                            title4RowItems.add(tmp);
                            checkIds.add(r.getCheckId());
                            entIds.add(r.getEntId());
                        }
                        if (title4RowItems.size() == 0) {
                            recycler.setVisibility(View.GONE);
                            viewError.setVisibility(View.VISIBLE);
                        } else {
                            recycler.setVisibility(View.VISIBLE);
                            viewError.setVisibility(View.GONE);
                        }
                        title4RowAdapter.notifyDataSetChanged();
                    } else {
                        viewError.setVisibility(View.VISIBLE);
                        recycler.setVisibility(View.GONE);
                    }
                } else {
                    viewError.setVisibility(View.VISIBLE);
                    recycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                isLoaded = true;
                Toast.makeText(RecyclerActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCCJCDB() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(RecyclerActivity.this)
                .build();
        loadingDialog.show();
        param.put("wsCodeReq", "000201");
        param.put(Constants.PAGE_NO, pageNo + "");
        param.put("userId", userId);
        if (organId.length() > 5)
            param.put("organId", organId.substring(0, 6));
        else
            param.put("organId", organId);
        Call<CCToDoResult> call = ApiManager.ccjcApi.ccTodo(param);
        call.enqueue(new Callback<CCToDoResult>() {
            @Override
            public void onResponse(Response<CCToDoResult> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                isLoaded = true;
                pageMax = response.body().getPageCount();
                totalRecord = response.body().getTotalRecord();
                if (response.isSuccess() && response.body() != null) {
                    CCToDoResult result = response.body();
                    if ("200".equals(result.getCode())) {
                        if (state == Constants.LOAD_REFRESH) {
                            title4RowItems.clear();
                            checkIds.clear();
                            entIds.clear();
                            onlyLocals.clear();
                        }
                        for (CCToDoResult.Result r : result.getResult()) {
                            String checkWayShow = r.getCheckWayShow();
                            if (Constants.SDHC.equals(checkWayShow)) {
                                Title4RowItem tmp = new Title4RowItem();
                                tmp.setTitle(r.getEntName());
                                tmp.setRowOneTitle("统一社会信用代码");
                                tmp.setRowOneContent(r.getUniScid());
                                tmp.setRowTwoTitle("注册号");
                                tmp.setRowTwoContent(r.getRegNo());
                                tmp.setRowThrTitle("地址");
                                tmp.setRowThrContent(r.getAddress());
                                tmp.setRowForTitle("抽查方式");
                                tmp.setRowForContent(checkWayShow);
                                title4RowItems.add(tmp);
                                checkIds.add(r.getCheckId());
                                entIds.add(r.getEntyId());
                                onlyLocals.add(r.getCheckWayOnlyLocal());
                            }
                        }
                        if (title4RowItems.size() == 0) {
                            recycler.setVisibility(View.GONE);
                            viewError.setVisibility(View.VISIBLE);
                            tvError.setText(Constants.NO_DB);
                        } else {
                            recycler.setVisibility(View.VISIBLE);
                            viewError.setVisibility(View.GONE);
                        }
                        title4RowAdapter.notifyDataSetChanged();
                    } else {
                        recycler.setVisibility(View.GONE);
                        viewError.setVisibility(View.VISIBLE);
                        tvError.setText(Constants.NO_DB);
                    }
                } else {
                    layoutSearch.setVisibility(View.GONE);
                    viewError.setVisibility(View.VISIBLE);
                    tvError.setText(Constants.NO_DB);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                isLoaded = true;
                layoutSearch.setVisibility(View.GONE);
                viewError.setVisibility(View.VISIBLE);
                tvError.setText(Constants.NO_DB);
            }
        });
    }

    public void getGWJSDataList() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010008");
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("organId", organId);
        Gson gson = new GsonBuilder().create();
        condition.setPageNo((pageNo + 1) + "");
        map.put("condition", gson.toJson(condition));
        if ("收文管理".equals(doctype)) {
            map.put("businessType", "2");
        } else if ("发文管理".equals(doctype)) {
            map.put("businessType", "1");
        }
        Call<GwjsBean> call = ApiManager.oaApi.apiGetDocList(map);
        call.enqueue(new Callback<GwjsBean>() {
            @Override
            public void onResponse(Response<GwjsBean> response, Retrofit retrofit) {
                isLoaded = true;
                if (response.body() != null) {
                    if ("200".equals(response.body().getCode()) && response.body().getResult() != null) {
                        List<GwjsBean.Result.Result_> temp = response.body().getResult().getResult();
                        pageMax = response.body().getResult().getPageCount();
                        totalRecord = response.body().getResult().getTotalRecord();
                        for (int i = 0; i < temp.size(); i++) {
                            TextWpicItem item = new TextWpicItem();
                            item.setTitle(temp.get(i).getTitle());
                            item.setRowTwoText(temp.get(i).getCreateAt());
                            item.setPicId(R.mipmap.icons_normal);
                            item.setPicText(temp.get(i).getType());
                            textWpicItems.add(item);
                        }
                        textWpicAdapter.notifyItemRangeInserted(dataList.size(), temp.size());
                        dataList.addAll(temp);
                    } else {
                        Toast.makeText(RecyclerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                isLoaded = true;
                Toast.makeText(RecyclerActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getEmail() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(RecyclerActivity.this).build();
        loadingDialog.show();
        Call<Result<EmailResult>> call = ApiManager.oaApi.getEmail(param);
        call.enqueue(new Callback<Result<EmailResult>>() {
            @Override
            public void onResponse(Response<Result<EmailResult>> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                isLoaded = true;
                if (response.body() != null
                        && response.body().getObject() != null
                        && response.body().getObject().getEmailList() != null
                        && response.body().getObject().getEmailList().size() != 0) {
                    List<EmailBean> emails = response.body().getObject().getEmailList();
                    //转换日期格式
                    changeDate(emails);
                    //包裝
                    ArrayList<ViewModel> list = new ArrayList<ViewModel>();
                    for (EmailBean emailBean: emails){
                        EmailViewModel emailViewModel = new EmailViewModel(emailBean);
                        list.add(emailViewModel);
                    }
                    if (state == Constants.LOAD_REFRESH) {
                        viewModels.clear();
                        viewModels.addAll(list);
                        adapter.notifyDataSetChanged();
                    } else if (state == Constants.LOAD_MORE) {
//                        emailBeanList.addAll(emails);
                        adapter.notifyDataSetChanged();
                    }
                    viewError.setVisibility(View.GONE);
                } else {
                    if (state == Constants.LOAD_REFRESH) {
                        viewModels.clear();
                        adapter.notifyDataSetChanged();
                        viewError.setVisibility(View.VISIBLE);
                    } else {
                        if (viewModels.size() == 0)
                            viewError.setVisibility(View.VISIBLE);
                        else
                            viewError.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                isLoaded = true;
                if (viewModels.size() == 0)
                    viewError.setVisibility(View.VISIBLE);
                else
                    viewError.setVisibility(View.GONE);
            }
        });
    }

    private List<AdQuery.AdOp> generateAdData(List<List<AdQuery.AdOp>> tmp) {
        List<AdQuery.AdOp> ret = new ArrayList<>();
        for (List<AdQuery.AdOp> item : tmp) {
            AdQuery.AdOp op = item.get(0);
            op.setBulicNo(item.get(1).getBulicNo());
            ret.add(op);
        }
        return ret;
    }

    /**
     * 广告查询
     */
    private void getAdQuery() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(RecyclerActivity.this).build();
        loadingDialog.show();
        param.put("pageNo", (pageNo + 1) + "");
        param.put("pageSize", "10");
        ApiManager.adApi.adQuery(param).enqueue(new Callback<AdQuery>() {
            @Override
            public void onResponse(Response<AdQuery> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                isLoaded = true;
                if (response.body() != null) {
                    AdQuery result = response.body();
                    if (result.getCode() == 200) {
                        pageMax = result.getResult().getPageCount();
                        totalRecord = result.getResult().getTotalRecord();
                        if (state == Constants.LOAD_REFRESH) {
                            adList.clear();
                            adList.addAll(generateAdData(result.getResult().getResultList()));
                            adAdapter.notifyDataSetChanged();
                        } else if (state == Constants.LOAD_MORE) {
                            adList.addAll(generateAdData(result.getResult().getResultList()));
                            adAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                        if (state == Constants.LOAD_REFRESH) {
                            adAdapter.notifyDataSetChanged();
                        }
                    }
                }
                if (adList.size() == 0)
                    viewError.setVisibility(View.VISIBLE);
                else
                    viewError.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                isLoaded = true;
                if (adList.size() == 0)
                    viewError.setVisibility(View.VISIBLE);
                else
                    viewError.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.searchbtn)
    public void onClick(View v) {
        String searchStr = searchTxt.getText().toString().trim();
        if (param != null && !"".equals(searchStr)) {
            param.put("entNameRegNoUniScid", searchStr);
            param.put(Constants.PAGE_NO, "1");
        }

        state = Constants.LOAD_REFRESH;
        getCCJCDB();
    }

    public void getCaseQueryList() {
        String url = CaseApi.URL_CASE_1 + CaseApi.TO_CASE_QUERY;
        Call<Result<List<CaseQueryBean>>> call = ApiManager.caseApi.toCaseQuery(url, param);
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(RecyclerActivity.this).build();
        loadingDialog.show();
        call.enqueue(new Callback<Result<List<CaseQueryBean>>>() {
            @Override
            public void onResponse(Response<Result<List<CaseQueryBean>>> response, Retrofit retrofit) {
                isLoaded = true;
                loadingDialog.dismiss();
                if (response.body() != null && response.body().getObject() != null
                        && response.body().getObject().size() != 0) {
                    pageMax = response.body().getPageCount();
                    totalRecord = response.body().getTotalRecord();
                    List<ViewModel> array = converToViewModel(CaseQueryViewModel.class, response.body().getObject());
                    List<CaseQueryViewModel> list = new ArrayList<CaseQueryViewModel>();
                    for (CaseQueryBean caseQueryBean: response.body().getObject()){
                        CaseQueryViewModel viewModel = new CaseQueryViewModel(caseQueryBean);
                        list.add(viewModel);
                    }
                    if (state == Constants.LOAD_REFRESH)
                        viewModels.clear();
                    viewModels.addAll(list);
                    adapter.notifyDataSetChanged();
                } else {
                    viewError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                isLoaded = true;
                loadingDialog.dismiss();
                viewError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void changeDate(List<EmailBean> emails) {
        for (int i = 0; i < emails.size(); i++) {
            EmailBean emailBean = emails.get(i);
            Date date = DateUtil.getDate(DateUtil.FORMAT_YY_MM_DD_HH_MM_SS, emailBean.getDate());
            if (DateUtil.isToday(date))
                emailBean.setDate(DateUtil.getTime(DateUtil.FORMAT_HH_MM, date));
            else if (DateUtil.isYestoday(date))
                emailBean.setDate("昨天 " + DateUtil.getTime(DateUtil.FORMAT_HH_MM, date));
            else if (DateUtil.isThisYear(date))
                emailBean.setDate(DateUtil.getTime(DateUtil.FORMAT_MM_DD, date));
            else
                emailBean.setDate(DateUtil.getTime(DateUtil.FORMATE_YY_MM_DD, date));
        }
    }

    public void loadMore() {
        isLoaded = false;
        pageNo++;
        state = Constants.LOAD_MORE;
    }

    public List<ViewModel> converToViewModel(Class c, List list){
        try {
            c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
