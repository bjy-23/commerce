package com.wondersgroup.commerce.fgdj.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.SingleChoiceActivity;
import com.wondersgroup.commerce.activity.SingleItemSelectActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.AreaBean;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.PartyInfo;
import com.wondersgroup.commerce.fgdj.util.CheckUtil;
import com.wondersgroup.commerce.fgdj.widget.InputUnit;
import com.wondersgroup.commerce.fgdj.widget.SelectUnit;
import com.wondersgroup.commerce.fgdj.widget.TimeUnit;
import com.wondersgroup.commerce.interface_.TextChanger;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.utils.DateUtil;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/5/9.
 */

public class DangzuzhiEditFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.input_full_name)
    InputUnit inputFullName;
    @BindView(R.id.input_super_name)
    InputUnit inputSuperName;
    @BindView(R.id.select_party_level)
    SelectUnit selectPartyLevel;
    @BindView(R.id.input_all_counts)
    InputUnit inputAllCounts;
    @BindView(R.id.input_brunch_counts)
    InputUnit inputBrunchCounts;
    @BindView(R.id.time_es)
    TimeUnit timeEs;
    @BindView(R.id.select_es_way)
    SelectUnit selectEsWay;
    @BindView(R.id.input_address)
    InputUnit inputAddress;
    @BindView(R.id.input_tel)
    InputUnit inputTel;
    @BindView(R.id.select_place)
    SelectUnit selectPlace;
    @BindView(R.id.input_secretary_name)
    InputUnit inputSecretaryName;
    @BindView(R.id.input_secretary_tel)
    InputUnit inputSecretaryTel;
    @BindView(R.id.input_secretary_dept)
    InputUnit inputSecretaryDept;
    @BindView(R.id.select_funds)
    SelectUnit selectFunds;
    @BindView(R.id.select_area)
    SelectUnit selectArea;
    @BindView(R.id.layout_all_counts)
    LinearLayout layoutAllCounts;
    @BindView(R.id.layout_brunch_counts)
    LinearLayout layoutBrunchCounts;

    private PartyInfo partyInfo;
    private String entId;
    private View viewInflater;
    private TotalLoginBean loginBean;
    private DicBean dicBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewInflater = inflater.inflate(R.layout.fragment_dangzuzhi_edit,container,false);
        ButterKnife.bind(this,viewInflater);

        return viewInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        entId = getArguments().getString(Constants.ENT_ID);
        dicBean = Hawk.get(Constants.DIC);
        partyInfo = getArguments().getParcelable(Constants.PARTY_INFO);
        if (partyInfo == null)
            partyInfo = new PartyInfo();

        initData();

        inputFullName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setFullName(String.valueOf(s));
            }
        });

        inputSuperName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setSuperName(String.valueOf(s));
            }
        });

        inputAllCounts.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
               partyInfo.setGeneralPartyBranchNum(String.valueOf(s));
            }
        });

        inputBrunchCounts.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setPartyBranchNum(String.valueOf(s));
            }
        });

        inputTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setTel(String.valueOf(s));
            }
        });

        inputAddress.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setAddress(String.valueOf(s));
            }
        });

        selectPartyLevel.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.PARTY_LEVEL);
                intent.putExtra(Constants.TITLE,"党组织级别");
                startActivityForResult(intent, Constants.REQUEST_PARTY_LEVEL);
            }
        });

        selectEsWay.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.ES_WAY);
                intent.putExtra(Constants.TITLE,"组建方式");
                startActivityForResult(intent, Constants.REQUEST_ES_WAY);
            }
        });

        selectPlace.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_HAVE);
                intent.putExtra(Constants.TITLE,"党组织活动场所");
                startActivityForResult(intent, Constants.REQUEST_ACT_PLACE);
            }
        });

        selectFunds.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TITLE,"党建活动经费");
                intent.putExtra(Constants.TYPE, Constants.RESULT_HAVE);
                startActivityForResult(intent, Constants.REQUEST_ACT_FUNDS);
            }
        });

        timeEs.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.createDatePicker(getActivity(),timeEs.tvDate);
            }
        });

        inputSecretaryName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setSecretaryName(String.valueOf(s));
            }
        });

        inputSecretaryTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setSecretaryMobile(String.valueOf(s));
            }
        });

        inputSecretaryDept.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                partyInfo.setSecretaryJobStation(String.valueOf(s));
            }
        });

        selectArea.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAreaCode();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.RESPONSE_KEY_VALUE){
            KeyValue keyValue = data.getParcelableExtra(Constants.KEY_VALUE);
            switch (requestCode){
                case Constants.REQUEST_PARTY_LEVEL:
                    selectPartyLevel.setSelected(true);
                    selectPartyLevel.tvSelected.setText(keyValue.getValue());
                    partyInfo.setPartyOgLevel(keyValue.getKey());
                    if ("1".equals(keyValue.getKey())){
                        layoutAllCounts.setVisibility(View.VISIBLE);
                        layoutBrunchCounts.setVisibility(View.VISIBLE);
                    }else if ("2".equals(keyValue.getKey())){
                        layoutAllCounts.setVisibility(View.GONE);
                        layoutBrunchCounts.setVisibility(View.VISIBLE);
                    }else {
                        layoutAllCounts.setVisibility(View.GONE);
                        layoutBrunchCounts.setVisibility(View.GONE);
                    }
                    break;
                case Constants.REQUEST_ACT_PLACE:
                    selectPlace.setSelected(true);
                    selectPlace.tvSelected.setText(keyValue.getValue());
                    partyInfo.setHasActivityPlace(keyValue.getKey());
                    break;
                case Constants.REQUEST_ACT_FUNDS:
                    selectFunds.setSelected(true);
                    selectFunds.tvSelected.setText(keyValue.getValue());
                    partyInfo.setHasActivityFunds(keyValue.getKey());
                    break;
                case Constants.REQUEST_ES_WAY:
                    selectEsWay.setSelected(true);
                    selectEsWay.tvSelected.setText(keyValue.getValue());
                    partyInfo.setBuildWay(keyValue.getKey());
                    break;
            }
        }else if (resultCode == Constants.RESPONSE_AREA_CODE){
            KeyValue keyValue = data.getParcelableExtra(Constants.KEY_VALUE);
            selectArea.setSelected(true);
            selectArea.tvSelected.setText(keyValue.getValue());
            partyInfo.setAreaCode(keyValue.getKey());
        }
    }

    @Override
    public void onClick(View v) {

    }

    public PartyInfo getPartyInfo() {
        partyInfo.setEntId(entId);
        partyInfo.setEsDate(timeEs.getTime());
        return partyInfo;
    }

    public boolean checkSubmit(){
        CheckUtil checkUtil = new CheckUtil();

        return checkUtil.checkInput(inputFullName) &&
                checkUtil.checkInput(inputSuperName) &&
                checkUtil.checkSelect(selectPartyLevel) &&
                ((layoutAllCounts.getVisibility() == View.VISIBLE)? (checkUtil.checkInput(inputAllCounts) && checkUtil.checkPositiveInt(inputAllCounts)):true )&&
                ((layoutBrunchCounts.getVisibility() == View.VISIBLE)? (checkUtil.checkInput(inputBrunchCounts) && checkUtil.checkPositiveInt(inputBrunchCounts)):true )&&
                checkUtil.checkTime(timeEs) &&
                checkUtil.checkSelect(selectEsWay) &&
                checkUtil.checkInput(inputAddress) &&
                checkUtil.checkInput(inputTel) &&
                checkUtil.checkTel(inputTel) &&
                checkUtil.checkSelect(selectPlace) &&
                checkUtil.checkSelect(selectArea) &&
                checkUtil.checkSelect(selectFunds) &&
                checkUtil.checkInput(inputSecretaryName) &&
                checkUtil.checkInput(inputSecretaryTel) &&
                checkUtil.checkTel(inputSecretaryTel) &&
                checkUtil.checkInput(inputSecretaryDept);
    }

    public void getAreaCode(){
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(getActivity()).build();
        loadingDialog.show();
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        Call<Result<List<AreaBean>>> call = ApiManager.fgdjApi.queryPartyArea(hashMap);
        call.enqueue(new Callback<Result<List<AreaBean>>>() {
            @Override
            public void onResponse(Response<Result<List<AreaBean>>> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                List<AreaBean> list = response.body().getObject();
                if (list!=null){
                    if (list.size()!=0){
                        handleResult(list);
                        Hawk.put(Constants.PARTY_AREA_LIST,list);
                    }else {
                        Toast.makeText(getActivity(),"获取区县信息失败",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(),"获取区县信息失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(getActivity(),"获取区县信息失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleResult(List<AreaBean> list){
        int rankLow = list.get(0).getRank();
        for (int i=1;i<list.size();i++){
            if (list.get(i).getRank()<rankLow)
                rankLow = list.get(1).getRank();
        }
        ArrayList<AreaBean> data = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            if (rankLow == list.get(i).getRank())
                data.add(list.get(i));
        }
        for (int i=0;i<data.size();i++){
            for (int j=0;j<list.size();j++){
                if (list.get(j).getpId()!=null){
                    if ((list.get(j).getpId()).equals(data.get(i).getId())){
                        data.get(i).setHasChild(true);
                        break;
                    }
                }
            }
        }

        Intent intent = new Intent(getActivity(), SingleChoiceActivity.class);
        intent.putExtra(Constants.TITLE,"党组织所在区县");
        intent.putExtra(Constants.TYPE, Constants.PARTY_AREA_LIST);
        intent.putExtra(Constants.AREA_LIST,data);
        startActivityForResult(intent, Constants.REQUEST_AREA_CODE);
    }

    public String changeCode(String code){
       if ("1".equals(code))
           return "有";
        else if ("0".equals(code))
            return "无";
        return "";
    }

    public void initData(){
        inputFullName.setInput(partyInfo.getFullName());
        inputSuperName.setInput(partyInfo.getSuperName());
        inputAllCounts.setInput(partyInfo.getGeneralPartyBranchNum());
        inputBrunchCounts.setInput(partyInfo.getPartyBranchNum());
        inputTel.setInput(partyInfo.getTel());
        inputAddress.setInput(partyInfo.getAddress());

        if (partyInfo.getPartyOgLevel()!=null){
            if ("1".equals(partyInfo.getPartyOgLevel())){
                layoutAllCounts.setVisibility(View.VISIBLE);
                layoutBrunchCounts.setVisibility(View.VISIBLE);
            }else if ("2".equals(partyInfo.getPartyOgLevel())){
                layoutAllCounts.setVisibility(View.GONE);
                layoutBrunchCounts.setVisibility(View.VISIBLE);
            }else {
                layoutAllCounts.setVisibility(View.GONE);
                layoutBrunchCounts.setVisibility(View.GONE);
            }
        }

        if (partyInfo.getPartyOgLevel()!=null)
            selectPartyLevel.tvSelected.setText(dicBean.getPartyLevel().get(partyInfo.getPartyOgLevel()).toString());

        if (partyInfo.getBuildWay()!=null)
            selectEsWay.tvSelected.setText(dicBean.getLeagueBuildWay().get(partyInfo.getBuildWay()).toString());

        if (partyInfo.getHasActivityPlace()!=null)
            selectPlace.tvSelected.setText(changeCode(partyInfo.getHasActivityPlace()));

        if (partyInfo.getHasActivityFunds()!=null)
            selectFunds.tvSelected.setText(changeCode(partyInfo.getHasActivityFunds()));

        if (partyInfo.getEsDate()!=null)
            timeEs.setTime(partyInfo.getEsDate());
        else
            timeEs.setTime("请选择");

        inputSecretaryName.setInput(partyInfo.getSecretaryName());
        inputSecretaryTel.setInput(partyInfo.getSecretaryMobile());
        inputSecretaryDept.setInput(partyInfo.getSecretaryJobStation());
        if (partyInfo.getAreaCode()!=null){
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
            hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
            hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
            Call<Result<List<AreaBean>>> call = ApiManager.fgdjApi.queryPartyArea(hashMap);
            call.enqueue(new Callback<Result<List<AreaBean>>>() {
                @Override
                public void onResponse(Response<Result<List<AreaBean>>> response, Retrofit retrofit) {
                    List<AreaBean> list = response.body().getObject();
                    if (list!=null){
                        if (list.size()!=0){
                            Hawk.put(Constants.PARTY_AREA_LIST,list);
                            setSelectArea(list);
                        }else {
                            Toast.makeText(getActivity(),"获取区县信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(),"获取区县信息失败",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(),"获取区县信息失败",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setSelectArea(List<AreaBean> list){
        String area = "";
        for (AreaBean areaBean : list){
            if (partyInfo.getAreaCode().equals(areaBean.getId())){
                area = areaBean.getName();
                break;
            }
        }
        selectArea.tvSelected.setText(area);
    }
}
