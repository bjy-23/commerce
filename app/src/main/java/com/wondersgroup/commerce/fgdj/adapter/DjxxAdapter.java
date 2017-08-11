package com.wondersgroup.commerce.fgdj.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wondersgroup.commerce.constant.Constants;

import java.util.List;

/**
 * Created by bjy on 2017/4/27.
 */

public class DjxxAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    public DjxxAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Bundle bundle = fragments.get(position).getArguments();
        if (bundle!=null)
            return bundle.getString(Constants.TITLE)==null?"":bundle.getString(Constants.TITLE);
        return "";
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
