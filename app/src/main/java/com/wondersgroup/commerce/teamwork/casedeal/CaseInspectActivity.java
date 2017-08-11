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

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.AttachmentDTO;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.DynamicComponentObject;
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
 * Created by Lee on 2016/2/17.
 * 现场检查 页面
 */
public class CaseInspectActivity  extends AppCompatActivity {

    private String TAG = "CaseInspectActivity";
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.components_LinearLayout)LinearLayout componentsLinearLayout;        //控件显示部分（动态添加控件）
    @Bind(R.id.commit_record_Button)Button commitButton;                        //提交按钮

    private String clueNo = null;                       //线索号
    private String serialNo = null;                     //序列号

    private List<DataVolume> componentObjectsList;            //动态控件对象列表
    private ArrayList<EditText> arrayedittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象

    private int resultCode = 0;
    private RootAppcation app;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_inspect_enquire);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        initView();
        initData();
    }

    private void initData(){

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010007");
        if(clueNo != null)
            map.put("clueNo", clueNo);
        else
            map.put("serialNo", serialNo);

        Log.d(TAG, "map.toString() = " + map.toString());
        String url = "";
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_INSPECT_EDIT;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_INSPECT_EDIT;
        Call<DynamicComponentObject> call = ApiManager.caseApi.toInspectEdit(url,map);
        call.enqueue(new Callback<DynamicComponentObject>() {
            @Override
            public void onResponse(Response<DynamicComponentObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final DynamicComponentObject dynamicComponentObject = response.body();

                    if ((null == dynamicComponentObject) || (null == dynamicComponentObject.getResult())) {
                        Toast.makeText(CaseInspectActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else
                        componentObjectsList = dynamicComponentObject.getResult();

                    //添加动态控件
                    if (null != componentObjectsList && componentObjectsList.size() > 1) {
                        dynamicWidgetUtils = new DynamicWidgetUtils(CaseInspectActivity.this, componentObjectsList, arrayedittext);
                        dynamicWidgetUtils.addComponents(componentsLinearLayout);
                    }

                } else {
                    Log.d(TAG, "CaseInspectActivity --------------- response.is not Success()");
                    Toast.makeText(CaseInspectActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseInspectActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CaseInvestigateTitle caseInvestigateTitle = (CaseInvestigateTitle) bundle.getSerializable("CaseInvestigateTitle");
        if(caseInvestigateTitle != null)
            clueNo = caseInvestigateTitle.getClueNo();
        else
            serialNo = bundle.getString("NoteRecordSpot");

        title.setText("现场检查");

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValue())
                    commitRecord();
            }
        });

    }

    //检查提交内容
    private boolean checkValue() {
        for(int i=0; i<componentObjectsList.size(); i++){
            if(componentObjectsList.get(i).getRequired().equals("1")){
                if(arrayedittext.get(i).getText() == null || arrayedittext.get(i).getText().toString().equals("")){
                    Toast.makeText(CaseInspectActivity.this, componentObjectsList.get(i).getName()+"不能为空！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    //新增、修改记录
    private void commitRecord() {

        Map<String, String> map = new HashMap<>();
        Map<String, String> mapValue = new HashMap<>();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        map.put("wsCodeReq", "03010008");
        if(clueNo != null){//新增记录
            map.put("clueNo", clueNo);
            map.put("serialNo", "");
        }else{//修改记录
            map.put("serialNo", serialNo);
            map.put("clueNo", "");
        }

        DataShared dataShared = new DataShared(this);
        final String userId = (String) dataShared.get("userId", "");
        map.put("userId", userId);

        for(int i=0; i<componentObjectsList.size(); i++){
            Log.d(TAG,"componentObjectsList.get("+i+").getName() : "+componentObjectsList.get(i).getName()+" = "+arrayedittext.get(i).getText().toString());
            if(arrayedittext.get(i).getText()!=null && !arrayedittext.get(i).getText().toString().trim().equals(""))
                mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+arrayedittext.get(i).getText().toString().trim()+"\"");
            else
                mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+""+"\"");
        }
        map.put("InspectStr", mapValue.toString());

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
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_INSPECT_SAVE;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.CASE_INSPECT_SAVE;
        Call<BackResultObject> call = ApiManager.caseApi.saveInspect(url,map);
        call.enqueue(new Callback<BackResultObject>() {
            @Override
            public void onResponse(Response<BackResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    BackResultObject result = response.body();
                    if (result.getCode().equals(ApiManager.RESULT_SUCCESS)){
                        CaseInspectActivity.this.setResult(resultCode, null);
                        CaseInspectActivity.this.finish();
                    }else{
                        Toast.makeText(CaseInspectActivity.this, "提交失败！", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "CaseInspectActivity--------result.getMessage() = " + result.getMessage());
                    }

                } else {
                    Log.d(TAG, "CaseInspectActivity --------------- response.is not Success()");
                    Toast.makeText(CaseInspectActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseInspectActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
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
