package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.commerce.R;


public class UnlicensedItemBaseInfoFragment extends Fragment {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.unlicensed_item_baseinfo_fragment, null);
		
		return view;
	}
	

}
