package com.wondersgroup.yngs.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.activity.TJActivity;
import com.wondersgroup.yngs.service.ApiManager;
import com.wondersgroup.yngs.widget.CusDatePickerDialog;
import com.wondersgroup.yngs.widget.DeptSelectDialog;
import com.wondersgroup.yngs.widget.InfoSelectBar;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QutjFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QutjFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.qutj_office)InfoSelectBar office;
    @Bind(R.id.qutj_date_start)Button btnStart;
    @Bind(R.id.qutj_date_end)Button btnEnd;
    @Bind(R.id.tj_btn_submit)Button btnSub;
    @Bind(R.id.tj_btn_clear)Button btnClear;

    private int selectId=0;
    private CusDatePickerDialog starDate;
    private CusDatePickerDialog endDate;
    private String startString=null;
    private String endString=null;
    private DeptSelectDialog dialog;

    public QutjFragment() {
        // Required empty public constructor
    }

    public static QutjFragment newInstance() {
        QutjFragment fragment = new QutjFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_qutj, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        office.setIcon(R.drawable.app_jiguan);
        office.setText("请选择承办机关");
        starDate=CusDatePickerDialog.newInstance("请选择开始日期");
        starDate.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
            @Override
            public void OnDateSet(String dateString) {
                btnStart.setText(dateString);
                startString=dateString;
                endDate.setMinDate(startString);
            }
        });
        endDate=CusDatePickerDialog.newInstance("请选择结束日期");
        endDate.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
            @Override
            public void OnDateSet(String dateString) {
                btnEnd.setText(dateString);
                endString=dateString;
                starDate.setMaxDate(dateString);
            }
        });
        dialog=DeptSelectDialog.newInstance("请选择机关", 0);
        dialog.setListener(new DeptSelectDialog.OnItemSelect() {
            @Override
            public void onItemSelect(int id, String name) {
                office.setText(name);
                selectId=id;
            }
        });
        btnSub.setOnClickListener(this);
        office.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnSub)){
            HashMap<String,String> body=new HashMap<>();
            if(selectId==0){
                body.put("organId", (String) Hawk.get("organId"));
            }else {
                body.put("organId", "" + selectId);
            }
            body.put("wsCodeReq", ApiManager.getWsCodeReq());
            body.put("startDate", btnStart.getText().toString());
            body.put("endDate", btnEnd.getText().toString());
            Intent intent=new Intent(getContext(), TJActivity.class);
            intent.putExtra("type","SZQY");
            intent.putExtra("body",body);
            startActivity(intent);
        }else if (v.equals(office)){
            dialog.show(getChildFragmentManager(),"QUTJDEPTSELECT");
        }else if(v.equals(btnStart)){
            starDate.show(getFragmentManager(),"QUTJSTART");
        }else if(v.equals(btnEnd)){
            endDate.show(getFragmentManager(),"QUTJEND");
        }else if(v.equals(btnClear)){
            office.setText("请选择承办机关");
            starDate.resetPicker();
            endDate.resetPicker();
            btnStart.setText("");
            btnEnd.setText("");
            startString="";
            endString="";
            selectId=0;
        }
    }
}
