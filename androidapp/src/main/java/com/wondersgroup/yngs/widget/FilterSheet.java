package com.wondersgroup.yngs.widget;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.DicItem;

import java.util.ArrayList;

/**
 * Created by 薛定猫 on 2015/12/16.
 */
public class FilterSheet extends DialogFragment implements View.OnClickListener{
    private TabLayout tabs;
    private TimeSqure startDate;
    private TimeSqure endDate;
    private Button ok;
    private Button clear;
    private CusDatePickerDialog startDialog;
    private CusDatePickerDialog endDialog;

    private static final String ARG_YP="y_position";
    private static final String ARG_DIC="arg_dic";
    private static final String ARG_SELECT="arg_selection";
    private static final String ARG_SDATE="arg_startDate";
    private static final String ARG_EDATE="arg_endDate";


    private int yPosition;
    private OnFilterListener listener;
    private String startString="";
    private String endString="";
    private String typeString;
    private ArrayList<DicItem> shenqingType;
    private int tabSelection;

    public interface OnFilterListener{
        void OnFiltered(String type,String startDate,String endDate,int selection);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
        if(getArguments()!=null){
            yPosition=getArguments().getInt(ARG_YP,144);
            shenqingType=getArguments().getParcelableArrayList(ARG_DIC);
            tabSelection=getArguments().getInt(ARG_SELECT, 0);
            startString=getArguments().getString(ARG_SDATE);
            endString=getArguments().getString(ARG_EDATE);
        }
    }

    public static FilterSheet newInstance(int yPosition,ArrayList<DicItem> typeDic,String startDate,String endDate,int select){
        FilterSheet sheet=new FilterSheet();
        Bundle args=new Bundle();
        args.putInt(ARG_YP, yPosition);
        args.putParcelableArrayList(ARG_DIC, typeDic);
        args.putInt(ARG_SELECT, select);
        args.putString(ARG_SDATE, startDate);
        args.putString(ARG_EDATE,endDate);
        sheet.setArguments(args);
        return sheet;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.view_filter_sheet,null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabs= (TabLayout) view.findViewById(R.id.sheet_tabs);
        startDate=(TimeSqure)view.findViewById(R.id.sheet_startDate);
        endDate=(TimeSqure)view.findViewById(R.id.sheet_endDate);
        ok= (Button) view.findViewById(R.id.sheet_ok);
        clear=(Button)view.findViewById(R.id.sheet_clear);

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        ok.setOnClickListener(this);
        clear.setOnClickListener(this);

        startDialog=CusDatePickerDialog.newInstance(getString(R.string.shenqing_start_time));
        startDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
            @Override
            public void OnDateSet(String dateString) {
                startDate.setText(dateString);
                endDialog.setMinDate(dateString);
                startString=dateString;
            }
        });
        endDialog=CusDatePickerDialog.newInstance(getString(R.string.shenqing_end_time));
        endDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
            @Override
            public void OnDateSet(String dateString) {
                endDate.setText(dateString);
                startDialog.setMaxDate(dateString);
                endString = dateString;
            }
        });

        startDialog.setInitDate(startString);
        endDialog.setInitDate(endString);

        tabs.addTab(tabs.newTab().setText("全部类型"));
        addTab();
        startDate.setText(startString);
        endDate.setText(endString);
        /*shenqingType=new ArrayList<>();
        DicT dic= Hawk.get("Dic");
        if(dic==null){
            SharedPreferences sp=getContext().getSharedPreferences("Default", Context.MODE_PRIVATE);
            Map<String,String> body =new HashMap<>();
            int selection=sp.getInt("selection",0);
            body.put("deptId",sp.getString("deptId","").split(",")[selection]);
            body.put("organId",sp.getString("organId","").split(",")[selection]);
            Call<DicResult> call = ApiManager.yunNanApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        DicResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            Hawk.put("Dic",result.getResult());
                            addTab(result.getResult());
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else {
            addTab(dic);
        }*/
        for(int i=0;i<tabs.getTabCount();i++){
            tabs.getTabAt(i).setCustomView(R.layout.view_tab_text);
        }
        /*TextView firstTab = (TextView)tabs.getTabAt(0).getCustomView();
        firstTab.setSelected(true);*/
        if(tabSelection==0){
            tabs.getTabAt(tabSelection).getCustomView().setSelected(true);
        }else {
            tabs.getTabAt(tabSelection).select();
        }
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //typeString=tab.getText().toString();
                if(tab.getPosition()>0)typeString=shenqingType.get(tab.getPosition()-1).getName();
                tabSelection=tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP|Gravity.LEFT;
        params.y=yPosition;
        params.dimAmount=0.3f;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public void setOnFilterListener(OnFilterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(v==startDate){
            startDialog.show(getFragmentManager(),"startDate");
        }else if(v==endDate){
            endDialog.show(getFragmentManager(),"endDate");
        }else if(v==ok){
            if(listener!=null){
                listener.OnFiltered(typeString,startString,endString,tabSelection);
            }
            dismiss();
        }else if(v==clear){
            tabs.getTabAt(0).select();
            startDate.setText("");
            endDate.setText("");
            startDialog.resetPicker();
            endDialog.resetPicker();
            startString="";
            endString="";
            tabSelection=0;
            tabs.getTabAt(tabSelection).getCustomView().setSelected(true);
        }
    }
    public void addTab(){
        for (DicItem item :
                shenqingType) {
            tabs.addTab(tabs.newTab().setText(item.getValue()));
        }
    }
}
