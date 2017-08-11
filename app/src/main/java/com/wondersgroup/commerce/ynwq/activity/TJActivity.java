package com.wondersgroup.commerce.ynwq.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.ccjc.DicItem;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.ynwq.bean.ChildOrganResult;
import com.wondersgroup.commerce.ynwq.bean.DicResult;
import com.wondersgroup.commerce.ynwq.bean.DicT;
import com.wondersgroup.commerce.ynwq.bean.TJItem;
import com.wondersgroup.commerce.ynwq.bean.TJProcessItem;
import com.wondersgroup.commerce.ynwq.bean.TJProcessResult;
import com.wondersgroup.commerce.ynwq.bean.TJResult;
import com.wondersgroup.commerce.ynwq.widget.SpinnerDropDown;
import com.wondersgroup.commerce.ynwq.widget.StaticSquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TJActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.spinner_toolbar)Toolbar toolbar;
    @Bind(R.id.spinner_toolbar_title)TextView title;
    @Bind(R.id.tj_square_one)StaticSquare squareOne;
    @Bind(R.id.tj_square_two)StaticSquare squareTwo;
    @Bind(R.id.tj_square_three)StaticSquare squareThree;
    @Bind(R.id.tj_square_four)StaticSquare squareFour;
    @Bind(R.id.tj_square_five)StaticSquare squareFive;
    @Bind(R.id.tj_square_six)StaticSquare squareSix;
    @Bind(R.id.tj_layout_single)LinearLayout single;
    @Bind(R.id.tj_layout_multi)LinearLayout multi;
    @Bind(R.id.tj_layout_double)LinearLayout layoutDoule;
    @Bind(R.id.tj_square_single)StaticSquare squareSingle;
    @Bind(R.id.tj_square_multi_one)StaticSquare squareMulOne;
    @Bind(R.id.tj_square_multi_two)StaticSquare squareMulTwo;
    @Bind(R.id.tj_square_multi_three)StaticSquare squareMulThr;
    @Bind(R.id.tj_square_double_one)StaticSquare squareDoubleOne;
    @Bind(R.id.tj_square_double_two)StaticSquare squareDoubleTwo;

    private String type;
    private ArrayList<String> items;
    private List<DicItem> dicItems;
    private SharedPreferences sp;
    private HashMap<String,String> body;
    private int selection=0;
    private MaterialDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tj2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        type=getIntent().getStringExtra("type");
        sp=getSharedPreferences("Default", MODE_PRIVATE);
        dicItems=new ArrayList<>();
        items=new ArrayList<>();
        body=(HashMap<String,String>)getIntent().getSerializableExtra("body");

        if("JZQK".equals(type)){
            multi.setVisibility(View.VISIBLE);
            layoutDoule.setVisibility(View.VISIBLE);
        }else {
            multi.setVisibility(View.GONE);
            layoutDoule.setVisibility(View.GONE);
        }

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] locs = new int[2];
                toolbar.getLocationOnScreen(locs);
                SpinnerDropDown dropDown = SpinnerDropDown.newInstance(locs[1] + toolbar.getHeight(),items);
                dropDown.setOnItemClickListener(new SpinnerDropDown.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, String value) {
                        title.setText(value);
                        selection=position;
                        if(value.equals("合计")){
                            disableAll();
                        }else {
                            enableAll();
                        }
                        fetchData();
                    }
                });
                dropDown.show(getSupportFragmentManager(), "SpinnerDropDown");
            }
        });
        initSquare();
        checkDic();
    }

    public void initSquare(){
        if("JZQK".equals(type)){
            squareOne.setLabel("受理数");
            squareOne.setColor(StaticSquare.COLOR_RED);
            squareOne.setOnClickListener(this);
            squareTwo.setLabel("已初审数");
            squareTwo.setColor(StaticSquare.COLOR_RED);
            squareTwo.setOnClickListener(this);
            squareThree.setLabel("已会审数");
            squareThree.setColor(StaticSquare.COLOR_GREEN);
            squareThree.setOnClickListener(this);
            squareFour.setLabel("已拨付数");
            squareFour.setColor(StaticSquare.COLOR_GREEN);
            squareFour.setOnClickListener(this);
            squareFive.setLabel("投资金额(万元)");
            squareFive.setColor(StaticSquare.COLOR_BLUE);
            squareSix.setLabel("带动就业人数");
            squareSix.setColor(StaticSquare.COLOR_BLUE);
            squareSingle.setLabel("申报数");
            squareSingle.setColor(StaticSquare.COLOR_RED);
            squareSingle.setOnClickListener(this);
            squareDoubleOne.setLabel("获贷户数");
            squareDoubleOne.setColor(StaticSquare.COLOR_BLUE);
            squareDoubleOne.setOnClickListener(this);
            squareDoubleTwo.setLabel("金额(万元)");
            squareDoubleTwo.setColor(StaticSquare.COLOR_BLUE);
            squareMulOne.setLabel("省级");
            squareMulOne.setColor(StaticSquare.COLOR_RED);
            squareMulTwo.setLabel("州市级");
            squareMulTwo.setColor(StaticSquare.COLOR_RED);
            squareMulThr.setLabel("省市区级");
            squareMulThr.setColor(StaticSquare.COLOR_RED);
        }else {
            squareOne.setLabel("申请办理总量");
            squareOne.setColor(StaticSquare.COLOR_RED);
            squareOne.setOnClickListener(this);
            squareTwo.setLabel("审查通过率");
            squareTwo.setColor(StaticSquare.COLOR_RED);
            squareThree.setLabel("申请补助企业数");
            squareThree.setColor(StaticSquare.COLOR_GREEN);
            squareThree.setOnClickListener(this);
            squareFour.setLabel("申请贷款企业数");
            squareFour.setColor(StaticSquare.COLOR_GREEN);
            squareFour.setOnClickListener(this);
            squareFive.setLabel("补助审查通过数量");
            squareFive.setColor(StaticSquare.COLOR_BLUE);
            squareFive.setOnClickListener(this);
            squareSix.setLabel("贷款审查通过数量");
            squareSix.setColor(StaticSquare.COLOR_BLUE);
            squareSix.setOnClickListener(this);
            squareSingle.setLabel("贷款资金总额");
            squareSingle.setColor(StaticSquare.COLOR_RED);
        }
    }

    private void disableAll(){
        squareOne.setEnabled(false);
        squareTwo.setEnabled(false);
        squareThree.setEnabled(false);
        squareFour.setEnabled(false);
        squareFive.setEnabled(false);
        squareSix.setEnabled(false);
        squareSingle.setEnabled(false);
        squareDoubleOne.setEnabled(false);
        squareDoubleTwo.setEnabled(false);
        squareMulOne.setEnabled(false);
        squareMulTwo.setEnabled(false);
        squareMulThr.setEnabled(false);
    }

    private void enableAll(){
        squareOne.setEnabled(true);
        squareTwo.setEnabled(true);
        squareThree.setEnabled(true);
        squareFour.setEnabled(true);
        squareFive.setEnabled(true);
        squareSix.setEnabled(true);
        squareSingle.setEnabled(true);
        squareDoubleOne.setEnabled(true);
        squareDoubleTwo.setEnabled(true);
        squareMulOne.setEnabled(true);
        squareMulTwo.setEnabled(true);
        squareMulThr.setEnabled(true);
    }

    public void checkDic(){
        progress=new MaterialDialog.Builder(this)
                .title("加载中")
                .content("检查字典表...")
                .progress(true,0)
                .show();
        DicT dicT= Hawk.get("Dic");
        if(dicT==null||dicT.getDicDeptTypeAll()==null||dicT.getIndustryMap()==null||dicT.getJsonReceiveOrganTree()==null){
            progress.setContent("获取字典表...");
            Map<String,String> body =new HashMap<>();
            int selection=sp.getInt("selection", 0);
            body.put("deptId", sp.getString("deptId", "").split(",")[selection]);
            body.put("organId", sp.getString("organId", "").split(",")[selection]);
            body.put("wsCodeReq", "01100001");
            Call<DicResult> call = ApiManager.ynWqApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        DicResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            Hawk.put("Dic", result.getResult());
                            fetchData();
                        }else {
                            progress.hide();
                            new MaterialDialog.Builder(TJActivity.this)
                                    .title("出错")
                                    .content("获取字典表失败")
                                    .positiveText("确定")
                                    .show();
                        }
                    }else {
                        progress.hide();
                        new MaterialDialog.Builder(TJActivity.this)
                                .title("出错")
                                .content("连接服务器失败")
                                .positiveText("确定")
                                .show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    progress.hide();
                    new MaterialDialog.Builder(TJActivity.this)
                            .title("出错")
                            .content("网络错误")
                            .positiveText("确定")
                            .show();
                }
            });
        }else {
            fetchData();
        }
    }

    public void fetchData(){
        progress.setContent("查询中...");
        if("SZBM".equals(type)){
            DicT dicT= Hawk.get("Dic");
            initItems(dicT.getDicDeptTypeAll());
            body.put("deptId", dicItems.get(selection).getName());
            title.setText(dicItems.get(selection).getValue());
            Call<TJResult> call= ApiManager.ynWqApi.getDeptStat(body);
            call.enqueue(new Callback<TJResult>() {
                @Override
                public void onResponse(Response<TJResult> response, Retrofit retrofit) {
                    progress.hide();
                    if (response.isSuccess()&&response.body()!=null) {
                        TJResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            setSquare(result.getResult());
                        }else {
                            new MaterialDialog.Builder(TJActivity.this)
                                    .title("出错")
                                    .content(result.getMessage())
                                    .positiveText("确定")
                                    .show();
                        }
                    }else {
                        new MaterialDialog.Builder(TJActivity.this)
                                .title("出错")
                                .content("连接服务器失败")
                                .positiveText("确定")
                                .show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    progress.hide();
                    new MaterialDialog.Builder(TJActivity.this)
                            .title("出错")
                            .content("网络错误")
                            .positiveText("确定")
                            .show();
                }
            });
        }else if("SZHY".equals(type)){
            DicT dicT= Hawk.get("Dic");
            initItems(dicT.getIndustryMap());
            body.put("industryId", dicItems.get(selection).getName());
            title.setText(dicItems.get(selection).getValue());
            Call<TJResult> call=ApiManager.ynWqApi.getInduStat(body);
            call.enqueue(new Callback<TJResult>() {
                @Override
                public void onResponse(Response<TJResult> response, Retrofit retrofit) {
                    progress.hide();
                    if (response.isSuccess()&&response.body()!=null) {
                        TJResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            setSquare(result.getResult());
                        }else {
                            new MaterialDialog.Builder(TJActivity.this)
                                    .title("出错")
                                    .content(result.getMessage())
                                    .positiveText("确定")
                                    .show();
                        }
                    }else {
                        new MaterialDialog.Builder(TJActivity.this)
                                .title("出错")
                                .content("连接服务器失败")
                                .positiveText("确定")
                                .show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    progress.hide();
                    new MaterialDialog.Builder(TJActivity.this)
                            .title("出错")
                            .content("网络错误")
                            .positiveText("确定")
                            .show();
                }
            });
        }else if("SZQY".equals(type)||"JZQK".equals(type)){
            progress.setContent("获取子机关...");
            String organId=body.get("organId");
            Map<String,String> newBody=new HashMap<>();
            newBody.put("organId",organId);
            Call<ChildOrganResult> call=ApiManager.ynWqApi.getChildOrgan(body);
            call.enqueue(new Callback<ChildOrganResult>() {
                @Override
                public void onResponse(Response<ChildOrganResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        ChildOrganResult result=response.body();
                        if ("200".equals(result.getResultCode())){
                            progress.setContent("获取数据中...");
                            initItems(result.getResult());
                            body.put("receiveOrganId",dicItems.get(selection).getName());
                            title.setText(dicItems.get(selection).getValue());
                            if("SZQY".equals(type)){
                                Call<TJResult> qyCall=ApiManager.ynWqApi.getAreaStat(body);
                                qyCall.enqueue(new Callback<TJResult>() {
                                    @Override
                                    public void onResponse(Response<TJResult> response, Retrofit retrofit) {
                                        progress.hide();
                                        if (response.isSuccess()&&response.body()!=null) {
                                            TJResult result = response.body();
                                            if ("200".equals(result.getResultCode())) {
                                                setSquare(result.getResult());
                                            }else {
                                                new MaterialDialog.Builder(TJActivity.this)
                                                        .title("出错")
                                                        .content(result.getMessage())
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }else {
                                            new MaterialDialog.Builder(TJActivity.this)
                                                    .title("出错")
                                                    .content("连接服务器失败")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        progress.hide();
                                        new MaterialDialog.Builder(TJActivity.this)
                                                .title("出错")
                                                .content("网络错误")
                                                .positiveText("确定")
                                                .show();
                                    }
                                });
                            }else if("JZQK".equals(type)){
                                Call<TJProcessResult> jzCall=ApiManager.ynWqApi.getProcStat(body);
                                jzCall.enqueue(new Callback<TJProcessResult>() {
                                    @Override
                                    public void onResponse(Response<TJProcessResult> response, Retrofit retrofit) {
                                        progress.hide();
                                        if(response.isSuccess()&&response.body()!=null) {
                                            TJProcessResult result = response.body();
                                            if ("200".equals(result.getResultCode())) {
                                                setSquare(result.getResult());
                                            }else {
                                                new MaterialDialog.Builder(TJActivity.this)
                                                        .title("出错")
                                                        .content(result.getMessage())
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }else {
                                            new MaterialDialog.Builder(TJActivity.this)
                                                    .title("出错")
                                                    .content("连接服务器失败")
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        progress.hide();
                                        new MaterialDialog.Builder(TJActivity.this)
                                                .title("出错")
                                                .content("网络错误")
                                                .positiveText("确定")
                                                .show();
                                    }
                                });
                            }
                        }else {
                            progress.hide();
                            new MaterialDialog.Builder(TJActivity.this)
                                    .title("出错")
                                    .content(result.getMessage())
                                    .positiveText("确定")
                                    .show();
                        }
                    }else {
                        progress.hide();
                        new MaterialDialog.Builder(TJActivity.this)
                                .title("出错")
                                .content("连接服务器失败")
                                .positiveText("确定")
                                .show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    progress.hide();
                    new MaterialDialog.Builder(TJActivity.this)
                            .title("出错")
                            .content("网络错误")
                            .positiveText("确定")
                            .show();
                }
            });
        }
    }

    public void initItems(List<DicItem> newItems){
        dicItems.clear();
        DicItem tmp=new DicItem();
        tmp.setValue("合计");
        tmp.setName(null);
        dicItems.add(tmp);
        if(newItems!=null)dicItems.addAll(newItems);
        items.clear();
        for (DicItem item :
                dicItems) {
            items.add(item.getValue());
        }
    }

    public void setSquare(TJItem item){
        squareOne.setValue(item.getCol0());
        squareTwo.setValue(item.getCol1());
        squareThree.setValue(item.getCol2());
        squareFour.setValue(item.getCol3());
        squareFive.setValue(item.getCol4());
        squareSix.setValue(item.getCol5());
        squareSingle.setValue(item.getCol6());
    }
    public void setSquare(TJProcessItem item){
        squareOne.setValue(item.getCol2());
        squareTwo.setValue(item.getCol3());
        squareThree.setValue(item.getCol4());
        squareFour.setValue(item.getCol5());
        squareFive.setValue(item.getCol9());
        squareSix.setValue(item.getCol10());
        squareSingle.setValue(item.getCol1());
        squareMulOne.setValue(item.getCol6());
        squareMulTwo.setValue(item.getCol7());
        squareMulThr.setValue(item.getCol8());
        squareDoubleOne.setValue(item.getCol11());
        squareDoubleTwo.setValue(item.getCol12());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if("合计".equals(title.getText())) return;
        if("SZBM".equals(type)){
            body.put("statType","1");
        }else if("SZQY".equals(type)){
            body.put("statType","2");
        }else if("SZHY".equals(type)){
            body.put("statType","3");
        }else if("JZQK".equals(type)){
            body.put("statType","4");
        }

        if(v.equals(squareOne)){
            if("JZQK".equals(type)){
                body.put("colNo","col2");
            }else {
                body.put("colNo","col0");
            }
            Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
            intent.putExtra("body",body);
            startActivity(intent);
        }else if(v.equals(squareTwo)){
            if("JZQK".equals(type)){
                body.put("colNo","col3");
                Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
                intent.putExtra("body",body);
                startActivity(intent);
            }else {

            }
        }else if(v.equals(squareThree)){
            if("JZQK".equals(type)){
                body.put("colNo","col4");
            }else {
                body.put("colNo","col2");
            }
            Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
            intent.putExtra("body",body);
            startActivity(intent);
        }else if(v.equals(squareFour)){
            if("JZQK".equals(type)){
                body.put("colNo","col5");
            }else {
                body.put("colNo","col3");
            }
            Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
            intent.putExtra("body",body);
            startActivity(intent);
        }else if(v.equals(squareFive)){
            if("JZQK".equals(type)){

            }else {
                body.put("colNo","col4");
                Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
                intent.putExtra("body",body);
                startActivity(intent);
            }
        }else if(v.equals(squareSix)){
            if("JZQK".equals(type)){

            }else {
                body.put("colNo","col5");
                Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
                intent.putExtra("body",body);
                startActivity(intent);
            }
        }else if(v.equals(squareSingle)){
            if("JZQK".equals(type)){
                body.put("colNo","col1");
                Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
                intent.putExtra("body",body);
                startActivity(intent);
            }else {

            }
        }else if(v.equals(squareDoubleOne)){
            if("JZQK".equals(type)){
                body.put("colNo","col11");
                Intent intent=new Intent(TJActivity.this,TJDetailActivity.class);
                intent.putExtra("body",body);
                startActivity(intent);
            }else {

            }
        }
    }
}
