package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UnlicensedAddFragment extends Fragment {

	private View view;
	private TextView tvAdd, tvTitle;
	private AppCompatActivity activity;
	private RootAppcation application;
	private Gson gson = new Gson();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;

	// selfList
	public static final int SHOW_RESPONSE_SELF = 3;
	public static final int SHOW_RESPONSE_INFO = 4;
	private List<EtpsBean> etpsBeans = new ArrayList<EtpsBean>();
	private ListView listView;
	private String recordId = "";
	private TotalLoginBean loginBean;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		loginBean = Hawk.get(Constants.LOGIN_BEAN);
		view = inflater.inflate(R.layout.mode_addandlist, null);
		activity = (AppCompatActivity) getActivity();
		application = (RootAppcation) activity.getApplication();

        ImageView imgBack = (ImageView) view.findViewById(R.id.img_back);
        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UnlicensedAddFragment.this.getFragmentManager().popBackStack();
                getActivity().finish();
            }
        });
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(Constants.WZJY_NAME);
		tvAdd = (TextView) view.findViewById(R.id.tv_save);
        tvAdd.setText("添加");
        tvAdd.setVisibility(View.VISIBLE);
        tvAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 河北接口
				String hebeiAddress = Url.QJ_IN_USE + "doCheck/%20/4/"
						+ loginBean.getResult().getDeptId();
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
		initSelfList();
		setHasOptionsMenu(true);
		return view;
	}

	private void initSelfList() {

		String address = Url.QJ_IN_USE + "searchList";
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("submitUser", loginBean.getResult().getUserId());
			jsonObject.put("checkType", "4");
			jsonObject.put("organId", loginBean.getResult().getOrganId());
			jsonObject.put("tmpFlag", 1);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpClientUtil.callWebService(jsonObject.toString(), address,
				new HttpCallbackListener() {

					@Override
					public void onFinish(String response) {
						Message message = new Message();
						message.what = SHOW_RESPONSE_SELF;
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
		listView = (ListView) view.findViewById(R.id.demo_list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				MyProgressDialog.show(activity);
				recordId = etpsBeans.get(position).getRecordId();
				String netAddress = Url.QJ_IN_USE + "getRecordInfo/4/"
						+ loginBean.getResult().getDeptId() + "/"
						+ recordId;
				Log.e("netAddress", netAddress);
				HttpClientUtil.callWebServiceForGet(netAddress,
						new HttpCallbackListener() {

							@Override
							public void onFinish(String response) {
								MyProgressDialog.dismiss();
								Message message = new Message();
								message.what = SHOW_RESPONSE_INFO;
								message.obj = response.toString();
								handler.sendMessage(message);
								Log.e("info", response);
							}

							@Override
							public void onError(Exception e) {
								MyProgressDialog.dismiss();
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
			activity.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				FirstBean firstBean = gson.fromJson(msg.obj.toString(),
						FirstBean.class);
				if (firstBean.getCode() == 200) {
					// BigBean bigBean = gson.fromJson(result, BigBean.class);
//					application.setBigBean(firstBean.getResult());

					UtilForFragment.switchContentWithStack(activity,
							new UnlicensedFragment(), R.id.parent);

				} else {

					Toast.makeText(getActivity(), "未查询到该记录详情",
							Toast.LENGTH_SHORT).show();
				}
				break;

			case SHOW_ERROR:

				Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;

			case SHOW_RESPONSE_SELF:

				EtpsFirstBean firstSelfBean = gson.fromJson(msg.obj.toString(),
						EtpsFirstBean.class);

				if (firstSelfBean.getCode() == 200) {

					etpsBeans = firstSelfBean.getResult();

					EtpsAdapter adapter = new EtpsAdapter(getActivity(),
							R.layout.mode_list_item1, etpsBeans);
					listView.setAdapter(adapter);

				}
				break;

			case SHOW_RESPONSE_INFO:

				InfoFirstBean infoFirstBean = gson.fromJson(msg.obj.toString(),
						InfoFirstBean.class);

				if (infoFirstBean.getCode() == 200) {
					application.setRecordId(recordId);
					UtilForFragment.switchContentWithStack(activity,
							new UnlicensedTempFragment(), R.id.content);

				} else {
					Toast.makeText(getActivity(), "没有记录详情", Toast.LENGTH_SHORT)
							.show();

				}
				break;
			}

		}
	};
}
