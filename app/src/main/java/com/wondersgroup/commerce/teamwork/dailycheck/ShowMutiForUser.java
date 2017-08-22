package com.wondersgroup.commerce.teamwork.dailycheck;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;

import java.util.HashMap;
import java.util.List;

public class ShowMutiForUser implements OnClickListener {
	private RootAppcation application;
	private AlertDialog alertDialog;
	private AlertDialog.Builder dialog;
	private ListView keyValueListView;
	private BaseAdapter adapter;
	private List<KeyValue> keyValues;
	private Context context;
	private TextView keyValueView;
	private static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
	private String[] backStrings;

	public ShowMutiForUser(List<KeyValue> keyValues, Context context,
						   TextView keyValueView) {
		super();
		this.keyValues = keyValues;
		this.context = context;
		this.keyValueView = keyValueView;
		this.backStrings = keyValueView.getTag().toString().split(",");
		for (int i = 0; i < backStrings.length; i++) {
			int position = keyValues.indexOf(backStrings[i]);
			isSelected.put(position, true);

		}
		this.application = (RootAppcation) context.getApplicationContext();
	}

	@Override
	public void onClick(View v) {
		keyValueListView = new ListView(context);
		adapter = new CheckListAdapter(context, keyValueView, keyValues);

		keyValueListView.setAdapter(adapter);
		keyValueListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
				KeyValue keyValue = keyValues.get(position);

				if (!keyValue.getIsTitle()) {
					// 改变CheckBox的状态
					CheckBox cb = (CheckBox) view.findViewById(R.id.item_cb);
					cb.toggle();
					// 将CheckBox的选中状况记录下来
					CheckListAdapter.getIsSelected().put(position,
							cb.isChecked());
					// 调整选定条目
					// if (holder.cb.isChecked() == true) {
					// checkNum++;
					// } else {
					// checkNum--;
					// }
					// 用TextView显示
				}

			}
		});

		dialog = new AlertDialog.Builder(context);
		dialog.setView(keyValueListView);
		dialog.setCancelable(true);
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HashMap<Integer, Boolean> hashMap = CheckListAdapter
						.getIsSelected();
				String string = application.getLoginUserInfo().getUserName()+"\n";
				String tag = application.getLoginUserInfo().getUserId()+",";
				for (int i = 0; i < hashMap.size(); i++) {
					if (hashMap.get(i)) {
						string += keyValues.get(i).getValue() + "\n";
						tag += keyValues.get(i).getKey() + ",";
					}
				}
				keyValueView.setText(string);
				keyValueView.setTag(tag);
				alertDialog.dismiss();
			}
		});
		alertDialog = dialog.show();

	}
}