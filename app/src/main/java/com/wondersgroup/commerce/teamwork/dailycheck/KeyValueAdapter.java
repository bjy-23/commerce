package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class KeyValueAdapter extends ArrayAdapter<KeyValue> {

	private int resourceId;

	public KeyValueAdapter(Context context, int resource, List<KeyValue> objects) {
		super(context, resource, objects);
		resourceId = R.layout.dialog_list_item;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView textView = (TextView) view
				.findViewById(R.id.dialog_list_item_name);
		KeyValue keyValue = getItem(position);
		textView.setText(keyValue.getValue());
		return view;
	}

}
