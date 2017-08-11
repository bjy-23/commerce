package com.wondersgroup.commerce.ynwq.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.adapter.ViewPagerAdapter;
import com.wondersgroup.commerce.ynwq.fragment.XCKCFragment;


import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.vp_tabs)TabLayout tabs;
    @Bind(R.id.vp_viewpager)ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String type;
    private String xckcType=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        type=getIntent().getStringExtra("type");
        if("DBXW".equals(type)){
            title.setText(getIntent().getStringExtra("entName"));
            xckcType=getIntent().getStringExtra("XCKCType");
        }
        adapter=new ViewPagerAdapter(getSupportFragmentManager(),type,xckcType);
        adapter.setMeId(getIntent().getStringExtra("meId"));
        adapter.setEtpsId(getIntent().getStringExtra("etpsId"));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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
        return true;
    }

    @Override
    public void onBackPressed() {
        if("DBXW".equals(type)&&"1".equals(xckcType)){
            XCKCFragment fragment= (XCKCFragment) adapter.getRegisteredFragment(1);
            if(fragment.confirmQuit()) {
                new MaterialDialog.Builder(ViewPagerActivity.this)
                        .title("有记录未提交")
                        .content("您有未提交的监管记录数据，仍要离开？")
                        .positiveText("是")
                        .negativeText("否")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                //ViewPagerActivity.this.finish();
                                ViewPagerActivity.super.onBackPressed();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                viewPager.setCurrentItem(2, true);
                            }
                        })
                        .build()
                        .show();
            }else {
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
    }
}
