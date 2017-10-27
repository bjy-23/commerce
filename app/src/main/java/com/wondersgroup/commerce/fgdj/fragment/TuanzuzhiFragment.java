package com.wondersgroup.commerce.fgdj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.LeagueInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/4/27.
 */

public class TuanzuzhiFragment extends Fragment {
    @BindView(R.id.tv_full_name)
    TextView tvFullName;
    @BindView(R.id.tv_short_name)
    TextView tvShortName;
    @BindView(R.id.tv_super_name)
    TextView tvSuperName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_address)
    TextView tvAddres;
    @BindView(R.id.tv_es_date)
    TextView tvEsDate;
    @BindView(R.id.tv_es_way)
    TextView tvEsWay;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_over_num)
    TextView tvOverNum;
    @BindView(R.id.tv_aff_num)
    TextView tvAffNum;
    @BindView(R.id.tv_ft_aff_num)
    TextView tvFtAffNum;
    @BindView(R.id.tv_secretary_name)
    TextView tvSecretaryName;
    @BindView(R.id.tv_secretary_tel)
    TextView tvSecretaryTel;
    @BindView(R.id.tv_instructor_name)
    TextView tvInstructorName;
    @BindView(R.id.tv_instructor_tel)
    TextView tvInstructorTel;
    @BindView(R.id.tv_instructor_from)
    TextView tvInstructorFrom;
    @BindView(R.id.tv_budget_inv)
    TextView tvBudgetInv;
    @BindView(R.id.tv_dues_inv)
    TextView tvDuesInv;
    @BindView(R.id.tv_ent_inv)
    TextView tvEntInv;
    @BindView(R.id.tv_other_inv)
    TextView tvOtherInv;

    private String entId;
    private TotalLoginBean loginBean;
    private LeagueInfo leagueInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuanzuzhi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        entId = getArguments().getString(Constants.ENT_ID);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        leagueInfo = getArguments().getParcelable(Constants.LEAGUE_INFO);
        if (leagueInfo == null)
            leagueInfo = new LeagueInfo();
        initData();
    }

    private void initData() {
        DicBean dicBean = Hawk.get(Constants.DIC);
        if (dicBean != null){
            if (!TextUtils.isEmpty(leagueInfo.getLeagueLevel()))
                tvLevel.setText(dicBean.getLeagueLevel().get(leagueInfo.getLeagueLevel()).toString());
            if (!TextUtils.isEmpty(leagueInfo.getBuildWay()))
                tvEsWay.setText(dicBean.getLeagueBuildWay().get(leagueInfo.getBuildWay()).toString());
        }
        tvFullName.setText(leagueInfo.getFullName());
        tvShortName.setText(leagueInfo.getName());
        tvSuperName.setText(leagueInfo.getSuperName());
        tvAddres.setText(leagueInfo.getAddress());
        tvEsDate.setText(leagueInfo.getEsDate());
        tvTel.setText(leagueInfo.getTel());
        tvOverNum.setText(leagueInfo.getOverEntNum());
        tvAffNum.setText(leagueInfo.getLeagueAffNum());
        tvFtAffNum.setText(leagueInfo.getFtLeagueAffNum());
        tvSecretaryName.setText(leagueInfo.getSecretaryName());
        tvSecretaryTel.setText(leagueInfo.getSecretaryMobile());
        tvInstructorName.setText(leagueInfo.getInstructorName());
        tvInstructorTel.setText(leagueInfo.getInstructorMobile());
        tvInstructorFrom.setText(leagueInfo.getInstructorFrom());
        tvBudgetInv.setText(leagueInfo.getFinanceBudgetInv());
        tvDuesInv.setText(leagueInfo.getMemberDuesInv());
        tvEntInv.setText(leagueInfo.getEntInv());
        tvOtherInv.setText(leagueInfo.getOthInv());
    }
}
