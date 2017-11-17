package com.wondersgroup.commerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;

public class BaseActivity extends AppCompatActivity {
    protected TextView tvTitle;
    protected TextView tvOption;
    private FrameLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.app_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseActivity.this.getSupportFragmentManager().getBackStackEntryCount() != 0)
                    UtilForFragment.popBackStack(BaseActivity.this);
                else
                    BaseActivity.this.finish();
            }
        });

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvOption = (TextView) findViewById(R.id.tv_option);
        layoutContent = (FrameLayout) findViewById(R.id.layout_content);
    }

    protected void addContentView(int layoutId) {
        View view = LayoutInflater.from(this).inflate(layoutId, null, false);
        layoutContent.addView(view);


//        Arrays.asList("a","b").forEach( e -> Log("",e));
    }


}
