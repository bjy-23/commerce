package com.wondersgroup.commerce.teamwork.wywork.fwqy;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.teamwork.dailycheck.AppRecordDetail;
import com.wondersgroup.commerce.teamwork.dailycheck.BitMapUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.BookBean;
import com.wondersgroup.commerce.teamwork.dailycheck.CnUpload;
import com.wondersgroup.commerce.teamwork.dailycheck.ExampleAdapter;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpCallbackListener;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpClientUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.ImgFirstBean;
import com.wondersgroup.commerce.teamwork.dailycheck.KeyValue;
import com.wondersgroup.commerce.teamwork.dailycheck.SaveCheckBean;
import com.wondersgroup.commerce.teamwork.dailycheck.ShowMutiDialogListListener;
import com.wondersgroup.commerce.teamwork.dailycheck.Url;
import com.wondersgroup.commerce.utils.CheckUtil;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntTempFragment extends Fragment {

	private View view;
	private Context context;
	// private LinearLayoutForListView dailyList;
	private List<String> strings = new ArrayList<String>();
//	private EtpsInfoRadioButtonAdapter adapter;
//	private Dialog progressDialog;
	private AppCompatActivity activity;
	// private List<CnApp> complainCnApps = new ArrayList<CnApp>();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_ERROR_DIALOG = 4;
	public static final int SHOW_complain_RESPONSE = 5;
	private RootAppcation application;
	ExampleAdapter expandalbeAdapter;

	private TextView serviceActivityDetail;
	private TextView serviceProblems;
	private TextView serviceSuggestions;
	private TextView checkedName;
	private TextView checkedNumber;
	private TextView inspector;
	private TextView memo;
	private Button tempButton;
	private Button realButton;

	// private TextView brandRegUse;
	// private TextView adBehavior;
	// private TextView businessStatus;
	// private TextView financialSystem;
	// private TextView innerManageSystem;
	// private TextView serviceMatter;

	// private TextView gpsInfo;

	// private TextView tradeScope;
	// private TextView changeRecord;
	// private TextView trdScope;
	private SaveCheckBean saveCheckBean = new SaveCheckBean();
	private List<AppRecordDetail> appRecordDetail = new ArrayList<AppRecordDetail>();
	private Gson gson = new Gson();

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

	// private TextView serviceBusinessStatus;
	// private TextView serviceFinancialSystem;
	// private TextView serviceAdBehavior;
	// private TextView serviceInnerMansys;
	// private TextView serviceBrandReguse;
	// private TextView serviceActivityItems;

	// Map<String, String> serviceMatterMap;
	// Map<String, String> brandRegUseMap;
	// Map<String, String> adBehaviorMap;
	// Map<String, String> businessStatusMap;
	// Map<String, String> financialSystemMap;
	// Map<String, String> innerManageSystemMap;
	List<BookBean> users;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.q_entservice, container, false);
		activity = (AppCompatActivity) getActivity();
		context = activity;
		application = (RootAppcation) activity.getApplication();

		TextView toolbarText = (TextView)activity.findViewById(R.id.toolbar_title);
		toolbarText.setText(application.getInfoBean().getEtpsInfoVo()
				.get("etpsName"));

//		progressDialog = LoadingDialog.createLoadingDialog(getActivity(),
//				"loading");

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
		// initCheckList();
		setHasOptionsMenu(true);
		return view;
	}

	// public void initCheckList() {
	// if (application.getInfoBean() != null) {
	// LinkedHashMap<String, String> changeMap = application.getInfoBean()
	// .getAppEntInfoVo();
	// LinkedHashMap<String, String> map = application.getInfoBean()
	// .getEtpsInfoVo();
	// MapToListUtil mapToListUtil = new MapToListUtil(map);
	// List<EtpsInfoBean> etpsInfoBeans = mapToListUtil.mapToEtpsInfoS();
	//
	// dailyList = (LinearLayoutForListView) view
	// .findViewById(R.id.demo_list);
	// adapter = new EtpsInfoRadioButtonAdapter(activity,
	// R.layout.radio_buton_list_item, etpsInfoBeans,
	// changeRecord, changeMap);
	// dailyList.setAdapter(adapter);
	// }
	// }

	private void initKeyValue() {

		// MapToListUtil serviceMatterUtil = new
		// MapToListUtil(serviceMatterMap);
		// List<KeyValue> serviceMatterKeyValues = serviceMatterUtil
		// .mapToKeyValues();
		// serviceMatter.setOnClickListener(new ShowMutiDialogListListener(
		// serviceMatterKeyValues, activity, serviceMatter));
		//
		// MapToListUtil brandRegUseUtil = new MapToListUtil(brandRegUseMap);
		// List<KeyValue> brandRegUseKeyValues =
		// brandRegUseUtil.mapToKeyValues();
		// brandRegUse.setOnClickListener(new ShowSingleDialogListListener(
		// brandRegUseKeyValues, activity, brandRegUse));
		//
		// MapToListUtil adBehaviorUtil = new MapToListUtil(adBehaviorMap);
		// List<KeyValue> adBehaviorKeyValues = adBehaviorUtil.mapToKeyValues();
		// adBehavior.setOnClickListener(new ShowSingleDialogListListener(
		// adBehaviorKeyValues, activity, adBehavior));
		//
		// MapToListUtil businessStatusUtil = new
		// MapToListUtil(businessStatusMap);
		// List<KeyValue> businessStatusKeyValues = businessStatusUtil
		// .mapToKeyValues();
		// businessStatus.setOnClickListener(new ShowSingleDialogListListener(
		// businessStatusKeyValues, activity, businessStatus));
		//
		// MapToListUtil financialSystemUtil = new MapToListUtil(
		// financialSystemMap);
		// List<KeyValue> financialSystemKeyValues = financialSystemUtil
		// .mapToKeyValues();
		// financialSystem.setOnClickListener(new ShowSingleDialogListListener(
		// financialSystemKeyValues, activity, financialSystem));
		//
		// MapToListUtil innerManageSystemUtil = new MapToListUtil(
		// innerManageSystemMap);
		// List<KeyValue> innerManageSystemKeyValues = innerManageSystemUtil
		// .mapToKeyValues();
		// innerManageSystem.setOnClickListener(new
		// ShowSingleDialogListListener(
		// innerManageSystemKeyValues, activity, innerManageSystem));

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

	private void initView() {

		TextView etpsName = (TextView) view.findViewById(R.id.etpsName);
		etpsName.setText(application.getInfoBean().getEtpsInfoVo()
				.get("etpsName"));
		// serviceMatter = (TextView) view.findViewById(R.id.serviceMatter);
		// brandRegUse = (TextView) view.findViewById(R.id.brandRegUse);
		// adBehavior = (TextView) view.findViewById(R.id.adBehavior);
		// businessStatus = (TextView) view.findViewById(R.id.businessStatus);
		// financialSystem = (TextView) view.findViewById(R.id.financialSystem);
		// innerManageSystem = (TextView) view
		// .findViewById(R.id.innerManageSystem);
		inspector = (TextView) view.findViewById(R.id.inspector);

		// serviceMatter.setTag("");
		// brandRegUse.setTag("");
		// adBehavior.setTag("");
		// businessStatus.setTag("");
		// financialSystem.setTag("");
		// innerManageSystem.setTag("");
		inspector.setTag("");

		// serviceMatterMap = application.getInfoBean().getServiceMatter();
		// brandRegUseMap = application.getInfoBean().getBrandRegUse();
		// adBehaviorMap = application.getInfoBean().getAdBehavior();
		// businessStatusMap = application.getInfoBean().getBusinessStatus();
		// financialSystemMap = application.getInfoBean().getFinancialSystem();
		// innerManageSystemMap =
		// application.getInfoBean().getInnerManageSystem();
		users = application.getInfoBean().getUserList();

		// serviceMatter.setTag(application.getInfoBean().getAppRecordContent()
		// .getServiceActivityItems());
		// brandRegUse.setTag(application.getInfoBean().getAppRecordContent()
		// .getServiceBrandReguse());
		// adBehavior.setTag(application.getInfoBean().getAppRecordContent()
		// .getServiceAdBehavior());
		// businessStatus.setTag(application.getInfoBean().getAppRecordContent()
		// .getServiceBusinessStatus());
		// financialSystem.setTag(application.getInfoBean().getAppRecordContent()
		// .getServiceFinancialSystem());
		// innerManageSystem.setTag(application.getInfoBean()
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

		// String[] matterStrings =
		// serviceMatter.getTag().toString().split(",");
		// String matterString = " ";
		// for (int i = 0; i < matterStrings.length; i++) {
		// if (serviceMatterMap.get(matterStrings[i]) != null) {
		// matterString += serviceMatterMap.get(matterStrings[i]) + ",";
		// }
		//
		// }
		// matterString=matterString.substring(0,matterString.length()-1);
		// serviceMatter.setText(matterString);
		//
		// String[] brandStrings = brandRegUse.getTag().toString().split(",");
		// String brandString = " ";
		// for (int i = 0; i < brandStrings.length; i++) {
		// if (brandRegUseMap.get(brandStrings[i]) != null) {
		// brandString += brandRegUseMap.get(brandStrings[i]) + ",";
		// }
		//
		// }
		// brandString=brandString.substring(0,brandString.length()-1);
		// brandRegUse.setText(brandString);
		//
		// String[] adStrings = adBehavior.getTag().toString().split(",");
		// String adString = " ";
		// for (int i = 0; i < adStrings.length; i++) {
		// if (adBehaviorMap.get(adStrings[i]) != null) {
		// adString += adBehaviorMap.get(adStrings[i]) + ",";
		// }
		//
		// }
		// adString=adString.substring(0,adString.length()-1);
		// adBehavior.setText(adString);
		//
		// String[] businessStrings = businessStatus.getTag().toString()
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
		// businessStatus.setText(businissString);
		//
		// String[] finacialStrings = financialSystem.getTag().toString()
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
		// financialSystem.setText(finacialString);
		//
		// String[] innerStrings = innerManageSystem.getTag().toString()
		// .split(",");
		// String innerString = " ";
		// for (int i = 0; i < innerStrings.length; i++) {
		// if (innerManageSystemMap.get(innerStrings[i]) != null) {
		// innerString += innerManageSystemMap.get(innerStrings[i]) + ",";
		// }
		//
		// }
		// innerString=innerString.substring(0,innerString.length()-1);
		// innerManageSystem.setText(innerString);

		// new
		// gpsInfo = (TextView) view.findViewById(R.id.gpsInfo);
		// changeRecord = (TextView) view.findViewById(R.id.changeRecord);
		// MapToListUtil mapToListUtil = new MapToListUtil(application
		// .getInfoBean().getAppEntInfoVo());
		// List<EtpsInfoBean> keyValues = mapToListUtil.mapToEtpsInfoS();
		// String string = " ";
		// for (int i = 0; i < keyValues.size(); i++) {
		// if (!keyValues.get(i).getValue().equals("EOF")) {
		// string += keyValues.get(i).getName() + ":"
		// + keyValues.get(i).getValue() + "\n";
		// }
		//
		// }
		// string = string.substring(0, string.length() - 1);
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
		tempButton = (Button) view.findViewById(R.id.tempButton);
		realButton = (Button) view.findViewById(R.id.realButton);

		checkedName = (TextView) view.findViewById(R.id.checkedName);
		checkedNumber = (TextView) view.findViewById(R.id.checkedNumber);
		CheckUtil.checkInputPhone(checkedNumber);
		CheckUtil.limitCheckMaxCount(checkedName, Constants.inputMaxCount);
		memo = (TextView) view.findViewById(R.id.memo);

		tempButton = (Button) view.findViewById(R.id.tempButton);

		tempButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				progressDialog.show();

				MyProgressDialog.show(context);
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

				// saveCheckBean
				// new
				saveCheckBean.getAppCheckInfo().setRecordId(
						application.getRecordId());
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				// saveCheckBean.setTrdScope(trdScope.getText().toString());
				saveCheckBean.getAppCheckInfo()
						.setEtpsId(
								application.getInfoBean().getAppCheckInfo()
										.getEtpsId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getInfoBean().getAppCheckInfo()
								.getGpsInfo());

//				saveCheckBean.setAppEntInfo(adapter.getChangeMap());
				saveCheckBean.getAppCheckInfo().setCheckType("5");
				saveCheckBean.getAppCheckInfo().setPhonenumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");

				saveCheckBean.getAppCheckInfo().setOrganId(
						application.getLoginUserInfo().getOrganId());
				saveCheckBean.getAppCheckInfo().setDeptId(
						application.getLoginUserInfo().getDeptId());
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
				saveCheckBean.getAppCheckInfo().setCheckedName(
						checkedName.getText() + "");
				saveCheckBean.getAppCheckInfo().setCheckedNumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setTmpFlag("1");

				saveCheckBean.getAppRecordContent().setServiceActivityDetail(
						serviceActivityDetail.getText() + "");
				// saveCheckBean.getAppRecordContent().setServiceActivityItems(
				// serviceMatter.getTag() + "");
				saveCheckBean.getAppRecordContent().setServiceProblems(
						serviceProblems.getText() + "");
				saveCheckBean.getAppRecordContent().setServiceSuggestions(
						serviceSuggestions.getText() + "");

				// saveCheckBean.getAppRecordContent().setServiceBusinessStatus(
				// businessStatus.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceFinancialSystem(
				// financialSystem.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceAdBehavior(
				// adBehavior.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceInnerMansys(
				// innerManageSystem.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceBrandReguse(
				// brandRegUse.getTag() + "");

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

				MyProgressDialog.show(context);
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
				// saveCheckBean.setTrdScope(trdScope.getText().toString());
				saveCheckBean.getAppCheckInfo()
						.setEtpsId(
								application.getInfoBean().getAppCheckInfo()
										.getEtpsId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getInfoBean().getAppCheckInfo()
								.getGpsInfo());
//				saveCheckBean.setAppEntInfo(adapter.getChangeMap());
				saveCheckBean.getAppCheckInfo().setCheckType("5");
				saveCheckBean.getAppCheckInfo().setPhonenumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");

				saveCheckBean.getAppCheckInfo().setOrganId(
						application.getLoginUserInfo().getOrganId());
				saveCheckBean.getAppCheckInfo().setDeptId(
						application.getLoginUserInfo().getDeptId());
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
				saveCheckBean.getAppCheckInfo().setCheckedName(
						checkedName.getText() + "");
				saveCheckBean.getAppCheckInfo().setCheckedNumber(
						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setTmpFlag("0");
				saveCheckBean.getAppRecordContent().setServiceActivityDetail(
						serviceActivityDetail.getText() + "");
				// saveCheckBean.getAppRecordContent().setServiceActivityItems(
				// serviceMatter.getTag() + "");
				saveCheckBean.getAppRecordContent().setServiceProblems(
						serviceProblems.getText() + "");
				saveCheckBean.getAppRecordContent().setServiceSuggestions(
						serviceSuggestions.getText() + "");

				// saveCheckBean.getAppRecordContent().setServiceBusinessStatus(
				// businessStatus.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceFinancialSystem(
				// financialSystem.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceAdBehavior(
				// adBehavior.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceInnerMansys(
				// innerManageSystem.getTag() + "");
				// saveCheckBean.getAppRecordContent().setServiceBrandReguse(
				// brandRegUse.getTag() + "");

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

		serviceSuggestions.setText(application.getInfoBean()
				.getAppRecordContent().getServiceSuggestions());
		serviceProblems.setText(application.getInfoBean().getAppRecordContent()
				.getServiceProblems());
		serviceActivityDetail.setText(application.getInfoBean()
				.getAppRecordContent().getServiceActivityDetail());
		checkedName.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckedName());
		checkedNumber.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckedNumber());

		memo.setText(application.getInfoBean().getAppCheckInfo().getMemo());
		// gpsInfo.setText(application.getInfoBean().getAppCheckInfo()
		// .getGpsInfo());

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
