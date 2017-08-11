package com.wondersgroup.commerce.teamwork.wywork.ydjyxz;

import android.content.Context;
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
import com.wondersgroup.commerce.teamwork.myxqzgdq.DetailPersonActivity;
import com.wondersgroup.commerce.teamwork.myxqzgdq.XqzgdqActivity;
import com.wondersgroup.commerce.teamwork.wywork.jcjgno.bean.JcjgbzcList;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JyqxdqGlxxAdapter;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.PullToRefreshLayout;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.QYXXActivity;
import com.wondersgroup.commerce.teamwork.wywork.ydjyxz.bean.YDJYAddBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class YDJYXZListActivity extends AppCompatActivity{

    public static int pageNo = 1;
    private YDJYAddBean ydjyAddBean;
    private JyqxdqGlxxAdapter adapter;
    private ListView listView;

    private String name;
    private String zch;
    private int type;

    private Context mContext;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_jyqxdq);

        ((PullToRefreshLayout) findViewById(R.id.refresh_view))
                .setOnRefreshListener(new MyListenerRefresh());

        mContext    =   YDJYXZListActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        type = Integer.parseInt(getIntent().getStringExtra("type"));
        name = getIntent().getStringExtra("name");
        zch = getIntent().getStringExtra("zch");
        if(type==0) {
            toolbarText.setText("异地经营企业列表");
        }else if(type==1){
            toolbarText.setText("异地经营个人列表");
        }


        listView = (ListView) findViewById(R.id.content_view);
        initListView();

        initListListener();

    }

    private void initListListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3){

                initList(arg2);
                String entityId = ydjyAddBean.getValues().getResult().get(arg2).getEtpsId();
                Intent i = new Intent(mContext,YDJYContentActivity.class);
                i.putStringArrayListExtra("list", list);
                i.putExtra("entityId", entityId);
                i.putExtra("type", type+"");
                startActivity(i);
            }
        });
    }

    private void initList(int position) {
        list    =   new ArrayList<>();
        list.add(ydjyAddBean.getValues().getResult().get(position).getEtpsName());//企业名称
        list.add(ydjyAddBean.getValues().getResult().get(position).getRegNo());//注册号
        list.add(ydjyAddBean.getValues().getResult().get(position).getSubObjId());//企业类型
        list.add("");//所属经济小区
        list.add(ydjyAddBean.getValues().getResult().get(position).getAcceptOrganId());//所属分局
        list.add(ydjyAddBean.getValues().getResult().get(position).getAreaOrganId());//属地工商所
        list.add("");//乡镇街道
        list.add(ydjyAddBean.getValues().getResult().get(position).getAddress());//地址
    }


    private void initListView() {

        MyProgressDialog.show(mContext);
        Call<YDJYAddBean> call  =   null;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNo", +pageNo + "");
        map.put("unitName", name);
        map.put("regNo", zch);
        map.put("preciseUnitName", +1 + "");
        if(type==0){
            map.put("unitType","02");
            call = ApiManager.shApi.queryYDJYqy(map);
        }else if(type==1){
            map.put("unitType","06");
//            Call<YDJYAddBean> call = ApiManager.shApi.queryYDJYqy(map);
        }

        call.enqueue(new Callback<YDJYAddBean>() {
            @Override
            public void onResponse(Response<YDJYAddBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    ydjyAddBean = response.body();
                    init();
                } else {
                    Toast.makeText(mContext, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(mContext, getResources().getString(R.string.error_connect)+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {

        //异地经营列表
        ArrayList<ArrayList<String>> ass    =   new ArrayList<>();
        for (int i=0;i< ydjyAddBean.getValues().getResult().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("企业名称" + "##" + ydjyAddBean.getValues().getResult().get(i).getEtpsName());
            as.add("注册号" + "##" + ydjyAddBean.getValues().getResult().get(i).getRegNo());
            as.add("所属工商局" + "##" + ydjyAddBean.getValues().getResult().get(i).getAreaOrganId());
            as.add(i+"");
            ass.add(as);
        }

        adapter = new JyqxdqGlxxAdapter(ass,mContext);
        listView.setAdapter(adapter);
        if (ydjyAddBean.getValues().getResult().size() > (pageNo-1)*20) {
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
                MyProgressDialog.show(mContext);
                Call<YDJYAddBean> call  =   null;
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("pageNo", ++pageNo + "");
                map.put("unitName", name);
                map.put("regNo", zch);
                map.put("preciseUnitName", +1 + "");
                if(type==0){
                    map.put("unitType","02");
                    call = ApiManager.shApi.queryYDJYqy(map);
                }else if(type==1){
                    map.put("unitType","06");
//            Call<YDJYAddBean> call = ApiManager.shApi.queryYDJYqy(map);
                }
                call.enqueue(new retrofit.Callback<YDJYAddBean>() {
                    @Override
                    public void onResponse(Response<YDJYAddBean> response, Retrofit retrofit) {
                        MyProgressDialog.dismiss();
                        if (response.isSuccess()) {
                            YDJYAddBean ydjyAddBeanTemp = response.body();
                            for (int i = 0; i < ydjyAddBeanTemp.getValues().getResult().size(); i++) {
                                ydjyAddBean.getValues().getResult().add(ydjyAddBeanTemp.getValues().getResult().get(i));
                            }
                            init();
                            if (ydjyAddBean.getValues().getResult().size() > (pageNo - 1) * 20) {
                                listView.setSelection((pageNo - 1) * 20);
                            }
                        } else {
                            MyProgressDialog.dismiss();
                            Toast.makeText(mContext, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        MyProgressDialog.dismiss();
                        Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
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
