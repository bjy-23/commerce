package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import com.wondersgroup.commerce.R;

import java.util.ArrayList;
import java.util.List;

public class UnlicensedQueryListFragment extends Fragment {

	private View view;
	private ListView demoList;
	private List<DemoBean> demoBeans = new ArrayList<DemoBean>();
	private DemoAdapter4 adapter;
	private FragmentActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mode_list, container, false);
		activity = (FragmentActivity) getActivity();
		initDemoList();
		setHasOptionsMenu(true);
		return view;
	}

	public void initDemoList() {
		demoList = (ListView) view.findViewById(R.id.demo_list);

		DemoBean demoBean = new DemoBean();
		demoBean.setTextView1("经营者：张三");
		demoBean.setTextView2("经营地址：昆明路138号");
		demoBean.setTextView3("名称：好优惠超市");
		demoBean.setTextView4("发现日期：2014-03-11");

		for (int i = 1; i < 8; i++) {
			demoBeans.add(demoBean);
		}
		adapter = new DemoAdapter4(activity, R.layout.mode_list_item4,
				demoBeans);
		demoList.setAdapter(adapter);
		demoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				UtilForFragment.switchContentWithStack(activity,
						new UnlicensedItemFragment(), R.id.content);

				//

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// // noinspection SimplifiableIfStatement
		// if (id == R.id.register) {
		// Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
		// return true;
		// }
		// if (id == R.id.forget_passwd) {
		// Toast.makeText(LoginActivity.this, "忘记密码",
		// Toast.LENGTH_SHORT).show();
		// return true;
		// }
		if (id == android.R.id.home) {
			UtilForFragment.popBackStack(activity);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
