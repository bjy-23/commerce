package com.wondersgroup.commerce.teamwork.mysupervision;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;
import com.wondersgroup.commerce.teamwork.dailycheck.InfoBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZongheListFragment extends Fragment {

	private View view;
	private ListView DailyCheckList;
	private ZongheAdapter adapter;
	private Dialog progressDialog;
	private AppCompatActivity activity;
	private RootAppcation application;
	private Gson gson = new Gson();
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_RESPONSE_INFO = 4;
	public String flag = "1";
	List<EtpsBean> etpsBeans = new ArrayList<EtpsBean>();
	private String recordId = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mode_list, container, false);
		activity = (AppCompatActivity) getActivity();
		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
		title.setText("监督管理查询列表");
		application = (RootAppcation) activity.getApplication();
		etpsBeans = application.getEtpsBeans();
//		progressDialog = LoadingDialog.createLoadingDialog(getActivity(),
//				"loading");
		initComplanList();

		setHasOptionsMenu(true);
		return view;
	}

	public void initComplanList() {
		DailyCheckList = (ListView) view.findViewById(R.id.demo_list);
//		adapter = new ZongheAdapter(getActivity(), R.layout.mode_list_item1,
//				etpsBeans);
		adapter = new ZongheAdapter(getActivity(),R.layout.mode_list_item1,etpsBeans);
		DailyCheckList.setAdapter(adapter);
		DailyCheckList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				progressDialog.show();
				MyProgressDialog.show(activity);
				flag = etpsBeans.get(position).getCheckType();
				recordId = etpsBeans.get(position).getRecordId();
//				String netAddress = "http://10.1.8.130:9010/consumerw/ws/app_qj/"
				String netAddress = Url.QJ_IN_USE
				+ "getRecordInfo/"
						+ flag
						+ "/"
						+  application.getLoginUserInfo().getDeptId()
						+ "/"
						+ application.getEtpsBeans().get(position)
								.getRecordId();
				Log.i("info########",netAddress);
//				String netAddress = "http://10.1.8.130:9010/consumerw/ws/app_qj/getRecordInfo/1/53000000026/990000000201603170001";
				HttpClientUtil.callWebServiceForGet(netAddress,
						new HttpCallbackListener() {

							@Override
							public void onFinish(String response) {
								Message message = new Message();
								message.what = SHOW_RESPONSE_INFO;
								message.obj = response.toString();
								handler.sendMessage(message);
								Log.e("info", response);
							}

							@Override
							public void onError(Exception e) {
								Message message = new Message();
								message.what = SHOW_ERROR;
								message.obj = e.toString();
								handler.sendMessage(message);

							}
						});

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == android.R.id.home) {
			UtilForFragment.popBackStack(activity);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_ERROR:
//				if (progressDialog.isShowing()) {
//					progressDialog.cancel();
//				}
				MyProgressDialog.dismiss();
				Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;

			case SHOW_RESPONSE_INFO:
//				if (progressDialog.isShowing()) {
//					progressDialog.cancel();
//				}
				MyProgressDialog.dismiss();
				JSONObject caseInfo = null;
				try {
					caseInfo = new JSONObject(msg.obj.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String code = "";
				try {
					code = caseInfo.getString("code");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String result = "";
				try {
					result = caseInfo.getString("result");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// InfoFirstBean infoFirstBean =
				// gson.fromJson(msg.obj.toString(),
				// InfoFirstBean.class);

				if (code.equals("200")) {
					// InfoBean infoBean = infoFirstBean.getResult();
					InfoBean infoBean = gson.fromJson(result, InfoBean.class);
					application.setInfoBean(infoBean);
					application.setRecordId(recordId);
					if (flag.equals("1")) {
						UtilForFragment.switchContentWithStack(activity,
								new DailyCheckInfoFragment(), R.id.content);
					}
					if (flag.equals("2")) {
						UtilForFragment.switchContentWithStack(activity,
								new KeyInfoFragment(), R.id.content);
					}
					if (flag.equals("3")) {
						UtilForFragment.switchContentWithStack(activity,
								new SpecialInfoFragment(), R.id.content);
					}
					if (flag.equals("4")) {
						UtilForFragment.switchContentWithStack(activity,
								new UnlicensedInfoFragment(), R.id.content);
					}
					if (flag.equals("5")) {
						UtilForFragment.switchContentWithStack(activity,
								new EntInfoFragment(), R.id.content);
					}
					if (flag.equals("6")) {
						UtilForFragment.switchContentWithStack(activity,
								new AssociationInfoFragment(), R.id.content);
					}

				} else {
					
					Toast.makeText(getActivity(), "没有记录详情", Toast.LENGTH_SHORT)
							.show();

				}
				break;
			}

		}
	};

}
