package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jiajiangyi on 2016/4/14.
 */
public class JbxxFragment extends Fragment {

    private String entityId;
    private CompanyJBXXBean jbxxBean;
    private AppCompatActivity act;
    private View view;
    private ListView lv_vestor_info;//投资人基本信息
    private ListView lv_keyperson_info;//主要人员信息

    private TextView tv_register_no;        //注册号
    private TextView tv_company_name;       //企业名称
    private TextView tv_company_type;       //类型
    private TextView tv_company_keyperson;  //法定代表人
    private TextView tv_register_mony;      //注册资本
    private TextView tv_register_date;      //成立日期
    private TextView tv_register_organ;     //登记机关
    private TextView tv_issue_date;         //发照日期
    private TextView tv_operation_start;    //经营期限自
    private TextView tv_operation_end;      //经营期限至
    private TextView tv_address;            //住所
    private TextView tv_operation_type;     //经营范围
    private TextView tv_operation_state;    //经营状态


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        act = (AppCompatActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_xqzg_jbxx, container, false);


        entityId = getActivity().getIntent().getStringExtra("entityId");


        initView();

        initData();


        return view;
    }

    private void initView() {
        lv_vestor_info = (ListView)view.findViewById(R.id.lv_vestor_info);
        lv_keyperson_info = (ListView)view.findViewById(R.id.lv_keyperson_info);

        tv_register_no = (TextView) view.findViewById(R.id.tv_register_no);
        tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
        tv_company_type = (TextView) view.findViewById(R.id.tv_company_type);
        tv_company_keyperson = (TextView) view.findViewById(R.id.tv_company_keyperson);
        tv_register_mony = (TextView) view.findViewById(R.id.tv_register_mony);
        tv_register_date = (TextView) view.findViewById(R.id.tv_register_date);
        tv_register_organ = (TextView) view.findViewById(R.id.tv_register_organ);
        tv_issue_date = (TextView) view.findViewById(R.id.tv_issue_date);
        tv_operation_start = (TextView) view.findViewById(R.id.tv_operation_start);
        tv_operation_end = (TextView) view.findViewById(R.id.tv_operation_end);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_operation_type = (TextView) view.findViewById(R.id.tv_operation_type);
        tv_operation_state = (TextView) view.findViewById(R.id.tv_operation_state);

    }

    private void initData() {

        MyProgressDialog.show(getActivity());

        Map<String, String> map = new HashMap<String, String>();
        map.put("entityId", entityId);

        Call<CompanyJBXXBean> call = ApiManager.shApi.queryCompanyJBXX(map);
        call.enqueue(new Callback<CompanyJBXXBean>() {
            @Override
            public void onResponse(Response<CompanyJBXXBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    jbxxBean = response.body();

//                    Toast.makeText(getActivity(), "hahaha" + jbxxBean.getValues().getUrl(), Toast.LENGTH_SHORT).show();
                    DetailCompanyActivity.setUrl(jbxxBean.getValues().getUrl());

                    //公司基本信息--基本信息
                    setCompanyBaseInfo(jbxxBean);

                    //公司基本信息--投资人信息
                    setCompanyVestorInfo(jbxxBean);

                    //公司基本信息--主要人员信息
                    setCompanyKeyPersonInfo(jbxxBean);

                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                getActivity().finish();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.d("okhttp########",t.getMessage());
            }
        });
    }

    private void setCompanyKeyPersonInfo(CompanyJBXXBean jbxxBean) {

        XqzgdqCompanyKeyPersonAdapter keyPersonAdapter = new XqzgdqCompanyKeyPersonAdapter(jbxxBean, act);
        lv_keyperson_info.setAdapter(keyPersonAdapter);

        lv_keyperson_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(act,"onclick : "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCompanyVestorInfo(CompanyJBXXBean jbxxBean) {
        XqzgdqCompanyVestorAdapter vestorAdapter = new XqzgdqCompanyVestorAdapter(jbxxBean,act);
        lv_vestor_info.setAdapter(vestorAdapter);

        lv_vestor_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(act,"onclick : "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCompanyBaseInfo(CompanyJBXXBean jbxxBean) {

        if (jbxxBean.getValues() != null) {

            CompanyJBXXBean.EtpsInfoEnty etpsInfoEnty = jbxxBean.getValues().getEtpsInfoEnty();
            CompanyJBXXBean.EtpsTradeInfoEnty etpsTradeInfoEnty = jbxxBean.getValues().getEtpsTradeInfoEnty();

            tv_register_no.setText(etpsInfoEnty.getRegNoNation());
            tv_company_name.setText(etpsInfoEnty.getEtpsName());
            tv_company_type.setText(etpsInfoEnty.getEtpsTypeGb());
            tv_company_keyperson.setText(etpsInfoEnty.getLeaderName());
            tv_register_mony.setText(etpsInfoEnty.getPartForm());
            tv_register_date.setText(etpsInfoEnty.getEstablishDate());
            tv_register_organ.setText(etpsInfoEnty.getRegOrganId());
            tv_issue_date.setText(etpsInfoEnty.getCanBranch());
            tv_operation_start.setText(etpsInfoEnty.getTradeStartDate());
            tv_operation_end.setText(etpsInfoEnty.getTradeEndDate());
            tv_address.setText(etpsInfoEnty.getRealAddress());
            tv_operation_type.setText(etpsTradeInfoEnty.getTrdScope());
            tv_operation_state.setText(etpsInfoEnty.getOperateState());

        }
    }

}
