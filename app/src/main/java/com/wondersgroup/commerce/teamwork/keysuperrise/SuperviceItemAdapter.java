package com.wondersgroup.commerce.teamwork.keysuperrise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class SuperviceItemAdapter extends ArrayAdapter<com.wondersgroup.commerce.teamwork.dailycheck.Father> {
	private List<com.wondersgroup.commerce.teamwork.dailycheck.Father> fathers;

	public SuperviceItemAdapter(Context context, int resource,
			List<com.wondersgroup.commerce.teamwork.dailycheck.Father> fathers) {
		super(context, R.layout.supervice_list_item, fathers);
		this.fathers = fathers;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		com.wondersgroup.commerce.teamwork.dailycheck.Father father = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.supervice_list_item, null);
		TextView textView = (TextView) view.findViewById(R.id.listTextView);
		textView.setText(father.getMatterName());
		RadioButton radioButton1 = (RadioButton) view
				.findViewById(R.id.listRadioButton1);
		RadioButton radioButton2 = (RadioButton) view
				.findViewById(R.id.listRadioButton2);
		if (father.getCheckFlag() == 1) {
			radioButton1.setChecked(true);
		}
		if (father.getCheckFlag() == 0) {
			radioButton2.setChecked(true);
		}
		radioButton1.setOnCheckedChangeListener(new Group_CheckBox_Click(
				position, 1));
		radioButton2.setOnCheckedChangeListener(new Group_CheckBox_Click(
				position, 0));
		return view;
	}

	class Group_CheckBox_Click implements OnCheckedChangeListener {
		private int flag;
		private int groupPosition;

		public Group_CheckBox_Click(int groupPosition, int flag) {
			super();
			this.groupPosition = groupPosition;
			this.flag = flag;
		}

		//
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// int flag = group.getCheckedRadioButtonId();
		// fathers.get(groupPosition).getChilds()
		// .get(childPosition).setChildFlag(flag);
		//
		// }

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				fathers.get(groupPosition).setCheckFlag(flag);
			}

		}
	}

}
