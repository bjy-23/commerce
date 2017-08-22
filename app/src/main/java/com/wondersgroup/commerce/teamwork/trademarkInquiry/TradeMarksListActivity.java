package com.wondersgroup.commerce.teamwork.trademarkInquiry;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.MoreBtnAdapter;
import com.wondersgroup.commerce.adapter.SingleAdapter;
import com.wondersgroup.commerce.adapter.Title4RowAdapter;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.model.Title4RowItem;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.TradeMarkDictTypeListBean;
import com.wondersgroup.commerce.model.TradeMarkItemListBean;
import com.wondersgroup.commerce.model.TradeMarkVoList;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.statistics.DeptActivity;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/*
* 注册商标 列表页面
* by Lee
* */

public class TradeMarksListActivity extends AppCompatActivity {

    private final String TAG = "TradeMarksListActivity";
    private final String MARK_CHOOSE_OPTION_NO_LIMITED = "不限";
    private final String MARK_CHOOSE_OPTION_YES = "是";
    private final String MARK_CHOOSE_OPTION_NO = "不是";
    @Bind(R.id.toolbar_edit)
    EditText toolbarEdit;
    @Bind(R.id.toolbar_clear)
    ImageView toolbarClear;
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.searchresult_list)
    ListView searchRecyclerView;
    @Bind(R.id.type_txt)
    TextView typeTxt;
    @Bind(R.id.searchresult_type)
    LinearLayout searchresultType;
    @Bind(R.id.txt_obligeetype)
    TextView obligeetypeTxt;
    @Bind(R.id.txt_claim_state)
    TextView claimStateTxt;
    @Bind(R.id.txt_more)
    TextView moreTxt;
    @Bind(R.id.gray_view)
    View grayBg;
    @Bind(R.id.right_btn)
    LinearLayout qrBtn;
    @Bind(R.id.drawer_choose_list)
    ListView chooseMenuOptionsList;
    @Bind(R.id.date1txt)
    TextView date1txt;
    @Bind(R.id.date1)
    LinearLayout date1;
    @Bind(R.id.date2txt)
    TextView date2txt;
    @Bind(R.id.date2)
    LinearLayout date2;
    @Bind(R.id.simple_navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.tvDominationName)
    TextView tvDominationNameSelected;
    @Bind(R.id.tvRegisterName)
    TextView tvRegisterNameSelected;

    private String type;
    private RootAppcation app;

    //筛选相关
    private List<String> typeList;
    private List<String> checkTypeList;             //商标类型
    private List<KeyValue> checkTypeKeyValueList;   //商标类型
    private List<String> checkRegisterTypeList;             //权利人类型
    private List<KeyValue> checkRegisterTypeKeyValueList;   //权利人类型
    private List<String> checkAcceptConTypeList;             //认领状态
    private List<KeyValue> checkAcceptConTypeKeyValueList;   //认领状态


    private List<String> checkResultList;
    private SingleAdapter typeAdapter;          //商标类型
    private SingleAdapter registerTypeAdapter;  //权利人类型
    private SingleAdapter acceptConditionAdapter;//认领状态
    private PopupWindow typePopup;
    private String typeProductChosen = null;        //商品类型
    private String typeAuthorChosen = null;          //权利人类型
    private String typeGetChosen = null;           //认领类型
    private String managerDepartment = null;        //管辖机关
    private String registerDepartment = null;       //登记机关

    //更多筛选相关
    private String division;
    private boolean isDate1null = true;
    private boolean isDate2null = true;


    private List<Title4RowItem> dataList;
    private Title4RowAdapter title4RowAdapter;
    private List<TradeMarkVoList> marksList;
    private RegisterMarksAdapter marksAdapter;
    private List<MarkChooseOption> menuChooseOption;
    private MarkChooseMenAdapter menuAdapter;
    private int page = 1;
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trademarks_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        initView();
        initData();
        initNetData();
    }

    private void initView() {

        menuChooseOption = new ArrayList<MarkChooseOption>();
        searchRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String tradeMarkId = marksList.get(pos).getTmId();
                Intent intent = new Intent(TradeMarksListActivity.this, TradeMarkDetailActivity.class);
                intent.putExtra("tradeMarkId",tradeMarkId);
                startActivity(intent);
            }
        });
        marksList = new ArrayList<TradeMarkVoList>();
        checkTypeKeyValueList = new ArrayList<KeyValue>();
        checkTypeList = new ArrayList<String>();
        checkRegisterTypeList = new ArrayList<String>();             //权利人类型
        checkRegisterTypeKeyValueList = new ArrayList<KeyValue>();   //权利人类型
        checkAcceptConTypeList = new ArrayList<String>();             //认领状态
        checkAcceptConTypeKeyValueList = new ArrayList<KeyValue>();   //认领状态

        toolbarEdit.setHint("请输入商标名称");
        toolbarEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if (keyEvent.getAction()==KeyEvent.ACTION_UP){
                        getTradeMarksList();
                    }
                    return true;
                }
                return false;
            }
        });


    }

    private void initData(){

        MarkChooseOption option1 = new MarkChooseOption();
        option1.setOptionName("是否共有商标");
        menuChooseOption.add(option1);
        MarkChooseOption option2 = new MarkChooseOption();
        option2.setOptionName("是否立体商标");
        menuChooseOption.add(option2);
        MarkChooseOption option3 = new MarkChooseOption();
        option3.setOptionName("是否地理商标");
        menuChooseOption.add(option3);
        MarkChooseOption option4 = new MarkChooseOption();
        option4.setOptionName("是否国际注册");
        menuChooseOption.add(option4);
        MarkChooseOption option5 = new MarkChooseOption();
        option5.setOptionName("是否有效商标");
        menuChooseOption.add(option5);

        menuAdapter = new MarkChooseMenAdapter(TradeMarksListActivity.this, menuChooseOption);
        chooseMenuOptionsList.setAdapter(menuAdapter);


        TradeMarkVoList data1 = new TradeMarkVoList();
        data1.setBrandName("蒙氏");
        data1.setRegNo("11010510");
        data1.setRegisterName("长沙啤酒厂");
        data1.setChProposerAddr("湖南场合是基督山");
        data1.setAreaOrganId("四川行政管理局");
        marksList.add(data1);
        TradeMarkVoList data2 = new TradeMarkVoList();
        data2.setBrandName("蒙氏试试2");
        data2.setRegNo("11010510");
        data2.setRegisterName("长沙啤酒厂");
        data2.setChProposerAddr("湖南场合是基督山");
        data2.setAreaOrganId("四川行政管理局");
        marksList.add(data2);
        TradeMarkVoList data3 = new TradeMarkVoList();
        data3.setBrandName("蒙氏开花");
        data3.setRegNo("11010510");
        data3.setRegisterName("长沙啤酒厂");
        data3.setChProposerAddr("湖南场合是基督山");
        data3.setAreaOrganId("四川行政管理局");
        marksList.add(data3);
        marksAdapter = new RegisterMarksAdapter(TradeMarksListActivity.this, marksList);
        searchRecyclerView.setAdapter(marksAdapter);

    }

    //获取
    private void initNetData() {
        ApiManager.getInstance().unitTestInit();
        getDictsList();
    }

    //获取选择字典列表
    private void getDictsList(){
        String userId = loginBean.getResult().getUserId();
        String organId = loginBean.getResult().getOrganId();
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("organId", organId);
        map.put("wsCodeReq", "03019991");

        Call<TradeMarkDictTypeListBean> call;
        call = ApiManager.tradeMarkApi.getDicLoad(map);

        call.enqueue(new Callback<TradeMarkDictTypeListBean>() {
            @Override
            public void onResponse(Response<TradeMarkDictTypeListBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    TradeMarkDictTypeListBean dictsData = response.body();

                    if ((null == dictsData) || (null == dictsData.getResult())) {
                        Toast.makeText(TradeMarksListActivity.this, "获得字典数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (null != dictsData.getResult()) {
                        //商标类型
                        if(dictsData.getResult().getDicTmType()!=null){
                            checkTypeKeyValueList.clear();
                            checkTypeList.clear();
                            checkTypeKeyValueList.addAll(dictsData.getResult().getDicTmType());
                            for(int i=0; i<checkTypeKeyValueList.size(); i++){
                                checkTypeList.add(String.valueOf(i+1)+"="+checkTypeKeyValueList.get(i).getValue());
                            }
                        }

                        //权利人类型
                        if(dictsData.getResult().getRegisterType()!=null){
                            checkRegisterTypeKeyValueList.clear();
                            checkRegisterTypeList.clear();
                            checkRegisterTypeKeyValueList.addAll(dictsData.getResult().getRegisterType());
                            for(int i=0; i<checkRegisterTypeKeyValueList.size(); i++){
                                checkRegisterTypeList.add(String.valueOf(i+1)+"="+checkRegisterTypeKeyValueList.get(i).getValue());
                            }
                        }

                        //认领状态
                        if(dictsData.getResult().getAcceptCondition()!=null){
                            checkAcceptConTypeKeyValueList.addAll(dictsData.getResult().getAcceptCondition());
                            for(int i=0; i<checkAcceptConTypeKeyValueList.size(); i++){
                                checkAcceptConTypeList.add(String.valueOf(i+1)+"="+checkAcceptConTypeKeyValueList.get(i).getValue());
                            }
                        }
                    }

                    getTradeMarksList();

                } else {
                    Toast.makeText(TradeMarksListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(TradeMarksListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取注册商标列表
    private void getTradeMarksList(){
        String userId = loginBean.getResult().getUserId();
        String organId = loginBean.getResult().getOrganId();
        Map<String, String> map = new HashMap<String, String>();
//        map.put("userId", "90125e91c95730897efc5305d799da7c");
//        map.put("organId", "510000000");
        map.put("userId", userId);
        map.put("organId", organId);
        map.put("pageNo", "1");
        map.put("wsCodeReq", "03019991");

        getConditions(map);

        Call<TradeMarkItemListBean> call;
        call = ApiManager.tradeMarkApi.getTmRegInfoList(map);

        call.enqueue(new Callback<TradeMarkItemListBean>() {
            @Override
            public void onResponse(Response<TradeMarkItemListBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    TradeMarkItemListBean marksData = response.body();

                    if ((null == marksData) || (null == marksData.getResult())) {
                        Toast.makeText(TradeMarksListActivity.this, "获得商标数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ("已经没有下一页了".equals(marksData.getMessage())) {
                        TradeMarksListActivity.this.page = -1;
                        return;
                    }

                    if (null != marksData.getResult()) {
                        marksList.clear();
                        marksList.addAll(marksData.getResult());
                        marksAdapter = new RegisterMarksAdapter(TradeMarksListActivity.this, marksList);
                        searchRecyclerView.setAdapter(marksAdapter);
                    }

                } else {
                    Toast.makeText(TradeMarksListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(TradeMarksListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getConditions(Map<String, String> map) {

        if(toolbarEdit.getText()!=null && !toolbarEdit.getText().toString().trim().equals("")){
            map.put("keyword", toolbarEdit.getText().toString().trim());
        }

        Condition con = new Condition();
        if(date1txt.getText()!=null)
            con.setSpecialStartDate(date1txt.getText().toString());

        if(date2txt.getText()!=null)
            con.setSpecialEndDate(date2txt.getText().toString());

        con.setIsCommon(convertChooseOption(menuChooseOption.get(0)));
        con.setIsSolid(convertChooseOption(menuChooseOption.get(1)));
        con.setIsGeography(convertChooseOption(menuChooseOption.get(2)));
        con.setIsInternational(convertChooseOption(menuChooseOption.get(3)));
        con.setIsExistStatus(convertChooseOption(menuChooseOption.get(4)));
        if(typeProductChosen!=null)
            con.setTmType(typeProductChosen);
        if(typeAuthorChosen!=null)
            con.setRegisterType(typeAuthorChosen);
        if(typeGetChosen!=null)
            con.setIsAccept(typeGetChosen);
        if(managerDepartment!=null)
            con.setAreaOrganId(managerDepartment);
        if(registerDepartment!=null)
            con.setRegOrganId(registerDepartment);

        Gson gson = new GsonBuilder().create();
//        map.put("sampleCaseVo", submitCase.toString());
        map.put("condition", gson.toJson(con));

    }

    private String convertChooseOption(MarkChooseOption markChooseOption) {
        if(markChooseOption.getOptionValue().equals(MARK_CHOOSE_OPTION_NO_LIMITED))
            return "";
        else if(markChooseOption.getOptionValue().equals(MARK_CHOOSE_OPTION_YES))
            return "1";
        else
            return "0";
    }

    @OnClick({R.id.toolbar_clear, R.id.right_btn, R.id.searchresult_type, R.id.searchobligee_checktype,
            R.id.searchresult_result, R.id.searchresult_more, R.id.date1, R.id.date2, R.id.resetbtn, R.id.submitbtn,
            R.id.llDomination4Trademarks, R.id.llRegister4Trademarks})
    public void onClick(View view) {
//        final CusDatePickerDialog dateDialog;
        switch (view.getId()) {
            case R.id.toolbar_clear:
                toolbarEdit.setText("");
                toolbarClear.setVisibility(View.INVISIBLE);
                break;
            case R.id.right_btn:
                break;
            case R.id.searchresult_type://商标类型
                if (checkTypeList == null || checkTypeList.size() == 0){
                    Toast.makeText(TradeMarksListActivity.this, "商标类型选项为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //typeTxt.setTextColor(ContextCompat.getColor(this, R.color.red));
                View view1 = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style01, null);
                RecyclerView typeRecyclerView = (RecyclerView) view1.findViewById(R.id.pop_recyclerview);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                typeRecyclerView.setLayoutManager(layoutManager);
                typeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                typeAdapter = new SingleAdapter(this, checkTypeList);
                typeRecyclerView.setAdapter(typeAdapter);
                typeAdapter.setOnItemClickListener(new SingleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        typePopup.dismiss();
                        typeTxt.setText(checkTypeList.get(position).split("=")[1].trim() + " ▼");
                        for(KeyValue option : checkTypeKeyValueList)
                            if(option.getValue().equals(checkTypeList.get(position).split("=")[1].trim()))
                                typeProductChosen = option.getKey();
                    }
                });
                typePopup = new PopupWindow(this);
                typePopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                typePopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                typePopup.setContentView(view1);
                // 设置背景颜色变暗
                grayBg.setVisibility(View.VISIBLE);
                grayBg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_popin_bg));
                typePopup.setFocusable(true);// 取得焦点
                typePopup.setBackgroundDrawable(new ColorDrawable(0x00000000));
                typePopup.showAsDropDown(searchresultType);
                typePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        grayBg.setVisibility(View.GONE);
                        grayBg.setAnimation(AnimationUtils.loadAnimation(TradeMarksListActivity.this, R.anim.anim_popout_bg));
                    }
                });
                break;
            case R.id.searchobligee_checktype://权利人类型
                if (checkRegisterTypeList == null || checkRegisterTypeList.size() == 0){
                    Toast.makeText(TradeMarksListActivity.this, "权利人类型选项为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                View view2 = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style01, null);
                RecyclerView registerTypeView = (RecyclerView) view2.findViewById(R.id.pop_recyclerview);
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
                registerTypeView.setLayoutManager(layoutManager2);
                registerTypeView.setItemAnimator(new DefaultItemAnimator());
                registerTypeAdapter = new SingleAdapter(this, checkRegisterTypeList);
                registerTypeView.setAdapter(registerTypeAdapter);
                registerTypeAdapter.setOnItemClickListener(new SingleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        typePopup.dismiss();
                        obligeetypeTxt.setText(checkRegisterTypeList.get(position).split("=")[1].trim() + " ▼");
                        for(KeyValue option : checkRegisterTypeKeyValueList)
                            if(option.getValue().equals(checkRegisterTypeList.get(position).split("=")[1].trim()))
                                typeAuthorChosen = option.getKey();
                    }
                });
                typePopup = new PopupWindow(this);
                typePopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                typePopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                typePopup.setContentView(view2);
                // 设置背景颜色变暗
                grayBg.setVisibility(View.VISIBLE);
                grayBg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_popin_bg));
                typePopup.setFocusable(true);// 取得焦点
                typePopup.setBackgroundDrawable(new ColorDrawable(0x00000000));
                typePopup.showAsDropDown(searchresultType);
                typePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        grayBg.setVisibility(View.GONE);
                        grayBg.setAnimation(AnimationUtils.loadAnimation(TradeMarksListActivity.this, R.anim.anim_popout_bg));
                    }
                });
                break;
            case R.id.searchresult_result://认领状态
                if (checkAcceptConTypeList == null || checkAcceptConTypeList.size() == 0){
                    Toast.makeText(TradeMarksListActivity.this, "认领状态选项为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                View view3 = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style01, null);
                RecyclerView acceptConTypeView = (RecyclerView) view3.findViewById(R.id.pop_recyclerview);
                LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
                acceptConTypeView.setLayoutManager(layoutManager3);
                acceptConTypeView.setItemAnimator(new DefaultItemAnimator());
                acceptConditionAdapter = new SingleAdapter(this, checkAcceptConTypeList);
                acceptConTypeView.setAdapter(acceptConditionAdapter);
                acceptConditionAdapter.setOnItemClickListener(new SingleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        typePopup.dismiss();
                        claimStateTxt.setText(checkAcceptConTypeList.get(position).split("=")[1].trim() + " ▼");
                        for(KeyValue option : checkAcceptConTypeKeyValueList)
                            if(option.getValue().equals(checkAcceptConTypeList.get(position).split("=")[1].trim()))
                                typeGetChosen = option.getKey();
                    }
                });
                typePopup = new PopupWindow(this);
                typePopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                typePopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                typePopup.setContentView(view3);
                // 设置背景颜色变暗
                grayBg.setVisibility(View.VISIBLE);
                grayBg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_popin_bg));
                typePopup.setFocusable(true);// 取得焦点
                typePopup.setBackgroundDrawable(new ColorDrawable(0x00000000));
                typePopup.showAsDropDown(searchresultType);
                typePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        grayBg.setVisibility(View.GONE);
                        grayBg.setAnimation(AnimationUtils.loadAnimation(TradeMarksListActivity.this, R.anim.anim_popout_bg));
                    }
                });
                break;
            case R.id.searchresult_more:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.date1:
//                dateDialog = CusDatePickerDialog.newInstance("选择起始时间");
                new DatePickerDialog(TradeMarksListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        System.out.println(String.format("%d-%d-%d", i, i1 + 1, i2));
                        String startDate = "";
                        if (i1 + 1 < 10) {
                            if (i2 + 1 < 10)
                                startDate = String.format("%d-0%d-0%d", i, i1 + 1, i2);
                            else
                                startDate = String.format("%d-0%d-%d", i, i1 + 1, i2);
                        } else {
                            if (i2 + 1 < 10)
                                startDate = String.format("%d-%d-0%d", i, i1 + 1, i2);
                            else
                                startDate = String.format("%d-%d-%d", i, i1 + 1, i2);
                        }
                        if(startDate.equals(""))
                            return;
                        if(date2txt.getText()!=null && !"".equals(date2txt.getText().toString())){
                            String date2 = date2txt.getText().toString();
                            if(Date.valueOf(startDate).after(Date.valueOf(date2))){
                                Toast.makeText(TradeMarksListActivity.this, "起始时间不能大于截止时间", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        if (isDate1null) {
                            date1.setBackgroundResource(R.drawable.rounded_rect_blueline);
                            isDate1null = false;
                        }
                        date1txt.setText(startDate);
                    }
                }, 2016, 1, 1).show();

//                dateDialog.show(getSupportFragmentManager(), "CLRQS");
                break;
            case R.id.date2:
//                dateDialog = CusDatePickerDialog.newInstance("选择截止时间");
                new DatePickerDialog(TradeMarksListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        System.out.println(String.format("%d-%d-%d", i, i1 + 1, i2));
                        String endDate = "";
                        if (i1 + 1 < 10) {
                            if (i2 + 1 < 10)
                                endDate=String.format("%d-0%d-0%d", i, i1 + 1, i2);
                            else
                                endDate=String.format("%d-0%d-%d", i, i1 + 1, i2);
                        } else {
                            if (i2 + 1 < 10)
                                endDate=String.format("%d-%d-0%d", i, i1 + 1, i2);
                            else
                                endDate=String.format("%d-%d-%d", i, i1 + 1, i2);
                        }
                        if(endDate.equals(""))
                            return;
                        if (date1txt!=null && !"".equals(date1txt.getText().toString())){
                            String date1 = date1txt.getText().toString();
                            if(Date.valueOf(endDate).before(Date.valueOf(date1))){
                                Toast.makeText(TradeMarksListActivity.this, "截止时间不能小于起始时间", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if (isDate2null) {
                            date2.setBackgroundResource(R.drawable.rounded_rect_blueline);
                            isDate2null = false;
                        }
                        date2txt.setText(endDate);
                    }
                }, 2016, 1, 1).show();
                break;
            case R.id.resetbtn:
                date1txt.setText("");
                date2txt.setText("");
                tvDominationNameSelected.setText("");
                tvRegisterNameSelected.setText("");
                for(MarkChooseOption option:menuChooseOption)
                    option.setOptionValue(MARK_CHOOSE_OPTION_NO_LIMITED);
                menuAdapter.notifyDataSetChanged();
                tvDominationNameSelected.setText("");
                tvRegisterNameSelected.setText("");
                toolbarEdit.setTag("");
                typeTxt.setText("商标类型 ▼");
                obligeetypeTxt.setText("权利人类型 ▼");
                claimStateTxt.setText("认领状态 ▼");
                typeProductChosen = null;
                typeAuthorChosen = null;
                typeGetChosen = null;
                managerDepartment = null;
                registerDepartment = null;
                break;
            case R.id.submitbtn:
                drawerLayout.closeDrawers();
                getTradeMarksList();
                break;
            case R.id.llDomination4Trademarks:
                startActivityForResult(new Intent(this, DeptActivity.class), 1);
//                Intent intent = new Intent(TradeMarksListActivity.this, DominationSelectActivity.class);
//                startActivityForResult(intent, 1);
                break;
            case R.id.llRegister4Trademarks:
                startActivityForResult(new Intent(this, DeptActivity.class), 2);
//                Intent intent2 = new Intent(TradeMarksListActivity.this, DominationSelectActivity.class);
//                startActivityForResult(intent2, 2);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Bundle b = data.getExtras(); //data为B中回传的Intent
            String organId = b.getString("organId");//str即为回传的值
            String organName = b.getString("organName");
            if(requestCode == 1){
                managerDepartment = organId;
                tvDominationNameSelected.setText(organName);
            }
            if(requestCode == 2){
                registerDepartment = organId;
                tvRegisterNameSelected.setText(organName);
            }
//            if(requestCode == 1){
//                tvDominationNameSelected.setText(data.getStringExtra("DominationName"));
//            }
//            if(requestCode == 2){
//                tvRegisterNameSelected.setText(data.getStringExtra("DominationName"));
//            }
        }

    }


    ////检查editText是否输入
    public boolean isEditTextEmpty(EditText tv) {
        boolean flag = false;
        if (tv.getText() == null
                || tv.getText().toString().trim().equals("")) {
            flag = true;
        }
        return flag;
    }

    public class MarkChooseMenAdapter extends BaseAdapter{

        private LayoutInflater inflater;
        private Context adapterContext;
        private List<MarkChooseOption> markChooseOptionList;

        public MarkChooseMenAdapter(Context mContext, List<MarkChooseOption> mList){
            adapterContext = mContext;
            markChooseOptionList = mList;
            inflater = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return markChooseOptionList.size();
        }

        @Override
        public Object getItem(int i) {
            return markChooseOptionList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int pos, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if(viewHolder == null){
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_trade_mark_inqury_menu, viewGroup, false);
                viewHolder.tvOptionName = (TextView)convertView.findViewById(R.id.tvName4ItemTradeMarkInquiryMenu);
                viewHolder.llOptionNoLimit = (LinearLayout)convertView.findViewById(R.id.llOptionNoLimit4ItemTradeMarkInquiryMenu);
                viewHolder.llOptionYes = (LinearLayout)convertView.findViewById(R.id.llOptionYes4ItemTradeMarkInquiryMenu);
                viewHolder.llOptionNo = (LinearLayout)convertView.findViewById(R.id.llOptionNo4ItemTradeMarkInquiryMenu);
                viewHolder.tvOptionNoLimit = (TextView)convertView.findViewById(R.id.tvOptionNoLimit4ItemTradeMarkInquiryMenu);
                viewHolder.tvOptionYes = (TextView)convertView.findViewById(R.id.tvOptionYes4ItemTradeMarkInquiryMenu);
                viewHolder.tvOptionNo = (TextView)convertView.findViewById(R.id.tvOptionNo4ItemTradeMarkInquiryMenu);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.tvOptionName.setText(markChooseOptionList.get(pos).getOptionName());
            if(markChooseOptionList.get(pos).getOptionValue().equals(MARK_CHOOSE_OPTION_NO_LIMITED)){
                viewHolder.llOptionNoLimit.setBackgroundResource(R.drawable.rounded_rect_blueline);
                viewHolder.tvOptionNoLimit.setTextColor(ContextCompat.getColor(adapterContext, R.color.blue));
            }else if(markChooseOptionList.get(pos).getOptionValue().equals(MARK_CHOOSE_OPTION_YES)){
                viewHolder.llOptionYes.setBackgroundResource(R.drawable.rounded_rect_blueline);
                viewHolder.tvOptionYes.setTextColor(ContextCompat.getColor(adapterContext, R.color.blue));
            }else{
                viewHolder.llOptionNo.setBackgroundResource(R.drawable.rounded_rect_blueline);
                viewHolder.tvOptionNo.setTextColor(ContextCompat.getColor(adapterContext, R.color.blue));
            }
            viewHolder.llOptionNoLimit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    markChooseOptionList.get(pos).setOptionValue(MARK_CHOOSE_OPTION_NO_LIMITED);
                    notifyDataSetChanged();
                }
            });
            viewHolder.llOptionYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    markChooseOptionList.get(pos).setOptionValue(MARK_CHOOSE_OPTION_YES);
                    notifyDataSetChanged();
                }
            });
            viewHolder.llOptionNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    markChooseOptionList.get(pos).setOptionValue(MARK_CHOOSE_OPTION_NO);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        private class ViewHolder{
            private TextView tvOptionName;
            private LinearLayout llOptionNoLimit;
            private LinearLayout llOptionYes;
            private LinearLayout llOptionNo;
            private TextView tvOptionNoLimit;
            private TextView tvOptionYes;
            private TextView tvOptionNo;
        }
    }

    public class MarkChooseOption{
        private String optionName;
        private String optionValue = MARK_CHOOSE_OPTION_NO_LIMITED;

        public String getOptionName() {
            return optionName;
        }
        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }
        public String getOptionValue() {
            return optionValue;
        }
        public void setOptionValue(String optionValue) {
            this.optionValue = optionValue;
        }
    }

    public class RegisterMarksAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkVoList> marksList;

        public RegisterMarksAdapter(Context context, List<TradeMarkVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() {
            return marksList.size();
        }

        @Override
        public Object getItem(int position) {
            return marksList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_trade_mark_list, parent, false);
                holder.tv_MarkName = (TextView) convertView.findViewById(R.id.tvMarkName4ItemTradeMarkList);
                holder.tv_RegisterNum = (TextView) convertView.findViewById(R.id.tvRegisterNum4ItemTradeMarkList);
                holder.tv_OwnerName = (TextView) convertView.findViewById(R.id.tvOwnerName4ItemTradeMarkList);
                holder.tv_OwnerAddress = (TextView) convertView.findViewById(R.id.tvOwnerAddress4ItemTradeMarkList);
                holder.tv_DominationName = (TextView) convertView.findViewById(R.id.tvDominationName4ItemTradeMarkList);
                holder.tv_AcceptSate = (TextView) convertView.findViewById(R.id.tvMarkAcceptState4ItemTradeMarkList);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if(marksList.get(position).getIsAccept()!=null){
                if(marksList.get(position).getIsAccept().equals("1")){
                    holder.tv_AcceptSate.setText("已认领");
                    holder.tv_AcceptSate.setBackgroundColor(ContextCompat.getColor(TradeMarksListActivity.this, R.color.accept_green));
                }
                if(marksList.get(position).getIsAccept().equals("0")){
                    holder.tv_AcceptSate.setText("未认领");
                    holder.tv_AcceptSate.setBackgroundColor(ContextCompat.getColor(TradeMarksListActivity.this, R.color.unaccept_grey));
                }
            }

            holder.tv_MarkName.setText(marksList.get(position).getBrandName());
            holder.tv_RegisterNum.setText(marksList.get(position).getRegNo());
            holder.tv_OwnerName.setText(marksList.get(position).getRegisterName());
            holder.tv_OwnerAddress.setText(marksList.get(position).getChProposerAddr());
            holder.tv_DominationName.setText(marksList.get(position).getAreaOrganId());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_MarkName;
            private TextView tv_RegisterNum;
            private TextView tv_OwnerName;
            private TextView tv_OwnerAddress;
            private TextView tv_DominationName;
            private TextView tv_AcceptSate;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //查询Condition对象
    public class Condition{
        private String tmType;  //	商标类型
        private String registerType;  //	权利人类型
        private String isAccept;  //	认领状态
        private String specialStartDate;  //	有效期起始
        private String specialEndDate;  //	有效期结束
        private String areaOrganId;  //	管辖机关代码
        private String regOrganId;  //	登记机关代码
        private String areaIsSelf;  //、regIsSelf	是否只查本级
        private String isCommon;  //	是否共有商标
        private String isSolid;  //	是否立方体商标
        private String isGeography;  //	是否地理标志
        private String isInternational;  //	是否国际注册
        private String isExistStatus;  //	是否有效商标

        public String getAreaIsSelf() {
            return areaIsSelf;
        }

        public void setAreaIsSelf(String areaIsSelf) {
            this.areaIsSelf = areaIsSelf;
        }

        public String getAreaOrganId() {
            return areaOrganId;
        }

        public void setAreaOrganId(String areaOrganId) {
            this.areaOrganId = areaOrganId;
        }

        public String getIsAccept() {
            return isAccept;
        }

        public void setIsAccept(String isAccept) {
            this.isAccept = isAccept;
        }

        public String getIsCommon() {
            return isCommon;
        }

        public void setIsCommon(String isCommon) {
            this.isCommon = isCommon;
        }

        public String getIsExistStatus() {
            return isExistStatus;
        }

        public void setIsExistStatus(String isExistStatus) {
            this.isExistStatus = isExistStatus;
        }

        public String getIsGeography() {
            return isGeography;
        }

        public void setIsGeography(String isGeography) {
            this.isGeography = isGeography;
        }

        public String getIsInternational() {
            return isInternational;
        }

        public void setIsInternational(String isInternational) {
            this.isInternational = isInternational;
        }

        public String getIsSolid() {
            return isSolid;
        }

        public void setIsSolid(String isSolid) {
            this.isSolid = isSolid;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getRegOrganId() {
            return regOrganId;
        }

        public void setRegOrganId(String regOrganId) {
            this.regOrganId = regOrganId;
        }

        public String getSpecialEndDate() {
            return specialEndDate;
        }

        public void setSpecialEndDate(String specialEndDate) {
            this.specialEndDate = specialEndDate;
        }

        public String getSpecialStartDate() {
            return specialStartDate;
        }

        public void setSpecialStartDate(String specialStartDate) {
            this.specialStartDate = specialStartDate;
        }

        public String getTmType() {
            return tmType;
        }

        public void setTmType(String tmType) {
            this.tmType = tmType;
        }
    }

}

