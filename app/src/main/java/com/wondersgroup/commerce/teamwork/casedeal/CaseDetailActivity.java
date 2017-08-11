package com.wondersgroup.commerce.teamwork.casedeal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.NoteRecordBean;
import com.wondersgroup.commerce.model.NoteRecordEnquire;
import com.wondersgroup.commerce.model.NoteRecordSpot;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
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

/*
* 案件详情：立案信息、现场检查、询问笔录
* by Lee
* */

public class CaseDetailActivity extends AppCompatActivity implements CaseDetialXwblAdapter.OnItemTouchListener {

    private String TAG = "CaseDetailActivity";
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    private RadioGroup radioGroupDealType;              //标签组（立案信息、现场笔录、询问笔录）
    private RadioButton caseRegisterRadioButton;        //立案信息选择tab
    private CaseInvestigateTitle caseInvestigateTitle;  //待调查案件目录对象
    private CaseInvestigateDetail caseDetail;           //待调查案件详情对象
    private LinearLayout caseInfoLinearLayout;          //立案信息显示部分（动态添加控件）
    private NoteRecordBean noteRecordBean;              //笔录对象（包括：现场笔录列表、询问笔录列表）
    private RecyclerView recordSpotListView;                //现场笔录列表
    private RecyclerView recordEnquireListView;             //询问笔录列表
    private ScrollView caseInfoScrollView;              //立案信息显示部分（带滚动条）
    private Button addRecordButton;                     //笔录新增按钮
    private int viewTagNo = 0;                          //动态控件tag
    private boolean bEditSpotRecord;              //当前列表为：现场笔录或询问笔录（true为现场笔录、false为询问笔录）
    private ArrayList<EditText> arrayedittext = new ArrayList<EditText>();//动态控件列表
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsDivider = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
    LinearLayout.LayoutParams paramstemp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsedittext = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);

    private List<NoteRecordSpot> spotRecordList;        //现场笔录对象 列表
    private List<NoteRecordEnquire> enquireRecordList;  //询问笔录对象 列表
    //    private InspectSpotAdapter spotInspectAdapter;       //
//    private NoteRecordsAdapter enquireRecordAdapter;    //询问笔录列表适配器
    private CaseDetialXwblAdapter caseDetialAdapter;        //询问笔录列表适配器
    private CaseDetialXccxAdapter caseDetialXccxAdapter;    //现场笔录列表适配器
    private int deleteInspectIndex = -1;                //现场检查删除记录序号
    private int deleteEnquireIndex = -1;                //询问笔录删除记录序号
    private int requestCode;
    private RootAppcation app;
    private String clueNo;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        paramstemp.weight = 2;
        paramsedittext.weight = 5;
        radioGroupDealType = (RadioGroup) this.findViewById(R.id.deal_type_radioGroup);
        caseRegisterRadioButton = (RadioButton) this.findViewById(R.id.rb_deal_type_create_case);
        caseInfoLinearLayout = (LinearLayout) this.findViewById(R.id.case_info_linearlayout);
        recordSpotListView = (RecyclerView) this.findViewById(R.id.note_record_spot_ListView);
        recordEnquireListView = (RecyclerView) this.findViewById(R.id.note_record_enquire_ListView);
        caseInfoScrollView = (ScrollView) this.findViewById(R.id.case_info_ScrollView);
        addRecordButton = (Button) this.findViewById(R.id.add_record_Button);
        caseRegisterRadioButton.setChecked(true);//默认选择“立案信息”
        caseInvestigateTitle = (CaseInvestigateTitle) getIntent().getExtras().getSerializable("CaseInvestigateTitle");
        clueNo = caseInvestigateTitle.getClueNo();
        initView();
        //立案信息
        initData();

        getNoteRecordsList();
    }

    private void initData() {

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
                        Toast.makeText(CaseDetailActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

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

                    //当事人基本情况
                    if (caseDetail.getResult().getSimLitigtList() != null
                            && caseDetail.getResult().getSimLitigtList().size() != 0){
                        TableRow title = new TableRow.Builder(CaseDetailActivity.this)
                                .asTitle("当事人基本情况")
                                .build();
                        caseInfoLinearLayout.addView(title);

                        for (final CaseInvestigateDetail.Result.LitigtVolume litigtVolume : caseDetail.getResult().getSimLitigtList()){
                            View view = View.inflate(CaseDetailActivity.this,R.layout.item_litigt,null);
                            TextView tvType = (TextView) view.findViewById(R.id.tv_type);
                            tvType.setText(litigtVolume.getAssort());
                            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
                            tvName.setText(litigtVolume.getLitigtName());
                            TextView tvIsPrime = (TextView) view.findViewById(R.id.tv_is_prime);
                            tvIsPrime.setText(litigtVolume.getIsPrime());
                            TextView tvLook = (TextView) view.findViewById(R.id.tv_look);
                            tvLook.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (litigtVolume.getLitigantId() != null && !TextUtils.isEmpty(litigtVolume.getLitigantId()))
                                        queryDetail(litigtVolume.getLitigantId());
                                }
                            });
                            caseInfoLinearLayout.addView(view);
                        }

                    }

                    //承办人情况

                } else {
                    Toast.makeText(CaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void queryDetail(String litigtId){
        String url = CaseApi.URL_CASE_2 + CaseApi.GET_LITIGT_DETAIL;
        HashMap map = new HashMap();
        map.put("wsCodeReq", "03010017");
        map.put("clueNo", clueNo);
        map.put("litigtId",litigtId);
        Call call = ApiManager.caseApi.getLitigtDetail(url,map);
    }

    //获取现场检查、询问笔录列表
    private void getNoteRecordsList() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010006");
        map.put("clueNo", clueNo);
        String url = "";
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.NOTE_RECORD_LIST;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.NOTE_RECORD_LIST;
        Call<NoteRecordBean> call = ApiManager.caseApi.queryInvestigateList(url,map);
        call.enqueue(new Callback<NoteRecordBean>() {
            @Override
            public void onResponse(Response<NoteRecordBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    noteRecordBean = response.body();

                    if ((null == noteRecordBean) || (null == noteRecordBean.getResult())) {
                        Toast.makeText(CaseDetailActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    deleteInspectIndex = -1;
                    deleteEnquireIndex = -1;

                    if (null != noteRecordBean.getResult().getEnquireList()) {
                        enquireRecordList = noteRecordBean.getResult().getEnquireList();
                    } else {
                        enquireRecordList = new ArrayList<NoteRecordEnquire>();
                    }


                    if (null != noteRecordBean.getResult().getInspectVoList()) {
                        spotRecordList = noteRecordBean.getResult().getInspectVoList();
                    } else {
                        spotRecordList = new ArrayList<NoteRecordSpot>();
                    }

                    //加载现场检查记录列表
                    caseDetialXccxAdapter = new CaseDetialXccxAdapter(CaseDetailActivity.this, spotRecordList);
                    recordSpotListView.setHasFixedSize(true);
                    recordSpotListView.setLayoutManager(new LinearLayoutManager(CaseDetailActivity.this));
                    recordSpotListView.setItemAnimator(new DefaultItemAnimator());
                    recordSpotListView.setAdapter(caseDetialXccxAdapter);
                    caseDetialXccxAdapter.setOnItemTouchListener(new CaseDetialXccxAdapter.OnItemTouchListener() {
                        @Override
                        public void onItemTouchListener(int position, View view) {
                            if (view.getTag() == "1") {
                                NoteRecordSpot data = spotRecordList.get(position);
                                Log.d(TAG, "spotRecordList.get(" + position + ")-------------data.getSerialNo() = " + data.getSerialNo());
                                Bundle bundle = new Bundle();
                                bundle.putString("NoteRecordSpot", data.getSerialNo());
                                Intent mIntent = new Intent(CaseDetailActivity.this, CaseInspectsActivity.class);
                                mIntent.putExtras(bundle);
                                requestCode = 0;
                                startActivityForResult(mIntent, requestCode);
                            } else {
                                deleteInspectIndex = position;
                                deleteRecord();
                            }
                        }
                    });

                    //加载询问记录列表
//                    enquireRecordAdapter = new NoteRecordsAdapter(CaseDetailActivity.this, enquireRecordList);
//                    recordEnquireListView.setAdapter(enquireRecordAdapter);
                    recordEnquireListView.setHasFixedSize(true);
                    recordEnquireListView.setLayoutManager(new LinearLayoutManager(CaseDetailActivity.this));
                    recordEnquireListView.setItemAnimator(new DefaultItemAnimator());
                    caseDetialAdapter = new CaseDetialXwblAdapter(CaseDetailActivity.this, enquireRecordList);
                    caseDetialAdapter.setOnItemTouchListener(CaseDetailActivity.this);
                    recordEnquireListView.setAdapter(caseDetialAdapter);
                } else {
                    Toast.makeText(CaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        if (null == caseInvestigateTitle)
            return;

        Log.d(TAG, "caseInvestigateTitle.getClueNo() = " + caseInvestigateTitle.getClueNo());
        title.setText(caseInvestigateTitle.getCaseName());

        //新增：现场检查/询问笔录
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("CaseInvestigateTitle", caseInvestigateTitle);
                Intent mIntent;

                if (bEditSpotRecord == true) {//现场检查(新增--现场笔录)
                    mIntent = new Intent(CaseDetailActivity.this, CaseInspectsActivity.class);
                    requestCode = 0;
                } else {//询问笔录（新增--询问笔录）
                    mIntent = new Intent(CaseDetailActivity.this, CaseRecordEnquiresActivity.class);
                    requestCode = 1;
                }

                mIntent.putExtras(bundle);
                startActivityForResult(mIntent, requestCode);
            }
        });

        radioGroupDealType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) CaseDetailActivity.this.findViewById(checkedId);

                if (radioButton.getText().equals("立案信息")) {
                    Log.d(TAG, "focus on radioButton.getText() = " + radioButton.getText());
                    recordSpotListView.setVisibility(View.GONE);
                    recordEnquireListView.setVisibility(View.GONE);
                    caseInfoScrollView.setVisibility(View.VISIBLE);
                } else if (radioButton.getText().equals("现场检查")) {
                    Log.d(TAG, "focus on radioButton.getText() = " + radioButton.getText());
                    caseInfoScrollView.setVisibility(View.GONE);
                    recordEnquireListView.setVisibility(View.GONE);
                    recordSpotListView.setVisibility(View.VISIBLE);
                    bEditSpotRecord = true;
                } else {//询问笔录
                    Log.d(TAG, "focus on radioButton.getText() = " + radioButton.getText());
                    caseInfoScrollView.setVisibility(View.GONE);
                    recordSpotListView.setVisibility(View.GONE);
                    recordEnquireListView.setVisibility(View.VISIBLE);
                    bEditSpotRecord = false;
                }

            }
        });
    }


    //添加基本信息
    private void addBaseView() {

        TableRow caseName = new TableRow.Builder(this)
                .title("案件名称")
                .content(caseDetail.getResult().getCaseName())
                .build();
        caseInfoLinearLayout.addView(caseName);
        TableRow caseDistrict = new TableRow.Builder(this)
                .title("所在区划")
                .content(caseDetail.getResult().getCaseCedistrict())
                .build();
        caseInfoLinearLayout.addView(caseDistrict);
        if (caseDetail.getResult().getCaseSpot() != null && !"".equals(caseDetail.getResult().getCaseSpot())) {
            TableRow caseSpot = new TableRow.Builder(this)
                    .title("案发地点")
                    .content(caseDetail.getResult().getCaseSpot())
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                        }
                    })
                    .build();
            caseInfoLinearLayout.addView(caseSpot);
        } else {
            TableRow caseSpot = new TableRow.Builder(this)
                    .title("案发地点")
                    .content(caseDetail.getResult().getCaseSpot())
                    .build();
            caseInfoLinearLayout.addView(caseSpot);
        }
        TableRow caseTime = new TableRow.Builder(this)
                .title("案发时间")
                .content(caseDetail.getResult().getCaseTime())
                .build();
        caseInfoLinearLayout.addView(caseTime);
        TableRow clueType = new TableRow.Builder(this)
                .title("案件来源")
                .content(caseDetail.getResult().getClueType())
                .build();
        caseInfoLinearLayout.addView(clueType);
        TableRow caseReason = new TableRow.Builder(this)
                .title("核查情况及立案(不予立案)理由")
                .content(caseDetail.getResult().getCaseReason())
                .build();
        caseInfoLinearLayout.addView(caseReason);
        TableRow deptOpi = new TableRow.Builder(this)
                .title("办案机构负责人意见")
                .content(caseDetail.getResult().getDepOpi())
                .build();
        caseInfoLinearLayout.addView(deptOpi);
        TableRow organOpi = new TableRow.Builder(this)
                .title("机关负责人意见")
                .content(caseDetail.getResult().getOrganOpi())
                .build();
        caseInfoLinearLayout.addView(organOpi);

    }

    //删除现场检查/询问笔录 记录
    private void deleteRecord() {
        Map<String, String> map = new HashMap<String, String>();
        Call<BackResultObject> call;
        if (bEditSpotRecord == true) {//现场检查(删除--现场笔录)
            map.put("wsCodeReq", "03010009");
            map.put("serialNo", spotRecordList.get(this.deleteInspectIndex).getSerialNo());
            String url = "";
            if (type == 1)
                url = CaseApi.URL_CASE_1 + CaseApi.CASE_INSPECT_DELETE;
            else
                url = CaseApi.URL_CASE_2 + CaseApi.CASE_INSPECT_DELETE;
            call = ApiManager.caseApi.deleteInspect(url,map);
        } else {//询问笔录（删除--询问笔录）
            map.put("wsCodeReq", "03010009");
            map.put("serialNo", enquireRecordList.get(this.deleteEnquireIndex).getSerialNo());
            String url = "";
            if (type == 1)
                url = CaseApi.URL_CASE_1 + CaseApi.CASE_ENQUIRE_DELETE;
            else
                url = CaseApi.URL_CASE_2 + CaseApi.CASE_ENQUIRE_DELETE;
            call = ApiManager.caseApi.deleteEnquire(url,map);
        }


        call.enqueue(new Callback<BackResultObject>() {
            @Override
            public void onResponse(Response<BackResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    BackResultObject result = response.body();
                    if (result.getCode().equals(ApiManager.RESULT_SUCCESS)) {
                        Toast.makeText(CaseDetailActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                        getNoteRecordsList();
                    } else {
                        Toast.makeText(CaseDetailActivity.this, "提交失败！", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "CaseDetailActivity--------result.getMessage() = " + result.getMessage());
                    }

                } else {
                    Log.d(TAG, "CaseDetailActivity --------------- response.is not Success()");
                    Toast.makeText(CaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    ;

    private void addTextViewWidget(String tabName, final String tabValue) {
        viewTagNo++;
        TextView textViewtemp = new TextView(this);
        textViewtemp.setText(tabName);//字段名称
        textViewtemp.setTextColor(this.getResources().getColor(R.color.gray));
        textViewtemp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        EditText editTexttemp = new EditText(this);
        editTexttemp.setText(tabValue);//字段值
        editTexttemp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        editTexttemp.setTag(viewTagNo);
        arrayedittext.add(editTexttemp);
        editTexttemp.setFocusable(false);
        editTexttemp.setFocusableInTouchMode(false);
        editTexttemp.setTextColor(this.getResources().getColor(R.color.deep_gray));
        editTexttemp.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        final int finalI = viewTagNo;
        LinearLayout linearLayouttemp = new LinearLayout(this);
        linearLayouttemp.setOrientation(LinearLayout.HORIZONTAL);
        linearLayouttemp.setGravity(Gravity.CENTER_VERTICAL);
        linearLayouttemp.addView(textViewtemp, paramstemp);
        linearLayouttemp.addView(editTexttemp, paramsedittext);
        linearLayouttemp.setBackgroundColor(this.getResources().getColor(R.color.white));
        ImageView imageViewLine = new ImageView(this);
        imageViewLine.setLayoutParams(new LinearLayout.LayoutParams(GridLayout.LayoutParams.FILL_PARENT, 2));
        imageViewLine.setBackgroundColor(this.getResources().getColor(R.color.linecolor));

        if ("案发地点".equals(tabName) && !tabValue.equals("")) {
            ImageView mapImg = new ImageView(this);
            mapImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.map));
            linearLayouttemp.addView(mapImg, paramstemp);
            mapImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }


        caseInfoLinearLayout.addView(linearLayouttemp, params);
        caseInfoLinearLayout.addView(imageViewLine, paramsDivider);

    }

    //主要当事人信息，动态加载
    private void addPersonView(DataVolume dataVolume) {

        TableRow tableRow = new TableRow.Builder(this)
                .title(dataVolume.getName())
                .content(dataVolume.getValue())
                .build();
        caseInfoLinearLayout.addView(tableRow);
    }

    //其他当事人列表，动态加载
    private void addLitigtView(CaseInvestigateDetail.Result.LitigtVolume dataVolume) {

        if (dataVolume.getLitigtName() != null)//字段值
            addTextViewWidget("其他当事人", dataVolume.getLitigtName());
        else
            addTextViewWidget("其他当事人", "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult!");
        switch (requestCode) {
            case 0:
                Log.d(TAG, "Back from CaseInspectActivity!");
                getNoteRecordsList();
                break;
            case 1:
                Log.d(TAG, "Back from CaseEnquireActivity!");
                getNoteRecordsList();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemTouchListener(int position, View view) {
        if (view.getTag() == "1") {
            NoteRecordEnquire data = enquireRecordList.get(position);
            Log.d(TAG, "enquireRecordList.get(" + position + ")-------------data.getSerialNo() = " + data.getSerialNo());
            Bundle bundle = new Bundle();
            bundle.putString("NoteRecordEnquire", data.getSerialNo());
            Intent mIntent = new Intent(CaseDetailActivity.this, CaseRecordEnquiresActivity.class);
            mIntent.putExtras(bundle);
            requestCode = 1;
            startActivityForResult(mIntent, requestCode);
        } else {
            deleteEnquireIndex = position;
            deleteRecord();
        }
    }


}
