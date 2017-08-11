package com.wondersgroup.commerce.teamwork.myrcxc;

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

/**
 * Created by jiajiangyi on 2016/5/5 0005 10:19
 */

public class RCXCActivity extends AppCompatActivity {
    private TabLayout tabmenu;
    private String[] dataMenu = {"企业", "个体"};
    private static int currTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcxc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarText.setText("日常巡查");

        tabmenu = (TabLayout) findViewById(R.id.rcxc_menu_layout);
        initTabMenu();
        initFragment();

    }

    private void initFragment() {
        currTab = 0;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.rcxc_fragment, new RCXCFragment());
        ft.commit();
    }

    private void initTabMenu() {
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
        setTabMenuClick();
    }

    private void setTabMenuClick() {
        tabmenu.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getCustomView().getTag().equals(dataMenu[0])) {
                    currTab = 0;
                }
                if (tab.getCustomView().getTag().equals(dataMenu[1])) {
                    currTab = 1;
                }
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.rcxc_fragment, new RCXCFragment());
                fragmentTransaction.commit();
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public static int getCurrTab() {
        return currTab;
    }

    public static void setCurrTab(int currTab) {
        RCXCActivity.currTab = currTab;
    }

}




















