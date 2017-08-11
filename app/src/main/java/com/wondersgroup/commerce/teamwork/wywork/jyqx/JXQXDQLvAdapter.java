package com.wondersgroup.commerce.teamwork.wywork.jyqx;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqBean.Result;

/**
 * 经营期限到期
 * 
 * @author krh
 * 
 */
public class JXQXDQLvAdapter extends BaseAdapter {
	private JyqxdqBean data;
	private Context mContext;

	public JXQXDQLvAdapter(JyqxdqBean data, Context context) {
		super();
		this.data = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.getValues().getResult().size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = View.inflate(mContext, R.layout.item_lv_jyqxdq, null);
			Holder holder = new Holder((TextView) arg1.findViewById(R.id.tv_jyrq),
					(TextView) arg1.findViewById(R.id.tv_qymc),
					(TextView) arg1.findViewById(R.id.tv_xcrq));
			arg1.setTag(holder);
		}

		Holder holder = (Holder) arg1.getTag();

		holder.twoTv.setText(data.getValues().getResult().get(arg0).getEtpsName().trim());
		holder.oneTv.setText(data.getValues().getResult().get(arg0).getTradeEndDate()
				.subSequence(0, 10));

		if (data.getValues().getResult().get(arg0).getCheckDate().length() > 10) {
			holder.threeTv.setText(data.getValues().getResult().get(arg0).getCheckDate()
					.subSequence(0, 10));
		} else {
			holder.threeTv.setText(data.getValues().getResult().get(arg0).getCheckDate().trim());
		}
		return arg1;
	}

	private class Holder {
		TextView oneTv;
		TextView twoTv;
		TextView threeTv;

		public TextView getOneTv() {
			return oneTv;
		}

		public void setOneTv(TextView oneTv) {
			this.oneTv = oneTv;
		}

		public TextView getTwoTv() {
			return twoTv;
		}

		public void setTwoTv(TextView twoTv) {
			this.twoTv = twoTv;
		}

		public TextView getThreeTv() {
			return threeTv;
		}

		public void setThreeTv(TextView threeTv) {
			this.threeTv = threeTv;
		}

		public Holder(TextView oneTv, TextView twoTv, TextView threeTv) {
			super();
			this.oneTv = oneTv;
			this.twoTv = twoTv;
			this.threeTv = threeTv;
		}

	}

}
