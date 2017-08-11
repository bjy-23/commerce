package com.wondersgroup.commerce.teamwork.mysupervision;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.List;

public class ShowSingleDialogListListener implements OnClickListener {
	private AlertDialog alertDialog;
	private AlertDialog.Builder dialog;
	private ListView keyValueListView;
	private KeyValueAdapter adapter;
	private List<KeyValue> keyValues;
	private Context context;
	private TextView keyValueView;

	public ShowSingleDialogListListener(List<KeyValue> keyValues, Context context,
										TextView keyValueView) {
		super();
		this.keyValues = keyValues;
		this.context = context;
		this.keyValueView = keyValueView;
	}

	@Override
	public void onClick(View v) {
		keyValueListView = new ListView(context);
		adapter = new KeyValueAdapter(context, R.layout.dialog_list_item,
				keyValues);
		keyValueListView.setAdapter(adapter);
		keyValueListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				keyValueView.setText(keyValues.get(position).getValue());
				keyValueView.setTag(keyValues.get(position).getKey());
				alertDialog.dismiss();

			}
		});

		dialog = new AlertDialog.Builder(context);
		dialog.setView(keyValueListView);
		alertDialog = dialog.show();

	}
}
