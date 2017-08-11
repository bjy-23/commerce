package com.wondersgroup.commerce.teamwork.keysuperrise;

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
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.teamwork.dailycheck.AppRecordDetail;
import com.wondersgroup.commerce.teamwork.dailycheck.BitMapUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.BookBean;
import com.wondersgroup.commerce.teamwork.dailycheck.CnUpload;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsInfoBean;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsInfoRadioButtonAdapter;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpCallbackListener;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpClientUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.KeyValue;
import com.wondersgroup.commerce.teamwork.dailycheck.MapToListUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.SaveCheckBean;
import com.wondersgroup.commerce.teamwork.dailycheck.ShowMutiDialogListListener;
import com.wondersgroup.commerce.teamwork.dailycheck.ShowSingleDialogListListener;
import com.wondersgroup.commerce.teamwork.dailycheck.Url;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;
import com.wondersgroup.commerce.teamwork.dailycheck.WebViewActivity;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KeyFragment extends Fragment {

	private View view;
	private LinearLayoutForListView infoList;
	private LinearLayoutForListView superviceList;
	private List<String> strings = new ArrayList<String>();
	private EtpsInfoRadioButtonAdapter adapter;
	private SuperviceItemAdapter superviceItemAdapter;
	private List<com.wondersgroup.commerce.teamwork.dailycheck.Father> fathers;

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

	// new
	// private String gpsString = "获取gps信息失败";
	// public LocationClient mLocationClient = null;
	// private TextView gpsInfo;
	private TextView trdScope;
	// new
	private int imageFlag = 0;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private List<ImageView> imageViews;
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Uri imageUri;
	private AlertDialog showimageAlertDialog;
	private AlertDialog imageAlertDialog;
	private View photoChooseView;
	private View photoShowView;
	private Boolean boolList[] = { false, false, false };
	// new
	private TextView tradeScope;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.q_keysupervise, container, false);
		activity = (AppCompatActivity) getActivity();
		application = (RootAppcation) activity.getApplication();
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setTitle("");

		TextView title = (TextView)activity.findViewById(R.id.toolbar_title);
		title.setText(application.getBigBean().getEtpsInfoVo()
				.get("etpsName"));

		// new
		// mLocationClient = application.mLocationClient;
		// mLocationClient.registerLocationListener(new BDLocationListener() {
		//
		// @Override
		// public void onReceiveLocation(BDLocation arg0) {
		// gpsString = "(" + arg0.getLatitude() + ","
		// + arg0.getLongitude() + ");" + arg0.getAddrStr();
		// // Log.e("gpsString", gpsString);
		// // gpsInfo.setText(gpsString);
		// }
		// });
		// mLocationClient.requestLocation();
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
		focusCheckMode.setTag("");
		inspector.setTag("");
		deal.setTag("");

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
		// gpsInfo.setText(gpsString);
		changeRecord = (TextView) view.findViewById(R.id.changeRecord);
		trdScope = (TextView) view.findViewById(R.id.trdScope);
		tradeScope = (TextView) view.findViewById(R.id.tradeScope);
		tradeScope.setText(application.getBigBean().getTradeScope());

		tempButton = (Button) view.findViewById(R.id.tempButton);
		realButton = (Button) view.findViewById(R.id.realButton);

		checkedName = (TextView) view.findViewById(R.id.checkedName);
		checkedNumber = (TextView) view.findViewById(R.id.checkedNumber);
		CheckUtil.checkInputPhone(checkedNumber);
		CheckUtil.limitCheckMaxCount(checkedName, Constants.inputMaxCount);
		checkedName.setText(application.getBigBean().getEtpsInfoVo()
				.get("leader"));
		checkedNumber.setText(application.getBigBean().getEtpsInfoVo()
				.get("telephone"));
		inspector = (TextView) view.findViewById(R.id.inspector);
		memo = (TextView) view.findViewById(R.id.memo);

		tempButton = (Button) view.findViewById(R.id.tempButton);

		tempButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				progressDialog.show();
				MyProgressDialog.show(activity);
				// saveCheckBean
				// new
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				saveCheckBean.setTrdScope(trdScope.getText().toString());
				saveCheckBean.getAppCheckInfo().setEtpsId(
						application.getBigBean().getEtpsId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getGpsInfoString());
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
				saveCheckBean.setAppEntInfo(adapter.getChangeMap());
				saveCheckBean.getAppCheckInfo().setCheckType("2");

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
				String address = Url.QJ_IN_USE + "saveCheck";
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
				saveCheckBean.getAppCheckInfo().setSubmitUser(
						application.getLoginUserInfo().getUserId());
				saveCheckBean.setTrdScope(trdScope.getText().toString());
				saveCheckBean.getAppCheckInfo().setEtpsId(
						application.getBigBean().getEtpsId());
				saveCheckBean.getAppCheckInfo().setGpsInfo(
						application.getGpsInfoString());
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
				String address = Url.QJ_IN_USE + "saveCheck";
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

		Map<String, String> focusIndustryTypeMap = application.getBigBean()
				.getFocusCatagory();
		MapToListUtil focusIndustryTypeUtil = new MapToListUtil(
				focusIndustryTypeMap);
		List<KeyValue> focusIndustryTypeKeyValues = focusIndustryTypeUtil
				.mapToKeyValues();
		focusIndustryType.setOnClickListener(new ShowMutiDialogListListener(
				focusIndustryTypeKeyValues, activity, focusIndustryType));

		Map<String, String> focusCheckModeMap = application.getBigBean()
				.getCheckMode();
		MapToListUtil focusCheckModeUtil = new MapToListUtil(focusCheckModeMap);
		List<KeyValue> focusCheckModeKeyValues = focusCheckModeUtil
				.mapToKeyValues();
		focusCheckMode.setOnClickListener(new ShowSingleDialogListListener(
				focusCheckModeKeyValues, activity, focusCheckMode));

		Map<String, String> dealMap = application.getBigBean().getDeal();
		MapToListUtil dealUtil = new MapToListUtil(dealMap);
		List<KeyValue> dealKeyValues = dealUtil.mapToKeyValues();
		deal.setOnClickListener(new ShowMutiDialogListListener(dealKeyValues,
				activity, deal));

		List<BookBean> users = application.getBigBean().getUserList();
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
		if (application.getBigBean() != null) {
			LinkedHashMap<String, String> map = application.getBigBean()
					.getEtpsInfoVo();
			MapToListUtil mapToListUtil = new MapToListUtil(map);
			List<EtpsInfoBean> etpsInfoBeans = mapToListUtil.mapToEtpsInfoS();
			infoList = (LinearLayoutForListView) view
					.findViewById(R.id.demo_list);
			adapter = new EtpsInfoRadioButtonAdapter(activity,
					R.layout.radio_buton_list_item, etpsInfoBeans,
					changeRecord, new LinkedHashMap<String, String>());
			infoList.setAdapter(adapter);

			fathers = application.getBigBean().getCheckMatters();
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
					UtilForFragment.popBackStackTwo(activity);
				} else {
					progressDialog.cancel();
					Toast.makeText(getActivity(), "未查询到该记录详情",
							Toast.LENGTH_SHORT).show();
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
				getActivity().startActivityForResult(intent, 51);

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
				getActivity().startActivityForResult(intent, 53);
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
		case 51:

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(imageUri, "image/*");
			intent.putExtra("scale", true);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			getActivity().startActivityForResult(intent, 52);

			break;
		case 52:
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
		case 53:

			imageUri = data.getData(); // 获得图片的uri
			Intent intent2 = new Intent("com.android.camera.action.CROP");
			intent2.setDataAndType(imageUri, "image/*");
			intent2.putExtra("scale", true);
			intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			getActivity().startActivityForResult(intent2, 52);

			break;
		default:
			break;
		}

	}

}
