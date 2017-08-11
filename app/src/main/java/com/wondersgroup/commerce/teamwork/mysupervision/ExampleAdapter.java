package com.wondersgroup.commerce.teamwork.mysupervision;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.Child;

import java.util.List;

public class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter{
	private static final int[] EMPTY_STATE_SET = {};
	/** State indicating the group is expanded. */
	private static final int[] GROUP_EXPANDED_STATE_SET = { android.R.attr.state_expanded };
	/** State indicating the group is empty (has no children). */
	private static final int[] GROUP_EMPTY_STATE_SET = { android.R.attr.state_empty };
	/** State indicating the group is expanded and empty (has no children). */
	private static final int[] GROUP_EXPANDED_EMPTY_STATE_SET = {
			android.R.attr.state_expanded, android.R.attr.state_empty };
	/** States for the group where the 0th bit is expanded and 1st bit is empty. */
	private static final int[][] GROUP_STATE_SETS = { EMPTY_STATE_SET, // 00
			GROUP_EXPANDED_STATE_SET, // 01
			GROUP_EMPTY_STATE_SET, // 10
			GROUP_EXPANDED_EMPTY_STATE_SET // 11
	};
	private LayoutInflater inflater;
	private List<Father> fathers;

	// @Override
	// public void onGroupCollapsed(int groupPosition) {
	// // onGroupCollapsedEx(groupPosition);
	// // super.onGroupCollapsed(groupPosition);
	// // notifyDataSetChanged();
	//
	// }
	//
	// private void onGroupCollapsedEx(int groupPosition) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onGroupExpanded(int groupPosition) {
	// // onGroupExpandedEx(groupPosition);
	// // super.onGroupExpanded(groupPosition);
	// // notifyDataSetChanged();
	// }
	//
	// private void onGroupExpandedEx(int groupPosition) {
	// // TODO Auto-generated method stub
	//
	// }

	protected void setIndicatorState(Drawable indicator, int groupPosition,
			boolean isExpanded) {
		final int stateSetIndex = (isExpanded ? 1 : 0);
		indicator.setState(GROUP_STATE_SETS[stateSetIndex]);
	}

	public ExampleAdapter(LayoutInflater inflater, List<Father> fathers) {
		super();
		this.inflater = inflater;
		this.fathers = fathers;
	}

	@Override
	public int getGroupCount() {

		return fathers.size();
	}

	@Override
	public Father getGroup(int groupPosition) {
		Father item = fathers.get(groupPosition);
		return item;
	}

	@Override
	public Child getChild(int groupPosition, int childPosition) {
		Child child1  = fathers.get(groupPosition).getChildMatter()
				.get(childPosition);
		return child1;
	}

	@Override
	public long getGroupId(int groupPosition) {

		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		FatherHolder fatherHolder;
		Father Father = getGroup(groupPosition);
		if (convertView == null) {
			fatherHolder = new FatherHolder();
			convertView = inflater.inflate(R.layout.father_item, parent, false);
			// fatherHolder.checkBox = (CheckBox) convertView
			// .findViewById(R.id.fatherCheckbox);
			fatherHolder.textView = (TextView) convertView
					.findViewById(R.id.fatherTextView);
			fatherHolder.imageView = (ImageView) convertView
					.findViewById(R.id.imageView);

			convertView.setTag(fatherHolder);
		} else {
			fatherHolder = (FatherHolder) convertView.getTag();

		}
		fatherHolder.textView.setText(Father.getMatterName());
		if (isExpanded) {
			fatherHolder.imageView.setImageResource(R.drawable.group_up);
		} else {
			fatherHolder.imageView.setImageResource(R.drawable.group_down);
		}
		// setIndicatorState(fatherHolder.imageView.getDrawable(),
		// groupPosition,
		// isExpanded);

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	@Override
	public View getRealChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildHolder childHolder;
		Child child = getChild(groupPosition, childPosition);
		if (convertView == null) {
			childHolder = new ChildHolder();
			convertView = inflater.inflate(R.layout.child_item, parent, false);
			childHolder.subTitle = (TextView) convertView
					.findViewById(R.id.childTextView);
			childHolder.radioGroup = (RadioGroup) convertView
					.findViewById(R.id.childRadioGroup);
			childHolder.radioButton1 = (RadioButton) convertView
					.findViewById(R.id.radioButton1);
			childHolder.radioButton2 = (RadioButton) convertView
					.findViewById(R.id.radioButton2);
			convertView.setTag(childHolder);
		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}

		childHolder.subTitle.setText(child.getMatterName());
		childHolder.radioButton1
				.setOnCheckedChangeListener(new Group_CheckBox_Click(
						groupPosition, childPosition, 1));
		childHolder.radioButton2
				.setOnCheckedChangeListener(new Group_CheckBox_Click(
						groupPosition, childPosition, 0));
		if (child.getChildFlag() == 1) {
			childHolder.radioButton1.setChecked(true);
		} else if (child.getChildFlag() == 0) {
			childHolder.radioButton2.setChecked(true);
		} else {
			childHolder.radioGroup.clearCheck();
		}
		// childHolder.radioGroup
		// .setOnCheckedChangeListener(new Group_CheckBox_Click(
		// groupPosition, childPosition));
		// if (Child.getChildFlag() > 0) {
		// RadioButton button = (RadioButton) childHolder.radioGroup
		// .findViewById(Child.getChildFlag());
		// button.setSelected(true);
		// } else {
		// childHolder.radioGroup.clearCheck();
		// }

		return convertView;
	}

	class Group_CheckBox_Click implements OnCheckedChangeListener {
		private int flag;
		private int groupPosition;
		private int childPosition;

		public Group_CheckBox_Click(int groupPosition, int childPosition,
				int flag) {
			super();
			this.groupPosition = groupPosition;
			this.childPosition = childPosition;
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
				fathers.get(groupPosition).getChildMatter().get(childPosition)
						.setChildFlag(flag);
			}

		}
	}

	@Override
	public int getRealChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return fathers.get(groupPosition).getChildMatter().size();
	}

	private class FatherHolder {
		// CheckBox checkBox;
		TextView textView;
		ImageView imageView;
	}

	private class ChildHolder {
		TextView subTitle;
		RadioGroup radioGroup;
		RadioButton radioButton1;
		RadioButton radioButton2;

	}

}
