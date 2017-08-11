package com.wondersgroup.commerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.FunctionModel;
import com.wondersgroup.commerce.model.MenuModel;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.memorandum.MemoActivity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bjy on 2017/3/31.
 * 辅助功能
 */

public class FragmentAssit extends Fragment implements View.OnClickListener{
    private LinearLayout layout0;
    private LayoutInflater layoutInflater;

    private RootAppcation appcation;
    private ArrayList<FunctionModel> functionList;
    private Map<String, Integer> meduleMap;
    private MenuModel menuModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_assit,container,false);
        initData();
        initView(view);

        return view;
    }

    private void initData(){
        appcation = (RootAppcation) getActivity().getApplicationContext();
        meduleMap = appcation.getMeduleMap();
        for (int i = 0; i < appcation.getMenuBtnList().size(); i++) {
            if (appcation.getMenuBtnList().get(i).getName().equals("辅助功能")) {
                menuModel = appcation.getMenuBtnList().get(i);
                functionList = menuModel.getFunctionList();
                break;
            }
        }
    }

    private void initView(View view){
        layout0 = (LinearLayout) view.findViewById(R.id.layout0);
        for (FunctionModel functionModel : functionList){
            View itemView = View.inflate(getActivity(),R.layout.item_assit,null);
            itemView.setOnClickListener(this);
            TextView textView = (TextView) itemView.findViewById(R.id.tv);
            itemView.setTag(functionModel.getName());
            textView.setText(functionModel.getName());
            ImageView imageView = (ImageView) itemView.findViewById(R.id.img);
            if (meduleMap.get(functionModel.getName())!=null)
                imageView.setBackgroundResource(meduleMap.get(functionModel.getName()));
            layout0.addView(itemView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()){
            default:
                break;

            //备忘录
            case Constants.bwl:
                Intent intentBwl = new Intent(getActivity(), MemoActivity.class);
                getActivity().startActivity(intentBwl);
                break;

            //通讯录
            case Constants.TXL:
                Intent intentTxl = new Intent(getActivity(), TXLActivity.class);
                startActivity(intentTxl);
                break;
        }
    }
}
