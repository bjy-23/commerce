package com.wondersgroup.commerce.teamwork.systemsetting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RootActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kangrenhui on 2016/3/3.
 */
public class SettingActivity extends RootActivity {
    @BindView(R.id.btn_about)
    TextView aboutBtn;
    @BindView(R.id.btn_check)
    TextView checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_about, R.id.btn_check, R.id.btn_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_about:
                Intent intent = new Intent(mContext, AboutActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_check:
                Toast.makeText(mContext, "目前已经是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_exit:
                finish();

                break;

        }
    }

}
