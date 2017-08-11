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
public class CheckBodyFragment extends Fragment {

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
    @Bind(R.id.tv_id_no)
    TextView tvIdNo;
    @Bind(R.id.tv_reg_gno)
    TextView tvRegGno;
    @Bind(R.id.tv_check_territory)
    TextView tvCheckTerritory;
    @Bind(R.id.tv_real_addr)
    TextView tvRealAddr;
    @Bind(R.id.tv_real_entity)
    TextView tvRealEntity;
    @Bind(R.id.tv_contact_person)
    TextView tvContactPerson;
    @Bind(R.id.tv_contact_num)
    TextView tvContactNum;
    private AppCompatActivity context;
    private View view;
    private CheckJGJLBean checkJGJLBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_jgjl_body, null);
        context = (AppCompatActivity) getActivity();

        ButterKnife.bind(this, view);

        if (JyqxdqQyxxJgjlActivity.currTab == 0) {
            initData(JyqxdqQyxxJgjlActivity.getCheckJGJLBean());
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
        tvIdNo.setText(etpsCommonInfo.getPriPid());
        tvRegGno.setText(etpsCommonInfo.getRegNo());
        tvCheckTerritory.setText(etpsCommonInfo.getZoneName());
        tvRealAddr.setText(ednEtpsChkColct.getRealAddress());
        tvRealEntity.setText(ednEtpsChkColct.getRealWork());
        tvContactPerson.setText(ednEtpsChkColct.getCntName());
        tvContactNum.setText(ednEtpsChkColct.getCntTelephone());


//         tvCompanyName
//         tvManagerType
//         tvRegisterNo
//         tvLegalPerson
//         tvRegisterAddr
//         tvIdNo
//         tvRegGno
//         tvCheckTerritory
//         tvRealAddr
//         tvRealEntity
//         tvContactPerson
//         tvContactNum

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
