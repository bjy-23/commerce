package com.wondersgroup.commerce.teamwork.myhwggdq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jiajiangyi on 2016/4/26 0026.
 */
public class HwggdqActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.mid_toolbar)
    Toolbar midToolbar;
    @BindView(R.id.content)
    FrameLayout content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_activity);
        ButterKnife.bind(this);

        setSupportActionBar(midToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarTitle.setText("户外广告到期");
        UtilForFragment.switchContent(this,new HwggdqFragment(),R.id.content);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
