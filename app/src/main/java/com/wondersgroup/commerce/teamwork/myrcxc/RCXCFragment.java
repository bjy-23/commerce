package com.wondersgroup.commerce.teamwork.myrcxc;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.SerializableMap;
import com.wondersgroup.commerce.teamwork.mysupervision.KeyValue;
import com.wondersgroup.commerce.teamwork.mysupervision.MapToListUtil;
import com.wondersgroup.commerce.teamwork.mysupervision.ShowSingleDialogListListener;
import com.wondersgroup.commerce.utils.CheckUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiajiangyi on 2016/5/5 0005 10:38
 */

public class RCXCFragment extends Fragment {

    @Bind(R.id.rcxc_single_choose)
    EditText rcxcSingleChoose;
    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.regNo_et)
    EditText regNoEt;
    @Bind(R.id.address_et)
    EditText addressEt;
    @Bind(R.id.commit_btn)
    Button commitBtn;
    @Bind(R.id.clear_btn)
    Button clearBtn;
    @Bind(R.id.searchpage_layout)
    LinearLayout searchpageLayout;

    private Map<String,String> checkTypeMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rcxc, null);
        ButterKnife.bind(this, view);

        checkTypeMap.put("xyfl","信用分类");
        checkTypeMap.put("zdhy","重点行业");
        checkTypeMap.put("zdqy","重点区域");
        MapToListUtil checkUtil = new MapToListUtil(checkTypeMap);
        List<KeyValue> checkKeyValues = checkUtil.mapToKeyValues();
        rcxcSingleChoose.setOnClickListener(new ShowSingleDialogListListener(
                checkKeyValues, getActivity(), rcxcSingleChoose));
//        CheckUtil.limitCheckMinCount(nameEt, Constants.inputMinCount);
        CheckUtil.limitCheckMaxCount(nameEt, Constants.inputMaxCount);
        regNoEt.setInputType(InputType.TYPE_CLASS_DATETIME);
        if(RCXCActivity.getCurrTab()==1){
            nameEt.setHint("字号名称");
            addressEt.setHint("地址");
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.commit_btn, R.id.clear_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit_btn:

                checkCommit();

                break;
            case R.id.clear_btn:

                rcxcSingleChoose.setText("");
                nameEt.setText("");
                regNoEt.setText("");
                addressEt.setText("");

                break;
        }
    }

    private void checkCommit() {

        if (rcxcSingleChoose.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "请选择分类", Toast.LENGTH_SHORT) .show();
            return;
        }
//
//        if(nameEt.getText().toString().equals("")){
//            nameEt.setError("输入字数不得少于"+Constants.inputMinCount);
//            return;
//        }

        if (nameEt.getText().toString().equals("")
                && regNoEt.getText().toString().equals("")
                && addressEt.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "必须填写一个查询条件",Toast.LENGTH_SHORT).show();

            return;
        }

        HashMap para = new HashMap<String, String>();

        if (rcxcSingleChoose.getText().toString().equals("信用分类")) {
            para.put("queryType", 2 + "");
        } else if (rcxcSingleChoose.getText().toString().equals("重点行业")) {
            para.put("queryType", 3 + "");
        } else if (rcxcSingleChoose.getText().toString().equals("重点区域")) {
            para.put("queryType", 4 + "");
        }

        if (nameEt.getText().toString().equals("")
                && regNoEt.getText().toString().equals("")
                && addressEt.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "查询全部信息", Toast.LENGTH_SHORT).show();
        }

        if (!nameEt.getText().toString().equals("")) {
            para.put("unitName", nameEt.getText().toString());
        }

        if (!regNoEt.getText().toString().equals("")) {
            para.put("regNo", regNoEt.getText().toString());
        }

        if (!addressEt.getText().toString().equals("")) {
            para.put("address", addressEt.getText().toString());
        }

        SerializableMap map = new SerializableMap();
        map.setMap(para);
        Bundle bundle = new Bundle();
        bundle.putSerializable("para",map);

        Intent i = new Intent(getActivity(),RCXCDetailActivity.class);
        if (RCXCActivity.getCurrTab() == 1) {//个体
            i.putExtra("currTab",1+"");
            i.putExtras(bundle);

        } else {//企业
            i.putExtra("currTab",0+"");
            i.putExtras(bundle);
        }
        startActivity(i);
    }
}




















