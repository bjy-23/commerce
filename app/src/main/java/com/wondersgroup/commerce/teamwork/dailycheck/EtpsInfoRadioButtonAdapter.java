package com.wondersgroup.commerce.teamwork.dailycheck;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.LinkedHashMap;
import java.util.List;

public class EtpsInfoRadioButtonAdapter extends ArrayAdapter<EtpsInfoBean> {
	public class ViewHolder {
		TextView textView;
		RadioButton radioButton1;
		RadioButton radioButton2;
	}

	private Context context;
	private TextView textView;
	private List<EtpsInfoBean> etpsInfoBeans;
	// private DemoApplication application;
	private LinkedHashMap<String, String> changeMap;

	public EtpsInfoRadioButtonAdapter(Context context, int resource,
									  List<EtpsInfoBean> objects, TextView textView,
									  LinkedHashMap<String, String> map) {
		super(context, R.layout.radio_buton_list_item, objects);
		this.changeMap = map;
		this.context = context;
		this.textView = textView;
		etpsInfoBeans = objects;
		// this.application = (DemoApplication) context.getApplicationContext();
		// Log.e("", "");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EtpsInfoBean etpsInfoBean = getItem(position);
		View view;
		ViewHolder viewHorlder;
		if(convertView==null){
			view= LayoutInflater.from(getContext()).inflate(
					R.layout.radio_buton_list_item, null);
			viewHorlder=new ViewHolder();
			viewHorlder.textView = (TextView) view.findViewById(R.id.listTextView);
			
			viewHorlder.radioButton1 = (RadioButton) view
					.findViewById(R.id.listRadioButton1);
			viewHorlder.radioButton2 = (RadioButton) view
					.findViewById(R.id.listRadioButton2);
			view.setTag(viewHorlder);
			
		}else{
			view=convertView;
			viewHorlder=(ViewHolder) view.getTag();
		}
		viewHorlder.textView.setText(etpsInfoBean.getName() + ":" + etpsInfoBean.getValue());
		if (changeMap.containsKey(etpsInfoBean.getKey())) {
			if (changeMap.get(etpsInfoBean.getKey()).equals("EOF")) {
				viewHorlder.radioButton1.setChecked(true);
			} else {
				viewHorlder.radioButton2.setChecked(true);
			}
		}
		viewHorlder.radioButton1
				.setOnCheckedChangeListener(new CheckedChangeListenerForOne(
						etpsInfoBean.getKey(), position));
		viewHorlder.radioButton2.setOnCheckedChangeListener(new CheckedChangeListener(
				etpsInfoBean.getKey(), position));
		return view;
	}

	class CheckedChangeListener implements OnCheckedChangeListener {
		private String key;
		private int position;

		public CheckedChangeListener(String key, int position) {
			super();
			this.key = key;
			this.position = position;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				EditText editText = new EditText(getContext());
				editText.setHint("请填写变动记录");
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
						getContext());
				dialogBuilder.setTitle("变动记录");
				dialogBuilder.setView(editText);
				dialogBuilder.setNegativeButton("确定", new Listener(editText,
						key, position));
				dialogBuilder.show();
			}

		}

	}

	class CheckedChangeListenerForOne implements OnCheckedChangeListener {
		private String key;
		private int position;

		public CheckedChangeListenerForOne(String key, int position) {
			super();
			this.key = key;
			this.position = position;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				changeMap.put(key, "EOF");
				// changeMap.remove(key);
				Log.e("map", changeMap + "");
				MapToListUtil mapToListUtil = new MapToListUtil(changeMap);
				List<EtpsInfoBean> keyValues = mapToListUtil.mapToEtpsInfoS();
				String string = "";
				for (int i = 0; i < keyValues.size(); i++) {
					if (!keyValues.get(i).getValue().equals("EOF")) {
						string += keyValues.get(i).getName() + ":"
								+ keyValues.get(i).getValue() + "\n";
					}

				}

				// String string = textView.getText().toString();
				// string += etpsInfoBeans.get(position).getName() + ":"
				// + editText.getText().toString() + "\n";

				textView.setText(string);

			}

		}

	}

	class Listener implements DialogInterface.OnClickListener {
		private EditText editText;
		private String key;
		private int position;

		public Listener(EditText editText, String key, int position) {
			super();
			this.editText = editText;
			this.key = key;
			this.position = position;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			changeMap.put(key, editText.getText().toString());
			Log.e("map", changeMap + "");
			MapToListUtil mapToListUtil = new MapToListUtil(changeMap);
			List<EtpsInfoBean> keyValues = mapToListUtil.mapToEtpsInfoS();
			String string = "";
			for (int i = 0; i < keyValues.size(); i++) {
				if (!keyValues.get(i).getValue().equals("EOF")) {
					string += keyValues.get(i).getName() + ":"
							+ keyValues.get(i).getValue() + "\n";
				}

			}

			// String string = textView.getText().toString();
			// string += etpsInfoBeans.get(position).getName() + ":"
			// + editText.getText().toString() + "\n";

			textView.setText(string);

		}
	}

	public LinkedHashMap<String, String> getChangeMap() {
		return changeMap;
	}

	public void setChangeMap(LinkedHashMap<String, String> changeMap) {
		this.changeMap = changeMap;
	}

}
