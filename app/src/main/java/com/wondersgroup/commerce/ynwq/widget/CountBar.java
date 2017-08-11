package com.wondersgroup.commerce.ynwq.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


/**
 * Created by 薛定猫 on 2016/2/24.
 */
public class CountBar extends DialogFragment {
    int total=0;
    int cur=0;
    TextView curText;
    TextView totalText;
    LinearLayout countbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
        if(getArguments()!=null){
            total=getArguments().getInt("total",0);
        }
    }

    public static CountBar newInstance(int total){
        CountBar bar=new CountBar();
        Bundle args=new Bundle();
        args.putInt("total", total);
        bar.setArguments(args);
        return bar;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.view_countbar,null);
        curText=(TextView)view.findViewById(R.id.curNum);
        totalText=(TextView)view.findViewById(R.id.totalNum);
        countbar=(LinearLayout)view.findViewById(R.id.countbar);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //countbar.setVisibility(View.INVISIBLE);
        if(cur!=0)curText.setText("第 "+cur+" 条");
        if(total!=0)totalText.setText("共 " + total + " 条");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.verticalMargin=0.03f;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);


        Window window = dialog.getWindow();
        // Make the dialog possible to be outside touch
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        return dialog;
    }

    public void setCur(int cur){
        this.cur=cur;
        if(getView()!=null){
            curText.setText("第 "+cur+" 条");
        }
        //if(countbar.getVisibility()==View.INVISIBLE)countbar.setVisibility(View.VISIBLE);
    }

    public void setTotal(int total){
        this.total=total;
        if (getView()!=null) {
            totalText.setText("共 " + total + " 条");
        }
        //if(countbar.getVisibility()==View.INVISIBLE)countbar.setVisibility(View.VISIBLE);
    }
}
