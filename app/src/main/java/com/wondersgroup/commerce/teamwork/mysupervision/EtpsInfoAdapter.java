package com.wondersgroup.commerce.teamwork.mysupervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class EtpsInfoAdapter extends ArrayAdapter<EtpsInfoChangeBean> {
	public EtpsInfoAdapter(Context context, int resource,
						   List<EtpsInfoChangeBean> objects) {
		super(context, R.layout.radio_buton_info_list_item, objects);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EtpsInfoChangeBean etpsInfoBean = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.radio_buton_info_list_item, null);
		TextView textView = (TextView) view.findViewById(R.id.listTextView);
		textView.setText(etpsInfoBean.getName() + ":" + etpsInfoBean.getValue());
		TextView textView2 = (TextView) view
				.findViewById(R.id.listTextViewChange);
		if (etpsInfoBean.getChange() != null) {
			if (etpsInfoBean.getChange().equals("EOF")) {
				textView2.setText("一致");
			} else {
				textView2.setText("变动记录:" + etpsInfoBean.getChange());
			}
		}

		return view;
	}

}
