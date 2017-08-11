package com.wondersgroup.commerce.teamwork.wywork.jyqx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.myxqzgdq.CheckJGJLBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JyqxdqQyxxJgjlActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/21 0021.
 */
public class CheckConditionFragment extends Fragment {
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;
    @Bind(R.id.tv_manager_type)
    TextView tvManagerType;
    @Bind(R.id.tv_register_no)
    TextView tvRegisterNo;
    @Bind(R.id.tv_legal_person)
    TextView tvLegalPerson;
    @Bind(R.id.tv_register_addr)
    TextView tvRegisterAddr;
    @Bind(R.id.tv_contact_person)
    TextView tvContactPerson;
    @Bind(R.id.tv_contact_num)
    TextView tvContactNum;
    @Bind(R.id.tv_check_goal)
    TextView tvCheckGoal;
    @Bind(R.id.tv_check_result)
    TextView tvCheckResult;
    @Bind(R.id.tv_check_find)
    TextView tvCheckFind;
    @Bind(R.id.tv_check_existed)
    TextView tvCheckExisted;
    @Bind(R.id.tv_treatment)
    TextView tvTreatment;
    @Bind(R.id.tv_check_organ)
    TextView tvCheckOrgan;
    @Bind(R.id.tv_check_person)
    TextView tvCheckPerson;
    @Bind(R.id.tv_note)
    TextView tvNote;
    private AppCompatActivity context;
    private View view;
    private CheckJGJLBean checkJGJLBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_jgjl_condition, null);
        context = (AppCompatActivity) getActivity();
        checkJGJLBean = JyqxdqQyxxJgjlActivity.getCheckJGJLBean();


        ButterKnife.bind(this, view);

        if (JyqxdqQyxxJgjlActivity.currTab == 0) {
            initData(checkJGJLBean);
        }

        return view;
    }

    private void initData(CheckJGJLBean checkJGJLBean) {
        CheckJGJLBean.EtpsCommonInfo etpsCommonInfo = checkJGJLBean.getValues().getEtpsCommonInfo().get(0);
        CheckJGJLBean.EdnEtpsChkColct ednEtpsChkColct = checkJGJLBean.getValues().getEdnEtpsChkColct().get(0);

        tvCompanyName.setText(etpsCommonInfo.getEtpsName());
        tvManagerType.setText(etpsCommonInfo.getManageLevelName());
        tvRegisterNo.setText(etpsCommonInfo.getRegNoNation());
        tvLegalPerson.setText(etpsCommonInfo.getLeaderName());
        tvRegisterAddr.setText(etpsCommonInfo.getAddress());
        tvContactPerson.setText(ednEtpsChkColct.getCntName());
        tvContactNum.setText(ednEtpsChkColct.getCntTelephone());
        tvCheckGoal.setText(checkJGJLBean.getValues().getEtpsChkType());
        tvCheckResult.setText(checkJGJLBean.getValues().getCheckResultGb());
        tvCheckFind.setText(checkJGJLBean.getValues().getChkProblem());
        tvCheckExisted.setText(checkJGJLBean.getValues().getEdnProblem());
        tvTreatment.setText(checkJGJLBean.getValues().getDealSuggestio());
        tvCheckOrgan.setText(etpsCommonInfo.getAreaOrganName());
        tvCheckPerson.setText(checkJGJLBean.getValues().getPersonString());
        tvNote.setText(checkJGJLBean.getValues().getMemo());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
