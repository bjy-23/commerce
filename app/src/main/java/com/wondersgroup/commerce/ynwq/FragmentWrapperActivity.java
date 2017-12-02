package com.wondersgroup.commerce.ynwq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wondersgroup.commerce.BuildConfig;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.BaseActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.ynwq.fragment.JinduQueryFragment;
import com.wondersgroup.commerce.ynwq.fragment.QueryFragment;

public class FragmentWrapperActivity extends BaseActivity {
    private String type;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        type = getIntent().getStringExtra(Constants.TYPE);
        if (type != null)
            tvTitle.setText(type);
        Fragment fragment = null;
        switch (type){
            default:
                break;
            case "办理进度":
                fragment = new JinduQueryFragment();
                break;
            case "微企信息":
            case "扶持情况":
                fragment = new QueryFragment();
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.layout_content, fragment).commit();
    }
}
