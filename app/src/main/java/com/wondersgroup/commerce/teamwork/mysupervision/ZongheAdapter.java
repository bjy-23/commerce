package com.wondersgroup.commerce.teamwork.mysupervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;

import java.util.List;

public class ZongheAdapter extends ArrayAdapter<EtpsBean> {

	private int resourceId;

	public ZongheAdapter(Context context, int resource, List<EtpsBean> objects) {
		super(context, R.layout.mode_list_item1, objects);
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EtpsBean etpsBean = getItem(position);
		String checkType = etpsBean.getCheckType();
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.mode_list_item1, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		if (checkType.equals("1")) {
			textView.setText("【日常】" + etpsBean.getEtpsName() + "   "
					+ etpsBean.getCheckDate());
		}
		if (checkType.equals("2")) {
			textView.setText("【重点】" + etpsBean.getEtpsName() + "   "
					+ etpsBean.getCheckDate());
		}
		if (checkType.equals("3")) {
			textView.setText("【专项】" + etpsBean.getEtpsName() + "   "
					+ etpsBean.getCheckDate());
		}
		if (checkType.equals("4")) {
			textView.setText("【无照】" + etpsBean.getAbbusePerson() + "  "
					+ etpsBean.getAbbuseLocation() + "   "
					+ etpsBean.getCheckDate());
		}
		if (checkType.equals("5")) {
			textView.setText("【服务】" + etpsBean.getEtpsName() + "   "
					+ etpsBean.getCheckDate());
		}
		if (checkType.equals("6")) {
			textView.setText("【协会】" + etpsBean.getEtpsName() + "   "
					+ etpsBean.getCheckDate());
		}

		return view;
	}

}
