package com.wondersgroup.commerce.fgdj.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.adapter.ListInfoAdapter;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.FgdjEntList;
import com.wondersgroup.commerce.fgdj.bean.FgdjEntListBean;
import com.wondersgroup.commerce.interface_.TextChanger;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.DateUtil;
import com.wondersgroup.commerce.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.ynwq.widget.CountBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/4/26.
 */

public class ListInfoActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.layout_select)
    ViewGroup layoutSelect;
    @Bind(R.id.layout_head)
    ViewGroup layoutHead;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.layout_search_item)
    ViewGroup layoutSearchItem;
    @Bind(R.id.layout_search)
    ViewGroup layoutSearch;
    @Bind(R.id.tv_search_content)
    TextView tvSearchContent;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.layout_search_bg)
    FrameLayout layoutSearchBg;
    @Bind(R.id.layout_error)
    View layoutError;

    private PopupWindow popupWindow;
    private PopupWindow popupWindowDatePicker;
    private NumberPicker pickerYear,pickerMonth,pickerDay;
    private TextView tvDateNo,tvDateOk;
    private TextView tvStartDate,tvEndDate;
    private TextView tvReSelect,tvSure;
    private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
    private ListInfoAdapter adapter;
    private List<FgdjEntListBean> data;
    private Dialog loadingDialog;
    private int clickId;
    private HashMap hashMap;

    private String type;
    private int loadType = Constants.LOAD_REFRESH;
    private int pageNo = 1,pageMax = 1 ,totalCount = 1;
    private CountBar countBar;
    private TotalLoginBean loginBean;
    private boolean isLoaded;//true:如果正在加载中：取消滑动加载更多

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvivity_list_info);
        ButterKnife.bind(this);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        hashMap = new HashMap();
        hashMap.put(Constants.CUR_ORGAN_ID, Constants.CUR_ORGAN_ID_VALUE);
        hashMap.put(Constants.PAGE_NO,pageNo);
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());

        type = getIntent().getStringExtra(Constants.TYPE);
        if(Constants.CHA_XUN.equals(type)){
            hashMap.put(Constants.ENT_NAME,getIntent().getStringExtra(Constants.ENT_NAME));
            hashMap.put(Constants.REG_NO,getIntent().getStringExtra(Constants.REG_NO));

            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("非公党建信息");

            layoutSearch.setVisibility(View.GONE);
            layoutSelect.setVisibility(View.GONE);
        }

        layoutSelect.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        layoutSearchItem.setOnClickListener(this);
        layoutSearch.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        etSearch.setOnClickListener(this);
        etSearch.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)){
                    layoutSearchItem.setVisibility(View.VISIBLE);
                    tvSearchContent.setText(s);
                }else
                    layoutSearchItem.setVisibility(View.GONE);
            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        data = new ArrayList();
        adapter = new ListInfoAdapter(data,this);
        if (type.equals(Constants.CHA_XUN))
            adapter.setType(0);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(countBar!=null && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) {
                        if (countBar.getDialog() != null)
                            countBar.getDialog().show();
                        if (countBar.isVisible()) {
                            countBar.setCur(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                            countBar.setTotal(totalCount);
                        }
                }

                if ((linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount()-1) &&
                        (pageNo < pageMax) && isLoaded){
                    View view = LayoutInflater.from(ListInfoActivity.this).inflate(R.layout.loading_layout,null,false);
                    view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    adapter.addFooterView(view);
                    loadType = Constants.LOAD_MORE;
                    isLoaded = false;
                    pageNo++;
                    hashMap.put(Constants.PAGE_NO,pageNo);
                    getData();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState== RecyclerView.SCROLL_STATE_IDLE){
                    if(countBar!=null) {
                        if (countBar.isVisible())
                            countBar.getDialog().hide();
                    }
                }else if(newState== RecyclerView.SCROLL_STATE_DRAGGING){
                    synchronized (this) {
                        if (countBar == null) {
                            if(recyclerView.getAdapter()!=null) {
                                countBar = CountBar.newInstance(totalCount);
                                countBar.show(getSupportFragmentManager(), "CountBar");
                            }
                        }
                    }
                }
            }
        });

        loadingDialog = LoadingDialog.showCanCancelable(this);
        loadingDialog.show();

        getData();
        getDic();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_select:
                initPopupWindow();
                break;
            case R.id.tv_re_select:
                initPopupWindowData();
                break;
            case R.id.tv_sure:
                popupWindow.dismiss();
                loadType = Constants.LOAD_REFRESH;
                if (!"初始日期".equals(tvStartDate.getText()))
                    hashMap.put(Constants.OPER_TIME_START,tvStartDate.getText());
                if (!"结束日期".equals(tvEndDate.getText()))
                    hashMap.put(Constants.OPER_TIME_END,tvEndDate.getText());
                getData();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.layout_search_item:
                hideSearchViews(1);
                actSearch();
                break;
            case R.id.layout_search:
            case R.id.et_search:
                etSearch.setFocusable(true);
                etSearch.setFocusableInTouchMode(true);
                etSearch.requestFocus();
                etSearch.requestFocusFromTouch();
                if (!TextUtils.isEmpty(etSearch.getText())){
                    layoutSearchItem.setVisibility(View.VISIBLE);
                    tvSearchContent.setText(etSearch.getText());
                }
                imgBack.setVisibility(View.GONE);
                layoutSelect.setVisibility(View.GONE);
                tvCancel.setVisibility(View.VISIBLE);
                layoutSearchBg.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etSearch,0);
                break;
            case R.id.tv_cancel:
                hideSearchViews(0);
                break;
            case R.id.tv_start_date:
                createDatePicker(R.id.tv_start_date);
                break;
            case R.id.tv_end_date:
                createDatePicker(R.id.tv_end_date);
                break;
            case R.id.tv_date_ok:
                if (clickId == R.id.tv_start_date){
                    tvStartDate.setText(pickerYear.getValue()+"-"+changeTime(pickerMonth.getValue())+"-"+changeTime(pickerDay.getValue()));
                }else if (clickId == R.id.tv_end_date){
                    tvEndDate.setText(pickerYear.getValue()+"-"+changeTime(pickerMonth.getValue())+"-"+changeTime(pickerDay.getValue()));
                }
                popupWindowDatePicker.dismiss();
                break;
            case R.id.tv_date_no:
            case R.id.v_extra:
                popupWindowDatePicker.dismiss();
                break;
        }
    }

    private void getData(){
        Call<Result<FgdjEntList>> call = ApiManager.fgdjApi.queruFgdjEntList(hashMap);
        call.enqueue(new Callback<Result<FgdjEntList>>() {
            @Override
            public void onResponse(Response<Result<FgdjEntList>> response, Retrofit retrofit) {
                if (loadingDialog.isShowing())
                    loadingDialog.dismiss();
                isLoaded = true;
                adapter.removeFooterView();
                if (response.body() != null && response.body().getCode()==0){
                    if (loadType == Constants.LOAD_REFRESH)
                        data.clear();

                    pageMax = response.body().getObject().getPageCount();
                    totalCount = response.body().getObject().getTotalRecord();
                    data.addAll(response.body().getObject().getResultList());
                    adapter.notifyDataSetChanged();
                    if (loadType == Constants.LOAD_REFRESH)
                        recyclerView.setScrollY(0);
                }

                if (data.size() == 0)
                    layoutError.setVisibility(View.VISIBLE);
                else
                    layoutError.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {
                if (loadingDialog.isShowing())
                    loadingDialog.dismiss();

                layoutError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void getDic(){
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
        Call<Result<DicBean>> call = ApiManager.fgdjApi.getDics(hashMap);
        call.enqueue(new Callback<Result<DicBean>>() {
            @Override
            public void onResponse(Response<Result<DicBean>> response, Retrofit retrofit) {
                if(response.body()!=null){
                    if (response.body().getCode() == 0){
                        DicBean dicBean = response.body().getObject();
                        Hawk.put(Constants.DIC,dicBean);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void initPopupWindow(){
        View view = LayoutInflater.from(this).inflate(R.layout.popup_djxx,null,false);
        radioGroup1 = (RadioGroup) view.findViewById(R.id.radio_group_1);
        if (hashMap.get(Constants.FGDJ_ENT_TYPE) != null){
            String tag1 = hashMap.get(Constants.FGDJ_ENT_TYPE).toString();
            if (!TextUtils.isEmpty(tag1))
                initRadioData(radioGroup1,tag1);
        }
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId != -1){
                    RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                    if ("企业".equals(radioButton.getText().toString())){
                        hashMap.put(Constants.FGDJ_ENT_TYPE,"1");
                    }else {
                        hashMap.put(Constants.FGDJ_ENT_TYPE,"2");
                    }
                }
            }
        });

        radioGroup2 = (RadioGroup) view.findViewById(R.id.radio_group_2);
        if (hashMap.get(Constants.EXIST_STATUS) != null){
            String tag2 = hashMap.get(Constants.EXIST_STATUS).toString();
            if (!TextUtils.isEmpty(tag2))
                initRadioData(radioGroup2,tag2);
        }
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId != -1){
                    RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                    String text = radioButton.getText().toString();
                    if ("存续".equals(text)){
                        hashMap.put(Constants.EXIST_STATUS,"1");
                    }else if ("吊销(未注销)".equals(text)){
                        hashMap.put(Constants.EXIST_STATUS,"2");
                    }else if ("吊销(已注销)".equals(text)){
                        hashMap.put(Constants.EXIST_STATUS,"3");
                    }else if ("注销".equals(text)){
                        hashMap.put(Constants.EXIST_STATUS,"4");
                    }else if ("撤销".equals(text)){
                        hashMap.put(Constants.EXIST_STATUS,"5");
                    }
                }
            }
        });

        radioGroup3 = (RadioGroup) view.findViewById(R.id.radio_group_3);
        if (hashMap.get(Constants.IS_CLAIM_ENT) != null){
            String tag3 = hashMap.get(Constants.IS_CLAIM_ENT).toString();
            if (!TextUtils.isEmpty(tag3))
                initRadioData(radioGroup3,tag3);
        }
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId != -1){
                    RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                    if ("未认领".equals(radioButton.getText().toString())){
                        hashMap.put(Constants.IS_CLAIM_ENT,"0");
                    }else {
                        hashMap.put(Constants.IS_CLAIM_ENT,"1");
                    }
                }
            }
        });

        radioGroup4 = (RadioGroup) view.findViewById(R.id.radio_group_4);
        if (hashMap.get(Constants.IS_BASE) != null){
            String tag4 = hashMap.get(Constants.IS_BASE).toString();
            if (!TextUtils.isEmpty(tag4))
                initRadioData(radioGroup4,tag4);
        }
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId != -1){
                    RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                    if ("是".equals(radioButton.getText().toString())){
                        hashMap.put(Constants.IS_BASE,"1");
                    }else {
                        hashMap.put(Constants.IS_BASE,"0");
                    }
                }
            }
        });
        tvReSelect = (TextView) view.findViewById(R.id.tv_re_select);
        tvReSelect.setOnClickListener(this);
        tvSure = (TextView) view.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(this);
        tvStartDate = (TextView) view.findViewById(R.id.tv_start_date);
        if (hashMap.get(Constants.OPER_TIME_START) != null){
            String startDate = hashMap.get(Constants.OPER_TIME_START).toString();
            if (!TextUtils.isEmpty(startDate))
                tvStartDate.setText(startDate);
        }
        tvStartDate.setOnClickListener(this);
        tvEndDate = (TextView) view.findViewById(R.id.tv_end_date);
        if (hashMap.get(Constants.OPER_TIME_END) != null){
            String endDate = hashMap.get(Constants.OPER_TIME_END).toString();
            if (!TextUtils.isEmpty(endDate))
                tvEndDate.setText(endDate);
        }
        tvEndDate.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        int height = (int) DWZH.dp2pt(this,400);
        popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT
            ,height,true);
        popupWindow.showAsDropDown(layoutHead);
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.7f;
        getWindow().setAttributes(layoutParams);
    }

    public void initRadioData(RadioGroup group,String tag){
        for (int i=0;i<group.getChildCount();i++){
            RadioButton radioButton = (RadioButton) group.getChildAt(i);
            if (tag.equals(radioButton.getTag())){
                group.check(radioButton.getId());
                break;
            }
        }
    }

    public void initPopupWindowData(){
        radioGroup1.clearCheck();
        hashMap.put(Constants.FGDJ_ENT_TYPE,"");
        radioGroup2.clearCheck();
        hashMap.put(Constants.EXIST_STATUS,"");
        radioGroup3.clearCheck();
        hashMap.put(Constants.IS_CLAIM_ENT,"");
        radioGroup4.clearCheck();
        hashMap.put(Constants.IS_BASE,"");
        tvStartDate.setText("初始日期");
        hashMap.put(Constants.OPER_TIME_START,"");
        tvEndDate.setText("结束日期");
        hashMap.put(Constants.OPER_TIME_END,"");
    }

    private void createDatePicker(int id){
        clickId = id;
        View view  = View.inflate(this, R.layout.date_picker,null);
        pickerYear = (NumberPicker) view.findViewById(R.id.picker_year);
        pickerMonth = (NumberPicker) view.findViewById(R.id.picker_month);
        pickerDay = (NumberPicker) view.findViewById(R.id.picker_day);
        tvDateNo = (TextView) view.findViewById(R.id.tv_date_no);
        tvDateNo.setOnClickListener(this);
        tvDateOk = (TextView) view.findViewById(R.id.tv_date_ok);
        tvDateOk.setOnClickListener(this);
        pickerYear.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        pickerYear.setMaxValue(3017);
        pickerYear.setMinValue(0);
        pickerYear.setValue(DateUtil.getNowYear());
        pickerMonth.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        pickerMonth.setMaxValue(12);
        pickerMonth.setMinValue(1);
        pickerMonth.setValue(DateUtil.getNowMonth());
        pickerDay.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        pickerDay.setMaxValue(31);
        pickerDay.setMinValue(1);
        pickerDay.setValue(DateUtil.getNowDay());
        popupWindowDatePicker = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindowDatePicker.showAtLocation(getWindow().getDecorView(),Gravity.BOTTOM,0,0);
        popupWindowDatePicker.setAnimationStyle(R.style.popup_animation_1);
        View viewExtra = view.findViewById(R.id.v_extra);
        viewExtra.setOnClickListener(this);
    }

    private void actSearch(){
        hashMap.put(Constants.ENT_NAME,"");
        hashMap.put(Constants.REG_NO,"");
        String text = tvSearchContent.getText().toString();
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");//如果有汉字，就添加到ent_name中
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            hashMap.put(Constants.ENT_NAME,text);
        }else {
            hashMap.put(Constants.REG_NO,text);
        }

        pageNo = 1;
        hashMap.put(Constants.PAGE_NO,pageNo);
        loadType = Constants.LOAD_REFRESH;

        loadingDialog = LoadingDialog.showCanCancelable(this);
        loadingDialog.show();

        getData();
    }

    public void hideSearchViews(int type){
        imgBack.setVisibility(View.VISIBLE);
        layoutSelect.setVisibility(View.VISIBLE);
        tvCancel.setVisibility(View.GONE);
        layoutSearchItem.setVisibility(View.GONE);
        layoutSearchBg.setVisibility(View.GONE);
        etSearch.clearFocus();
        if (type == 0)
            etSearch.setText("");
        etSearch.setFocusable(false);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(),0);
    }

    private static String changeTime(int time){
        if (time < 10)
            return "0" + time;
        return time+"";
    }
}
