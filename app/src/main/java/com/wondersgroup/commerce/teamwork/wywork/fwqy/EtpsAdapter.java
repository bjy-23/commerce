package com.wondersgroup.commerce.teamwork.wywork.fwqy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;

import java.util.List;


public class EtpsAdapter extends ArrayAdapter<EtpsBean> {

	private int resourceId;

	public EtpsAdapter(Context context, int resource, List<EtpsBean> objects) {
		super(context, R.layout.record_list_item, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EtpsBean etpsBean = getItem(position);
		String checkType = etpsBean.getCheckType();
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.record_list_item, null);
		if (checkType.equals("4")) {
			TextView textView = (TextView) view.findViewById(R.id.textView1);
			textView.setText(etpsBean.getAbbusePerson() + "  "
					+ etpsBean.getAbbuseLocation());

			TextView textView2 = (TextView) view.findViewById(R.id.textView2);
			textView2.setText(etpsBean.getCheckDate());

		} else {
			TextView textView = (TextView) view.findViewById(R.id.textView1);
			textView.setText("  " + etpsBean.getEtpsName());

			TextView textView2 = (TextView) view.findViewById(R.id.textView2);
			textView2.setText(etpsBean.getCheckDate());
		}

		return view;
	}

}
