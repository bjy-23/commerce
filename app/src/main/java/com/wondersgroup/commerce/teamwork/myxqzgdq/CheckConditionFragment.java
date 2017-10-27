package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/21 0021.
 */
public class CheckConditionFragment extends Fragment {
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_manager_type)
    TextView tvManagerType;
    @BindView(R.id.tv_register_no)
    TextView tvRegisterNo;
    @BindView(R.id.tv_legal_person)
    TextView tvLegalPerson;
    @BindView(R.id.tv_register_addr)
    TextView tvRegisterAddr;
    @BindView(R.id.tv_contact_person)
    TextView tvContactPerson;
    @BindView(R.id.tv_contact_num)
    TextView tvContactNum;
    @BindView(R.id.tv_check_goal)
    TextView tvCheckGoal;
    @BindView(R.id.tv_check_result)
    TextView tvCheckResult;
    @BindView(R.id.tv_check_find)
    TextView tvCheckFind;
    @BindView(R.id.tv_check_existed)
    TextView tvCheckExisted;
    @BindView(R.id.tv_treatment)
    TextView tvTreatment;
    @BindView(R.id.tv_check_organ)
    TextView tvCheckOrgan;
    @BindView(R.id.tv_check_person)
    TextView tvCheckPerson;
    @BindView(R.id.tv_note)
    TextView tvNote;
    private AppCompatActivity context;
    private View view;
    private CheckJGJLBean checkJGJLBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_jgjl_condition, null);
        context = (AppCompatActivity) getActivity();
        checkJGJLBean = DetailJGJLActivity.getCheckJGJLBean();


        ButterKnife.bind(this, view);

        if (DetailJGJLActivity.currTab == 0) {
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
    }
}
