package com.wondersgroup.commerce.teamwork.wywork.jyqx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class JYQXDQActivity extends AppCompatActivity{

    public static int pageNo = 1;
    private JyqxdqBean jyqxdqBean;
    private JyqxdqGlxxAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_jyqxdq);

        ((PullToRefreshLayout) findViewById(R.id.refresh_view))
                .setOnRefreshListener(new MyListenerRefresh());

        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        // toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarText.setText("经营期限到期企业列表");


        listView = (ListView) findViewById(R.id.content_view);
        initListView();

        initListListener();

    }

    private void initListListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3){
                MyProgressDialog.show(JYQXDQActivity.this);

                String entityId = jyqxdqBean.getValues().getResult().get(arg2).getEtpsId();
                Intent i = new Intent(JYQXDQActivity.this,QYXXActivity.class);
                i.putExtra("entityId", entityId);
                startActivity(i);
                MyProgressDialog.dismiss();
            }
        });
    }


    private void initListView() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNo", +pageNo + "");
        MyProgressDialog.show(JYQXDQActivity.this);
        Call<JyqxdqBean> call = ApiManager.shApi.jyqxdq_index(map);
        call.enqueue(new Callback<JyqxdqBean>() {
            @Override
            public void onResponse(Response<JyqxdqBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    jyqxdqBean = response.body();
                    init();
                } else {
                    MyProgressDialog.dismiss();
                    Toast.makeText(JYQXDQActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(JYQXDQActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void init() {

        //检查结果不正常企业列表
        ArrayList<ArrayList<String>> ass    =   new ArrayList<>();
        for (int i=0;i< jyqxdqBean.getValues().getResult().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("企业名称" + "##" + jyqxdqBean.getValues().getResult().get(i).getEtpsName());
            as.add("经营期限" + "##" + jyqxdqBean.getValues().getResult().get(i).getTradeEndDate());
            as.add("巡查日期" + "##" + jyqxdqBean.getValues().getResult().get(i).getCheckDate());
            as.add(i+"");
            ass.add(as);
        }

        adapter = new JyqxdqGlxxAdapter(ass,
                JYQXDQActivity.this);
        listView.setAdapter(adapter);
        if (jyqxdqBean.getValues().getResult().size() > (pageNo-1)*20) {
            listView.setSelection( (pageNo-1)*20);
        }
    }

    class MyListenerRefresh implements PullToRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
        {
            // 下拉刷新操作
            pageNo  =   1;
            initListView();
            // 千万别忘了告诉控件刷新完毕了哦！
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
        {
            // 加载操作
            {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("pageNo", ++pageNo + "");
                MyProgressDialog.show(JYQXDQActivity.this);
                Call<JyqxdqBean> call = ApiManager.shApi.jyqxdq_index(map);
                call.enqueue(new retrofit.Callback<JyqxdqBean>() {
                    @Override
                    public void onResponse(Response<JyqxdqBean> response, Retrofit retrofit) {
                        MyProgressDialog.dismiss();
                        if (response.isSuccess()) {
                            JyqxdqBean jyqxdqBeanTemp = response.body();
                            for (int i=0;i<jyqxdqBeanTemp.getValues().getResult().size();i++){
                                jyqxdqBean.getValues().getResult().add(jyqxdqBeanTemp.getValues().getResult().get(i));
                            }
                            init();
                            if (jyqxdqBean.getValues().getResult().size() > (pageNo-1)*20) {
                                listView.setSelection( (pageNo-1)*20);
                            }
                        } else {
                            MyProgressDialog.dismiss();
                            Toast.makeText(JYQXDQActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        MyProgressDialog.dismiss();
                        Toast.makeText(JYQXDQActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                    }
                });
            }




            // 千万别忘了告诉控件加载完毕了哦！
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
