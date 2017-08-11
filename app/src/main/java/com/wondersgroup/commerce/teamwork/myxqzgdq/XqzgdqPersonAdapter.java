package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


public class XqzgdqPersonAdapter extends BaseAdapter {
	private CompanyPersonBean data;
	private Context mContext;

	public CompanyPersonBean getData() {
		return data;
	}

	public void setData(CompanyPersonBean data) {
		this.data = data;
	}

	public XqzgdqPersonAdapter(CompanyPersonBean data, Context context) {
		super();
		this.data = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (data == null) {
			return 0;
		}

		return  data.getValues().getResult().size();
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
			arg1 = View.inflate(mContext, R.layout.item_lv_xqzgdq, null);
			Holder holder = new Holder((TextView) arg1.findViewById(R.id.one),
					(TextView) arg1.findViewById(R.id.four));
			arg1.setTag(holder);
		}

		Holder holder = (Holder) arg1.getTag();

//		if (XqzgdqFragment.currentTab == 0
//				&& !data.getValues().getResult().get(arg0).getEntityName().equals("")) {
//			holder.getOne().setText(data.getValues().getResult().get(arg0).getEntityName());
//		} else {
//			holder.getOne().setText(data.getValues().getResult().get(arg0).getPersnName());
//		}
		holder.getOne().setText(data.getValues().getResult().get(arg0).getPersnName());
		holder.getFour().setText(data.getValues().getResult().get(arg0).getChangeDate());


		return arg1;
	}

	private class Holder {
		TextView one;
		TextView four;

		public TextView getOne() {
			return one;
		}

		public void setOne(TextView one) {
			this.one = one;
		}


		public TextView getFour() {
			return four;
		}

		public void setFour(TextView four) {
			this.four = four;
		}


		public Holder(TextView one,TextView four) {
			super();
			this.one = one;
			this.four = four;
		}

	}

}
