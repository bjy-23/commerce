package com.wondersgroup.yngs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.adapter.XiaoWeiAdapter;
import com.wondersgroup.yngs.entity.XiaoWeiResult;
import com.wondersgroup.yngs.service.ApiManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class XiaoWeiActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.xiaowei_recycler)RecyclerView recycler;
    XiaoWeiAdapter adapter;
    String supportId;
    String etpsId;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_wei);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        //supportId=getIntent().getStringExtra("supportId");
        type=getIntent().getStringExtra("type");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        fetchData();
    }
    public void fetchData(){
        if("WQFCCX".equals(type)) {
            supportId=getIntent().getStringExtra("supportId");
            title.setText("扶持情况");
            Map<String, String> body = new HashMap<>();
            body.put("supportId", supportId);
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<XiaoWeiResult> call = ApiManager.yunNanApi.getSupport(body);
            call.enqueue(new Callback<XiaoWeiResult>() {
                @Override
                public void onResponse(Response<XiaoWeiResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XiaoWeiResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            adapter = new XiaoWeiAdapter(result.getResult());
                            recycler.setAdapter(adapter);
                        } else if ("403".equals(result.getResultCode())) {
                            new MaterialDialog.Builder(XiaoWeiActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .cancelable(false)
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp = getSharedPreferences("Default", MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(XiaoWeiActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else if("WQCX".equals(type)){
            etpsId=getIntent().getStringExtra("etpsId");
            title.setText("基本信息");
            Map<String,String> body = new HashMap<>();
            body.put("etpsId",etpsId);
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<XiaoWeiResult> call=ApiManager.yunNanApi.getEntInfo(body);
            call.enqueue(new Callback<XiaoWeiResult>() {
                @Override
                public void onResponse(Response<XiaoWeiResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XiaoWeiResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            adapter = new XiaoWeiAdapter(result.getResult());
                            recycler.setAdapter(adapter);
                        } else if ("403".equals(result.getResultCode())) {
                            new MaterialDialog.Builder(XiaoWeiActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .cancelable(false)
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp = getSharedPreferences("Default", MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(XiaoWeiActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
