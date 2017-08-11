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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UnlicensedTempFragment extends Fragment {

	private View view;

	private AppCompatActivity activity;
	private TextView abbuseDate;
	// private List<CnApp> complainCnApps = new ArrayList<CnApp>();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_ERROR_DIALOG = 4;
	private RootAppcation application;
	private TextView cardType;
	private TextView caseFrom;
	private TextView deal;
	private TextView entityType;
	private TextView legalBasis;
	private TextView unLicensedType;
	private TextView abbuseFormmer;

	private TextView abbusePerson;
	private TextView abbuseSex;
	private TextView abbuseAge;
	private TextView abbuseCompany;
	private TextView abbuseOccupation;
	private TextView abbuseName;
	private TextView abbuseNumber;
	private TextView abbuseIdNumber;
	private TextView abbuseAddress;
	private TextView abbuseLeaderCompany;
	private TextView abbuseLeaderName;
	private TextView abbuseLeaderRegNumber;
	private TextView abbuseLeaderAddress;
	private TextView abbuseLeaderNumber;
	private TextView abbuseLocation;
	private TextView abbuseValue;
	private TextView abbuseScale;
	private TextView abbuseIllegal;
//	private TextView checkedName;
//	private TextView checkedNumber;
	private TextView inspector;
	private TextView memo;

	private Button tempButton;
	private Button realButton;
	private SaveCheckBean saveCheckBean = new SaveCheckBean();
	private List<AppRecordDetail> appRecordDetail = new ArrayList<AppRecordDetail>();
	private Gson gson = new Gson();
	// private TextView gpsInfo;

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

	private Map<String, String> dealMap;
	private List<BookBean> users;
	Map<String, String> cardTypeMap;
	Map<String, String> caseFromMap;
	Map<String, String> entityTypeMap;
	Map<String, String> legalBasisMap;
	Map<String, String> unLicensedTypeMap;
	Map<String, String> abbuseFormmerMap;
	Map<String, String> sexMap;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.q_unlicensed, container, false);
		activity = (AppCompatActivity) getActivity();
		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
		title.setText("无照经营信息");

		application = (RootAppcation) activity.getApplication();
		abbuseDate = (TextView) view.findViewById(R.id.abbuseDate);
		abbuseDate.setOnTouchListener(new DateOnTouch(activity, abbuseDate));

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
		setHasOptionsMenu(true);
		return view;
	}

	private void initView() {

		deal = (TextView) view.findViewById(R.id.deal);
		inspector = (TextView) view.findViewById(R.id.inspector);
		deal.setTag("");
		inspector.setTag("");

		cardType = (TextView) view.findViewById(R.id.cardType);
		caseFrom = (TextView) view.findViewById(R.id.caseFrom);
		entityType = (TextView) view.findViewById(R.id.entityType);
		legalBasis = (TextView) view.findViewById(R.id.legalBasis);
		unLicensedType = (TextView) view.findViewById(R.id.unLicensedType);
		abbuseSex = (TextView) view.findViewById(R.id.abbuseSex);
		abbuseFormmer = (TextView) view.findViewById(R.id.abbuseFormmer);
		abbuseFormmer.setTag("");
		abbuseSex.setTag("");
		cardType.setTag("");
		caseFrom.setTag("");
		entityType.setTag("");
		legalBasis.setTag("");
		unLicensedType.setTag("");

		dealMap = application.getInfoBean().getDeal();
		users = application.getInfoBean().getUserList();
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

		cardType.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseIdType());
		caseFrom.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseSource());
		entityType.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseTitle());
		legalBasis.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseBasis());
		unLicensedType.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseType());
		abbuseFormmer.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseFormmer());
		abbuseSex.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseSex());

		cardTypeMap = application.getInfoBean().getCardType();
		caseFromMap = application.getInfoBean().getCaseFrom();
		entityTypeMap = application.getInfoBean().getEntityType();
		legalBasisMap = application.getInfoBean().getLegalBasis();
		unLicensedTypeMap = application.getInfoBean().getUnLicensedType();
		abbuseFormmerMap = application.getInfoBean().getAbbuseFormmer();
		sexMap = application.getInfoBean().getSex();

		String[] sexStrings = abbuseSex.getTag().toString().split(",");
		String sexString = " ";
		for (int i = 0; i < sexStrings.length; i++) {
			if (sexMap.get(sexStrings[i]) != null) {
				sexString += sexMap.get(sexStrings[i]) + ",";
			}
		}
		sexString = sexString.substring(0, sexString.length() - 1);
		abbuseSex.setText(sexString);

		String[] formmerStrings = abbuseFormmer.getTag().toString().split(",");
		String formmerString = " ";
		for (int i = 0; i < formmerStrings.length; i++) {
			if (abbuseFormmerMap.get(formmerStrings[i]) != null) {
				formmerString += abbuseFormmerMap.get(formmerStrings[i]) + ",";
			}

		}
		formmerString = formmerString.substring(0, formmerString.length() - 1);
		abbuseFormmer.setText(formmerString);

		String[] cardStrings = cardType.getTag().toString().split(",");
		String cardString = " ";
		for (int i = 0; i < cardStrings.length; i++) {
			if (cardTypeMap.get(cardStrings[i]) != null) {
				cardString += cardTypeMap.get(cardStrings[i]) + ",";
			}

		}
		cardString = cardString.substring(0, cardString.length() - 1);
		cardType.setText(cardString);

		String[] formStrings = caseFrom.getTag().toString().split(",");
		String formString = " ";
		for (int i = 0; i < formStrings.length; i++) {
			if (caseFromMap.get(formStrings[i]) != null) {
				formString += caseFromMap.get(formStrings[i]) + ",";
			}

		}
		formString = formString.substring(0, formString.length() - 1);
		caseFrom.setText(formString);

		String[] entityStrings = entityType.getTag().toString().split(",");
		String entityString = " ";
		for (int i = 0; i < entityStrings.length; i++) {
			if (entityTypeMap.get(entityStrings[i]) != null) {
				entityString += entityTypeMap.get(entityStrings[i]) + ",";
			}

		}
		entityString = entityString.substring(0, entityString.length() - 1);
		entityType.setText(entityString);

		String[] basisStrings = legalBasis.getTag().toString().split(",");
		String basisString = " ";
		for (int i = 0; i < basisStrings.length; i++) {
			if (legalBasisMap.get(basisStrings[i]) != null) {
				basisString += legalBasisMap.get(basisStrings[i]) + ",";
			}

		}
		basisString = basisString.substring(0, basisString.length() - 1);
		legalBasis.setText(basisString);

		String[] unlicenseStrings = unLicensedType.getTag().toString()
				.split(",");
		String unlicenseString = " ";
		for (int i = 0; i < unlicenseStrings.length; i++) {
			if (unLicensedTypeMap.get(unlicenseStrings[i]) != null) {
				unlicenseString += unLicensedTypeMap.get(unlicenseStrings[i])
						+ ",";
			}

		}
		unlicenseString = unlicenseString.substring(0,
				unlicenseString.length() - 1);
		unLicensedType.setText(unlicenseString);

		// new
		// gpsInfo = (TextView) view.findViewById(R.id.gpsInfo);
		tempButton = (Button) view.findViewById(R.id.tempButton);
		realButton = (Button) view.findViewById(R.id.realButton);
		abbusePerson = (TextView) view.findViewById(R.id.abbusePerson);

		abbuseAge = (TextView) view.findViewById(R.id.abbuseAge);
		abbuseCompany = (TextView) view.findViewById(R.id.abbuseCompany);
		abbuseOccupation = (TextView) view.findViewById(R.id.abbuseOccupation);
		abbuseName = (TextView) view.findViewById(R.id.abbuseName);
		abbuseNumber = (TextView) view.findViewById(R.id.abbuseNumber);
		abbuseIdNumber = (TextView) view.findViewById(R.id.abbuseIdNumber);
		abbuseAddress = (TextView) view.findViewById(R.id.abbuseAddress);
		abbuseLeaderCompany = (TextView) view
				.findViewById(R.id.abbuseLeaderCompany);
		abbuseLeaderName = (TextView) view.findViewById(R.id.abbuseLeaderName);
		abbuseLeaderRegNumber = (TextView) view
				.findViewById(R.id.abbuseLeaderRegNumber);
		abbuseLeaderAddress = (TextView) view
				.findViewById(R.id.abbuseLeaderAddress);
		abbuseLeaderNumber = (TextView) view
				.findViewById(R.id.abbuseLeaderNumber);
		abbuseLocation = (TextView) view.findViewById(R.id.abbuseLocation);
		abbuseValue = (TextView) view.findViewById(R.id.abbuseValue);
		abbuseScale = (TextView) view.findViewById(R.id.abbuseScale);
		abbuseIllegal = (TextView) view.findViewById(R.id.abbuseIllegal);
//		checkedName = (TextView) view.findViewById(R.id.checkedName);
//		checkedNumber = (TextView) view.findViewById(R.id.checkedNumber);
		inspector = (TextView) view.findViewById(R.id.inspector);
		memo = (TextView) view.findViewById(R.id.memo);

		tempButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

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
				saveCheckBean.getAppRecordContent().setAbbuseFormmer(
						abbuseFormmer.getTag() + "");
				saveCheckBean.getAppCheckInfo().setRecordId(
						application.getRecordId());
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getInfoBean().getAppCheckInfo()
								.getGpsInfo());

				saveCheckBean.getAppCheckInfo().setCheckType("4");
				
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getText() + "");
				saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");
				saveCheckBean.getAppCheckInfo().setOrganId(
						application.getLoginUserInfo().getOrganId());
				saveCheckBean.getAppCheckInfo().setDeptId(
						application.getLoginUserInfo().getDeptId());

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
//				saveCheckBean.getAppCheckInfo().setCheckedName(
//						checkedName.getText() + "");
//				saveCheckBean.getAppCheckInfo().setCheckedNumber(
//						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setTmpFlag("1");
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");
				saveCheckBean.getAppRecordContent().setTreatment(
						deal.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseSource(
						caseFrom.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbusePerson(
						abbusePerson.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseSex(
						abbuseSex.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseAge(
						abbuseAge.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseCompany(
						abbuseCompany.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseOccupation(
						abbuseOccupation.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseName(
						abbuseName.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseNumber(
						abbuseNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseIdType(
						cardType.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseIdNumber(
						abbuseIdNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseAddress(
						abbuseAddress.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderCompany(
						abbuseLeaderCompany.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderName(
						abbuseLeaderName.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderRegNumber(
						abbuseLeaderRegNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderAddress(
						abbuseLeaderAddress.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderNumber(
						abbuseLeaderNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLocation(
						abbuseLocation.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseDate(
						abbuseDate.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseValue(
						abbuseValue.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseScale(
						abbuseScale.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseIllegal(
						abbuseIllegal.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseType(
						unLicensedType.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseTitle(
						entityType.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseBasis(
						legalBasis.getTag() + "");

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
				saveCheckBean.getAppRecordContent().setAbbuseFormmer(
						abbuseFormmer.getTag() + "");
				saveCheckBean.getAppCheckInfo().setRecordId(
						application.getRecordId());
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getInfoBean().getAppCheckInfo()
								.getGpsInfo());

				saveCheckBean.getAppCheckInfo().setCheckType("4");
			
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getText() + "");
				saveCheckBean.getAppCheckInfo().setMemo(memo.getText() + "");
				saveCheckBean.getAppCheckInfo().setOrganId(
						application.getLoginUserInfo().getOrganId());
				saveCheckBean.getAppCheckInfo().setDeptId(
						application.getLoginUserInfo().getDeptId());

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String checkDate = df.format(new Date());// new Date()为获取当前系统时间
				saveCheckBean.getAppCheckInfo().setCheckDate(checkDate);
//				saveCheckBean.getAppCheckInfo().setCheckedName(
//						checkedName.getText() + "");
//				saveCheckBean.getAppCheckInfo().setCheckedNumber(
//						checkedNumber.getText() + "");
				saveCheckBean.getAppCheckInfo().setTmpFlag("0");
				saveCheckBean.getAppCheckInfo().setInspector(
						inspector.getTag() + "");
				saveCheckBean.getAppRecordContent().setTreatment(
						deal.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseSource(
						caseFrom.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbusePerson(
						abbusePerson.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseSex(
						abbuseSex.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseAge(
						abbuseAge.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseCompany(
						abbuseCompany.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseOccupation(
						abbuseOccupation.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseName(
						abbuseName.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseNumber(
						abbuseNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseIdType(
						cardType.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseIdNumber(
						abbuseIdNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseAddress(
						abbuseAddress.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderCompany(
						abbuseLeaderCompany.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderName(
						abbuseLeaderName.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderRegNumber(
						abbuseLeaderRegNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderAddress(
						abbuseLeaderAddress.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLeaderNumber(
						abbuseLeaderNumber.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseLocation(
						abbuseLocation.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseDate(
						abbuseDate.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseValue(
						abbuseValue.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseScale(
						abbuseScale.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseIllegal(
						abbuseIllegal.getText() + "");
				saveCheckBean.getAppRecordContent().setAbbuseType(
						unLicensedType.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseTitle(
						entityType.getTag() + "");
				saveCheckBean.getAppRecordContent().setAbbuseBasis(
						legalBasis.getTag() + "");

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

		abbuseLeaderName.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderName());
		abbuseLeaderCompany.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderCompany());
		abbuseAddress.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseAddress());
		abbuseIdNumber.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseIdNumber());

		abbuseNumber.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseNumber());
		abbuseName.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseName());
		abbuseOccupation.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseOccupation());
		abbuseCompany.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseCompany());
		abbuseAge.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseAge());
		abbusePerson.setText(application.getInfoBean().getAppRecordContent()
				.getAbbusePerson());

		// abbuseFormmer.setText("涉及前置许可无照经营:"
		// + application.getInfoBean().getAppRecordContent()
		// .getAbbuseFormmer());

		abbuseIllegal.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseIllegal());
		abbuseScale.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseScale());
		abbuseValue.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseValue());
		abbuseDate.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseDate());
		abbuseLocation.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseLocation());
		abbuseLeaderNumber.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderNumber());
		abbuseLeaderAddress.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderAddress());
		abbuseLeaderRegNumber.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderRegNumber());

//		checkedName.setText(application.getInfoBean().getAppCheckInfo()
//				.getCheckedName());
//		checkedNumber.setText(application.getInfoBean().getAppCheckInfo()
//				.getCheckedNumber());
		memo.setText(application.getInfoBean().getAppCheckInfo().getMemo());
		// gpsInfo.setText(application.getInfoBean().getAppCheckInfo()
		// .getGpsInfo());

	}

	private void initKeyValue() {

		Map<String, String> formmerMap = application.getInfoBean()
				.getAbbuseFormmer();
		MapToListUtil formmerUtil = new MapToListUtil(formmerMap);
		List<KeyValue> formmmerKeyValues = formmerUtil.mapToKeyValues();
		abbuseFormmer.setOnClickListener(new ShowSingleDialogListListener(
				formmmerKeyValues, activity, abbuseFormmer));

		Map<String, String> sexMap = application.getInfoBean().getSex();
		MapToListUtil sexUtil = new MapToListUtil(sexMap);
		List<KeyValue> sexKeyValues = sexUtil.mapToKeyValues();
		abbuseSex.setOnClickListener(new ShowSingleDialogListListener(
				sexKeyValues, activity, abbuseSex));

		MapToListUtil dealUtil = new MapToListUtil(dealMap);
		List<KeyValue> dealKeyValues = dealUtil.mapToKeyValues();
		deal.setOnClickListener(new ShowMutiDialogListListener(dealKeyValues,
				activity, deal));

		MapToListUtil cardTypeUtil = new MapToListUtil(cardTypeMap);
		List<KeyValue> cardTypeKeyValues = cardTypeUtil.mapToKeyValues();
		cardType.setOnClickListener(new ShowSingleDialogListListener(
				cardTypeKeyValues, activity, cardType));

		MapToListUtil caseFromUtil = new MapToListUtil(caseFromMap);
		List<KeyValue> caseFromKeyValues = caseFromUtil.mapToKeyValues();
		caseFrom.setOnClickListener(new ShowSingleDialogListListener(
				caseFromKeyValues, activity, caseFrom));

		MapToListUtil entityTypeUtil = new MapToListUtil(entityTypeMap);
		List<KeyValue> entityTypeKeyValues = entityTypeUtil.mapToKeyValues();
		entityType.setOnClickListener(new ShowSingleDialogListListener(
				entityTypeKeyValues, activity, entityType));

		MapToListUtil legalBasisUtil = new MapToListUtil(legalBasisMap);
		List<KeyValue> legalBasisKeyValues = legalBasisUtil.mapToKeyValues();
		legalBasis.setOnClickListener(new ShowSingleDialogListListener(
				legalBasisKeyValues, activity, legalBasis));

		MapToListUtil unLicensedTypeUtil = new MapToListUtil(unLicensedTypeMap);
		List<KeyValue> unLicensedTypeKeyValues = unLicensedTypeUtil
				.mapToKeyValues();
		unLicensedType.setOnClickListener(new ShowSingleDialogListListener(
				unLicensedTypeKeyValues, activity, unLicensedType));

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
					UtilForFragment.popBackStack(activity);

				} else {
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
				getActivity().startActivityForResult(intent, 81);

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
				getActivity().startActivityForResult(intent, 83);
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
		case 81:

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(imageUri, "image/*");
			intent.putExtra("scale", true);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			getActivity().startActivityForResult(intent, 82);

			break;
		case 82:
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
		case 83:

			imageUri = data.getData(); // 获得图片的uri
			Intent intent2 = new Intent("com.android.camera.action.CROP");
			intent2.setDataAndType(imageUri, "image/*");
			intent2.putExtra("scale", true);
			intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			getActivity().startActivityForResult(intent2, 82);

			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
		title.setText("日常检查");
		super.onDestroy();
	}

}
