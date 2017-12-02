package com.wondersgroup.commerce.law_rule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.law_rule.bean.LawBean;
import com.wondersgroup.commerce.law_rule.bean.LawDetailsBean;
import com.wondersgroup.commerce.law_rule.bean.LawTypeBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.CallAdapter;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LawQueryActivity extends AppCompatActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.layout_error)
    View layoutError;

    private LawQueryAdapter mAdapter;
    private List<LawBean> mData;
    private String type;
    private HashMap hashMap;
    private int pageNo = 1, pageMax = 1;//分页查找：当前页、最大页
    private boolean isLoading;//true:如果正在加载中：取消互动加载更多

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_query);

        ButterKnife.bind(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawQueryActivity.this, LawSearchActivity.class);
                startActivity(intent);
            }
        });

        hashMap = new HashMap();
        type = getIntent().getStringExtra(Constants.TYPE);
        mData = new ArrayList<>();
        mAdapter = new LawQueryAdapter(this, mData);
        recyclerView.setAdapter(mAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ((linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1) &&
                        (pageNo < pageMax) && isLoading) {
                    int a = linearLayoutManager.findLastVisibleItemPosition();
                    int b = linearLayoutManager.getItemCount();
                    isLoading = false;
                    pageNo++;
                    hashMap.put("currentPage", pageNo);
                    switch (type) {
                        case Constants.LAW:
                            getDataLaw();
                            break;
                        case Constants.T:
                            getDataT();
                            break;
                        case Constants.K:
                            getDataT();
                            break;
                    }
                }
            }
        });
        switch (type) {
            case Constants.TYPE:
                tvTitle.setText("案件法律法规");
                imgSearch.setVisibility(View.VISIBLE);
                imgSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LawQueryActivity.this, LawSearchActivity.class));
                    }
                });
                getData();
                break;
            case Constants.LAW:
                LawTypeBean beanLaw = getIntent().getParcelableExtra(Constants.BEAN);
                tvTitle.setText(beanLaw.getTypeName());
                hashMap.put("wsCodeReq", "03010301");
                hashMap.put("currentPage", pageNo);
                hashMap.put("lawType", beanLaw.getTypeId());
                getDataLaw();
                break;
            case Constants.T:
                LawTypeBean beanT = getIntent().getParcelableExtra(Constants.BEAN);
                tvTitle.setText(beanT.getTypeName());
                hashMap.put("wsCodeReq", "03010302");
                hashMap.put("currentPage", pageNo);
                hashMap.put("lawCode", beanT.getTypeId());
                getDataT();
                break;
            case Constants.K:
                LawDetailsBean lawDetailsBean = getIntent().getParcelableExtra(Constants.BEAN);
                tvTitle.setText(lawDetailsBean.getDetailContent());
                hashMap.put("wsCodeReq", "03010302");
                hashMap.put("currentPage", pageNo);
                hashMap.put("lawCode", lawDetailsBean.getDetailCode());
                getDataT();
                break;
            case Constants.X:
                break;
            case Constants.M:
                break;
            case Constants.QUERY_NAME:
                tvTitle.setText("案件法律法规");
                hashMap.put(Constants.LAW_NAME, getIntent().getStringExtra(Constants.LAW_NAME));
                getLawByName();
        }

    }

    private void getData() {
        hashMap.put("wsCodeReq", "03010303");
        Call<Result<LinkedHashMap>> call = ApiManager.caseApi.lawTypeQuery(hashMap);
        call.enqueue(new Callback<Result<LinkedHashMap>>() {
            @Override
            public void onResponse(Response<Result<LinkedHashMap>> response, Retrofit retrofit) {
                if (response.body() != null && response.body().getObject() != null) {
                    LinkedHashMap linkedHashMap = response.body().getObject();
                    Hawk.put(Constants.LAW_TYPE, linkedHashMap);
                    Iterator iterator = linkedHashMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        LawTypeBean lawTypeBean = new LawTypeBean();
                        lawTypeBean.setTypeId(entry.getKey().toString());
                        lawTypeBean.setTypeName(entry.getValue().toString());
                        lawTypeBean.setType(0);
                        mData.add(lawTypeBean);
                    }
                    mAdapter.notifyDataSetChanged();
                    mAdapter.addFootView();
                }else {
                    layoutError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getDataLaw() {
        Call<Result<List<LawTypeBean>>> call1 = ApiManager.caseApi.lawQuery(hashMap);
        call1.enqueue(new Callback<Result<List<LawTypeBean>>>() {
            @Override
            public void onResponse(Response<Result<List<LawTypeBean>>> response, Retrofit retrofit) {
                if (response.body() != null && response.body().getObject() != null) {
                    isLoading = true;
                    pageMax = response.body().getPageCount();
                    ArrayList<LawTypeBean> lawList = (ArrayList<LawTypeBean>) response.body().getObject();
                    for (LawTypeBean lawTypeBean : lawList) {
                        lawTypeBean.setType(1);
                    }
                    mData.addAll(lawList);
                    mAdapter.notifyDataSetChanged();
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

    private void getDataT() {
        Call<Result<List<LawDetailsBean>>> call = ApiManager.caseApi.detailsQuery(hashMap);
        call.enqueue(new Callback<Result<List<LawDetailsBean>>>() {
            @Override
            public void onResponse(Response<Result<List<LawDetailsBean>>> response, Retrofit retrofit) {
                if (response.body() != null && response.body().getObject() != null) {
                    isLoading = true;
                    pageMax = response.body().getPageCount();
                    ArrayList<LawDetailsBean> list = (ArrayList<LawDetailsBean>) response.body().getObject();
                    for (LawDetailsBean lawDetailsBean : list) {
                        lawDetailsBean.setType(2);
                    }
                    mData.addAll(list);
                    mAdapter.notifyDataSetChanged();
                }else {
                    if (mData.size() == 0)//考虑到第一页有数据，剩下页无数据
                        layoutError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (mData.size() == 0)
                    layoutError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getLawByName() {
        hashMap.put("wsCodeReq", "03010304");
        Call<Result<List<LawTypeBean>>> call = ApiManager.caseApi.lawNameQuery(hashMap);
        call.enqueue(new Callback<Result<List<LawTypeBean>>>() {
            @Override
            public void onResponse(Response<Result<List<LawTypeBean>>> response, Retrofit retrofit) {
                if (response.body() != null && response.body().getObject() != null && response.body().getObject().size() != 0) {
                    ArrayList<LawTypeBean> arrayList = (ArrayList<LawTypeBean>) response.body().getObject();
                    ArrayList<LawTypeBean> data = new ArrayList<LawTypeBean>();
                    String lawType = "";
                    HashMap linkedHashMap = Hawk.get(Constants.LAW_TYPE);
                    for (int i = 0; i < arrayList.size(); i++) {
                        LawTypeBean lawTypeBean = arrayList.get(i);
                        lawTypeBean.setType(1);
                        if (!lawType.equals(lawTypeBean.getLawType())) {
                            lawType = lawTypeBean.getLawType();
                            LawTypeBean bean = new LawTypeBean();
                            bean.setHead(true);
                            bean.setTypeName(linkedHashMap.get(lawType).toString());
                            bean.setType(3);
                            data.add(bean);
                        }
                        data.add(lawTypeBean);
                    }
                    mData.addAll(data);
                    mAdapter.notifyDataSetChanged();
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
}
