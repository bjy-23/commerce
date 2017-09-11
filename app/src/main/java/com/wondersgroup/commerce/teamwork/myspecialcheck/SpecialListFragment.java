package com.wondersgroup.commerce.teamwork.myspecialcheck;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsOneAdapter;
import com.wondersgroup.commerce.teamwork.dailycheck.BigBean;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpCallbackListener;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpClientUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.Url;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;
import com.wondersgroup.commerce.teamwork.mysupervision.CnApp;
import com.wondersgroup.commerce.teamwork.mysupervision.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpecialListFragment extends Fragment {

	private View view;
	private ListView DailyCheckList;
	private List<CnApp> cnApps = new ArrayList<CnApp>();
	private EtpsOneAdapter adapter;
	private Dialog progressDialog;
	private AppCompatActivity activity;
	private RootAppcation application;
	private Gson gson = new Gson();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	private TotalLoginBean loginBean;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mode_list, container, false);
		activity = (AppCompatActivity) getActivity();
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		TextView title = (TextView)activity.findViewById(R.id.toolbar_title);
		title.setText("专项整治列表");
		application = (RootAppcation) activity.getApplication();
		progressDialog = LoadingDialog.createLoadingDialog(getActivity(),
				"loading");
		initCheckList();
		setHasOptionsMenu(true);
		return view;
	}

	public void initCheckList() {
		DailyCheckList = (ListView) view.findViewById(R.id.demo_list);
		adapter = new EtpsOneAdapter(getActivity(), R.layout.mode_list_item1,
				application.getEtpsBeans());
		DailyCheckList.setAdapter(adapter);
		DailyCheckList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				progressDialog.show();
				application.setCaseId(application.getEtpsBeans().get(position)
						.getEtpsId());
				// 河北接口
				String hebeiAddress = Url.QJ_IN_USE + "doCheck/"
						+ application.getEtpsBeans().get(position).getEtpsId()
						+ "/3/" + loginBean.getResult().getDeptId();
                Log.e("hebeiAddress", hebeiAddress);
				HttpClientUtil.callWebServiceForGet(hebeiAddress,
						new HttpCallbackListener() {

							@Override
							public void onFinish(String response) {
								Message message = new Message();
								message.what = SHOW_RESPONSE;
								message.obj = response.toString();
								handler.sendMessage(message);
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
			case SHOW_RESPONSE:
				JSONObject caseInfo = null;
				try {
					caseInfo = new JSONObject(msg.obj.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String code = "";
				try {
					code = caseInfo.getString("code");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String result = null;
				try {
					result = caseInfo.getString("result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (code.equals("200")) {
					BigBean bigBean = gson.fromJson(result, BigBean.class);
					application.setBigBean(bigBean);
					progressDialog.cancel();
					UtilForFragment.switchContentWithStack(activity,
							new SpecialFragment(), R.id.content);
				} else {
					progressDialog.cancel();
					Toast.makeText(getActivity(), "未查询到该记录详情",
							Toast.LENGTH_SHORT).show();
				}
				break;

			case SHOW_ERROR:
				progressDialog.cancel();
				Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	};

}
