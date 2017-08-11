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
public class FragmentFive extends RootFragment {
    FragmentManager fm;
    private RootAppcation rootApplication;
    private MenuModel menuModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootApplication = (RootAppcation) getActivity().getApplicationContext();

        for (int i = 0; i < rootApplication.getMenuBtnList().size(); i++) {
            if (rootApplication.getMenuBtnList().get(i).getName().equals("业务办理")) {
                menuModel = rootApplication.getMenuBtnList().get(i);
            }
        }

        View view = View.inflate(getActivity(), R.layout.fragment_homepage, null);

        fm = getFragmentManager();
        FragmentNav navFragment = new FragmentNav();
        navFragment.setFunctionList(menuModel.getFunctionList());
        FragmentHelper.replaceFragmentNoBack(fm, navFragment, "业务办理", null, R.id.layout_chird_fragment);

        return view;
    }
}
