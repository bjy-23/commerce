package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DailyCheckActivity extends AppCompatActivity {

    @BindView(R.id.mid_toolbar)Toolbar toolbar;
    @BindView(R.id.toolbar_title)TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("日常检查");

        UtilForFragment.switchContent(this, new DailyListSearchFragment(),
                R.id.content);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {
        switch (requestCode) {

            default:
                if (resultCode == RESULT_OK
                        && (requestCode == 51 || requestCode == 52 || requestCode == 53)) {
                    DailyFragment dailyFragment = (DailyFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.content);

                    dailyFragment.onActivityResult(requestCode, resultCode, arg2);
                }

                if (resultCode == RESULT_OK
                        && (requestCode == 61 || requestCode == 62 || requestCode == 63)) {
                    DailyCheckTempFragment checkTempFragment = (DailyCheckTempFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.content);

                    checkTempFragment.onActivityResult(requestCode, resultCode,
                            arg2);
                }
                if (resultCode == RESULT_OK
                        && (requestCode == 71 || requestCode == 72 || requestCode == 73)) {
                    UnlicensedFragment unlicensedFragment = (UnlicensedFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.content);

                    unlicensedFragment.onActivityResult(requestCode, resultCode,
                            arg2);
                }
                if (resultCode == RESULT_OK
                        && (requestCode == 81 || requestCode == 82 || requestCode == 83)) {
                    UnlicensedTempFragment unlicensedTempFragment = (UnlicensedTempFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.content);

                    unlicensedTempFragment.onActivityResult(requestCode, resultCode,
                            arg2);
                }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
