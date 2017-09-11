package com.wondersgroup.commerce.teamwork.dailycheck;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.utils.CheckUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DailyCheckTempFragment extends Fragment {
	private int imageFlag = 0;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private List<ImageView> imageViews;
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Uri imageUri;
	private Dialog showimageAlertDialog;
	private AlertDialog imageAlertDialog;
	private View photoChooseView;
	private View photoShowView;
	private Boolean boolList[] = { false, false, false };

	// public LocationClient mLocationClient = null;
	private View view;
	private LinearLayoutForListView dailyList;
	private List<String> strings = new ArrayList<String>();
	private EtpsInfoRadioButtonAdapter adapter;
	private Dialog progressDialog;
	private AppCompatActivity activity;
	// private List<CnApp> complainCnApps = new ArrayList<CnApp>();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_ERROR_DIALOG = 4;
	public static final int SHOW_IMG_RESPONSE = 5;

	private CheckBox checkBox;
	private AnimatedExpandableListView expandableListView;
	private RootAppcation application;
	// footView
	private View headView;
	private TextView trdScope;
	private TextView tradeScope;
	private TextView changeRecord;

	// footview
	private View footView;
	private TextView deal;
	private TextView checkedName;
	private TextView checkedNumber;
	private TextView inspector;
	private TextView problem;
	// private TextView gpsInfo;
	private TextView memo;
	private Button tempButton;
	private Button realButton;
	private SaveCheckBean saveCheckBean = new SaveCheckBean();
	private Gson gson = new Gson();

	private List<Father> newFathers = new ArrayList<Father>();
	private List<AppRecordDetail> tempDetails = new ArrayList<AppRecordDetail>();
	ExampleAdapter expandalbeAdapter;
	private List<Father> fathers = new ArrayList<Father>();

	private Map<String, String> dealMap;
	private List<BookBean> users;

	private  boolean flag = false;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mode_expandablelist, container, false);
		activity = (AppCompatActivity) getActivity();
		application = (RootAppcation) activity.getApplication();
//		application.setProblemName(application.getInfoBean()
//				.getAppRecordContentVo().getProblemName());
//		application.setProblem(application.getInfoBean()
//				.getAppRecordContentVo().getProblem());
//
//		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
//		title.setText(application.getInfoBean().getEtpsInfoVo().get("etpsName"));

		headView = inflater.inflate(R.layout.q_dailycheck_up, null);
		footView = inflater.inflate(R.layout.q_dailycheck_down, null);

		TextView all_open_close = (TextView)headView.findViewById(R.id.all_open_close);

		all_open_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag){
					for (int i = 0; i < expandalbeAdapter.getGroupCount(); i++) {
						expandableListView.collapseGroup(i);
					}
				}else {
					for (int i = 0; i < expandalbeAdapter.getGroupCount(); i++) {
						expandableListView.expandGroup(i);
					}
				}
				flag = !flag;
			}
		});

		imageView1 = (ImageView) footView.findViewById(R.id.imageView1);
		imageView2 = (ImageView) footView.findViewById(R.id.imageView2);
		imageView3 = (ImageView) footView.findViewById(R.id.imageView3);

		imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				imageFlag = 1;
				if (boolList[0]) {
					showCleanDialog();
				} else {

					getBitmap();
				}

			}

		});
		imageView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				imageFlag = 2;
				if (boolList[1]) {
					showCleanDialog();
				} else {

					getBitmap();
				}

			}

		});
		imageView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				imageFlag = 3;
				if (boolList[2]) {
					showCleanDialog();
				} else {

					getBitmap();
				}

			}

		});
		// imageView1.setBackground(null);
		// imageView2.setBackground(null);
		// imageView3.setBackground(null);

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
						// Message message = new Message();
						// message.what = SHOW_ERROR;
						// message.obj = e.toString();
						// handler.sendMessage(message);

					}
				});

		initView();
		initKeyValue();
		initExpandableList();
		initCheckList();
		setHasOptionsMenu(true);
		return view;
	}

	private void initExpandableList() {

//		tempDetails = application.getInfoBean().getAppRecordDetails();
//
//		fathers = application.getInfoBean().getCheckMatters();
		newFathers = new ArrayList<Father>();
		for (int i = 0; i < fathers.size(); i++) {
			if (fathers.get(i).getParentMatterId() == null) {
				List<Child> childs = new ArrayList<Child>();
				Father newFather = fathers.get(i);
				newFather.setChildMatter(childs);
				newFathers.add(newFather);
			} else {
				int size = newFathers.size();
				Child child = new Child();
				child.setCheckType(fathers.get(i).getCheckType());
				child.setMatterId(fathers.get(i).getMatterId());
				child.setMatterName(fathers.get(i).getMatterName());
				for (int j = 0; j < tempDetails.size(); j++) {
					if (fathers.get(i).getMatterId()
							.equals(tempDetails.get(j).getMatterId())) {
						child.setChildFlag(Integer.parseInt(tempDetails.get(j)
								.getCheckResult()));
					}
				}

				newFathers.get(size - 1).getChildMatter().add(child);
			}
		}
		expandalbeAdapter = new ExampleAdapter(getActivity()
				.getLayoutInflater(), newFathers);

		expandableListView.addFooterView(footView);
		expandableListView.addHeaderView(headView);
		expandableListView.setAdapter(expandalbeAdapter);
		// initHeight();
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (expandableListView.isGroupExpanded(groupPosition)) {
					expandableListView.collapseGroupWithAnimation(groupPosition);

				} else {
					expandableListView.expandGroupWithAnimation(groupPosition);
				}

				for (int i = 0; i < expandalbeAdapter.getGroupCount(); i++) {
					if (groupPosition != i) {
						expandableListView.collapseGroup(i);
					}
				}




				return true;
			}
		});
	}

	private void initView() {
		deal = (TextView) footView.findViewById(R.id.deal);
		inspector = (TextView) footView.findViewById(R.id.inspector);
//		dealMap = application.getInfoBean().getDeal();
//		users = application.getInfoBean().getUserList();
//		deal.setTag(application.getInfoBean().getAppRecordContent()
//				.getTreatment());
//		inspector.setTag(application.getInfoBean().getAppCheckInfo()
//				.getInspector());
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

		String[] dealStrings = deal.getTag().toString().split(",");
		String dealString = " ";
		for (int i = 0; i < dealStrings.length; i++) {
			if (dealMap.get(dealStrings[i]) != null) {
				dealString += dealMap.get(dealStrings[i]) + ",";
			}

		}
		dealString = dealString.substring(0, dealString.length() - 1);
		deal.setText(dealString);

		problem = (TextView) footView.findViewById(R.id.problem);
		problem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, WebViewActivity.class);
				startActivityForResult(intent, 10);

			}
		});
		// gpsInfo = (TextView) footView.findViewById(R.id.gpsInfo);
		expandableListView = (AnimatedExpandableListView) view
				.findViewById(R.id.doubleList);
		changeRecord = (TextView) footView.findViewById(R.id.changeRecord);
//		MapToListUtil mapToListUtil = new MapToListUtil(application
//				.getInfoBean().getAppEntInfoVo());
//		List<EtpsInfoBean> keyValues = mapToListUtil.mapToEtpsInfoS();
		String string = "";
//		for (int i = 0; i < keyValues.size(); i++) {
//			if (!keyValues.get(i).getValue().equals("EOF")) {
//				string += keyValues.get(i).getName() + ":"
//						+ keyValues.get(i).getValue() + "\n";
//			}
//
//		}

		// String string = textView.getText().toString();
		// string += etpsInfoBeans.get(position).getName() + ":"
		// + editText.getText().toString() + "\n";

		changeRecord.setText(string);

		trdScope = (TextView) footView.findViewById(R.id.trdScope);
//		trdScope.setText(application.getInfoBean().getTrdScopeChg());
		tradeScope = (TextView) footView.findViewById(R.id.tradeScope);
//		tradeScope.setText(application.getInfoBean().getTrdScope());
		tempButton = (Button) footView.findViewById(R.id.tempButton);
		realButton = (Button) footView.findViewById(R.id.realButton);
		checkedName = (TextView) footView.findViewById(R.id.checkedName);
		checkedNumber = (TextView) footView.findViewById(R.id.checkedNumber);
		memo = (TextView) footView.findViewById(R.id.memo);
		CheckUtil.limitCheckMaxCount(memo,50);
		CheckUtil.limitCheckMaxCount(checkedName,50);
		CheckUtil.limitCheckMaxCount(trdScope, 50);
		CheckUtil.checkInputPhone(checkedNumber);
//		checkedName.setText(application.getInfoBean().getAppCheckInfo()
//				.getCheckedName());
//		checkedNumber.setText(application.getInfoBean().getAppCheckInfo()
//				.getCheckedNumber());
//		memo.setText(application.getInfoBean().getAppCheckInfo().getMemo());
		// gpsInfo.setText(application.getInfoBean().getAppCheckInfo()
		// .getGpsInfo());
		// problem.setText(application.getInfoBean().getAppRecordContent()
		// .getProblem());

		tempButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkedNumber.getError() == null) {
					progressDialog.show();
					List<AppRecordDetail> appRecordDetails = new ArrayList<AppRecordDetail>();
					for (int i = 0; i < newFathers.size(); i++) {
						List<Child> childs = newFathers.get(i).getChildMatter();
						for (int j = 0; j < childs.size(); j++) {
							AppRecordDetail appRecordDetail = new AppRecordDetail();
							appRecordDetail.setCheckResult(childs.get(j)
									.getChildFlag() + "");
							appRecordDetail
									.setMatterId(childs.get(j).getMatterId());
							appRecordDetails.add(appRecordDetail);
						}

					}

					// new
					saveCheckBean.getAppRecordContent().setProblem(
							problem.getTag().toString());
//					saveCheckBean.getAppCheckInfo().setSubmitUser(
//							application.getLoginUserInfo().getUserId());
					saveCheckBean.getAppCheckInfo().setRecordId(
							application.getRecordId());
					saveCheckBean.setTrdScope(trdScope.getText().toString());

					saveCheckBean.setAppRecordDetail(appRecordDetails);
					saveCheckBean.setAppEntInfo(adapter.getChangeMap());
					saveCheckBean.getAppCheckInfo().setCheckType("1");
//					saveCheckBean.getAppCheckInfo().setGpsInfo(
//							application.getInfoBean().getAppCheckInfo()
//									.getGpsInfo());
//					saveCheckBean.getAppCheckInfo()
//							.setEtpsId(
//									application.getInfoBean().getAppCheckInfo()
//											.getEtpsId());
					saveCheckBean.getAppCheckInfo().setPhonenumber(
							checkedNumber.getText() + "");
					saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");

//					saveCheckBean.getAppCheckInfo().setOrganId(
//							application.getLoginUserInfo().getOrganId());
//					saveCheckBean.getAppCheckInfo().setDeptId(
//							application.getLoginUserInfo().getDeptId());

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
					String checkDate = df.format(new Date());// new Date()为获取当前系统时间
					saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
					saveCheckBean.getAppCheckInfo().setInspector(
							inspector.getTag() + "");
					saveCheckBean.getAppCheckInfo().setCheckedName(
							checkedName.getText() + "");
					saveCheckBean.getAppCheckInfo().setCheckedNumber(
							checkedNumber.getText() + "");
					saveCheckBean.getAppCheckInfo().setTmpFlag("1");
					saveCheckBean.getAppRecordContent().setTreatment(
							deal.getTag() + "");

					List<CnUpload> cnUploads = new ArrayList<CnUpload>();

					if (boolList[0]) {
						CnUpload cnUpload = new CnUpload();
						String filePath1 = "";
					/*
					 * imageView1.setDrawingCacheEnabled(true); bitmap1 =
					 * imageView1.getDrawingCache();
					 */
						filePath1 = BitMapUtil.bitmapToBase64(bitmap1);
					/* imageView1.setDrawingCacheEnabled(false); */
						cnUpload.setFileName("1.jpg");
						cnUpload.setFilePath(filePath1);
						cnUploads.add(cnUpload);
					}

					if (boolList[1]) {
						CnUpload cnUpload = new CnUpload();
						String filePath2 = "";
					/*
					 * imageView2.setDrawingCacheEnabled(true); Bitmap bitmap2 =
					 * imageView2.getDrawingCache();
					 */
						filePath2 = BitMapUtil.bitmapToBase64(bitmap2);
					/* imageView2.setDrawingCacheEnabled(false); */
						cnUpload.setFileName("2.jpg");
						cnUpload.setFilePath(filePath2);
						cnUploads.add(cnUpload);

					}

					if (boolList[2]) {
						CnUpload cnUpload = new CnUpload();
						String filePath3 = "";
					/*
					 * imageView3.setDrawingCacheEnabled(true); Bitmap bitmap3 =
					 * imageView3.getDrawingCache();
					 */
						filePath3 = BitMapUtil.bitmapToBase64(bitmap3);
					/* imageView3.setDrawingCacheEnabled(false); */
						cnUpload.setFileName("3.jpg");
						cnUpload.setFilePath(filePath3);
						cnUploads.add(cnUpload);
					}
					saveCheckBean.setAppUploads(cnUploads);

					String postParam = gson.toJson(saveCheckBean,
							SaveCheckBean.class);
					// Log.e("postParam", postParam);
					String address = Url.QJ_IN_USE + "saveTmpCheck";
					HttpClientUtil.callWebService(postParam, address,
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
				}else{
					CheckUtil.showSoftInput(checkedNumber,getActivity());
				}
			}
		});

		realButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				progressDialog.show();
				List<AppRecordDetail> appRecordDetails = new ArrayList<AppRecordDetail>();
				for (int i = 0; i < newFathers.size(); i++) {
					List<Child> childs = newFathers.get(i).getChildMatter();
					for (int j = 0; j < childs.size(); j++) {
						AppRecordDetail appRecordDetail = new AppRecordDetail();
						appRecordDetail.setCheckResult(childs.get(j)
								.getChildFlag() + "");
						appRecordDetail
								.setMatterId(childs.get(j).getMatterId());
						appRecordDetails.add(appRecordDetail);
					}

				}
				// new
				// problem.setText(application.getInfoBean()
				// .getAppRecordContentVo().getProblem());
				List<CnUpload> cnUploads = new ArrayList<CnUpload>();
				if (boolList[0]) {
					CnUpload cnUpload = new CnUpload();
					String filePath1 = "";
					/*
					 * imageView1.setDrawingCacheEnabled(true); bitmap1 =
					 * imageView1.getDrawingCache();
					 */
					filePath1 = BitMapUtil.bitmapToBase64(bitmap1);
					/* imageView1.setDrawingCacheEnabled(false); */
					cnUpload.setFileName("1.jpg");
					cnUpload.setFilePath(filePath1);
					cnUploads.add(cnUpload);
				}

				if (boolList[1]) {
					CnUpload cnUpload = new CnUpload();
					String filePath2 = "";
					/*
					 * imageView2.setDrawingCacheEnabled(true); Bitmap bitmap2 =
					 * imageView2.getDrawingCache();
					 */
					filePath2 = BitMapUtil.bitmapToBase64(bitmap2);
					/* imageView2.setDrawingCacheEnabled(false); */
					cnUpload.setFileName("2.jpg");
					cnUpload.setFilePath(filePath2);
					cnUploads.add(cnUpload);

				}

				if (boolList[2]) {
					CnUpload cnUpload = new CnUpload();
					String filePath3 = "";
					/*
					 * imageView3.setDrawingCacheEnabled(true); Bitmap bitmap3 =
					 * imageView3.getDrawingCache();
					 */
					filePath3 = BitMapUtil.bitmapToBase64(bitmap3);
					/* imageView3.setDrawingCacheEnabled(false); */
					cnUpload.setFileName("3.jpg");
					cnUpload.setFilePath(filePath3);
					cnUploads.add(cnUpload);
				}
					saveCheckBean.setAppUploads(cnUploads);
				saveCheckBean.getAppRecordContent().setProblem(
						problem.getTag().toString());

//				saveCheckBean.getAppCheckInfo().setSubmitUser(
//						application.getLoginUserInfo().getUserId());

					saveCheckBean.setTrdScope(trdScope.getText().toString());
					saveCheckBean.setAppRecordDetail(appRecordDetails);
					saveCheckBean.setAppEntInfo(adapter.getChangeMap());
				saveCheckBean.getAppCheckInfo().setRecordId(
						application.getRecordId());
				saveCheckBean.getAppCheckInfo().setCheckType("1");
//				saveCheckBean.getAppCheckInfo().setGpsInfo(
//						application.getInfoBean().getAppCheckInfo()
//								.getGpsInfo());
//				saveCheckBean.getAppCheckInfo()
//						.setEtpsId(
//								application.getInfoBean().getAppCheckInfo()
//										.getEtpsId());
				saveCheckBean.getAppCheckInfo().setPhonenumber(
						checkedNumber.getText() + "");
					saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");

//				saveCheckBean.getAppCheckInfo().setOrganId(
//						application.getLoginUserInfo().getOrganId());
//				saveCheckBean.getAppCheckInfo().setDeptId(
//						application.getLoginUserInfo().getDeptId());

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");
				saveCheckBean.getAppCheckInfo().setCheckedName(
						checkedName.getText() + "");
				saveCheckBean.getAppCheckInfo().setCheckedNumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setTmpFlag("0");
				saveCheckBean.getAppRecordContent().setTreatment(
						deal.getTag() + "");

				String postParam = gson.toJson(saveCheckBean,
						SaveCheckBean.class);
				String address = Url.QJ_IN_USE + "saveTmpCheck";
				HttpClientUtil.callWebService(postParam, address,
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

	private void initKeyValue() {

		MapToListUtil dealUtil = new MapToListUtil(dealMap);
		List<KeyValue> dealKeyValues = dealUtil.mapToKeyValues();
		deal.setOnClickListener(new ShowMutiDialogListListener(dealKeyValues,
				activity, deal));

		List<KeyValue> inspectorKeyValues = new ArrayList<KeyValue>();
		for (int i = 0; i < users.size(); i++) {
			KeyValue keyValue = new KeyValue();
			keyValue.setKey(users.get(i).getUserId());
			keyValue.setValue(users.get(i).getName());
			inspectorKeyValues.add(keyValue);
		}

		// inspector.setTag(application.getLoginUserInfo().getUserId());
		// inspector.setText(application.getLoginUserInfo().getUserName());
		inspector.setOnClickListener(new ShowMutiDialogListListener(
				inspectorKeyValues, activity, inspector));
	}

	public void initCheckList() {
//		if (application.getInfoBean() != null) {
//			LinkedHashMap<String, String> changeMap = application.getInfoBean()
//					.getAppEntInfoVo();
//			LinkedHashMap<String, String> map = application.getInfoBean()
//					.getEtpsInfoVo();
//			MapToListUtil mapToListUtil = new MapToListUtil(map);
//			List<EtpsInfoBean> etpsInfoBeans = mapToListUtil.mapToEtpsInfoS();
//
//			dailyList = (LinearLayoutForListView) view
//					.findViewById(R.id.demo_list);
//			adapter = new EtpsInfoRadioButtonAdapter(activity,
//					R.layout.radio_buton_list_item, etpsInfoBeans,
//					changeRecord, changeMap);
//			adapter.setChangeMap(application.getInfoBean().getAppEntInfoVo());
//			dailyList.setAdapter(adapter);
//		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
					progressDialog.cancel();
					UtilForFragment.popBackStack(activity);

				} else {
					progressDialog.cancel();
					Toast.makeText(getActivity(), "未查询到该记录详情",
							Toast.LENGTH_SHORT).show();
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
							boolList[0] = true;
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
							boolList[1] = true;
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
							boolList[2] = true;
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
					Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
							.show();
				}
				break;
			}

		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {

		case 61:

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(imageUri, "image/*");
			intent.putExtra("scale", true);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			getActivity().startActivityForResult(intent, 62);

			break;
		case 62:
			imageAlertDialog.dismiss();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(getActivity()
						.getContentResolver().openInputStream(imageUri));
				if (imageFlag == 1) {
					bitmap1 = bitmap;
					imageView1.setImageBitmap(bitmap);
					boolList[0] = true;

				}
				if (imageFlag == 2) {
					bitmap2 = bitmap;
					imageView2.setImageBitmap(bitmap);
					boolList[1] = true;

				}
				if (imageFlag == 3) {
					bitmap3 = bitmap;
					imageView3.setImageBitmap(bitmap);
					boolList[2] = true;

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case 63:

			imageUri = data.getData(); // 获得图片的uri
			Intent intent2 = new Intent("com.android.camera.action.CROP");
			intent2.setDataAndType(imageUri, "image/*");
			intent2.putExtra("scale", true);
			intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			getActivity().startActivityForResult(intent2, 62);

			break;

		default:
			break;
		}

	}


	@Override
	public void onResume() {
		problem.setText(application.getProblemName());
		problem.setTag(application.getProblem());
		super.onResume();
	}

	@Override
	public void onDestroy() {
		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
		title.setText("日常检查");
		application.setProblem("");
		application.setProblemName("");
		super.onDestroy();
	}

	private void showCleanDialog() {
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		photoShowView = layoutInflater.inflate(R.layout.photo_view, null);
		ImageView imageView = (ImageView) photoShowView
				.findViewById(R.id.show_photo);
		if (imageFlag == 1) {
			imageView.setImageBitmap(bitmap1);
		}
		if (imageFlag == 2) {
			imageView.setImageBitmap(bitmap2);
		}
		if (imageFlag == 3) {
			imageView.setImageBitmap(bitmap3);
		}

		AlertDialog.Builder showimageDialog = new AlertDialog.Builder(
				getActivity());
		showimageDialog.setTitle("清除图片");
		showimageDialog.setView(photoShowView);
		showimageDialog.setNegativeButton("清除",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (imageFlag == 1) {
							imageView1.setImageBitmap(null);
							boolList[0] = false;
							showimageAlertDialog.dismiss();
						}
						if (imageFlag == 2) {
							imageView2.setImageBitmap(null);
							boolList[1] = false;
							showimageAlertDialog.dismiss();
						}
						if (imageFlag == 3) {
							imageView3.setImageBitmap(null);
							boolList[2] = false;
							showimageAlertDialog.dismiss();
						}
					}
				});
		showimageDialog.setNeutralButton("返回",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						showimageAlertDialog.dismiss();

					}
				});
		showimageAlertDialog = showimageDialog.show();

		// 设置dialogList的长宽
		WindowManager windowManager = (WindowManager) getActivity()
				.getSystemService(Context.WINDOW_SERVICE);
		WindowManager.LayoutParams layoutParams = showimageAlertDialog
				.getWindow().getAttributes();
		layoutParams.width = layoutParams.MATCH_PARENT;
		layoutParams.height = layoutParams.MATCH_PARENT;
		showimageAlertDialog.getWindow().setAttributes(layoutParams);
	}

	private void getBitmap() {
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		photoChooseView = layoutInflater.inflate(R.layout.photo_choose_view,
				null);
		TextView byPhoto = (TextView) photoChooseView
				.findViewById(R.id.byPhoto);
		TextView byFile = (TextView) photoChooseView.findViewById(R.id.byFile);
		byPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				File outputImage = new File(Environment
						.getExternalStorageDirectory(), "sunhapper.jpg");
				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				getActivity().startActivityForResult(intent, 61);

			}
		});
		byFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				intent.putExtra("crop", true);
				intent.putExtra("scale", true);
				getActivity().startActivityForResult(intent, 63);
			}
		});
		AlertDialog.Builder imageDialog = new AlertDialog.Builder(getActivity());
		imageDialog.setView(photoChooseView);
		imageAlertDialog = imageDialog.show();

		/*
		 * // 设置dialogList的长宽 WindowManager windowManager = (WindowManager)
		 * getActivity() .getSystemService(Context.WINDOW_SERVICE);
		 * WindowManager.LayoutParams layoutParams =
		 * imageAlertDialog.getWindow() .getAttributes(); layoutParams.width =
		 * layoutParams.WRAP_CONTENT; layoutParams.height =
		 * layoutParams.WRAP_CONTENT;
		 * imageAlertDialog.getWindow().setAttributes(layoutParams);
		 */
	}
}
