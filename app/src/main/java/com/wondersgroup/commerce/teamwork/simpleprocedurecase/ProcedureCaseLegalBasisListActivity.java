package com.wondersgroup.commerce.teamwork.simpleprocedurecase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.ProcedureCaseLegalBasis;
import com.wondersgroup.commerce.utils.DataShared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
* 简易程序案件 定性依据查询页面
* activityType="legalBasis"  定性依据列表
* activityType="penaltyBasis"  处罚依据列表
* by Lee
* */

public class ProcedureCaseLegalBasisListActivity extends AppCompatActivity {

    private final String  TAG = "ProcedureCaseLegalBasisListActivity";
    public static final int RESULT = 10;
    public static final int LEGAL_BASIS = 11;
    public static final int PENALTY_BASIS = 12;
    private Toolbar toolbar;
    private TextView title;
    private DrawerLayout drawerLayout;
    private ListView caseListView;
    private Button confirmButton;
    private List<ProcedureCaseLegalBasis> dataList = new ArrayList();
    private LegalBasisAdapter legalBasisAdapter;
    private DataShared dataShared;
    private String activityType = null;
    private RootAppcation app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_case_legal_basis_list);
        toolbar = (Toolbar)this.findViewById(R.id.mid_toolbar) ;
        title = (TextView) this.findViewById(R.id.toolbar_title);
        drawerLayout = (DrawerLayout) this.findViewById(R.id.simple_navigation_drawer);
        caseListView = (ListView) this.findViewById(R.id.case_investigate_ListView);
        confirmButton = (Button) this.findViewById(R.id.confirm_Button);
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

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activityType.equals("legalBasis")){//定性依据
                    boolean noMajorBasis = true;
                    for(ProcedureCaseLegalBasis basis:dataList)
                        if(basis.isMajorBasis())
                            noMajorBasis = false;
                    if(noMajorBasis == true){
                        Toast.makeText(ProcedureCaseLegalBasisListActivity.this, "缺少主要定性依据！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                List<ProcedureCaseLegalBasis> choiceDataList = new ArrayList<ProcedureCaseLegalBasis>();
                for(ProcedureCaseLegalBasis basis:dataList)
                    if(basis.isChecked())
                        choiceDataList.add(basis);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProcedureCaseLegalBasis", (Serializable)choiceDataList);
                Intent intent=new Intent(ProcedureCaseLegalBasisListActivity.this,ProcedureCaseDetailActivity.class);
                intent.putExtras(bundle);
                setResult(RESULT, intent);
                finish();
            }
        });
    }

    private void initData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        activityType = bundle.getString("activityType");
        if(activityType.equals("legalBasis")){
            title.setText("查询定性依据信息");
//            getProcedureCastList(1);
        }else{
            title.setText("查询处罚依据信息");
        }
        dataList = (ArrayList<ProcedureCaseLegalBasis>)bundle.getSerializable("ProcedureCaseLegalBasis");
        legalBasisAdapter = new LegalBasisAdapter(ProcedureCaseLegalBasisListActivity.this, dataList);
        caseListView.setAdapter(legalBasisAdapter);

    }

//    //本地调试数据
//    private void initDataWithoutNet() {
//        ProcedureCaseLegalBasis data = new ProcedureCaseLegalBasis();
//        data.setLawCode("ID_7867");
//        data.setLawContent("对张三超市违法行为的处罚");
//        if(activityType.equals("legalBasis"))
//            data.setBasisType("0");
//        else
//            data.setBasisType("1");
//        data.setIlglActId("010101");
//        dataList.add(data);
//        ProcedureCaseLegalBasis data2 = new ProcedureCaseLegalBasis();
//        data2.setLawCode("ID_9887");
//        data2.setLawContent("对李四超市违法行为的处罚");
//        if(activityType.equals("legalBasis"))
//            data2.setBasisType("0");
//        else
//            data2.setBasisType("1");
//        data2.setIlglActId("010101");
//        dataList.add(data2);
//        ProcedureCaseLegalBasis data3 = new ProcedureCaseLegalBasis();
//        data3.setLawCode("ID_987");
//        data3.setLawContent("对健哦超市违法行为的处罚");
//        if(activityType.equals("legalBasis"))
//            data3.setBasisType("0");
//        else
//            data3.setBasisType("1");
//        data3.setIlglActId("010101");
//        dataList.add(data3);
//        legalBasisAdapter.notifyDataSetChanged();
//    }

    //获取待调查案件列表
    private void getProcedureCastList(final int page){
//
//        dataShared = new DataShared(this);
//        String userId = (String) dataShared.get("userId", "");
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("userId", userId);
//        map.put("currentPage", String.valueOf(page));
//        map.put("wsCodeReq", "03010101");
//        Log.d(TAG, "initData()---------" + userId + "------------map.toString()=" + map.toString());
//        ApiManager.getInstance().unitTestInit();
//        Call<ProcedureCaseItemListBean> call;
//        call = ApiManager.caseModule.getProcedureCaseList(map);
//
//        Log.d(TAG, "map.toString() = " + map.toString());
//
//        call.enqueue(new Callback<ProcedureCaseItemListBean>() {
//            @Override
//            public void onResponse(Response<ProcedureCaseItemListBean> response, Retrofit retrofit) {
//                if (response.isSuccess()) {
//                    MyProgressDialog.dismiss();
//                    ProcedureCaseItemListBean caseList = response.body();
//
//                    if ((null == caseList) || (null == caseList.getResult())) {
//                        Toast.makeText(ProcedureCaseLegalBasisListActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if ("已经没有下一页了".equals(caseList.getMessage())) {
//                        ProcedureCaseLegalBasisListActivity.this.page = -1;
//                        return;
//                    }
//
//                    if (null != caseList.getResult().getList()) {
//                        dataList.addAll(caseList.getResult().getList());
//                        for (sampleCaseVoList data : dataList) {
//                            Log.d(TAG, "data.getName() = " + data.getCaseName());
//                            Log.d(TAG, "data.getCasePerson() = " + data.getLitigtName());
//                        }
//                        caseInvestigateAdapter.notifyDataSetChanged();
//                    } else
//                        Log.d(TAG, "caseList.getCaseInfo().getCaseInfo() = NULL!");
//
//                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- 5");
//                    Toast.makeText(ProcedureCaseLegalBasisListActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
//                Toast.makeText(ProcedureCaseLegalBasisListActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public class LegalBasisAdapter extends BaseAdapter{

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<ProcedureCaseLegalBasis> caseDataList;

        public LegalBasisAdapter(Context context, List<ProcedureCaseLegalBasis> dataList){
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
                convertView = inflater.inflate(R.layout.item_procedure_case_legal_basis_list, parent, false);
                holder.tvLegalBasisCode = (TextView) convertView.findViewById(R.id.tvLeagalBasisCode);
                holder.tvLegalBasisContext = (TextView) convertView.findViewById(R.id.tvLeagalBasisContext);
                holder.ivCheckedBox = (ImageView) convertView.findViewById(R.id.iv_check_box);
                holder.ivStarCheckBox = (ImageView) convertView.findViewById(R.id.iv_star_check_box);
                holder.llCheckBox = (LinearLayout) convertView.findViewById(R.id.ll_check_box);
                holder.llLegalBasisContext = (LinearLayout) convertView.findViewById(R.id.ll_item_text);
                holder.llStarCheckBox = (LinearLayout) convertView.findViewById(R.id.ll_star_check_box);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if(caseDataList.get(position).getBasisType().equals("1")){//1：处罚依据，隐藏主定性依据选项; 0:定性依据
                holder.llStarCheckBox.setVisibility(View.GONE);
                holder.tvLegalBasisCode.setText("处罚依据代码："+caseDataList.get(position).getLawCode());
                holder.tvLegalBasisContext.setText(caseDataList.get(position).getLawContent());
            }else{
                holder.tvLegalBasisCode.setText("定性依据代码："+caseDataList.get(position).getLawCode());
                holder.tvLegalBasisContext.setText(caseDataList.get(position).getLawContent());
            }
            if(caseDataList.get(position).isChecked())
                holder.ivCheckedBox.setImageResource(R.mipmap.checkbox_checked);
            else
                holder.ivCheckedBox.setImageResource(R.mipmap.checkbox_null);
            if(caseDataList.get(position).isMajorBasis()){
                holder.llStarCheckBox.setBackgroundColor(ContextCompat.getColor(ProcedureCaseLegalBasisListActivity.this, R.color.blue));
                holder.ivStarCheckBox.setImageResource(R.mipmap.star_fill);
            }else{
                holder.llStarCheckBox.setBackgroundColor(ContextCompat.getColor(ProcedureCaseLegalBasisListActivity.this, R.color.gray));
                holder.ivStarCheckBox.setImageResource(R.mipmap.star_line);
            }
            holder.llCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(caseDataList.get(position).isChecked()){
                        caseDataList.get(position).setChecked(false);
                        if(caseDataList.get(position).isMajorBasis())
                            caseDataList.get(position).setMajorBasis(false);
                    }else
                        caseDataList.get(position).setChecked(true);
                    legalBasisAdapter.notifyDataSetChanged();
                }
            });
            holder.llLegalBasisContext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(caseDataList.get(position).isChecked()){
                        caseDataList.get(position).setChecked(false);
                        if(caseDataList.get(position).isMajorBasis())
                            caseDataList.get(position).setMajorBasis(false);
                    }else
                        caseDataList.get(position).setChecked(true);
                    legalBasisAdapter.notifyDataSetChanged();
                }
            });
            holder.llStarCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(ProcedureCaseLegalBasis basis:caseDataList)
                        basis.setMajorBasis(false);
                    caseDataList.get(position).setChecked(true);
                    caseDataList.get(position).setMajorBasis(true);
                    legalBasisAdapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }

        private class ViewHolder {
            private TextView tvLegalBasisCode;
            private TextView tvLegalBasisContext;
            private ImageView ivCheckedBox;
            private ImageView ivStarCheckBox;
            private LinearLayout llCheckBox;
            private LinearLayout llLegalBasisContext;
            private LinearLayout llStarCheckBox;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

