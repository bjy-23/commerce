package com.wondersgroup.commerce.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.report_tab)
    TabLayout tabs;
    @BindView(R.id.report_vp)
    ViewPager viewPager;

    private String titleString;
    private String type;
    public static String BUENT_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.app_back);
        titleString = getIntent().getStringExtra("title");
        if (titleString == null)
            titleString = getResources().getString(R.string.report_title);
        title.setText(titleString);
        type = getIntent().getStringExtra("type");
        if (type == null)
            type = "TSJBCL";

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), type);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
//        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
