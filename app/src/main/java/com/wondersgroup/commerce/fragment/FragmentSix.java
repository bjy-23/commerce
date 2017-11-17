package com.wondersgroup.commerce.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.GSActivity;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.activity.TableListActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.MenuModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.utils.FragmentHelper;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yclli on 2016/8/26.
 */
public class FragmentSix extends RootFragment {

    @BindView(R.id.fragment_six_mine)
    ImageView mineBtn;
    @BindView(R.id.layout_home_menu)
    LinearLayout homeMenuLayout;
//    @BindView(R.id.fragment_six_version)
//    TextView versionTxt;

    private RootAppcation rootAppcation;
    private Map<String, Integer> meduleMap;
    private Toolbar toolbar;

    public FragmentSix() {
        // Required empty public constructor
    }


    public static FragmentSix newInstance() {
        FragmentSix fragment = new FragmentSix();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_six, container, false);
        ButterKnife.bind(this, view);

        rootAppcation = (RootAppcation) getActivity().getApplicationContext();
        meduleMap = rootAppcation.getMeduleMap();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        createMenu();

        return view;
    }

    private void createMenu() {
        LinearLayout linearLayout = null;

        for (int i = 0; i < rootAppcation.getMenuBtnList().size(); i++) {
            if (rootAppcation.getMenuBtnList().get(i).getName().equals("首页")) {
                MenuModel menuModel = rootAppcation.getMenuBtnList().get(i);
                for (int j = 0; j < menuModel.getFunctionList().size(); j++) {
                    if (j % 2 == 0) {
                        linearLayout = (LinearLayout) View.inflate(getActivity(), R.layout.item_hnhome_menu, null);

                        RelativeLayout layout1 = (RelativeLayout) linearLayout.findViewById(R.id.layout_1);
                        TextView tv = (TextView) linearLayout.findViewById(R.id.tv_one);
                        tv.setText(menuModel.getFunctionList().get(j).getName());
                        ImageView iv = (ImageView) linearLayout.findViewById(R.id.img_one);
                        if(meduleMap.get(menuModel.getFunctionList().get(j).getName())!=null)
                            iv.setImageResource(meduleMap.get(menuModel.getFunctionList().get(j).getName()));

                        if(j == menuModel.getFunctionList().size()-1){
                            homeMenuLayout.addView(linearLayout);
                        }

                        setMenuOnClick(layout1, tv.getText().toString());

                    } else {
                        RelativeLayout layout2 = (RelativeLayout) linearLayout.findViewById(R.id.layout_2);
                        TextView tv = (TextView) linearLayout.findViewById(R.id.tv_two);
                        tv.setText(menuModel.getFunctionList().get(j).getName());
                        ImageView iv = (ImageView) linearLayout.findViewById(R.id.img_two);
                        if(meduleMap.get(menuModel.getFunctionList().get(j).getName())!=null)
                            iv.setImageResource(meduleMap.get(menuModel.getFunctionList().get(j).getName()));

                        homeMenuLayout.addView(linearLayout);

                        setMenuOnClick(layout2, tv.getText().toString());
                    }
                }
            }
        }
    }

    private void setMenuOnClick(final RelativeLayout layout, final String txt){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FragmentSix","setMenuOnClick()--------in");
                if(txt.equals(Constants.ccjcdb)){
                    Intent intent=new Intent(getActivity(),RecyclerActivity.class);
                    intent.putExtra("type","CCJCDB");
                    intent.putExtra("title",Constants.ccjcdb);
                    startActivity(intent);
                }else if(txt.equals(Constants.ccjgcx)){
                    Intent intent=new Intent(getActivity(), TableListActivity.class);
                    intent.putExtra("title",Constants.ccjccx);
                    intent.putExtra("type","CCJCCX");
                    startActivity(intent);
                }else if(txt.equals(Constants.ajdc)){
                    Bundle bundle = new Bundle();
                    bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                    Intent intent = new Intent(getActivity(), CaseInvestigateActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(txt.equals(Constants.ajcx)){
                    Intent intent = new Intent(getActivity(), CaseEnquireActivity.class);
                    startActivity(intent);
                }else if(txt.equals(Constants.gsxx)){
                    Intent intent = new Intent(getActivity(), GSActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @OnClick(R.id.fragment_six_mine)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fragment_six_mine:
                FragmentFour fFour = new FragmentFour();
                FragmentHelper.replaceFragment(getFragmentManager(), fFour,"我的", null, R.id.layout_fragment);
                break;
        }
    }

}
