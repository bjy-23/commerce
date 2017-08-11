package com.wondersgroup.commerce.teamwork.simpleprocedurecase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.ProcedureCaseAICItemsVo;
import com.wondersgroup.commerce.model.ProcedureCaseAICRequiryComResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseAICRequiryResultObject;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/*
* 简易程序案件 个体工商户登记信息 查询页面
* by Lee
* */

public class AICRegisterInquireListActivity extends AppCompatActivity {

    private final String  TAG = "AICRegisterInquireListActivity";
    public static final int RESULT = 100;
    public static final int REQUEST = 1234;
    public static final String TYPE_PERSONAL_NAME = "personal_name";//个体工商户名称
    public static final String TYPE_PERSONAL_REGISTER = "personal_register";//个体工商户注册号
    public static final String TYPE_COMPANY_NAME = "company_name";//企业工商户名称
    public static final String TYPE_COMPANY_REGISTER = "company_register";//企业工商户注册号
    private Toolbar toolbar;
    private TextView title;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayoutQuery;
    private ListView caseListView;
    private List<ProcedureCaseAICItemsVo> dataList = new ArrayList();
    private LegalBasisAdapter legalBasisAdapter;
    private DataShared dataShared;
    private String activityType = null;
    private String paramUnitName = null;
    private String paramRegNo = null;
    private String paramPeName = null;
    private Map<String, String> orgnizationMap = null;      //机关
    private Map<String, String> statusMap = new HashMap<String, String>();           //存续状态
    private RootAppcation app;
    private TableRow organNameRow, statusRow;
    private Button confirm_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_case_aic_quire_list);
        toolbar = (Toolbar)this.findViewById(R.id.mid_toolbar) ;
        title = (TextView) this.findViewById(R.id.toolbar_title);
        drawerLayout = (DrawerLayout) this.findViewById(R.id.simple_navigation_drawer);
        caseListView = (ListView) this.findViewById(R.id.case_investigate_ListView);
        linearLayoutQuery = (LinearLayout)this.findViewById(R.id.query_confident_linearlayout);
        confirm_Button = (Button)this.findViewById(R.id.confirm_Button);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        initView();
        initData();
    }

    private void initView(){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        title.setText("查询个体工商户登记信息");
        activityType = bundle.getString("activityType");
        if(activityType.equals(TYPE_PERSONAL_NAME))
            paramPeName = bundle.getString("param");
        else if(activityType.equals(TYPE_PERSONAL_REGISTER))
            paramRegNo = bundle.getString("param");
        else if(activityType.equals(TYPE_COMPANY_NAME))
            paramUnitName = bundle.getString("param");
        else if(activityType.equals(TYPE_COMPANY_REGISTER))
            paramRegNo = bundle.getString("param");
        SerializableMap serializableOrganIdMapMap = (SerializableMap)bundle.getSerializable("iOrganIdMap");
        if(serializableOrganIdMapMap!=null)
            orgnizationMap = serializableOrganIdMapMap.getMap();

        caseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProcedureCaseAICItemsVo data = dataList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("entityId", data.getEntityId());
                bundle.putString("activityType", activityType);
                Intent intent=new Intent(AICRegisterInquireListActivity.this,ProcedureCaseDetailActivity.class);
                intent.putExtras(bundle);
                setResult(RESULT, intent);
                finish();
                }
        });

        organNameRow = new TableRow.Builder(this)
                .title("机关")
                .input("请选择机关")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if(orgnizationMap == null){
                            Toast.makeText(AICRegisterInquireListActivity.this, "机关类型字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            final String organStringArray[] = new String[orgnizationMap.size()];
                            int i=0;
                            for(Map.Entry<String, String> entry : orgnizationMap.entrySet())
                                organStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(AICRegisterInquireListActivity.this)
                                    .items(organStringArray)
                                    .widgetColor(ContextCompat.getColor(AICRegisterInquireListActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            int organFlag = which;
                                            organNameRow.setTvContent(organStringArray[organFlag]);
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(AICRegisterInquireListActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        linearLayoutQuery.addView(organNameRow);
        statusMap.put("0","非存续");
        statusMap.put("1","存续");
        statusRow = new TableRow.Builder(this)
                .title("存续状态")
                .input("请选择存续状态")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        final String statusStringArray[] = new String[statusMap.size()];
                        int i=0;
                        for(Map.Entry<String, String> entry : statusMap.entrySet())
                            statusStringArray[i++] = entry.getValue();
                        new MaterialDialog.Builder(AICRegisterInquireListActivity.this)
                                .items(statusStringArray)
                                .widgetColor(ContextCompat.getColor(AICRegisterInquireListActivity.this, R.color.blue))
                                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                        int statusFlag = which;
                                        statusRow.setTvContent(statusStringArray[statusFlag]);
                                        return true;
                                    }
                                })
                                .positiveText("确定")
                                .positiveColor(ContextCompat.getColor(AICRegisterInquireListActivity.this, R.color.blue))
                                .show();
                    }
                })
                .build();
        linearLayoutQuery.addView(statusRow);

        confirm_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

    }


    //5.3.3. 个体工商户查询
    private void queryPersonList( ){

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010103");
        if(paramPeName!=null)
            map.put("peName", paramPeName);

        if(paramRegNo!=null)
            map.put("regNo", paramRegNo);

        if(!isSelectTextEmpty(organNameRow)){
            String organId = getKeyByValue(orgnizationMap, organNameRow.getTvContent());
            map.put("iOrganId", organId);
        }

        if(!isSelectTextEmpty(statusRow)){
            String statusId = getKeyByValue(statusMap, statusRow.getTvContent());
            map.put("status", statusId);
        }else
            map.put("status", "1");

        Call<ProcedureCaseAICRequiryResultObject> call;
        call = ApiManager.caseModule.queryProcedureCaseAICInquiryPerson(map);

        call.enqueue(new Callback<ProcedureCaseAICRequiryResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseAICRequiryResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    ProcedureCaseAICRequiryResultObject resultObject = response.body();

                    if ((null == resultObject) || (null == resultObject.getGetPersList())) {
                        Toast.makeText(AICRegisterInquireListActivity.this, "查询个体工商户信息失败", Toast.LENGTH_SHORT).show();
                        dataList = new ArrayList<ProcedureCaseAICItemsVo>();
                    }else
                        dataList = resultObject.getGetPersList();
                    legalBasisAdapter = new LegalBasisAdapter(AICRegisterInquireListActivity.this, dataList);
                    caseListView.setAdapter(legalBasisAdapter);

                } else {
                    Log.d(TAG, "AICRegisterInquireListActivity --------------- 5");
                    Toast.makeText(AICRegisterInquireListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                Toast.makeText(AICRegisterInquireListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //5.3.3. 企业工商户查询
    private void queryCompanyList( ){

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010005");
        if(paramUnitName!=null)
            map.put("unitName", paramUnitName);

        if(paramRegNo!=null)
            map.put("regNo", paramRegNo);

        if(!isSelectTextEmpty(organNameRow)){
            String organId = getKeyByValue(orgnizationMap, organNameRow.getTvContent());
            map.put("iOrganId", organId);
        }

        if(!isSelectTextEmpty(statusRow)){
            String statusId = getKeyByValue(statusMap, statusRow.getTvContent());
            map.put("status", statusId);
        }else
            map.put("status", "1");

        Call<ProcedureCaseAICRequiryComResultObject> call;
        call = ApiManager.caseModule.queryProcedureCaseAICInquiryCompany(map);

        call.enqueue(new Callback<ProcedureCaseAICRequiryComResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseAICRequiryComResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    ProcedureCaseAICRequiryComResultObject resultObject = response.body();

                    if ((null == resultObject) || (null == resultObject.getGetEtpsList())) {
                        Toast.makeText(AICRegisterInquireListActivity.this, "查询企业工商户信息失败", Toast.LENGTH_SHORT).show();
                        dataList = new ArrayList<ProcedureCaseAICItemsVo>();
                    }else
                        dataList = resultObject.getGetEtpsList();
                    legalBasisAdapter = new LegalBasisAdapter(AICRegisterInquireListActivity.this, dataList);
                    caseListView.setAdapter(legalBasisAdapter);

                } else {
                    Log.d(TAG, "AICRegisterInquireListActivity --------------- 5");
                    Toast.makeText(AICRegisterInquireListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                Toast.makeText(AICRegisterInquireListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData(){

        if(activityType.equals(TYPE_PERSONAL_NAME) || activityType.equals(TYPE_PERSONAL_REGISTER))
            queryPersonList();
        else
            queryCompanyList();
    }

    public class LegalBasisAdapter extends BaseAdapter{

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<ProcedureCaseAICItemsVo> caseDataList;

        public LegalBasisAdapter(Context context, List<ProcedureCaseAICItemsVo> dataList){
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            caseDataList = dataList;
        }

        @Override
        public int getCount() {
            return caseDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return caseDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_procedure_case_aic_inquiry_list, parent, false);
                holder.tvRegisterNo = (TextView) convertView.findViewById(R.id.tvRegisterNo);
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.tvPerson = (TextView) convertView.findViewById(R.id.tvPerson);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvRegisterNo.setText(caseDataList.get(position).getRegNo());
            holder.tvName.setText(caseDataList.get(position).getLitigtName());
            holder.tvPerson.setText(caseDataList.get(position).getLegalName());

            return convertView;
        }

        private class ViewHolder {
            private TextView tvRegisterNo;  //个体工商户注册号
            private TextView tvName;        //个体工商户名称
            private TextView tvPerson;     //法定代表人

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    ////检查selectText是否输入
    public boolean isSelectTextEmpty(TableRow tv) {
        boolean flag = false;
        if (tv.getTvContent() == null
                || tv.getTvContent().trim().equals("")) {
            flag = true;
        }
        return flag;
    }

    private String getKeyByValue(Map<String, String> map, String value){
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(value))
                return entry.getKey();
        }
        return "";
    }
}

