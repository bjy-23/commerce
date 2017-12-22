package com.wondersgroup.commerce.teamwork.simpleprocedurecase;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.sampleCaseVoList;
import com.wondersgroup.commerce.model.ProcedureCaseItemListBean;
import com.wondersgroup.commerce.model.ProcedureCaseQueryResult;
import com.wondersgroup.commerce.model.SerializableMap;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/*
* 简易程序案件 列表页面
* activityType="simplePrcedureList"  简易程序案件列表
* activityType="queryMyCase"  案件条件查询结果列表
* by Lee
* */

public class ProcedureCaseListActivity extends AppCompatActivity {

    private final String TAG = "ProcedureCaseListActivity";
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.simple_navigation_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.search_bar)
    RelativeLayout searchBar;
    @BindView(R.id.searchtxt)
    EditText searchEdit;
    @BindView(R.id.case_investigate_ListView)
    ListView caseListView;
    @BindView(R.id.date1)
    LinearLayout startDateLayout;
    @BindView(R.id.date1txt)
    TextView startDateTxt;
    @BindView(R.id.date2)
    LinearLayout endDateLayout;
    @BindView(R.id.date2txt)
    TextView endDateTxt;
    @BindView(R.id.toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_option)
    TextView tvOption;
    private CaseInvestigateAdapter caseInvestigateAdapter;
    private List<sampleCaseVoList> dataList = new ArrayList<>();
    private String activityType = null;
    Map<String, String> conditionMap = new HashMap<String, String>();
    private int page = 1;
    private RootAppcation app;
    private String userId;

    private boolean isDate1null = true;
    private boolean isDate2null = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_case_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();
        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        initView();
        initData();
        searchBar.setVisibility(View.GONE);
    }

    private void initView() {

        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        tvOption.setText(Constants.ADD_CHS);
        tvOption.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        caseInvestigateAdapter = new CaseInvestigateAdapter(ProcedureCaseListActivity.this, dataList);
        caseListView.setAdapter(caseInvestigateAdapter);

        caseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sampleCaseVoList data = dataList.get(position);
                Intent mIntent = new Intent(ProcedureCaseListActivity.this, ProcedureCaseDetailActivity.class);
                mIntent.putExtra("clueNo", data.getClueNo());
                mIntent.putExtra("status", data.getStatus());
                startActivity(mIntent);
            }
        });
        caseListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
                        if (page != -1) {
                            if (activityType.equals(ApiManager.caseApi.INVESTIGATE_CASE_LIST)) {
                                page++;
                                getProcedureCastList(page);
                            } else {
                                page++;
                                getQueryMyCase(page);
                            }
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if (keyEvent.getAction()==KeyEvent.ACTION_UP){
                        searchByName();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        activityType = bundle.getString("activityType");
        if (activityType.equals(ApiManager.caseApi.INVESTIGATE_CASE_LIST)) {
            tvTitle.setText(Constants.JYCX_NAME_SC);
            getProcedureCastList(1);
        } else {
            tvTitle.setText("案件查询结果");
            searchBar.setVisibility(View.GONE);
            final SerializableMap serializableMap = (SerializableMap) bundle.getSerializable("conditionMap");
            conditionMap = serializableMap.getMap();
            getQueryMyCase(1);
        }
    }

    @OnClick({R.id.cleanBtn, R.id.filterbtn, R.id.date1, R.id.date2, R.id.resetbtn, R.id.submitbtn, R.id.tv_option})
    public void onClick(View v) {
        final CusDatePickerDialog dateDialog;
        switch (v.getId()) {
            case R.id.tv_option:
                Intent mIntent = new Intent(ProcedureCaseListActivity.this, ProcedureCaseDetailActivity.class);
                startActivity(mIntent);
                break;
            case R.id.cleanBtn:
                searchEdit.setText("");
                searchByName();
                break;
            case R.id.filterbtn:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.date1:
                new DatePickerDialog(ProcedureCaseListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        System.out.println(String.format("%d-%d-%d", i, i1 + 1, i2));
                        if (i1 + 1 < 10) {
                            if (i2 + 1 < 10)
                                startDateTxt.setText(String.format("%d-0%d-0%d", i, i1 + 1, i2));
                            else
                                startDateTxt.setText(String.format("%d-0%d-%d", i, i1 + 1, i2));
                        } else {
                            if (i2 + 1 < 10)
                                startDateTxt.setText(String.format("%d-%d-0%d", i, i1 + 1, i2));
                            else
                                startDateTxt.setText(String.format("%d-%d-%d", i, i1 + 1, i2));
                        }
                        isDate1null = false;
                    }
                }, 2016, 1, 1).show();
                break;
            case R.id.date2:
                new DatePickerDialog(ProcedureCaseListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        System.out.println(String.format("%d-%d-%d", i, i1 + 1, i2));
                        if (i1 + 1 < 10) {
                            if (i2 + 1 < 10)
                                endDateTxt.setText(String.format("%d-0%d-0%d", i, i1 + 1, i2));
                            else
                                endDateTxt.setText(String.format("%d-0%d-%d", i, i1 + 1, i2));
                        } else {
                            if (i2 + 1 < 10)
                                endDateTxt.setText(String.format("%d-%d-0%d", i, i1 + 1, i2));
                            else
                                endDateTxt.setText(String.format("%d-%d-%d", i, i1 + 1, i2));
                        }
                        isDate2null = false;
                    }
                }, 2016, 1, 1).show();
                break;
            case R.id.resetbtn:
                resetBtns();
                break;
            case R.id.submitbtn:
                page = 1;
                dataList.clear();
                getProcedureCastList(page);
                drawerLayout.closeDrawers();
                break;
        }
    }


    //通过名称或编号查询
    private void searchByName() {
//        Toast.makeText(this, searchEdit.getText().toString(), Toast.LENGTH_SHORT).show();
        page = 1;
        dataList.clear();
        getProcedureCastList(1);
    }

    //重置各按钮状态
    private void resetBtns() {
        //时间按钮
        if (!isDate1null) {
            startDateLayout.setBackgroundResource(R.drawable.rounded_rect_grayfill);
            startDateTxt.setTextColor(ContextCompat.getColor(this, R.color.light_gray));
            startDateTxt.setText("起始日期");
            isDate1null = true;
        }
        if (!isDate2null) {
            endDateLayout.setBackgroundResource(R.drawable.rounded_rect_grayfill);
            endDateTxt.setTextColor(ContextCompat.getColor(this, R.color.light_gray));
            endDateTxt.setText("截止日期");
            isDate2null = true;
        }
    }


    //获取待调查案件列表
    private void getProcedureCastList(final int page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("currentPage", String.valueOf(page));
        map.put("wsCodeReq", "03010101");
        if (!isEditTextEmpty(searchEdit)) {
            map.put("name", searchEdit.getText().toString().trim());
        }
        if (isDate1null == false) {
            map.put("startCasefiDate", startDateTxt.getText().toString().trim());
        }
        if (isDate2null == false) {
            map.put("endCasefiDate", endDateTxt.getText().toString().trim());
        }

        Call<ProcedureCaseItemListBean> call;
        call = ApiManager.caseApi.getProcedureCaseList(map);
        call.enqueue(new Callback<ProcedureCaseItemListBean>() {
            @Override
            public void onResponse(Response<ProcedureCaseItemListBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    ProcedureCaseItemListBean caseList = response.body();

                    if ((null == caseList) || (null == caseList.getResult())) {
                        Toast.makeText(ProcedureCaseListActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ("已经没有下一页了".equals(caseList.getMessage())) {
                        if(page == 1){
                            dataList.clear();
                            caseInvestigateAdapter.notifyDataSetChanged();
                            Toast.makeText(ProcedureCaseListActivity.this, "搜索结果为空！", Toast.LENGTH_SHORT).show();
                        }
                        ProcedureCaseListActivity.this.page = -1;
                        return;
                    }

                    if (null != caseList.getResult().getList()) {
                        dataList.addAll(caseList.getResult().getList());
                        caseInvestigateAdapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(ProcedureCaseListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ProcedureCaseListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //案件条件查询
    private void getQueryMyCase(int page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        //map.put("currentPage", String.valueOf(page));
        map.put("wsCodeReq", "03010003");
        conditionMap.put("\"" + "currentPage" + "\"", "\"" + String.valueOf(page) + "\"");
        map.put("condition", conditionMap.toString());
        Call<ProcedureCaseQueryResult> call = ApiManager.caseApi.queryProcedureCase(map);

        call.enqueue(new Callback<ProcedureCaseQueryResult
                >() {
            @Override
            public void onResponse(Response<ProcedureCaseQueryResult> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    ProcedureCaseQueryResult caseQueryResult = response.body();

                    if ((null == caseQueryResult) || (null == caseQueryResult.getResult())) {
                        Toast.makeText(ProcedureCaseListActivity.this, "案件查询数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if ("已经没有下一页了".equals(caseQueryResult.getMessage())) {
                        ProcedureCaseListActivity.this.page = -1;
                        return;
                    }

                    if (null != caseQueryResult.getResult()) {
                        dataList.addAll(caseQueryResult.getResult());
                        for (sampleCaseVoList data : dataList) {

                        }
                        caseInvestigateAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(ProcedureCaseListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ProcedureCaseListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class CaseInvestigateAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<sampleCaseVoList> caseDataList;

        public CaseInvestigateAdapter(Context context, List<sampleCaseVoList> dataList) {
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_procedure_case_list, parent, false);
                holder.tv_CaseName = (TextView) convertView.findViewById(R.id.case_name_textview);
                holder.tv_CasePerson = (TextView) convertView.findViewById(R.id.case_person_textview);
                holder.tv_PenaltyDate = (TextView) convertView.findViewById(R.id.penalty_date_textview);
                holder.tv_UnderTakePerson = (TextView) convertView.findViewById(R.id.undertake_person_textview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_CaseName.setText(caseDataList.get(position).getCaseName());
            holder.tv_CasePerson.setText(caseDataList.get(position).getLitigtName());
            String date = caseDataList.get(position).getCasefiDate();
            if(date!=null)
                holder.tv_PenaltyDate.setText(date.substring(0,date.lastIndexOf(" ")));
            holder.tv_UnderTakePerson.setText(caseDataList.get(position).getUserIdMainName());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_CaseName;
            private TextView tv_CasePerson;
            private TextView tv_PenaltyDate;
            private TextView tv_UnderTakePerson;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
}

