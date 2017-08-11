package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.HashMap;
import java.util.List;

public class CheckListAdapter extends BaseAdapter {
	// public class ViewHolder {
	// public TextView tv;
	// public CheckBox cb;
	// public Boolean isTitle = false;
	// }

	// 填充数据的list
	private List<KeyValue> keyValues;
	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;
	// 上下文
	private Context context;
	// 用来导入布局
	private LayoutInflater inflater = null;

	private TextView keyValueView;

	private String[] backStrings;

	// 构造器
	public CheckListAdapter(Context context, TextView keyValueView,
							List<KeyValue> keyValues) {
		this.context = context;
		this.keyValues = keyValues;
		this.keyValueView = keyValueView;
		this.backStrings = keyValueView.getTag().toString().split(",");
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		// 初始化数据
		initDate();

	}

	// 初始化isSelected的数据
	private void initDate() {
		for (int i = 0; i < keyValues.size(); i++) {
			getIsSelected().put(i, false);
		}

		for (int i = 0; i < backStrings.length; i++) {
			for (KeyValue keyValue : keyValues) {
				if (keyValue.getKey().equals(backStrings[i])) {
					int position = keyValues.indexOf(keyValue);
					Log.e("position", position + "");
					if (position != -1) {
						isSelected.put(position, true);
					}
				}
			}

		}
	}

	@Override
	public int getCount() {
		return keyValues.size();
	}

	@Override
	public Object getItem(int position) {
		return keyValues.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// ViewHolder holder = null;
	// KeyValue keyValue = keyValues.get(position);
	// if (keyValue.getKey().startsWith("tag")) {
	// if (convertView == null) {
	// // 获得ViewHolder对象
	// holder = new ViewHolder();
	//
	// // 导入布局并赋值给convertview
	// convertView = inflater.inflate(R.layout.check_list_title, null);
	// holder.tv = (TextView) convertView
	// .findViewById(R.id.checkListTitle);
	// // 为view设置标签
	// convertView.setTag(holder);
	// } else {
	// // 取出holder
	// holder = (ViewHolder) convertView.getTag();
	// }
	//
	// // 设置list中TextView的显示
	// holder.tv.setText(keyValues.get(position).getValue());
	// holder.isTitle = true;
	// } else {
	// if (convertView == null) {
	// // 获得ViewHolder对象
	// holder = new ViewHolder();
	//
	// // 导入布局并赋值给convertview
	// convertView = inflater.inflate(R.layout.check_list, null);
	// holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
	// holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
	// // 为view设置标签
	// convertView.setTag(holder);
	// } else {
	// // 取出holder
	// holder = (ViewHolder) convertView.getTag();
	// }
	//
	// // 设置list中TextView的显示
	// holder.tv.setText(keyValues.get(position).getValue());
	// // 根据isSelected来设置checkbox的选中状况
	// holder.cb.setChecked(getIsSelected().get(position));
	// }
	// return convertView;
	// }

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		KeyValue keyValue = keyValues.get(position);
		if (keyValue.getKey().startsWith("tag")) {
			view = inflater.inflate(R.layout.check_list_title, null);
			TextView checkListTitle = (TextView) view
					.findViewById(R.id.checkListTitle);
			checkListTitle.setText(keyValue.getValue());
			keyValue.setIsTitle(true);

		} else {
			view = inflater.inflate(R.layout.check_list, null);
			TextView tv = (TextView) view.findViewById(R.id.item_tv);
			CheckBox cb = (CheckBox) view.findViewById(R.id.item_cb);
			tv.setText(keyValues.get(position).getValue());
			// 根据isSelected来设置checkbox的选中状况
			if (getIsSelected().get(position) != null) {
				cb.setChecked(getIsSelected().get(position));
			}

		}

		return view;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		CheckListAdapter.isSelected = isSelected;
	}

}