package com.wondersgroup.commerce.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kangrenhui on 2016/2/24.
 */
public class RootFragment extends Fragment {
    protected Context mContext;
    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mActivity = getActivity();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
