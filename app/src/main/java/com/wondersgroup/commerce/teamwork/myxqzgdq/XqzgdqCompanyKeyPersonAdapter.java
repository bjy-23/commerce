package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


public class XqzgdqCompanyKeyPersonAdapter extends BaseAdapter {
	private CompanyJBXXBean data;
	private Context mContext;

	public CompanyJBXXBean getData() {
		return data;
	}

	public void setData(CompanyJBXXBean data) {
		this.data = data;
	}

	public XqzgdqCompanyKeyPersonAdapter(CompanyJBXXBean data, Context context) {
		super();
		this.data = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  data.getValues().getEtpsMemberEnty().size();
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
			arg1 = View.inflate(mContext, R.layout.item_xqzgdq_company_keyperson_info, null);

			Holder holder = new Holder((TextView) arg1.findViewById(R.id.tv_keyperson_no),
					(TextView) arg1.findViewById(R.id.tv_keyperson_name),
					(TextView) arg1.findViewById(R.id.tv_keyperson_voting)

			);
			arg1.setTag(holder);
		}

		Holder holder = (Holder) arg1.getTag();

		holder.getTv_keyperson_no().setText((1+arg0)+"");
		holder.getTv_keyperson_name().setText(data.getValues().getEtpsMemberEnty().get(arg0).getPersonName());
		holder.getTv_keyperson_voting().setText(data.getValues().getEtpsMemberEnty().get(arg0).getHdshGb());

		return arg1;
	}

	private class Holder {
		TextView tv_keyperson_no;       //序号
		TextView tv_keyperson_name;     //姓名
		TextView tv_keyperson_voting;   //职务

		public TextView getTv_keyperson_no() {
			return tv_keyperson_no;
		}

		public void setTv_keyperson_no(TextView tv_keyperson_no) {
			this.tv_keyperson_no = tv_keyperson_no;
		}

		public TextView getTv_keyperson_name() {
			return tv_keyperson_name;
		}

		public void setTv_keyperson_name(TextView tv_keyperson_name) {
			this.tv_keyperson_name = tv_keyperson_name;
		}

		public TextView getTv_keyperson_voting() {
			return tv_keyperson_voting;
		}

		public void setTv_keyperson_voting(TextView tv_keyperson_voting) {
			this.tv_keyperson_voting = tv_keyperson_voting;
		}

		public Holder(TextView tv_keyperson_no, TextView tv_keyperson_name, TextView tv_keyperson_voting) {
			this.tv_keyperson_no = tv_keyperson_no;
			this.tv_keyperson_name = tv_keyperson_name;
			this.tv_keyperson_voting = tv_keyperson_voting;
		}
	}

}
