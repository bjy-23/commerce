package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class StringAdapter extends ArrayAdapter<String> {
	private int resourceId;

	public StringAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}

	public StringAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView textView = (TextView) view
				.findViewById(R.id.dialog_list_item_name);
		String string = getItem(position);
		textView.setText(string);
		return view;
	}

}
