package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


public class XqzgdqCompanyVestorAdapter extends BaseAdapter {
	private CompanyJBXXBean data;
	private Context mContext;

	public CompanyJBXXBean getData() {
		return data;
	}

	public void setData(CompanyJBXXBean data) {
		this.data = data;
	}

	public XqzgdqCompanyVestorAdapter(CompanyJBXXBean data, Context context) {
		super();
		this.data = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  data.getValues().getInvestorEnty().size();
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
			arg1 = View.inflate(mContext, R.layout.item_xqzgdq_company_vestor_info, null);

			Holder holder = new Holder((TextView) arg1.findViewById(R.id.tv_invest_person),
					(TextView) arg1.findViewById(R.id.tv_invest_type),
					(TextView) arg1.findViewById(R.id.tv_licence_type),
					(TextView) arg1.findViewById(R.id.tv_licence_no),
					(TextView) arg1.findViewById(R.id.tv_cptl),
					(TextView) arg1.findViewById(R.id.tv_actlInvt)

			);
			arg1.setTag(holder);
		}

		Holder holder = (Holder) arg1.getTag();


		holder.getTv_invest_person().setText(data.getValues().getInvestorEnty().get(arg0).getInvestorName());
		holder.getTv_invest_type().setText(data.getValues().getInvestorEnty().get(arg0).getEtpsTypeGb());
		holder.getTv_licence_type().setText(data.getValues().getInvestorEnty().get(arg0).getCetfType());
		holder.getTv_licence_no().setText(data.getValues().getInvestorEnty().get(arg0).getCetfId());
		holder.getTv_cptl().setText(data.getValues().getInvestorEnty().get(arg0).getCptl()+"(万人民币)");
		holder.getTv_actlInvt().setText(data.getValues().getInvestorEnty().get(arg0).getActlInvt()+"(万人民币)");

		return arg1;
	}

	private class Holder {
		TextView tv_invest_person;
		TextView tv_invest_type;
		TextView tv_licence_type;
		TextView tv_licence_no;
		TextView tv_cptl;
		TextView tv_actlInvt;

		public TextView getTv_invest_person() {
			return tv_invest_person;
		}

		public void setTv_invest_person(TextView tv_invest_person) {
			this.tv_invest_person = tv_invest_person;
		}

		public TextView getTv_invest_type() {
			return tv_invest_type;
		}

		public void setTv_invest_type(TextView tv_invest_type) {
			this.tv_invest_type = tv_invest_type;
		}

		public TextView getTv_licence_type() {
			return tv_licence_type;
		}

		public void setTv_licence_type(TextView tv_licence_type) {
			this.tv_licence_type = tv_licence_type;
		}

		public TextView getTv_licence_no() {
			return tv_licence_no;
		}

		public void setTv_licence_no(TextView tv_licence_no) {
			this.tv_licence_no = tv_licence_no;
		}

		public TextView getTv_cptl() {
			return tv_cptl;
		}

		public void setTv_cptl(TextView tv_cptl) {
			this.tv_cptl = tv_cptl;
		}

		public TextView getTv_actlInvt() {
			return tv_actlInvt;
		}

		public void setTv_actlInvt(TextView tv_actlInvt) {
			this.tv_actlInvt = tv_actlInvt;
		}

		public Holder(TextView tv_invest_person, TextView tv_invest_type, TextView tv_licence_type, TextView tv_licence_no, TextView tv_cptl, TextView tv_actlInvt) {
			this.tv_invest_person = tv_invest_person;
			this.tv_invest_type = tv_invest_type;
			this.tv_licence_type = tv_licence_type;
			this.tv_licence_no = tv_licence_no;
			this.tv_cptl = tv_cptl;
			this.tv_actlInvt = tv_actlInvt;
		}

	}

}
