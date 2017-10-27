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
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.PartyInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/4/27.
 */

public class DangzuzhiFragment extends Fragment {
    @BindView(R.id.tv_full_name)
    TextView tvFullName;
    @BindView(R.id.tv_super_name)
    TextView tvSuperName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.layout_all_counts)
    View layoutAllCounts;
    @BindView(R.id.tv_all_counts)
    TextView tvAllCounts;
    @BindView(R.id.layout_brunch_counts)
    View layoutBrunchCounts;
    @BindView(R.id.tv_brunch_counts)
    TextView tvBrunchCounts;
    @BindView(R.id.tv_es_date)
    TextView tvEsDate;
    @BindView(R.id.tv_es_way)
    TextView tvEsWay;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_act_place)
    TextView tvActPlace;
    @BindView(R.id.tv_act_funds)
    TextView tvActFuns;
    @BindView(R.id.tv_secretary_name)
    TextView tvSecretaryName;
    @BindView(R.id.tv_secretary_tel)
    TextView tvSecretaryTel;
    @BindView(R.id.tv_secretary_dept)
    TextView tvSecretaryDept;
    @BindView(R.id.tv_town)
    TextView tvTown;

    private String entId;
    private TotalLoginBean loginBean;
    private PartyInfo partyInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangzuzhi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        entId = getArguments().getString(Constants.ENT_ID);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        partyInfo = getArguments().getParcelable(Constants.PARTY_INFO);
        if (partyInfo == null)
            partyInfo = new PartyInfo();
        initData();
        if (!TextUtils.isEmpty(partyInfo.getAreaCode())) {
            List<AreaBean> list = Hawk.get(Constants.PARTY_AREA_LIST);
            if (list == null)
                getAreaCode();
            else
                setArea(list);
        }
    }

    private void initData() {

        DicBean dicBean = Hawk.get(Constants.DIC);
        if (dicBean != null) {
            if (!TextUtils.isEmpty(partyInfo.getPartyOgLevel()))
                tvLevel.setText(dicBean.getPartyLevel().get(partyInfo.getPartyOgLevel()).toString());
        }

        tvFullName.setText(partyInfo.getFullName());
        tvSuperName.setText(partyInfo.getSuperName());
        if ("2".equals(partyInfo.getPartyOgLevel())) {
            layoutAllCounts.setVisibility(View.GONE);
        } else if ("3".equals(partyInfo.getPartyOgLevel())) {
            layoutAllCounts.setVisibility(View.GONE);
            layoutBrunchCounts.setVisibility(View.GONE);
        }
        tvAllCounts.setText(partyInfo.getGeneralPartyBranchNum());
        tvBrunchCounts.setText(partyInfo.getPartyBranchNum());
        tvEsDate.setText(partyInfo.getEsDate());
        tvEsWay.setText(dicBean.getMapEsWay().get(partyInfo.getBuildWay()).toString());
        tvTel.setText(partyInfo.getTel());
        tvAddress.setText(partyInfo.getAddress());
        tvActPlace.setText("1".equals(partyInfo.getHasActivityPlace()) ? "有" : "无");
        tvActFuns.setText("1".equals(partyInfo.getHasActivityFunds()) ? "有" : "无");
        tvSecretaryName.setText(partyInfo.getSecretaryName());
        tvSecretaryTel.setText(partyInfo.getSecretaryMobile());
        tvSecretaryDept.setText(partyInfo.getSecretaryJobStation());
    }

    public void getAreaCode() {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, loginBean.getResult().getUserId());
        hashMap.put(Constants.DEPT_ID, loginBean.getResult().getDeptId());
        hashMap.put(Constants.ORGAN_ID, loginBean.getResult().getOrganId());
        Call<Result<List<AreaBean>>> call = ApiManager.fgdjApi.queryPartyArea(hashMap);
        call.enqueue(new Callback<Result<List<AreaBean>>>() {
            @Override
            public void onResponse(Response<Result<List<AreaBean>>> response, Retrofit retrofit) {
                List<AreaBean> list = response.body().getObject();
                if (list != null && list.size() != 0) {
                    setArea(list);
                } else {
                    Toast.makeText(getActivity(), "获取区县信息失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "获取区县信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setArea(List<AreaBean> list) {
        for (AreaBean areaBean : list) {
            if (partyInfo.getAreaCode().equals(areaBean.getId())) {
                tvTown.setText(areaBean.getName());
                break;
            }
        }
    }
}
