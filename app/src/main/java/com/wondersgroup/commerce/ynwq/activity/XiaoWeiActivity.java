package com.wondersgroup.commerce.ynwq.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.ynwq.adapter.XiaoWeiAdapter;
import com.wondersgroup.commerce.ynwq.bean.XiaoWeiResult;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class XiaoWeiActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)Toolbar toolbar;
    @BindView(R.id.toolbar_title)TextView title;
    @BindView(R.id.xiaowei_recycler)RecyclerView recycler;
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
            body.put("wsCodeReq","01100001");
            Call<XiaoWeiResult> call = ApiManager.ynWqApi.getSupport(body);
            call.enqueue(new Callback<XiaoWeiResult>() {
                @Override
                public void onResponse(Response<XiaoWeiResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XiaoWeiResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            adapter = new XiaoWeiAdapter(result.getResult());
                            recycler.setAdapter(adapter);
                        } else if ("403".equals(result.getResultCode())) {

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
            body.put("wsCodeReq","01100001");
            Call<XiaoWeiResult> call=ApiManager.ynWqApi.getEntInfo(body);
            Log.e("123","");
            call.enqueue(new Callback<XiaoWeiResult>() {
                @Override
                public void onResponse(Response<XiaoWeiResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XiaoWeiResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            adapter = new XiaoWeiAdapter(result.getResult());
                            recycler.setAdapter(adapter);
                        } else if ("403".equals(result.getResultCode())) {

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
