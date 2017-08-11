package com.wondersgroup.commerce.teamwork.dailycheck;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.wondersgroup.commerce.R;


public class UnlicensedQueryFragment extends Fragment {
	private AlertDialog alertDialog;
	private AlertDialog.Builder dialog;
	private Button spinnerButton;
	private ListView listView;
	// private TextView spinnerTextView;
	private String[] items = { "转案源", "上报抄告", "责令改正", "其他" };
	private View view;
	private Button queryButton;
	private ActionBarActivity activity;

	// @Override
	// public void onAttach(Activity activity) {
	// Log.e("sign", sign+"");
	// super.onAttach(activity);
	// }

	// @Override
	// public void onResume() {
	// Log.e("sign", sign+"");
	// super.onResume();
	// }

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.unlicensed_query_fragment, null);
		activity = (ActionBarActivity) getActivity();

		listView = new ListView(activity);
		StringAdapter adapter = new StringAdapter(activity,
				R.layout.dialog_list_item, items);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				spinnerButton.setText(items[position]);
				alertDialog.dismiss();

			}
		});
		spinnerButton = (Button) view.findViewById(R.id.spinnerButton);
		spinnerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (dialog == null) {
					dialog = new AlertDialog.Builder(getActivity());
					dialog.setView(listView);
					dialog.setCancelable(true);
					alertDialog = dialog.show();
				} else {
					alertDialog.show();
				}
				/*
				 * areaDialog.setPositiveButton("确定", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface dialog, int
				 * which) {
				 * 
				 * } });
				 */

				// 设置dialogList的长宽
				WindowManager windowManager = (WindowManager) getActivity()
						.getSystemService(Context.WINDOW_SERVICE);
				WindowManager.LayoutParams layoutParams = alertDialog
						.getWindow().getAttributes();
				layoutParams.width = layoutParams.WRAP_CONTENT;
				layoutParams.height = layoutParams.WRAP_CONTENT;
				;
				alertDialog.getWindow().setAttributes(layoutParams);

			}
		});
		// StringAdapter aa = new StringAdapter(getActivity(),
		// android.R.layout.simple_spinner_item, items); //
		// 第二个参数表示spinner没有展开前的UI类型
		// spinner.setAdapter(aa); // 之前已经通过Spinner spin = (Spinner)
		// // findViewById(R.id.spinner);来获取spin对象
		// aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		//
		// @Override
		// public void onItemSelected(AdapterView<?> parent, View view,
		// int position, long id) {
		// // spinnerTextView.setText(items[position]);
		//
		// }
		//
		// @Override
		// public void onNothingSelected(AdapterView<?> parent) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

		queryButton = (Button) view.findViewById(R.id.queryButton);
		queryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UtilForFragment.switchContentWithStack(activity,
						new UnlicensedQueryListFragment(), R.id.content);

			}
		});
		return view;
	}

}
