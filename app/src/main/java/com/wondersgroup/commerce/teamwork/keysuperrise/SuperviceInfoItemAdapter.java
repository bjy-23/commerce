package com.wondersgroup.commerce.teamwork.keysuperrise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.List;

public class SuperviceInfoItemAdapter extends ArrayAdapter<Father> {
	private List<Father> fathers;

	public SuperviceInfoItemAdapter(Context context, int resource,
			List<Father> fathers) {
		super(context, R.layout.supervice_list_item, fathers);
		this.fathers = fathers;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Father father = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.supervice_list_info_item, null);
		TextView textView = (TextView) view.findViewById(R.id.listTextView);
		if (father.getCheckFlag() == 1) {
			textView.setText(father.getMatterName() + "    是");
		} else if (father.getCheckFlag() == 0) {
			textView.setText(father.getMatterName() + "    否");
		} else {
			textView.setText(father.getMatterName());
		}

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
