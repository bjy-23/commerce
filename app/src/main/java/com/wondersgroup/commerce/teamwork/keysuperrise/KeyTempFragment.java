package com.wondersgroup.commerce.teamwork.keysuperrise;

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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.teamwork.dailycheck.AppRecordDetail;
import com.wondersgroup.commerce.teamwork.dailycheck.BitMapUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.BookBean;
import com.wondersgroup.commerce.teamwork.dailycheck.CnUpload;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsInfoBean;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsInfoRadioButtonAdapter;
import com.wondersgroup.commerce.teamwork.dailycheck.Father;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpCallbackListener;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpClientUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.ImgFirstBean;
import com.wondersgroup.commerce.teamwork.dailycheck.KeyValue;
import com.wondersgroup.commerce.teamwork.dailycheck.MapToListUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.SaveCheckBean;
import com.wondersgroup.commerce.teamwork.dailycheck.ShowMutiDialogListListener;
import com.wondersgroup.commerce.teamwork.dailycheck.ShowSingleDialogListListener;
import com.wondersgroup.commerce.teamwork.dailycheck.Url;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;
import com.wondersgroup.commerce.teamwork.dailycheck.WebViewActivity;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KeyTempFragment extends Fragment {

	private View view;
	private LinearLayoutForListView infoList;

	private List<String> strings = new ArrayList<String>();
	private EtpsInfoRadioButtonAdapter adapter;

	private AppCompatActivity activity;
	// private List<CnApp> complainCnApps = new ArrayList<CnApp>();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_ERROR_DIALOG = 4;
	public static final int SHOW_complain_RESPONSE = 5;
	private RootAppcation application;
	private TextView focusIndustryType;
	private TextView focusCheckMode;
	private TextView problem;
	private TextView checkedName;
	private TextView checkedNumber;
	private TextView inspector;
	private TextView memo;
	private TextView changeRecord;

	private TextView deal;

	private Button tempButton;
	private Button realButton;
	private SaveCheckBean saveCheckBean = new SaveCheckBean();

	private Gson gson = new Gson();
	private Dialog progressDialog;

	// private TextView gpsInfo;
	private TextView trdScope;
	private TextView tradeScope;

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

	private int imageFlag = 0;
	private Uri imageUri;
	private AlertDialog imageAlertDialog;
	private View photoChooseView;
	private Boolean boolList[] = { false, false, false };

	Map<String, String> focusIndustryTypeMap;
	Map<String, String> focusCheckModeMap;
	Map<String, String> dealMap;
	List<BookBean> users;

	private List<com.wondersgroup.commerce.teamwork.dailycheck.Father> fathers;
	private LinearLayoutForListView superviceList;
	private SuperviceItemAdapter superviceItemAdapter;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.q_keysupervise, container, false);
		activity = (AppCompatActivity) getActivity();
		application = (RootAppcation) activity.getApplication();
		application.setProblemName(application.getInfoBean()
				.getAppRecordContentVo().getProblemName());
		application.setProblem(application.getInfoBean()
				.getAppRecordContentVo().getProblem());
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setTitle("");

		TextView toolbarTitle = (TextView)activity.findViewById(R.id.toolbar_title);
		toolbarTitle.setText(application.getInfoBean().getEtpsInfoVo()
				.get("etpsName"));


		// new
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		imageView2 = (ImageView) view.findViewById(R.id.imageView2);
		imageView3 = (ImageView) view.findViewById(R.id.imageView3);
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
		initCheckList();
		initKeyValue();
		setHasOptionsMenu(true);
		return view;
	}

	private void initView() {

		focusIndustryType = (TextView) view
				.findViewById(R.id.focusIndustryType);
		focusCheckMode = (TextView) view.findViewById(R.id.focusCheckMode);
		deal = (TextView) view.findViewById(R.id.deal);
		inspector = (TextView) view.findViewById(R.id.inspector);
		focusIndustryType.setTag("");
		deal.setTag("");
		focusCheckMode.setTag("");
		inspector.setTag("");

		focusIndustryTypeMap = application.getInfoBean().getFocusCatagory();
		focusCheckModeMap = application.getInfoBean().getCheckMode();
		dealMap = application.getInfoBean().getDeal();
		users = application.getInfoBean().getUserList();

		focusCheckMode.setTag(application.getInfoBean().getAppRecordContent()
				.getFocusCheckMode());
		focusIndustryType.setTag(application.getInfoBean()
				.getAppRecordContent().getFocusIndustryType());
		inspector.setTag(application.getInfoBean().getAppCheckInfo()
				.getInspector());
		deal.setTag(application.getInfoBean().getAppRecordContent()
				.getTreatment());

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

		String[] dealStrings = deal.getTag().toString().split(",");
		String dealString = " ";
		for (int i = 0; i < dealStrings.length; i++) {
			if (dealMap.get(dealStrings[i]) != null) {
				dealString += dealMap.get(dealStrings[i]) + ",";
			}

		}
		dealString = dealString.substring(0, dealString.length() - 1);
		deal.setText(dealString);

		String[] checkStrings = focusCheckMode.getTag().toString().split(",");
		String checkString = " ";
		for (int i = 0; i < checkStrings.length; i++) {
			if (focusCheckModeMap.get(checkStrings[i]) != null) {
				checkString += focusCheckModeMap.get(checkStrings[i]) + ",";
			}

		}
		checkString = checkString.substring(0, checkString.length() - 1);
		focusCheckMode.setText(checkString);

		String[] typeStrings = focusIndustryType.getTag().toString().split(",");
		String typeString = " ";
		for (int i = 0; i < typeStrings.length; i++) {
			if (focusIndustryTypeMap.get(typeStrings[i]) != null) {
				typeString += focusIndustryTypeMap.get(typeStrings[i]) + ",";
			}

		}
		typeString = typeString.substring(0, typeString.length() - 1);
		focusIndustryType.setText(typeString);

		problem = (TextView) view.findViewById(R.id.problem);
		problem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, WebViewActivity.class);
				startActivity(intent);

			}
		});

		// new
		// gpsInfo = (TextView) view.findViewById(R.id.gpsInfo);
		changeRecord = (TextView) view.findViewById(R.id.changeRecord);
		MapToListUtil mapToListUtil = new MapToListUtil(application
				.getInfoBean().getAppEntInfoVo());
		List<EtpsInfoBean> keyValues = mapToListUtil.mapToEtpsInfoS();
		String string = " ";
		for (int i = 0; i < keyValues.size(); i++) {
			if (!keyValues.get(i).getValue().equals("EOF")) {
				string += keyValues.get(i).getName() + ":"
						+ keyValues.get(i).getValue() + "\n";
			}

		}
		changeRecord.setText(string);

		// temp
		trdScope = (TextView) view.findViewById(R.id.trdScope);
		trdScope.setText(application.getInfoBean().getTrdScopeChg());
		tradeScope = (TextView) view.findViewById(R.id.tradeScope);
		tradeScope.setText(application.getInfoBean().getTrdScope());

		tempButton = (Button) view.findViewById(R.id.tempButton);
		realButton = (Button) view.findViewById(R.id.realButton);

		checkedName = (TextView) view.findViewById(R.id.checkedName);
		checkedNumber = (TextView) view.findViewById(R.id.checkedNumber);
		memo = (TextView) view.findViewById(R.id.memo);

		tempButton = (Button) view.findViewById(R.id.tempButton);

		tempButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				progressDialog.show();
				MyProgressDialog.show(activity);
				// saveCheckBean
				// new
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
				saveCheckBean.getAppCheckInfo().setRecordId(
						application.getRecordId());
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				saveCheckBean.setTrdScope(trdScope.getText().toString());
				saveCheckBean.getAppCheckInfo()
						.setEtpsId(
								application.getInfoBean().getAppCheckInfo()
										.getEtpsId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getInfoBean().getAppCheckInfo()
								.getGpsInfo());

				List<AppRecordDetail> appRecordDetails = new ArrayList<AppRecordDetail>();
				for (int i = 0; i < fathers.size(); i++) {
					AppRecordDetail appRecordDetail = new AppRecordDetail();
					appRecordDetail.setCheckResult(fathers.get(i)
							.getCheckFlag() + "");
					appRecordDetail.setMatterId(fathers.get(i).getMatterId());
					appRecordDetails.add(appRecordDetail);
				}

				saveCheckBean.setAppRecordDetail(appRecordDetails);
				saveCheckBean.setAppEntInfo(adapter.getChangeMap());
				saveCheckBean.getAppCheckInfo().setCheckType("2");
				saveCheckBean.getAppRecordContent().setProblem(
						problem.getTag().toString());
				saveCheckBean.getAppRecordContent().setFocusCheckMode(
						focusCheckMode.getTag().toString());

				saveCheckBean.getAppCheckInfo().setPhonenumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");
				saveCheckBean.getAppCheckInfo().setOrganId(
						application.getLoginUserInfo().getOrganId());
				saveCheckBean.getAppCheckInfo().setDeptId(
						application.getLoginUserInfo().getDeptId());
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");
				saveCheckBean.getAppRecordContent().setFocusIndustryType(
						focusIndustryType.getTag() + "");

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
				saveCheckBean.getAppCheckInfo().setCheckedName(
						checkedName.getText() + "");
				saveCheckBean.getAppCheckInfo().setCheckedNumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setTmpFlag("1");
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

		realButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				progressDialog.show();
				MyProgressDialog.show(activity);
				// saveCheckBean
				// new
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
				saveCheckBean.getAppCheckInfo().setRecordId(
						application.getRecordId());
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				saveCheckBean.setTrdScope(trdScope.getText().toString());
				saveCheckBean.getAppCheckInfo()
						.setEtpsId(
								application.getInfoBean().getAppCheckInfo()
										.getEtpsId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getInfoBean().getAppCheckInfo()
								.getGpsInfo());
				List<AppRecordDetail> appRecordDetails = new ArrayList<AppRecordDetail>();
				for (int i = 0; i < fathers.size(); i++) {
					AppRecordDetail appRecordDetail = new AppRecordDetail();
					appRecordDetail.setCheckResult(fathers.get(i)
							.getCheckFlag() + "");
					appRecordDetail.setMatterId(fathers.get(i).getMatterId());
					appRecordDetails.add(appRecordDetail);
				}
				saveCheckBean.getAppRecordContent().setProblem(
						problem.getTag().toString());
				saveCheckBean.setAppRecordDetail(appRecordDetails);

				saveCheckBean.getAppRecordContent().setFocusCheckMode(
						focusCheckMode.getTag().toString());

				saveCheckBean.setAppEntInfo(adapter.getChangeMap());
				saveCheckBean.getAppCheckInfo().setCheckType("2");
				saveCheckBean.getAppCheckInfo().setPhonenumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");
				saveCheckBean.getAppCheckInfo().setOrganId(
						application.getLoginUserInfo().getOrganId());
				saveCheckBean.getAppCheckInfo().setDeptId(
						application.getLoginUserInfo().getDeptId());
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");
				saveCheckBean.getAppRecordContent().setFocusIndustryType(
						focusIndustryType.getTag() + "");

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
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

		checkedName.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckedName());
		checkedNumber.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckedNumber());

		memo.setText(application.getInfoBean().getAppCheckInfo().getMemo());
		// gpsInfo.setText(application.getInfoBean().getAppCheckInfo()
		// .getGpsInfo());

	}

	private void initKeyValue() {

		MapToListUtil focusIndustryTypeUtil = new MapToListUtil(
				focusIndustryTypeMap);
		List<KeyValue> focusIndustryTypeKeyValues = focusIndustryTypeUtil
				.mapToKeyValues();
		focusIndustryType.setOnClickListener(new ShowMutiDialogListListener(
				focusIndustryTypeKeyValues, activity, focusIndustryType));

		MapToListUtil focusCheckModeUtil = new MapToListUtil(focusCheckModeMap);
		List<KeyValue> focusCheckModeKeyValues = focusCheckModeUtil
				.mapToKeyValues();
		focusCheckMode.setOnClickListener(new ShowSingleDialogListListener(
				focusCheckModeKeyValues, activity, focusCheckMode));

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
		if (application.getInfoBean() != null) {
			LinkedHashMap<String, String> changeMap = application.getInfoBean()
					.getAppEntInfoVo();
			LinkedHashMap<String, String> map = application.getInfoBean()
					.getEtpsInfoVo();
			MapToListUtil mapToListUtil = new MapToListUtil(map);
			List<EtpsInfoBean> etpsInfoBeans = mapToListUtil.mapToEtpsInfoS();
			infoList = (LinearLayoutForListView) view
					.findViewById(R.id.demo_list);
			adapter = new EtpsInfoRadioButtonAdapter(activity,
					R.layout.radio_buton_list_item, etpsInfoBeans,
					changeRecord, changeMap);
			infoList.setAdapter(adapter);

			List<AppRecordDetail> appRecordDetailChanges = application
					.getInfoBean().getAppRecordDetails();
			Map<String, String> detailChangeMap = new HashMap<String, String>();
			for (int i = 0; i < appRecordDetailChanges.size(); i++) {
				AppRecordDetail appRecordDetail = appRecordDetailChanges.get(i);
				detailChangeMap.put(appRecordDetail.getMatterId(),
						appRecordDetail.getCheckResult());
			}

			fathers = application.getInfoBean().getCheckMatters();
			for (int i = 0; i < fathers.size(); i++) {
				Father father = fathers.get(i);
				father.setCheckFlag(Integer.parseInt(detailChangeMap.get(father
						.getMatterId())));
			}
			superviceList = (LinearLayoutForListView) view
					.findViewById(R.id.superviceItemList);
			superviceItemAdapter = new SuperviceItemAdapter(activity,
					R.layout.supervice_list_item, fathers);
			superviceList.setAdapter(superviceItemAdapter);

		}
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
//					progressDialog.cancel();
					MyProgressDialog.dismiss();
					UtilForFragment.popBackStack(activity);

				} else {
//					progressDialog.cancel();
					MyProgressDialog.dismiss();
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
//				progressDialog.cancel();
				MyProgressDialog.dismiss();
				Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	};

	@Override
	public void onResume() {
		problem.setText(application.getProblemName());
		problem.setTag(application.getProblem());
		super.onResume();
	}

	@Override
	public void onDestroy() {
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

}