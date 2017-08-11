package com.wondersgroup.commerce.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.mysupervision.CatalogFixBean;
import com.wondersgroup.commerce.teamwork.mysupervision.CompanyListBean;
import com.wondersgroup.commerce.teamwork.wywork.AnimatedExpandableListView;
import com.wondersgroup.commerce.ynwq.bean.CompanyAllDataBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

//    private App app;
//    public static String searchString;
//    public static Handler handler;
//    private AnimatedExpandableListView elv;
//    ArrayList<CompanyListBean> dataList = new ArrayList<>();
//    private CommonAdapter adapter;
//
//    private CompanyAllDataBean companyAllDataBean;
//
//    private HashMap<String, String> dicEntTypeHashMap = new HashMap<>();
//    private HashMap<String, ArrayList<CatalogFixBean>> dicLayoutConfigMap = new HashMap<>();
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
//
//        //获得传过来的String
//        final String queryString=getIntent().getStringExtra("queryString");
//
//        final HttpHelper hh  = new HttpHelper();
//
//
//        //设置Toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
//        TextView title=(TextView)findViewById(R.id.toolbar_title);
//        title.setText("企业查询");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
//
//        elv = (AnimatedExpandableListView) findViewById(R.id.elv);
//
//        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//
//                dataList.get(groupPosition).getRegNo();
//                String url = UrlConfig.URL_COMPANY_DETAIL + "/"
//                        + dataList.get(groupPosition).getUuid();
//                Log.i("url", url);
//                hh.get(url, new AjaxCallBack<String>() {
//                    @Override
//                    public void onStart() {
//                        MyProgressDialog.show(SearchActivity.this);
//                        super.onStart();
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t, int errorNo,
//                                          String strMsg) {
//                        MyProgressDialog.dismiss();
//                        Toast.makeText(SearchActivity.this, "连接服务器失败，请检查网络。",
//                                Toast.LENGTH_SHORT).show();
//                        super.onFailure(t, errorNo, strMsg);
//                    }
//
//                    @Override
//                    public void onSuccess(String t) {
//                        MyProgressDialog.dismiss();
//
//                        JSONObject jsonObject = null;
//                        try {
//                            jsonObject = new JSONObject(t);
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        if (jsonObject == null) {
//                            Toast.makeText(SearchActivity.this, "服务器数据有误",
//                                    Toast.LENGTH_SHORT).show();
//
//                            return;
//                        }
//
//                        companyAllDataBean = new CompanyAllDataBean();
//                        CompanyInfoBean cib = new CompanyInfoBean();
//
//                        JsonHelper.parse(cib, jsonObject.toString());
//                        companyAllDataBean.setCompanyInfoBean(cib);
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entBlackSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntBlackSetBean entBlackSetBean = new EntBlackSetBean();
//                                JsonHelper.parse(entBlackSetBean,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntBlackSetList().add(
//                                        entBlackSetBean);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entBranchSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntBranchSet entBranchSet = new EntBranchSet();
//                                JsonHelper.parse(entBranchSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntBranchSetList().add(
//                                        entBranchSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entClearSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntClearSetBean entClearSet = new EntClearSetBean();
//                                JsonHelper.parse(entClearSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntClearSetList().add(
//                                        entClearSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entInvestorSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntInvestorSetBean entInvestorSet = new EntInvestorSetBean();
//                                JsonHelper.parse(entInvestorSet,
//                                        jsonArrayA.getString(i));
//                                JSONObject jab = null;
//                                JSONArray jsonArr = null;
//                                try {
//                                    jab = new JSONObject(jsonArrayA
//                                            .getString(i));
//                                    jsonArr = new JSONArray(jab
//                                            .getString("entInvtSet"));
//                                } catch (JSONException e) {
//                                    // TODO: handle exception
//                                }
//
//                                if (jsonArr != null && jsonArr.length() != 0) {
//                                    entInvestorSet
//                                            .setConFormInterpreted(jsonArr
//                                                    .getJSONObject(0)
//                                                    .getString(
//                                                            "conFormInterpreted"));
//                                }
//
//                                companyAllDataBean.getEntInvestorSetList().add(
//                                        entInvestorSet);
//                            }
//
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entMemberSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntMemberSetBean entMemberSet = new EntMemberSetBean();
//                                JsonHelper.parse(entMemberSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntMemberSetList().add(
//                                        entMemberSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entMortgageSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntMortgageSetBean entMortgageSet = new EntMortgageSetBean();
//                                JsonHelper.parse(entMortgageSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntMortgageSetList().add(
//                                        entMortgageSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entPledgeSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntPledgeSetBean entPledgeSet = new EntPledgeSetBean();
//                                JsonHelper.parse(entPledgeSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntPledgeSetList().add(
//                                        entPledgeSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entPunishSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntPunishSetBean entPunishSet = new EntPunishSetBean();
//                                JsonHelper.parse(entPunishSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntPunishSetList().add(
//                                        entPunishSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entSpotCheckSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntSpotCheckSetBean entSpotCheckSet = new EntSpotCheckSetBean();
//                                JsonHelper.parse(entSpotCheckSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntSpotCheckSetList()
//                                        .add(entSpotCheckSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entChangeSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntChangeSet entChangeSet = new EntChangeSet();
//                                JsonHelper.parse(entChangeSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntChangeSet().add(
//                                        entChangeSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            JSONArray jsonArrayA = new JSONArray(jsonObject
//                                    .getString("entExceptSet"));
//
//                            for (int i = 0; i < jsonArrayA.length(); i++) {
//                                EntExceptSet entExceptSet = new EntExceptSet();
//                                JsonHelper.parse(entExceptSet,
//                                        jsonArrayA.getString(i));
//                                companyAllDataBean.getEntExceptSetList().add(
//                                        entExceptSet);
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//
//                        Intent intent = new Intent();
//                        DjxxActivity.cadb = companyAllDataBean;
//                        intent.setClass(SearchActivity.this, DjxxActivity.class);
//                        startActivity(intent);
//
//                        super.onSuccess(t);
//                    }
//                });
//
//                return true;
//            }
//
//        });
//
//
//        initHanlder();
//
//        //网络请求1
//        Log.i("urlurl", UrlConfig.URL_Catalog_LIST);
//        app = (App) SearchActivity.this.getApplication();
//        app.getDicEntTypeHashMap();
//        if (app.getDicEntTypeHashMap()==null||app.getDicLayoutConfigMap()==null) {
//            app.setDicEntTypeHashMap(null);
//            app.setDicLayoutConfigMap(null);
//            hh.get(UrlConfig.URL_Catalog_LIST, new AjaxCallBack<String>() {
//
//                @Override
//                public void onSuccess(String t) {
//                    JSONObject jsonF = null;
//                    try {
//                        jsonF = new JSONObject(t);
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//
//                    if (jsonF == null) {
//                        Toast.makeText(SearchActivity.this, "目录获取失败，请重新启动程序",
//                                Toast.LENGTH_SHORT).show();
//                        SearchActivity.this.finish();
//                    }
//
//                    JSONObject jsonB = null;
//                    try {
//                        jsonB = new JSONObject(jsonF.getString("dicEntType"));
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//
//                    if (jsonB == null) {
//                        Toast.makeText(SearchActivity.this, "目录获取失败，请重新启动程序",
//                                Toast.LENGTH_SHORT).show();
//                        SearchActivity.this.finish();
//                    }
//
//                    HashMap<String, String> keysOne = new HashMap<String, String>();
//                    Iterator keyIter = jsonB.keys();
//                    while (keyIter.hasNext()) {
//                        String key = (String) keyIter.next();
//                        String value = "";
//                        try {
//                            value = jsonB.getString(key);
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                        dicEntTypeHashMap.put(key, value);
//                        if (dicLayoutConfigMap.get(value) == null) {
//                            dicLayoutConfigMap.put(value,
//                                    new ArrayList<CatalogFixBean>());
//                        }
//                    }
//
//                    JSONObject jsonA = null;
//                    try {
//                        jsonA = new JSONObject(jsonF.getString("dicLayoutConfig"));
//                    } catch (JSONException e1) {
//                        // TODO Auto-generated catch block
//                        e1.printStackTrace();
//                    }
//
//                    if (jsonA == null) {
//                        Toast.makeText(SearchActivity.this, "目录获取失败，请重新启动程序",
//                                Toast.LENGTH_SHORT).show();
//                        SearchActivity.this.finish();
//                    }
//
//                    for (Map.Entry<String, ArrayList<CatalogFixBean>> entry : dicLayoutConfigMap
//                            .entrySet()) {
//                        String key = entry.getKey();
//
//                        try {
//                            JSONObject json = new JSONObject(jsonA.getString(key));
//
//                            JSONArray jsonArray1 = new JSONArray(json
//                                    .getString("01"));
//
//                            for (int i = 0; i < jsonArray1.length(); i++) {
//                                CatalogBean cb = new CatalogBean();
//                                JsonHelper.parse(cb, jsonArray1.getString(i));
//                                CatalogFixBean cfb = new CatalogFixBean(cb);
//                                entry.getValue().add(cfb);
//                            }
//
//                            JSONArray jsonArray2 = new JSONArray(json
//                                    .getString("02"));
//
//                            for (int i = 0; i < jsonArray2.length(); i++) {
//                                CatalogBean cb = new CatalogBean();
//                                JsonHelper.parse(cb, jsonArray2.getString(i));
//                                CatalogFixBean cfb = new CatalogFixBean(cb);
//
//                                for (int j = 0; j < entry.getValue().size(); j++) {
//                                    if (cb.getParentId()
//                                            .equals(entry.getValue().get(j)
//                                                    .getCatalogBean().getLayoutId())) {
//                                        entry.getValue().get(j).getChildren()
//                                                .add(cfb);
//
//                                        break;
//                                    }
//                                }
//                            }
//
//                            JSONArray jsonArray3 = new JSONArray(json
//                                    .getString("03"));
//
//                            for (int i = 0; i < jsonArray3.length(); i++) {
//                                CatalogBean cb = new CatalogBean();
//                                JsonHelper.parse(cb, jsonArray3.getString(i));
//                                CatalogFixBean cfb = new CatalogFixBean(cb);
//
//                                for (int j = 0; j < entry.getValue().size(); j++) {
//                                    for (int k = 0; k < entry.getValue().get(j)
//                                            .getChildren().size(); k++) {
//                                        if (cb.getParentId().equals(
//                                                entry.getValue().get(j)
//                                                        .getChildren().get(k)
//                                                        .getCatalogBean()
//                                                        .getLayoutId())) {
//                                            entry.getValue().get(j).getChildren()
//                                                    .get(k).getChildren().add(cfb);
//
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//
//                            JSONArray jsonArray4 = new JSONArray(json
//                                    .getString("04"));
//
//                            for (int i = 0; i < jsonArray4.length(); i++) {
//                                CatalogBean cb = new CatalogBean();
//                                JsonHelper.parse(cb, jsonArray4.getString(i));
//                                CatalogFixBean cfb = new CatalogFixBean(cb);
//
//                                for (int j = 0; j < entry.getValue().size(); j++) {
//                                    for (int k = 0; k < entry.getValue().get(j)
//                                            .getChildren().size(); k++) {
//                                        for (int l = 0; l < entry.getValue().get(j)
//                                                .getChildren().get(k).getChildren()
//                                                .size(); l++) {
//                                            if (cb.getParentId().equals(
//                                                    entry.getValue().get(j)
//                                                            .getChildren().get(k)
//                                                            .getChildren().get(l)
//                                                            .getCatalogBean()
//                                                            .getLayoutId())) {
//                                                entry.getValue().get(j)
//                                                        .getChildren().get(k)
//                                                        .getChildren().get(l)
//                                                        .getChildren().add(cfb);
//
//                                                break;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                    app.setDicEntTypeHashMap(dicEntTypeHashMap);
//                    app.setDicLayoutConfigMap(dicLayoutConfigMap);
//
//                    SharedPreferences sp = getSharedPreferences("city",
//                            MODE_PRIVATE);
//
//                    super.onSuccess(t);
//
//                }
//
//                @Override
//                public void onFailure(Throwable t, int errorNo, String strMsg) {
//                    Toast.makeText(SearchActivity.this, "连接服务器失败",
//                            Toast.LENGTH_SHORT).show();
//
//                    SearchActivity.this.finish();
//                    super.onFailure(t, errorNo, strMsg);
//                }
//            });
//        }
//
//        //网络请求2
//        String post = UrlConfig.URL_COMPANY_LIST + "/"
//                +queryString + "/1" + "/1";
//        Log.i("url", post);
//        hh.get(post, new AjaxCallBack<String>() {
//
//            @Override
//            public void onStart() {
//                MyProgressDialog.show(SearchActivity.this);
//                super.onStart();
//            }
//
//            @Override
//            public void onSuccess(String t) {
//                MyProgressDialog.dismiss();
//                Message m = new Message();
//                m.obj = t;
//                searchString = queryString;
//                // MainActivity.currentTypeId = TypeId.NZGSFR;
//                handler.sendMessage(m);
//                super.onSuccess(t);
//            }
//
//            @Override
//            public void onFailure(Throwable t, int errorNo, String strMsg) {
//                MyProgressDialog.dismiss();
//                Toast.makeText(SearchActivity.this, "连接服务器失败，请检查网络。",
//                        Toast.LENGTH_SHORT).show();
//                super.onFailure(t, errorNo, strMsg);
//            }
//
//        });
//
//    }
//
//    private void initHanlder() {
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                // 查询企业列表
//
//                String result = (String) msg.obj;
//                JSONObject resultJ = null;
//                try {
//                    resultJ = new JSONObject(result);
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                if (resultJ == null) {
//                    Toast.makeText(SearchActivity.this, "服务器数据异常", Toast.LENGTH_SHORT)
//                            .show();
//
//                    return;
//                }
//
//                // 取出列表
//                JSONArray resultL = null;
//                try {
//                    resultL = new JSONArray(resultJ.getString("info"));
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                if (resultL == null) {
//                    Toast.makeText(SearchActivity.this, "对不起，没有查询到相关企业信息！",
//                            Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
//
//
//                for (int i = 0; i < resultL.length(); i++) {
//                    CompanyListBean temp = new CompanyListBean();
//                    try {
//                        JsonHelper.parse(temp, resultL.getString(i));
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    dataList.add(temp);
//                }
//
//                if (dataList.size() == 0) {
//                    Toast.makeText(SearchActivity.this, "对不起，没有查询到相关企业信息！",
//                            Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
//                initAdapter();
//            }
//        };
//    }
//
//
//
//    private void initAdapter() {
//        ArrayList<Object> data = new ArrayList<Object>();
//
//        for (int i = 0; i < dataList.size(); i++) {
//            FiveBean temp = new FiveBean();
//            temp.setOne(dataList.get(i).getEntName());
//            temp.setTwo(dataList.get(i).getRegNo());
//            temp.setThree(dataList.get(i).getLerep());
//            temp.setFour(dataList.get(i).getRegOrgan());
//            temp.setFive(dataList.get(i).getEstDate());
//
//            data.add(temp);
//        }
//
//        adapter = new CommonAdapter(SearchActivity.this, new String[] { "企业名称:",
//                "注册号:", "法定代表人:", "登记机关:", "成立日期:" }, data, R.layout.item_five);
//
//        elv.setAdapter(adapter);
//
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//

}
