package com.wondersgroup.commerce.ynwq.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.ListDialog;
import com.wondersgroup.commerce.ynwq.activity.QueryActivity;
import com.wondersgroup.commerce.ynwq.widget.InfoEditBar;
import com.wondersgroup.commerce.ynwq.widget.InfoSelectBar;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JinduQueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JinduQueryFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.jindu_input)InfoEditBar input;
    @Bind(R.id.jdcx_date_start)Button dateStart;
    @Bind(R.id.jdcx_date_end)Button dateEnd;
    @Bind(R.id.jindu_type)InfoSelectBar typeS;
    @Bind(R.id.jindu_status)InfoSelectBar statusS;
    @Bind(R.id.jindu_clear)Button clear;
    @Bind(R.id.jindu_search)Button search;

    private CusDatePickerDialog startDate;
    private CusDatePickerDialog endDate;
    private HashMap<String,String> body;

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    public JinduQueryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment JinduQueryFragment.
     */
    public static JinduQueryFragment newInstance(String param1) {
        JinduQueryFragment fragment = new JinduQueryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jindu_query, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        input.setIcon(R.drawable.app_input);
        input.setHint(getString(R.string.entName_input_hint));
        typeS.setIcon(R.drawable.app_type_select);
        typeS.setText(getString(R.string.type_select_hint));
        statusS.setIcon(R.drawable.app_state_select);
        statusS.setText(getString(R.string.status_select_hint));

        startDate =CusDatePickerDialog.newInstance(getString(R.string.date_select_hint));
        startDate.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
            @Override
            public void OnDateSet(String dateString) {
                dateStart.setText(dateString);
                body.put("appDateStart", dateString);
                endDate.setMinDate(dateString);
            }
        });

        endDate =CusDatePickerDialog.newInstance("请选择申请日期");
        endDate.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
            @Override
            public void OnDateSet(String dateString) {
                dateEnd.setText(dateString);
                body.put("appDateEnd",dateString);
                startDate.setMaxDate(dateString);
            }
        });
        body = new HashMap<>();

        dateStart.setOnClickListener(this);
        dateEnd.setOnClickListener(this);
        typeS.setOnClickListener(this);
        statusS.setOnClickListener(this);
        search.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100&&resultCode==getActivity().RESULT_OK){
            String result=data.getStringExtra("SQLX");
            String typeCode=data.getStringExtra("typeCode");
            typeS.setText(result);
            body.put("appType",typeCode);
        }else if(requestCode==200&&resultCode==getActivity().RESULT_OK){
            String result=data.getStringExtra("SQZT");
            String typeCode=data.getStringExtra("typeCode");
            statusS.setText(result);
            body.put("flowStatus",typeCode);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(dateStart)){
            startDate.show(getFragmentManager(), "JinDuDateStart");
        }else if(v.equals(dateEnd)){
            endDate.show(getFragmentManager(),"JinDuDateEnd");
        }else if(v.equals(typeS)){/*
            Intent intent=new Intent(getContext(), ListActivity.class);
            intent.putExtra("type", "SQLX");
            startActivityForResult(intent, 100);*/
            ListDialog dialog=ListDialog.newInsance("请选择申请类型","SQLX");
            dialog.setListener(new ListDialog.OnSelected() {
                @Override
                public void onSelected(String type, String typeCode) {
                    typeS.setText(type);
                    body.put("appType",typeCode);
                }
            });
            dialog.show(getChildFragmentManager(),"JinDuSQLX");
        }else if(v.equals(statusS)){/*
            Intent intent=new Intent(getContext(), ListActivity.class);
            intent.putExtra("type", "SQZT");
            startActivityForResult(intent, 200);*/
            ListDialog dialog=ListDialog.newInsance("请选择申请状态","SQZT");
            dialog.setListener(new ListDialog.OnSelected() {
                @Override
                public void onSelected(String type, String typeCode) {
                    statusS.setText(type);
                    body.put("flowStatus",typeCode);
                }
            });
            dialog.show(getChildFragmentManager(),"JinDuSQZT");
        }else if (v.equals(search)){
            body.put("etpsNameOrRegNo", input.getText());
            Intent intent=new Intent(getActivity(),QueryActivity.class);
            intent.putExtra("type","JDCX");
            intent.putExtra("body",body);
            startActivity(intent);
        }else if(v.equals(clear)){
            body=new HashMap<>();
            input.setHint(getString(R.string.entName_input_hint));
            input.setText("");
            typeS.setText(getString(R.string.type_select_hint));
            statusS.setText(getString(R.string.status_select_hint));
            startDate.resetPicker();
            endDate.resetPicker();
            dateStart.setText("");
            dateEnd.setText("");
        }
    }
}
