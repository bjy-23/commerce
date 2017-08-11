package com.wondersgroup.commerce.ynwq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.commerce.ynwq.adapter.TongJiAdapter;
import com.wondersgroup.commerce.ynwq.bean.StatItem;
import com.wondersgroup.commerce.ynwq.bean.StatResult;
import com.wondersgroup.commerce.ynwq.widget.CountBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TJDetailActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.tjdetail_recycler)RecyclerView recycler;
    @Bind(R.id.net_area)RelativeLayout netArea;
    @Bind(R.id.net_msg)TextView netMsg;

    private TongJiAdapter adapter;
    private List<StatItem> items;
    private Map<String,String> body;

    private int total=0;
    private CountBar countBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjdetail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("统计信息");
        body= (HashMap<String, String>) getIntent().getSerializableExtra("body");
        recycler.setHasFixedSize(true);
        final LinearLayoutManager manager=new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.setItemAnimator(new SlideInUpAnimator());
        adapter=new TongJiAdapter();
        items=new ArrayList<>();
        adapter.setItems(items);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
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
                            countBar.setCur(manager.findLastCompletelyVisibleItemPosition() + 1);
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
                if(newState== RecyclerView.SCROLL_STATE_IDLE){
                    if(countBar.isVisible())countBar.getDialog().hide();
                }else if(newState== RecyclerView.SCROLL_STATE_DRAGGING){
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

        netArea.setVisibility(View.VISIBLE);
        netMsg.setText("加载中...");
        init();
    }

    public void init(){
        body.put("pageNo","0");
        body.put("wsCodeReq","01100001");
        Call<StatResult> call= ApiManager.ynWqApi.getStatList(body);
        call.enqueue(new Callback<StatResult>() {
            @Override
            public void onResponse(Response<StatResult> response, Retrofit retrofit) {
                if(response.isSuccess()&&response.body()!=null){
                    StatResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        netArea.setVisibility(View.GONE);
                        items.addAll(result.getResult());
                        adapter.notifyItemRangeInserted(0, items.size());
                        setTotal(result.getTotalRecord());
                    }else {
                        netMsg.setText(result.getMessage());
                    }
                }else {
                    netMsg.setText("连接服务器出错");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                netMsg.setText("网络错误");
            }
        });
    }
    public void fetchData(int page){
        body.put("pageNo",""+page);
        Call<StatResult> call=ApiManager.ynWqApi.getStatList(body);
        call.enqueue(new Callback<StatResult>() {
            @Override
            public void onResponse(Response<StatResult> response, Retrofit retrofit) {
                if(response.isSuccess()&&response.body()!=null){
                    StatResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        int curSize=adapter.getItemCount();
                        items.addAll(result.getResult());
                        adapter.notifyItemRangeInserted(curSize, result.getResult().size());
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(countBar!=null&&countBar.isVisible()){
            countBar.getDialog().hide();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void setTotal(String totalRecord){
        try {
            total=Integer.parseInt(totalRecord);
        }catch (Exception e){

        }
    }
}
