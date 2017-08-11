package com.wondersgroup.commerce.activity;

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

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.TextWpicAdapter;
import com.wondersgroup.commerce.adapter.Title3RowAdapter;
import com.wondersgroup.commerce.adapter.Title4RowAdapter;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.GwjsBean;
import com.wondersgroup.commerce.model.TextWpicItem;
import com.wondersgroup.commerce.model.Title4RowItem;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.CCCXResult;
import com.wondersgroup.commerce.model.ccjc.CCToDoResult;
import com.wondersgroup.commerce.model.yn.CaseInfo;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.ynwq.widget.CountBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yclli on 2016/3/16.
 *
 * 抽查检查录入、公文检索结果
 */
public class RecyclerActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.search_bar)
    LinearLayout searchBar;
    @Bind(R.id.searchtxt)
    EditText searchTxt;
    @Bind(R.id.activity_recycler)
    RecyclerView recycler;
    @Bind(R.id.layout_search)
    View layoutSearch;
    @Bind(R.id.view_error)
    View viewError;
    @Bind(R.id.tv_error)
    TextView tvError;

    private String doctype;
    private String json;
    private String type;
    private TextWpicAdapter textWpicAdapter;
    private Title4RowAdapter title4RowAdapter;
    private Title3RowAdapter title3RowAdapter;
    private List<Title4RowItem> title4RowItems;
    private List<Title4RowItem> title3RowItems;
    private List<TextWpicItem> textWpicItems;
    private List<GwjsBean.Result.Result_> dataList;
    private String userId;
    private String organId;
    private String deptId;
    private HashMap<String,String> body;
    private List<String> checkIds;
    private List<String> entIds;
    private List<String> onlyLocals;
    private RootAppcation app;
    private CountBar countBar;
    private int pageNo = 0, pageMax = 0, totalRecord = 0;
    private boolean isLoaded = false;
    private int state = Constants.LOAD_REFRESH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_recycler);
        ButterKnife.bind(this);
        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId=loginBean.getResult().getUserId();
        organId=loginBean.getResult().getOrganId();
        deptId=loginBean.getResult().getDeptId();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        String titleString = getIntent().getStringExtra("title");
        if (titleString == null) titleString = "公文检索结果";
        type=getIntent().getStringExtra("type");
        if(type==null)type="GWJS";
        title.setText(titleString);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView view, int dx, int dy) {
                super.onScrolled(view, dx, dy);

                if(countBar!=null) {
                    if (countBar.getDialog() != null) countBar.getDialog().show();
                    if (countBar.isVisible()) {
                        countBar.setCur(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                        countBar.setTotal(totalRecord);
                    }
                }

                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getItemCount() - 2
                        && pageNo <pageMax-1 && isLoaded){
                    isLoaded = false;
                    pageNo++;
                    state = Constants.LOAD_MORE;
                    if ("CCJCCX".equals(type)){
                        getCCJCCX();
                    }else if ("CCJCDB".equals(type)){
                        getCCJCDB();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== RecyclerView.SCROLL_STATE_IDLE){
                    if(countBar!=null) {
                        if (countBar.isVisible()) countBar.getDialog().hide();
                    }
                }else if(newState== RecyclerView.SCROLL_STATE_DRAGGING){
                    synchronized (this) {
                        if (countBar == null) {
                            if(recyclerView.getAdapter()!=null) {
                                countBar = CountBar.newInstance(totalRecord);
                                countBar.show(getSupportFragmentManager(), "CountBar");
                            }
                        }
                    }
                }
            }
        });

        init();
    }

    public void init(){
        body = new HashMap<>();
        checkIds=new ArrayList<>();
        entIds=new ArrayList<>();
        onlyLocals = new ArrayList<>();
        if("GWJS".equals(type)) {
            searchBar.setVisibility(View.GONE);
            doctype = getIntent().getStringExtra("doctype");
            json = getIntent().getStringExtra("json");

            dataList = new ArrayList<>();
            textWpicItems = new ArrayList<>();
            textWpicAdapter = new TextWpicAdapter(textWpicItems);
            recycler.setAdapter(textWpicAdapter);
            textWpicAdapter.setOnItemClickListener(new TextWpicAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    //Toast.makeText(getActivity(), "HAHAHA", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecyclerActivity.this, ViewPagerActivity.class);
                    intent.putExtra("title", dataList.get(position).getTitle());
                    intent.putExtra("type", "GWJSXQ");
                    Hawk.put("gwType", doctype);
                    Hawk.put("gwId", dataList.get(position).getDocId());
                    startActivity(intent);
                }
            });

            getGWJSDataList();
        }else if("TSCX".equals(type)||"JBCX".equals(type)){
            title4RowItems=new ArrayList<>();
            title4RowAdapter=new Title4RowAdapter(title4RowItems);
            recycler.setAdapter(title4RowAdapter);
            title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Intent intent=new Intent(RecyclerActivity.this,ViewPagerActivity.class);
                    intent.putExtra("type",type.startsWith("TS")?"TSXQXXLZ":"JBXQXXLZ");
                    intent.putExtra("title",title4RowItems.get(position).getTitle());
                    Hawk.put("TSJBXQ_caseId",title4RowItems.get(position).getTitle());
                    startActivity(intent);
                }
            });
            String infoType=type.startsWith("TS")?"1":"2";
            String basicName=getIntent().getStringExtra("TSJBCX_basicName").equals("")?" ":getIntent().getStringExtra("TSJBCX_basicName");
            String accuseName=getIntent().getStringExtra("TSJBCX_accuseName").equals("")?" ":getIntent().getStringExtra("TSJBCX_accuseName");
            String caseId=getIntent().getStringExtra("TSJBCX_caseId").equals("")?" ":getIntent().getStringExtra("TSJBCX_caseId");
            String pageSize="25";
            Call<Result<List<CaseInfo>>> call=ApiManager.ynApi.searchCase(userId,infoType,basicName,accuseName,caseId,"1",pageSize);
            call.enqueue(new Callback<Result<List<CaseInfo>>>() {
                @Override
                public void onResponse(Response<Result<List<CaseInfo>>> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        if(200==response.body().getCode()){
                            List<CaseInfo> list = response.body().getObject();
                            if (list!=null){
                                if (list.size()!=0){
                                    for (CaseInfo info : list) {
                                        Title4RowItem tmp=new Title4RowItem();
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
                                }else {
                                    layoutSearch.setVisibility(View.GONE);
                                    viewError.setVisibility(View.VISIBLE);
                                }
                            }else {
                                layoutSearch.setVisibility(View.GONE);
                                viewError.setVisibility(View.VISIBLE);
                            }
                        }else {
                            layoutSearch.setVisibility(View.GONE);
                            viewError.setVisibility(View.VISIBLE);
                        }
                    }else {
                        layoutSearch.setVisibility(View.GONE);
                        viewError.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    layoutSearch.setVisibility(View.GONE);
                    viewError.setVisibility(View.VISIBLE);
                }
            });
        }else if("ZTCX".equals(type)){
            getIntent().getStringExtra("name");
            getIntent().getStringExtra("uid");
            getIntent().getStringExtra("register");
            title3RowItems=new ArrayList<>();
            title3RowAdapter=new Title3RowAdapter(title3RowItems);
            recycler.setAdapter(title3RowAdapter);
            for(int i=0; i<10; i++){
                Title4RowItem tmp=new Title4RowItem();
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
        }else if("CCJCCX".equals(type)){
            title4RowItems=new ArrayList<>();
            title4RowAdapter=new Title4RowAdapter(title4RowItems);
            recycler.setAdapter(title4RowAdapter);
            title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Intent intent=new Intent(RecyclerActivity.this,ViewPagerActivity.class);
                    intent.putExtra("type","CCJCCX");
                    intent.putExtra("title","抽查检查查询");
                    Hawk.put("CCJC_CheckId",checkIds.get(position));
                    Hawk.put("CCJC_EntId",entIds.get(position));
                    startActivity(intent);
                }
            });
            getCCJCCX();
        }else if("CCJCDB".equals(type)){
            searchBar.setVisibility(View.VISIBLE);
            title4RowItems=new ArrayList<>();
            title4RowAdapter=new Title4RowAdapter(title4RowItems);
            recycler.setAdapter(title4RowAdapter);
            title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Intent intent=new Intent(RecyclerActivity.this,ViewPagerActivity.class);
                    intent.putExtra("type","CCJCDB");
                    intent.putExtra("title","抽查检查待办");
                    Hawk.put("CCJC_CheckId",checkIds.get(position));
                    Hawk.put("CCJC_EntId",entIds.get(position));
                    Hawk.put("CCJC_OnlyLocal", onlyLocals.get(position));
                    startActivity(intent);
                }
            });
            getCCJCDB();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getCCJCCX(){
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(RecyclerActivity.this);
        body= (HashMap<String, String>) getIntent().getSerializableExtra("body");
        body.put(Constants.PAGE_NO,pageNo+"");
        Call<CCCXResult> call =ApiManager.ccjcApi.ccCX(body);
        call.enqueue(new Callback<CCCXResult>() {
            @Override
            public void onResponse(Response<CCCXResult> response, Retrofit retrofit) {
                dialog.dismiss();
                isLoaded = true;
                pageMax = response.body().getPageCount();
                totalRecord = response.body().getTotalRecord();
                if(response.isSuccess()){
                    CCCXResult result=response.body();
                    if("200".equals(result.getCode())){
                        if(state == Constants.LOAD_REFRESH){
                            title4RowItems.clear();
                            checkIds.clear();
                            entIds.clear();
                            onlyLocals.clear();
                        }
                        for (CCCXResult.Result r :
                                result.getResult()) {
                            Title4RowItem tmp=new Title4RowItem();
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
                        if(title4RowItems.size() == 0) {
                            recycler.setVisibility(View.GONE);
                            viewError.setVisibility(View.VISIBLE);
                        }else {
                            recycler.setVisibility(View.VISIBLE);
                            viewError.setVisibility(View.GONE);
                        }
                        title4RowAdapter.notifyDataSetChanged();
                    }else {
                        viewError.setVisibility(View.VISIBLE);
                        recycler.setVisibility(View.GONE);
                    }
                }else {
                    viewError.setVisibility(View.VISIBLE);
                    recycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(RecyclerActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCCJCDB() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(RecyclerActivity.this);
        body.put("wsCodeReq","000201");
        body.put(Constants.PAGE_NO,pageNo+"");
        body.put("userId",userId);
        if (organId.length()>5)
            body.put("organId",organId.substring(0,6));
        else
            body.put("organId",organId);
        Call<CCToDoResult> call = ApiManager.ccjcApi.ccTodo(body);
        call.enqueue(new Callback<CCToDoResult>() {
            @Override
            public void onResponse(Response<CCToDoResult> response, Retrofit retrofit) {
                dialog.dismiss();
                isLoaded = true;
                pageMax = response.body().getPageCount();
                totalRecord = response.body().getTotalRecord();
                if (response.isSuccess() && response.body()!=null) {
                    CCToDoResult result=response.body();
                    if("200".equals(result.getCode())){
                        if(state == Constants.LOAD_REFRESH){
                            title4RowItems.clear();
                            checkIds.clear();
                            entIds.clear();
                            onlyLocals.clear();
                        }
                        for (CCToDoResult.Result r : result.getResult()) {
                            String checkWayShow = r.getCheckWayShow();
                            if (Constants.SDHC.equals(checkWayShow)){
                                Title4RowItem tmp=new Title4RowItem();
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
                        if(title4RowItems.size() == 0) {
                            recycler.setVisibility(View.GONE);
                            viewError.setVisibility(View.VISIBLE);
                            tvError.setText(Constants.NO_DB);
                        }else {
                            recycler.setVisibility(View.VISIBLE);
                            viewError.setVisibility(View.GONE);
                        }
                        title4RowAdapter.notifyDataSetChanged();
                    }else {
                        recycler.setVisibility(View.GONE);
                        viewError.setVisibility(View.VISIBLE);
                        tvError.setText(Constants.NO_DB);
                    }
                }else {
                    layoutSearch.setVisibility(View.GONE);
                    viewError.setVisibility(View.VISIBLE);
                    tvError.setText(Constants.NO_DB);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                layoutSearch.setVisibility(View.GONE);
                viewError.setVisibility(View.VISIBLE);
                tvError.setText(Constants.NO_DB);
            }
        });
    }

    public void getGWJSDataList(){
        int curSize = textWpicItems.size();
        textWpicItems.clear();
        textWpicAdapter.notifyItemRangeRemoved(0, curSize);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010008");
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("organId", organId);
        if (json.equals("")){
            map.put("condition", "{}");
        }else{
            map.put("condition", json);
        }
        if("收文管理".equals(doctype)) {
            map.put("businessType", "2");
        }else if("发文管理".equals(doctype)) {
            map.put("businessType", "1");
        }
        Call<GwjsBean> call = ApiManager.oaApi.apiGetDocList(map);
        call.enqueue(new Callback<GwjsBean>() {
            @Override
            public void onResponse(Response<GwjsBean> response, Retrofit retrofit) {
                if(response.body()!=null) {
                    if (response.body().getResult() != null) {
                        dataList = response.body().getResult().getResult();
                        totalRecord = response.body().getResult().getTotalRecord();
                        if(dataList!=null) {
                            for (int i = 0; i < dataList.size(); i++) {
                                TextWpicItem item = new TextWpicItem();
                                item.setTitle(dataList.get(i).getTitle());
                                item.setRowTwoText(dataList.get(i).getCreateAt());
                                item.setPicId(R.mipmap.icons_normal);
                                item.setPicText(dataList.get(i).getType());
                                textWpicItems.add(item);
                            }
                            textWpicAdapter.notifyItemRangeInserted(0,textWpicItems.size());
                        }
                    }else{
                        Toast.makeText(RecyclerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RecyclerActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.searchbtn)
    public void onClick(View v){
        String searchStr = searchTxt.getText().toString().trim();
        if (body !=null && !"".equals(searchStr)){
            body.put("entNameRegNoUniScid", searchStr);
            body.put(Constants.PAGE_NO,"1");
        }

        state = Constants.LOAD_REFRESH;
        getCCJCDB();
    }
}
