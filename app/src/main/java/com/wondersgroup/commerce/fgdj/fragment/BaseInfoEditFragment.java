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
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.SingleChoiceActivity;
import com.wondersgroup.commerce.activity.SingleItemSelectActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.AreaBean;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.EntBaseInfo;
import com.wondersgroup.commerce.fgdj.bean.LeaderInfo;
import com.wondersgroup.commerce.fgdj.util.CheckUtil;
import com.wondersgroup.commerce.fgdj.widget.InputUnit;
import com.wondersgroup.commerce.fgdj.widget.SelectUnit;
import com.wondersgroup.commerce.interface_.TextChanger;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/5/9.
 */

public class BaseInfoEditFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.tv_ent_name)
    TextView tvEntName;
    @Bind(R.id.tv_ent_type)
    TextView tvEntType;
    @Bind(R.id.tv_ent_id)
    TextView tvEntId;
    @Bind(R.id.input_enp_num)
    InputUnit inputEnpNum;//从业人员
    @Bind(R.id.tv_es_date)
    TextView tvEsDate;
    @Bind(R.id.input_vend_inc)
    InputUnit inputVendInc;//年营业收入
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.select_area)
    SelectUnit selectArea;//所属乡镇
    @Bind(R.id.select_market)
    SelectUnit selectMarket;//是否经营场所
    @Bind(R.id.select_party)
    SelectUnit selectParty;//是否组建党组织
    @Bind(R.id.select_league)
    SelectUnit selectLeague;//是否组建团组织
    @Bind(R.id.select_instructor)
    SelectUnit selectInstructor;//是否派驻党建指导员
    @Bind(R.id.select_wf)
    SelectUnit selectWF;//是否组建妇联
    @Bind(R.id.select_union)
    SelectUnit selectUnion;//是否组建工会
    @Bind(R.id.input_tel)
    InputUnit inputTel;//联系电话
    @Bind(R.id.select_social_duty)
    SelectUnit selectSocialDuty;//主要社会职务
    @Bind(R.id.select_political_status)
    SelectUnit selectPoliticalStatus;//政治面貌
    @Bind(R.id.layout_political_status_add)
    LinearLayout layoutPoliticalStatusAdd;
    @Bind(R.id.select_secretary)
    SelectUnit selectSecretary;//是否党、团组织书记
    @Bind(R.id.input_organization_name)
    InputUnit inputOrganizationName;//党、团组织名称
    @Bind(R.id.tv_person_name)
    TextView tvPersonName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_cert_type)
    TextView tvCertType;
    @Bind(R.id.tv_cert_no)
    TextView tvCertNo;

    private String entId,entType;
    private DicBean dicBean;
    public EntBaseInfo entBaseInfo;
    public LeaderInfo leaderInfo;
    private TotalLoginBean loginBean;
    private FragmentListener fragmentListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_info_edit, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        entId = getArguments().getString(Constants.ENT_ID);
        entType = getArguments().getString(Constants.ENT_TYPE);
        dicBean = Hawk.get(Constants.DIC);
        entBaseInfo = getArguments().getParcelable(Constants.ENT_BASE_INFO);
        if (entBaseInfo == null)
            entBaseInfo = new EntBaseInfo();
        leaderInfo = getArguments().getParcelable(Constants.LEADER_INFO);
        if (leaderInfo == null)
            leaderInfo = new LeaderInfo();

        initData();

        tvEntType.setText(entType);

        inputEnpNum.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                entBaseInfo.setEnpNum(String.valueOf(s));
            }
        });

        inputVendInc.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                entBaseInfo.setVendInc(String.valueOf(s));
            }
        });

        selectMarket.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否经营场所在专业市场内");
                startActivityForResult(intent, Constants.WHETHER_MARKET);
            }
        });

        selectParty.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否组建党组织");
                startActivityForResult(intent, Constants.BUILD_PARTY);
            }
        });

        selectLeague.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否组建团组织");
                startActivityForResult(intent, Constants.BUILD_LEAGUE);
            }
        });

        selectInstructor.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否派驻党建指导员");
                startActivityForResult(intent, Constants.DISPATCH_INSTRUCTOR);
            }
        });

        selectWF.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否组建妇联");
                startActivityForResult(intent, Constants.BUILD_WF);
            }
        });

        selectUnion.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否组建工会");
                startActivityForResult(intent, Constants.BUILD_LABOR);
            }
        });

        inputTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leaderInfo.setTel(String.valueOf(s));
            }
        });

        selectSocialDuty.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.SOCIAL_DUTY);
                intent.putExtra(Constants.TITLE,"主要社会职务");
                startActivityForResult(intent, Constants.REQUEST_SOCIAL_DUTY);
            }
        });

        selectPoliticalStatus.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.POLITICAL_STATUS);
                intent.putExtra(Constants.TITLE,"政治面貌");
                startActivityForResult(intent, Constants.REQUEST_POLITICAL_STATUS);
            }
        });

        selectSecretary.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.RESULT_YES);
                intent.putExtra(Constants.TITLE,"是否党组织书记");
                startActivityForResult(intent, Constants.REQUEST_SECRETARY);
            }
        });

        inputOrganizationName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                if ("党组织名称：".equals(inputOrganizationName.tvName.getText())){
                    leaderInfo.setPartyOrgnztion(String.valueOf(s));
                }else {
                    leaderInfo.setLeagueName(String.valueOf(s));
                }
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
                default:
                    break;
                case Constants.REQUEST_SECRETARY:
                    selectSecretary.tvSelected.setText(keyValue.getValue());
                    selectSecretary.setSelected(true);
                    if ("是否党组织书记：".equals(selectSecretary.tvName.getText())){
                        leaderInfo.setIsPartySecty(keyValue.getKey());
                    }else {
                        leaderInfo.setIsLeagueSecty(keyValue.getKey());
                    }
                    break;
                case Constants.REQUEST_POLITICAL_STATUS:
                    selectPoliticalStatus.tvSelected.setText(keyValue.getValue());
                    if (keyValue.getKey().equals("01")){
                        layoutPoliticalStatusAdd.setVisibility(View.VISIBLE);
                        selectSecretary.setName("是否党组织书记：");
                        inputOrganizationName.tvName.setText("党组织名称：");
                    }else if (keyValue.getKey().equals("02")){
                        layoutPoliticalStatusAdd.setVisibility(View.VISIBLE);
                        selectSecretary.setName("是否团组织书记：");
                        inputOrganizationName.tvName.setText("团组织名称：");
                    }else {
                        layoutPoliticalStatusAdd.setVisibility(View.GONE);
                    }
                    selectPoliticalStatus.setSelected(true);
                    leaderInfo.setPoliticalStatus(keyValue.getKey());
                    break;
                case Constants.REQUEST_SOCIAL_DUTY:
                    selectSocialDuty.tvSelected.setText(keyValue.getValue());
                    selectSocialDuty.setSelected(true);
                    leaderInfo.setSocialDuty(keyValue.getKey());
                    break;
                case Constants.WHETHER_MARKET:
                    selectMarket.tvSelected.setText(keyValue.getValue());
                    entBaseInfo.setIsMarket(keyValue.getKey());
                    selectMarket.setSelected(true);
                    break;
                case Constants.BUILD_PARTY:
                    selectParty.tvSelected.setText(keyValue.getValue());
                    entBaseInfo.setIsBuildParty(keyValue.getKey());
                    selectParty.setSelected(true);
                    //往viewpage中动态添加或删除fragmen
                    if ("1".equals(keyValue.getKey()) && fragmentListener!=null){
                        fragmentListener.addParty();
                    }else if ("0".equals(keyValue.getKey()) && fragmentListener!=null){
                        fragmentListener.delParty();
                    }
                    break;
                case Constants.BUILD_LEAGUE:
                    selectLeague.tvSelected.setText(keyValue.getValue());
                    entBaseInfo.setIsBuildLeague(keyValue.getKey());
                    selectLeague.setSelected(true);
                    if ("1".equals(keyValue.getKey()) && fragmentListener!=null){
                        fragmentListener.addLeague();
                    }else if ("0".equals(keyValue.getKey()) && fragmentListener!=null){
                        fragmentListener.delLeague();
                    }
                    break;
                case Constants.DISPATCH_INSTRUCTOR:
                    selectInstructor.tvSelected.setText(keyValue.getValue());
                    selectInstructor.setSelected(true);
                    entBaseInfo.setDispatchInstructor(keyValue.getKey());
                    break;
                case Constants.BUILD_WF:
                    selectWF.tvSelected.setText(keyValue.getValue());
                    entBaseInfo.setIsBuildWf(keyValue.getKey());
                    selectWF.setSelected(true);
                    break;
                case Constants.BUILD_LABOR:
                    selectUnion.tvSelected.setText(keyValue.getValue());
                    entBaseInfo.setIsBuildLabor(keyValue.getKey());
                    selectUnion.setSelected(true);
                    break;
            }
        }else if (resultCode == Constants.RESPONSE_AREA_CODE){
            KeyValue keyValue = data.getParcelableExtra(Constants.KEY_VALUE);
            selectArea.setSelected(true);
            selectArea.tvSelected.setText(keyValue.getValue());
            entBaseInfo.setAreaCode(keyValue.getKey());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
        }
    }

    public boolean checkSubmit(){
        CheckUtil checkUtil = new CheckUtil();

        return checkUtil.checkInput(inputEnpNum) &&
                checkUtil.checkPositiveInt(inputEnpNum) &&
                checkUtil.checkInput(inputVendInc) &&
                checkUtil.checkPositive(inputVendInc) &&
                checkUtil.checkInput(inputTel) &&
                checkUtil.checkTel(inputTel) &&
                (layoutPoliticalStatusAdd.getVisibility() == View.VISIBLE ?(checkUtil.checkInput(inputOrganizationName)):true) &&
                checkUtil.checkSelect(selectArea) &&
                checkUtil.checkSelect(selectMarket) &&
                checkUtil.checkSelect(selectParty) &&
                checkUtil.checkSelect(selectLeague) &&
                checkUtil.checkSelect(selectInstructor) &&
                checkUtil.checkSelect(selectWF) &&
                checkUtil.checkSelect(selectUnion) &&
                checkUtil.checkSelect(selectSocialDuty) &&
                checkUtil.checkSelect(selectPoliticalStatus) &&
                checkUtil.checkSelect(selectLeague) &&
                (layoutPoliticalStatusAdd.getVisibility() == View.VISIBLE ?(checkUtil.checkSelect(selectSecretary)):true);
    }

    public EntBaseInfo getEntBaseInfo() {
        entBaseInfo.setEntId(entId);
        return entBaseInfo;
    }

    public LeaderInfo getLeaderInfo() {
        leaderInfo.setEntId(entId);
        return leaderInfo;
    }

    public void getAreaCode(){
        final Dialog dialog = LoadingDialog.showCanCancelable(getActivity());
        dialog.show();
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        hashMap.put(Constants.ENT_ID,entId);
        Call<Result<List<AreaBean>>> call = ApiManager.fgdjApi.queryArea(hashMap);
        call.enqueue(new Callback<Result<List<AreaBean>>>() {
            @Override
            public void onResponse(Response<Result<List<AreaBean>>> response, Retrofit retrofit) {
                List<AreaBean> arrayList = response.body().getObject() ;

                if (arrayList.size()!=0){
                    handleResult(arrayList);
                    dialog.dismiss();
                    Hawk.put(Constants.BASE_AREA_LIST,arrayList);
                }else {
                    Toast.makeText(getActivity(),"获取乡镇信息失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(),"获取乡镇信息失败",Toast.LENGTH_SHORT).show();
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
        intent.putExtra(Constants.TITLE,"所属乡镇（街道）");
        intent.putExtra(Constants.TYPE, Constants.BASE_AREA_LIST);
        intent.putExtra(Constants.AREA_LIST,data);
        startActivityForResult(intent, Constants.REQUEST_AREA_CODE);
    }

    public String changeCode(String code){
        if ("1".equals(code))
            return "是";
        else if ("0".equals(code))
            return "否";
        return "";
    }

    public interface FragmentListener{
        void addParty();
        void delParty();
        void addLeague();
        void delLeague();
    }

    public void setFragmentListener(FragmentListener fragmentListener) {
        this.fragmentListener = fragmentListener;
    }

    public void initData(){
        tvEntName.setText(entBaseInfo.getEntName());
        tvEntId.setText(entBaseInfo.getRegNo());
        tvEsDate.setText(entBaseInfo.getEsDate());
        tvAddress.setText(entBaseInfo.getAddress());
        tvPersonName.setText(leaderInfo.getName());
        tvCertNo.setText(leaderInfo.getCerno());
        if ("1".equals(leaderInfo.getSex()))
            tvSex.setText("男");
        else
            tvSex.setText("女");

        if (leaderInfo.getCertype()!=null)
            tvCertType.setText(dicBean.getCertType().get(leaderInfo.getCertype()).toString());

        inputEnpNum.etInput.setText(entBaseInfo.getEnpNum());
        inputVendInc.etInput.setText(entBaseInfo.getVendInc());
        selectMarket.tvSelected.setText(changeCode(entBaseInfo.getIsMarket()));
        selectParty.tvSelected.setText(changeCode(entBaseInfo.getIsBuildParty()));
        selectLeague.tvSelected.setText(changeCode(entBaseInfo.getIsBuildLeague()));
        selectInstructor.tvSelected.setText(changeCode(entBaseInfo.getDispatchInstructor()));
        selectWF.tvSelected.setText(changeCode(entBaseInfo.getIsBuildWf()));
        selectUnion.tvSelected.setText(changeCode(entBaseInfo.getIsBuildLabor()));
        inputTel.etInput.setText(leaderInfo.getTel());
        if (leaderInfo.getSocialDuty()!=null)
            selectSocialDuty.tvSelected.setText(dicBean.getMapSocialDuty().get(leaderInfo.getSocialDuty()).toString());

        if (leaderInfo.getPoliticalStatus()!=null)
            selectPoliticalStatus.tvSelected.setText(dicBean.getPoliticalStatus().get(leaderInfo.getPoliticalStatus()).toString());

        if ("01".equals(leaderInfo.getPoliticalStatus())){
            layoutPoliticalStatusAdd.setVisibility(View.VISIBLE);
            selectSecretary.setName("是否党组织书记：");
            selectSecretary.tvSelected.setText(changeCode(leaderInfo.getIsPartySecty()));
            inputOrganizationName.tvName.setText("党组织名称：");
            inputOrganizationName.etInput.setText(leaderInfo.getPartyOrgnztion());
        }else if ("02".equals(leaderInfo.getPoliticalStatus())){
            layoutPoliticalStatusAdd.setVisibility(View.VISIBLE);
            selectSecretary.setName("是否团组织书记：");
            selectSecretary.tvSelected.setText(changeCode(leaderInfo.getIsLeagueSecty()));
            inputOrganizationName.tvName.setText("团组织名称：");
            inputOrganizationName.etInput.setText(leaderInfo.getLeagueName());
        }

        if (entBaseInfo.getAreaCode()!=null){
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
                hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
                hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
                hashMap.put(Constants.ENT_ID,entId);
                Call<Result<List<AreaBean>>> call = ApiManager.fgdjApi.queryArea(hashMap);
                call.enqueue(new Callback<Result<List<AreaBean>>>() {
                    @Override
                    public void onResponse(Response<Result<List<AreaBean>>> response, Retrofit retrofit) {
                        List<AreaBean> arrayList = response.body().getObject() ;
                        if (arrayList.size()!=0){
                            Hawk.put(Constants.BASE_AREA_LIST,arrayList);
                            setSelectArea(arrayList);
                        }else {
                            Toast.makeText(getActivity(),"获取乡镇信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(),"获取乡镇信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    public void setSelectArea(List<AreaBean> list){
        String area = "";
        for (AreaBean areaBean : list){
            if (entBaseInfo.getAreaCode().equals(areaBean.getId())){
                area = areaBean.getName();
                break;
            }
        }
        selectArea.tvSelected.setText(area);
    }
}
