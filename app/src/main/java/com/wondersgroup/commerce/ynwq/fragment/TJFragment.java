package com.wondersgroup.commerce.ynwq.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.ListDialog;
import com.wondersgroup.commerce.ynwq.activity.TJActivity;
import com.wondersgroup.commerce.ynwq.widget.DeptSelectDialog;
import com.wondersgroup.commerce.ynwq.widget.InfoSelectBar;


import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TJFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TJFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.tj_office)InfoSelectBar office;
    @BindView(R.id.tj_dept)InfoSelectBar dept;
    @BindView(R.id.tj_date_start)Button btnStart;
    @BindView(R.id.tj_date_end)Button btnEnd;
    @BindView(R.id.tj_btn_submit)Button btnSub;
    @BindView(R.id.tj_btn_clear)Button btnClear;
    @BindView(R.id.tj_check)AppCompatCheckBox check;

    private static final String ARG_TYPE = "type";

    private String type;

    private int organId =0;
    private String deptId = "0";
    private CusDatePickerDialog starDate;
    private CusDatePickerDialog endDate;
    private String startString=null;
    private String endString=null;
    private DeptSelectDialog organDialog;
    private ListDialog deptDialog;

    public TJFragment() {
        // Required empty public constructor
    }

    public static TJFragment newInstance(String param1) {
        TJFragment fragment = new TJFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tj, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        office.setIcon(R.drawable.app_jiguan);
        office.setText("请选择承办机关");
        dept.setIcon(R.drawable.app_bumen);
        dept.setText("请选择承办部门");
        if("JZQK".equals(type)){
            check.setVisibility(View.INVISIBLE);
            dept.setVisibility(View.GONE);
        }
        starDate= CusDatePickerDialog.newInstance("请选择开始日期");
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
                starDate.setMaxDate(endString);
            }
        });
        organDialog = DeptSelectDialog.newInstance("请选择机关", 0);
        organDialog.setListener(new DeptSelectDialog.OnItemSelect() {
            @Override
            public void onItemSelect(int id, String name) {
                office.setText(name);
                organId = id;
            }
        });
        deptDialog=ListDialog.newInsance("请选择部门","BMXZ");
        deptDialog.setListener(new ListDialog.OnSelected() {
            @Override
            public void onSelected(String type, String typeCode) {
                dept.setText(type);
                deptId=typeCode;
            }
        });
        btnSub.setOnClickListener(this);
        office.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        dept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnSub)){
            HashMap<String,String> body=new HashMap<>();
            if(organId==0){
                body.put("organId", (String) Hawk.get("organId"));
            }else {
                body.put("organId", "" + organId);
            }
            body.put("wsCodeReq", "01100001");
            body.put("startDate", btnStart.getText().toString());
            body.put("endDate", btnEnd.getText().toString());
            if(check.isChecked())body.put("isSelf","1");
            if(!"0".equals(deptId))body.put("deptId",""+deptId);
            Intent intent=new Intent(getContext(), TJActivity.class);
            intent.putExtra("type",type);
            intent.putExtra("body",body);
            startActivity(intent);
        }else if (v.equals(office)){
            organDialog.show(getChildFragmentManager(), "TJDEPTSELECT");
        }else if(v.equals(btnStart)){
            starDate.show(getFragmentManager(),"TJSTART");
        }else if(v.equals(btnEnd)){
            endDate.show(getFragmentManager(),"TJEND");
        }else if(v.equals(btnClear)){
            office.setText("请选择承办机关");
            dept.setText("请选择承办部门");
            starDate.resetPicker();
            endDate.resetPicker();
            btnStart.setText("");
            btnEnd.setText("");
            startString="";
            endString="";
            organId =0;
        }else if(v.equals(dept)){
            deptDialog.show(getChildFragmentManager(),"TJBMXZ");
            /*Map<String,String> childBody=new HashMap<>();
            childBody.put("organId",""+organId);
            Call<ChildOrganResult> call=ApiManager.yunNanApi.getChildOrgan(childBody);
            call.enqueue(new Callback<ChildOrganResult>() {
                @Override
                public void onResponse(Response<ChildOrganResult> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        ChildOrganResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            Hawk.put("BMXZDic",result.getResult());
                            deptDialog.show(getChildFragmentManager(),"TJBMXZ");
                        }else {
                            Toast.makeText(getContext(),"部门获取失败",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(),"部门获取失败",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getContext(),"部门获取失败",Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}
