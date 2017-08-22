package com.wondersgroup.commerce.teamwork.casedeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.AttachmentDTO;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.utils.DynamicWidgetUtils;
import com.wondersgroup.commerce.widget.MyProgressDialog;


import org.json.JSONException;
import org.json.JSONObject;

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
 * Created by Lee on 2016/2/19.
 * 询问笔录 编辑界面
 */
public class CaseRecordEnquireActivity extends AppCompatActivity {

    private String TAG = "CaseEnquireActivity";
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.components_LinearLayout)LinearLayout componentsLinearLayout;        //控件显示部分（动态添加控件）
    @Bind(R.id.commit_record_Button)Button commitButton;                        //提交按钮

    private String clueNo = null;                       //线索号
    private String serialNo = null;                     //序列号

    private List<DataVolume> componentObjectsList;            //动态控件对象列表
    private ArrayList<EditText> arrayedittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象
    private int resultCode = 1;
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_inspect_enquire);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        initView();
        initData();
    }

    private void initData() {

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010011");
        if(clueNo != null)
            map.put("clueNo", clueNo);
        else
            map.put("serialNo", serialNo);

        String url = "";
        if (ApiManager.caseType == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_ENQUIRE_EDIT;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_ENQUIRE_EDIT;
        Call<DynamicComponentObject> call = ApiManager.caseApi.toEnquireEdit(url,map);
        Log.d(TAG, "initData()--------map.toString() = " + map.toString());
        call.enqueue(new Callback<DynamicComponentObject>() {
            @Override
            public void onResponse(Response<DynamicComponentObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final DynamicComponentObject dynamicComponentObject = response.body();

                    if ((null == dynamicComponentObject) || (null == dynamicComponentObject.getResult())) {
                        Toast.makeText(CaseRecordEnquireActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else
                        componentObjectsList = dynamicComponentObject.getResult();

                    //添加动态控件
                    if (null != componentObjectsList && componentObjectsList.size() > 1) {
                        dynamicWidgetUtils = new DynamicWidgetUtils(CaseRecordEnquireActivity.this, componentObjectsList, arrayedittext);
                        dynamicWidgetUtils.addComponents(componentsLinearLayout);
                    }

                } else {
                    Log.d(TAG, "CaseInspectActivity --------------- response.is not Success()");
                    Toast.makeText(CaseRecordEnquireActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseRecordEnquireActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CaseInvestigateTitle caseInvestigateTitle = (CaseInvestigateTitle) bundle.getSerializable("CaseInvestigateTitle");
        if(caseInvestigateTitle != null)
            clueNo = caseInvestigateTitle.getClueNo();
        else
            serialNo = bundle.getString("NoteRecordEnquire");

        title.setText("询问笔录");

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValue() == true)
                    commitRecord();
            }
        });

    }

    //检查新增、修改内容
    private boolean checkValue() {
        for(int i=0; i<componentObjectsList.size(); i++){
            if(componentObjectsList.get(i).getRequired().equals("1")){
                if(arrayedittext.get(i).getText() == null || arrayedittext.get(i).getText().toString().equals("")){
                    Toast.makeText(CaseRecordEnquireActivity.this, componentObjectsList.get(i).getName()+"不能为空！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    //新增、修改记录
    private void commitRecord() {

        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> mapValue = new HashMap<String, String>();
        map.put("wsCodeReq", "03010012");
        if(clueNo != null){//新增记录
            map.put("clueNo", clueNo);
            map.put("serialNo", "");
        }else{//修改记录
            map.put("serialNo", serialNo);
            map.put("clueNo", "");
        }
        map.put("userId", loginBean.getResult().getUserId());

        for(int i=0; i<componentObjectsList.size(); i++){
            Log.d(TAG,"componentObjectsList.get("+i+").getName() : "+componentObjectsList.get(i).getName()+" = "+arrayedittext.get(i).getText().toString());
            if(arrayedittext.get(i).getText()!=null && !arrayedittext.get(i).getText().toString().trim().equals(""))
                if("2".equals(componentObjectsList.get(i).getType())){
                    String key = "";
                    for(int k = 0; k<componentObjectsList.get(i).getRemark().size(); k++){
                        if(arrayedittext.get(i).getText().toString().trim()
                                .equals(componentObjectsList.get(i).getRemark().get(k).getValue()))
                            key = componentObjectsList.get(i).getRemark().get(k).getKey();
                    }
                    mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+key+"\"");
                }else
                    mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+arrayedittext.get(i).getText().toString().trim()+"\"");
            else
                mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+""+"\"");
        }
        map.put("enquireStr", mapValue.toString());

        //上传附件
        ArrayList<AttachmentDTO> attachList = dynamicWidgetUtils.getAttachList();
        if(attachList!=null){
            StringBuffer attString = new StringBuffer();
            for(AttachmentDTO image:attachList){
                JSONObject jsonObj = new JSONObject();
                try {
                    if(image.getAttachId()!=null)
                        jsonObj.put("attachId",""+image.getAttachId());
                    else{
                        jsonObj.put("attachName",image.getAttachName());
                        jsonObj.put("attachFileStr", image.getAttachFileStr());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(attString.length()>0)
                    attString.append("*");
                attString.append(jsonObj.toString());
            }
            map.put("attachListStr", attString.toString());
        }

        Log.d(TAG, "map = " + map.toString());
        String url = "";
        if (ApiManager.caseType == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_ENQUIRE_SAVE;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_ENQUIRE_SAVE;
        Call<BackResultObject> call = ApiManager.caseApi.saveEnquire(url,map);
        call.enqueue(new Callback<BackResultObject>() {
            @Override
            public void onResponse(Response<BackResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    BackResultObject result = response.body();
                    if (result.getCode().equals(ApiManager.RESULT_SUCCESS)){
                        CaseRecordEnquireActivity.this.setResult(resultCode, null);
                        CaseRecordEnquireActivity.this.finish();
                    }else{
                        Toast.makeText(CaseRecordEnquireActivity.this, "提交失败！", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "CaseEnquireActivity--------result.getMessage() = " + result.getMessage());
                    }

                } else {
                    Log.d(TAG, "CaseEnquireActivity --------------- response.is not Success()");
                    Toast.makeText(CaseRecordEnquireActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseRecordEnquireActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult!,requestCode = " + requestCode);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b=data.getExtras(); //data为B中回传的Intent
//                ArrayList<ImageEntry> imageArrayList =(ArrayList<ImageEntry>)b.getSerializable("characterStream");//str即为回传的bitmap的字符流
//                if(imageArrayList!=null && imageArrayList.size()>0)
//                    dynamicWidgetUtils.refreshGridView(imageArrayList);
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

}
