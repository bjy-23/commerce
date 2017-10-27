package com.wondersgroup.commerce.teamwork.systemsetting;

import android.os.Bundle;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.BaseActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kangrenhui on 2016/3/4.
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.tv_dept)
    TextView tvDept;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        tvTitle.setText(RootAppcation.getInstance().getVersion() + Constants.DEPT);
        tvVersion.setText("版本号" + RootAppcation.getInstance().getVersionName());
        tvDept.setText(RootAppcation.getInstance().getVersion()+Constants.DEPT);
        tvBottom.setText("版权所有：" + RootAppcation.getInstance().getVersion() + Constants.DEPT);
    }
}
