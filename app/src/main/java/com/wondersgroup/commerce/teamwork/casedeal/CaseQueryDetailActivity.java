package com.wondersgroup.commerce.teamwork.casedeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.utils.DynamicWidgetUtils;
import com.wondersgroup.commerce.utils.TableRowUtils;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.widget.TableRow;

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

/**
 * Created by Lee on 2016/3/2.
 * 案件详情（案件总览、立案信息）
 */
public class CaseQueryDetailActivity extends AppCompatActivity {

    private String TAG = "CaseQueryDetailActivity";
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    private RadioGroup radioGroupDealType;              //标签组（案件总览、立案信息）
    private RadioButton caseBrowseRadioButton;          //案件总览按钮
    private ScrollView caseBrowseScrollView;            //案件总览ScrollView
    private ScrollView caseRegisterScrollView;          //立案信息ScrollView
    private LinearLayout caseBrowseLinearlayout;        //案件总览LinearLayout(动态控件)
    private LinearLayout caseRegisterLinearlayout;      //立案信息LinearLayout(动态控件)

    private int viewTagNo = 0;                          //立案信息动态控件tag
    private List<DataVolume> componentCaseBrowseList;   //案件总览动态控件对象列表
    private ArrayList<EditText> arrayBrowseEdittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象
    private ArrayList<EditText> arrayRegisterEdittext = new ArrayList<EditText>();
    LinearLayout.LayoutParams paramsDivider = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,2);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramstemp = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsedittext = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);

    private CaseInvestigateDetail caseDetail;           //立案信息对象
    private String clueNo;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_query_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        paramstemp.weight=2;
        paramsedittext.weight   =   5;
        radioGroupDealType = (RadioGroup)this.findViewById(R.id.deal_type_radioGroup);
        caseBrowseRadioButton = (RadioButton)this.findViewById(R.id.rb_browse_case_tab);
        caseBrowseScrollView = (ScrollView)this.findViewById(R.id.case_browse_ScrollView);
        caseRegisterScrollView = (ScrollView)this.findViewById(R.id.case_register_ScrollView);
        caseBrowseLinearlayout = (LinearLayout)this.findViewById(R.id.case_browse_linearlayout);
        caseRegisterLinearlayout = (LinearLayout)this.findViewById(R.id.case_register_linearlayout);
        caseBrowseRadioButton.setChecked(true);//默认选择“案件总览”

        clueNo = getIntent().getStringExtra("clueNo");
        if (TextUtils.isEmpty(clueNo))
            return;
        initView();
        initData();
        getCaseRegisterData();
    }

    //获得立案信息数据
    private void getCaseRegisterData() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010005");
        map.put("clueNo", clueNo);
        String url = "";
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.INVESTIGATE_CASE_DETAIL;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.INVESTIGATE_CASE_DETAIL;
        Call<CaseInvestigateDetail> call = ApiManager.caseApi.getCaseRegDetail(url,map);
        call.enqueue(new Callback<CaseInvestigateDetail>() {
            @Override
            public void onResponse(Response<CaseInvestigateDetail> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    caseDetail = response.body();

                    if ((null == caseDetail) || (null == caseDetail.getResult())) {
                        Toast.makeText(CaseQueryDetailActivity.this, "获得立案信息数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Log.d(TAG, "-----------caseDetail.getCaseInfo().getCaseName() = " + caseDetail.getResult().getCaseName());
                    addBaseView();//添加基本信息

                    //添加主要当事人信息
                    if (null != caseDetail.getResult().getDataVoList() && caseDetail.getResult().getDataVoList().size() > 1)
                        for (DataVolume voData : caseDetail.getResult().getDataVoList()) {
                            Log.d(TAG, "=Key = " + voData.getKey() + "---Type =" + voData.getType() + "---Value=" + voData.getValue());
                            addPersonView(voData);
                        }

                    //添加其他当事人列表
                    if (null != caseDetail.getResult().getLitigtList() && caseDetail.getResult().getLitigtList().size() > 0) {
                        for (CaseInvestigateDetail.Result.LitigtVolume litigtData : caseDetail.getResult().getLitigtList()) {
                            addLitigtView(litigtData);
                        }
                    }

                } else {
                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(CaseQueryDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseQueryDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //案件总览
    private void initData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010004");
        map.put("clueNo", clueNo);
        String url = "";
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_GENERAL_DETAIL;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_GENERAL_DETAIL;
        Call<DynamicComponentObject> call = ApiManager.caseApi.getCaseGeneralDetail(url,map);

        call.enqueue(new Callback<DynamicComponentObject>() {
            @Override
            public void onResponse(Response<DynamicComponentObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final DynamicComponentObject dynamicComponentObject = response.body();

                    if ((null == dynamicComponentObject) || (null == dynamicComponentObject.getResult())) {
                        Toast.makeText(CaseQueryDetailActivity.this, "案件总览数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else
                        componentCaseBrowseList = dynamicComponentObject.getResult();

                    //添加动态控件
                    TableRowUtils utils = new TableRowUtils(CaseQueryDetailActivity.this, caseBrowseLinearlayout, componentCaseBrowseList);
                    utils.build();
//                    if (null != componentCaseBrowseList && componentCaseBrowseList.size() > 1) {
//                        dynamicWidgetUtils = new DynamicWidgetUtils(CaseQueryDetailActivity.this, componentCaseBrowseList, arrayBrowseEdittext);
//                        dynamicWidgetUtils.addComponents(caseBrowseLinearlayout);
//                    }

                } else {
                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(CaseQueryDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseQueryDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        title.setText("案件详情");

        //标签
        radioGroupDealType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) CaseQueryDetailActivity.this.findViewById(checkedId);

                if (radioButton.getText().equals("案件总览")) {
                    Log.d(TAG, "focus on radioButton.getText() = " + radioButton.getText());
                    caseBrowseScrollView.setVisibility(View.VISIBLE);
                    caseRegisterScrollView.setVisibility(View.GONE);
                } else {//立案信息
                    Log.d(TAG, "focus on radioButton.getText() = " + radioButton.getText());
                    caseBrowseScrollView.setVisibility(View.GONE);
                    caseRegisterScrollView.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    //添加基本信息
    private void addBaseView(){

        TableRow caseName = new TableRow.Builder(this)
                .title("案件名称")
                .content(caseDetail.getResult().getCaseName())
                .build();
        caseRegisterLinearlayout.addView(caseName);
        TableRow caseDistrict = new TableRow.Builder(this)
                .title("所在区划")
                .content(caseDetail.getResult().getCaseCedistrict())
                .build();
        caseRegisterLinearlayout.addView(caseDistrict);
        TableRow caseSpot = new TableRow.Builder(this)
                .title("案发地点")
                .content(caseDetail.getResult().getCaseSpot())
                .build();
        caseRegisterLinearlayout.addView(caseSpot);
        TableRow caseTime = new TableRow.Builder(this)
                .title("案发时间")
                .content(caseDetail.getResult().getCaseTime())
                .build();
        caseRegisterLinearlayout.addView(caseTime);
        TableRow clueType = new TableRow.Builder(this)
                .title("案件来源")
                .content(caseDetail.getResult().getClueType())
                .build();
        caseRegisterLinearlayout.addView(clueType);
        TableRow caseReason = new TableRow.Builder(this)
                .title("核查情况及立案(不予立案)理由")
                .content(caseDetail.getResult().getCaseReason())
                .build();
        caseRegisterLinearlayout.addView(caseReason);
        TableRow deptOpi = new TableRow.Builder(this)
                .title("办案机构负责人意见")
                .content(caseDetail.getResult().getDepOpi())
                .build();
        caseRegisterLinearlayout.addView(deptOpi);
        TableRow organOpi = new TableRow.Builder(this)
                .title("机关负责人意见")
                .content(caseDetail.getResult().getOrganOpi())
                .build();
        caseRegisterLinearlayout.addView(organOpi);

    }

    private void addTextViewWidget(String tabName, String tabValue){
        viewTagNo++;
        TextView textViewtemp = new TextView(this);
        textViewtemp.setText(tabName);//字段名称
        textViewtemp.setTextColor(this.getResources().getColor(R.color.gray));
        textViewtemp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        EditText editTexttemp = new EditText(this);
        editTexttemp.setText(tabValue);//字段值
        editTexttemp.setTag(viewTagNo);
        arrayRegisterEdittext.add(editTexttemp);
        editTexttemp.setFocusable(false);
        editTexttemp.setFocusableInTouchMode(false);
        editTexttemp.setTextColor(this.getResources().getColor(R.color.deep_gray));
        editTexttemp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        editTexttemp.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        final int finalI = viewTagNo;
        LinearLayout linearLayouttemp = new LinearLayout(this);
        linearLayouttemp.setOrientation(LinearLayout.HORIZONTAL);
        linearLayouttemp.addView(textViewtemp, paramstemp);
        linearLayouttemp.addView(editTexttemp, paramsedittext);
        ImageView imageViewLine = new ImageView(this);
        imageViewLine.setLayoutParams(new LinearLayout.LayoutParams(GridLayout.LayoutParams.FILL_PARENT,2));
        imageViewLine.setBackgroundColor(this.getResources().getColor(R.color.linecolor));

        caseRegisterLinearlayout.addView(linearLayouttemp, params);
        caseRegisterLinearlayout.addView(imageViewLine, paramsDivider);

    }

    //主要当事人信息，动态加载
    private void addPersonView(DataVolume dataVolume){

        TableRow tableRow = new TableRow.Builder(this)
                .title(dataVolume.getName())
                .content(dataVolume.getValue())
                .build();
        caseRegisterLinearlayout.addView(tableRow);
    }

    //其他当事人列表，动态加载
    private void addLitigtView(CaseInvestigateDetail.Result.LitigtVolume dataVolume){

        if(dataVolume.getLitigtName()!=null)//字段值
            addTextViewWidget("其他当事人", dataVolume.getLitigtName());
        else
            addTextViewWidget("其他当事人", "");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
