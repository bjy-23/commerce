package com.wondersgroup.commerce.teamwork.myrcxc;

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
import com.wondersgroup.commerce.model.SerializableMap;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.myxqzgdq.DetailCompanyActivity;
import com.wondersgroup.commerce.teamwork.myxqzgdq.DetailPersonActivity;
import com.wondersgroup.commerce.teamwork.myxqzgdq.PullToRefreshLayout;
import com.wondersgroup.commerce.teamwork.myxqzgdq.XqzgdqGlxxAdapter;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RCXCDetailActivity extends AppCompatActivity {

    private int currTab;
    private Map<String, String> map;
    private ArrayList<ArrayList<String>> datas;
    private Context context;
    private RCXCCompanyBean companyBean;
    private RCXCPersonBean personBean;
    private int pageNo = 1;

    private ListView listView;
    private PullToRefreshLayout pullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcxc_detail);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarText.setText("日常巡查列表");

        SerializableMap serializableMap = (SerializableMap) getIntent().getExtras().getSerializable("para");
        map = serializableMap.getMap();
        currTab = Integer.parseInt(getIntent().getStringExtra("currTab"));


        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        pullToRefreshLayout.setOnRefreshListener(new MyListener());

        listView = (ListView) findViewById(R.id.content_view);

        if (currTab == 0) {
            initData();
        } else {
            initPersonData();
        }


    }

    private void initPersonData() {
        pageNo = 1;
        MyProgressDialog.show(context);
        map.put("pageNo", pageNo + "");

        Call<RCXCPersonBean> call = ApiManager.shApi.queryRCXCPerson(map);
        call.enqueue(new Callback<RCXCPersonBean>() {
            @Override
            public void onResponse(Response<RCXCPersonBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    personBean = response.body();
//                    Toast.makeText(RCXCDetailActivity.this, "个体" + personBean.getMsg(), Toast.LENGTH_SHORT).show();

                    setPersonListData(personBean);

                } else {
                    Toast.makeText(context, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(context, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPersonListData(RCXCPersonBean personBean) {

        if (personBean.getValues().getResult().isEmpty()) {
            Toast.makeText(RCXCDetailActivity.this, "没有数据返回！", Toast.LENGTH_SHORT).show();
            return;
        }
        datas = new ArrayList<>();
        for (int i = 0; i < personBean.getValues().getResult().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("注册号" + "##" + personBean.getValues().getResult().get(i).getRegNo());
            as.add("字号名称" + "##" + personBean.getValues().getResult().get(i).getName());
            as.add("地址" + "##" + personBean.getValues().getResult().get(i).getAddress());
            as.add("日期" + "##" + personBean.getValues().getResult().get(i).getCheckDate());
            as.add("jgjl");
            datas.add(as);
        }
        XqzgdqGlxxAdapter adapter = new XqzgdqGlxxAdapter(datas, context);
        listView.setAdapter(adapter);
        RcxcCompanyItemListener();
    }

    private void initData() {
        pageNo = 1;
        MyProgressDialog.show(context);
        map.put("pageNo", pageNo + "");

        Call<RCXCCompanyBean> call = ApiManager.shApi.queryRCXCCompany(map);
        call.enqueue(new Callback<RCXCCompanyBean>() {
            @Override
            public void onResponse(Response<RCXCCompanyBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    companyBean = response.body();
//                    Toast.makeText(RCXCDetailActivity.this, "企业" + companyBean.getMsg(), Toast.LENGTH_SHORT).show();
                    setCompanyListData(companyBean);

                } else {
                    Toast.makeText(context, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
//                Log.d("ok###", t.getMessage());
                Toast.makeText(context, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCompanyListData(RCXCCompanyBean companyBean) {
        if (companyBean.getValues().getResult().isEmpty()) {
            Toast.makeText(RCXCDetailActivity.this, "没有数据返回！", Toast.LENGTH_SHORT).show();
//            finish();
            return;
        }
        datas = new ArrayList<>();
        for (int i = 0; i < companyBean.getValues().getResult().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("注册号" + "##" + companyBean.getValues().getResult().get(i).getRegNo());
            as.add("企业名称" + "##" + companyBean.getValues().getResult().get(i).getEtpsName());
            as.add("地址" + "##" + companyBean.getValues().getResult().get(i).getAddress());
            as.add("日期" + "##" + companyBean.getValues().getResult().get(i).getCheckDate());
            as.add("jgjl");
            datas.add(as);
        }
        XqzgdqGlxxAdapter adapter = new XqzgdqGlxxAdapter(datas, context);
        listView.setAdapter(adapter);
        RcxcCompanyItemListener();

    }

    private void RcxcCompanyItemListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(RCXCDetailActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                if (currTab == 0) {
                    Intent i = new Intent(context,DetailCompanyActivity.class);
                    i.putExtra("entityId",companyBean.getValues().getResult().get(position).getEtpsId());
                    startActivity(i);
                } else {
                    Intent i = new Intent(context,DetailPersonActivity.class);
                    i.putExtra("entityId",personBean.getValues().getResult().get(position).getPeId());
                    startActivity(i);
                }

            }
        });
    }


    public class MyListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            Toast.makeText(context, "刷新数据", Toast.LENGTH_SHORT).show();
            if (currTab == 0) {
                initData();
            } else {
                initPersonData();
            }
            // 千万别忘了告诉控件刷新完毕了哦！
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            Toast.makeText(context, "加载数据", Toast.LENGTH_SHORT).show();

            MyProgressDialog.show(context);

            map.put("pageNo", ++pageNo + "");

            if (currTab == 0) {
                onMore();
            } else {
                onPersonMore();
            }

            // 千万别忘了告诉控件加载完毕了哦！
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);


        }
    }

    private void onPersonMore() {
        Call<RCXCPersonBean> call = ApiManager.shApi.queryRCXCPerson(map);
        call.enqueue(new Callback<RCXCPersonBean>() {
            @Override
            public void onResponse(Response<RCXCPersonBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    RCXCPersonBean dataMore = response.body();

                    Toast.makeText(RCXCDetailActivity.this, "加载成功", Toast.LENGTH_SHORT).show();

//                    int len = dataMore.getValues().getResult().size();
//                    for (int i = 0; i < len; i++) {
//                        personBean.getValues().getResult().add(dataMore.getValues().getResult().get(i));
//                    }


                    ArrayList<ArrayList<String>> datas_more = new ArrayList<>();
                    for (int i = 0; i < dataMore.getValues().getResult().size(); i++) {
                        ArrayList<String> as = new ArrayList<>();
                        as.add("注册号" + "##" + dataMore.getValues().getResult().get(i).getRegNo());
                        as.add("字号名称" + "##" + dataMore.getValues().getResult().get(i).getName());
                        as.add("地址" + "##" + dataMore.getValues().getResult().get(i).getAddress());
                        as.add("日期" + "##" + dataMore.getValues().getResult().get(i).getCheckDate());
                        as.add("jgjl");
                        datas_more.add(as);
                    }

                    datas.addAll(datas_more);

                    XqzgdqGlxxAdapter adapter = new XqzgdqGlxxAdapter(datas, context);
                    listView.setAdapter(adapter);


//                        XqzgdqGlxxAdapter adapter = new XqzgdqGlxxAdapter(companyBean, context);
//                        listView.setAdapter(adapter);

                } else {
                    pageNo--;
                    Toast.makeText(context, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                pageNo--;
                Toast.makeText(context, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onMore() {
        Call<RCXCCompanyBean> call = ApiManager.shApi.queryRCXCCompany(map);
        call.enqueue(new Callback<RCXCCompanyBean>() {
            @Override
            public void onResponse(Response<RCXCCompanyBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    RCXCCompanyBean dataMore = response.body();

                    Toast.makeText(RCXCDetailActivity.this, "加载成功", Toast.LENGTH_SHORT).show();

//                    int len = dataMore.getValues().getResult().size();
//                    for (int i = 0; i < len; i++) {
//                        companyBean.getValues().getResult().add(dataMore.getValues().getResult().get(i));
//                    }

                    ArrayList<ArrayList<String>> datas_more = new ArrayList<>();
                    for (int i = 0; i < dataMore.getValues().getResult().size(); i++) {
                        ArrayList<String> as = new ArrayList<>();
                        as.add("注册号" + "##" + dataMore.getValues().getResult().get(i).getRegNo());
                        as.add("企业名称" + "##" + dataMore.getValues().getResult().get(i).getEtpsName());
                        as.add("地址" + "##" + dataMore.getValues().getResult().get(i).getAddress());
                        as.add("日期" + "##" + dataMore.getValues().getResult().get(i).getCheckDate());
                        as.add("jgjl");
                        datas_more.add(as);
                    }

                    datas.addAll(datas_more);

                    XqzgdqGlxxAdapter adapter = new XqzgdqGlxxAdapter(datas, context);
                    listView.setAdapter(adapter);

                } else {
                    pageNo--;
                    Toast.makeText(context, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                pageNo--;
                Toast.makeText(context, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
