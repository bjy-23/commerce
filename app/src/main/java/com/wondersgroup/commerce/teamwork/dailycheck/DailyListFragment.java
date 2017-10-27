package com.wondersgroup.commerce.teamwork.dailycheck;

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
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class DailyListFragment extends Fragment {

	private View view;
	private ListView DailyCheckList;
	private EtpsOneAdapter adapter;
	private AppCompatActivity activity;
	private RootAppcation application;
	private Gson gson = new Gson();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
    private TotalLoginBean loginBean;
	private boolean isLoaded;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mode_list, container, false);
		activity = (AppCompatActivity) getActivity();
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setTitle("");
		application = (RootAppcation) activity.getApplication();
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
		TextView title = (TextView)activity.findViewById(R.id.toolbar_title);
		title.setText("日常检查列表");

//		if (!isLoaded)
			initCheckList();
		setHasOptionsMenu(true);
		return view;
	}

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
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

				application.setCaseId(application.getEtpsBeans().get(position)
						.getEtpsId());
				// 河北接口
				String hebeiAddress = Url.QJ_IN_USE + "doCheck/"
						+ application.getEtpsBeans().get(position).getEtpsId()
						+ "/1/" + loginBean.getResult().getDeptId();
				Log.e("address", hebeiAddress);
				HttpClientUtil.callWebServiceForGet(hebeiAddress,
						new HttpCallbackListener() {

							@Override
							public void onFinish(String response) {
                                isLoaded = true;
								Log.e("response", response);
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

					UtilForFragment.switchContentWithStack(activity,
							new DailyFragment(), R.id.content);

				} else {
					Toast.makeText(getActivity(), "未查询到该记录详情",
							Toast.LENGTH_SHORT).show();
				}
				break;

			case SHOW_ERROR:
				Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	};

}
