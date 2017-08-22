package com.wondersgroup.commerce.teamwork.mysupervision;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.teamwork.dailycheck.BookBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntInfoFragment extends Fragment {

	private View view;
	// private LinearLayoutForListView dailyList;
	private List<String> strings = new ArrayList<String>();
//	private EtpsInfoAdapter adapter;
	private Dialog progressDialog;
	private AppCompatActivity activity;
	// private List<CnApp> complainCnApps = new ArrayList<CnApp>();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_ERROR_DIALOG = 4;
	private RootAppcation application;
	ExampleAdapter expandalbeAdapter;

	private TextView checkDate;
	private TextView checkedName;
	private TextView checkedNumber;
	private TextView inspector;
	private TextView inspection;
	private TextView submitUser;
	private TextView inspectUser;
	private TextView memo;
	private TextView gpsInfo;
	// private TextView serviceBusinessStatus;
	// private TextView serviceFinancialSystem;
	// private TextView serviceAdBehavior;
	// private TextView serviceInnerMansys;
	// private TextView serviceBrandReguse;
	// private TextView serviceActivityItems;
	private TextView serviceActivityDetail;
	private TextView serviceProblems;
	private TextView serviceSuggestions;

	// new
	// private TextView trdScope;
	// private TextView tradeScope;
	// private TextView changeRecord;

	// new
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Dialog showimageAlertDialog;
	private View photoShowView;
	public static final int SHOW_IMG_RESPONSE = 5;
	private Gson gson = new Gson();

	// Map<String, String> serviceMatterMap;
	// Map<String, String> brandRegUseMap;
	// Map<String, String> adBehaviorMap;
	// Map<String, String> businessStatusMap;
	// Map<String, String> financialSystemMap;
	// Map<String, String> innerManageSystemMap;
	List<BookBean> users;

	// new
	private TextView inspectionText;
	private Button confirmButton;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.my_q_entservice_info, container, false);
		activity = (AppCompatActivity) getActivity();
		application = (RootAppcation) activity.getApplication();
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setTitle("");

		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
		title.setText(application.getInfoBean().getEtpsInfoVo()
				.get("etpsName"));

		progressDialog = LoadingDialog.createLoadingDialog(getActivity(),
				"loading");

		// new
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		imageView2 = (ImageView) view.findViewById(R.id.imageView2);
		imageView3 = (ImageView) view.findViewById(R.id.imageView3);
		imageView1.setBackground(null);
		imageView2.setBackground(null);
		imageView3.setBackground(null);
		 String imageAddress = Url.QJ_IN_USE + "fetchImg/"
				+ application.getRecordId();
		HttpClientUtil.callWebServiceForGet(imageAddress,
				new HttpCallbackListener() {

					@Override
					public void onFinish(String response) {
						Message message = new Message();
						message.what = SHOW_IMG_RESPONSE;
						message.obj = response.toString();
						handler.sendMessage(message);
					}

					@Override
					public void onError(Exception e) {
//						 Message message = new Message();
//						 message.what = SHOW_ERROR;
//						 message.obj = e.toString();
//						 handler.sendMessage(message);

					}
				});

		initView();

		// initcomplainList();
		setHasOptionsMenu(true);
		return view;
	}

	private void initView() {

		TextView etpsName = (TextView) view.findViewById(R.id.etpsName);
		etpsName.setText(application.getInfoBean().getEtpsInfoVo()
				.get("etpsName"));

		// serviceActivityItems = (TextView) view
		// .findViewById(R.id.serviceActivityItems);
		// serviceBrandReguse = (TextView) view
		// .findViewById(R.id.serviceBrandReguse);
		// serviceAdBehavior = (TextView) view
		// .findViewById(R.id.serviceAdBehavior);
		// serviceBusinessStatus = (TextView) view
		// .findViewById(R.id.serviceBusinessStatus);
		// serviceFinancialSystem = (TextView) view
		// .findViewById(R.id.serviceFinancialSystem);
		// serviceInnerMansys = (TextView) view
		// .findViewById(R.id.serviceInnerMansys);
		inspector = (TextView) view.findViewById(R.id.inspector);

		// serviceActivityItems.setTag("");
		// serviceBrandReguse.setTag("");
		// serviceAdBehavior.setTag("");
		// serviceBusinessStatus.setTag("");
		// serviceFinancialSystem.setTag("");
		// serviceInnerMansys.setTag("");
		inspector.setTag("");

		// serviceMatterMap = application.getInfoBean().getServiceMatter();
		// brandRegUseMap = application.getInfoBean().getBrandRegUse();
		// adBehaviorMap = application.getInfoBean().getAdBehavior();
		// businessStatusMap = application.getInfoBean().getBusinessStatus();
		// financialSystemMap = application.getInfoBean().getFinancialSystem();
		// innerManageSystemMap =
		// application.getInfoBean().getInnerManageSystem();
		users = application.getInfoBean().getUserList();

		// serviceActivityItems.setTag(application.getInfoBean()
		// .getAppRecordContent().getServiceActivityItems());
		// serviceBrandReguse.setTag(application.getInfoBean()
		// .getAppRecordContent().getServiceBrandReguse());
		// serviceAdBehavior.setTag(application.getInfoBean()
		// .getAppRecordContent().getServiceAdBehavior());
		// serviceBusinessStatus.setTag(application.getInfoBean()
		// .getAppRecordContent().getServiceBusinessStatus());
		// serviceFinancialSystem.setTag(application.getInfoBean()
		// .getAppRecordContent().getServiceFinancialSystem());
		// serviceInnerMansys.setTag(application.getInfoBean()
		// .getAppRecordContent().getServiceInnerMansys());
		inspector.setTag(application.getInfoBean().getAppCheckInfo()
				.getInspector());

		String[] inspectorStrings = inspector.getTag().toString().split(",");
		String inspectorString = " ";
		for (int i = 0; i < inspectorStrings.length; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).getUserId().equals(inspectorStrings[i])) {
					inspectorString += users.get(j).getName() + ",";
				}

			}

		}
		inspectorString = inspectorString.substring(0,
				inspectorString.length() - 1);
		inspector.setText(inspectorString);

		// String[] matterStrings = serviceActivityItems.getTag().toString()
		// .split(",");
		// String matterString = " ";
		// for (int i = 0; i < matterStrings.length; i++) {
		// if (serviceMatterMap.get(matterStrings[i]) != null) {
		// matterString += serviceMatterMap.get(matterStrings[i]) + ",";
		// }
		//
		// }
		// matterString=matterString.substring(0,matterString.length()-1);
		// serviceActivityItems.setText(matterString);
		//
		// String[] brandStrings = serviceBrandReguse.getTag().toString()
		// .split(",");
		// String brandString = " ";
		// for (int i = 0; i < brandStrings.length; i++) {
		// if (brandRegUseMap.get(brandStrings[i]) != null) {
		// brandString += brandRegUseMap.get(brandStrings[i]) + ",";
		// }
		//
		// }
		// brandString=brandString.substring(0,brandString.length()-1);
		// serviceBrandReguse.setText(brandString);
		//
		// String[] adStrings =
		// serviceAdBehavior.getTag().toString().split(",");
		// String adString = " ";
		// for (int i = 0; i < adStrings.length; i++) {
		// if (adBehaviorMap.get(adStrings[i]) != null) {
		// adString += adBehaviorMap.get(adStrings[i]) + ",";
		// }
		//
		// }
		// adString=adString.substring(0,adString.length()-1);
		// serviceAdBehavior.setText(adString);
		//
		// String[] businessStrings = serviceBusinessStatus.getTag().toString()
		// .split(",");
		// String businissString = " ";
		// for (int i = 0; i < businessStrings.length; i++) {
		// if (businessStatusMap.get(businessStrings[i]) != null) {
		// businissString += businessStatusMap.get(businessStrings[i])
		// + ",";
		// }
		//
		// }
		// businissString=businissString.substring(0,businissString.length()-1);
		// serviceBusinessStatus.setText(businissString);
		//
		// String[] finacialStrings = serviceFinancialSystem.getTag().toString()
		// .split(",");
		// String finacialString = " ";
		// for (int i = 0; i < finacialStrings.length; i++) {
		// if (financialSystemMap.get(finacialStrings[i]) != null) {
		// finacialString += financialSystemMap.get(finacialStrings[i])
		// + ",";
		// }
		//
		// }
		// finacialString=finacialString.substring(0,finacialString.length()-1);
		// serviceFinancialSystem.setText(finacialString);
		//
		// String[] innerStrings = serviceInnerMansys.getTag().toString()
		// .split(",");
		// String innerString = " ";
		// for (int i = 0; i < innerStrings.length; i++) {
		// if (innerManageSystemMap.get(innerStrings[i]) != null) {
		// innerString += innerManageSystemMap.get(innerStrings[i]) + ",";
		// }
		//
		// }
		// innerString=innerString.substring(0,innerString.length()-1);
		// serviceInnerMansys.setText(innerString);

		// new
		// changeRecord = (TextView) view.findViewById(R.id.changeRecord);
		// MapToListUtil mapToListUtil = new MapToListUtil(application
		// .getInfoBean().getAppEntInfoVo());
		// List<EtpsInfoBean> keyValues = mapToListUtil.mapToEtpsInfoS();
		// String string = "";
		// for (int i = 0; i < keyValues.size(); i++) {
		// if (!keyValues.get(i).getValue().equals("EOF")) {
		// string += keyValues.get(i).getName() + ":"
		// + keyValues.get(i).getValue() + "\n";
		// }
		//
		// }
		// string=string.substring(0,innerString.length()-1);
		// changeRecord.setText(string);
		// trdScope = (TextView) view.findViewById(R.id.trdScope);
		// trdScope.setText(application.getInfoBean().getTrdScopeChg());
		// tradeScope = (TextView) view.findViewById(R.id.tradeScope);
		// tradeScope.setText(application.getInfoBean().getTrdScope());

		serviceActivityDetail = (TextView) view
				.findViewById(R.id.serviceActivityDetail);
		serviceProblems = (TextView) view.findViewById(R.id.serviceProblems);
		serviceSuggestions = (TextView) view
				.findViewById(R.id.serviceSuggestions);
		checkDate = (TextView) view.findViewById(R.id.checkDate);
		checkedName = (TextView) view.findViewById(R.id.checkedName);
		checkedNumber = (TextView) view.findViewById(R.id.checkedNumber);
		inspector = (TextView) view.findViewById(R.id.inspector);
		inspection = (TextView) view.findViewById(R.id.inspection);
		submitUser = (TextView) view.findViewById(R.id.submitUser);
		memo = (TextView) view.findViewById(R.id.memo);
		gpsInfo = (TextView) view.findViewById(R.id.gpsInfo);
		/* appRecordContent */
		// problem.setText(application.getInfoBean().getAppRecordContent()
		// .getProblem());

		serviceSuggestions.setText(application.getInfoBean()
				.getAppRecordContent().getServiceSuggestions());
		serviceProblems.setText(application.getInfoBean().getAppRecordContent()
				.getServiceProblems());
		serviceActivityDetail.setText(application.getInfoBean()
				.getAppRecordContent().getServiceActivityDetail());
		checkDate.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckDate());
		checkedName.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckedName());
		checkedNumber.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckedNumber());
		submitUser.setText(application.getInfoBean().getAppCheckInfo()
				.getSubmitUser());
		memo.setText(application.getInfoBean().getAppCheckInfo().getMemo());
		gpsInfo.setText(application.getInfoBean().getAppCheckInfo()
				.getGpsInfo());

		// new
		inspectUser = (TextView) view.findViewById(R.id.inspectUser);
		inspectUser.setText(application.getInfoBean().getAppCheckInfo()
				.getInspectUser());
		inspectionText = (TextView) view.findViewById(R.id.inspection_text);
		confirmButton = (Button) view.findViewById(R.id.confirmButton);
		if (application.getInfoBean().getAppCheckInfo().getInspection() != null) {
			inspectionText.setVisibility(View.VISIBLE);
			inspectionText.setText(application.getInfoBean().getAppCheckInfo()
					.getInspection());
			inspection.setVisibility(View.GONE);
			confirmButton.setVisibility(View.GONE);
		}

		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(inspection.getText())) {
					Toast.makeText(activity, "请填写督察意见", Toast.LENGTH_SHORT)
							.show();
				} else {
					progressDialog.show();
					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("inspectUser", application
								.getLoginUserInfo().getUserId());
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						jsonObject.put("inspection", inspection.getText()
								.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						jsonObject.put("recordId", application.getInfoBean()
								.getAppRecordContent().getRecordId());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String netAddress = Url.QJ_IN_USE + "updateCheckInfo";
					HttpClientUtil.callWebService(jsonObject.toString(),
							netAddress, new HttpCallbackListener() {

								@Override
								public void onFinish(String response) {
									Message message = new Message();
									message.what = SHOW_RESPONSE;
									message.obj = response.toString();
									handler.sendMessage(message);
								}

								@Override
								public void onError(Exception e) {
									// Message message = new Message();
									// message.what = SHOW_ERROR;
									// message.obj = e.toString();
									// handler.sendMessage(message);

								}
							});
				}

			}
		});

	}

	// public void initcomplainList() {
	// if (application.getInfoBean() != null) {
	// LinkedHashMap<String, String> map = application.getInfoBean()
	// .getEtpsInfoVo();
	// MapToListUtil mapToListUtil = new MapToListUtil(map);
	// List<EtpsInfoChangeBean> etpsInfoChangeBeans = mapToListUtil
	// .mapToEtpsChangeInfo();
	//
	// dailyList = (LinearLayoutForListView) view
	// .findViewById(R.id.demo_list);
	// adapter = new EtpsInfoAdapter(activity,
	// R.layout.radio_buton_info_list_item, etpsInfoChangeBeans);
	// dailyList.setAdapter(adapter);
	// }
	// }

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
					Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
					if (progressDialog.isShowing()) {
						progressDialog.cancel();
					}

				} else {
					if (progressDialog.isShowing()) {
						progressDialog.cancel();
					}
					Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_SHORT)
							.show();
				}
				break;

			case SHOW_IMG_RESPONSE:
				JSONObject imgJson = null;
				try {
					imgJson = new JSONObject(msg.obj.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String imgCode = "";
				try {
					imgCode = imgJson.getString("code");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String imgResult = null;
				try {
					imgResult = imgJson.getString("result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImgFirstBean imgFirstBean = gson.fromJson(msg.obj.toString(),
						ImgFirstBean.class);
				if (imgFirstBean.getCode() == 200) {

					int i = 0;
					for (CnUpload pic : imgFirstBean.getResult()) {
						if (i == 0) {
							String raw = pic.getFilePath();
							bitmap1 = BitMapUtil.base64ToBitmap(raw);
							imageView1.setImageBitmap(bitmap1);
							imageView1
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											LayoutInflater layoutInflater = (LayoutInflater) getActivity()
													.getSystemService(
															Context.LAYOUT_INFLATER_SERVICE);
											photoShowView = layoutInflater
													.inflate(
															R.layout.photo_view,
															null);
											ImageView imageView = (ImageView) photoShowView
													.findViewById(R.id.show_photo);
											imageView
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {
															showimageAlertDialog
																	.cancel();

														}
													});
											imageView.setImageBitmap(bitmap1);
											showimageAlertDialog = new Dialog(
													getActivity(),
													android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
											showimageAlertDialog
													.setContentView(photoShowView);
											showimageAlertDialog.show();
										}
									});
						}
						if (i == 1) {
							String raw = pic.getFilePath();
							bitmap2 = BitMapUtil.base64ToBitmap(raw);
							imageView2.setImageBitmap(bitmap2);
							imageView2
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											LayoutInflater layoutInflater = (LayoutInflater) getActivity()
													.getSystemService(
															Context.LAYOUT_INFLATER_SERVICE);
											photoShowView = layoutInflater
													.inflate(
															R.layout.photo_view,
															null);
											ImageView imageView = (ImageView) photoShowView
													.findViewById(R.id.show_photo);
											imageView
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {
															showimageAlertDialog
																	.cancel();

														}
													});
											imageView.setImageBitmap(bitmap2);
											showimageAlertDialog = new Dialog(
													getActivity(),
													android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
											showimageAlertDialog
													.setContentView(photoShowView);
											showimageAlertDialog.show();
										}
									});
						}
						if (i == 2) {
							String raw = pic.getFilePath();
							bitmap3 = BitMapUtil.base64ToBitmap(raw);
							imageView3.setImageBitmap(bitmap3);
							imageView3
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											LayoutInflater layoutInflater = (LayoutInflater) getActivity()
													.getSystemService(
															Context.LAYOUT_INFLATER_SERVICE);
											photoShowView = layoutInflater
													.inflate(
															R.layout.photo_view,
															null);
											ImageView imageView = (ImageView) photoShowView
													.findViewById(R.id.show_photo);
											imageView
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {
															showimageAlertDialog
																	.cancel();

														}
													});
											imageView.setImageBitmap(bitmap3);
											showimageAlertDialog = new Dialog(
													getActivity(),
													android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
											showimageAlertDialog
													.setContentView(photoShowView);
											showimageAlertDialog.show();
										}
									});
						}
						i++;
					}

				}
				break;

			case SHOW_ERROR:
				if (progressDialog.isShowing()) {
					progressDialog.cancel();
				}

				Toast.makeText(getActivity(),"网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	};

}