package com.wondersgroup.commerce.teamwork.gwpy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.FwTypeBean;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.model.SwTypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by yclli on 2015/11/29.
 */
public class CheckListActivity extends AppCompatActivity {

    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.activity_checklist_list) ListView listView;

    private String type;

    private String docType;
    private List<String> resultList;
    private SendDetailBean sendDetailBean;
    private ReceiveDetailBean receiveDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        type = getIntent().getStringExtra("type");
        if(type == null) type = "BLJG";
        String titleString = getIntent().getStringExtra("title");
        if (titleString == null) titleString = "选择办理结果";
        title.setText(titleString);
        docType = getIntent().getStringExtra("docType");
        if ("BLJG".equals(type)) {
            resultList = getResultList();
            listView.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, resultList));
        }else if("swDocType".equals(type) || "fwDocType".equals(type) || "fwDocClass".equals(type) ||
                "fwOpenOp".equals(type) || "fwDept".equals(type)){
            resultList = getOptionList();
            listView.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, resultList));
        }
    }

    @OnItemClick(R.id.activity_checklist_list)
    public void onItemClick(int position){
        String text = resultList.get(position);
        if("BLJG".equals(type)){
            Intent data = new Intent();
            data.putExtra("result", text);
            data.putExtra("position", position);
            setResult(1, data);
            finish();
        }else if("swDocType".equals(type)){
            Intent data = new Intent();
            data.putExtra("result", text);
            data.putExtra("position", position);
            setResult(1, data);
            finish();
        }else if("fwDocType".equals(type)){
            Intent data = new Intent();
            data.putExtra("result", text);
            data.putExtra("position", position);
            setResult(2, data);
            finish();
        }else if("fwDocClass".equals(type)){
            Intent data = new Intent();
            data.putExtra("result", text);
            data.putExtra("position", position);
            setResult(3, data);
            finish();
        }else if("fwOpenOp".equals(type)){
            Intent data = new Intent();
            data.putExtra("result", text);
            data.putExtra("position", position);
            setResult(4, data);
            finish();
        }else if("fwDept".equals(type)){
            Intent data = new Intent();
            data.putExtra("result", text);
            data.putExtra("position", position);
            setResult(5, data);
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public List<String> getResultList(){
        List<String> data = new ArrayList<String>();
        if("发文管理".equals(docType)){
            sendDetailBean = SendDetailBean.sendDetailBean;
            List<SendDetailBean.Result.DicFlowOperationList> orgList = sendDetailBean.getResult().getDicFlowOperationList();
            if(orgList == null){
                return data;
            }
            for(int i=0; i<orgList.size(); i++){
                data.add(orgList.get(i).getOperationName());
            }
        }else if("收文管理".equals(docType)){
            receiveDetailBean = ReceiveDetailBean.receiveDetailBean;
            List<ReceiveDetailBean.Result.DicFlowOperationList> orgList = receiveDetailBean.getResult().getDicFlowOperationList();
            if(orgList == null){
                return data;
            }
            for(int i=0; i<orgList.size(); i++){
                data.add(orgList.get(i).getOperationName());
            }
        }
        return data;
    }

    public List<String> getOptionList(){
        List<String> data = new ArrayList<String>();
        if("swDocType".equals(type)){
            List<SwTypeBean.Result.Doctype> doctypes = Hawk.get("swDocType");
            if(doctypes!=null){
                for(int i=0; i<doctypes.size(); i++){
                    data.add(doctypes.get(i).getValue());
                }
            }
        }else if("fwDocType".equals(type)){
            List<FwTypeBean.Result.DocTypeItem> docTypeItems = Hawk.get("fwDocType");
            if(docTypeItems!=null){
                for(int i=0; i<docTypeItems.size(); i++){
                    data.add(docTypeItems.get(i).getValue());
                }
            }
        }else if("fwDocClass".equals(type)){
            List<FwTypeBean.Result.DocClassItem> docClassItems = Hawk.get("fwDocClass");
            if(docClassItems!=null){
                for(int i=0; i<docClassItems.size(); i++){
                    data.add(docClassItems.get(i).getValue());
                }
            }
        }else if("fwOpenOp".equals(type)){
            data.add("主动公开");
            data.add("依申请公开");
            data.add("不公开");
            data.add("全部");
        }else if("fwDept".equals(type)){
            List<FwTypeBean.Result.DeptMap> deptMaps = Hawk.get("fwDept");
            if(deptMaps!=null){
                for(int i=0; i<deptMaps.size(); i++){
                    data.add(deptMaps.get(i).getValue());
                }
            }
        }
        return data;
    }
}
