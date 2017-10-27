package com.wondersgroup.commerce.fgdj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.SingleItemSelectActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.LeagueInfo;
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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/5/9.
 */

public class TuanzuzhiEditFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.input_full_name)
    InputUnit inputFullName;
    @BindView(R.id.input_short_name)
    InputUnit inputShortName;
    @BindView(R.id.input_super_name)
    InputUnit inputSuperName;
    @BindView(R.id.select_league_level)
    SelectUnit selectLeagueLevel;
    @BindView(R.id.input_address)
    InputUnit inputAddress;
    @BindView(R.id.time_es)
    TimeUnit timeEs;
    @BindView(R.id.select_es_way)
    SelectUnit selectEsWay;
    @BindView(R.id.input_tel)
    InputUnit inputTel;
    @BindView(R.id.input_over_num)
    InputUnit inputOverNum;
    @BindView(R.id.input_aff_num)
    InputUnit inputAffNum;
    @BindView(R.id.input_special_aff_num)
    InputUnit inputSpecialAffNum;
    @BindView(R.id.input_secretary_name)
    InputUnit inputSecretaryName;
    @BindView(R.id.input_secretary_tel)
    InputUnit inputSecretaryTel;
    @BindView(R.id.input_instructor_name)
    InputUnit inputInstructorName;
    @BindView(R.id.input_instructor_tel)
    InputUnit inputInstructorTel;
    @BindView(R.id.input_instructor_from)
    InputUnit inputInstructorFrom;
    @BindView(R.id.input_budget_inv)
    InputUnit inputBudgetInv;
    @BindView(R.id.input_dues_inv)
    InputUnit inputDuesInv;
    @BindView(R.id.input_ent_inv)
    InputUnit inputEntInv;
    @BindView(R.id.input_other_inv)
    InputUnit inputtOtherInv;

    private LeagueInfo leagueInfo;
    private String entId;
    private TotalLoginBean loginBean;
    private DicBean dicBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuanzuzhi_edit,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        entId = getArguments().getString(Constants.ENT_ID);
        dicBean = Hawk.get(Constants.DIC);
        leagueInfo = getArguments().getParcelable(Constants.LEAGUE_INFO);
        if (leagueInfo == null)
            leagueInfo = new LeagueInfo();

        initData();

        inputFullName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setFullName(String.valueOf(s));
            }
        });

        inputShortName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setName(String.valueOf(s));
            }
        });

        inputSuperName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setSuperName(String.valueOf(s));
            }
        });

        inputAddress.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setAddress(String.valueOf(s));
            }
        });

        timeEs.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.createDatePicker(getActivity(),timeEs.tvDate);
            }
        });

        inputTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setTel(String.valueOf(s));
            }
        });

        inputOverNum.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setOverEntNum(String.valueOf(s));
            }
        });

        inputAffNum.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setLeagueAffNum(String.valueOf(s));
            }
        });

        inputSpecialAffNum.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setFtLeagueAffNum(String.valueOf(s));
            }
        });

        inputSecretaryName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setSecretaryName(String.valueOf(s));
            }
        });

        inputSecretaryTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setSecretaryMobile(String.valueOf(s));
            }
        });

        inputInstructorName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setInstructorName(String.valueOf(s));
            }
        });

        inputInstructorTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setInstructorMobile(String.valueOf(s));
            }
        });

        inputInstructorFrom.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setInstructorFrom(String.valueOf(s));
            }
        });

        inputBudgetInv.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setFinanceBudgetInv(String.valueOf(s));
            }
        });

        inputDuesInv.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setMemberDuesInv(String.valueOf(s));
            }
        });

        inputEntInv.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setEntInv(String.valueOf(s));
            }
        });

        inputtOtherInv.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueInfo.setOthInv(String.valueOf(s));
            }
        });

        selectLeagueLevel.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.LEAGUE_LEVEL);
                intent.putExtra(Constants.TITLE,"团组织级别");
                startActivityForResult(intent, Constants.REQUEST_LEAGUE_LEVEL);
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.RESPONSE_KEY_VALUE){
            KeyValue keyValue = data.getParcelableExtra(Constants.KEY_VALUE);
            switch (requestCode){
                case Constants.REQUEST_LEAGUE_LEVEL:
                    selectLeagueLevel.tvSelected.setText(keyValue.getValue());
                    leagueInfo.setLeagueLevel(keyValue.getKey());
                    break;
                case Constants.REQUEST_ES_WAY:
                    selectEsWay.tvSelected.setText(keyValue.getValue());
                    leagueInfo.setBuildWay(keyValue.getKey());
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
    }

    public LeagueInfo getLeagueInfo() {
        leagueInfo.setEsDate(timeEs.getTime());
        leagueInfo.setEntId(entId);
        return leagueInfo;
    }

    public boolean checkSubmit(){
        CheckUtil checkUtil = new CheckUtil();
        return checkUtil.checkInput(inputFullName) &&
                checkUtil.checkInput(inputShortName) &&
                checkUtil.checkInput(inputSuperName) &&
                checkUtil.checkSelect(selectLeagueLevel) &&
                checkUtil.checkInput(inputAddress) &&
                checkUtil.checkTime(timeEs) &&
                checkUtil.checkSelect(selectEsWay) &&
                checkUtil.checkInput(inputOverNum) &&
                checkUtil.checkPositiveInt(inputOverNum) &&
                checkUtil.checkInput(inputAffNum) &&
                checkUtil.checkPositiveInt(inputAffNum) &&
                checkUtil.checkInput(inputSpecialAffNum) &&
                checkUtil.checkPositiveInt(inputSpecialAffNum) &&
                checkUtil.checkInput(inputSecretaryName) &&
                checkUtil.checkInput(inputSecretaryTel) &&
                checkUtil.checkTel(inputSecretaryTel) &&
                (TextUtils.isEmpty(inputInstructorTel.getInput()) ? true : checkUtil.checkTel(inputInstructorTel)) &&
                (TextUtils.isEmpty(inputBudgetInv.getInput()) ? true : checkUtil.checkPositive(inputBudgetInv)) &&
                (TextUtils.isEmpty(inputDuesInv.getInput()) ? true : checkUtil.checkPositive(inputDuesInv)) &&
                (TextUtils.isEmpty(inputEntInv.getInput()) ? true : checkUtil.checkPositive(inputEntInv)) &&
                (TextUtils.isEmpty(inputtOtherInv.getInput()) ? true : checkUtil.checkPositive(inputtOtherInv));
    }

    public void initData(){
        inputFullName.etInput.setText(leagueInfo.getFullName());
        inputShortName.etInput.setText(leagueInfo.getName());
        inputSuperName.etInput.setText(leagueInfo.getSuperName());
        inputAddress.etInput.setText(leagueInfo.getAddress());
        if (leagueInfo.getEsDate()!=null)
            timeEs.setTime(leagueInfo.getEsDate());
        else
            timeEs.setTime("请选择");

        inputTel.etInput.setText(leagueInfo.getTel());
        inputOverNum.etInput.setText(leagueInfo.getOverEntNum());
        inputAffNum.etInput.setText(leagueInfo.getLeagueAffNum());
        inputSpecialAffNum.etInput.setText(leagueInfo.getFtLeagueAffNum());
        inputSecretaryName.etInput.setText(leagueInfo.getSecretaryName());
        inputSecretaryTel.etInput.setText(leagueInfo.getSecretaryMobile());
        inputInstructorName.etInput.setText(leagueInfo.getInstructorName());
        inputInstructorTel.etInput.setText(leagueInfo.getInstructorMobile());
        inputInstructorFrom.etInput.setText(leagueInfo.getInstructorFrom());
        inputBudgetInv.etInput.setText(leagueInfo.getFinanceBudgetInv());
        inputDuesInv.etInput.setText(leagueInfo.getMemberDuesInv());
        inputEntInv.etInput.setText(leagueInfo.getEntInv());
        inputtOtherInv.etInput.setText(leagueInfo.getOthInv());
        if (leagueInfo.getLeagueLevel()!=null)
            selectLeagueLevel.tvSelected.setText(dicBean.getLeagueLevel().get(leagueInfo.getLeagueLevel()).toString());
        if (leagueInfo.getBuildWay()!=null)
            selectEsWay.tvSelected.setText(dicBean.getLeagueBuildWay().get(leagueInfo.getBuildWay()).toString());
    }
}
