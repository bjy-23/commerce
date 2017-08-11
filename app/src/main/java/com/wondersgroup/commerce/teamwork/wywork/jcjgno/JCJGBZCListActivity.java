package com.wondersgroup.commerce.teamwork.wywork.jcjgno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.myxqzgdq.DetailPersonActivity;
import com.wondersgroup.commerce.teamwork.myxqzgdq.XqzgdqActivity;
import com.wondersgroup.commerce.teamwork.wywork.jcjgno.bean.JcjgbzcList;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JyqxdqGlxxAdapter;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.PullToRefreshLayout;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.QYXXActivity;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class JCJGBZCListActivity extends AppCompatActivity{

    private ListView listView;
    private int type;
    private String startDate;
    private String endDate;
    private JcjgbzcList jcjgbzcList;
    private JyqxdqGlxxAdapter   adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_jcjgbzc);


        type = Integer.parseInt(this.getIntent().getStringExtra("jcjgbzc_type"));
        startDate   =   this.getIntent().getStringExtra("checkDateBegin");
        endDate   =   this.getIntent().getStringExtra("checkDateEnd");

        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        // toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        if(type==0) {
            toolbarText.setText("检查结果不正常企业列表");
        }else if(type==1){
            toolbarText.setText("检查结果不正常个人列表");
        }

        listView = (ListView) findViewById(R.id.content_view);
        initListView();
        initListListener();
    }

    private void initListListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                MyProgressDialog.show(JCJGBZCListActivity.this);
                Intent i    =   null;
                if(type==0){

                    i = new Intent(JCJGBZCListActivity.this, QYXXActivity.class);
                }else if(type==1){
                    XqzgdqActivity.setCurrTab(1);
                    i = new Intent(JCJGBZCListActivity.this, DetailPersonActivity.class);
                }
                String entityId = jcjgbzcList.getValues().getResult().get(arg2).getEntityId();
                i.putExtra("entityId", entityId);
                startActivity(i);
                MyProgressDialog.dismiss();
            }
        });
    }


    private void initListView() {
        HashMap<String, String> map = new HashMap<String, String>();
        MyProgressDialog.show(JCJGBZCListActivity.this);
        Call<JcjgbzcList> call  =   null;
        if(type==0) {
            map.put("checkDateBegin", startDate);
            map.put("checkDateEnd", endDate);
            call = ApiManager.shApi.queryJCJGBZCqy(map);
        }else if(type==1){
            map.put("begin", startDate);
            map.put("end", endDate);
            call = ApiManager.shApi.queryJCJGBZCgr(map);
        }
        call.enqueue(new Callback<JcjgbzcList>() {
            @Override
            public void onResponse(Response<JcjgbzcList> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    jcjgbzcList = response.body();
                    init();
                } else {
                    MyProgressDialog.dismiss();
                    Toast.makeText(JCJGBZCListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(JCJGBZCListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void init() {
        if(true){
            //检查结果不正常企业列表
            ArrayList<ArrayList<String>> ass    =   new ArrayList<>();
            for (int i=0;i< jcjgbzcList.getValues().getResult().size();i++){
                ArrayList<String> as    =   new ArrayList<>();
                as.add("企业名称" + "##" + jcjgbzcList.getValues().getResult().get(i).getName());
                as.add("管理目的" + "##" + jcjgbzcList.getValues().getResult().get(i).getManageDes());
                as.add("检查日期" + "##" + jcjgbzcList.getValues().getResult().get(i).getCheckDate());
                as.add("检查结果" + "##" + jcjgbzcList.getValues().getResult().get(i).getCheckResult());
                as.add(i+"");
                ass.add(as);
            }
            adapter =   new JyqxdqGlxxAdapter(ass,JCJGBZCListActivity.this);
            listView.setAdapter(adapter);
//            if (jcjgbzcQy.getValues().getResult().size() > (pageNo-1)*20) {
//                listView.setSelection( (pageNo-1)*20);
//            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
