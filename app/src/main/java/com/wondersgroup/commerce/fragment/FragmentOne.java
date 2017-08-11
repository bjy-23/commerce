package com.wondersgroup.commerce.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.MenuModel;
import com.wondersgroup.commerce.utils.FragmentHelper;

/**
 * Created by kangrenhui on 2016/1/19.
 */
public class FragmentOne extends RootFragment {
    FragmentManager fm;
    private RootAppcation rootApplication;
    private MenuModel menuModel;

    private TextView titleText;
    private Button messageBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootApplication = (RootAppcation) getActivity().getApplicationContext();

        for (int i = 0; i < rootApplication.getMenuBtnList().size(); i++) {
            if (rootApplication.getMenuBtnList().get(i).getName().equals("首页")) {
                menuModel = rootApplication.getMenuBtnList().get(i);
            }
        }

        View view = View.inflate(getActivity(), R.layout.fragment_homepage, null);

        fm = getFragmentManager();
//        FragmentHome homeFragment = new FragmentHome();
//        FragmentHelper.replaceFragmentNoBack(fm, homeFragment, "nav", null, R.id.layout_chird_fragment);
        FragmentFirstPage fragmentFirstPage = new FragmentFirstPage();
        FragmentHelper.replaceFragmentNoBack(fm,fragmentFirstPage,"nav",null,R.id.layout_chird_fragment);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("FragmentOne","onResume");

//        titleText = (TextView) getActivity().findViewById(R.id.toolbar_txt);
//        messageBtn = (Button) getActivity().findViewById(R.id.toolbar_btn);
//        titleText.setText("云南省工商行政管理局");
//        messageBtn.setVisibility(View.VISIBLE);

    }

}
