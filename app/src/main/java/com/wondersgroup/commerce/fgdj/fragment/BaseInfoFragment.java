package com.wondersgroup.commerce.fgdj.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.AreaBean;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.EntBaseInfo;
import com.wondersgroup.commerce.fgdj.bean.LeaderInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class BaseInfoFragment extends Fragment {
    @BindView(R.id.tv_ent_name)
    TextView tvEntName;
    @BindView(R.id.tv_ent_type)
    TextView tvEntType;
    @BindView(R.id.tv_reg_no)
    TextView tvRegNo;
    @BindView(R.id.tv_es_date)
    TextView tvEsDate;
    @BindView(R.id.tv_enp_num)
    TextView tvEnpNum;
    @BindView(R.id.tv_vend_inc)
    TextView tvVendInc;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_is_build_party)
    TextView tvIsBuildParty;
    @BindView(R.id.tv_is_build_league)
    TextView tvIsBuildLeague;
    @BindView(R.id.tv_is_build_labor)
    TextView tvIsBuildLabor;
    @BindView(R.id.tv_dispatch_instructor)
    TextView tvDispatchInstructor;
    @BindView(R.id.tv_is_build_wf)
    TextView tvIsBuildWf;
    @BindView(R.id.tv_is_market)
    TextView tvIsMarket;
    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_cert_type)
    TextView tvCertType;
    @BindView(R.id.tv_cert_no)
    TextView tvCertNo;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_town)
    TextView tvTown;
    @BindView(R.id.tv_social_duty)
    TextView tvSocialDuty;
    @BindView(R.id.tv_political_status)
    TextView tvPolitcalStatus;
    @BindView(R.id.layout_political_status_add)
    View layoutPolitcalAdd;
    @BindView(R.id.tv_org_name)
    TextView tvOrgName;
    @BindView(R.id.tv_org)
    TextView tvOrg;
    @BindView(R.id.tv_is_secretary_name)
    TextView tvIsSecretaryName;
    @BindView(R.id.tv_is_secretary)
    TextView tvIsSecretary;

    private String entId, entType;
    private TotalLoginBean loginBean;
    private EntBaseInfo entBaseInfo;
    private LeaderInfo leaderInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        entId = getArguments().getString(Constants.ENT_ID);
        entType = getArguments().getString(Constants.ENT_TYPE);
        entBaseInfo = getArguments().getParcelable(Constants.ENT_BASE_INFO);
        if (entBaseInfo == null)
            entBaseInfo = new EntBaseInfo();
        leaderInfo = getArguments().getParcelable(Constants.LEADER_INFO);
        if (leaderInfo == null)
            leaderInfo = new LeaderInfo();

        initData();
        getAreaCode();
    }

    private void initData() {
        DicBean dicBean = Hawk.get(Constants.DIC);
        tvEntName.setText(entBaseInfo.getEntName());
        tvEntType.setText(entType);
        tvRegNo.setText(entBaseInfo.getRegNo());
        tvEsDate.setText(entBaseInfo.getEsDate());
        tvEnpNum.setText(entBaseInfo.getEnpNum());
        tvVendInc.setText(entBaseInfo.getVendInc());
        tvAddress.setText(entBaseInfo.getAddress());
        tvIsBuildParty.setText(yesOrNo(entBaseInfo.getIsBuildParty()));
        tvIsBuildLeague.setText(yesOrNo(entBaseInfo.getIsBuildLeague()));
        tvIsBuildLabor.setText(yesOrNo(entBaseInfo.getIsBuildLabor()));
        tvDispatchInstructor.setText(yesOrNo(entBaseInfo.getDispatchInstructor()));
        tvIsBuildWf.setText(yesOrNo(entBaseInfo.getIsBuildWf()));
        tvIsMarket.setText(yesOrNo(entBaseInfo.getIsMarket()));
        tvPersonName.setText(leaderInfo.getName());
        if ("1".equals(leaderInfo.getSex()))
            tvSex.setText("男");
        else
            tvSex.setText("女");
        LinkedHashMap mapCertType = ((DicBean) Hawk.get(Constants.DIC)).getCertType();
        tvCertType.setText(mapCertType.get(leaderInfo.getCertype()).toString());
        tvCertNo.setText(leaderInfo.getCerno());
        tvTel.setText(leaderInfo.getTel());
        if (!TextUtils.isEmpty(leaderInfo.getSocialDuty()) && dicBean.getMapSocialDuty() != null){
            tvSocialDuty.setText(dicBean.getMapSocialDuty().get(leaderInfo.getSocialDuty()).toString());
        }
        if (!TextUtils.isEmpty(leaderInfo.getPoliticalStatus()) && dicBean.getPoliticalStatus() != null){
            tvPolitcalStatus.setText(dicBean.getPoliticalStatus().get(leaderInfo.getPoliticalStatus()).toString());

            if ("01".equals(leaderInfo.getPoliticalStatus())){
                layoutPolitcalAdd.setVisibility(View.VISIBLE);
                tvIsSecretaryName.setText("是否党组织书记：");
                tvIsSecretary.setText(yesOrNo(leaderInfo.getIsPartySecty()));
                tvOrgName.setText("党组织名称：");
                tvOrg.setText(leaderInfo.getPartyOrgnztion());
            }else if ("02".equals(leaderInfo.getPoliticalStatus())){
                layoutPolitcalAdd.setVisibility(View.VISIBLE);
                tvIsSecretaryName.setText("是否团组织书记：");
                tvIsSecretary.setText(yesOrNo(leaderInfo.getIsLeagueSecty()));
                tvOrgName.setText("团组织名称：");
                tvOrg.setText(leaderInfo.getLeagueName());
            }
        }

    }

    private String yesOrNo(String input) {
        return "1".equals(input) ? "是" : "否";
    }

    public void getAreaCode(){
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
                if (arrayList.size()!=0 && !TextUtils.isEmpty(entBaseInfo.getAreaCode())){
                    for (AreaBean areaBean : arrayList){
                        if(entBaseInfo.getAreaCode().equals(areaBean.getId())){
                            tvTown.setText(areaBean.getName());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(),"获取乡镇信息失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
