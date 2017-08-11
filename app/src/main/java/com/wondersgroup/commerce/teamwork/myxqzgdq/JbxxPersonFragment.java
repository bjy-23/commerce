package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class JbxxPersonFragment extends Fragment {

    private String entityId;
    private PersonJBXXBean jbxxBean;
    private AppCompatActivity act;
    private View view;

    private TextView tv_register_no;        //注册号
    private TextView tv_company_name;       //企业名称
    private TextView tv_operator;           //经营者
    private TextView tv_operator_num;       //人数
    private TextView tv_register_date;      //成立日期
    private TextView tv_address;            //地址
    private TextView tv_operation_start;    //经营期限自
    private TextView tv_operation_end;      //经营期限至
    private TextView tv_operation_type;     //经营范围
    private TextView tv_mony;               //资金金额


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        act = (AppCompatActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_person_jbxx, container, false);


        entityId = getActivity().getIntent().getStringExtra("entityId");

//        Toast.makeText(getActivity(),"entityId="+entityId,Toast.LENGTH_SHORT).show();

        initView();

        initData();


        return view;
    }

    private void initView() {

        tv_register_no = (TextView) view.findViewById(R.id.tv_register_no);
        tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
        tv_operator = (TextView) view.findViewById(R.id.tv_operator);
        tv_operator_num = (TextView) view.findViewById(R.id.tv_operator_num);
        tv_register_date = (TextView) view.findViewById(R.id.tv_register_date);
        tv_operation_start = (TextView) view.findViewById(R.id.tv_operation_start);
        tv_operation_end = (TextView) view.findViewById(R.id.tv_operation_end);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_operation_type = (TextView) view.findViewById(R.id.tv_operation_type);
        tv_mony = (TextView) view.findViewById(R.id.tv_mony);

    }

    private void initData() {

        MyProgressDialog.show(getActivity());

        Map<String, String> map = new HashMap<String, String>();
        map.put("entityId", entityId);

        Call<PersonJBXXBean> call = ApiManager.shApi.queryPersonJBXX(map);
        call.enqueue(new Callback<PersonJBXXBean>() {
            @Override
            public void onResponse(Response<PersonJBXXBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    jbxxBean = response.body();

                    Toast.makeText(getActivity(),jbxxBean.getMsg(), Toast.LENGTH_SHORT).show();
                    DetailPersonActivity.setUrl(jbxxBean.getValues().getUrl());

                    //公司基本信息--基本信息
                    setCompanyBaseInfo(jbxxBean);


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
            }
        });
    }



    private void setCompanyBaseInfo(PersonJBXXBean jbxxBean) {
        PersonJBXXBean.PeInfoEnty peInfoEnty = jbxxBean.getValues().getPeInfoEnty();
//        PersonJBXXBean.PeTrdInfoEnty peTrdInfoEnty = jbxxBean.getValues().getPeTrdInfoEnty();

        tv_register_no.setText(peInfoEnty.getRegNo());
        tv_company_name.setText(peInfoEnty.getName());
        tv_operator.setText(peInfoEnty.getTrdTypeId());
        tv_operator_num.setText(""+peInfoEnty.getTotalPersn());
        tv_register_date.setText(peInfoEnty.getEstablishDate());
        tv_address.setText(peInfoEnty.getAddress());
        tv_operation_start.setText(peInfoEnty.getTradeStartDate());
        tv_operation_end.setText(peInfoEnty.getTradeEndDate());
        tv_operation_type.setText(peInfoEnty.getTrdScope());
        tv_mony.setText(peInfoEnty.getCptlTotal()+"元");

    }



}
