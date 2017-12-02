package com.wondersgroup.yngs.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.adapter.FuchiAdapter;
import com.wondersgroup.yngs.adapter.JinduAdapter;
import com.wondersgroup.yngs.adapter.XiaoWeiComAdapter;
import com.wondersgroup.yngs.entity.FuchiItem;
import com.wondersgroup.yngs.entity.FuchiResult;
import com.wondersgroup.yngs.entity.JinduItem;
import com.wondersgroup.yngs.entity.JinduResult;
import com.wondersgroup.yngs.entity.XwComItem;
import com.wondersgroup.yngs.entity.XwComResult;
import com.wondersgroup.yngs.service.ApiManager;
import com.wondersgroup.yngs.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.yngs.widget.CountBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class QueryActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.query_recycler)RecyclerView recycler;
    @Bind(R.id.net_area)RelativeLayout netArea;
    @Bind(R.id.net_msg)TextView netMsg;

    private String type;
    private String deptId;
    private String organId;
    private Map<String,String> body;
    private CountBar countBar;

    private int total=0;


    private JinduAdapter jinduAdapter;
    private List<JinduItem> jinduItems;
    private XiaoWeiComAdapter xiaoWeiComAdapter;
    private List<XwComItem> xwComItems;
    private FuchiAdapter fuchiAdapter;
    private List<FuchiItem> fuchiItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
        int selection=sp.getInt("selection",-1);
        if(selection!=-1){
            deptId=sp.getString("deptId","").split(",")[selection];
            organId=sp.getString("organId","").split(",")[selection];
        }
        type=getIntent().getStringExtra("type");
        body= (HashMap<String, String>) getIntent().getSerializableExtra("body");
        body.put("deptId", deptId);
        body.put("organId", organId);
        body.put("wsCodeReq", ApiManager.getWsCodeReq());
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        init();
        //countBar=CountBar.newInstance(0);
        recycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchData(page);
            }
        });
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(countBar!=null) {
                    if (dy > 0) {
                        //if(!countBar.isVisible()&&!countBar.isAdded())countBar.show(getSupportFragmentManager(),"CountBar");
                        if (countBar.getDialog() != null) countBar.getDialog().show();
                        if (countBar.isVisible()) {
                            countBar.setCur(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                            if (total == 0)
                                countBar.setTotal(recyclerView.getAdapter().getItemCount());
                            else countBar.setTotal(total);
                        }
                    } else {
                        if (countBar.isVisible()) countBar.getDialog().hide();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(countBar!=null) {
                        if (countBar.isVisible()) countBar.getDialog().hide();
                    }
                }else if(newState==RecyclerView.SCROLL_STATE_DRAGGING){
                    synchronized (this) {
                        if (countBar == null) {
                            if(recyclerView.getAdapter()!=null) {
                                countBar = CountBar.newInstance(total == 0 ? recyclerView.getAdapter().getItemCount() : total);
                                countBar.show(getSupportFragmentManager(), "CountBar");
                            }
                        }
                    }
                }
            }
        });
    }

    public void init(){
        netArea.setVisibility(View.VISIBLE);
        netMsg.setText("加载中...");
        if("JDCX".equals(type)){
            title.setText(R.string.jdcx_title);
            body.put("pageNo", "0");
            Call<JinduResult> call=ApiManager.yunNanApi.getProgress(body);
            call.enqueue(new Callback<JinduResult>() {
                @Override
                public void onResponse(Response<JinduResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        JinduResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            netArea.setVisibility(View.GONE);
                            jinduItems=result.getResult();
                            jinduAdapter=new JinduAdapter(jinduItems);
                            recycler.setAdapter(jinduAdapter);

                            setTotal(result.getTotalRecord());

                        }else if("403".equals(result.getResultCode())) {
                            netMsg.setText(result.getMessage());
                            new MaterialDialog.Builder(QueryActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(QueryActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .cancelable(false)
                                    .show();
                        }else {
                            netMsg.setText(result.getMessage());
                        }
                    }else {
                        netMsg.setText("与服务器连接失败");
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    netMsg.setText("网络出错");
                }
            });
        }else if("WQCX".equals(type)){
            title.setText(R.string.wqcx_title);
            body.put("pageNo", "0");
            Call<XwComResult> call=ApiManager.yunNanApi.getSupportInfoList(body);
            call.enqueue(new Callback<XwComResult>() {
                @Override
                public void onResponse(Response<XwComResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XwComResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            netArea.setVisibility(View.GONE);
                            xwComItems = result.getResult();
                            xiaoWeiComAdapter = new XiaoWeiComAdapter(xwComItems);
                            recycler.setHasFixedSize(true);
                            recycler.setAdapter(xiaoWeiComAdapter);
                            xiaoWeiComAdapter.setOnItemClickListener(new XiaoWeiComAdapter.OnItemClickListener() {
                                @Override
                                public void OnItemClick(View view, int position) {
                                    /*Intent intent = new Intent(QueryActivity.this, ViewPagerActivity.class);
                                    intent.putExtra("type", type);
                                    intent.putExtra("etpsId",xwComItems.get(position).getEtpsId());
                                    intent.putExtra("meId",xwComItems.get(position).getMeId());
                                    startActivity(intent);*/
                                    Intent intent = new Intent(QueryActivity.this, XiaoWeiActivity.class);
                                    intent.putExtra("type", type);
                                    intent.putExtra("etpsId", xwComItems.get(position).getEtpsId());
                                    startActivity(intent);
                                }
                            });

                            setTotal(result.getTotalRecord());
                        }else if("403".equals(result.getResultCode())) {
                            netMsg.setText(result.getMessage());
                            new MaterialDialog.Builder(QueryActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(QueryActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .cancelable(false)
                                    .show();
                        }else {
                            netMsg.setText(result.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    netMsg.setText("网络错误");
                }
            });
        }else if("WQFCCX".equals(type)){
            title.setText(R.string.fccx_title);
            body.put("pageNo","0");
            Call<FuchiResult> call=ApiManager.yunNanApi.getSupportList(body);
            call.enqueue(new Callback<FuchiResult>() {
                @Override
                public void onResponse(Response<FuchiResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        FuchiResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            netArea.setVisibility(View.GONE);
                            fuchiItems=result.getResult();
                            fuchiAdapter=new FuchiAdapter(fuchiItems);
                            recycler.setAdapter(fuchiAdapter);
                            fuchiAdapter.setOnItemClickListener(new FuchiAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {
                                    Intent intent = new Intent(QueryActivity.this, XiaoWeiActivity.class);
                                    intent.putExtra("type", type);
                                    intent.putExtra("supportId", fuchiItems.get(position).getSupportId());
                                    startActivity(intent);
                                }
                            });
                            setTotal(result.getTotalRecord());
                        }else if("403".equals(result.getResultCode())) {
                            netMsg.setText(result.getMessage());
                            new MaterialDialog.Builder(QueryActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(QueryActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .cancelable(false)
                                    .show();
                        }else {
                            netMsg.setText(result.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    netMsg.setText("网络错误");
                }
            });
        }
    }
    public void fetchData(int page){
        if("JDCX".equals(type)){
            body.put("pageNo",""+page);
            Call<JinduResult> call=ApiManager.yunNanApi.getProgress(body);
            call.enqueue(new Callback<JinduResult>() {
                @Override
                public void onResponse(Response<JinduResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        JinduResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            int curSize=jinduAdapter.getItemCount();
                            jinduItems.addAll(result.getResult());
                            jinduAdapter.notifyItemRangeInserted(curSize,result.getResult().size());
                        }else if("403".equals(result.getResultCode())) {
                            new MaterialDialog.Builder(QueryActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(QueryActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .cancelable(false)
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else if("WQCX".equals(type)){
            body.put("pageNo",""+page);
            Call<XwComResult> call=ApiManager.yunNanApi.getSupportInfoList(body);
            call.enqueue(new Callback<XwComResult>() {
                @Override
                public void onResponse(Response<XwComResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XwComResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            int curSize = xiaoWeiComAdapter.getItemCount();
                            xwComItems.addAll(result.getResult());
                            xiaoWeiComAdapter.notifyItemRangeInserted(curSize, result.getResult().size());
                        }else if("403".equals(result.getResultCode())) {
                            new MaterialDialog.Builder(QueryActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(QueryActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .cancelable(false)
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure (Throwable t){

                }
            });
        }else if("WQFCCX".equals(type)){
            body.put("pageNo",""+page);
            Call<FuchiResult> call=ApiManager.yunNanApi.getSupportList(body);
            call.enqueue(new Callback<FuchiResult>() {
                @Override
                public void onResponse(Response<FuchiResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        FuchiResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            int curSize=fuchiAdapter.getItemCount();
                            fuchiItems.addAll(result.getResult());
                            fuchiAdapter.notifyItemRangeInserted(curSize, result.getResult().size());
                        }else if("403".equals(result.getResultCode())) {
                            new MaterialDialog.Builder(QueryActivity.this)
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(QueryActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .cancelable(false)
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
    @Override
    protected void onResume() {
        super.onResume();
        if(countBar!=null&&countBar.isVisible()){
            countBar.getDialog().hide();
        }
    }

    public void setTotal(String totalRecord){
        try {
            total=Integer.parseInt(totalRecord);
        }catch (Exception e){

        }
    }
}
