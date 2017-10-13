package com.wondersgroup.commerce.teamwork.casedeal;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.activity.SingleChoiceActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.CaseQueryDic;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.model.SerializableMap;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.TreeBean;
import com.wondersgroup.commerce.model.ccjc.Tree;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.teamwork.casedeal.bean.CaseQueryBean;
import com.wondersgroup.commerce.utils.CodeUtils;
import com.wondersgroup.commerce.utils.DateUtil;
import com.wondersgroup.commerce.utils.DynamicWidgetUtils;
import com.wondersgroup.commerce.utils.FileHelper;
import com.wondersgroup.commerce.utils.TableRowUtils;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.widget.TableRow;
import com.wondersgroup.commerce.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Lee on 2016/2/29.
 * 我的案件查询、案件查询
 */
public class CaseEnquireActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "CaseEnquireActivity";

    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.components_LinearLayout)
    LinearLayout componentsLinearLayout;        //控件显示部分（动态添加控件）
    @Bind(R.id.query_button)
    Button queryButton;                        //查询按钮
    @Bind(R.id.clear_btn)
    Button clearButton;//清空按钮
    @Bind(R.id.img_scan)
    ImageView imageScan;

    private List<DataVolume> componentObjectsList;            //动态控件对象列表
    private ArrayList<EditText> arrayedittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象
    private Map<String, String> conditionMap;           //查询条件，要传递到下一个activity页面中去的map
    private TableRowUtils tableRowUtils;
    private String type;
    private TotalLoginBean loginBean;
    private CaseQueryDic caseQueryDic;
    private TreeBean lajgTree;
    private TableRow cxlx, lajg, bajg, gsqk, ajjd, ajjd_1, ajjd_2, ajmc, dsrmc, sycx, larq, larq_1, larq_2, cfrq, cfrq_1, cfrq_2;
    private HashMap<String, String> queryConditionMap;
    private HashMap<String, String> param;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_enquire);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        type = getIntent().getStringExtra(Constants.TYPE);
        queryConditionMap = new HashMap<>();
        param = new HashMap<>();
        param.put("currentPage", currentPage + "");
        if (Constants.WDAJCX_ID.equals(type) || Constants.WDAJCX_ID_2.equals(type)) {
            initView();
            initData();
        } else if (Constants.AJCX_ID.equals(type)) {
            initView2();
            initViewData2();
            initData2();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && permissions[0] == Manifest.permission.CAMERA && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, 101);
        } else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            TreeBean treeBean = null;
            switch (requestCode) {
                case 101:
                    Log.e("扫码", "返回成功");
                    String result = data.getStringExtra("result");
                    int p1 = result.indexOf("=");
                    int p2 = result.indexOf("=", p1 + 1);
                    String clueNo = new String(Base64.decode(result.substring(p1 + 1, p2), 0));
                    if (TextUtils.isEmpty(clueNo)) {
                        Toast.makeText(this, "查询的clueNo为空！", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent mIntent = new Intent(this, CaseQueryDetailActivity.class);
                        mIntent.putExtra("clueNo", clueNo);
                        mIntent.putExtra(Constants.TYPE, Constants.SC);
                        startActivity(mIntent);
                    }
                    break;
                case 10:
                    if (data != null) {
                        treeBean = data.getParcelableExtra("data");
                        if (treeBean != null) {
                            lajg.setContent(treeBean.getName());
                            queryConditionMap.put("transactOrgan", treeBean.getId());
                        }
                    }
                    break;
                case 11:
                    if (data != null) {
                        treeBean = data.getParcelableExtra("data");
                        if (treeBean != null) {
                            cxlx.setContent(treeBean.getName());
                            param.put("queryType", treeBean.getId());
                            String code = queryConditionMap.get("transactOrgan") != null ? queryConditionMap.get("transactOrgan") : queryConditionMap.get("appOrgan");
                            if ("01".equals(treeBean.getId())) {
                                lajg.setTitle("立案机关");
                                queryConditionMap.remove("appOrgan");
                                queryConditionMap.put("transactOrgan", code);
                            } else {
                                lajg.setTitle("办案机关");
                                queryConditionMap.remove("transactOrgan");
                                queryConditionMap.put("appOrgan", code);
                            }
                        }
                    }
                    break;
                case 12:
                    if (data != null) {
                        treeBean = data.getParcelableExtra("data");
                        if (treeBean != null) {
                            bajg.setContent(treeBean.getName());
                            if ("01".equals(param.get("queryType")))
                                queryConditionMap.put("transactDept", treeBean.getId());
                            else
                                queryConditionMap.put("appOrgan", treeBean.getId());
                        }
                    }
                    break;
                case 13:
                    if (data != null) {
                        ArrayList<TreeBean> list = data.getParcelableArrayListExtra("list");
                        String content = "";
                        for (TreeBean bean : list) {
                            content += bean.getName() + ", ";
                        }
                        content = content.substring(0, content.length() - 2);
                        sycx.setContent(content);
                        String code = "";
                        for (TreeBean bean : list) {
                            code += bean.getName() + ",";
                        }
                        queryConditionMap.put("suitProcedure", code.substring(0, code.length() - 1));
                    }
                case 14:
                    if (data != null) {
                        treeBean = data.getParcelableExtra("data");
                        if (treeBean != null) {
                            gsqk.setContent(treeBean.getName());
                            queryConditionMap.put("caseNoticeFlag", treeBean.getId());
                        }
                    }
                    break;
                case 15:
                    if (data != null) {
                        treeBean = data.getParcelableExtra("data");
                        if (treeBean != null) {
                            ajjd_1.setContent(treeBean.getName());
                            queryConditionMap.put("regStage1", treeBean.getId());
                        }
                    }
                    break;
                case 16:
                    if (data != null) {
                        treeBean = data.getParcelableExtra("data");
                        if (treeBean != null) {
                            ajjd_2.setContent(treeBean.getName());
                            queryConditionMap.put("regStage2", treeBean.getId());
                        }
                    }
                    break;
            }
        }

    }

    private void initView() {
        title.setText("我的案件查询");
        queryButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    private void initData() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010001");
        String url = "";
        Call<DynamicComponentObject> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_QUERY_CONDITION;
            call = ApiManager.caseApi.toQueryCase(url, map);
        } else{
            url = CaseApi.CASE_QUERY_CONDITION;
            call = ApiManager.shyApi.toQueryCase(url, map);
        }
        call.enqueue(new Callback<DynamicComponentObject>() {
            @Override
            public void onResponse(Response<DynamicComponentObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final DynamicComponentObject dynamicComponentObject = response.body();

                    if ((null == dynamicComponentObject) || (null == dynamicComponentObject.getResult())) {
                        Toast.makeText(CaseEnquireActivity.this, "获得案件数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else
                        componentObjectsList = dynamicComponentObject.getResult();

                    addEmpty();
                    //添加动态控件
                    tableRowUtils = new TableRowUtils(CaseEnquireActivity.this, componentsLinearLayout, componentObjectsList, false);
                    tableRowUtils.build();

                } else {
                    Log.d(TAG, "CaseInspectActivity --------------- response.is not Success()");
                    Toast.makeText(CaseEnquireActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseEnquireActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView2() {
        title.setText("案件查询");
        if (Constants.SC.equals(RootAppcation.getInstance().getVersion())) {
            imageScan.setVisibility(View.VISIBLE);
            imageScan.setOnClickListener(this);
        }

        queryButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        cxlx = new TableRow.Builder(this)
                .title("查询类型")
                .select("")
                .build();
        componentsLinearLayout.addView(cxlx);

        lajg = new TableRow.Builder(this)
                .title("立案机关")
                .select("")
                .build();
        componentsLinearLayout.addView(lajg);

        bajg = new TableRow.Builder(this)
                .title("办案机构")
                .select("")
                .build();
        componentsLinearLayout.addView(bajg);

        sycx = new TableRow.Builder(this)
                .title("适用程序")
                .select("")
                .build();
        componentsLinearLayout.addView(sycx);

        gsqk = new TableRow.Builder(this)
                .title("公示情况")
                .select("请选择")
                .build();
        componentsLinearLayout.addView(gsqk);

        ajmc = new TableRow.Builder(this)
                .title("案件名称")
                .input("请输入案件名称")
                .build();
        componentsLinearLayout.addView(ajmc);

        dsrmc = new TableRow.Builder(this)
                .title("当事人名称")
                .input("请输入当事人名称")
                .build();
        componentsLinearLayout.addView(dsrmc);

        ajjd_1 = new TableRow.Builder(this)
                .titleW(0)
                .select("")
                .content("正处于")
                .build();
        queryConditionMap.put("regStage1", "1");

        ajjd_2 = new TableRow.Builder(this)
                .titleW(0)
                .select("")
                .content("全部")
                .hideBtmLine()
                .build();
        queryConditionMap.put("regStage2", "");

        ajjd = new TableRow.Builder(this)
                .title("案件阶段")
                .addChild(ajjd_1, ajjd_2)
                .build();
        componentsLinearLayout.addView(ajjd);

        larq_1 = new TableRow.Builder(this)
                .titleW(0)
                .select("开始时间")
                .hasIndex(false)
                .content(DateUtil.getNowYear() + "-01-01")
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        DateUtil.createDatePicker(CaseEnquireActivity.this, new DateUtil.DateListener() {
                            @Override
                            public void back(Date date) {
                                String time = DateUtil.getYMD(date);
                                larq_1.setContent(time);
                                queryConditionMap.put("regCaseDateShow1", time);
                            }
                        });
                    }
                })
                .build();
        queryConditionMap.put("regCaseDateShow1", DateUtil.getNowYear() + "-01-01");

        larq_2 = new TableRow.Builder(this)
                .titleW(0)
                .select("结束时间")
                .hasIndex(false)
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        DateUtil.createDatePicker(CaseEnquireActivity.this, new DateUtil.DateListener() {
                            @Override
                            public void back(Date date) {
                                String time = DateUtil.getYMD(date);
                                larq_2.setContent(time);
                                queryConditionMap.put("regCaseDateShow2", time);
                            }
                        });
                    }
                })
                .hideBtmLine()
                .build();

        larq = new TableRow.Builder(this)
                .title("立案日期")
                .addChild(larq_1, larq_2)
                .build();
        componentsLinearLayout.addView(larq);

        cfrq_1 = new TableRow.Builder(this)
                .titleW(0)
                .select("开始时间")
                .hasIndex(false)
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        DateUtil.createDatePicker(CaseEnquireActivity.this, new DateUtil.DateListener() {
                            @Override
                            public void back(Date date) {
                                String time = DateUtil.getYMD(date);
                                cfrq_1.setContent(time);
                                queryConditionMap.put("regCaseDateShow1", time);
                            }
                        });
                    }
                })
                .build();

        cfrq_2 = new TableRow.Builder(this)
                .titleW(0)
                .select("结束时间")
                .hasIndex(false)
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        DateUtil.createDatePicker(CaseEnquireActivity.this, new DateUtil.DateListener() {
                            @Override
                            public void back(Date date) {
                                String time = DateUtil.getYMD(date);
                                cfrq_2.setContent(time);
                                queryConditionMap.put("regCaseDateShow2", time);
                            }
                        });
                    }
                })
                .build();

        cfrq = new TableRow.Builder(this)
                .title("处罚决定日期")
                .addChild(cfrq_1, cfrq_2)
                .build();
        componentsLinearLayout.addView(cfrq);
    }

    public void initViewData2(){
        cxlx.setContent("按立案机关查询");
        param.put("queryType", "01");

        lajg.setTitle("立案机关");
        lajg.setContent(loginBean.getResult().getOrganName());
        queryConditionMap.put("transactOrgan", loginBean.getResult().getOrganId());

        bajg.setContent(loginBean.getResult().getDeptName());
        queryConditionMap.put("transactDept", loginBean.getResult().getDeptId());

        sycx.setContent("简易程序, 一般程序, 其他");
        queryConditionMap.put("suitProcedure", "1,2,9");

        gsqk.setContent("");

        ajmc.setContent("");

        dsrmc.setContent("");

        ajjd_1.setContent("正处于");
        queryConditionMap.put("regStage1", "1");

        ajjd_2.setContent("全部");
        queryConditionMap.put("regStage2", "");

        larq_1.setContent(DateUtil.getNowYear() + "-01-01");
        queryConditionMap.put("regCaseDateShow1", DateUtil.getNowYear() + "-01-01");
        larq_2.setContent("");
        queryConditionMap.remove("regCaseDateShow2");

        cfrq_1.setContent("");
        queryConditionMap.remove("regCaseDateShow1");
        cfrq_2.setContent("");
        queryConditionMap.remove("regCaseDateShow2");
    }

    public void initData2() {
        final Dialog dialog = LoadingDialog.showCanCancelable(this);
        dialog.show();
        String url = CaseApi.URL_CASE_1 + CaseApi.CASE_QUERY_LIST;
        param = new HashMap<>();
        param.put(Constants.WS_CODE_REQ, "03010025");
        param.put(Constants.USER_ID, loginBean.getResult().getUserId());
        param.put(Constants.ORGAN_ID, loginBean.getResult().getOrganId());
        param.put(Constants.DEPT_ID, loginBean.getResult().getDeptId());
        Call<Result<CaseQueryDic>> call = ApiManager.caseApi.caseQueryList(url, param);
        call.enqueue(new Callback<Result<CaseQueryDic>>() {
            @Override
            public void onResponse(Response<Result<CaseQueryDic>> response, Retrofit retrofit) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.body() != null) {
                    caseQueryDic = response.body().getObject();
                    handleDic();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(CaseEnquireActivity.this, "获取查询条件失败，无法选择", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加全部项
     */
    private void addEmpty() {
        if (componentObjectsList != null) {
            for (int i = 0; i < componentObjectsList.size(); i++) {
                DataVolume data = componentObjectsList.get(i);
                List<DataVolume> remark = data.getRemark();
                if (remark != null && remark.size() != 0) {
                    DataVolume dataVolume = new DataVolume();
                    dataVolume.setKey("");
                    dataVolume.setValue("全部");
                    remark.add(0, dataVolume);
                }
            }
        }
    }

    //检查查询条件
    private boolean checkValue() {
        for (int i = 0; i < componentObjectsList.size(); i++) {
            if (componentObjectsList.get(i).getRequired().equals("1")) {
                if (tableRowUtils.getContent(i) == null || "".equals(tableRowUtils.getContent(i))) {
                    Toast.makeText(CaseEnquireActivity.this, componentObjectsList.get(i).getName() + "不能为空！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    //查询案件
    private void queryRecord() {
        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010003");
        map.put("userId", loginBean.getResult().getUserId());

        conditionMap = new HashMap<String, String>();

        for (int i = 0; i < componentObjectsList.size(); i++) {
            if (tableRowUtils.getContent(i) != null && !"".equals(tableRowUtils.getContent(i))) {
                if ("2".equals(componentObjectsList.get(i).getType())) {
                    String key = "";
                    for (int k = 0; k < componentObjectsList.get(i).getRemark().size(); k++) {
                        if (tableRowUtils.getContent(i).trim()
                                .equals(componentObjectsList.get(i).getRemark().get(k).getValue()))
                            key = componentObjectsList.get(i).getRemark().get(k).getKey();
                    }
                    conditionMap.put("\"" + componentObjectsList.get(i).getKey() + "\"", "\"" + key + "\"");
                } else
                    conditionMap.put("\"" + componentObjectsList.get(i).getKey() + "\"", "\"" + tableRowUtils.getContent(i).trim() + "\"");
            } else
                conditionMap.put("\"" + componentObjectsList.get(i).getKey() + "\"", "\"" + "" + "\"");
        }
        conditionMap.put("\"" + "currentPage" + "\"", "\"" + "1" + "\"");
        map.put("condition", conditionMap.toString());

        Log.d(TAG, "map = " + map.toString());
        String url = "";
        Call<CaseQueryResult> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_QUERY_MY_CASE;
            call = ApiManager.caseApi.queryMyCase(url, map);
        } else{
            url = CaseApi.CASE_QUERY_MY_CASE;
            call = ApiManager.shyApi.queryMyCase(url, map);
        }
        call.enqueue(new Callback<CaseQueryResult>() {
            @Override
            public void onResponse(Response<CaseQueryResult> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    CaseQueryResult caseQueryResult = response.body();

                    if ((null == caseQueryResult) || (null == caseQueryResult.getResult())) {
                        Toast.makeText(CaseEnquireActivity.this, "案件查询数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.d(TAG, "0-------caseQueryResult.getCaseInfo().getList().size() = " + caseQueryResult.getResult().size());
                    if (null != caseQueryResult.getResult() && caseQueryResult.getResult().size() > 0) {
                        Log.d(TAG, "1-------caseQueryResult.getCaseInfo().getList().size() = " + caseQueryResult.getResult().size());
                        Bundle bundle = new Bundle();
                        bundle.putString("activityType", "queryMyCase");
                        SerializableMap tmpmap = new SerializableMap();
                        tmpmap.setMap(conditionMap);
                        bundle.putSerializable("conditionMap", tmpmap);
                        Intent intent = new Intent(CaseEnquireActivity.this, CaseInvestigateActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else
                        Toast.makeText(CaseEnquireActivity.this, "没有查到案件！", Toast.LENGTH_SHORT).show();

                } else {
                    Log.d(TAG, "CaseEnquireActivity --------------- response.is not Success()");
                    Toast.makeText(CaseEnquireActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseEnquireActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "CaseEnquireActivity --------------- t.getMessage() = " + t.getMessage());
            }
        });
    }

    public void queryRecord2() {
        queryConditionMap.put("caseName", ajmc.getInput().trim());
        queryConditionMap.put("litigtName", dsrmc.getInput().trim());
        queryConditionMap.put("currentPage", 1 + "");

        Intent intent = new Intent(this, RecyclerActivity.class);
        intent.putExtra(Constants.TYPE, "casequery");
        intent.putExtra(Constants.TITLE, "案件查询");
        intent.putExtra(Constants.PARAM, param);
        intent.putExtra("queryConditionMap", queryConditionMap);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_scan:
                scan();
                break;
            case R.id.query_button:
                if (Constants.WDAJCX_ID.equals(type) || Constants.WDAJCX_ID_2.equals(type)) {
                    if (checkValue() == true)
                        queryRecord();
                } else if (Constants.AJCX_ID.equals(type)) {
                    queryRecord2();
                }

                break;
            case R.id.clear_btn:
                if (Constants.WDAJCX_ID.equals(type) || Constants.WDAJCX_ID_2.equals(type)) {
                    for (int i = 0; i < componentObjectsList.size(); i++) {
                        tableRowUtils.setContent(i, "");
                    }
                } else if (Constants.AJCX_ID.equals(type)) {
                    initViewData2();
                }
                break;
        }
    }

    public void scan() {
        //1.权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            //2.扫描
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, 101);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    //处理字典项
    public void handleDic() {
        //立案机关
        lajgTree = getTree(caseQueryDic.getOrgList());
//        lajg.setContent(lajgTree.getName());
        lajg.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
//                ArrayList<TreeBean> list = new ArrayList<TreeBean>();
//                list.addAll(lajgTree.getChilds());
//                lajgTree.getChilds().clear();
//                list.add(0, lajgTree);
                Log.e("startTime", System.currentTimeMillis() + "");
                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra("root", lajgTree);
                intent.putExtra(Constants.TITLE, lajgTree.getName());
                intent.putExtra(Constants.TYPE, "caseQuery");
                startActivityForResult(intent, 10);
            }
        });

        final ArrayList<TreeBean> cxlxList = MapToList(caseQueryDic.getQueryTypeMap());
        cxlx.setContent(cxlxList.get(0).getName());
        cxlx.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra(Constants.TYPE, "caseQuery2");
                intent.putExtra(Constants.TITLE, "选择");
                intent.putParcelableArrayListExtra("list", cxlxList);
                startActivityForResult(intent, 11);
            }
        });

        final ArrayList<TreeBean> bajgList = (ArrayList<TreeBean>) caseQueryDic.getDeptList();
        bajg.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra(Constants.TYPE, "caseQuery2");
                intent.putParcelableArrayListExtra("list", bajgList);
                intent.putExtra(Constants.TITLE, "选择");
                startActivityForResult(intent, 12);
            }
        });

        final ArrayList<TreeBean> sycxList = MapToList(caseQueryDic.getAppProcedureMap());
        sycx.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra(Constants.TYPE, "caseQuery3");
                intent.putParcelableArrayListExtra("list", sycxList);
                intent.putExtra(Constants.TITLE, "多项选择");
                startActivityForResult(intent, 13);
            }
        });

        final ArrayList<TreeBean> gsqkList = MapToList(caseQueryDic.getPublicityMap());
        gsqk.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra(Constants.TYPE, "caseQuery2");
                intent.putParcelableArrayListExtra("list", gsqkList);
                intent.putExtra(Constants.TITLE, "选择");
                startActivityForResult(intent, 14);
            }
        });

        final ArrayList<TreeBean> ajjdList1 = MapToList(caseQueryDic.getStatusMap());
        final ArrayList<TreeBean> ajjdList2 = MapToList(caseQueryDic.getStatusMap2());
        final ArrayList<TreeBean> ajjdList3 = MapToList(caseQueryDic.getStatusMap3());
        ajjd_1.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra(Constants.TYPE, "caseQuery2");
                intent.putParcelableArrayListExtra("list", ajjdList1);
                intent.putExtra(Constants.TITLE, "选择");
                startActivityForResult(intent, 15);
            }
        });

        ajjd_2.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                ArrayList<TreeBean> list = null;

                if ("1".equals(queryConditionMap.get("regStage1")))
                    list = ajjdList2;
                else
                    list = ajjdList3;

                Intent intent = new Intent(CaseEnquireActivity.this, SingleChoiceActivity.class);
                intent.putExtra(Constants.TYPE, "caseQuery2");
                intent.putParcelableArrayListExtra("list", list);
                intent.putExtra(Constants.TITLE, "选择");
                startActivityForResult(intent, 16);

            }
        });
    }

    public TreeBean getTree(List<TreeBean> list) {
        //找出根节点
        Log.e("startTime", System.currentTimeMillis() + "");
        Log.e("size", list.size() + "");
        TreeBean root = new TreeBean();
        loop1:
        for (int i = 0; i < list.size(); i++) {
            TreeBean bean = list.get(i);
            boolean match = false;
            loop2:
            for (int j = 0; j < list.size(); j++) {
                if (bean.getpId().equals(list.get(j).getId())) {
                    match = true;
                    break loop2;
                }
            }
            if (!match) {
                root = bean;
            }
        }
        //组成树---测试需要10s!!需改进
//        addChilds(root, list);
        Hawk.put("caseQueryDic", list);

        Log.e("endTime", System.currentTimeMillis() + "");
        return root;
    }

    public void addChilds(TreeBean root, List<TreeBean> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getpId().equals(root.getId())) {
                if (root.getChilds() == null) {
//                    root.setChilds(Arrays.asList(list.get(i)));
                    ArrayList<TreeBean> arrayList = new ArrayList<>();
                    arrayList.add(list.get(i));
                    root.setChilds(arrayList);
                } else {
                    root.getChilds().add(list.get(i));
                }
            }
        }

        if (root.getChilds() != null) {
            for (TreeBean child : root.getChilds()) {
                addChilds(child, list);
            }
        }
    }

    public ArrayList<TreeBean> MapToList(LinkedHashMap linkedHashMap) {
        ArrayList<TreeBean> list = new ArrayList<>();
        Iterator iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            TreeBean treeBean = new TreeBean();
            treeBean.setId(entry.getKey().toString());
            treeBean.setName(entry.getValue().toString());
            list.add(treeBean);
        }

        return list;
    }
}
