package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class DemoAdapter4 extends ArrayAdapter<DemoBean> {

	public DemoAdapter4(Context context, int resource, List<DemoBean> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DemoBean demoBean = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.mode_list_item4, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(demoBean.getTextView1());
		TextView textView2 = (TextView) view.findViewById(R.id.textView2);
		textView2.setText(demoBean.getTextView2());
		TextView textView3 = (TextView) view.findViewById(R.id.textView3);
		textView3.setText(demoBean.getTextView3());
		TextView textView4 = (TextView) view.findViewById(R.id.textView4);
		textView4.setText(demoBean.getTextView4());
		return view;
	}
}