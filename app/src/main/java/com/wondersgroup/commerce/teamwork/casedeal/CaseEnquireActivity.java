package com.wondersgroup.commerce.teamwork.casedeal;

import android.Manifest;
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

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.SerializableMap;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.utils.CodeUtils;
import com.wondersgroup.commerce.utils.DynamicWidgetUtils;
import com.wondersgroup.commerce.utils.TableRowUtils;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.zxing.activity.CaptureActivity;

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
 * Created by Lee on 2016/2/29.
 * 案件查询页面
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
    Button quieryButton;                        //查询按钮
    @Bind(R.id.clear_btn)
    Button clearButton;//清空按钮
    @Bind(R.id.img_scan)
    ImageView imageScan;

    private List<DataVolume> componentObjectsList;            //动态控件对象列表
    private ArrayList<EditText> arrayedittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象
    private Map<String, String> conditionMap;           //查询条件，要传递到下一个activity页面中去的map
    private RootAppcation app;
    private TableRowUtils tableRowUtils;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_enquire);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("我的案件查询");
        app = (RootAppcation) getApplication();

        initView();
        initData();
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
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && data != null) {
            Log.e("扫码", "返回成功");
            String result = data.getStringExtra("result");
            int p1 = result.indexOf("=");
            int p2 = result.indexOf("=", p1 + 1);
            String clueNo = new String(Base64.decode(result.substring(p1 + 1, p2),0));
            if (TextUtils.isEmpty(clueNo)) {
                Toast.makeText(this, "查询的clueNo为空！", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Intent mIntent = new Intent(this, CaseQueryDetailActivity.class);
                mIntent.putExtra("clueNo", clueNo);
                startActivity(mIntent);
            }
        }
    }

    private void initView() {
        quieryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValue() == true)
                    queryRecord();
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < componentObjectsList.size(); i++) {
                    tableRowUtils.setContent(i, "");
                }
            }
        });

        if (Constants.SC.equals(RootAppcation.getInstance().getVersion())){
            imageScan.setVisibility(View.VISIBLE);
            imageScan.setOnClickListener(this);
        }
    }

    private void initData() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010001");

        String url = "";
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_QUERY_CONDITION;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_QUERY_CONDITION;
        Call<DynamicComponentObject> call = ApiManager.caseApi.toQueryCase(url,map);
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
//                    if (null != componentObjectsList && componentObjectsList.size() > 1) {
//                        dynamicWidgetUtils = new DynamicWidgetUtils(CaseEnquireActivity.this, componentObjectsList, arrayedittext);
//                        dynamicWidgetUtils.addComponents(componentsLinearLayout);
//                    }

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
        final String userId = loginBean.getResult().getUserId();
//        map.put("userId", userId);
        map.put("userId", "00005859");

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
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_QUERY_MY_CASE;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_QUERY_MY_CASE;
        Call<CaseQueryResult> call = ApiManager.caseApi.queryMyCase(url,map);
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
}
