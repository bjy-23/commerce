package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class DetailCompanyActivity extends AppCompatActivity {

    public static String  url;

    private TabLayout tabmenu;
    private String[] dataMenu = {"基本信息", "管理信息", "信用公示"};
    private String[] dataTag    =   {"jbxx","glxx","xygs"};
    private ArrayList<Fragment>   fragments= new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xqzg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarText.setText("企业信息");

        tabmenu = (TabLayout) findViewById(R.id.qyxx_menu_layout);
        fragments.add(new JbxxFragment());
        fragments.add(new GlxxFragment());
        fragments.add(new XygsFragment());
        iniMenuData();
        initFragment();

    }

    private void iniMenuData() {
        //menudata
        for (int i = 0; i < dataMenu.length; i++) {
            View view = View.inflate(this, R.layout.qyxx_tab_menu, null);
            view.setTag(dataMenu[i].toString());
            TextView tv_tabmenu_name = (TextView) view.findViewById(R.id.qyxx_tv_menu);
            tv_tabmenu_name.setText(dataMenu[i].toString());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            view.setLayoutParams(params);

            tabmenu.addTab(tabmenu.newTab().setCustomView(view));
        }

        initTabMenuClick();
    }

    private void initTabMenuClick() {
        tabmenu.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        if (tab.getCustomView().getTag().equals(dataMenu[0])) {
                            showFragment(fm,ft, dataTag[0]);
                        } else if (tab.getCustomView().getTag().equals(dataMenu[1])) {
                            showFragment(fm,ft,  dataTag[1]);
                        } else if (tab.getCustomView().getTag().equals(dataMenu[2])) {
                            showFragment(fm,ft,  dataTag[2]);
                        }
                        ft.commit();

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }

        );
    }

    private void showFragment(FragmentManager fm,FragmentTransaction ft,String tag){
        for (int i=0;i<dataTag.length;i++) {
            if(!dataTag[i].equals(tag)) {
                Fragment fragment = fm.findFragmentByTag(dataTag[i]);
                if (fragment != null) {
                    ft.hide(fragment);
                }
            }
        }
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            for (int i=0;i<fragments.size();i++){
                if(dataTag[i].equals(tag)){
                    fragment = fragments.get(i);
                }
            }
            ft.add(R.id.qyxx_fragment,fragment,tag);
        }else{
            ft.show(fragment);
        }

    }

    private void initFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment    fragment    =   null;
        if (fragment == null) {
            fragment    =   fragments.get(0);
        }
        ft.add(R.id.qyxx_fragment, fragment,dataTag[0]);
        ft.commit();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String urll) {
        url = urll;
    }
}
