package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class EtpsOneAdapter extends ArrayAdapter<EtpsBean> {

	private int resourceId;

	public EtpsOneAdapter(Context context, int resource, List<EtpsBean> objects) {
		super(context, R.layout.mode_list_item1, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EtpsBean etpsBean = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.mode_list_item1, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText("  " + etpsBean.getEtpsName());
		return view;
	}

}
