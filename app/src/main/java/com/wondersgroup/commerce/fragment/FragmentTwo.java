package com.wondersgroup.commerce.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.MenuModel;
import com.wondersgroup.commerce.utils.FragmentHelper;

/**
 * Created by kangrenhui on 2016/1/19.
 */
public class FragmentTwo extends RootFragment {
    FragmentManager fm;
    private RootAppcation rootApplication;
    private MenuModel menuModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootApplication = (RootAppcation) getActivity().getApplicationContext();

        for (int i = 0; i < rootApplication.getMenuBtnList().size(); i++) {
            if (rootApplication.getMenuBtnList().get(i).getName().equals("查询统计")) {
                menuModel = rootApplication.getMenuBtnList().get(i);
            }
        }

        View view = View.inflate(getActivity(), R.layout.fragment_homepage, null);

        if(menuModel.getFunctionList().size()==1){
            fm = getFragmentManager();
            TJFXFragment navFragment = new TJFXFragment();
            FragmentHelper.replaceFragmentNoBack(fm, navFragment, "查询统计", null, R.id.layout_chird_fragment);
        }else {
            fm = getFragmentManager();
            FragmentNav navFragment = new FragmentNav();
            navFragment.setFunctionList(menuModel.getFunctionList());
            FragmentHelper.replaceFragmentNoBack(fm, navFragment, "查询统计", null, R.id.layout_chird_fragment);
        }
        return view;
    }
}
